package org.climatecollaboratorium.models.support;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;

public class ModelDisplayWrapper {
    private ModelDisplay display;
    
    public ModelDisplayWrapper(ModelDisplay display) {
        this.display = display;
    }
    
    public List<ModelInputDisplayItemWrapper> getInputs() {
        List<ModelInputDisplayItemWrapper> items = new ArrayList<ModelInputDisplayItemWrapper>();
        for (ModelInputDisplayItem input: display.getInputs()) {
            items.add(ModelInputDisplayItemWrapper.getInputWrapper(input));
        }
        return items;
    }

}
