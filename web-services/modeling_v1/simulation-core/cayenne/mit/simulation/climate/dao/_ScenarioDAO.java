package mit.simulation.climate.dao;

import java.util.Date;
import java.util.List;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _ScenarioDAO was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _ScenarioDAO extends CayenneDataObject {

    public static final String CREATION_PROPERTY = "creation";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String ID_PROPERTY = "id";
    public static final String NAME_PROPERTY = "name";
    public static final String SCENARIO_OUTPUT_TO_VARS_PROPERTY = "scenarioOutputToVars";
    public static final String SCENARIO_TO_AUTHOR_PROPERTY = "scenarioToAuthor";
    public static final String SCENARIO_TO_INPUT_VARS_PROPERTY = "scenarioToInputVars";
    public static final String SCENARIO_TO_SIMULATION_PROPERTY = "scenarioToSimulation";

    public static final String ID_PK_COLUMN = "id";

    public void setCreation(Date creation) {
        writeProperty("creation", creation);
    }
    public Date getCreation() {
        return (Date)readProperty("creation");
    }

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }

    public void setId(String id) {
        writeProperty("id", id);
    }
    public String getId() {
        return (String)readProperty("id");
    }

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void addToScenarioOutputToVars(VariableDAO obj) {
        addToManyTarget("scenarioOutputToVars", obj, true);
    }
    public void removeFromScenarioOutputToVars(VariableDAO obj) {
        removeToManyTarget("scenarioOutputToVars", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<VariableDAO> getScenarioOutputToVars() {
        return (List<VariableDAO>)readProperty("scenarioOutputToVars");
    }


    public void setScenarioToAuthor(UserDAO scenarioToAuthor) {
        setToOneTarget("scenarioToAuthor", scenarioToAuthor, true);
    }

    public UserDAO getScenarioToAuthor() {
        return (UserDAO)readProperty("scenarioToAuthor");
    }


    public void addToScenarioToInputVars(VariableDAO obj) {
        addToManyTarget("scenarioToInputVars", obj, true);
    }
    public void removeFromScenarioToInputVars(VariableDAO obj) {
        removeToManyTarget("scenarioToInputVars", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<VariableDAO> getScenarioToInputVars() {
        return (List<VariableDAO>)readProperty("scenarioToInputVars");
    }


    public void setScenarioToSimulation(SimulationDAO scenarioToSimulation) {
        setToOneTarget("scenarioToSimulation", scenarioToSimulation, true);
    }

    public SimulationDAO getScenarioToSimulation() {
        return (SimulationDAO)readProperty("scenarioToSimulation");
    }


}
