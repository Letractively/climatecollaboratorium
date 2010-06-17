package org.climatecollaboratorium.models;

import java.io.IOException;

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
    private List<SimulationDecorator> simulations;
    private boolean editing;

    public SimulationsBean() throws IOException, SystemException {
        simulations = SimulationsHelper.getInstance().getSimulations();
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
    
    public void edit(ActionEvent e) {
        editing = !editing;
    }
}