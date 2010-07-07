package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


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
    
    public User getUpdateAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUpdateAuthorId());
    }
}
