package com.ext.portlet.messaging.service.persistence;

public class MessagingUserPreferencesUtil {
    private static MessagingUserPreferencesPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences) {
        getPersistence().cacheResult(messagingUserPreferences);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> messagingUserPreferenceses) {
        getPersistence().cacheResult(messagingUserPreferenceses);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences create(
        java.lang.Long messagingPreferencesId) {
        return getPersistence().create(messagingPreferencesId);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences remove(
        java.lang.Long messagingPreferencesId)
        throws com.ext.portlet.messaging.NoSuchUserPreferencesException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(messagingPreferencesId);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences remove(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(messagingUserPreferences);
    }

    /**
     * @deprecated Use <code>update(MessagingUserPreferences messagingUserPreferences, boolean merge)</code>.
     */
    public static com.ext.portlet.messaging.model.MessagingUserPreferences update(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingUserPreferences);
    }

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
    public static com.ext.portlet.messaging.model.MessagingUserPreferences update(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingUserPreferences, merge);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences updateImpl(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(messagingUserPreferences, merge);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences findByPrimaryKey(
        java.lang.Long messagingPreferencesId)
        throws com.ext.portlet.messaging.NoSuchUserPreferencesException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(messagingPreferencesId);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences fetchByPrimaryKey(
        java.lang.Long messagingPreferencesId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(messagingPreferencesId);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences findBymessagingPreferences(
        java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchUserPreferencesException,
            com.liferay.portal.SystemException {
        return getPersistence().findBymessagingPreferences(userId);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences fetchBymessagingPreferences(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchBymessagingPreferences(userId);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences fetchBymessagingPreferences(
        java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchBymessagingPreferences(userId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeBymessagingPreferences(java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchUserPreferencesException,
            com.liferay.portal.SystemException {
        getPersistence().removeBymessagingPreferences(userId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countBymessagingPreferences(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBymessagingPreferences(userId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingUserPreferencesPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(MessagingUserPreferencesPersistence persistence) {
        _persistence = persistence;
    }
}
