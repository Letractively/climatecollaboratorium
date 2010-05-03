/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import mit.excelwrapper.model.ExcelModel;

/**
 * <p>
 * The default implementation for the service which returns the associated workbook for given model. It uses weak
 * reference map to cache the excel file content. Considering there is no update performed for each model thus this is
 * efficient and fast one to do it. It is very easy to make adjustment or use more sophiscated cache solution later if
 * necessary.
 * </p>
 * <p>
 * <b>Thread Safety</b> All implementation classes should be thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class DefaultExcelService implements ExcelService {
    /**
     * <p>
     * The cache excel map. It uses weak reference therefore it will not waste any memory.
     * </p>
     */
    private Map<Integer, WeakReference<byte[]>> cacheExcelMap = new HashMap<Integer, WeakReference<byte[]>>();

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
    public HSSFWorkbook getWorkbook(ExcelModel excelModel) throws IOException {
        byte[] fileContent = getContentByModel(excelModel);
        return new HSSFWorkbook(new POIFSFileSystem(new ByteArrayInputStream(fileContent)));
    }

    /**
     * <p>
     * Gets the byte content for the given model. It will get it from cache map first. If not, then we will create a
     * new entry in the cache.
     * </p>
     *
     * @param excelModel the excel model
     * @return the byte content. It will be a not null cloned byte content.
     *
     * @throws IOException if any IO error occurs
     */
    private synchronized byte[] getContentByModel(ExcelModel excelModel) throws IOException {
        WeakReference<byte[]> content = cacheExcelMap.get(excelModel.getId());
        if (content == null || content.get() == null) {
            // grab the content from file path and cache it in the memory
            byte[] fileContent = FileUtils.readFileToByteArray(new File(excelModel.getPath()));
            content = new WeakReference<byte[]>(fileContent);
            cacheExcelMap.put(excelModel.getId(), content);
        }
        // make a clone copy of it
        return content.get().clone();
    }

    /**
     * <p>
     * Clears up any resource.
     * </p>
     */
    public void destroy() {
        cacheExcelMap.clear();
    }
}
