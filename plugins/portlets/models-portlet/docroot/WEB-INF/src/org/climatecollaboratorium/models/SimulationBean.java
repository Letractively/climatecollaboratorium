package org.climatecollaboratorium.models;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelUIFactory;

import java.io.IOException;

import javax.faces.event.ActionEvent;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ModelNotFoundException;

import net.sf.json.JSONArray;

import org.climatecollaboratorium.jsintegration.JSEvent;
import org.climatecollaboratorium.jsintegration.JSEventHandler;
import org.climatecollaboratorium.jsintegration.JSEventManager;
import org.climatecollaboratorium.models.support.SimulationsHelper;

public class SimulationBean implements JSEventHandler {

    private Simulation simulation;
    private boolean editing;
    private String description;
    private JSEventManager jsEventManager;
    private ModelDisplay display;

    public SimulationBean() {
    }

    public Simulation getSimulation() {
        return simulation;
    }
    
    public ModelDisplay getDisplay() {
        return display;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;

        display = ModelUIFactory.getInstance().getDisplay(simulation);
        JSEvent event = new JSEvent();
        event.setId("renderModelInputs");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload(simulation.getInputs());

        jsEventManager.sendEvent(event);
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
    }

    public void onJsEvent(JSEvent event) {
        System.out.println("On js event: " + event);
    }

}