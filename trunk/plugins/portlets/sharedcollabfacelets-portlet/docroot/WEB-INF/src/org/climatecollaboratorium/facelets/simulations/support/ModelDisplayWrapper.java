package org.climatecollaboratorium.facelets.simulations.support;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.climatecollaboratorium.facelets.simulations.SimulationBean;


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
        Map<ModelInputDisplayItem,ModelInputDisplayItemWrapper> tracking = new HashMap<ModelInputDisplayItem,ModelInputDisplayItemWrapper> ();
                
        for (ModelInputGroupDisplayItem item: wrapped.getTabs()) {
            ModelInputGroupDisplayItemWrapper itemWrapper = new ModelInputGroupDisplayItemWrapper(item, simulationBean, values);
            wrappedTabs.add(itemWrapper);
            tracking.put(item,itemWrapper);
           // wrappedInputs.addAll(itemWrapper.getAllItems());
        }
        
        for (ModelInputDisplayItem item: wrapped.getNonTabs()) {
            ModelInputDisplayItemWrapper itemWrapper = ModelInputDisplayItemWrapper.getInputWrapper(item, simulationBean, values);
            wrappedNonTabs.add(itemWrapper);
            tracking.put(item,itemWrapper);

        }
        
        //we just need the top level here
        for (ModelInputDisplayItem item: wrapped.getInputs()) {
            if (!tracking.containsKey(item)) {
                wrappedInputs.add(ModelInputDisplayItemWrapper.getInputWrapper(item, simulationBean, values));

            } else {
                wrappedInputs.add(tracking.get(item));
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
        int i = 0;
        for (ModelInputDisplayItemWrapper item: wrappedInputs) {
            System.out.println((++i)+"Process item "+item.getName());
            if (! (item instanceof ModelInputGroupDisplayItemWrapper)) {
                inputsValues.put(item.getId(), item.getTypedValue());
                System.out.println("setting: " + item.getId() + "\t to " + item.getTypedValue());
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
