package org.climatecollaboratorium.models;

import java.io.IOException;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.models.support.SimulationsHelper;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ModelNotFoundException;

public class SimulationBean {
    
    private Simulation simulation;
    private boolean editing;
    private String description;
    
    public SimulationBean() {
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
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

}
