package excel_model.runner;

import cayenne_classes.ExcelModelDAO;
import cayenne_classes.InputParamDAO;
import cayenne_classes.OutputParamDAO;
import excel_model.Input;
import excel_model.Model;
import excel_model.Output;
import excel_model.persistence.ServerFactory;

public class RunnerFactory implements ServerFactory {

	private static RunnerFactory instance;
	
	private RunnerFactory() {}

	public static RunnerFactory instance() {
		if (instance == null) {
			instance = new RunnerFactory();
		}
		return instance;
	}

	@Override
	public Model get(ExcelModelDAO dao) {
		return new ModelRun(dao);
	}

	@Override
	public Input get(InputParamDAO dao) {
		return new InputRun(dao);
	}

	@Override
	public Output get(OutputParamDAO dao) {
		return new OutputRun(dao);
	}



}
