package org.climatecollaboratorium.plans.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.plans.admin.wrappers.PlanSectionDefinitionWrapper;

import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.liferay.portal.SystemException;

public class PlanSectionDefinitionsAdmin {
    private PlanSectionDefinitionWrapper edited;
    
    public List<PlanSectionDefinitionWrapper> getSectionDefinitions() throws SystemException {
        List<PlanSectionDefinitionWrapper> ret = new ArrayList<PlanSectionDefinitionWrapper>();
        
        for (PlanSectionDefinition def: PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinitions(0, Integer.MAX_VALUE)) {
            ret.add(new PlanSectionDefinitionWrapper(def));
        }
        
        return ret;
    }
    
    public void editDefinitionActionListener(ActionEvent e) {
        edited = (PlanSectionDefinitionWrapper) e.getComponent().getAttributes().get("definition");
    }
    
    public String createNew() {
        edited = new PlanSectionDefinitionWrapper(PlanSectionDefinitionLocalServiceUtil.createPlanSectionDefinition(null));
        
        return "editPlanSectionDefinition";
    }
    
    

    public void setEdited(PlanSectionDefinitionWrapper edited) {
        this.edited = edited;
    }

    public PlanSectionDefinitionWrapper getEdited() {
        return edited;
    }
    

    

}
