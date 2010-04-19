import javax.xml.bind.annotation.XmlElement;

import mit.simulation.climate.proxy.pangaea.SimulationResults;

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