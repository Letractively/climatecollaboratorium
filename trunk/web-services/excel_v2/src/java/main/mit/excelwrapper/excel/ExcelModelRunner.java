/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.excel;

import mit.excelwrapper.DataType;
import mit.excelwrapper.Utils;
import mit.excelwrapper.exception.ExcelWrapperException;
import mit.excelwrapper.exception.FormulaComputationException;
import mit.excelwrapper.model.ExcelModel;
import mit.excelwrapper.model.InputParam;
import mit.excelwrapper.model.OutputParam;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import javax.ws.rs.core.MultivaluedMap;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Excel runner. It parses given workbook and tries to create a model out of it.
 * </p>
 * <p>
 * <b>Thread Safety</b> It is not thread safe but it is meant to be initiated/called sequentially.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class ExcelModelRunner {
    /**
     * <p>
     * Logger for this class.
     * </p>
     */
    private static final Logger LOGGER = Logger.getLogger(ExcelModelRunner.class);

    /**
     * <p>
     * The workbook worked on.
     * </p>
     */
    private final HSSFWorkbook workbook;

    /**
     * <p>
     * The worksheet worked on.
     * </p>
     */
    private HSSFSheet currentWorksheet;

    /**
     * <p>
     * The model.
     * </p>
     */
    private final ExcelModel model;

    /**
     * <p>
     * The form fields.
     * </p>
     */
    private final MultivaluedMap<String, String> formFields;

    /**
     * <p>
     * The ctor.
     * </p>
     *
     * @param workbook the workbook
     * @param model the model
     * @param formFields the form fields
     *
     * @throws IllegalArgumentException if any parameter is null
     */
    public ExcelModelRunner(HSSFWorkbook workbook, ExcelModel model, MultivaluedMap<String, String> formFields) {
        Utils.checkNull(workbook, "workbook");
        Utils.checkNull(model, "model");
        Utils.checkNull(formFields, "formFields");

        this.workbook = workbook;
        //this.worksheet = workbook.getSheetAt(model.getWorksheet());
        this.model = model;
        this.formFields = formFields;
    }

    /**
     * <p>
     * Runs the model.
     * </p>
     *
     * @return the result
     */
    public String runModel() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Runs the model with if of " + model.getId());
        }
        ExcelConstants.Format format = model.getFormat()==null?ExcelConstants.Format.SINGLE_SHEET:ExcelConstants.Format.valueOf(model.getFormat());
        currentWorksheet = workbook.getSheetAt(format.getWorksheetIndex(model.getWorksheet(),true));

        Map<String, List<String>> inputValues = validateAndGetInputValues();
        // assign the value
        updateInputCells(inputValues);
        int outputidx =format.getWorksheetIndex(model.getWorksheet(),false);
        currentWorksheet = workbook.getSheetAt(outputidx);
        // run formulas
        runFormulas();

        // get the result
        return getResult(inputValues);
    }

    /**
     * <p>
     * Gets the result.
     * </p>
     *
     * @param inputValues the input values used in latest run
     * @return the result
     */
    private String getResult(Map<String, List<String>> inputValues) {
        // inputs
        StringBuilder sb = new StringBuilder();
        for (String paramName : inputValues.keySet()) {
            List<String> paramValue = inputValues.get(paramName);
            sb.append("<").append(paramName).append(">[");
            for (String s:paramValue) {
                sb.append("[").append(encodeValue(s)).append("]");
            }
            sb.append("]");
        }

        // outputs
        for (OutputParam outputParam : model.getOutputParams()) {
            sb.append("<").append(outputParam.getInternalName()).append(">[");
            // multiple values
            int startRow = outputParam.getRowNum();
            int colCounter = outputParam.getColNum();
            int numOfValues = outputParam.getNumRows();
            for (int i = startRow; i < startRow + numOfValues; i++) {
                String outputValue = null;
                try {
                    outputValue = Utils.getCellValueAsString(currentWorksheet, i, colCounter, "");
                    sb.append("[").append(encodeValue(outputValue)).append("]");
                } catch (FormulaComputationException e) {
                    LOGGER.info("Error executing formula in cell "+colCounter+","+i);
                    sb.append("[").append("@ERROR").append("]");
                }

            }
            sb.append("]");
        }
        return sb.toString();
    }

    /**
     * <p>
     * Encodes the values to avoid conflict with "[","]","<",">" etc. It uses <code>URLEncoder</code>.
     * </p>
     *
     * @param value the value to be encoded
     * @return the encoded value
     */
    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, "UTF8");
        } catch (UnsupportedEncodingException e) {
            // it should not occur since UTF8 should be supported universally
            throw new ExcelWrapperException("UTF8 encoding is not supported : " + e.getMessage());
        }
    }

    /**
     * <p>
     * Runs all the formulas.
     * </p>
     */
    private void runFormulas() {
        // updates all of the formula cells in the excel file to display the
        // changes that were made to the parameter values
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        for (Row r : currentWorksheet) {
            for (Cell c : r) {
                if (c.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    try {
                    evaluator.evaluateFormulaCell(c);
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                   
                }
            }
        }
    }

    /**
     * <p>
     * Validates and gets all input values from form fields.
     * </p>
     *
     * @return input values
     */
    private Map<String, List<String>> validateAndGetInputValues() {
        Map<String, List<String>> inputValues = new HashMap<String, List<String>>();
        // initiate the map using model input parameters.
        Map<String, InputParam> paramMap = model.getInputParamMap();

        if (formFields.size() != paramMap.size()) {
            throw new ExcelWrapperException(
                "The number of form fields should be equal to the number of input parameters.");
        }

        for (String fieldName : formFields.keySet()) {
            List<String> values = new ArrayList<String>();
            String fieldValue = formFields.getFirst(fieldName);
            InputParam inputParam = paramMap.get(fieldName);
            if (inputParam == null) {
                throw new ExcelWrapperException("unrecognzied form field of " + fieldName);
            }

            int numrows = inputParam.getNumRows()==null?1:inputParam.getNumRows();
            if (numrows>1) {
                values = Utils.parseList(fieldName,fieldValue,inputParam.getDataTypeEnum());
            } else {
                Utils.checkInputParamValue(fieldName, fieldValue, inputParam.getDataTypeEnum());
                values.add(fieldValue);
            }

            inputValues.put(fieldName, values);
        }

        return inputValues;
    }

    /**
     * <p>
     * Updates the input cells.
     * </p>
     *
     * @param inputValues the input values
     */
    private void updateInputCells(Map<String, List<String>> inputValues) {
        Map<String, InputParam> paramMap = model.getInputParamMap();
        for (String paramName : inputValues.keySet()) {
            List<String> paramValues = inputValues.get(paramName);
            InputParam inputParam = paramMap.get(paramName);
            updateInputCellValue(inputParam, paramValues);
        }

    }

    /**
     * <p>
     * Updates the input cell.
     * </p>
     *
     * @param inputParam the input parameter
     * @param paramValues the parameter values
     */
    private void updateInputCellValue(InputParam inputParam, List<String> paramValues) {
        if (paramValues.size() != inputParam.getNumRows()) {
            LOGGER.warn("Number of inputs provided != number of inputs expected!");
        }
        int offset = inputParam.getRowNum();

        for (int i = 0;i<inputParam.getNumRows() && i<paramValues.size();i++) {
            Cell cell = currentWorksheet.getRow(i+offset).getCell(inputParam.getColNum());
        
        if (inputParam.getDataTypeEnum() != DataType.TEXT) {
            cell.setCellValue(Double.parseDouble(paramValues.get(i)));
        } else {
            cell.setCellValue(paramValues.get(i));
        }
        }
    }
}
