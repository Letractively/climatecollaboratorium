package org.climatecollaboratorium.facelets.simulations.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mit.simulation.climate.client.Simulation;

import org.climatecollaboratorium.facelets.simulations.SimulationBean;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;

public class ModelDisplayWrapper {
    private ModelDisplay wrapped;
    private SimulationBean simulationBean;
    private List<ModelInputDisplayItemWrapper> wrappedInputs = new ArrayList<ModelInputDisplayItemWrapper>();
    
    public ModelDisplayWrapper(ModelDisplay wrapped, SimulationBean simulationBean) {
        this.wrapped = wrapped;
        this.simulationBean = simulationBean;
        
        for (ModelInputDisplayItem item: wrapped.getInputs()) {
            wrappedInputs.add(ModelInputDisplayItemWrapper.getInputWrapper(item, simulationBean));
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
                inputsValues.put(item.getWrapped().getMetaData().getId(), item.getValue());
            } else {
                for (ModelInputDisplayItemWrapper groupedItem: ((ModelInputGroupDisplayItemWrapper) item).getDisplayItemsWrapped()) {
                    inputsValues.put(groupedItem.getWrapped().getMetaData().getId(), groupedItem.getValue());
                    
                }
            }
        }
        return inputsValues;
    }

}
