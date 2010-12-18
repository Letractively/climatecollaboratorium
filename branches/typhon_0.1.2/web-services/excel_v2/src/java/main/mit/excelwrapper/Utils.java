/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper;

import mit.excelwrapper.exception.FormulaComputationException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import mit.excelwrapper.exception.ExcelWrapperException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Utils class.
 * </p>
 * <p>
 * <b>Thread Safety</b> This is an util class thus it is thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public final class Utils {
    /**
     * <p>
     * Private ctor.
     * </p>
     */
    private Utils() {
        // do nothing
    }

    /**
     * <p>
     * Checks to object is null or not. It is used to check method parameter.
     * </p>
     *
     * @param object the object value
     * @param objectName the object name
     *
     * @throws IllegalArgumentException if the object is null
     */
    public static void checkNull(Object object, String objectName) {
        if (object == null) {
            throw new IllegalArgumentException(objectName + " should not be null.");
        }
    }

    /**
     * <p>
     * Normalize the string value to avoid non digital/word character.
     * </p>
     *
     * @param value the value
     * @return the normalized value
     */
    public static String normalizeStringValue(String value) {
        if (value == null) {
            return "";
        }
        return value.replaceAll("[^\\d\\w]", "_").replaceAll("_+", "_").replaceAll("^_", "");
    }

    /**
     * <p>
     * Checks the input parameter value.
     * </p>
     *
     * @param name the parameter name
     * @param value the parameter value
     * @param dataType the data type
     *
     * @throws ExcelWrapperException if the value is null or invalid
     */
    public static void checkInputParamValue(String name, String value, DataType dataType) {
        // it should not be null
        if (value == null) {
            throw new ExcelWrapperException("the value should not be null for parameter " + name);
        }

        if (dataType == null) {
            throw new ExcelWrapperException("the date type should not be null for parameter " + name);
        }

        if (dataType == DataType.INTEGER) {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new ExcelWrapperException("the value should be integer for parameter " + name);
            }
        }

        if (dataType == DataType.DOUBLE) {
            try {
                Double.parseDouble(value);
            } catch (NumberFormatException e) {
                throw new ExcelWrapperException("the value should be double for parameter " + name);
            }
        }
    }

    /**
     * <p>
     * Gets the cell value as string. It assumes that cell should be blank/string/numeric/formula. If it is formula,
     * assumes it generates numeric/string value.
     * </p>
     *
     * @param worksheet the worksheet. It should not be null.
     * @param rowCounter the row counter
     * @param colCounter the column counter
     * @param defaultValue the default value
     * @return the cell value as string
     *
     * @throws IllegalArgumentException if the worksheet is null
     * @throws ExcelWrapperException if cell type is not as indicated above
     * @throws FormulaComputationException if the formula resulted in an error
     */
    public static String getCellValueAsString(HSSFSheet worksheet, int rowCounter, int colCounter,
        String defaultValue) throws FormulaComputationException {
        checkNull(worksheet, "worksheet");

        Row row = worksheet.getRow(rowCounter);
        if (row == null) {
            return defaultValue;
        }
        Cell cell = row.getCell(colCounter);
        if (cell == null) {
            return defaultValue;
        }
        // cell should be either blank,string, numeric or formula
        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return defaultValue;
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return cell.getNumericCellValue() + "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
                return cell.getNumericCellValue() + "";
            } else if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_ERROR) {
                throw new FormulaComputationException("Error computing fomula");
            } else {
                throw new ExcelWrapperException("invalid formula type with cached type of  "
                    + cell.getCachedFormulaResultType() + " for row of " + rowCounter + " and col of " + colCounter);
            }
        } else {
            throw new ExcelWrapperException("invalid type with type of  " + cell.getCellType() + " for rowr of "
                + rowCounter + " and col of " + colCounter);
        }

    }

    public static List<String> parseList(String name, String value, DataType dataType) {
        List<String> result = new ArrayList<String>();
        Pattern p = Pattern.compile("([^\\[^\\]]+)");
        Matcher m = p.matcher(value);
        while (m.find()) {
            String match = m.group(1);
            checkInputParamValue(name,match,dataType);
            result.add(match);
        }
        return result;

    }
}
