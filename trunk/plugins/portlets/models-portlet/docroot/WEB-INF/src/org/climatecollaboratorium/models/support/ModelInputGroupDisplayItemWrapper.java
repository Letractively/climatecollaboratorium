package org.climatecollaboratorium.models.support;

import java.util.List;

import javax.faces.event.ActionEvent;

import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;

import com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.liferay.portal.SystemException;

public class ModelInputGroupDisplayItemWrapper extends ModelInputDisplayItemWrapper {
    private boolean editing;
    private MetaData md;
    private Simulation simulation;

    public ModelInputGroupDisplayItemWrapper(ModelInputGroupDisplayItem wrappedItem) {
        super(wrappedItem);
        simulation = wrappedItem.getSimulation();
    }
    
    public ModelInputGroupDisplayItemWrapper(Simulation simulation) {
        super(null);
        this.simulation = simulation;
    }
    
    
    public List<ModelInputDisplayItem> getDisplayItems() {
        return ((ModelInputGroupDisplayItem)wrappedItem).getDisplayItems();
    }
    
    public void update(ActionEvent e) throws SystemException {
        if (wrappedItem == null) {
            // adding
            if (md != null) {
                ModelInputGroupDisplayItem.create(simulation, md);
            }
            else {
                ModelInputGroupDisplayItem.create(simulation, "name", "description");
            }
        }
    }
    
    public void delete(ActionEvent e) {
        System.out.println("delete");
    }
    
    
    public void cancel(ActionEvent e) {
        System.out.println("cancel");
    }
    
    public void edit(ActionEvent e) {
        editing = true;
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public MetaData getMetaData() {
        if (wrappedItem != null) {
            return ((ModelInputGroupDisplayItem) wrappedItem).getMetaData();
        }
        return null;
    }
    
    public void setMetaData(Long mdId) {
        for (MetaData md: simulation.getInputs()) {
            if (md.getId().equals(mdId)) {
                this.md = md;
            }
        } 
    }
}
