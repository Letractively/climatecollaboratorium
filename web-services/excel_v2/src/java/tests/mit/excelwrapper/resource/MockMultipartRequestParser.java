/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mit.excelwrapper.service.MultipartRequestParser;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

/**
 * <p>
 * Mock multipart request parser implementation. We will be able to control what will be returned here.
 * </p>
 * <p>
 * <b>Thread Safety</b> There is no class field thus it is thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class MockMultipartRequestParser implements MultipartRequestParser {
    /**
     * <p>
     * The fileItems field.
     * </p>
     */
    private List<? extends FileItem> fileItems = new ArrayList<FileItem>();

    /**
     * <p>
     * Sets the <code>fileItems</code> field value.
     * </p>
     *
     * @param fileItems the value to set
     */
    public void setFileItems(List<? extends FileItem> fileItems) {
        this.fileItems = fileItems;
    }

    /**
     * <p>
     * Gets the <code>fileItems</code> field value.
     * </p>
     *
     * @return the <code>fileItems</code> field value
     */
    public List<? extends FileItem> getFileItems() {
        return this.fileItems;
    }

    /**
     * <p>
     * Simply returns the file Items field.
     * </p>
     *
     * @param request the request to be parsed
     * @return the file item list
     *
     * @throws FileUploadException if any error occurs during parsing
     */
    @Override
    public List<? extends FileItem> parseRequest(HttpServletRequest request) throws FileUploadException {
        return this.fileItems;
    }

}
