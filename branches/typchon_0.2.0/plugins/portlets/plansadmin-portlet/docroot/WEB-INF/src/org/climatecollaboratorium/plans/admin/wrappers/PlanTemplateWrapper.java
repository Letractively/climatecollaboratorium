package org.climatecollaboratorium.plans.admin.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.model.PlanTemplateSection;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateSectionLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanTemplateWrapper {
    private PlanTemplate template;
    private PlanSectionDefinition newSection = null;

    public PlanTemplateWrapper(PlanTemplate template) throws PortalException, SystemException {
        this.template = template;
    }
    

    public void setTemplate(PlanTemplate template) {
        this.template = template;
    }

    public PlanTemplate getTemplate() {
        return template;
    }
    
    

    public String save() throws SystemException {
        template.store();
        
        return "backToIndex";
    }
    
    
    public List<SelectItem> getAvailableSections() throws SystemException, PortalException {
        List<PlanSectionDefinition> available = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinitions(0, Integer.MAX_VALUE);
        List<PlanSectionDefinition> used = template.getSections();
        
        List<SelectItem> ret = new ArrayList<SelectItem>();
        for (PlanSectionDefinition av: available) {
            if (used.contains(av)) continue;
            ret.add(new SelectItem(av.getId(), av.getTitle()));
        }
        
        return ret;
    }


    public void setNewSection(PlanSectionDefinition newSection) {
        this.newSection = newSection;
    }
    
    public void addSection(ActionEvent e) throws PortalException, SystemException {
        if (newSection != null) template.addSection(newSection);
    }


    public PlanSectionDefinition getNewSection() {
        return newSection;
    }
    
    public void newSection(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        if (e.getNewValue() == null || e.getNewValue().toString().trim().equals("")) return;
        newSection = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(Long.parseLong(e.getNewValue().toString()));
    }
    
    public void reorderSection(ActionEvent e) throws SystemException {
        PlanSectionDefinition def = (PlanSectionDefinition) e.getComponent().getAttributes().get("section");
        int direction = Integer.parseInt(e.getComponent().getAttributes().get("direction").toString());
        
        PlanTemplateSection prev = null, next = null, current = null;
        boolean found = false;
        for (PlanTemplateSection pts: PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(template.getId())) {
            if (pts.getPlanSectionId().equals(def.getId())) {
                found = true;
                current = pts;
            }
            else {
                if (!found) prev = pts;
                else {
                    next = pts;
                    break;
                }
            }
        }

        int tmp = current.getWeight();
        if (direction < 0 && prev != null) {
            current.setWeight(prev.getWeight());
            prev.setWeight(tmp);
            
            current.store();
            prev.store();
        }
        if (direction > 0 && next != null) {
            current.setWeight(next.getWeight());
            next.setWeight(tmp);
            
            next.store();
            current.store();
        }
        
    }
    
    public void removeSection(ActionEvent e) throws SystemException, PortalException {
        PlanSectionDefinition def = (PlanSectionDefinition) e.getComponent().getAttributes().get("section");
        template.removeSection(def);
    }
    

}
