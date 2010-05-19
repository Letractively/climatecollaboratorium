package com.ext.portlet.messaging.service.persistence;

public class MessageRecipientStatusUtil {
    private static MessageRecipientStatusPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus) {
        getPersistence().cacheResult(messageRecipientStatus);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> messageRecipientStatuses) {
        getPersistence().cacheResult(messageRecipientStatuses);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus create(
        java.lang.Long messageRecipientId) {
        return getPersistence().create(messageRecipientId);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus remove(
        java.lang.Long messageRecipientId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(messageRecipientId);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus remove(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(messageRecipientStatus);
    }

    /**
     * @deprecated Use <code>update(MessageRecipientStatus messageRecipientStatus, boolean merge)</code>.
     */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus update(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(messageRecipientStatus);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messageRecipientStatus the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messageRecipientStatus is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus update(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(messageRecipientStatus, merge);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus updateImpl(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(messageRecipientStatus, merge);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByPrimaryKey(
        java.lang.Long messageRecipientId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(messageRecipientId);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus fetchByPrimaryKey(
        java.lang.Long messageRecipientId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(messageRecipientId);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().findByReceivingUser(userId);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByReceivingUser(userId, start, end);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByReceivingUser(userId, start, end, obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUser_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence().findByReceivingUser_First(userId, obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUser_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence().findByReceivingUser_Last(userId, obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus[] findByReceivingUser_PrevAndNext(
        java.lang.Long messageRecipientId, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByReceivingUser_PrevAndNext(messageRecipientId, userId,
            obc);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        java.lang.Long userId, java.lang.Boolean archived)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByReceivingUserArchived(userId, archived);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        java.lang.Long userId, java.lang.Boolean archived, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived(userId, archived, start, end);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        java.lang.Long userId, java.lang.Boolean archived, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived(userId, archived, start, end,
            obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUserArchived_First(
        java.lang.Long userId, java.lang.Boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived_First(userId, archived, obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUserArchived_Last(
        java.lang.Long userId, java.lang.Boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived_Last(userId, archived, obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus[] findByReceivingUserArchived_PrevAndNext(
        java.lang.Long messageRecipientId, java.lang.Long userId,
        java.lang.Boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived_PrevAndNext(messageRecipientId,
            userId, archived, obc);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        java.lang.Long messageId) throws com.liferay.portal.SystemException {
        return getPersistence().findByMessageId(messageId);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        java.lang.Long messageId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByMessageId(messageId, start, end);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        java.lang.Long messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByMessageId(messageId, start, end, obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageId_First(
        java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence().findByMessageId_First(messageId, obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageId_Last(
        java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence().findByMessageId_Last(messageId, obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus[] findByMessageId_PrevAndNext(
        java.lang.Long messageRecipientId, java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByMessageId_PrevAndNext(messageRecipientId, messageId,
            obc);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageReciever(
        java.lang.Long messageId, java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getPersistence().findByMessageReciever(messageId, userId);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus fetchByMessageReciever(
        java.lang.Long messageId, java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByMessageReciever(messageId, userId);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus fetchByMessageReciever(
        java.lang.Long messageId, java.lang.Long userId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByMessageReciever(messageId, userId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByReceivingUser(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByReceivingUser(userId);
    }

    public static void removeByReceivingUserArchived(java.lang.Long userId,
        java.lang.Boolean archived) throws com.liferay.portal.SystemException {
        getPersistence().removeByReceivingUserArchived(userId, archived);
    }

    public static void removeByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByMessageId(messageId);
    }

    public static void removeByMessageReciever(java.lang.Long messageId,
        java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        getPersistence().removeByMessageReciever(messageId, userId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByReceivingUser(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByReceivingUser(userId);
    }

    public static int countByReceivingUserArchived(java.lang.Long userId,
        java.lang.Boolean archived) throws com.liferay.portal.SystemException {
        return getPersistence().countByReceivingUserArchived(userId, archived);
    }

    public static int countByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByMessageId(messageId);
    }

    public static int countByMessageReciever(java.lang.Long messageId,
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().countByMessageReciever(messageId, userId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static MessageRecipientStatusPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(MessageRecipientStatusPersistence persistence) {
        _persistence = persistence;
    }
}
