package org.climatecollaboratorium.plans.admin.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanSectionDefinitionWrapper {

    private PlanSectionDefinition definition;

    public PlanSectionDefinitionWrapper(PlanSectionDefinition definition) {
        this.definition = definition;
        
        
        
    }

    public void setDefinition(PlanSectionDefinition definition) {
        this.definition = definition;
    }

    public PlanSectionDefinition getDefinition() {
        return definition;
    }
    
    public String save() throws SystemException {
        definition.store();
        
        return "backToIndex";
    }
    
    public void focusAreaChange(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(Long.parseLong(e.getNewValue().toString()));
        
        definition.setFocusAreaId(fa.getId());
        definition.store();
    }
    
    public List<SelectItem> getAvailableFocusAreas() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        for (FocusArea fa: FocusAreaLocalServiceUtil.getFocusAreas(0, Integer.MAX_VALUE)) {
            ret.add(new SelectItem(fa.getId(), fa.getName()));
        }
        return ret;
    }
    
    
}
