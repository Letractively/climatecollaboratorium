package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanSectionPlanMap;
import com.ext.portlet.plans.service.PlanSectionPlanMapLocalServiceUtil;
import com.liferay.portal.SystemException;


public class PlanSectionPlanMapImpl extends PlanSectionPlanMapModelImpl
    implements PlanSectionPlanMap {
    public PlanSectionPlanMapImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            PlanSectionPlanMapLocalServiceUtil.addPlanSectionPlanMap(this);
        }
        else {
            PlanSectionPlanMapLocalServiceUtil.updatePlanSectionPlanMap(this);
        }
    }
}
