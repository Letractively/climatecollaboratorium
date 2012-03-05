package com.ext.portlet.plans.model.impl;

import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class PlanSectionDefinitionImpl extends PlanSectionDefinitionModelImpl
    implements PlanSectionDefinition {
    
    public PlanSectionDefinitionImpl() {
        
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            if (getId() == null || getId() <= 0) {
                this.setId(CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName()));
            }
            
            PlanSectionDefinitionLocalServiceUtil.addPlanSectionDefinition(this);
        }
        else {
            PlanSectionDefinitionLocalServiceUtil.updatePlanSectionDefinition(this);
        }
    }
    
    public FocusArea getFocusArea() throws PortalException, SystemException {
        if (getFocusAreaId() != null) {
            return FocusAreaLocalServiceUtil.getFocusArea(getFocusAreaId());
        }
        return null;
    }
}
