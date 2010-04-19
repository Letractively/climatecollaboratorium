package mit.simulation.climate.model;

import java.net.URL;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mit.simulation.climate.model.jaxb.SimulationJAXBDelegate;

@XmlJavaTypeAdapter(SimulationJAXBDelegate.Adapter.class)
public interface Simulation extends HasId {

	public String getId();

	public String getDescription();
	public void setDescription(String description);

	public URL getURL();
	public void setURL(String url);
	public void setURL(URL url);


	public List<MetaData> getInputs();
	public void addInput(MetaData md);

	public List<MetaData> getOutputs();
	public void addOutput(MetaData md);

	public String getName();
	public void setName(String name);

}
