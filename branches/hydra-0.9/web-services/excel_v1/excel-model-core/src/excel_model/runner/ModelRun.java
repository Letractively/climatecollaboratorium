package excel_model.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import cayenne_classes.ExcelModelDAO;
import excel_model.Input;
import excel_model.Output;
import excel_model.persistence.ServerModel;

public class ModelRun extends ServerModel {

	private static Logger log = Logger.getLogger(ModelRun.class);

	/**
	  * create Model object
	  * @param pathname: path to excel file to parse
	  * @param inputMetaData: boolean indicating whether metadata is included
	  * @throws IOException
	  */
	public ModelRun(ExcelModelDAO dao) {
		super(dao,RunnerFactory.instance());
	}

	/*
	 * takes a mapping input names to new values and changes the input's
	 * stored values; then puts the new values into the spread sheet and gets
	 * the new output values; assumes the inputs and outputs are in specific
	 * locations
	 */
	public void inputNewVals(Map<String,String> newVals) throws IOException{
		// loop through all the new inputs values and change
		// the current input values to these
		
		for (Input i:getInputs()){
			log.debug("Looking for "+i.getInternalName());
			if (newVals.containsKey(i.getInternalName())){
				log.debug("Identified input with internal name "+i.getInternalName());
				String newVal = newVals.get(i.getInternalName());
				((InputRun)i).setValue(newVal);
				changeCell(i,newVal);
			}
		}

		// updates all of the formula cells in the excel file to display the
		// changes that were made to the parameter values
		FormulaEvaluator evaluator = getWorkbook().getCreationHelper().createFormulaEvaluator();
		for(Row r : getWorksheet()) {
	        for(Cell c : r) {
	            if(c.getCellType() == Cell.CELL_TYPE_FORMULA) {
	                evaluator.evaluateFormulaCell(c);
	            }
	        }
	    }

		//JEI - commented out - why loop through all sheets to update?
//		    for(int sheetNum = 0; sheetNum < getWorkbook().getNumberOfSheets(); sheetNum++) {
//			    HSSFSheet updateSheet = getWorkbook().getSheetAt(sheetNum);
//			}

	}

	/*
	 * takes a value and an InputParamDAO and replaces the current input
	 * in the spreadsheet with the new value
	 */
	public void changeCell(Input input, String val) throws IOException{
		HSSFSheet sheet = getWorksheet();
		Row row = sheet.getRow(input.getRow());
		Cell cell = row.getCell(input.getCol());
		row.removeCell(cell);
		Cell newCell = row.createCell(input.getCol());
		try {
			newCell.setCellValue(Double.valueOf(val));
		} catch (NumberFormatException e){
			newCell.setCellValue(val);
		}
	}

	/*
	 * sets the values for all of the OutputRun objects
	 */
	public void getOutputsFromWorkbook() throws IOException{
		HSSFSheet sheet = getWorksheet();
		for (Output o:getOutputs()){
			List<String> values = new ArrayList<String>();
			for (int i=0;i<o.getNumValues();i++){
				Row row = sheet.getRow(o.getRow()+i);
				Cell cell = row.getCell(o.getCol());
				log.debug("Reading "+o.getRow()+","+o.getCol());
				try {
					values.add(String.valueOf(cell.getNumericCellValue()));
				} catch (IllegalStateException e) {
					try {
						values.add(cell.getStringCellValue());
					} catch (IllegalStateException l){
						values.add("error");
					}
				} catch (NumberFormatException n){
					values.add("error");
				} catch (NullPointerException p){
					values.add("error");
				}
			}
			log.debug("Setting output values "+values);
			((OutputRun)o).setValues(values);
		}
	}

	/*
	 * sets the value for all InputRun objects
	 */
	public void getInputsFromWorkbook() throws IOException{
		HSSFSheet sheet = getWorksheet();
		for (Input i:getInputs()){
			Row row = sheet.getRow(i.getRow());
			Cell cell = row.getCell(i.getCol());
			try {
				((InputRun)i).setValue(String.valueOf(cell.getNumericCellValue()));
			} catch (IllegalStateException e) {
				((InputRun)i).setValue(cell.getStringCellValue());
			} catch (NumberFormatException n){
				((InputRun)i).setValue("error");
			} catch (NullPointerException p){
				((InputRun)i).setValue("error");
			}
		}
	}

	/*
	 * returns a list containing the incremental steps for the output values
	 */
	public List<String> getIncrements() {
		HSSFSheet sheet = null;
		int startRow = getOutputs().get(0).getRow();
		int numIncrements = getOutputs().get(0).getNumValues();
		List<String> increments = new ArrayList<String>();
		try {
			sheet = getWorksheet();
		} catch (IOException e){
			e.printStackTrace();
			for (int i=0;i<numIncrements;i++){
				increments.add("none");
			}
			return increments;
		}

		for (int i=0;i<numIncrements;i++){
			Row row = sheet.getRow(startRow+i);
			Cell cell = row.getCell(0);
			try {
				increments.add(String.valueOf(cell.getNumericCellValue()));
			} catch (IllegalStateException e) {
				increments.add(cell.getStringCellValue());
			}
		}
		return increments;
	}

	public String getType(int column) throws IOException{
		HSSFSheet sheet = getWorksheet();
		String type = "";
		try {
			type = sheet.getRow(1).getCell(column).getStringCellValue();
		} catch (IllegalStateException i){
			return "error";
		} catch (NullPointerException n){
			return "error";
		}
		return type;
	}
}
