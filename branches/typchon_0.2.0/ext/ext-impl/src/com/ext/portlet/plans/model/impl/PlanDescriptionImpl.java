package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


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
    
    public User getUpdateAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUpdateAuthorId());
    }
}
