package excel_model.persistence;

import cayenne_classes.ExcelModelDAO;
import cayenne_classes.InputParamDAO;
import cayenne_classes.OutputParamDAO;
import excel_model.Input;
import excel_model.Model;
import excel_model.Output;
import excel_model.parser.InputParse;
import excel_model.parser.ModelParse;
import excel_model.parser.OutputParse;



public interface ServerFactory {



	public Model get(ExcelModelDAO dao);

	public Input get(InputParamDAO dao);

	public Output get(OutputParamDAO dao);

}
