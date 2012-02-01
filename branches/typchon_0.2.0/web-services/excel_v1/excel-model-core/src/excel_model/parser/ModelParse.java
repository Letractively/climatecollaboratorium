package excel_model.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import cayenne_classes.ExcelModelDAO;
import excel_model.Model;
import excel_model.persistence.ServerModel;

public class ModelParse extends ServerModel {

//	private  int numInputMetaData = 12;
//	private  int numOutputMetaData = 12;
	//private  int outputValsStart = numInputMetaData+numOutputMetaData+2;

	// input data row numbers:
	private static int inNameRow = 0;
	private static int inDefaultRow = inNameRow+1;
	private static int inLabelRow = inDefaultRow+1;
	private static int inDescriptionRow = inLabelRow+1;
	private static int inUnitsRow = inDescriptionRow+1;
	private static int inVarContextRow = inUnitsRow+1;
	private static int inVarTypeRow = inVarContextRow+1;
	private static int inDatatypeRow = inVarTypeRow+1;
	private static int inExtRow = inDatatypeRow+1;
	private static int inMinRow = inExtRow+1;
	private static int inMaxRow = inMinRow+1;
	private static int inCategoryRow = inMaxRow+1;





	// output data row numbers:
	private static int outNameRow = inCategoryRow+1;
	private static int outDefaultRow = outNameRow+1;
	private static int outLabelRow = outDefaultRow+1;
	private static int outDescriptionRow = outLabelRow+1;
	private static int outUnitsRow = outDescriptionRow+1;
	private static int outVarContextRow = outUnitsRow+1;
	private static int outVarTypeRow = outVarContextRow+1;
	private static int outDatatypeRow = outVarTypeRow+1;
	private static int outExtRow = outDatatypeRow+1;
	private static int outMinRow = outExtRow+1;
	private static int outMaxRow = outMinRow+1;
	private static int outCategoryRow = outMaxRow+1;

	private static int outputValueStartRow = outCategoryRow+2;

	private static  Logger log = Logger.getLogger(ModelParse.class);

	/*
	 * create ModelParse from dao
	 */
	public ModelParse(ExcelModelDAO dao) {
		super(dao, ParserFactory.instance());
	}

	/*
	 * create new ModelParse
	 */
	public ModelParse(String name, String path, int worksheet){
		super(name, path, worksheet, ParserFactory.instance());

	}

	public static HSSFWorkbook getWorkbook(InputStream stream) throws IOException {
		POIFSFileSystem fileSystem = new POIFSFileSystem(stream);
		HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
		return workbook;
	}

	public static Collection<ModelParse> parseWorkbook(HSSFWorkbook workbook, String basename, String path) {
		List<ModelParse> result = new ArrayList<ModelParse>();
		//for now, let's just parse the first worksheet
		if (workbook.getNumberOfSheets() <1) return Collections.emptySet();
		else {
			result.add(populateModelFromSheet(workbook.getSheetAt(0), new ModelParse(basename+"."+0,path,0)));
		}
//		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//			ModelParse model = new ModelParse(basename+"."+i,path,i);
//			result.add(populateModelFromSheet(workbook.getSheetAt(i), model));
//		}
		return result;
	}

	//TODO: update withNoMetaData; not currently using
	public static ModelParse populateModelFromSheet(HSSFSheet sheet, ModelParse model) {
		if (hasMetaData(sheet)) {
			return populateModelFromSheetWithMetaData(sheet, model);
		} else
			return populateModelFromSheetWithMetaData(sheet, model);
	}

