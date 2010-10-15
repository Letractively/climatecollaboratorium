/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.simulation.climate.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * <p>
 * Default multipart request parser implementation.
 * </p>
 * <p>
 * <b>Thread Safety</b> There is no class field thus it is thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class DefaultMultipartRequestParser implements MultipartRequestParser {

    /**
     * <p>
     * Parses the request using <code>ServletFileUpload</code>.
     * </p>
     *
     * @param request the request to be parsed
     * @return the file item list
     *
     * @throws FileUploadException if any error occurs during parsing
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<FileItem> parseRequest(HttpServletRequest request) throws FileUploadException {
        if (request == null || !ServletFileUpload.isMultipartContent(request)) {
            return new ArrayList<FileItem>();
        }

        // this class is not thread safe so we create new one each time
        ServletFileUpload servletUpload = new ServletFileUpload(new DiskFileItemFactory());
        return servletUpload.parseRequest(request);
    }

}
