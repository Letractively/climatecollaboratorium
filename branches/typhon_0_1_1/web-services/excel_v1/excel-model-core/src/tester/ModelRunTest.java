package tester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import excel_model.Input;
import excel_model.Output;
import excel_model.persistence.ServerModel;
import excel_model.persistence.ServerRepository;
import excel_model.runner.InputRun;
import excel_model.runner.ModelRun;
import excel_model.runner.OutputRun;

public class ModelRunTest {

	public static void main(String[] args) throws IOException{

		ModelRun model = new ModelRun(ServerRepository.instance().findModelById("3900"));
		model.getInputsFromWorkbook();
		Map<String,String> newVals = new HashMap<String,String>();
//		newVals.put("T", "2");
//		model.inputNewVals(newVals);
//		model.getInputsFromWorkbook();
		model.getOutputsFromWorkbook();
		System.out.println("type = "+model.getInputs().get(0).getName());
//		for (Input i:model.getInputs()){
//			System.out.println(((InputRun)i).getValue());;
//		}
		System.out.println("outputs size = "+model.getInputs().size());
		for (Output o:model.getOutputs()){
			System.out.println("outputName = "+o.getName());
			for (String d:((OutputRun)o).getValues()){
				System.out.println(d);
			}
		}
	}

}
