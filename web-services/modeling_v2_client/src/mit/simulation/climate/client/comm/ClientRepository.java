package mit.simulation.climate.client.comm;

import mit.simulation.climate.client.*;
import mit.simulation.climate.client.model.jaxb.ClientJaxbReference;
import mit.simulation.climate.client.model.jaxb.ResponseWrapper;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: jintrone
 * @date: May 18, 2010
 */
public class ClientRepository implements Deserializer {


    private static ClientRepository instance;
    private JAXBContext context;
    private Unmarshaller um;
    private Connector<ResponseWrapper> connector;

    private static Logger log = Logger.getLogger(ClientRepository.class);


    private ClientRepository() {
        try {
            context = JAXBContext.newInstance(ResponseWrapper.class);
            um= context.createUnmarshaller();

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    public static ClientRepository instance(String hostname, int port) throws IOException {
        if (instance==null) {
            instance = new ClientRepository();
            instance.connector = new Connector<ResponseWrapper>(instance,hostname,port);
            instance.refreshSimulations();
        }
        return instance;
    }

    public static ClientRepository instance() {
        return instance;
    }

    private Map<String,HasId> cache = new HashMap<String,HasId>();


    private <T> T cacheLookup(ClientJaxbReference ref, Class<T> clz) {
        return (T) cache.get(type(ref).getName()+ref.id);
    }

    private <T> T cacheLookup(Long id, Class<T> clz) {
        return (T) cache.get(clz.getName()+id);
    }


    public <T> T resolveReference(ClientJaxbReference ref, Class<T> clz) {
        T result = cacheLookup(ref,clz);
        if (result == null) {
            result = (T)requestObject(ref,type(ref));
        }
        if (result ==null) {
            throw new RuntimeException("Could not resolve reference for "+ref.type+":"+ref.id);
        }
        return result;
    }


    public <T extends HasId> T resolveOrDefer(ClientJaxbReference ref,Class clz) {
        T result = (T)cacheLookup(ref,clz);
        if (result == null) {
            result = (T)Proxy.newProxyInstance(clz.getClassLoader(),new Class[]{clz},new ClientProxyObject<T>(ref));
        }
        return result;
    }
    

    public static Class type(ClientJaxbReference ref) {
       if ("simulation".equals(ref.type)) {
           return Simulation.class;
       } else if ("scenario".equals(ref.type)) {
           return Scenario.class;
       } else if ("metadata".equals(ref.type)) {
           return MetaData.class;
       } else if ("variable".equals(ref.type)) {
           return Variable.class;
       }
        throw new RuntimeException("Unknown reference type exception");
    }

    public <T> T requestObject(ClientJaxbReference ref, Class<T> type) {
        return null;
    }

    public void register(HasId object) {
        Class type = null;
        if (object instanceof Simulation) {
            type = Simulation.class;
        } else if (object instanceof Scenario) {
            type = Scenario.class;
        } else if (object instanceof MetaData) {
           type = MetaData.class;
        } else if (object instanceof Variable) {
            type = Variable.class;
        } else throw new RuntimeException("Cannot register object "+object);

        cache.put(type.getName()+object.getId(),object);
    }


    public ResponseWrapper handleResponse(InputStream stream) throws JAXBException {
        return (ResponseWrapper)um.unmarshal(stream);
    }

    public void refreshSimulations() throws IOException {
        ResponseWrapper wrapper = connector.get(ModelAccessPoint.GET_SIMULATION,null);
        for (Simulation s:wrapper.sims) {
            register(s);
        }

        //register all metadata after all simulations have been cached
        for (Simulation s:wrapper.sims) {
            for (MetaData md:s.getInputs()) {
                register(md);
            }
            for (MetaData md:s.getOutputs()) {
                register(md);
            }
        }
    }

    public Set<Simulation> getAllSimulations() {

        Set<Simulation> result = new HashSet<Simulation>();
        for (HasId elt:cache.values()) {
           if (elt instanceof Simulation) result.add((Simulation) elt);
        }
        return result;
    }

    public Simulation getSimulation(Long id) {
       return cacheLookup(id,Simulation.class);
    }

    public MetaData getMetaData(Long id) {
        return cacheLookup(id,MetaData.class);
    }



    public void updateSimulation(Simulation s) throws ModelNotFoundException, IOException {
        Simulation existing = getSimulation(s.getId());
        if (existing == null) {
            throw new ModelNotFoundException("Simulation with id "+s.getId()+" could not be found");
        }
        if (s.isDirty()) {
        ResponseWrapper wrapper = connector.post(ModelAccessPoint.EDIT_SIMULATION,s.getUpdate(),String.valueOf(s.getId()));
        if (wrapper.sims.size()>0) {
            register(wrapper.sims.get(0));
        }
        } else {
           log.warn("No changes detected on updateable fields, not sending updates");
        }

    }

    public void saveScenario(Scenario s) throws ScenarioNotFoundException, IOException {
        Scenario existing = getScenario(s.getId());
        if (existing == null) {
            throw new ScenarioNotFoundException("Scenario with id "+s.getId()+" could not be found");
        }
        Map<String,String> params = new HashMap<String,String>();
        params.put("state",EntityState.PUBLIC.name());
        connector.post(ModelAccessPoint.EDIT_SCENARIO_URL,params,String.valueOf(s.getId()));
    }

    public Scenario runModel(Simulation s, Map<Long,Object> inputs, Long userid, boolean save) throws ModelNotFoundException, IOException, ScenarioNotFoundException {
        Simulation existing = getSimulation(s.getId());
        Scenario result = null;
        if (existing == null) {
            throw new ModelNotFoundException("Simulation with id "+s.getId()+" could not be found");
        }
        Map<String,String> params = new HashMap<String,String>();
        for (MetaData input:s.getInputs()) {
            Object val = inputs.get(input.getId());
            params.put(input.getInternalName(),val==null?null:val.toString());
        }
        params.put("user",String.valueOf(userid));
        params.put("simId",String.valueOf(s.getId()));


        ResponseWrapper wrapper = connector.post(ModelAccessPoint.RUN_MODEL_URL,params);
        if (wrapper.scenarios.size() > 0) {
           result = wrapper.scenarios.get(0);
        }
        if (result!=null && save) {
            saveScenario(result);
        }
        return result;
    }

     public Scenario runModelWithInputNames(Simulation s, Map<String,Object> inputs, Long userid, boolean save) throws ModelNotFoundException, IOException, ScenarioNotFoundException, MetaDataNotFoundException {
        Simulation existing = getSimulation(s.getId());
        Scenario result = null;
        if (existing == null) {
            throw new ModelNotFoundException("Simulation with id "+s.getId()+" could not be found");
        }

        Map<String,Long> inputX = new HashMap<String,Long>();
        for (MetaData m:s.getInputs()) {
          inputX.put(m.getInternalName(),m.getId());
        }

         Map<Long,Object> ninputs = new HashMap<Long,Object>();
         for (Map.Entry<String,Object> ent:inputs.entrySet()) {
             Long id = inputX.get(ent.getKey());
             if (id == null) throw new MetaDataNotFoundException("Metadata with internalname "+ent.getKey()+" not found on simulation "+s.getName());
             ninputs.put(id,ent.getValue());
         }

         return runModel(s,ninputs,userid,save);
     }

    
    public Scenario getScenario(Long id) throws IOException {
        ResponseWrapper wrapper = connector.get(ModelAccessPoint.GET_SCENARIO,null,String.valueOf(id));
        if (wrapper.scenarios.size() > 0) {
            return wrapper.scenarios.get(0);
        } else {
            return null;
        }

    }

    @Override
    public ResponseWrapper deserialize(InputStream stream) {
        try {
            return (ResponseWrapper)um.unmarshal(stream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

   


    public static enum ModelAccessPoint implements RestAccessPoint {
        RUN_MODEL_URL("/simulation-servlet/rest/runsim"),
        GET_SIMULATION("/simulation-servlet/rest/simulation"),
        GET_SCENARIO("/simulation-servlet/rest/scenario"),
        EDIT_SIMULATION("/simulation-servlet/rest/editsim"),
        EDIT_SCENARIO_URL("/simulation-servlet/rest/scenariostate");

        String url;

        ModelAccessPoint(String s) {
           this.url = s;
        }

        public String create(InetAddress base, int port, String... params) {
            StringBuffer buf = new StringBuffer("http://");
            buf.append(base.getHostName());
            buf.append(":");
            buf.append(port);
            buf.append(url);
            for (String p:params) {
                buf.append("/");
                buf.append(p);
            }
            return buf.toString();
        }




    }

}
