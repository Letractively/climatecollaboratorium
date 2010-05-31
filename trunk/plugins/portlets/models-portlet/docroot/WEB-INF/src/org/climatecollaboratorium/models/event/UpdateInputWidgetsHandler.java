package org.climatecollaboratorium.models.event;

import mit.simulation.climate.client.Simulation;

import org.climatecollaboratorium.jsintegration.JSEvent;
import org.climatecollaboratorium.jsintegration.JSEventHandler;
import org.climatecollaboratorium.models.SimulationChangedListener;

import com.ext.portlet.models.ui.ModelDisplay;

public class UpdateInputWidgetsHandler implements JSEventHandler, SimulationChangedListener {
    private Simulation simulation;
    private ModelDisplay modelDisplay;
    
    @Override
    public void onJsEvent(JSEvent event) {
        System.out.println("Event: " + event);

    }

    @Override
    public void onSimulationChanged(Simulation s, ModelDisplay md) {
        simulation = s;
        modelDisplay = md;
    }

}
