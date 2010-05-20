package mit.simulation.climate.model.client;

import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Simulation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@XmlRootElement(name="simulation")
public class ClientSimulation implements Simulation {

    private Long id;
    private boolean composite;
    private List<MetaData> inputs = null;
    private String name;
    private List<MetaData> outputs = null;
    private Date creation;
    private EntityState state;
    private String description;
    private Set<Simulation> children = null;
    private URL url;



    public ClientSimulation() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComposite(boolean composite) {
        this.composite = composite;
    }

    public void setInputs(List<MetaData> inputs) {
        this.inputs = inputs;
    }

    public void setOutputs(List<MetaData> outputs) {
        this.outputs = outputs;
    }

    public void setChildren(List<Simulation> children) {
        this.children = new HashSet<Simulation>(children);

    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @XmlElement(name="description")
    public String getDescription() {
        return description;
    }

    @XmlAttribute(name="id")
    public Long getId() {
        return id;
    }

    @XmlAttribute(name="composite")
        public boolean isComposite() {
         return composite;
        }



    @XmlElementWrapper(name="inputs")
    @XmlElement(name="metadata")
    public List<MetaData> getInputs() {
        if (inputs == null) {
            inputs=  new ArrayList<MetaData>();
        }
        return inputs;
    }

    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    @XmlElementWrapper(name="outputs")
    @XmlElement(name="metadata")
    public List<MetaData> getCombinedOutputs() {
        return getOutputs();
    }

    @XmlElementWrapper(name="children")
    @XmlElement(name="childsim")
    @XmlJavaTypeAdapter(ClientJaxbReference.SimulationAdapter.class)
    public Set<Simulation> getChildren() {
        if (children ==null) {
            children = new HashSet<Simulation>();

        }
        return children;
    }

    public List<MetaData> getOutputs() {
       if (outputs == null) {
           outputs = new ArrayList<MetaData>();
       }
        return outputs;
    }

    @XmlElement(name="url")
    public URL getURL() {
        return url;
    }

    @XmlElement(name="state")
    public EntityState getState() {
        return state;
    }


    @XmlElement(name="creation")
    public Date getCreation() {
        return creation;
    }

    @Override
    public void addInput(MetaData md) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addOutput(MetaData md) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setDescription(String description) {
        this.description = description;

    }

    @Override
    public void setName(String name) {
        this.name = name;

    }

    @Override
    public void setURL(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void setURL(URL url) {
        this.url = url;

    }


    public static class Adapter extends XmlAdapter<ClientSimulation,Simulation> {

        @Override
        public ClientSimulation marshal(Simulation v) throws Exception {
            return null;
        }

        @Override
        public Simulation unmarshal(ClientSimulation v) throws Exception {
            return v;
        }

    }


    @Override
    public void setState(EntityState name) {
        this.state = name;

    }



    @Override
    public void setCreation(Date d) {
       this.creation = d;

    }







}