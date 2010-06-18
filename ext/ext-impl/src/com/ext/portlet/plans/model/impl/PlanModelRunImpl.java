package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.liferay.portal.SystemException;


public class PlanModelRunImpl extends PlanModelRunModelImpl
    implements PlanModelRun {
    public PlanModelRunImpl() {
    }
    
    public void store() throws SystemException {
        if (this.isNew()) {
            PlanModelRunLocalServiceUtil.addPlanModelRun(this);
        }
        else {
            PlanModelRunLocalServiceUtil.updatePlanModelRun(this);
        }
    }
}
