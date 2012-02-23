package com.ext.portlet.plans.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.model.PlanTemplateSection;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class PlanTemplateImpl extends PlanTemplateModelImpl
    implements PlanTemplate {
    public PlanTemplateImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            if (getId() == null) {
                setId(CounterLocalServiceUtil.increment(PlanTemplate.class.getName()));
            }
            
            PlanTemplateLocalServiceUtil.addPlanTemplate(this);
        }
        else {

            PlanTemplateLocalServiceUtil.updatePlanTemplate(this);
        }
    }
    
    public List<PlanSectionDefinition> getSections() throws SystemException, PortalException {
        List<PlanSectionDefinition> ret = new ArrayList<PlanSectionDefinition>();
        for (PlanTemplateSection pts: PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(getId())) {
            ret.add(PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(pts.getPlanSectionId()));
        }
        
        return ret;
    }
    
    public void addSection(PlanSectionDefinition section) throws SystemException, PortalException {
        
        int maxWeight = 0;
        for (PlanTemplateSection def: PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(getId())) {
            maxWeight = Math.max(maxWeight, def.getWeight());
        }
        
        PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(this.getId(), section.getId(), maxWeight+1);
    }
    
    public void removeSection(PlanSectionDefinition section) throws SystemException, PortalException {
        PlanTemplateSectionLocalServiceUtil.removePlanTemplateSection(getId(), section.getId());
    }
    
    public void updateSectionWeight(PlanSectionDefinition section, int weight) throws SystemException, PortalException {
        PlanTemplateSection pts = PlanTemplateSectionLocalServiceUtil.getPlanTemplateSection(new PlanTemplateSectionPK(getId(), section.getId()));
        pts.setWeight(weight);
        pts.store();
    }
}
