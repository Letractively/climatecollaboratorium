package org.climatecollaboratorium.models.support;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.climatecollaboratorium.models.SimulationBean;

import mit.simulation.climate.client.Simulation;


import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;

public class ModelDisplayWrapper {
    private ModelDisplay wrapped;
    private SimulationBean simulationBean;
    private List<ModelInputDisplayItemWrapper> wrappedInputs = new ArrayList<ModelInputDisplayItemWrapper>();
    private List<ModelInputGroupDisplayItemWrapper> wrappedTabs = new ArrayList<ModelInputGroupDisplayItemWrapper>();
    private List<ModelInputDisplayItemWrapper> wrappedNonTabs = new ArrayList<ModelInputDisplayItemWrapper>();
    
    public ModelDisplayWrapper(ModelDisplay wrapped, SimulationBean simulationBean, Map<Long, Object> values) {
        this.wrapped = wrapped;
        this.simulationBean = simulationBean;
        Set<Long> inputsDefined = new HashSet<Long>();
        Map<Long, ModelInputDisplayItemWrapper> inputsById = new HashMap<Long, ModelInputDisplayItemWrapper>();
                
        for (ModelInputGroupDisplayItem item: wrapped.getTabs()) {
            ModelInputGroupDisplayItemWrapper itemWrapper = new ModelInputGroupDisplayItemWrapper(item, simulationBean, values);
            wrappedTabs.add(itemWrapper);
            wrappedInputs.addAll(itemWrapper.getAllItems());
        }
        
        for (ModelInputDisplayItem item: wrapped.getNonTabs()) {
            ModelInputDisplayItemWrapper itemWrapper = ModelInputDisplayItemWrapper.getInputWrapper(item, simulationBean, values);
            wrappedNonTabs.add(itemWrapper);
            wrappedInputs.add(itemWrapper);
        }
        
        
        for (ModelInputDisplayItemWrapper item: wrappedInputs) {
            if (item != null && item.getMetaData() != null) {
                inputsDefined.add(item.getMetaData().getId());
            }
        }
        for (ModelInputDisplayItem item: wrapped.getAllIndividualInputs()) {
            if (! inputsDefined.contains(item.getMetaData().getId())) {
                wrappedInputs.add(ModelInputDisplayItemWrapper.getInputWrapper(item, simulationBean, values));
                inputsDefined.add(item.getMetaData().getId());
            }
        }
    }
    
    public List<ModelOutputDisplayItem> getOutputs() {
        return wrapped.getOutputs();
    }
    
    public List<ModelInputDisplayItemWrapper> getInputs() {
        return wrappedInputs;
    }

    public ModelDisplay getWrapped() {
        return wrapped;
    }

    public Map<Long, Object> getInputsValues() {
        Map<Long, Object> inputsValues = new HashMap<Long, Object>();
        for (ModelInputDisplayItemWrapper item: wrappedInputs) {
            if (! (item instanceof ModelInputGroupDisplayItemWrapper)) {
                inputsValues.put(item.getId(), item.getTypedValue());
                System.out.println("seting: " + item.getId() + "\t to " + item.getTypedValue());
            } else {
                getInputsValues((ModelInputGroupDisplayItemWrapper) item, inputsValues);
            }
        }
        return inputsValues;
    }

    public void getInputsValues(ModelInputGroupDisplayItemWrapper group, Map<Long, Object> inputsValues) {
        for (ModelInputDisplayItemWrapper groupedItem: group.getDisplayItemsWrapped()) {
            if (groupedItem instanceof ModelInputGroupDisplayItemWrapper) {
                getInputsValues((ModelInputGroupDisplayItemWrapper) groupedItem, inputsValues);
            }
            else {
                System.out.println("seting: " + groupedItem.getId() + "\t to " + groupedItem.getTypedValue());
                inputsValues.put(groupedItem.getId(), groupedItem.getTypedValue());
            }
            
        }
    }
    
    public List<ModelInputGroupDisplayItemWrapper> getTabsWrapped() {
        return wrappedTabs;
    }
    
    public List<ModelInputGroupDisplayItem> getTabs() {
        return wrapped.getTabs();
    }
    
    public boolean hasTabs() {
        return wrapped.getTabs().size() > 0;
    }
    
    public List<ModelInputDisplayItem> getOryginalInputs() {
        return wrapped.getInputs();
    }
    
    public List<ModelInputDisplayItemWrapper> getNonTabs() {
        return wrappedNonTabs;
    }
    
    public List<ModelInputDisplayItemWrapper> getWrappedInputs() {
        return wrappedInputs;
    }
    

}