	/*
	 * check for metadata in spreadsheet
	 */
	private static boolean hasMetaData(HSSFSheet sheet) {
		try {
			Cell cell = sheet.getRow(2).getCell(0);
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}



	/*
	 * use to parse a spread sheet in which metadata is included
	 */
	private  static ModelParse populateModelFromSheetWithMetaData(HSSFSheet sheet,ModelParse model)  {

		// create all input and output objects
		populateInputsWithMetaData(sheet,model);
		populateOutputsWithMetaData(sheet,model);
		return model;
	}

	/*
	 * create all InputParse objects from spread sheet
	 */
	private  static Model populateInputsWithMetaData(HSSFSheet sheet,ModelParse model) {
		String intName = "";
		String value = "";
		String units = "";
		String name = "";
		String descr = "";
		String min	= "";
		String max = "";
		String type = "";
		String label = "";
		String context = "";
		String datatype = "";
		String external = "";
		String category = "";
		int rowCounter = 0;
		int colCounter = 0;


		while(true){
			Row row = sheet.getRow(rowCounter);
			if (row == null || row.getCell(colCounter) == null || row.getCell(colCounter).getCellType()==Cell.CELL_TYPE_BLANK) break;
			name = getCellValString(sheet,colCounter,inNameRow,name);
			type = getCellValString(sheet,colCounter,inVarTypeRow,type);
			value = getCellValString(sheet,colCounter,inDefaultRow,value);
			units = getCellValString(sheet,colCounter,inUnitsRow,units);
			descr = getCellValString(sheet,colCounter,inDescriptionRow,descr);
			label = getCellValString(sheet,colCounter,inLabelRow,label);
			context = getCellValString(sheet,colCounter,inVarContextRow,context);
			datatype = getCellValString(sheet,colCounter,inDatatypeRow,datatype);
			external = getCellValString(sheet,colCounter,inExtRow,external);
			category = getCellValString(sheet,colCounter,inCategoryRow,category);
			min = getCellValString(sheet,colCounter,inMinRow,min);
			max = getCellValString(sheet,colCounter,inMaxRow,max);
			intName = charCheck(name);
			type = inputTypeCheck(type);
			model.addInput(new InputParse(name,intName,value,label,descr,
					units,context,type,datatype,external,
					min,max,category,inDefaultRow,colCounter));
			colCounter++;
		}
		return model;
	}

	/*
	 * create all OuputParse objects from spread sheet
	 */
	private static Model populateOutputsWithMetaData(HSSFSheet sheet,ModelParse model) {
		String intName = "";
		String value = "";
		String units = "";
		String name = "";
		String descr = "";
		String min	= "";
		String max = "";
		String type = "";
		String label = "";
		String context = "";
		String datatype = "";
		String external = "";
		String category = "";
		int numTimes = getIncrTimes(sheet);
		type = "VAL";
//		numInputMetaData = getNumIMD(sheet);
//		numOutputMetaData = getNumOMD(sheet);
//		log.debug("ouvputValsStart = "+outputValsStart);

		// get all of meta data
		int rowCounter = outNameRow;
		int colCounter = 0;
		while(true){
			Row row = sheet.getRow(rowCounter);
			if (row == null || row.getCell(colCounter) == null || row.getCell(colCounter).getCellType()==Cell.CELL_TYPE_BLANK) break;
			value = getCellValString(sheet,colCounter,outDefaultRow,value);
			min = getCellValString(sheet,colCounter,outMinRow,min);
			max = getCellValString(sheet,colCounter,outMaxRow,max);
			context = getCellValString(sheet,colCounter,outVarContextRow,context);
			//if ("INDEX".equals(context)) indexCol = colCounter;

			datatype = getCellValString(sheet,colCounter,outDatatypeRow,datatype);
			external = getCellValString(sheet,colCounter,outExtRow,external);
			category = getCellValString(sheet,colCounter,outCategoryRow,category);
			type = getCellValString(sheet,colCounter,outVarTypeRow,type);
			units = getCellValString(sheet,colCounter,outUnitsRow,units);
			descr = getCellValString(sheet,colCounter,outDescriptionRow,descr);
			name = getCellValString(sheet,colCounter,outNameRow,name);

			intName = charCheck(name)+"_output";
			type = outputTypeCheck(type);



			model.addOutput(new OutputParse(name,intName,value,label,descr,
						units,context,type,datatype,external,
						min,max,category,outputValueStartRow,
						colCounter,numTimes));
			colCounter++;
		}
		return model;
	}


	private static int getIncrTimes(HSSFSheet sheet){
		int count = 0;
		while (true) {
			Row row = sheet.getRow(outputValueStartRow+count);
			if (row == null || row.getCell(0) == null || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK) {
				break;
			}
			++count;
		}
		return count;
	}

	private static String getCellValString(HSSFSheet sheet, int c,
			int r, String defVal){
		String currentValue = "";
		log.debug("Atempting to read row "+r);
		Row row = sheet.getRow(r);

		if (row == null) {
			return currentValue;
		}
		Cell cell = row.getCell(c);

		try {
			currentValue = cell.getStringCellValue();
		} catch (IllegalStateException e){
			currentValue = String.valueOf(cell.getNumericCellValue());
		} catch (NumberFormatException n){
			currentValue = "error";
		} catch (NullPointerException p){
			currentValue = defVal;
		}
		if (currentValue.equals("")){
			return defVal;
		} else return currentValue;
	}

	private static String charCheck(String name){
		String regex = "[^\\d\\w]";
		Pattern p =  Pattern.compile(regex);
		Matcher m = p.matcher(name);
		String internal = m.replaceAll("_");
		return internal;
	}

	private  static String inputTypeCheck(String type){
//		if (!type.equals("category") && !type.equals("range")){
//			return "category";
//		} else return type;
		return type;
	}

	private static String outputTypeCheck(String type){
//		if (!type.equals("VAL") && !type.equals("IDX")){
//			return "VAL";
//		} else return type;
		return type;
	}

	/*
	 * checks for blank rows at start of worksheet
	 */
	private static int getStartRow(HSSFSheet sheet){
		int startRowNum = 0;
		while(true){
			Row row = sheet.getRow(startRowNum);
			Cell cell = row.getCell(0);
			try {
				if (cell.getCellType()==Cell.CELL_TYPE_BLANK){
					startRowNum++;
				}
				break;
			} catch (NullPointerException e){
				startRowNum++;
			}
		}
		return startRowNum;
	}

	/*
	 * checks how much meta data is included for inputs
	 */
	private static int getNumIMD(HSSFSheet sheet){
		int numIMD = 0;
		int rowNum = inNameRow;
		while (true){
			Row row = sheet.getRow(rowNum);
			try{
				row.getCell(0).getNumericCellValue();
			} catch (NullPointerException e){
				break;
			} catch (IllegalStateException i){
				try {
					row.getCell(0).getStringCellValue();
				} catch (IllegalStateException t){
					break;
				}
			}
			if (row.getCell(0).getCellType()==Cell.CELL_TYPE_BLANK){
				break;
			}
			rowNum++;
			numIMD++;
		}
		return numIMD;
	}

	private static int getNumOMD(HSSFSheet sheet){
		int numOMD = 0;
		int rowNum = outNameRow;
		while (true){
			Row row = sheet.getRow(rowNum);
			try{
				row.getCell(0).getNumericCellValue();
			} catch (NullPointerException e){
				break;
			} catch (IllegalStateException i){
				try {
					row.getCell(0).getStringCellValue();
				} catch (IllegalStateException t){
					break;
				}
			}
			if (row.getCell(0).getCellType()==Cell.CELL_TYPE_BLANK){
				break;
			}
			rowNum++;
			numOMD++;
		}
		return numOMD;
	}

}
