package com.ext.portlet.messaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface MessagePersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.messaging.model.Message message);

    public void cacheResult(
        java.util.List<com.ext.portlet.messaging.model.Message> messages);

    public void clearCache();

    public com.ext.portlet.messaging.model.Message create(
        java.lang.Long messageId);

    public com.ext.portlet.messaging.model.Message remove(
        java.lang.Long messageId)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message remove(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(Message message, boolean merge)</code>.
     */
    public com.ext.portlet.messaging.model.Message update(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.messaging.model.Message update(
        com.ext.portlet.messaging.model.Message message, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message updateImpl(
        com.ext.portlet.messaging.model.Message message, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message findByPrimaryKey(
        java.lang.Long messageId)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message fetchByPrimaryKey(
        java.lang.Long messageId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.Message> findBySendingUser(
        java.lang.Long fromId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.Message> findBySendingUser(
        java.lang.Long fromId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.Message> findBySendingUser(
        java.lang.Long fromId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message findBySendingUser_First(
        java.lang.Long fromId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message findBySendingUser_Last(
        java.lang.Long fromId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message[] findBySendingUser_PrevAndNext(
        java.lang.Long messageId, java.lang.Long fromId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.Message> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.Message> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.Message> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeBySendingUser(java.lang.Long fromId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countBySendingUser(java.lang.Long fromId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
