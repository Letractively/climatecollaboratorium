/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.simulation.climate.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

/**
 * <p>
 * Multipart request parser interface.
 * </p>
 * <p>
 * <b>Thread Safety</b> all implemented classes should be thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public interface MultipartRequestParser {
    /**
     * <p>
     * It parses the request and return the list of file item.
     * </p>
     *
     * @param request the request to be parsed
     * @return a list of file item. It could be empty list.
     *
     * @throws FileUploadException if any error occurs during file upload
     */
    public List<? extends FileItem> parseRequest(HttpServletRequest request) throws FileUploadException;
}
