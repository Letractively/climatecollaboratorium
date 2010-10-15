package com.ext.portlet.plans.service.impl;

import com.ext.portlet.plans.NoSuchPlanColumnSettingsException;
import com.ext.portlet.plans.model.PlanColumnSettings;
import com.ext.portlet.plans.service.base.PlanColumnSettingsLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class PlanColumnSettingsLocalServiceImpl
    extends PlanColumnSettingsLocalServiceBaseImpl {
    
    public PlanColumnSettings findByPlanUserSettingsIdColumnName(Long planUserSettingsId, String columnName) 
        throws NoSuchPlanColumnSettingsException, SystemException {
        return planColumnSettingsPersistence.findByPlanUserSettingsIdColumnName(planUserSettingsId, columnName);
    }
}
