package excel_model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.cayenne.access.DataContext;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class Model {
	
	private final String filename = ""; // path to the excel file being input
	private ArrayList<Output> outputList = new ArrayList<Output>();
	private ArrayList<Input> inputList = new ArrayList<Input>();
	private ArrayList<Double> outputIncrs = new ArrayList<Double>();
	private int numInputMetaData = 7;
	private int numOutputMetaData = 5;
	private boolean inputMetaData = false;
	
	private InputStream input = null;
	private HSSFWorkbook workBook = null;
	private HSSFSheet sheet = null;
	DataContext context;

	 /**
	  * create Model object
	  * @param pathname: path to excel file to parse
	  * @param inputMetaData: boolean indicating whether metadata is included
	  */
	public Model(InputStream inStream){
		try{
			input = inStream;
			POIFSFileSystem fileSystem = new POIFSFileSystem(input);
			workBook = new HSSFWorkbook (fileSystem);
		} catch(IOException e){
			System.out.println("IOException");
		}
		this.inputMetaData = this.metaDataCheck();
		if(inputMetaData){
			this.parseSpreadSheetWithMetaData();
		} else this.parseSpreadSheetNoMetaData();
	}
	
	/*
	 * checks whether there is any metadata in the input spread sheet
	 */
	private boolean metaDataCheck(){	
		sheet = this.workBook.getSheetAt(0);
		try{
			Cell cell = sheet.getRow(2).getCell(0);
		} catch (NullPointerException e){
			return false;
		}
		return true;
		
	}
	
	public boolean getMetaDataCheck(){
		return this.inputMetaData;
	}
	
	/*
	 * parse spread sheet in which no metadata is included
	 */
	private void parseSpreadSheetNoMetaData(){
		
		/*
		 * first get input names and values
		 */
		ArrayList<String> inputIntNames = new ArrayList<String>();
		ArrayList<Double> inputValues = new ArrayList<Double>();
		ArrayList<Double> allValues = new ArrayList<Double>();
		ArrayList<String> outputIntNames = new ArrayList<String>();
		Row currentRow;
		Cell currentCell;
		Iterator<Row> rows;
		Iterator<Cell> cells;
		String strVal;
		double numVal;
		sheet = this.workBook.getSheetAt(0);
		
		// get internal names
		rows = sheet.rowIterator();
		currentRow = rows.next();
		String currentName;
		cells = currentRow.cellIterator();
		while(cells.hasNext()){
			currentCell = cells.next();
			currentName = currentCell.getStringCellValue();
			inputIntNames.add(currentName);
		}
		
		// get values
		currentRow = rows.next();
		double currentValue;
		cells = currentRow.cellIterator();
		while(cells.hasNext()){
			currentCell = cells.next();
			currentValue = currentCell.getNumericCellValue();
			inputValues.add(currentValue);
		}
		
		
		/*
		 * get output names and values
		 */
		currentRow = rows.next();
		cells = currentRow.cellIterator();
		currentCell = cells.next();
		while(cells.hasNext()){
			currentCell = cells.next();
			strVal = currentCell.getStringCellValue();
			outputIntNames.add(strVal);
		}
		while(rows.hasNext()){
			currentRow = rows.next();
			cells = currentRow.cellIterator();
			currentCell = cells.next(); // skip time increment column
			if(currentCell.getCellType()==0 || currentCell.getCellType()==2){}
			else break;
			outputIncrs.add(currentCell.getNumericCellValue());
			while(cells.hasNext()){
				currentCell = cells.next();
				if (currentCell.getCellType()!=3){
					numVal = currentCell.getNumericCellValue();
					allValues.add(numVal);
				}
			}
		}
		
		// create Input and Output objects and put in the inputList and outputList
		for (int i=0;i<inputIntNames.size();i++){
			inputList.add(new Input(inputIntNames.get(i),inputValues.get(i),null,null,null,0.0,0.0));
		}
		ArrayList<Double> values;
		int counter = 0;
		for (int i=0;i<outputIntNames.size();i++){
			values = new ArrayList<Double>();
			for (int j=counter;j<allValues.size();j+=outputIntNames.size()){
				values.add(allValues.get(j));
			}
			outputList.add(new Output(outputIntNames.get(i),values,null,null,null,0.0,0.0));
			counter += 1;
		}
	}
	
	
	/*
	 * use to parse a spread sheet in which metadata is included
	 */
	private void parseSpreadSheetWithMetaData(){
		
		/*
		 * first parse the spread sheet for the input metadata, names, and values
		 */
		ArrayList<String> inputIntNames = new ArrayList<String>();
		ArrayList<Double> inputValues = new ArrayList<Double>();
		ArrayList<String> inputUnits = new ArrayList<String>();
		ArrayList<String> inputNames = new ArrayList<String>();
		ArrayList<String> inputDescr = new ArrayList<String>();
		ArrayList<Double> inputMins = new ArrayList<Double>();
		ArrayList<Double> inputMaxs = new ArrayList<Double>();
		
		// collection of all the metadata arrays
		ArrayList<ArrayList> allInputArrays = new ArrayList<ArrayList>();
		Collections.addAll(allInputArrays, inputIntNames, inputValues, inputUnits, inputNames, inputDescr, 
				inputMins, inputMaxs);
		
		Row currentRow;
		Cell currentCell;
		Iterator<Row> rows;
		Iterator<Cell> cells;
		String strVal;
		double numVal;
		sheet = this.workBook.getSheetAt(0);
		rows = sheet.rowIterator();
		for(int i=0;i<numInputMetaData;i++){
			currentRow = rows.next();
			cells = currentRow.cellIterator();
			if(currentRow.getCell(0).getCellType()==0){
				while(cells.hasNext()){
					currentCell = cells.next();
					numVal = currentCell.getNumericCellValue();
					allInputArrays.get(i).add(numVal);
				}
			} else {
				while(cells.hasNext()){
					currentCell = cells.next();
					strVal = currentCell.getStringCellValue();
					allInputArrays.get(i).add(strVal);
				}
			}
			while(cells.hasNext()){
				currentCell = cells.next();
				
			}
		}
		
		// create Input objects and put in inputList
		for (int i=0;i<inputIntNames.size();i++){
			inputList.add(new Input(inputIntNames.get(i),inputValues.get(i),inputUnits.get(i),inputNames.get(i),
					inputDescr.get(i),inputMins.get(i),inputMaxs.get(i)));
		}
		
		/*
		 * parse the spread sheet for the output metadata, names, and values
		 */
		ArrayList<String> outputIntNames = new ArrayList<String>();
		ArrayList<String> outputUnits = new ArrayList<String>();
		ArrayList<String> outputNames = new ArrayList<String>();
		ArrayList<String> outputDescr = new ArrayList<String>();
		ArrayList<Double> outputMins = new ArrayList<Double>();
		ArrayList<Double> outputMaxs = new ArrayList<Double>();
		
		// array of all of the output values; use mod to pair values to names
		ArrayList<Double> allValues = new ArrayList<Double>();
		
		// collection of all output metadata arrays
		ArrayList<ArrayList> allOutputArrays = new ArrayList<ArrayList>();
		Collections.addAll(allOutputArrays,outputUnits, outputNames, 
				outputDescr, outputMins, outputMaxs);
		
		// collect metadata
		currentRow = rows.next();
		for(int i=0;i<numOutputMetaData;i++){
			currentRow = rows.next();
			cells = currentRow.cellIterator();
			if(currentRow.getCell(0).getCellType()==1){
				while(cells.hasNext()){
					currentCell = cells.next();
					strVal = currentCell.getStringCellValue();
					allOutputArrays.get(i).add(strVal);
				}
			} else {
				while(cells.hasNext()){
					currentCell = cells.next();
					numVal = currentCell.getNumericCellValue();
					allOutputArrays.get(i).add(numVal);
				}
			}
		}
		
		//collect output names and values
		currentRow = rows.next();
		cells = currentRow.cellIterator();
		currentCell = cells.next();
		while(cells.hasNext()){
			currentCell = cells.next();
			strVal = currentCell.getStringCellValue();
			outputIntNames.add(strVal);
		}
		while(rows.hasNext()){
			currentRow = rows.next();
			cells = currentRow.cellIterator();
			currentCell = cells.next(); // skip time increment column
			if(currentCell.getCellType()==0 || currentCell.getCellType()==2){}
			else break;
			outputIncrs.add(currentCell.getNumericCellValue());
			while(cells.hasNext()){
				currentCell = cells.next();
				if (currentCell.getCellType()!=3){
					numVal = currentCell.getNumericCellValue();
					allValues.add(numVal);
				}
			}
		}
		
		// create Output objects and put in outputList
		ArrayList<Double> values;
		int counter = 0; // keep track of current output
		for (int i=0;i<outputIntNames.size();i++){
			values = new ArrayList<Double>();
			for (int j=counter;j<allValues.size();j+=outputIntNames.size()){
				values.add(allValues.get(j));
			}
			outputList.add(new Output(outputIntNames.get(i),values,outputUnits.get(i),outputNames.get(i),outputDescr.get(i),
					outputMins.get(i),outputMaxs.get(i)));
			counter ++;
		}
		
	}
	
	/*
	 * takes a mapping input names to new values and changes the input's
	 * stored values; then puts the new values into the spread sheet and gets
	 * the new output values
	 */
	public void update(Map<String,Double> newVals){
		// loop through all the new inputs values and change
		// the current input values to these
		for (String s:newVals.keySet()){
			for(Input i:this.inputList){
				if(i.getIntName().equals(s)){
					i.setValue(newVals.get(s));
				}
			}
		}
		FormulaEvaluator evaluator = this.workBook.getCreationHelper().createFormulaEvaluator();
		sheet = this.workBook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Row row = null;
		Cell cell = null;
		
		// skip first row
		row = rows.next();
		row = rows.next();
		
		// changes the current parameter values in the excel file to
		// those stored in the paramMap
		int counter = 0;
		Iterator<Cell> cells = row.cellIterator();
		while(cells.hasNext()){
			cell = cells.next();
			row.removeCell(cell);
			Cell newCell = row.createCell(counter);
			newCell.setCellValue(this.inputList.get(counter).getValue());
			counter += 1;
		}
		
		// updates all of the formula cells in the excel file to display the
		// changes that were made to the parameter values
		for(int sheetNum = 0; sheetNum < this.workBook.getNumberOfSheets(); sheetNum++) {
		    HSSFSheet updateSheet = this.workBook.getSheetAt(sheetNum);
		    for(Row r : updateSheet) {
		        for(Cell c : r) {
		            if(c.getCellType() == Cell.CELL_TYPE_FORMULA) {
		                evaluator.evaluateFormulaCell(c);
		            }
		        }
		    }
		}
		
		// clear the current outputList and re-parse the changed workbook
		outputList.clear();
		if(inputMetaData){
			this.parseSpreadSheetWithMetaData();
		} else this.parseSpreadSheetNoMetaData();
	}
	
	/*
	 * returns an array of all Inputs
	 */
	public ArrayList<Input> getInputs(){
		return this.inputList;
	}
	
	/*
	 * returns array of all Outputs
	 */
	public ArrayList<Output> getOutputs(){
		return this.outputList;
	}
	
	/*
	 * returns array of the increments
	 */
	public ArrayList<Double> getIncrements(){
		return this.outputIncrs;
	}
	
}
