package cayenne_classes;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _OutputParamDAO was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _OutputParamDAO extends CayenneDataObject {

    public static final String COL_NUM_PROPERTY = "colNum";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String INTERNAL_NAME_PROPERTY = "internalName";
    public static final String NAME_PROPERTY = "name";
    public static final String NUM_COLUMNS_PROPERTY = "numColumns";
    public static final String NUM_ROWS_PROPERTY = "numRows";
    public static final String ROW_NUM_PROPERTY = "rowNum";
    public static final String TYPE_PROPERTY = "type";
    public static final String TO_MODEL_PROPERTY = "toModel";

    public static final String ID_PK_COLUMN = "ID";

    public void setColNum(Integer colNum) {
        writeProperty("colNum", colNum);
    }
    public Integer getColNum() {
        return (Integer)readProperty("colNum");
    }

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }

    public void setInternalName(String internalName) {
        writeProperty("internalName", internalName);
    }
    public String getInternalName() {
        return (String)readProperty("internalName");
    }

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void setNumColumns(Integer numColumns) {
        writeProperty("numColumns", numColumns);
    }
    public Integer getNumColumns() {
        return (Integer)readProperty("numColumns");
    }

    public void setNumRows(Integer numRows) {
        writeProperty("numRows", numRows);
    }
    public Integer getNumRows() {
        return (Integer)readProperty("numRows");
    }

    public void setRowNum(Integer rowNum) {
        writeProperty("rowNum", rowNum);
    }
    public Integer getRowNum() {
        return (Integer)readProperty("rowNum");
    }

    public void setType(String type) {
        writeProperty("type", type);
    }
    public String getType() {
        return (String)readProperty("type");
    }

    public void setToModel(ExcelModelDAO toModel) {
        setToOneTarget("toModel", toModel, true);
    }

    public ExcelModelDAO getToModel() {
        return (ExcelModelDAO)readProperty("toModel");
    }


}
