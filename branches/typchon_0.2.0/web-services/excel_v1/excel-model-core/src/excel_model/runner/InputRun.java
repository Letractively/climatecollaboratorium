package excel_model.runner;

import java.util.ArrayList;

import cayenne_classes.InputParamDAO;

import excel_model.Input;
import excel_model.persistence.ServerFactory;
import excel_model.persistence.ServerInput;
import excel_model.persistence.ServerObject;

public class InputRun extends ServerInput {


	public String value;

	public InputRun(InputParamDAO dao) {
		super(dao);
	}

	public String getValue(){
		return this.value;
	}

	public void setValue(String s){
		this.value = s;
	}

}
