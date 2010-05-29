package org.climatecollaboratorium.pangaea.vensim;

import java.util.*;


public class SimulationResults {
    
    private static float DEFAULT_FF_DIVISOR = 3.66F;


	public enum Variable {
		DEVELOPED_COUNTRIES_FF_EMISSIONS("Developed countries fossil fuel emissions","DevelopedFossilFuelEmissions", "Aggregated CO2 FF emissions[Developed Countries]", DEFAULT_FF_DIVISOR),
		DEVELOPINGA_COUNTRIES_FF_EMISSIONS("Developing A countries fossil fuel emissions","DevelopingAFossilFuelEmissions", "Aggregated CO2 FF emissions[Developing A Countries]", DEFAULT_FF_DIVISOR),
        DEVELOPINGB_COUNTRIES_FF_EMISSIONS("Developing B fossel fuel emissions","DevelopingBFossilFuelEmissions", "Aggregated CO2 FF emissions[Developing B Countries]", DEFAULT_FF_DIVISOR),
        CO2_CONCENTRATION("CO2 Concentration","AtmosphericCO2Concentration", "Atm conc CO2[\"2C\"]", 1),  
        TEMP_CHANGE("Expected temperature change","GlobalTempChange", "Temperature change from preindustrial[\"2C\"]", 1),
        BAU_CO2_CONCENTRATION("BAU CO2 Concentration","BAUCO2Concentration", "BAU atm conc CO2", 1),
        BAU_TEMP_CHANGE("Expected BAU Temperature Change","ExpectedBAUTempChange", "BAU temperature change from preindustrial", 1),
        CO2_TARGET("CO2 Target","CO2Target", "target CO2eq Scenario 2 emissions", 1),
        RADIATIVE_FORCING("Total Radiative Forcing","RadiativeForcing","Radiative Forcing[\"2C\"]",1),
        YEAR("Year","Year", "Time", 1),
		DEVELOPED_COUNTRIES_FF_EQ_EMISSIONS("Developed countries fossil fuel emissions (CO2eq)","DevelopedFossilFuelEmissions", "Aggregated CO2eq emissions[Developed Countries]", DEFAULT_FF_DIVISOR),
        ;
		/*
		
		CO2_TARGET("CO2 Target","CO2Target"), 
		YEAR("Year","Year");
		*/
;
		String name;
		String internalName;
		String vensimName;
		float divisor;

		private Variable(String name,String internalName, String vensimName, float divisor) {
			this.name = name;
			this.internalName = internalName;
			this.vensimName = vensimName;
			this.divisor = divisor;
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
		for (ScalarElement elt : elts) {
			idx.add(new ScalarElement(min));
			min += inc;
		}
		//data.put(Variable.YEAR, idx);

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
				getCollection(variable).add(new ScalarElement(Float.parseFloat(v)/variable.divisor));
			}
		}
	
	   public void addDataPoints(Variable variable,float[] vals) {
	        int idx = 0;

	            for (float v:vals) {
	                getCollection(variable).add(new ScalarElement(v/variable.divisor));
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