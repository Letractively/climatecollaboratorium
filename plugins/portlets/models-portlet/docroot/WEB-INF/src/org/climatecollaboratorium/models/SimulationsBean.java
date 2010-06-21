package org.climatecollaboratorium.models;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import mit.simulation.climate.client.comm.ClientRepository;

import org.climatecollaboratorium.models.support.SimulationDecorator;
import org.climatecollaboratorium.models.support.SimulationsHelper;

import com.ext.portlet.models.ui.ModelUIFactory;
import com.liferay.portal.SystemException;

public class SimulationsBean {

    private ClientRepository r;
    public List<SimulationDecorator> getVisibleSimulations() {
        return visibleSimulations;
    }

    private List<SimulationDecorator> simulations;
    private List<SimulationDecorator> visibleSimulations = new ArrayList<SimulationDecorator>();
    private boolean editing;

    public SimulationsBean() throws IOException, SystemException {
        simulations = SimulationsHelper.getInstance().getSimulations();
        updateVisible();
    }

    private void updateVisible() throws SystemException {
        visibleSimulations.clear();
        for (SimulationDecorator sim: simulations) {
            if (sim.isVisible()) {
                visibleSimulations.add(sim);
            }
        }
        
    }

    public List<SimulationDecorator> getSimulations() {
        return simulations;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean ediitng) {
        this.editing = editing;
    }

    public void edit(ActionEvent e) throws SystemException {
        editing = !editing;
        updateVisible();
    }
}