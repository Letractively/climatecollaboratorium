package org.climatecollaboratorium.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.TupleStatus;
import mit.simulation.climate.client.Variable;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import mit.simulation.climate.client.comm.ScenarioNotFoundException;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.climatecollaboratorium.jsintegration.JSEvent;
import org.climatecollaboratorium.jsintegration.JSEventHandler;
import org.climatecollaboratorium.jsintegration.JSEventManager;
import org.climatecollaboratorium.models.event.UpdateInputWidgetsHandler;
import org.climatecollaboratorium.models.event.UpdateOutputsOrderHandler;
import org.climatecollaboratorium.models.support.ModelInputDisplayItemWrapper;
import org.climatecollaboratorium.models.support.ModelInputGroupDisplayItemWrapper;
import org.climatecollaboratorium.models.support.ModelOutputErrorSettingWrapper;
import org.climatecollaboratorium.models.support.SimulationsHelper;
import org.climatecollaboratorium.models.support.SupportBean;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItemType;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItemType;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelOutputSeriesDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.liferay.portal.SystemException;

public class SimulationBean implements JSEventHandler {

    private Simulation simulation;
    private Scenario scenario;
    private boolean editing;
    private String description;
    private JSEventManager jsEventManager;
    private ModelDisplay display;
    private boolean embeddedEditing;
    private Map<ModelInputDisplayItem, ModelInputDisplayItemWrapper> wrappedInputs = new HashMap<ModelInputDisplayItem, ModelInputDisplayItemWrapper>();
    private List<ModelOutputErrorSettingWrapper> outputErrorSettingWrappers = new ArrayList<ModelOutputErrorSettingWrapper>();

    public boolean isEmbeddedEditing() {
        return embeddedEditing;
    }

    public void setEmbeddedEditing(boolean embeddedEditing) {
        this.embeddedEditing = embeddedEditing;
    }

    private List<SimulationChangedListener> simulationChangedListeners = new ArrayList<SimulationChangedListener>();

    private Map<Long, String> inputsValues = new HashMap<Long, String>();
    private boolean scenarioSaved;
    private ModelInputGroupDisplayItemWrapper newGroupWrapper;

