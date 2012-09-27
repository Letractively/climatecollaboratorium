package com.ext.portlet.Activity.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ActivitySubscriptionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription);

    public void cacheResult(
        java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> activitySubscriptions);

    public void clearCache();

    public com.ext.portlet.Activity.model.ActivitySubscription create(
        java.lang.Long pk);

    public com.ext.portlet.Activity.model.ActivitySubscription remove(
        java.lang.Long pk)
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
        java.lang.Long pk)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription fetchByPrimaryKey(
        java.lang.Long pk) throws com.liferay.portal.SystemException;

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
        java.lang.Long pk, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKReceiverId_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKReceiverId_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKReceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeReceiverId_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeReceiverId_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKTypeReceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKTypeExtraDataReceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraData_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraData_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKTypeExtraData_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
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

    public void removeByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.SystemException;

    public void removeByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException;

    public void removeByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId)
        throws com.liferay.portal.SystemException;

    public void removeByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException;

    public void removeByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.SystemException;

    public int countByClassNameIdClassPKReceiverId(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Long receiverId)
        throws com.liferay.portal.SystemException;

    public int countByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId)
        throws com.liferay.portal.SystemException;

    public int countByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException;

    public int countByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
