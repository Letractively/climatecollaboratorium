package mit.simulation.climate.model.jaxb;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;

import mit.simulation.climate.model.Variable;

@XmlRootElement
public class ScenarioJAXBDelegate implements Scenario {

	private Scenario src;

	public ScenarioJAXBDelegate() {

	}

	public ScenarioJAXBDelegate(Scenario src) {
		this.src = src;
	}

	@XmlElement(name="authorid")
	public String getAuthor() {
		return src.getAuthor();
	}

	@XmlElement(name="creation")
	public Date getCreation() {
		return src.getCreation();
	}

	@XmlElement(name="description")
	public String getDescription() {
		return src.getDescription();
	}

	@XmlAttribute(name="id")
	public String getId() {
		return src.getId();
	}

	@XmlElementWrapper(name="inputs")
	@XmlElement(name="variable")
	public List<Variable> getInputSet() {
		return src.getInputSet();
	}


	public List<Variable> getOutputSet() {
		return src.getOutputSet();
	}

	@XmlElementWrapper(name="outputs")
	@XmlElement(name="variable")
	public List<Variable> getCombinedOutputs() {
		return src.getCombinedOutputs();
	}

	@XmlElement(name="simulation")
	@XmlJavaTypeAdapter(JaxbReference.Adapter.class)
	public Simulation getSimulation() {
		return src.getSimulation();
	}

	@XmlElement(name="state")
	public EntityState getState() {
		return src.getState();
	}

	@Override
	public void addToInput(Variable v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addToOutput(Variable v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAuthor(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCreation(Date d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDescription(String desc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSimulation(Simulation s) {
		// TODO Auto-generated method stub

	}


	public static class Adapter extends XmlAdapter<ScenarioJAXBDelegate,Scenario> {

		@Override
		public ScenarioJAXBDelegate marshal(Scenario v) throws Exception {
			return new ScenarioJAXBDelegate(v);
		}

		@Override
		public Scenario unmarshal(ScenarioJAXBDelegate v) throws Exception {
			return v;
		}


	}


	@XmlElement(name="name")
	public String getName() {
		return src.getName();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}



	@Override
	public void setState(EntityState name) {
		// TODO Auto-generated method stub

	}




}
