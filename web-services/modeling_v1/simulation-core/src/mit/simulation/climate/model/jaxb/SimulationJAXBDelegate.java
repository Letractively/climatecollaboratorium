package mit.simulation.climate.model.jaxb;

import java.net.URL;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Simulation;

@XmlRootElement
public class SimulationJAXBDelegate implements Simulation {

	private Simulation src;


	public SimulationJAXBDelegate() {

	}

	public SimulationJAXBDelegate(Simulation src) {
		this.src = src;
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
	@XmlElement(name="metadata")
	public List<MetaData> getInputs() {
		return src.getInputs();
	}

	@XmlElement(name="name")
	public String getName() {
		return src.getName();
	}

	@XmlElementWrapper(name="outputs")
	@XmlElement(name="metadata")
	public List<MetaData> getOutputs() {
		return src.getOutputs();
	}

	@XmlElement(name="url")
	public URL getURL() {
		return src.getURL();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setURL(String url) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setURL(URL url) {
		// TODO Auto-generated method stub

	}


	public static class Adapter extends XmlAdapter<SimulationJAXBDelegate,Simulation> {

		@Override
		public SimulationJAXBDelegate marshal(Simulation v) throws Exception {
			return new SimulationJAXBDelegate(v);
		}

		@Override
		public Simulation unmarshal(SimulationJAXBDelegate v) throws Exception {
			return v;
		}

	}



}
