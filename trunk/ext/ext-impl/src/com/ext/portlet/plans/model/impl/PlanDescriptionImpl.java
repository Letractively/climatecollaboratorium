package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.liferay.portal.SystemException;


public class PlanDescriptionImpl extends PlanDescriptionModelImpl
    implements PlanDescription {
    public PlanDescriptionImpl() {
    }
    
    public void store() throws SystemException {
        if (this.isNew()) {
            PlanDescriptionLocalServiceUtil.addPlanDescription(this);
        }
        else {
            PlanDescriptionLocalServiceUtil.updatePlanDescription(this);
        }
    }
}
