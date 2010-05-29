package org.climatecollaboratorium.models;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.liferay.portal.kernel.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ActionEvent;

import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import mit.simulation.climate.client.comm.ScenarioNotFoundException;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.climatecollaboratorium.jsintegration.JSEvent;
import org.climatecollaboratorium.jsintegration.JSEventHandler;
import org.climatecollaboratorium.jsintegration.JSEventManager;
import org.climatecollaboratorium.models.event.UpdateOutputsOrderHandler;
import org.climatecollaboratorium.models.support.SimulationsHelper;

public class SimulationBean implements JSEventHandler {

    private Simulation simulation;
    private Scenario scenario;
    private boolean editing;
    private String description;
    private JSEventManager jsEventManager;
    private ModelDisplay display;
    
    private Map<Long, String> inputsValues = new HashMap<Long, String>();

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
        if (this.simulation != simulation) {
            if (simulation == null) {
                this.simulation = null;
                return;
            }
            
            System.out.println("simulation change " + (simulation == null ? "null" : simulation.getId()) + " " + (this.simulation == null ? "null" : this.simulation.getId()));
            this.simulation = simulation;
            scenario = null;
            inputsValues.clear();
            
            display = ModelUIFactory.getInstance().getDisplay(simulation);
        
            JSEvent event = new JSEvent();
            event.setId("renderModelInputs");
            event.setTimestamp(System.currentTimeMillis());
            event.setPayload(simulation.getInputs());

            jsEventManager.sendEvent(event);
        
            display.getInputs().get(0).getMetaData().getProfile();
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

    public void updateSimulation(ActionEvent event) throws IOException, ModelNotFoundException {
        simulation.setDescription(description);
        SimulationsHelper.getInstance().getRepository().updateSimulation(simulation);
        editing = false;
    }

    public void editSimulation(ActionEvent event) {
        editing = true;
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
    }

    public void onJsEvent(JSEvent event) {
        //JSONObject.fromevent.getPayload();
        
        System.out.println(event.getPayload().getClass().getName());
        DynaBean bean = (DynaBean) event.getPayload();
        Map<Long, Object> inputs = new HashMap<Long, Object>();
        
        for (MetaData md: simulation.getInputs()) {
            Object value = bean.get(md.getId().toString());
            inputs.put(md.getId(), value);
            inputsValues.put(md.getId(), value.toString());
        }
        try {
            
            System.out.println("Running the simulation");
            scenario = SimulationsHelper.getInstance().runSimulation(simulation, inputs);
            
            System.out.println("Simulation run was successful, scenario id: " + scenario.getId());

            display = ModelUIFactory.getInstance().getDisplay(scenario);
            //outputDisplay = display;
            
            System.out.println("On js event: " + event);
           // SimulationsHelper.getInstance().runSimulation(simulation, inputs);
            

            event.setId("modelRunSuccessful");
            event.setTimestamp(System.currentTimeMillis());
            event.setPayload(simulation.getInputs());

            jsEventManager.sendEvent(event);
            
            //i.getSeriesVariables().get(0).getValue().get(0);
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ScenarioNotFoundException e) {
            e.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            System.out.println("This shouldn't happen");
            // FIXME
            e.printStackTrace();
        }
    }
    
    public Map<Long, String> getInputValues() {
        return inputsValues;
    }
}