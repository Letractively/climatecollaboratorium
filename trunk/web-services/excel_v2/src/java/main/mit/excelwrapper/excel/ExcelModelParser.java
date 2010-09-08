/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mit.excelwrapper.DataType;
import mit.excelwrapper.Utils;
import mit.excelwrapper.dao.ExcelWrapperDAO;
import mit.excelwrapper.exception.ExcelWrapperException;
import mit.excelwrapper.exception.FormulaComputationException;
import mit.excelwrapper.model.ExcelModel;
import mit.excelwrapper.model.InputParam;
import mit.excelwrapper.model.OutputParam;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * <p>
 * Excel Parser. It parses given workbook and tries to create a model out of it.
 * </p>
 * <p>
 * <b>Thread Safety</b> It is not thread safe but it is meant to be initiated/called sequentially.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class ExcelModelParser {

    private static Logger log = Logger.getLogger(ExcelModelParser.class);


    /**
     * <p>
     * The allowed VarContext values.
     * </p>
     */
    private static final List<String> VAR_CONTEXT_VALUE_SET = Arrays.asList("SCALAR", "INDEX", "INDEXED","LIST");

    /**
     * <p>
     * The allowed VarType values for input.
     * </p>
     */
    private static final List<String> VAR_TYPE_VALUE_SET_INPUT = Arrays.asList("RANGE", "CATEGORICAL", "FREE",
        "FUZZY_DISCRETE");

    /**
     * <p>
     * The allowed VarType values for output.
     * </p>
     */
    private static final List<String> VAR_TYPE_VALUE_SET_OUTPUT = Arrays.asList("RANGE", "CATEGORICAL", "FREE");

    /**
     * <p>
     * The allowed DataType values.
     * </p>
     */
    private static final List<String> DATA_TYPE_VALUE_SET = new ArrayList<String>();
    /**
     * <p>
     * Initialize the allowed data type value set.
     * </p>
     */
    static {
        for (DataType dataType : DataType.values()) {
            DATA_TYPE_VALUE_SET.add(dataType.toString());
        }
    }

    /**
     * <p>
     * Constant for row counter for start row for input parameter.
     * </p>
     */
    private static final int START_ROW_INPUT_PARAM = 0;


    /**
     * <p>
     * Dao instance.
     * </p>
     */
    private final ExcelWrapperDAO dao;

    /**
     * <p>
     * worksheet instance.
     * </p>
     */
    private final int baseWorksheetIndex;

    private final HSSFWorkbook workbook;

    private HSSFSheet currentSheet;

    private final ExcelConstants.Format parserFormat;

    /**
     * <p>
     * The ctor.
     * </p>
     *
     * @param workbook the workbook parameter. It should not be null.
     * @param dao the dao parameter. It should not be null.
     *
     * @throws IllegalArgumentException if any parameter is null or workbook doesn't have sheet assoicated
     */
    public ExcelModelParser(HSSFWorkbook workbook, ExcelWrapperDAO dao, int worksheet, ExcelConstants.Format parserFormat) {
        Utils.checkNull(workbook, "workbook");
        Utils.checkNull(dao, "dao");
        this.workbook = workbook;
        this.parserFormat = parserFormat;

        // Gets the first sheet
        if (workbook.getNumberOfSheets() < parserFormat.getNumSheets()+worksheet) {
            throw new IllegalArgumentException("the workbook should at least contain at least "+(parserFormat.getNumSheets()+worksheet)+" sheet.");
        }

       // currentSheet = workbook.getSheetAt(worksheet);
        baseWorksheetIndex = worksheet;
        this.dao = dao;

    }

     public ExcelModelParser(HSSFWorkbook workbook, ExcelWrapperDAO dao) {
        this(workbook,dao,0, ExcelConstants.Format.SINGLE_SHEET);
    }

    /**
     * <p>
     * Parses the worksheet to get the model.
     * </p>
     *
     * @return the excel model generated
     */
    public ExcelModel parse() {
        ExcelModel excelModel = dao.newExcelModel();
        excelModel.setWorksheet(baseWorksheetIndex);
        excelModel.setFormat(parserFormat.name());
        // it will be updated later
        excelModel.setPath("");
        List<List<NameValuePair>> paramList = new ArrayList<List<NameValuePair>>();

        parseParameters(excelModel, paramList, true);
        int numOfInputs = paramList.size();
        if (numOfInputs < 1) {
            throw new ExcelWrapperException("No input parameter could be found.");
        }

        parseParameters(excelModel, paramList, false);
        int numOfOutputs = paramList.size() - numOfInputs;
        if (numOfOutputs < 1) {
            throw new ExcelWrapperException("No output parameter could be found.");
        }

        excelModel.setParamList(paramList);
        return excelModel;
    }


    private void updateWorksheet(boolean isInput) {
        currentSheet = workbook.getSheetAt(parserFormat.getWorksheetIndex(baseWorksheetIndex,isInput));
    }

    /**
     * <p>
     * Parses to get parameters.
     * </p>
     *
     * @param excelModel the excel model
     * @param paramList the parameter list
     * @param isInput indicates if it is input parameter or now
     */
    private void parseParameters(ExcelModel excelModel, List<List<NameValuePair>> paramList, boolean isInput) {

        updateWorksheet(isInput);
        final int firstRow = parserFormat.getStartRow(isInput);

        int colCounter = 0;
        int countOutputValues = 0;


        if (!isInput) {
            countOutputValues = countValues(isInput, 0);
        }

        // scan parameter from left to right
        while (true) {
            if (isCellBlank(firstRow, colCounter)) {
                break;
            }

            // add a property pairs
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new NameValuePair("type", (isInput) ? "input" : "output"));
            String internalName = "";
            String dataType = "";
            for (int i = 0; i < ExcelConstants.Params.values().length; i++) {
                int rowCounter = i + firstRow;
                ExcelConstants.Params property = ExcelConstants.Params.values()[i];
                if (ExcelConstants.Params.VALUE == property) {
                    pairs.add(new NameValuePair(property.toFieldName(), null));
                    continue;
                }
                String propertyValue = null;
                try {
                    propertyValue = Utils.getCellValueAsString(currentSheet, rowCounter, colCounter, "");
                } catch (FormulaComputationException e) {
                    log.error("Should not encounter this error on initial parse",e);
                }
                propertyValue = validateAndGetValue(property, propertyValue, isInput);

                pairs.add(new NameValuePair(property.toFieldName(), propertyValue));

                // derived internal name
                if (ExcelConstants.Params.NAME == property) {
                    // internalName = Utils.normalizeStringValue(propertyValue);
                    internalName = getInternalName(propertyValue, isInput, colCounter);
                }
                if (ExcelConstants.Params.DATATYPE==property) {
                    dataType = propertyValue;
                }
            }
            pairs.add(new NameValuePair("intName", internalName));
            paramList.add(pairs);

            if (isInput) {
                InputParam input = dao.newInputParam();
                input.setColNum(colCounter);
                input.setRowNum(parserFormat.getValueRow(isInput));
                int numRows = parserFormat==ExcelConstants.Format.TWO_SHEET?countValues(true, colCounter):1;
                input.setNumRows(numRows);
                input.setInternalName(internalName);
                input.setDataType(dataType);
                excelModel.addToInputParams(input);
            } else {
                OutputParam output = dao.newOutputParam();
                output.setColNum(colCounter);
                output.setRowNum(parserFormat.getValueRow(isInput));
                output.setNumRows(countOutputValues);
                output.setInternalName(internalName);
                excelModel.addToOutputParams(output);
            }
            colCounter++;
        }
    }

    /**
     * <p>
     * Gets internal name.
     * <p>
     *
     * @param name the name
     * @param isInput indicates if it is input or output
     * @param i the index for the given name
     * @return the unique internal name
     */
    private String getInternalName(String name, boolean isInput, int i) {
        // make sure they unique by adding unique suffix
        name = Utils.normalizeStringValue(name.trim());
        return name + (isInput ? "_input" : "_output") + i;
    }

    /**
     * <p>
     * Validates and gets the property value.
     * </p>
     *
     * @param param the property
     * @param propertyValue the property value
     * @param isInput indicates if it is input or output
     * @return the validated property name
     *
     * @throws ExcelWrapperException if the value is invalid
     */
    private String validateAndGetValue(ExcelConstants.Params param, String propertyValue, boolean isInput) {
        if (ExcelConstants.Params.VARCONTEXT == param) {
            propertyValue = propertyValue.toUpperCase();
            if (!VAR_CONTEXT_VALUE_SET.contains(propertyValue)) {
                throw new ExcelWrapperException("VarContext value is invalid : " + propertyValue);
            }
        }

        if (ExcelConstants.Params.TYPE2 == param) {
            List<String> testSet = (isInput) ? VAR_TYPE_VALUE_SET_INPUT : VAR_TYPE_VALUE_SET_OUTPUT;
            propertyValue = propertyValue.toUpperCase();
            if (!testSet.contains(propertyValue)) {
                throw new ExcelWrapperException("VarType value is invalid : " + propertyValue);
            }
        }

        if (ExcelConstants.Params.DATATYPE == param) {
            propertyValue = propertyValue.toUpperCase();
            if (!DATA_TYPE_VALUE_SET.contains(propertyValue)) {
                throw new ExcelWrapperException("DataType value is invalid : " + propertyValue);
            }
        }

        return propertyValue;
    }

    /**
     * <p>
     * Calculates the number of output values.
     * </p>
     *
     * @return the number of output values
     */
    private int countValues(boolean isInput, int colNum) {
        int count = 0;
        while (true) {
            if (isCellBlank(parserFormat.getValueRow(isInput) + count, colNum)) {
                break;
            }
            ++count;
        }
        return count;
    }

    /**
     * <p>
     * Checks to see if the cell is blank.
     * </p>
     *
     * @param rowCounter the row counter
     * @param colCounter the column counter
     * @return true if the cell is blank or doesn't exist otherwise false
     */
    private boolean isCellBlank(int rowCounter, int colCounter) {
        Row row = currentSheet.getRow(rowCounter);
        return (row == null || row.getCell(colCounter) == null || row.getCell(colCounter).getCellType() == Cell.CELL_TYPE_BLANK);
    }

}
