package org.climatecollaboratorium.plans.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.plans.admin.wrappers.PlanTemplateWrapper;

import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.service.PlanTemplateLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanTemplatesAdmin {
    private PlanTemplateWrapper edited;
    
    public List<PlanTemplateWrapper> getPlanTemplates() throws SystemException, PortalException {
        List<PlanTemplateWrapper> ret = new ArrayList<PlanTemplateWrapper>();
        
        for (PlanTemplate def: PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE)) {
            ret.add(new PlanTemplateWrapper(def));
        }
        
        return ret;
    }
    
    public void edit(ActionEvent e) {
        edited = (PlanTemplateWrapper) e.getComponent().getAttributes().get("template");
    }
    
    public String createNew() throws PortalException, SystemException {
        edited = new PlanTemplateWrapper(PlanTemplateLocalServiceUtil.createPlanTemplate(null));
        
        return "editPlanTemplate";
    }
    
    

    public void setEdited(PlanTemplateWrapper edited) {
        this.edited = edited;
    }

    public PlanTemplateWrapper getEdited() {
        return edited;
    }

}
