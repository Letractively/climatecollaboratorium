package excel_model;



import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public interface Model {
	
	public List<Input> getInputs();
	public void addInput(Input i);

	public List<Output> getOutputs();
	public void addOutput(Output o);

	public void setName(String s);
	public String getName();

	public HSSFWorkbook getWorkbook() throws IOException;

	public void setPath(String s);
	public String getPath();

	public void setWorksheetIndex(int i);
	public int getWorksheetIndex();
	
	public Integer getId();
}
