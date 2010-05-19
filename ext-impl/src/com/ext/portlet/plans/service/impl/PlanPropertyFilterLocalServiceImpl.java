package com.ext.portlet.plans.service.impl;

import com.ext.portlet.plans.NoSuchPlanPropertyFilterException;
import com.ext.portlet.plans.model.PlanPropertyFilter;
import com.ext.portlet.plans.service.base.PlanPropertyFilterLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class PlanPropertyFilterLocalServiceImpl
    extends PlanPropertyFilterLocalServiceBaseImpl {
    
    public PlanPropertyFilter getByPlanPlanUserSettingsIdPropertyName(Long planUserSettingsId, String propertyName)
    throws SystemException, NoSuchPlanPropertyFilterException {
        return planPropertyFilterPersistence.findByPlanUserSettingsIdPropertyName(planUserSettingsId, propertyName);
    }
}
