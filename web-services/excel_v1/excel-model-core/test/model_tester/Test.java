package model_tester;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import excel_model.Input;
import excel_model.Model;
import excel_model.Output;

public class Test {
	
	public static void main(String[] args){
		
		InputStream input = Test.class.getResourceAsStream("jacoby_md.xls");
		Model m = new Model(input);
		Map<String,Double> myMap = new HashMap<String,Double>();
		myMap.put("delta", .1);
		myMap.put("gamma", .1);
		m.update(myMap);
		for (Input i:m.getInputs()){
			System.out.println("input name = "+i.getIntName());
			System.out.println("input val = "+i.getValue());
		}
//		for(Output o:m.getOutputs()){
//			System.out.println("output name = "+o.getIntName());
//			for(Double d:o.getValues()){
//				System.out.println("val = "+d);
//			}
//		}
		
	}
}

