import javax.xml.bind.annotation.XmlElement;

import org.climatecollaboratorium.pangaea.vensim.SimulationResults;


/**
 *
 */

public class Wrapper {

	@XmlElement(name="simulationdata")
	public SimulationResults result;

	public Wrapper() {

	}

	public Wrapper(SimulationResults result) {
		this.result = result;
	}

}