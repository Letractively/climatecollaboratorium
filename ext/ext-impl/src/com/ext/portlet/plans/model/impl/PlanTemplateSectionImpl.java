package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanTemplateSection;
import com.ext.portlet.plans.service.PlanTemplateSectionLocalServiceUtil;
import com.liferay.portal.SystemException;


public class PlanTemplateSectionImpl extends PlanTemplateSectionModelImpl
    implements PlanTemplateSection {
    public PlanTemplateSectionImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(this);
        else  PlanTemplateSectionLocalServiceUtil.updatePlanTemplateSection(this);
    }
    
    public void remove() throws SystemException {
        PlanTemplateSectionLocalServiceUtil.deletePlanTemplateSection(this);
    }
}
