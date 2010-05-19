/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ActivitySubscriptionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription);

    public void cacheResult(
        java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> activitySubscriptions);

    public void clearCache();

    public com.ext.portlet.Activity.model.ActivitySubscription create(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK);

    public com.ext.portlet.Activity.model.ActivitySubscription remove(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription remove(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ActivitySubscription activitySubscription, boolean merge)</code>.
     */
    public com.ext.portlet.Activity.model.ActivitySubscription update(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.Activity.model.ActivitySubscription update(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription updateImpl(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByPrimaryKey(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription fetchByPrimaryKey(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityId(
        java.lang.Long entityId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityId(
        java.lang.Long entityId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityId(
        java.lang.Long entityId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByentityId_First(
        java.lang.Long entityId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByentityId_Last(
        java.lang.Long entityId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription[] findByentityId_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.Long entityId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByreceiverId(
        java.lang.Long receiverId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByreceiverId(
        java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByreceiverId(
        java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByreceiverId_First(
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByreceiverId_Last(
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription[] findByreceiverId_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByactivitytype(
        java.lang.String activitytype)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByactivitytype(
        java.lang.String activitytype, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByactivitytype(
        java.lang.String activitytype, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByactivitytype_First(
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByactivitytype_Last(
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription[] findByactivitytype_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceiver(
        java.lang.Long entityId, java.lang.Long receiverId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceiver(
        java.lang.Long entityId, java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceiver(
        java.lang.Long entityId, java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByentityreceiver_First(
        java.lang.Long entityId, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByentityreceiver_Last(
        java.lang.Long entityId, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription[] findByentityreceiver_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.Long entityId, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceivertype(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceivertype(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByentityreceivertype(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByentityreceivertype_First(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByentityreceivertype_Last(
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription[] findByentityreceivertype_PrevAndNext(
        com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK activitySubscriptionPK,
        java.lang.Long entityId, java.lang.Long receiverId,
        java.lang.String activitytype,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByentityId(java.lang.Long entityId)
        throws com.liferay.portal.SystemException;

    public void removeByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.SystemException;

    public void removeByactivitytype(java.lang.String activitytype)
        throws com.liferay.portal.SystemException;

    public void removeByentityreceiver(java.lang.Long entityId,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException;

    public void removeByentityreceivertype(java.lang.Long entityId,
        java.lang.Long receiverId, java.lang.String activitytype)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByentityId(java.lang.Long entityId)
        throws com.liferay.portal.SystemException;

    public int countByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.SystemException;

    public int countByactivitytype(java.lang.String activitytype)
        throws com.liferay.portal.SystemException;

    public int countByentityreceiver(java.lang.Long entityId,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException;

    public int countByentityreceivertype(java.lang.Long entityId,
        java.lang.Long receiverId, java.lang.String activitytype)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
