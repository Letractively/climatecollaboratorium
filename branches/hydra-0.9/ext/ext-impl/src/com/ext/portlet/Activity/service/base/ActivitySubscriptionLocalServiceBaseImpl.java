/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service.base;

import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalService;
import com.ext.portlet.Activity.service.ActivitySubscriptionService;
import com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK;
import com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class ActivitySubscriptionLocalServiceBaseImpl
    implements ActivitySubscriptionLocalService {
    @BeanReference(name = "com.ext.portlet.Activity.service.ActivitySubscriptionLocalService.impl")
    protected ActivitySubscriptionLocalService activitySubscriptionLocalService;
    @BeanReference(name = "com.ext.portlet.Activity.service.ActivitySubscriptionService.impl")
    protected ActivitySubscriptionService activitySubscriptionService;
    @BeanReference(name = "com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPersistence.impl")
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;

    public ActivitySubscription addActivitySubscription(
        ActivitySubscription activitySubscription) throws SystemException {
        activitySubscription.setNew(true);

        return activitySubscriptionPersistence.update(activitySubscription,
            false);
    }

    public ActivitySubscription createActivitySubscription(
        ActivitySubscriptionPK activitySubscriptionPK) {
        return activitySubscriptionPersistence.create(activitySubscriptionPK);
    }

    public void deleteActivitySubscription(
        ActivitySubscriptionPK activitySubscriptionPK)
        throws PortalException, SystemException {
        activitySubscriptionPersistence.remove(activitySubscriptionPK);
    }

    public void deleteActivitySubscription(
        ActivitySubscription activitySubscription) throws SystemException {
        activitySubscriptionPersistence.remove(activitySubscription);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return activitySubscriptionPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return activitySubscriptionPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public ActivitySubscription getActivitySubscription(
        ActivitySubscriptionPK activitySubscriptionPK)
        throws PortalException, SystemException {
        return activitySubscriptionPersistence.findByPrimaryKey(activitySubscriptionPK);
    }

    public List<ActivitySubscription> getActivitySubscriptions(int start,
        int end) throws SystemException {
        return activitySubscriptionPersistence.findAll(start, end);
    }

    public int getActivitySubscriptionsCount() throws SystemException {
        return activitySubscriptionPersistence.countAll();
    }

    public ActivitySubscription updateActivitySubscription(
        ActivitySubscription activitySubscription) throws SystemException {
        activitySubscription.setNew(false);

        return activitySubscriptionPersistence.update(activitySubscription, true);
    }

    public ActivitySubscription updateActivitySubscription(
        ActivitySubscription activitySubscription, boolean merge)
        throws SystemException {
        activitySubscription.setNew(false);

        return activitySubscriptionPersistence.update(activitySubscription,
            merge);
    }

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
