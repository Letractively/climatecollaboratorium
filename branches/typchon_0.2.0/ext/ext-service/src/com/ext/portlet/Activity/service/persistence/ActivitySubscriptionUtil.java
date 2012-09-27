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
        java.lang.Long pk) {
        return getPersistence().create(pk);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription remove(
        java.lang.Long pk)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(pk);
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
        java.lang.Long pk)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(pk);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription fetchByPrimaryKey(
        java.lang.Long pk) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(pk);
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
        java.lang.Long pk, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByreceiverId_PrevAndNext(pk, receiverId, obc);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId, start, end, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKReceiverId_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId_First(classNameId,
            classPK, receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKReceiverId_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId_Last(classNameId,
            classPK, receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKReceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId_PrevAndNext(pk,
            classNameId, classPK, receiverId, obc);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId(classNameId,
            classPK, type, receiverId);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId(classNameId,
            classPK, type, receiverId, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId(classNameId,
            classPK, type, receiverId, start, end, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeReceiverId_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId_First(classNameId,
            classPK, type, receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeReceiverId_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId_Last(classNameId,
            classPK, type, receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKTypeReceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId_PrevAndNext(pk,
            classNameId, classPK, type, receiverId, obc);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId, start, end, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId_First(classNameId,
            classPK, type, extraData, receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId_Last(classNameId,
            classPK, type, extraData, receiverId, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKTypeExtraDataReceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId_PrevAndNext(pk,
            classNameId, classPK, type, extraData, receiverId, obc);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData(classNameId, classPK,
            type, extraData);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData(classNameId, classPK,
            type, extraData, start, end);
    }

    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData(classNameId, classPK,
            type, extraData, start, end, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraData_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData_First(classNameId,
            classPK, type, extraData, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraData_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData_Last(classNameId,
            classPK, type, extraData, obc);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKTypeExtraData_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData_PrevAndNext(pk,
            classNameId, classPK, type, extraData, obc);
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

    public static void removeByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByreceiverId(receiverId);
    }

    public static void removeByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException {
        getPersistence()
            .removeByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId);
    }

    public static void removeByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId)
        throws com.liferay.portal.SystemException {
        getPersistence()
            .removeByClassNameIdClassPKTypeReceiverId(classNameId, classPK,
            type, receiverId);
    }

    public static void removeByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException {
        getPersistence()
            .removeByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId);
    }

    public static void removeByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.SystemException {
        getPersistence()
            .removeByClassNameIdClassPKTypeExtraData(classNameId, classPK,
            type, extraData);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByreceiverId(receiverId);
    }

    public static int countByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId);
    }

    public static int countByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByClassNameIdClassPKTypeReceiverId(classNameId,
            classPK, type, receiverId);
    }

    public static int countByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId);
    }

    public static int countByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByClassNameIdClassPKTypeExtraData(classNameId,
            classPK, type, extraData);
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
