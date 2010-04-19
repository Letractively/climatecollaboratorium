package mit.simulation.climate.dao;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _MetaDataDAO was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _MetaDataDAO extends CayenneDataObject {

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String EXTERNAL_PROPERTY = "external";
    public static final String ID_PROPERTY = "id";
    public static final String INTERNALNAME_PROPERTY = "internalname";
    public static final String LABELS_PROPERTY = "labels";
    public static final String NAME_PROPERTY = "name";
    public static final String PROFILE_PROPERTY = "profile";
    public static final String TYPE_PROPERTY = "type";
    public static final String UNITS_PROPERTY = "units";
    public static final String META_TO_DEFAULTS_PROPERTY = "metaToDefaults";
    public static final String META_TO_VARIABLE_PROPERTY = "metaToVariable";

    public static final String ID_PK_COLUMN = "id";

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }

    public void setExternal(String external) {
        writeProperty("external", external);
    }
    public String getExternal() {
        return (String)readProperty("external");
    }

    public void setId(String id) {
        writeProperty("id", id);
    }
    public String getId() {
        return (String)readProperty("id");
    }

    public void setInternalname(String internalname) {
        writeProperty("internalname", internalname);
    }
    public String getInternalname() {
        return (String)readProperty("internalname");
    }

    public void setLabels(String labels) {
        writeProperty("labels", labels);
    }
    public String getLabels() {
        return (String)readProperty("labels");
    }

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void setProfile(String profile) {
        writeProperty("profile", profile);
    }
    public String getProfile() {
        return (String)readProperty("profile");
    }

    public void setType(String type) {
        writeProperty("type", type);
    }
    public String getType() {
        return (String)readProperty("type");
    }

    public void setUnits(String units) {
        writeProperty("units", units);
    }
    public String getUnits() {
        return (String)readProperty("units");
    }

    public void addToMetaToDefaults(MetaDataDefaultsDAO obj) {
        addToManyTarget("metaToDefaults", obj, true);
    }
    public void removeFromMetaToDefaults(MetaDataDefaultsDAO obj) {
        removeToManyTarget("metaToDefaults", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<MetaDataDefaultsDAO> getMetaToDefaults() {
        return (List<MetaDataDefaultsDAO>)readProperty("metaToDefaults");
    }


    public void addToMetaToVariable(VariableDAO obj) {
        addToManyTarget("metaToVariable", obj, true);
    }
    public void removeFromMetaToVariable(VariableDAO obj) {
        removeToManyTarget("metaToVariable", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<VariableDAO> getMetaToVariable() {
        return (List<VariableDAO>)readProperty("metaToVariable");
    }


}
