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
    
    public ModelDisplayWrapper(ModelDisplay wrapped, SimulationBean simulationBean, Map<Long, Object> values) {
        this.wrapped = wrapped;
        this.simulationBean = simulationBean;
        Map<Long, ModelInputDisplayItemWrapper> inputsById = new HashMap<Long, ModelInputDisplayItemWrapper>();
                
        for (ModelInputGroupDisplayItem item: wrapped.getTabs()) {
            System.out.println("tab: " + item.getOriginalName());
            ModelInputGroupDisplayItemWrapper itemWrapper = new ModelInputGroupDisplayItemWrapper(item, simulationBean, values);
            wrappedTabs.add(itemWrapper);
            wrappedInputs.addAll(itemWrapper.getAllItems());
        }
        for (ModelInputDisplayItem item: wrapped.getAllIndividualInputs()) {
            wrappedInputs.add(ModelInputDisplayItemWrapper.getInputWrapper(item, simulationBean, values));
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
                inputsValues.put(item.getId(), item.getValue());
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
                inputsValues.put(groupedItem.getId(), groupedItem.getValue());
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
    
    public List<ModelInputDisplayItem> getNonTabs() {
        return wrapped.getNonTabs();
    }
    
    public List<ModelInputDisplayItemWrapper> getWrappedInputs() {
        return wrappedInputs;
    }

}
