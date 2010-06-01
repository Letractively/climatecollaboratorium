package mit.simulation.climate.dao.auto;

import org.apache.cayenne.CayenneDataObject;

import mit.simulation.climate.dao.VariableDAO;

/**
 * Class _TupleDAO was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _TupleDAO extends CayenneDataObject {

    public static final String ID_PROPERTY = "id";
    public static final String SEQ_PROPERTY = "seq";
    public static final String STATUS_PROPERTY = "status";
    public static final String VALUE_PROPERTY = "value";
    public static final String TUPLES_TO_VARIABLE_PROPERTY = "tuplesToVariable";

    public static final String ID_PK_COLUMN = "id";

    public void setId(Long id) {
        writeProperty("id", id);
    }
    public Long getId() {
        return (Long)readProperty("id");
    }

    public void setSeq(Integer seq) {
        writeProperty("seq", seq);
    }
    public Integer getSeq() {
        return (Integer)readProperty("seq");
    }

    public void setStatus(String status) {
        writeProperty("status", status);
    }
    public String getStatus() {
        return (String)readProperty("status");
    }

    public void setValue(String value) {
        writeProperty("value", value);
    }
    public String getValue() {
        return (String)readProperty("value");
    }

    public void setTuplesToVariable(VariableDAO tuplesToVariable) {
        setToOneTarget("tuplesToVariable", tuplesToVariable, true);
    }

    public VariableDAO getTuplesToVariable() {
        return (VariableDAO)readProperty("tuplesToVariable");
    }


}
