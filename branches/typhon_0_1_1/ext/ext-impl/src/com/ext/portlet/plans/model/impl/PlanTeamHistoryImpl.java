package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanTeamHistory;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTeamHistoryLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class PlanTeamHistoryImpl extends PlanTeamHistoryModelImpl
    implements PlanTeamHistory {
    public PlanTeamHistoryImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            PlanTeamHistoryLocalServiceUtil.addPlanTeamHistory(this);
        }
        else {
            PlanTeamHistoryLocalServiceUtil.updatePlanTeamHistory(this);
        }
    }
    
    public User getUser() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUserId());
    }
    
    public PlanItem getPlan() throws NoSuchPlanItemException, SystemException {
        return PlanItemLocalServiceUtil.getPlan(getPlanId());
    }
}
