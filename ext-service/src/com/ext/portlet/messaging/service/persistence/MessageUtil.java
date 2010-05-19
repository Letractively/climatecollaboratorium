package com.ext.portlet.messaging.service.persistence;

public class MessageUtil {
    private static MessagePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.messaging.model.Message message) {
        getPersistence().cacheResult(message);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.messaging.model.Message> messages) {
        getPersistence().cacheResult(messages);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.messaging.model.Message create(
        java.lang.Long messageId) {
        return getPersistence().create(messageId);
    }

    public static com.ext.portlet.messaging.model.Message remove(
        java.lang.Long messageId)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(messageId);
    }

    public static com.ext.portlet.messaging.model.Message remove(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(message);
    }

    /**
     * @deprecated Use <code>update(Message message, boolean merge)</code>.
     */
    public static com.ext.portlet.messaging.model.Message update(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(message);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                message the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when message is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.messaging.model.Message update(
        com.ext.portlet.messaging.model.Message message, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(message, merge);
    }

    public static com.ext.portlet.messaging.model.Message updateImpl(
        com.ext.portlet.messaging.model.Message message, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(message, merge);
    }

    public static com.ext.portlet.messaging.model.Message findByPrimaryKey(
        java.lang.Long messageId)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(messageId);
    }

    public static com.ext.portlet.messaging.model.Message fetchByPrimaryKey(
        java.lang.Long messageId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(messageId);
    }

    public static java.util.List<com.ext.portlet.messaging.model.Message> findBySendingUser(
        java.lang.Long fromId) throws com.liferay.portal.SystemException {
        return getPersistence().findBySendingUser(fromId);
    }

    public static java.util.List<com.ext.portlet.messaging.model.Message> findBySendingUser(
        java.lang.Long fromId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBySendingUser(fromId, start, end);
    }

    public static java.util.List<com.ext.portlet.messaging.model.Message> findBySendingUser(
        java.lang.Long fromId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBySendingUser(fromId, start, end, obc);
    }

    public static com.ext.portlet.messaging.model.Message findBySendingUser_First(
        java.lang.Long fromId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findBySendingUser_First(fromId, obc);
    }

    public static com.ext.portlet.messaging.model.Message findBySendingUser_Last(
        java.lang.Long fromId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findBySendingUser_Last(fromId, obc);
    }

    public static com.ext.portlet.messaging.model.Message[] findBySendingUser_PrevAndNext(
        java.lang.Long messageId, java.lang.Long fromId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBySendingUser_PrevAndNext(messageId, fromId, obc);
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

    public static java.util.List<com.ext.portlet.messaging.model.Message> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.messaging.model.Message> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.messaging.model.Message> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeBySendingUser(java.lang.Long fromId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeBySendingUser(fromId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countBySendingUser(java.lang.Long fromId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBySendingUser(fromId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static MessagePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(MessagePersistence persistence) {
        _persistence = persistence;
    }
}
