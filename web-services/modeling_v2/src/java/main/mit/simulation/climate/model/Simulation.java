package mit.simulation.climate.model;

import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mit.simulation.climate.model.jaxb.SimulationJAXBDelegate;

@XmlJavaTypeAdapter(SimulationJAXBDelegate.Adapter.class)
public interface Simulation extends HasId {

    public Long getId();

    public String getDescription();
    public void setDescription(String description);

    public URL getURL();
    public void setURL(String url);
    public void setURL(URL url);


    public Date getCreation();
    public void setCreation(Date d);

    public List<MetaData> getInputs();
    public void addInput(MetaData md);

    public List<MetaData> getOutputs();
    public void addOutput(MetaData md);

    public List<MetaData> getCombinedOutputs();

    public String getName();
    public void setName(String name);

    public void setState(EntityState name);
    public EntityState getState();



    //public boolean isComposite();

    public void setType(String type);
    public String getType();
}
