package org.climatecollaboratorium.models.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import com.ext.portlet.models.service.base.ModelInputGroupType;
import org.climatecollaboratorium.models.SimulationBean;

import mit.simulation.climate.client.MetaData;

import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class ModelInputGroupDisplayItemWrapper extends ModelInputDisplayItemWrapper {
    private boolean editing;
    private MetaData md;
    private SimulationBean simulationBean;
    private String name = "";
    private String description = "";
    private ModelInputGroupDisplayItem groupItem;
    private List<ModelInputDisplayItemWrapper> wrappedItems = new ArrayList<ModelInputDisplayItemWrapper>();


    public ModelInputGroupDisplayItemWrapper(ModelInputGroupDisplayItem groupItem, SimulationBean bean, Map<Long, Object> inputsValues) {
        super(groupItem, inputsValues);
        simulationBean = bean;
        this.groupItem = groupItem;

        System.out.println("group: " + getOryginalName());
        for (ModelInputDisplayItem item: groupItem.getAllItems()) {
            wrappedItems.add(ModelInputDisplayItemWrapper.getInputWrapper(item, bean, inputsValues));
            System.out.println(" group item: " + wrappedItems.get(wrappedItems.size()-1).getName());
        }
    }
    
    public ModelInputGroupDisplayItemWrapper(SimulationBean bean) {
        super(null, null);
        this.simulationBean = bean;
    }
    
    
    public List<ModelInputDisplayItem> getDisplayItems() {
        return ((ModelInputGroupDisplayItem)groupItem).getDisplayItems();
    }
    
    public void update(ActionEvent e) throws SystemException {
        if (groupItem == null) {
            // adding
            ModelInputGroupDisplayItem createdItem = null;
            if (md != null) {
                createdItem = ModelInputGroupDisplayItem.create(simulationBean.getSimulation(), md, ModelInputGroupType.HORIZONTAL);
            }
            else {
                createdItem = ModelInputGroupDisplayItem.create(simulationBean.getSimulation(), name, description, ModelInputGroupType.HORIZONTAL);
            }

            int maxOrder = Integer.MIN_VALUE;
            for (ModelInputDisplayItem item: simulationBean.getDisplay().getOryginalInputs()) {
                if (item.getOrder() > maxOrder) {
                    maxOrder = item.getOrder();
                }
            }
            createdItem.setOrder(maxOrder+1);
        }
        else {
            groupItem.setDescription(description);
            groupItem.setName(name);
            groupItem.setMetaData(md);
        }
        simulationBean.updateDisplay();
    }
    
    public void delete(ActionEvent e) throws PortalException, SystemException {
        if (groupItem != null) {
            ((ModelInputGroupDisplayItem) groupItem).delete();
            simulationBean.updateDisplay();
        }
    }
    
    
    public void cancel(ActionEvent e) {
        editing = false;
    }
    
    public void edit(ActionEvent e) {
        editing = true;
        name = "";
        description = "";
        if (groupItem != null) {
            name = groupItem.getOriginalName();
            description = groupItem.getOriginalDescription();
        }
    }
    
    public boolean isEditing() {
        return editing;
    }
    /*
    public Long getMetaData() {
        if (groupItem != null && groupItem.getMetaData() != null) {
            return groupItem.getMetaData().getId();
        }
        return null;
    }
    */
    
    public void setMetaData(Long mdId) {
        for (MetaData md: simulationBean.getSimulation().getInputs()) {
            if (md.getId().equals(mdId)) {
                this.md = md;
            }
        } 
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isAdding() {
        return groupItem == null;
    }
    
    public String getOryginalDescription() {
        return wrappedItem.getDescription();
    }

    public String getOryginalName() {
        return wrappedItem.getName();
    }
    

    public List<ModelInputDisplayItemWrapper> getDisplayItemsWrapped() {
        return wrappedItems;
    }
    
    public List<ModelInputDisplayItemWrapper> getAllItems() {
        return wrappedItems;
        
    }
    
    protected boolean hasValue() {
        return false;
    }

}
