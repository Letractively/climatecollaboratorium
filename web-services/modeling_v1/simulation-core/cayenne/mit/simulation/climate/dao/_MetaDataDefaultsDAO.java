package mit.simulation.climate.dao;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _MetaDataDefaultsDAO was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _MetaDataDefaultsDAO extends CayenneDataObject {

    public static final String CATEGORIES_PROPERTY = "categories";
    public static final String DEFAULT_PROPERTY = "default";
    public static final String MAX_PROPERTY = "max";
    public static final String MIN_PROPERTY = "min";
    public static final String TO_META_DATA_PROPERTY = "toMetaData";

    public static final String ID_PK_COLUMN = "id";

    public void setCategories(String categories) {
        writeProperty("categories", categories);
    }
    public String getCategories() {
        return (String)readProperty("categories");
    }

    public void setDefault(String _default) {
        writeProperty("default", _default);
    }
    public String getDefault() {
        return (String)readProperty("default");
    }

    public void setMax(String max) {
        writeProperty("max", max);
    }
    public String getMax() {
        return (String)readProperty("max");
    }

    public void setMin(String min) {
        writeProperty("min", min);
    }
    public String getMin() {
        return (String)readProperty("min");
    }

    public void setToMetaData(MetaDataDAO toMetaData) {
        setToOneTarget("toMetaData", toMetaData, true);
    }

    public MetaDataDAO getToMetaData() {
        return (MetaDataDAO)readProperty("toMetaData");
    }


}
