package org.climatecollaboratorium.models;

import java.io.IOException;
import java.util.List;

import javax.faces.event.ActionEvent;


import org.climatecollaboratorium.models.support.SimulationDecorator;
import org.climatecollaboratorium.models.support.SimulationsHelper;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import edu.mit.cci.simulation.client.comm.ClientRepository;

public class SimulationsBean {

    private ClientRepository r;
    private List<SimulationDecorator> simulations;
    private CategoriesBean categories;
    private boolean editing;

    public SimulationsBean() throws IOException, SystemException, PortalException {
        simulations = SimulationsHelper.getInstance().getSimulations();
        //updateVisible();
    }

    private void updateVisible() throws SystemException, IOException, PortalException {
        categories.updateCategorySimulations();
    }
        


    public List<SimulationDecorator> getSimulations() {
        return simulations;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public void edit(ActionEvent e) throws SystemException, IOException, PortalException {
        editing = !editing;
        if (!editing) updateVisible();
    }

    public CategoriesBean getCategories() {
            return categories;
    }

    public void setCategories(CategoriesBean bean) {
        this.categories = bean;
    }
}