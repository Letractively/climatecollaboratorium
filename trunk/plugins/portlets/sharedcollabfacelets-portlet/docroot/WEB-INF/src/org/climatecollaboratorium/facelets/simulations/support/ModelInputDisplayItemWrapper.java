package org.climatecollaboratorium.facelets.simulations.support;


import java.util.Map;

import org.climatecollaboratorium.facelets.simulations.SimulationBean;

import mit.simulation.climate.client.MetaData;


import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItemType;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelInputIndividualDisplayItem;
import com.ext.portlet.models.ui.ModelInputWidgetType;
import com.liferay.portal.SystemException;

public class ModelInputDisplayItemWrapper {
    protected ModelInputDisplayItem wrappedItem;
    private Double value;


    public static ModelInputDisplayItemWrapper getInputWrapper(ModelInputDisplayItem input, SimulationBean bean, Map<Long, Object> values) {
        if (input.getDisplayItemType() == ModelInputDisplayItemType.GROUP) {
            return new ModelInputGroupDisplayItemWrapper((ModelInputGroupDisplayItem) input, bean, values);
        }
        return new ModelInputDisplayItemWrapper(input, values);
    }
    
    public ModelInputDisplayItemWrapper(ModelInputDisplayItem wrappedItem, Map<Long, Object> values) {
        this.wrappedItem = wrappedItem;
        
        if (this.hasValue()) {
            if (values.containsKey(wrappedItem.getMetaData().getId())) {
                value = Double.valueOf(values.get(getId()).toString());
            }
            else {
                String defVal = wrappedItem.getMetaData() != null && wrappedItem.getMetaData().getDefault() != null ? wrappedItem.getMetaData().getDefault()[0] : null;
                value = defVal == null || defVal.trim().length() == 0 ? Double.parseDouble(wrappedItem.getMetaData().getMin()[0]) : Double.parseDouble(defVal);
            }
        }
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
    
    public Object getTypedValue() {
        if (wrappedItem.getMetaData().getProfile()[0].equals(Integer.class)) {
            return Math.round(value);
        }
        else 
        return value;
    
        
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
    
    protected boolean hasValue() {
        return true;
    }
    
    public Long getId() {
        System.out.println("returning id: " + getMetaData().getId());
        return getMetaData().getId();
    }

}
