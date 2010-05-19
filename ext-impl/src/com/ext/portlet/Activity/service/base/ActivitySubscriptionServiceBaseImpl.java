/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service.base;

import com.ext.portlet.Activity.service.ActivitySubscriptionLocalService;
import com.ext.portlet.Activity.service.ActivitySubscriptionService;
import com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class ActivitySubscriptionServiceBaseImpl extends PrincipalBean
    implements ActivitySubscriptionService {
    @BeanReference(name = "com.ext.portlet.Activity.service.ActivitySubscriptionLocalService.impl")
    protected ActivitySubscriptionLocalService activitySubscriptionLocalService;
    @BeanReference(name = "com.ext.portlet.Activity.service.ActivitySubscriptionService.impl")
    protected ActivitySubscriptionService activitySubscriptionService;
    @BeanReference(name = "com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPersistence.impl")
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;

    public ActivitySubscriptionLocalService getActivitySubscriptionLocalService() {
        return activitySubscriptionLocalService;
    }

    public void setActivitySubscriptionLocalService(
        ActivitySubscriptionLocalService activitySubscriptionLocalService) {
        this.activitySubscriptionLocalService = activitySubscriptionLocalService;
    }

    public ActivitySubscriptionService getActivitySubscriptionService() {
        return activitySubscriptionService;
    }

    public void setActivitySubscriptionService(
        ActivitySubscriptionService activitySubscriptionService) {
        this.activitySubscriptionService = activitySubscriptionService;
    }

    public ActivitySubscriptionPersistence getActivitySubscriptionPersistence() {
        return activitySubscriptionPersistence;
    }

    public void setActivitySubscriptionPersistence(
        ActivitySubscriptionPersistence activitySubscriptionPersistence) {
        this.activitySubscriptionPersistence = activitySubscriptionPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
