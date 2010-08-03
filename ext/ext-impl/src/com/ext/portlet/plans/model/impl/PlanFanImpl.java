package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanFanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class PlanFanImpl extends PlanFanModelImpl implements PlanFan {
    public PlanFanImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            PlanFanLocalServiceUtil.addPlanFan(this);
        }
        else {
            PlanFanLocalServiceUtil.updatePlanFan(this);
        }
    }
    
    public User getUser() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUserId());
    }
    
    public PlanItem getPlan() throws NoSuchPlanItemException, SystemException {
        return PlanItemLocalServiceUtil.getPlan(getPlanId());
    }
}
