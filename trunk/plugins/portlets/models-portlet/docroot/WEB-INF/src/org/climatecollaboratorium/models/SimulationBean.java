package org.climatecollaboratorium.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.models.service.base.ModelInputGroupType;
import com.ext.portlet.models.ui.*;
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
import org.climatecollaboratorium.models.support.ModelDisplayWrapper;
import org.climatecollaboratorium.models.support.ModelInputDisplayItemWrapper;
import org.climatecollaboratorium.models.support.ModelInputGroupDisplayItemWrapper;
import org.climatecollaboratorium.models.support.ModelOutputErrorSettingWrapper;
import org.climatecollaboratorium.models.support.SimulationsHelper;
import org.climatecollaboratorium.models.support.SupportBean;

import com.liferay.portal.SystemException;

public class SimulationBean implements JSEventHandler {

    private Simulation simulation;
    private Scenario scenario;
    private boolean editing;
    private String description;
    private JSEventManager jsEventManager;
    private ModelDisplayWrapper display;
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

    private Map<Long, Object> inputsValues = new HashMap<Long, Object>();
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

    public ModelDisplayWrapper getDisplay() {
        return display;
    }

    public boolean getHasTabs() {
       return display.hasTabs();
    }

    public void setSimulation(Simulation simulation) {
        if (simulation == null) {
            this.simulation = null;
            return;
        }

        this.simulation = simulation;
        scenario = null;
       // inputsValues.clear();
        editing = false;

        updateDisplay();

        JSEvent event = new JSEvent();
        event.setId("renderModelInputs");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload(simulation.getInputs());

        jsEventManager.sendEvent(event);

        for (SimulationChangedListener listener : simulationChangedListeners) {
            listener.onSimulationChanged(simulation, display.getWrapped());
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

       // inputsValues.clear();
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
            listener.onSimulationChanged(simulation, display.getWrapped());
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

    /** Runs the model */
    public void onJsEvent(JSEvent event) {
        // JSONObject.fromevent.getPayload();

        DynaBean bean = (DynaBean) event.getPayload();
        Map<Long, Object> inputs = new HashMap<Long, Object>();
        for (MetaData md : simulation.getInputs()) {
            try {
                Object value = bean.get(md.getId().toString());
                inputs.put(md.getId(), value);
                inputsValues.put(md.getId(), value.toString());
            }
            catch (Exception e) {
                // ignore
                e.printStackTrace();
                if (inputsValues.containsKey(md.getId())) {
                    inputs.put(md.getId(), inputsValues.get(md.getId()));
                }
                else {
                    inputs.put(md.getId(), md.getMin()[0]);
                }
            }
        }
        try {
            Map<Long, Object> vals = display.getInputsValues();
            scenario = SimulationsHelper.getInstance().runSimulation(simulation, inputs);
            System.out.println("scenario id after run: " + scenario.getId());
            
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
    
    public void runModel(ActionEvent e) throws SystemException, IOException, ScenarioNotFoundException, ModelNotFoundException {
        Map<Long, Object> values = display.getInputsValues();
        /*
        List<Long> inputIds = new ArrayList<Long>();
        Integer year = 2010;
        for (MetaData md: simulation.getInputs()) {
            inputIds.add(md.getId());
            if (! values.containsKey(md.getId())) {
                values.put(md.getId(), year.toString());
                year++;
            }
        }*/
        
        
        Map<Long, Object> vals = new HashMap<Long, Object>();
        inputsValues.putAll(values);
        
        for (Long id: inputsValues.keySet()) {
            System.out.println(id + ": " + inputsValues.get(id));
            vals.put(id, inputsValues.get(id).toString());
        }
        scenario = SimulationsHelper.getInstance().runSimulation(simulation, vals);

        System.out.println("scenario id after run: " + scenario.getId());



        updateDisplay();
        JSEvent event = new JSEvent();
        event.setId("modelRunSuccessful");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload(simulation.getInputs());

        jsEventManager.sendEvent(event);
        scenarioSaved = false;

    }

    public Map<Long, Object> getInputValues() {
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
        display = null;
        System.err.println(ModelInputGroupType.TAB);
        System.err.println(ModelInputGroupType.VERTICAL);
        System.err.println(ModelInputGroupType.HORIZONTAL);
        if (scenario != null) {
            try {
            display = new ModelDisplayWrapper(ModelUIFactory.getInstance().getDisplay(scenario), this, inputsValues);
            } catch (IllegalUIConfigurationException e) {
                e.printStackTrace();
            } catch (SystemException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        else {
            try {
                display = new ModelDisplayWrapper(ModelUIFactory.getInstance().getDisplay(simulation), this, inputsValues);
            } catch (SystemException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalUIConfigurationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (Throwable t) {
                t.printStackTrace();
            }
        } 
        wrappedInputs.clear();
        for (ModelInputDisplayItem item: display.getOryginalInputs()) {
            wrappedInputs.put(item, ModelInputDisplayItemWrapper.getInputWrapper(item, this, inputsValues));
        }
        
        newGroupWrapper = new ModelInputGroupDisplayItemWrapper(this);
    }
    
    public List<SelectItem> getModelInputsOptions() {
        return SupportBean.getModelInputsOptions(display.getWrapped());
    }
    
    public ModelInputGroupDisplayItemWrapper getNewGroupWrapper() {
        return newGroupWrapper;
    }
    
    public List<ModelInputDisplayItem> getIndividualInputsFromDisplay() {
        List<ModelInputDisplayItem> inputs = new ArrayList<ModelInputDisplayItem>();
        for (ModelInputDisplayItem input: display.getOryginalInputs()) {
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