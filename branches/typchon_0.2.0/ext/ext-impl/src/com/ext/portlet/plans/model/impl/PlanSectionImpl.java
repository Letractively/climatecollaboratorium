package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class PlanSectionImpl extends PlanSectionModelImpl implements PlanSection {
    public PlanSectionImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            if (getId() == null) {
                setId(CounterLocalServiceUtil.increment(PlanSection.class.getName()));
            }
            
            PlanSectionLocalServiceUtil.addPlanSection(this);
        }
        else {
            PlanSectionLocalServiceUtil.updatePlanSection(this);
        }
    }
    
    public PlanSectionDefinition getDefinition() throws PortalException, SystemException {
        return PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(getPlanSectionDefinitionId());
    }
}
