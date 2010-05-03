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
import mit.excelwrapper.model.ExcelModel;
import mit.excelwrapper.model.InputParam;
import mit.excelwrapper.model.OutputParam;

import org.apache.commons.httpclient.NameValuePair;
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
    /**
     * <p>
     * The parameter property name list. It follows the order it is supposed to appear in the excel sheet.
     * </p>
     */
    private static final String[] PARAM_PROPERTY_NAMES = new String[] {"name", "value", "label", "description",
        "units", "varcontext", "type2", "datatype", "external", "min", "max", "categories"};

    /**
     * <p>
     * The allowed VarContext values.
     * </p>
     */
    private static final List<String> VAR_CONTEXT_VALUE_SET = Arrays.asList("SCALAR", "INDEX", "INDEXED");

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
     * Constant for row counter for input value.
     * </p>
     */
    private static final int ROW_COUNTER_INPUT_VALUE = START_ROW_INPUT_PARAM + 1;

    /**
     * <p>
     * Constant for row counter for start row for output parameter.
     * </p>
     */
    private static final int START_ROW_OUTPUT_PARAM = PARAM_PROPERTY_NAMES.length;

    /**
     * <p>
     * Constant for row counter for start row for input value.
     * </p>
     */
    private static final int START_ROW_OUTPUT_VALUES = PARAM_PROPERTY_NAMES.length * 2 + 1;

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
    private final HSSFSheet sheet;

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
    public ExcelModelParser(HSSFWorkbook workbook, ExcelWrapperDAO dao) {
        Utils.checkNull(workbook, "workbook");
        Utils.checkNull(dao, "dao");

        // Gets the first sheet
        if (workbook.getNumberOfSheets() < 1) {
            throw new IllegalArgumentException("the workbook should at least contain 1 sheet.");
        }

        sheet = workbook.getSheetAt(0);
        this.dao = dao;
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
        excelModel.setWorksheet(0);
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
        final int firstRow = (isInput) ? START_ROW_INPUT_PARAM : START_ROW_OUTPUT_PARAM;
        int colCounter = 0;

        int countOutputValues = 0;
        if (!isInput) {
            countOutputValues = countOutputValues();
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
            for (int i = 0; i < PARAM_PROPERTY_NAMES.length; i++) {
                int rowCounter = i + firstRow;
                String propertyName = PARAM_PROPERTY_NAMES[i];
                if ("value".equals(propertyName)) {
                    pairs.add(new NameValuePair(propertyName, null));
                    continue;
                }
                String propertyValue = Utils.getCellValueAsString(sheet, rowCounter, colCounter, "");
                propertyValue = validateAndGetValue(propertyName, propertyValue, isInput);

                pairs.add(new NameValuePair(propertyName, propertyValue));

                // derived internal name
                if ("name".equals(propertyName)) {
                    // internalName = Utils.normalizeStringValue(propertyValue);
                    internalName = getInternalName(propertyValue, isInput, colCounter);
                }
                if ("datatype".equals(propertyName)) {
                    dataType = propertyValue;
                }
            }
            pairs.add(new NameValuePair("intName", internalName));
            paramList.add(pairs);

            if (isInput) {
                InputParam input = dao.newInputParam();
                input.setColNum(colCounter);
                input.setRowNum(ROW_COUNTER_INPUT_VALUE);
                input.setInternalName(internalName);
                input.setDataType(dataType);
                excelModel.addToInputParams(input);
            } else {
                OutputParam output = dao.newOutputParam();
                output.setColNum(colCounter);
                output.setRowNum(START_ROW_OUTPUT_VALUES);
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
     * @param propertyName the property name
     * @param propertyValue the property value
     * @param isInput indicates if it is input or output
     * @return the validated property name
     *
     * @throws ExcelWrapperException if the value is invalid
     */
    private String validateAndGetValue(String propertyName, String propertyValue, boolean isInput) {
        if ("varcontext".equals(propertyName)) {
            propertyValue = propertyValue.toUpperCase();
            if (!VAR_CONTEXT_VALUE_SET.contains(propertyValue)) {
                throw new ExcelWrapperException("VarContext value is invalid : " + propertyValue);
            }
        }

        if ("type2".equals(propertyName)) {
            List<String> testSet = (isInput) ? VAR_TYPE_VALUE_SET_INPUT : VAR_TYPE_VALUE_SET_OUTPUT;
            propertyValue = propertyValue.toUpperCase();
            if (!testSet.contains(propertyValue)) {
                throw new ExcelWrapperException("VarType value is invalid : " + propertyValue);
            }
        }

        if ("datatype".equals(propertyName)) {
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
    private int countOutputValues() {
        int count = 0;
        while (true) {
            if (isCellBlank(START_ROW_OUTPUT_VALUES + count, 0)) {
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
        Row row = sheet.getRow(rowCounter);
        return (row == null || row.getCell(colCounter) == null || row.getCell(colCounter).getCellType() == Cell.CELL_TYPE_BLANK);
    }

}
