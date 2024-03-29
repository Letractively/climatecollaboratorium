package com.ext.inlinehelp.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface HelpUserSettingPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting);

    public void cacheResult(
        java.util.List<com.ext.inlinehelp.model.HelpUserSetting> helpUserSettings);

    public void clearCache();

    public com.ext.inlinehelp.model.HelpUserSetting create(
        java.lang.Long HelpUserSettingId);

    public com.ext.inlinehelp.model.HelpUserSetting remove(
        java.lang.Long HelpUserSettingId)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting remove(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(HelpUserSetting helpUserSetting, boolean merge)</code>.
     */
    public com.ext.inlinehelp.model.HelpUserSetting update(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException;

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
    public com.ext.inlinehelp.model.HelpUserSetting update(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting updateImpl(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting findByPrimaryKey(
        java.lang.Long HelpUserSettingId)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting fetchByPrimaryKey(
        java.lang.Long HelpUserSettingId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findByUserIdMessageId(
        java.lang.Long userId, java.lang.String messageId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findByUserIdMessageId(
        java.lang.Long userId, java.lang.String messageId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findByUserIdMessageId(
        java.lang.Long userId, java.lang.String messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting findByUserIdMessageId_First(
        java.lang.Long userId, java.lang.String messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting findByUserIdMessageId_Last(
        java.lang.Long userId, java.lang.String messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting[] findByUserIdMessageId_PrevAndNext(
        java.lang.Long HelpUserSettingId, java.lang.Long userId,
        java.lang.String messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.inlinehelp.NoSuchHelpUserSettingException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.inlinehelp.model.HelpUserSetting> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByUserIdMessageId(java.lang.Long userId,
        java.lang.String messageId) throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByUserIdMessageId(java.lang.Long userId,
        java.lang.String messageId) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
