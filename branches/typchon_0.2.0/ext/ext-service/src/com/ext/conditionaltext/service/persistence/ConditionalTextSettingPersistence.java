package com.ext.conditionaltext.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ConditionalTextSettingPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting);

    public void cacheResult(
        java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> conditionalTextSettings);

    public void clearCache();

    public com.ext.conditionaltext.model.ConditionalTextSetting create(
        java.lang.Long ConditionalTextSettingId);

    public com.ext.conditionaltext.model.ConditionalTextSetting remove(
        java.lang.Long ConditionalTextSettingId)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting remove(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ConditionalTextSetting conditionalTextSetting, boolean merge)</code>.
     */
    public com.ext.conditionaltext.model.ConditionalTextSetting update(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                conditionalTextSetting the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when conditionalTextSetting is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.conditionaltext.model.ConditionalTextSetting update(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting updateImpl(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting findByPrimaryKey(
        java.lang.Long ConditionalTextSettingId)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting fetchByPrimaryKey(
        java.lang.Long ConditionalTextSettingId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findByparamKey(
        java.lang.String paramKey) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findByparamKey(
        java.lang.String paramKey, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findByparamKey(
        java.lang.String paramKey, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting findByparamKey_First(
        java.lang.String paramKey,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting findByparamKey_Last(
        java.lang.String paramKey,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting[] findByparamKey_PrevAndNext(
        java.lang.Long ConditionalTextSettingId, java.lang.String paramKey,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting findByparamKeyParamValue(
        java.lang.String paramKey, java.lang.String paramValue)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting fetchByparamKeyParamValue(
        java.lang.String paramKey, java.lang.String paramValue)
        throws com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting fetchByparamKeyParamValue(
        java.lang.String paramKey, java.lang.String paramValue,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByparamKey(java.lang.String paramKey)
        throws com.liferay.portal.SystemException;

    public void removeByparamKeyParamValue(java.lang.String paramKey,
        java.lang.String paramValue)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByparamKey(java.lang.String paramKey)
        throws com.liferay.portal.SystemException;

    public int countByparamKeyParamValue(java.lang.String paramKey,
        java.lang.String paramValue) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
