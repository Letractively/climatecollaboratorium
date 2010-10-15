/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service;


/**
 * <a href="ActivitySubscriptionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.Activity.service.ActivitySubscriptionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.Activity.service.ActivitySubscriptionLocalService
 *
 */
public class ActivitySubscriptionLocalServiceUtil {
    private static ActivitySubscriptionLocalService _service;

    public static com.ext.portlet.Activity.model.ActivitySubscription addActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException {
        return getService().addActivitySubscription(activitySubscription);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription createActivitySubscription(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK) {
        return getService().createActivitySubscription(activitySubscriptionPK);
    }

    public static void deleteActivitySubscription(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteActivitySubscription(activitySubscriptionPK);
    }

    public static void deleteActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException {
        getService().deleteActivitySubscription(activitySubscription);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription getActivitySubscription(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getActivitySubscription(activitySubscriptionPK);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> getActivitySubscriptions(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getActivitySubscriptions(start, end);
    }

    public static int getActivitySubscriptionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getActivitySubscriptionsCount();
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException {
        return getService().updateActivitySubscription(activitySubscription);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateActivitySubscription(activitySubscription, merge);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByUser(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getService().findByUser(userId);
    }

    public static ActivitySubscriptionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ActivitySubscriptionLocalService is not set");
        }

        return _service;
    }

    public void setService(ActivitySubscriptionLocalService service) {
        _service = service;
    }
}
