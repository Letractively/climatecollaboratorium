package mit.simulation.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.persistence.ServerScenario;
import mit.simulation.climate.model.persistence.ServerTuple;
import mit.simulation.climate.model.persistence.ServerVariable;

public class CsvDumper {


	Map<String,ServerVariable> map = new HashMap<String,ServerVariable>();


	public CsvDumper(String input, ServerScenario scenario) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(input));
		processScenario(scenario);
		String line;
		List<Number> years = new ArrayList<Number>();
		List<String> varnames = new ArrayList<String>();
		Map<ServerVariable,List<Number>> valmap = new HashMap<ServerVariable,List<Number>>();

		while ((line = in.readLine())!=null) {
			String[] vals = line.split(",");
			if (varnames.size() == 0) {
				for (int i =1;i<vals.length;i++) {
					varnames.add(vals[i].trim());
				}
			} else {
				years.add(Integer.parseInt(vals[0]));
				for (int i = 1;i<vals.length;i++) {
					ServerVariable var = map.get(varnames.get(i-1));
					Number n = ServerTuple.parseNumber(var.getMetaData().getProfile()[1],vals[i]);
					List<Number> nums = valmap.get(var);
					if (nums == null) {
						nums = new ArrayList<Number>();
						valmap.put(var, nums);
					}
					nums.add(n);

				}
			}
		}

		for (Map.Entry<ServerVariable, List<Number>> ent:valmap.entrySet()) {

			ServerVariable var = ent.getKey();
			for (int i=0;i<ent.getValue().size();i++) {
				ServerTuple tuple = new ServerTuple(new Number[] {years.get(i),ent.getValue().get(i)});
				var.addValue(tuple);
			}
		}


	}

	public void processScenario(Scenario scenario) {
		for (MetaData md:scenario.getSimulation().getInputs()) {
			ServerVariable sv =  new ServerVariable(md);
			map.put(md.getInternalName(),sv);
			scenario.addToInput(sv);
		}
		for (MetaData md:scenario.getSimulation().getOutputs()) {
			ServerVariable sv =  new ServerVariable(md);
			map.put(md.getInternalName(), sv);
			scenario.addToOutput(sv);
		}
	}

}
