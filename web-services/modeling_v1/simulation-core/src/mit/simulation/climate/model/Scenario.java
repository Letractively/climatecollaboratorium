package mit.simulation.climate.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mit.simulation.climate.model.jaxb.MetaDataJAXBDelegate;
import mit.simulation.climate.model.jaxb.ScenarioJAXBDelegate;

@XmlJavaTypeAdapter(ScenarioJAXBDelegate.Adapter.class)
public interface Scenario extends HasId {

	public String getId();

	public User getAuthor();
	public void setAuthor(User u);

	public Date getCreation();
	public void setCreation(Date d);

	public Simulation getSimulation();
	public void setSimulation(Simulation s);

	public String getDescription();
	public void setDescription(String desc);

	public List<Variable> getInputSet();
	public List<Variable> getOutputSet();

	public void addToInput(Variable v);
	public void addToOutput(Variable v);

	public void setName(String name);
	public String getName();

}
