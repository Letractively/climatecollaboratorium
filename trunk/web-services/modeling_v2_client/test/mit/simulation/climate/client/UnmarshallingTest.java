package mit.simulation.climate.client;

import mit.simulation.climate.client.comm.ClientRepository;
import mit.simulation.climate.client.comm.MetaDataNotFoundException;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import mit.simulation.climate.client.comm.ScenarioNotFoundException;
import mit.simulation.climate.client.model.impl.ClientSimulation;
import mit.simulation.climate.client.model.jaxb.ResponseWrapper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: jintrone
 * @date: May 17, 2010
 */
public class UnmarshallingTest {


    private static Logger log = Logger.getLogger(UnmarshallingTest.class);

    @Test
    public void testUnmarshall() throws FileNotFoundException, JAXBException {

        JAXBContext context = JAXBContext.newInstance(ResponseWrapper.class);
        System.setProperty("jaxb.debug", "true");


        Unmarshaller um = context.createUnmarshaller();
        um.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
        ResponseWrapper o = (ResponseWrapper) um.unmarshal(ClientSimulation.class.getResourceAsStream("/AllSimulations.xml"));

    }

    @Test
    public void testRepositoryRetrieval_Simulations() throws IOException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Set<Simulation> sims = repo.getAllSimulations();
        log.info("Retrieved " + sims.size() + " simulations:");
        for (Simulation sim : sims) {
            log.info(sim.getName());
            StringBuffer buf = new StringBuffer();
            buf.append("-- Inputs\n");
            for (MetaData md : sim.getInputs()) {
                buf.append("---- ").append(md.getId()).append(":").append(md.getName()).append(" - ").append(md.getDescription());
                buf.append("\n");
            }
            log.info(buf.toString());
            buf = new StringBuffer();
            buf.append("-- Outputs\n");
            for (MetaData md : sim.getOutputs()) {
                buf.append("---- ").append(md.getId()).append(":").append(md.getName()).append(" - ").append(md.getDescription());
                buf.append(" : isIndex - ").append(md.getIndex());
                buf.append(" : indexingmd - "+(md.getIndexingMetaData()!=null?md.getIndexingMetaData().getId():"<none>"));

                buf.append("\n");
            }
            buf.append("\n");
            log.info(buf);
        }

    }

    @Test
    public void testRepositoryRetrieval_Scenario() throws IOException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);

        Scenario s = repo.getScenario(2703l);
        Assert.assertNotNull(s);
        log.info("Scenario: " + s.getName());
        log.info(getScenarioString(s));

    }


    @Test
    public void testCompositeModelRun() throws IOException, ScenarioNotFoundException, ModelNotFoundException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);

        Simulation sim = repo.getSimulation(760L);

        Map<Long, Object> inputs = new HashMap<Long, Object>();
        //developed
        inputs.put(3027L, "2012");  //start year
        inputs.put(3022L, "2013");  //target year
        inputs.put(3028L, "-.5");   //target

        //developing a
        inputs.put(3024L, "2012");
        inputs.put(3020L, "2013");
        inputs.put(3029L, "-.5");

        //developing b
        inputs.put(3031L, "2012");
        inputs.put(3026L, "2013");
        inputs.put(3032L, "0");


        inputs.put(3034L, "0.50");  //sequestration (afforestation)
        inputs.put(3030L, "0.50");  //deforestation
        Scenario scenario = repo.runModel(sim, inputs, 1L, true);
        log.info("Scenario: "+scenario.getName()+" id:"+scenario.getId());
        log.info(getScenarioString(scenario));
        Assert.assertEquals(EntityState.TEMPORARY,scenario.getState());

    }
       @Test
     public void testCompositeModelRun2() throws IOException, ScenarioNotFoundException, ModelNotFoundException, MetaDataNotFoundException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Simulation sim = repo.getSimulation(981L);

        Map<String, Object> inputs = new HashMap<String, Object>();
        //developed
        inputs.put("Developed_Nations_Change_Start_Year_input2", "2012");  //start year
        inputs.put("Developed_Nations_Change_Target_Year_input3", "2050");  //target year
         inputs.put("US_EU_Emissions_Change_input0","100"); //US & EU
         inputs.put("Other_Developed_Change_input1", "100");   //other developed change

        //developing a
        inputs.put("Developing_Nations_Start_Year_input6", "2012");
        inputs.put("Developing_Nations_Target_Year_input7", "2013");
        inputs.put("China_India_Emissions_Change_input4", "100"); //china, india
        inputs.put("Other_Developing_Change_input5","100"); //other developing change 

        //developing b
        inputs.put("Other_Nations_Start_Year_input10","2012");
        inputs.put("Other_Nations_Target_Year_input11","2050");
        inputs.put("Bloc_A_Emissions_Change_input8", "100"); //bloc a
        inputs.put("Bloc_B_Emissions_Change_input9", "100"); //bloc b


        inputs.put("Target Sequestration", "0.50");  //sequestration (afforestation)
        inputs.put("Global land use emissions change", "0.50");  //deforestation

        inputs.put("Percent_Transfer_from_Developed_to_Developing_input0", "50"); //percent transfer from developed to developing
        Scenario scenario = repo.runModelWithInputNames(sim, inputs, 1L, true);
        log.info("Scenario: "+scenario.getName()+" id:"+scenario.getId());
        log.info(getScenarioString(scenario));
        Assert.assertEquals(EntityState.TEMPORARY,scenario.getState());

    }

    @Test
    public void testTypeField() throws IOException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
         Set<Simulation> sims = repo.getAllSimulations();
        log.info("Retrieved " + sims.size() + " simulations:");
        for (Simulation s:repo.getAllSimulations()) {
            log.info("Retrieved: "+s.getName()+" - "+s.getId());
        }
        Simulation sim = repo.getSimulation(920L);
        Assert.assertNotNull(sim);
        Assert.assertEquals("test test",sim.getType());
    }

   

    @Test
    public void testModelUpdate() throws IOException, ScenarioNotFoundException, ModelNotFoundException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Simulation s = repo.getSimulation(621L);
        String name = s.getName();
        s.setName("fooey");
        repo.updateSimulation(s);

        s = repo.getSimulation(621L);
        Assert.assertEquals("fooey",s.getName());

        s.setName(name);
        repo.updateSimulation(s);

        s = repo.getSimulation(621L);
        Assert.assertEquals(name,s.getName());

    }


    private static String getScenarioString(Scenario s) {
        List<Variable> inputs = s.getInputSet();
        StringBuffer buf = new StringBuffer();
        buf.append("Inputs\n");
        for (Variable v : inputs) {
            buf.append(v.getMetaData().getName()).append(":").append(v.getMetaData().getId()).append(":");
            buf.append(v.getValue().toString());
            buf.append("\n");
        }
        buf.append("\n");
        List<Variable> outputs = s.getOutputSet();
        buf.append("Outputs\n");
        for (Variable v : outputs) {
            buf.append(v.getMetaData().getName()).append(":");
            buf.append(v.getValue().toString()).append(":");
            buf.append("\n");
        }
        return buf.toString();
    }

   


}
