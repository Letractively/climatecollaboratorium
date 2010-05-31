package org.climatecollaboratorium.models;

import com.ext.portlet.models.ui.ModelDisplay;

import mit.simulation.climate.client.Simulation;

public interface SimulationChangedListener {
    public void onSimulationChanged(Simulation s, ModelDisplay md);

}
