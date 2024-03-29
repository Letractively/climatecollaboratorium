package mit.simulation.climate.dao.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;

import mit.simulation.climate.dao.MetaDataDAO;
import mit.simulation.climate.dao.ScenarioDAO;
import mit.simulation.climate.dao.TupleDAO;

/**
 * Class _VariableDAO was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _VariableDAO extends CayenneDataObject {

    public static final String ID_PROPERTY = "id";
    public static final String SCENARIO_INPUT_PROPERTY = "scenarioInput";
    public static final String SCENARIO_OUTPUT_PROPERTY = "scenarioOutput";
    public static final String VAR_TO_SCENARIO_INPUT_PROPERTY = "varToScenarioInput";
    public static final String VARIABLE_TO_META_DATA_PROPERTY = "variableToMetaData";
    public static final String VARIABLE_TO_TUPLES_PROPERTY = "variableToTuples";

    public static final String ID_PK_COLUMN = "id";

    public void setId(Long id) {
        writeProperty("id", id);
    }
    public Long getId() {
        return (Long)readProperty("id");
    }

    public void setScenarioInput(Long scenarioInput) {
        writeProperty("scenarioInput", scenarioInput);
    }
    public Long getScenarioInput() {
        return (Long)readProperty("scenarioInput");
    }

    public void setScenarioOutput(Long scenarioOutput) {
        writeProperty("scenarioOutput", scenarioOutput);
    }
    public Long getScenarioOutput() {
        return (Long)readProperty("scenarioOutput");
    }

    public void setVarToScenarioInput(ScenarioDAO varToScenarioInput) {
        setToOneTarget("varToScenarioInput", varToScenarioInput, true);
    }

    public ScenarioDAO getVarToScenarioInput() {
        return (ScenarioDAO)readProperty("varToScenarioInput");
    }


    public void setVariableToMetaData(MetaDataDAO variableToMetaData) {
        setToOneTarget("variableToMetaData", variableToMetaData, true);
    }

    public MetaDataDAO getVariableToMetaData() {
        return (MetaDataDAO)readProperty("variableToMetaData");
    }


    public void addToVariableToTuples(TupleDAO obj) {
        addToManyTarget("variableToTuples", obj, true);
    }
    public void removeFromVariableToTuples(TupleDAO obj) {
        removeToManyTarget("variableToTuples", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<TupleDAO> getVariableToTuples() {
        return (List<TupleDAO>)readProperty("variableToTuples");
    }


}