    public SimulationBean() {
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public ModelDisplay getDisplay() {
        return display;
    }

    public void setSimulation(Simulation simulation) {
        if (simulation == null) {
            this.simulation = null;
            return;
        }

        this.simulation = simulation;
        scenario = null;
        inputsValues.clear();
        editing = false;

        updateDisplay();

        JSEvent event = new JSEvent();
        event.setId("renderModelInputs");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload(simulation.getInputs());

        jsEventManager.sendEvent(event);

        for (SimulationChangedListener listener : simulationChangedListeners) {
            listener.onSimulationChanged(simulation, display);
        }
    }

    public void setScenario(Scenario scenario) {
        if (scenario == null) {
            this.simulation = null;
            this.scenario = null;
            return;
        }

        this.scenario = scenario;
        this.simulation = scenario.getSimulation();
        scenarioSaved = false;

        inputsValues.clear();
        editing = false;

        updateDisplay();
        for (Variable var: scenario.getInputSet()) {
            inputsValues.put(var.getMetaData().getId(), var.getValue().get(0).getValues()[0]);
        }
        JSEvent event = new JSEvent();
        event.setId("modelRunSuccessful");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload(simulation.getInputs());

        jsEventManager.sendEvent(event);

        for (SimulationChangedListener listener : simulationChangedListeners) {
            listener.onSimulationChanged(simulation, display);
        }
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public String getDescription() {
        return simulation.getDescription();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateSimulation(ActionEvent event) throws IOException, ModelNotFoundException, SystemException {
        simulation.setDescription(description);
        SimulationsHelper.getInstance().getRepository().updateSimulation(simulation);
        editing = false;
    }

    public void editSimulation(ActionEvent event) {
        editing = true;
        outputErrorSettingWrappers.clear();
        for (ModelOutputDisplayItem item: getAllOutputsFromDisplay()) {
            if (item instanceof ModelOutputSeriesDisplayItem || item instanceof ModelOutputIndexedDisplayItem) {
                for (TupleStatus status: TupleStatus.values()) {
                    if (status.getCode() != null) {
                        outputErrorSettingWrappers.add(new ModelOutputErrorSettingWrapper(item, status, this));
                    }
                }
            }
        }
    }

    public void cancelEditing(ActionEvent event) {
        editing = false;
    }

    public String getInputsJSON() {
        return JSONArray.fromObject(simulation.getInputs()).toString();
    }

    public void setInputsJSON(String x) {
    }

    public void setJSEventManager(JSEventManager jsEventManager) {
        this.jsEventManager = jsEventManager;
        jsEventManager.addJsEventHandler(this, "simulationInputsDefined");
        jsEventManager.addJsEventHandler(this, "modelRun");

        UpdateOutputsOrderHandler outputsOrderHandler = new UpdateOutputsOrderHandler();
        jsEventManager.addJsEventHandler(outputsOrderHandler, "updateOutputsOrder");

        UpdateInputWidgetsHandler updateInputsWidgetsHandler = new UpdateInputWidgetsHandler();
        simulationChangedListeners.add(updateInputsWidgetsHandler);

        jsEventManager.addJsEventHandler(updateInputsWidgetsHandler, "updateInputWidgets");

        // simulationChangedListeners.add(e)
    }

    public void onJsEvent(JSEvent event) {
        // JSONObject.fromevent.getPayload();

        DynaBean bean = (DynaBean) event.getPayload();
        Map<Long, Object> inputs = new HashMap<Long, Object>();
        for (MetaData md : simulation.getInputs()) {
            Object value = bean.get(md.getId().toString());
            inputs.put(md.getId(), value);
            inputsValues.put(md.getId(), value.toString());
        }
        try {

            scenario = SimulationsHelper.getInstance().runSimulation(simulation, inputs);
            
            for (Variable var: scenario.getInputSet()) {
                inputsValues.put(var.getId(), var.getValue().get(0).getValues()[0]);
            }


            updateDisplay();

            event.setId("modelRunSuccessful");
            event.setTimestamp(System.currentTimeMillis());
            event.setPayload(simulation.getInputs());

            jsEventManager.sendEvent(event);
            scenarioSaved = false;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ScenarioNotFoundException e) {
            e.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            // FIXME
            e.printStackTrace();
        }
    }

    public Map<Long, String> getInputValues() {
        return inputsValues;
    }

    public void editActions(ActionEvent e) {
        embeddedEditing = !embeddedEditing;
        scenarioSaved = false;
    }

    public void saveScenario(ActionEvent e) throws ScenarioNotFoundException, IOException, SystemException {
        SimulationsHelper.getInstance().saveScenario(scenario);

        JSEvent event = new JSEvent();

        event.setId("modelSaved");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload("");

        jsEventManager.sendEvent(event);
        embeddedEditing = false;

    }

    public boolean isScenarioSaved() {
        return scenarioSaved;
    }

    public void setScenarioSaved(boolean scenarioSaved) {
        this.scenarioSaved = scenarioSaved;
    }
    
    public void updateInputs(ActionEvent e) {
        updateDisplay();
    }
    
    
    public Map<ModelInputDisplayItem, ModelInputDisplayItemWrapper> getWrappedInputs() {
        return wrappedInputs;
    }
    
    public void updateDisplay() {
        if (scenario != null) {
            display = ModelUIFactory.getInstance().getDisplay(scenario);
        }
        else {
            display = ModelUIFactory.getInstance().getDisplay(simulation);
        } 
        wrappedInputs.clear();
        for (ModelInputDisplayItem item: display.getInputs()) {
            wrappedInputs.put(item, ModelInputDisplayItemWrapper.getInputWrapper(item, this));
        }
        
        newGroupWrapper = new ModelInputGroupDisplayItemWrapper(this);
    }
    
    public List<SelectItem> getModelInputsOptions() {
        return SupportBean.getModelInputsOptions(display);
    }
    
    public ModelInputGroupDisplayItemWrapper getNewGroupWrapper() {
        return newGroupWrapper;
    }
    
    public List<ModelInputDisplayItem> getIndividualInputsFromDisplay() {
        List<ModelInputDisplayItem> inputs = new ArrayList<ModelInputDisplayItem>();
        for (ModelInputDisplayItem input: display.getInputs()) {
            if (input.getDisplayItemType() == ModelInputDisplayItemType.INDIVIDUAL) {
                inputs.add(input);
            }
            else {
                for (ModelInputDisplayItem groupedInput: ((ModelInputGroupDisplayItem) input).getDisplayItems()) {
                    inputs.add(groupedInput);
                }
            }
        }
        return inputs;
    }
    
    public List<ModelOutputDisplayItem> getAllOutputsFromDisplay() {
        List<ModelOutputDisplayItem> outputs = new ArrayList<ModelOutputDisplayItem>();
        for (ModelOutputDisplayItem output: display.getOutputs()) {
            outputs.add(output);
            if (output.getDisplayItemType() == ModelOutputDisplayItemType.INDEXED) {
                for (ModelOutputDisplayItem serie: ((ModelOutputIndexedDisplayItem) output).getSeries()) {
                    outputs.add(serie);
                }
            }
        }
        return outputs;
    }
    
    public Map<ModelOutputDisplayItem, List<SelectItem>> getOutputAssociationOptions() {
        Map<ModelOutputDisplayItem, List<SelectItem>> itemsMap = new HashMap<ModelOutputDisplayItem, List<SelectItem>>();
        for (ModelOutputDisplayItem output: display.getOutputs()) {
            if (output.getDisplayItemType() == ModelOutputDisplayItemType.INDEXED) {
                List<SelectItem> options = new ArrayList<SelectItem>();
                for (ModelOutputDisplayItem serie: ((ModelOutputIndexedDisplayItem) output).getSeries()) {
                    itemsMap.put(serie, options);
                    options.add(new SelectItem( ((ModelOutputSeriesDisplayItem) serie).getMetaData().getId(), serie.getName()));
                }
            }
        }
        return itemsMap;
    }
    
    public List<ModelOutputErrorSettingWrapper> getOutputErrorSettingWrappers() {
        return outputErrorSettingWrappers;
    }
}