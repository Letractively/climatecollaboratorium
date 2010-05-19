package com.ext.portlet.plans.service.impl;

import com.ext.portlet.plans.NoSuchPlanAttributeFilterException;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.service.base.PlanAttributeFilterLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class PlanAttributeFilterLocalServiceImpl
    extends PlanAttributeFilterLocalServiceBaseImpl {
    
    public PlanAttributeFilter getByPlansUserSettingsIdAttributeName(Long planUserSettingsId, String attributeName) throws SystemException, NoSuchPlanAttributeFilterException {
        return this.planAttributeFilterPersistence.findByPlanUserSettingsIdAttributeName(planUserSettingsId, attributeName);
    }
}
