package com.ext.conditionaltext.service.persistence;

public class ConditionalTextSettingUtil {
    private static ConditionalTextSettingPersistence _persistence;

    public static void cacheResult(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting) {
        getPersistence().cacheResult(conditionalTextSetting);
    }

    public static void cacheResult(
        java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> conditionalTextSettings) {
        getPersistence().cacheResult(conditionalTextSettings);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting create(
        java.lang.Long ConditionalTextSettingId) {
        return getPersistence().create(ConditionalTextSettingId);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting remove(
        java.lang.Long ConditionalTextSettingId)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(ConditionalTextSettingId);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting remove(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(conditionalTextSetting);
    }

    /**
     * @deprecated Use <code>update(ConditionalTextSetting conditionalTextSetting, boolean merge)</code>.
     */
    public static com.ext.conditionaltext.model.ConditionalTextSetting update(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(conditionalTextSetting);
    }

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
    public static com.ext.conditionaltext.model.ConditionalTextSetting update(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(conditionalTextSetting, merge);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting updateImpl(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(conditionalTextSetting, merge);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting findByPrimaryKey(
        java.lang.Long ConditionalTextSettingId)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(ConditionalTextSettingId);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting fetchByPrimaryKey(
        java.lang.Long ConditionalTextSettingId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(ConditionalTextSettingId);
    }

    public static java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findByparamKey(
        java.lang.String paramKey) throws com.liferay.portal.SystemException {
        return getPersistence().findByparamKey(paramKey);
    }

    public static java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findByparamKey(
        java.lang.String paramKey, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByparamKey(paramKey, start, end);
    }

    public static java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findByparamKey(
        java.lang.String paramKey, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByparamKey(paramKey, start, end, obc);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting findByparamKey_First(
        java.lang.String paramKey,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByparamKey_First(paramKey, obc);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting findByparamKey_Last(
        java.lang.String paramKey,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByparamKey_Last(paramKey, obc);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting[] findByparamKey_PrevAndNext(
        java.lang.Long ConditionalTextSettingId, java.lang.String paramKey,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByparamKey_PrevAndNext(ConditionalTextSettingId,
            paramKey, obc);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting findByparamKeyParamValue(
        java.lang.String paramKey, java.lang.String paramValue)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByparamKeyParamValue(paramKey, paramValue);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting fetchByparamKeyParamValue(
        java.lang.String paramKey, java.lang.String paramValue)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByparamKeyParamValue(paramKey, paramValue);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting fetchByparamKeyParamValue(
        java.lang.String paramKey, java.lang.String paramValue,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByparamKeyParamValue(paramKey, paramValue,
            retrieveFromCache);
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

    public static java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByparamKey(java.lang.String paramKey)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByparamKey(paramKey);
    }

    public static void removeByparamKeyParamValue(java.lang.String paramKey,
        java.lang.String paramValue)
        throws com.ext.conditionaltext.NoSuchConditionalTextSettingException,
            com.liferay.portal.SystemException {
        getPersistence().removeByparamKeyParamValue(paramKey, paramValue);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByparamKey(java.lang.String paramKey)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByparamKey(paramKey);
    }

    public static int countByparamKeyParamValue(java.lang.String paramKey,
        java.lang.String paramValue) throws com.liferay.portal.SystemException {
        return getPersistence().countByparamKeyParamValue(paramKey, paramValue);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ConditionalTextSettingPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ConditionalTextSettingPersistence persistence) {
        _persistence = persistence;
    }
}
