package com.ext.portlet.messaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface MessagingUserPreferencesPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences);

    public void cacheResult(
        java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> messagingUserPreferenceses);

    public void clearCache();

    public com.ext.portlet.messaging.model.MessagingUserPreferences create(
        java.lang.Long messagingPreferencesId);

    public com.ext.portlet.messaging.model.MessagingUserPreferences remove(
        java.lang.Long messagingPreferencesId)
        throws com.ext.portlet.messaging.NoSuchUserPreferencesException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessagingUserPreferences remove(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(MessagingUserPreferences messagingUserPreferences, boolean merge)</code>.
     */
    public com.ext.portlet.messaging.model.MessagingUserPreferences update(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingUserPreferences the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingUserPreferences is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.messaging.model.MessagingUserPreferences update(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessagingUserPreferences updateImpl(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessagingUserPreferences findByPrimaryKey(
        java.lang.Long messagingPreferencesId)
        throws com.ext.portlet.messaging.NoSuchUserPreferencesException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessagingUserPreferences fetchByPrimaryKey(
        java.lang.Long messagingPreferencesId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessagingUserPreferences findBymessagingPreferences(
        java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchUserPreferencesException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessagingUserPreferences fetchBymessagingPreferences(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessagingUserPreferences fetchBymessagingPreferences(
        java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeBymessagingPreferences(java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchUserPreferencesException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countBymessagingPreferences(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
