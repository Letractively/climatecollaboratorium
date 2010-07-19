package org.climatecollaboratorium.facelets.simulations.support;

import mit.simulation.climate.client.MetaData;

import org.climatecollaboratorium.facelets.simulations.SimulationBean;

import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItemType;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelInputIndividualDisplayItem;
import com.ext.portlet.models.ui.ModelInputWidgetType;

public class ModelInputDisplayItemWrapper {
    protected ModelInputDisplayItem wrappedItem;
    private Double value;


    public static ModelInputDisplayItemWrapper getInputWrapper(ModelInputDisplayItem input, SimulationBean bean) {
        if (input.getDisplayItemType() == ModelInputDisplayItemType.GROUP) {
            return new ModelInputGroupDisplayItemWrapper((ModelInputGroupDisplayItem) input, bean);
        }
        return new ModelInputDisplayItemWrapper(input);
    }
    
    public ModelInputDisplayItemWrapper(ModelInputDisplayItem wrappedItem) {
        this.wrappedItem = wrappedItem;
    }
    
    public Long getGroupId() {
        if (wrappedItem instanceof ModelInputGroupDisplayItem) {
            return ((ModelInputGroupDisplayItem) wrappedItem).getGroupId();
        }
        else if (wrappedItem instanceof ModelInputIndividualDisplayItem) {
            return ((ModelInputIndividualDisplayItem) wrappedItem).getGroupId();
        }
        return 0L;
    }
    
    public Double getValue() {
        return value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }
    
    public ModelInputDisplayItem getWrapped() {
        return wrappedItem;
    }
    
    public MetaData getMetaData() {
        return wrappedItem.getMetaData();
    }
    
    public ModelInputWidgetType getType() {
        return wrappedItem.getType();
    }
    
    public ModelInputDisplayItemType getDisplayItemType() {
        return wrappedItem.getDisplayItemType();
    }
    
    public String getName() {
        return wrappedItem.getName();
    }
    
    public String getDescription() {
        return wrappedItem.getDescription();
    }

}
