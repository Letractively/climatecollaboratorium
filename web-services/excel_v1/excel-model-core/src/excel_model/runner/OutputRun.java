package excel_model.runner;

import java.util.ArrayList;
import java.util.List;

import cayenne_classes.OutputParamDAO;

import excel_model.Output;
import excel_model.persistence.ServerObject;
import excel_model.persistence.ServerOutput;

public class OutputRun extends ServerOutput implements Output{

	List<String> values = new ArrayList<String>();

	public OutputRun(OutputParamDAO dao){
		super(dao);
	}

	public List<String> getValues(){
		return this.values;
	}
	public void setValues(List<String> a){
		this.values.addAll(a);
	}
	public void clearValues(){
		this.values.clear();
	}

}
