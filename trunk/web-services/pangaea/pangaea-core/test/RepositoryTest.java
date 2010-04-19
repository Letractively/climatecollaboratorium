

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;

import junit.framework.TestCase;
import mit.simulation.climate.proxy.pangaea.PangaeaConnection;
import mit.simulation.climate.proxy.pangaea.SimulationInput;
import mit.simulation.climate.proxy.pangaea.SimulationResults;

import org.apache.log4j.Logger;

public class RepositoryTest extends TestCase {

	static Logger log= Logger.getLogger(RepositoryTest.class);


	public void testMarshaller() {
		PangaeaConnection con = new PangaeaConnection();
		SimulationInput input = new SimulationInput();
		input.setVariable(SimulationInput.Variable.DEVELOPED_FF_CHANGE, 0.1);
		input.setVariable(SimulationInput.Variable.DEVELOPINGA_FF_CHANGE, 0.1);
		SimulationResults result = con.submit(input);
		Wrapper wrapper = new Wrapper();
		wrapper.results = result;

		JAXB.marshal(wrapper, System.err);
	}

	public static class Wrapper {

		@XmlElement(name="results")
		public SimulationResults results;
	}




}
