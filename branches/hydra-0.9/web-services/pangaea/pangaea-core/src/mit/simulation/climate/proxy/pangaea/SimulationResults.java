package mit.simulation.climate.proxy.pangaea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.log4j.Logger;

@XmlJavaTypeAdapter(JaxbResultsAdapter.Adapter.class)
public class SimulationResults {

	private static Logger log = Logger.getLogger(SimulationResults.class);

	public enum Variable {
		DEVELOPED_COUNTRIES_FF_EMISSIONS(
				"Developed countries fossil fuel emissions","DevelopedFossilFuelEmissions"), DEVELOPINGA_COUNTRIES_FF_EMISSIONS(
				"Developing A countries fossil fuel emissions","DevelopingAFossilFuelEmissions"), DEVELOPINGB_COUNTRIES_FF_EMISSIONS(
				"Developing B fossel fuel emissions","DevelopingBFossilFuelEmissions"), CO2_CONCENTRATION(
				"CO2 Concentration","AtmosphericCO2Concentration"), BAU_CO2_CONCENTRATION(
				"BAU CO2 Concentration","BAUCO2Concentration"), CO2_TARGET("CO2 Target","CO2Target"), TEMP_CHANGE(
				"Expected temperature change","GlobalTempChange"), BAU_TEMP_CHANGE(
				"Expected BAU Temperature Change","ExpectedBAUTempChange"), YEAR("Year","Year");

		String name;
		String internalName;

		private Variable(String name,String internalName) {
			this.name = name;
			this.internalName = internalName;
		}

		public String getInternalName() {
			return internalName;
		}


		public String toString() {
			return name;
		}

	}

	private Map<Variable, List<ScalarElement>> data = new HashMap<Variable, List<ScalarElement>>();

	private List<ScalarElement> getCollection(Variable v) {
		if (!data.containsKey(v)) {
			data.put(v, new ArrayList<ScalarElement>());
		}
		return data.get(v);
	}

	public void addDataPoint(Variable v, Number idx, Number val) {
		getCollection(v).add(new ScalarElement(val));
	}

	public void createIndexFor(Variable v, int min, int max) {
		List<ScalarElement> elts = getCollection(v);
		List<ScalarElement> idx = new ArrayList<ScalarElement>();
		int inc = (max - min) / (elts.size() - 1);
		log.debug("Num elements = " + elts.size());
		for (ScalarElement elt : elts) {
			idx.add(new ScalarElement(min));
			min += inc;
		}
		data.put(Variable.YEAR, idx);

	}

	// public void updateIndices(Variable v, int min, int max) {
	// List<IndexedElement> elts = getCollection(v);
	//
	// int inc = (max - min) / (elts.size() - 1);
	// log.debug("Num elements = " + elts.size());
	// for (IndexedElement elt : elts) {
	// elt.idx = min;
	// min += inc;
	// }
	// }
	//
	// public void updateIndices(Variable v, float min, float max) {
	// List<IndexedElement> elts = getCollection(v);
	// float inc = (max - min) / (float) elts.size();
	// for (IndexedElement elt : elts) {
	// elt.idx = min;
	// min += inc;
	// }
	// }

	public void addDataPoints(Variable variable,String[] vals) {
		int idx = 0;

			for (String v:vals) {
				getCollection(variable).add(new ScalarElement(Float.parseFloat(v)));
			}
		}



	public List<ScalarElement> get(Variable v) {
		return data.get(v);
	}

	public Set<Variable> getPopulatedVariables() {
		return data.keySet();
	}

	public String toString() {
		String result = "";
		for (Variable v : data.keySet()) {
			result += v + ":" + data.get(v) + "\n";
		}
		return result;
	}

	public static class ScalarElement {

		public Number val;

		public ScalarElement(Number val) {
			this.val = val;
		}

		public String toString() {
			return "[" + val + "]";
		}
	}

	public static class IndexedElement {

		public Number idx;
		public Number val;

		public IndexedElement(Number idx, Number val) {
			this.idx = idx;
			this.val = val;
		}

		public String toString() {
			return "[" + idx + "," + val + "]";
		}

	}

}