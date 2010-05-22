package org.climatecollaboratorium.models;

import mit.simulation.climate.client.Simulation;

public class SimulationBean {
    
    private Simulation simulation;
    
    public SimulationBean() {
        System.out.println("tworzonko" + this);
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

}
