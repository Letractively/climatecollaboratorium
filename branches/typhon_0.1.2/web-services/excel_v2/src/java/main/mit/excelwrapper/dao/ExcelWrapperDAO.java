/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.dao;

import mit.excelwrapper.model.ExcelModel;
import mit.excelwrapper.model.InputParam;
import mit.excelwrapper.model.OutputParam;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.DataObjectUtils;
import org.apache.cayenne.access.DataContext;

/**
 * <p>
 * Excel wrapper DAO class. It assume that it has a <code>DataContext</code> in the thread context.
 * </p>
 * <p>
 * <b>Thread Safety</b> all functions are thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class ExcelWrapperDAO {
    /**
     * <p>
     * Creates new <code>ExcelModel</code> object.
     * </p>
     *
     * @return the <code>ExcelModel</code> object
     */
    public ExcelModel newExcelModel() {
        return getDataContext().newObject(ExcelModel.class);
    }

    /**
     * <p>
     * Creates new <code>InputParam</code> object.
     * </p>
     *
     * @return the <code>InputParam</code> object
     */
    public InputParam newInputParam() {
        return getDataContext().newObject(InputParam.class);
    }

    /**
     * <p>
     * Creates new <code>OutputParam</code> object.
     * </p>
     *
     * @return the <code>OutputParam</code> object
     */
    public OutputParam newOutputParam() {
        return getDataContext().newObject(OutputParam.class);
    }

    /**
     * <p>
     * Gets the model by id.
     * </p>
     *
     * @param id the model id
     * @return the <code>ExcelModel</code> object
     */
    public ExcelModel findModelById(int id) {
        return DataObjectUtils.objectForPK(getDataContext(), ExcelModel.class, id);
    }

    /**
     * <p>
     * Deletes the model.
     * </p>
     *
     * @param model the model to be deleted
     */
    public void deleteModel(ExcelModel model) {
        getDataContext().deleteObject(model);
    }

    /**
     * <p>
     * It will commit the changes made.
     * </p>
     */
    public void commit() {
        getDataContext().commitChanges();
    }

    /**
     * <p>
     * It will rollbak the changes made.
     * </p>
     */
    public void rollback() {
        getDataContext().rollbackChanges();
    }

    /**
     * <p>
     * Gets the <code>DataContext</code> object in the current execution thread.
     * </p>
     *
     * @return the <code>DataContext</code>.
     */
    private DataContext getDataContext() {
        return (DataContext) BaseContext.getThreadObjectContext();
    }
}
