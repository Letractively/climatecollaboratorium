package com.ext.portlet.messaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface MessageRecipientStatusPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus);

    public void cacheResult(
        java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> messageRecipientStatuses);

    public void clearCache();

    public com.ext.portlet.messaging.model.MessageRecipientStatus create(
        java.lang.Long messageRecipientId);

    public com.ext.portlet.messaging.model.MessageRecipientStatus remove(
        java.lang.Long messageRecipientId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus remove(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(MessageRecipientStatus messageRecipientStatus, boolean merge)</code>.
     */
    public com.ext.portlet.messaging.model.MessageRecipientStatus update(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus update(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus updateImpl(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus findByPrimaryKey(
        java.lang.Long messageRecipientId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus fetchByPrimaryKey(
        java.lang.Long messageRecipientId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUser_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUser_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus[] findByReceivingUser_PrevAndNext(
        java.lang.Long messageRecipientId, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        java.lang.Long userId, java.lang.Boolean archived)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        java.lang.Long userId, java.lang.Boolean archived, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        java.lang.Long userId, java.lang.Boolean archived, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUserArchived_First(
        java.lang.Long userId, java.lang.Boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUserArchived_Last(
        java.lang.Long userId, java.lang.Boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus[] findByReceivingUserArchived_PrevAndNext(
        java.lang.Long messageRecipientId, java.lang.Long userId,
        java.lang.Boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        java.lang.Long messageId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        java.lang.Long messageId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        java.lang.Long messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageId_First(
        java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageId_Last(
        java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus[] findByMessageId_PrevAndNext(
        java.lang.Long messageRecipientId, java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageReciever(
        java.lang.Long messageId, java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus fetchByMessageReciever(
        java.lang.Long messageId, java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus fetchByMessageReciever(
        java.lang.Long messageId, java.lang.Long userId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByReceivingUser(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public void removeByReceivingUserArchived(java.lang.Long userId,
        java.lang.Boolean archived) throws com.liferay.portal.SystemException;

    public void removeByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.SystemException;

    public void removeByMessageReciever(java.lang.Long messageId,
        java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByReceivingUser(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public int countByReceivingUserArchived(java.lang.Long userId,
        java.lang.Boolean archived) throws com.liferay.portal.SystemException;

    public int countByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.SystemException;

    public int countByMessageReciever(java.lang.Long messageId,
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
