/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.service;

import java.io.IOException;

import mit.excelwrapper.model.ExcelModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <p>
 * The interface for the service which returns the associated workbook for given model.
 * </p>
 * <p>
 * <b>Thread Safety</b> All implementation classes should be thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public interface ExcelService {
    /**
     * <p>
     * Gets the associated workbook object for the given excel model.
     * </p>
     *
     * @param excelModel the excel model
     * @return the work book associated
     *
     * @throws IOException if any IO error occurs
     */
    public HSSFWorkbook getWorkbook(ExcelModel excelModel) throws IOException;

    /**
     * <p>
     * Clears up any resource.
     * </p>
     */
    public void destroy();

    byte[] getContentByModel(ExcelModel excelModel) throws IOException;
}
