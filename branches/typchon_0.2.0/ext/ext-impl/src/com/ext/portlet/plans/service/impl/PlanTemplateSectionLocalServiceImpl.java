package com.ext.portlet.plans.service.impl;

import java.util.List;

import com.ext.portlet.plans.model.PlanTemplateSection;
import com.ext.portlet.plans.service.base.PlanTemplateSectionLocalServiceBaseImpl;
import com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class PlanTemplateSectionLocalServiceImpl
    extends PlanTemplateSectionLocalServiceBaseImpl {
    
    public List<PlanTemplateSection> findByPlanTemplateId(Long planTemplateId) throws SystemException {
        return planTemplateSectionPersistence.findByPlanTemplateId(planTemplateId);
        
    }
    
    public PlanTemplateSection addPlanTemplateSection(Long planTemplateId, Long sectionId, int weight) throws SystemException {
        PlanTemplateSection pts = createPlanTemplateSection(new PlanTemplateSectionPK(planTemplateId, sectionId));
        
        pts.setWeight(weight);
        pts.store();
        
        return pts;
    }
    
    public void removePlanTemplateSection(Long planTemplateId, Long sectionId) throws SystemException, PortalException {
        getPlanTemplateSection(new PlanTemplateSectionPK(planTemplateId, sectionId)).remove();
    }
}
