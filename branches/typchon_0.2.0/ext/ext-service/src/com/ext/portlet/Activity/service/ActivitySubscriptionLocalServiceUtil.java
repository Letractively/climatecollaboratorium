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
        java.lang.Long pk) {
        return getService().createActivitySubscription(pk);
    }

    public static void deleteActivitySubscription(java.lang.Long pk)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteActivitySubscription(pk);
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
        java.lang.Long pk)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getActivitySubscription(pk);
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

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> getActivitySubscriptions(
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService()
                   .getActivitySubscriptions(clasz, classPK, type, extraData);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByUser(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getService().findByUser(userId);
    }

    public static boolean isSubscribed(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService()
                   .isSubscribed(userId, classNameId, classPK, type, extraData);
    }

    public static boolean isSubscribed(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().isSubscribed(userId, clasz, classPK, type, extraData);
    }

    public static void deleteSubscription(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.SystemException {
        getService()
            .deleteSubscription(userId, classNameId, classPK, type, extraData);
    }

    public static void deleteSubscription(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData) throws com.liferay.portal.SystemException {
        getService().deleteSubscription(userId, clasz, classPK, type, extraData);
    }

    public static void addSubscription(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService()
            .addSubscription(classNameId, classPK, type, extraData, userId);
    }

    public static void addSubscription(java.lang.Class clasz,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().addSubscription(clasz, classPK, type, extraData, userId);
    }

    public static void addActivityInterpreter(
        com.ext.portlet.Activity.ICollabActivityInterpreter interpreter) {
        getService().addActivityInterpreter(interpreter);
    }

    public static com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreterForClass(
        java.lang.String className) {
        return getService().getInterpreterForClass(className);
    }

    public static com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreterForClass(
        java.lang.Long classNameId) {
        return getService().getInterpreterForClass(classNameId);
    }

    public static java.util.List<com.liferay.portlet.social.model.SocialActivity> getActivities(
        java.lang.Long userId, int start, int count)
        throws com.liferay.portal.SystemException {
        return getService().getActivities(userId, start, count);
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
