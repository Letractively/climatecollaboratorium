package mit.simulation.climate.proxy.pangaea;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;

import mit.simulation.climate.proxy.pangaea.SimulationResults.IndexedElement;
import mit.simulation.climate.proxy.pangaea.SimulationResults.ScalarElement;

public class JaxbResultsAdapter {

	private static Logger log = Logger.getLogger(JaxbResultsAdapter.class);

	@XmlElement(name="DevelopedFossilFuelEmissions")
	public String developedCountFF;

	@XmlElement(name="DevelopingAFossilFuelEmissions")
	public String developingCountFF;

	@XmlElement(name="AtmosphericCO2Concentration")
	public String co2Concentration;

	@XmlElement(name="DevelopingBFossilFuelEmissions")
	public String rowFFEmissions;

	@XmlElement(name="CO2Target")
	public String co2Target;

	@XmlElement(name="GlobalTempChange")
	public String tempChange;

	public JaxbResultsAdapter() {

	}

	public JaxbResultsAdapter(SimulationResults results) {
		developingCountFF = constructString(results.get(SimulationResults.Variable.DEVELOPINGA_COUNTRIES_FF_EMISSIONS));
		developedCountFF = constructString(results.get(SimulationResults.Variable.DEVELOPED_COUNTRIES_FF_EMISSIONS));
		co2Concentration = constructString(results.get(SimulationResults.Variable.CO2_CONCENTRATION));
		co2Target = constructString(results.get(SimulationResults.Variable.CO2_TARGET));
		rowFFEmissions = constructString(results.get(SimulationResults.Variable.DEVELOPINGB_COUNTRIES_FF_EMISSIONS));
		tempChange = constructString(results.get(SimulationResults.Variable.TEMP_CHANGE));
	}

	public String constructString(List list) {
		String result = "[";
		for (Object elt:list) {
			result+=elt.toString();
		}
		result+="]";
		return result;
	}

	public static class Adapter extends XmlAdapter<JaxbResultsAdapter,SimulationResults> {

		@Override
		public JaxbResultsAdapter marshal(SimulationResults v) throws Exception {
			log.debug("Marshalling results");
			return new JaxbResultsAdapter(v);
		}

		@Override
		public SimulationResults unmarshal(JaxbResultsAdapter v)
				throws Exception {
			// TODO Auto-generated method stub
			return null;
		}



	}

}
