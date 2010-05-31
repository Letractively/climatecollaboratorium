package org.climatecollaboratorium.models.support;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.ext.portlet.models.ui.ModelInputWidgetType;

public class SupportBean {
    
    public List<SelectItem> getInputWidgets() {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        
        List<String> ret = new ArrayList<String>();
        for(ModelInputWidgetType type: ModelInputWidgetType.values()) {
            selectItems.add(new SelectItem(type.name(), type.name()));
        }
        return selectItems;
    }

}
