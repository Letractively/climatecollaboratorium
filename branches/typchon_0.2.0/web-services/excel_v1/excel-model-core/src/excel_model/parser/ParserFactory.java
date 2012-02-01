package excel_model.parser;

import cayenne_classes.ExcelModelDAO;
import cayenne_classes.InputParamDAO;
import cayenne_classes.OutputParamDAO;
import excel_model.Input;
import excel_model.Model;
import excel_model.Output;
import excel_model.persistence.ServerFactory;

public class ParserFactory implements ServerFactory {

	private static ParserFactory instance;

	private ParserFactory() {
	}

	public static ParserFactory instance() {
		if (instance == null) {
			instance = new ParserFactory();
		}
		return instance;
	}

	@Override
	public Model get(ExcelModelDAO dao) {
		return new ModelParse(dao);
	}

	@Override
	public Input get(InputParamDAO dao) {
		return new InputParse(dao);
	}

	@Override
	public Output get(OutputParamDAO dao) {
		return new OutputParse(dao);
	}



}
