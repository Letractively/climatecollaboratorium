package com.ext.portlet.Activity.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="ActivitySubscriptionLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.Activity.service.impl.ActivitySubscriptionLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ActivitySubscriptionLocalService {
    public com.ext.portlet.Activity.model.ActivitySubscription addActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription createActivitySubscription(
        java.lang.Long pk);

    public void deleteActivitySubscription(java.lang.Long pk)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.Activity.model.ActivitySubscription getActivitySubscription(
        java.lang.Long pk)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> getActivitySubscriptions(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getActivitySubscriptionsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription,
        boolean merge) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> getActivitySubscriptions(
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByUser(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isSubscribed(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isSubscribed(java.lang.Long userId, java.lang.Class clasz,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void deleteSubscription(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.SystemException;

    public void deleteSubscription(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData) throws com.liferay.portal.SystemException;

    public void addSubscription(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void addSubscription(java.lang.Class clasz, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void addActivityInterpreter(
        com.ext.portlet.Activity.ICollabActivityInterpreter interpreter);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreterForClass(
        java.lang.String className);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreterForClass(
        java.lang.Long classNameId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portlet.social.model.SocialActivity> getActivities(
        java.lang.Long userId, int start, int count)
        throws com.liferay.portal.SystemException;
}
