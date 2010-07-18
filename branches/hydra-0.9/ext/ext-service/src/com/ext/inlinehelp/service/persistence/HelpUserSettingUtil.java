package com.ext.inlinehelp.service.persistence;

public class HelpUserSettingUtil {
    private static HelpUserSettingPersistence _persistence;

    public static void cacheResult(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting) {
        getPersistence().cacheResult(helpUserSetting);
    }

    public static void cacheResult(
        java.util.List<com.ext.inlinehelp.model.HelpUserSetting> helpUserSettings) {
        getPersistence().cacheResult(helpUserSettings);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.inlinehelp.model.HelpUserSetting create(
        java.lang.Long HelpUserSettingId) {
        return getPersistence().create(HelpUserSettingId);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting remove(
        java.lang.Long HelpUserSettingId)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(HelpUserSettingId);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting remove(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(helpUserSetting);
    }

    /**
     * @deprecated Use <code>update(HelpUserSetting helpUserSetting, boolean merge)</code>.
     */
    public static com.ext.inlinehelp.model.HelpUserSetting update(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(helpUserSetting);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                helpUserSetting the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when helpUserSetting is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.inlinehelp.model.HelpUserSetting update(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(helpUserSetting, merge);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting updateImpl(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(helpUserSetting, merge);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting findByPrimaryKey(
        java.lang.Long HelpUserSettingId)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(HelpUserSettingId);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting fetchByPrimaryKey(
        java.lang.Long HelpUserSettingId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(HelpUserSettingId);
    }

    public static java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findByUserIdMessageId(
        java.lang.Long userId, java.lang.String messageId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserIdMessageId(userId, messageId);
    }

    public static java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findByUserIdMessageId(
        java.lang.Long userId, java.lang.String messageId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdMessageId(userId, messageId, start, end);
    }

    public static java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findByUserIdMessageId(
        java.lang.Long userId, java.lang.String messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdMessageId(userId, messageId, start, end, obc);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting findByUserIdMessageId_First(
        java.lang.Long userId, java.lang.String messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdMessageId_First(userId, messageId, obc);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting findByUserIdMessageId_Last(
        java.lang.Long userId, java.lang.String messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdMessageId_Last(userId, messageId, obc);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting[] findByUserIdMessageId_PrevAndNext(
        java.lang.Long HelpUserSettingId, java.lang.Long userId,
        java.lang.String messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdMessageId_PrevAndNext(HelpUserSettingId,
            userId, messageId, obc);
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

    public static java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByUserIdMessageId(java.lang.Long userId,
        java.lang.String messageId) throws com.liferay.portal.SystemException {
        getPersistence().removeByUserIdMessageId(userId, messageId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByUserIdMessageId(java.lang.Long userId,
        java.lang.String messageId) throws com.liferay.portal.SystemException {
        return getPersistence().countByUserIdMessageId(userId, messageId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static HelpUserSettingPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(HelpUserSettingPersistence persistence) {
        _persistence = persistence;
    }
}
