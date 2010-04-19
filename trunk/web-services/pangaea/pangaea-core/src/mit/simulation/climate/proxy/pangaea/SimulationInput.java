package mit.simulation.climate.proxy.pangaea;

import java.util.HashMap;
import java.util.Map;

public class SimulationInput {


	private static final Map<String,Variable> nameVarMap = new HashMap<String,Variable>();

	public enum Variable {

		DEVELOPED_FF_CHANGE("Developed countries fossil fuel emissions","D_Pct change in Developed FF emissions",234),
		DEVELOPED_FF_START("Developed countries fossil fuel emissions start reduction year","D_Developed start year",2012),
		DEVELOPED_FF_TARGET("Developed countries fossil fuel emissions target reduction year","D_Developed target year",2050),

		DEVELOPINGA_FF_CHANGE("Developing countries fossil fuel emissions","D_Pct change in Developing A FF emissions",123),
		DEVELOPINGA_FF_START("Developing countries fossil fuel emissions start reduction year","D_Developing A start year",2012),
		DEVELOPINGA_FF_TARGET("Developing countries fossil fuel emissions target reduction year","D_Developing A target year",2050),

		DEVELOPINGB_FF_CHANGE("Rest of the world fossel fuel emissions","D_Pct change in Developing B FF emissions",-86),
		DEVELOPINGB_FF_START("Rest of the world fossil fuel emissions start reduction year","D_Developing B start year",2012),
		DEVELOPINGB_FF_TARGET("Rest of the world fossil fuel emissions target reduction year","D_Developing B target year",2050),

		DEFORESTATION("Deforestation","D_Global land use change",0.6349999904632568),
		AFFORESTATION("Afforestation","D_Target Sequestration",0.0);


		String name;
		String internalName;
		Number defvalue;


		private Variable(String name, String internalName, Number val) {
			this.name = name;
			this.internalName = internalName;
			this.defvalue = val;
			nameVarMap.put(internalName, this);
		}

		public String internalName() {
			return internalName;
		}

		public Number defaultValue() {
			return defvalue;
		}

		public String toString() {
			return name;
		}

	}


	private Map<Variable,Number> data = new HashMap<Variable,Number>();

	public SimulationInput() {
		for (Variable v:Variable.values()) {
			setVariable(v,null);
		}
	}


	public void setVariable(Variable v, Number val) {
		data.put(v, val);
	}

	public Number getValue(Variable v) {
		return data.get(v)==null?v.defvalue:data.get(v);
	}

	public Map<Variable,Number> getAllVariables() {
		return data;
	}


}