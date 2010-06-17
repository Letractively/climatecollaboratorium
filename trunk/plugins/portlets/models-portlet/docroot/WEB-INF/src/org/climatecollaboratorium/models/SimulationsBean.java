package org.climatecollaboratorium.models;

import java.io.IOException;

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
        Collections.sort(simulations, new Comparator<SimulationDecorator>() {

            @Override
            public int compare(SimulationDecorator o1, SimulationDecorator o2) {

                try {
                    if (!o1.isVisible()) {
                        return 1;
                    } else if (!o2.isVisible()) {
                        return -1;
                    }
                } catch (SystemException e) {
                    // ignore
                }
                return o1.getName().compareTo(o2.getName());
            }

        });
    }
}