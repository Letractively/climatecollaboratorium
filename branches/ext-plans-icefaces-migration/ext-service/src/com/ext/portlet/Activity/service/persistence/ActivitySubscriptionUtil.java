/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service.persistence;

public class ActivitySubscriptionUtil {
    private static ActivitySubscriptionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription) {
        getPersistence().cacheResult(activitySubscription);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> activitySubscriptions) {
        getPersistence().cacheResult(activitySubscriptions);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription create(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK) {
        return getPersistence().create(activitySubscriptionPK);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription remove(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(activitySubscriptionPK);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription remove(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(activitySubscription);
    }

    /**
     * @deprecated Use <code>update(ActivitySubscription activitySubscription, boolean merge)</code>.
     */
    public static com.ext.portlet.Activity.model.ActivitySubscription update(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(activitySubscription);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                activitySubscription the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when activitySubscription is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.Activity.model.ActivitySubscription update(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(activitySubscription, merge);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription updateImpl(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(activitySubscription, merge);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByPrimaryKey(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(activitySubscriptionPK);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription fetchByPrimaryKey(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(activitySubscriptionPK);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityId(
        java.lang.Long entityId) throws com.liferay.portal.SystemException {
        return getPersistence().findByentityId(entityId);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityId(
        java.lang.Long entityId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByentityId(entityId, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityId(
        java.lang.Long entityId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByentityId(entityId, start, end, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByentityId_First(
        java.lang.Long entityId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByentityId_First(entityId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByentityId_Last(
        java.lang.Long entityId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByentityId_Last(entityId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByentityId_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.Long entityId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityId_PrevAndNext(activitySubscriptionPK,
            entityId, obc);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByreceiverId(
        java.lang.Long receiverId) throws com.liferay.portal.SystemException {
        return getPersistence().findByreceiverId(receiverId);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByreceiverId(
        java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByreceiverId(receiverId, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByreceiverId(
        java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByreceiverId(receiverId, start, end, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByreceiverId_First(
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByreceiverId_First(receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByreceiverId_Last(
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByreceiverId_Last(receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByreceiverId_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByreceiverId_PrevAndNext(activitySubscriptionPK,
            receiverId, obc);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByactivitytype(
        java.lang.String activitytype)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByactivitytype(activitytype);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByactivitytype(
        java.lang.String activitytype, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByactivitytype(activitytype, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByactivitytype(
        java.lang.String activitytype, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByactivitytype(activitytype, start, end, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByactivitytype_First(
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByactivitytype_First(activitytype, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByactivitytype_Last(
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByactivitytype_Last(activitytype, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByactivitytype_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByactivitytype_PrevAndNext(activitySubscriptionPK,
            activitytype, obc);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceiver(
        java.lang.Long entityId, java.lang.Long receiverId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByentityreceiver(entityId, receiverId);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceiver(
        java.lang.Long entityId, java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceiver(entityId, receiverId, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceiver(
        java.lang.Long entityId, java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceiver(entityId, receiverId, start, end, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByentityreceiver_First(
        java.lang.Long entityId, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceiver_First(entityId, receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByentityreceiver_Last(
        java.lang.Long entityId, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceiver_Last(entityId, receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByentityreceiver_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.Long entityId, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceiver_PrevAndNext(activitySubscriptionPK,
            entityId, receiverId, obc);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceivertype(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceivertype(entityId, receiverId, activitytype);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceivertype(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceivertype(entityId, receiverId,
            activitytype, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceivertype(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceivertype(entityId, receiverId,
            activitytype, start, end, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByentityreceivertype_First(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceivertype_First(entityId, receiverId,
            activitytype, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByentityreceivertype_Last(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceivertype_Last(entityId, receiverId,
            activitytype, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByentityreceivertype_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityreceivertype_PrevAndNext(activitySubscriptionPK,
            entityId, receiverId, activitytype, obc);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByentityId(java.lang.Long entityId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByentityId(entityId);
    }

    public static void removeByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByreceiverId(receiverId);
    }

    public static void removeByactivitytype(java.lang.String activitytype)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByactivitytype(activitytype);
    }

    public static void removeByentityreceiver(java.lang.Long entityId,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException {
        getPersistence().removeByentityreceiver(entityId, receiverId);
    }

    public static void removeByentityreceivertype(java.lang.Long entityId,
        java.lang.Long receiverId, java.lang.String activitytype)
        throws com.liferay.portal.SystemException {
        getPersistence()
            .removeByentityreceivertype(entityId, receiverId, activitytype);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByentityId(java.lang.Long entityId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByentityId(entityId);
    }

    public static int countByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByreceiverId(receiverId);
    }

    public static int countByactivitytype(java.lang.String activitytype)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByactivitytype(activitytype);
    }

    public static int countByentityreceiver(java.lang.Long entityId,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException {
        return getPersistence().countByentityreceiver(entityId, receiverId);
    }

    public static int countByentityreceivertype(java.lang.Long entityId,
        java.lang.Long receiverId, java.lang.String activitytype)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByentityreceivertype(entityId, receiverId, activitytype);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ActivitySubscriptionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ActivitySubscriptionPersistence persistence) {
        _persistence = persistence;
    }
}
