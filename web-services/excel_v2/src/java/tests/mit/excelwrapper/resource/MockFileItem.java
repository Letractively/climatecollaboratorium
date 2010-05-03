/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.disk.DiskFileItem;

/**
 * <p>
 * Mock <code>FileItem</code> implementation.
 * </p>
 * <p>
 * <b>Thread Safety</b> It represents file item and it is not thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class MockFileItem extends DiskFileItem {
    /**
     * <p>
     * Generated serial version UID.
     * </p>
     */
    private static final long serialVersionUID = 7549003264956897740L;

    /**
     * <p>
     * The field value.
     * </p>
     */
    private String fieldValue;

    /**
     * <p>
     * The file name.
     * </p>
     */
    private String fileName;

    /**
     * <p>
     * Ctor.
     * </p>
     *
     * @param fieldName the field name
     * @param isFormField indicates if this is a form field or updated file
     * @param fieldValue the field value if it is a form field
     * @param fileName the file name if it is an uploaded file
     */
    public MockFileItem(String fieldName, boolean isFormField, String fieldValue, String fileName) {
        super(fieldName, null, isFormField, null, -1, null);
        this.fieldValue = fieldValue;
        this.fileName = fileName;
    }

    /**
     * <p>
     * Gets the string. It only allows for the form field.
     * </p>
     *
     * @return the string
     *
     * @throws IllegalStateException if this is an uploaded file
     */
    @Override
    public String getString() {
        if (isFormField()) {
            return fieldValue;
        } else {
            throw new IllegalStateException("call getInputStream to get content instead.");
        }
    }

    /**
     * <p>
     * Gets the input stream.
     * </p>
     *
     * @return the input stream
     *
     * @throws IOException if any error during getting the stream
     * @throws IllegalStateException if this is a form field
     */
    @Override
    public InputStream getInputStream() throws IOException {
        if (isFormField()) {
            throw new IllegalStateException("call getString to get content instead.");
        } else {
            return new FileInputStream(new File(fileName));
        }
    }
}
