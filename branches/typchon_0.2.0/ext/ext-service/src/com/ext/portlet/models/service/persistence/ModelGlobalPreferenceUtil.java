package com.ext.portlet.models.service.persistence;

public class ModelGlobalPreferenceUtil {
    private static ModelGlobalPreferencePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference) {
        getPersistence().cacheResult(modelGlobalPreference);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> modelGlobalPreferences) {
        getPersistence().cacheResult(modelGlobalPreferences);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference create(
        java.lang.Long modelGlobalPreferencePK) {
        return getPersistence().create(modelGlobalPreferencePK);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference remove(
        java.lang.Long modelGlobalPreferencePK)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelGlobalPreferencePK);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference remove(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelGlobalPreference);
    }

    /**
     * @deprecated Use <code>update(ModelGlobalPreference modelGlobalPreference, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelGlobalPreference update(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelGlobalPreference);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelGlobalPreference the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelGlobalPreference is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.models.model.ModelGlobalPreference update(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(modelGlobalPreference, merge);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference updateImpl(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelGlobalPreference, merge);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference findByPrimaryKey(
        java.lang.Long modelGlobalPreferencePK)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelGlobalPreferencePK);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference fetchByPrimaryKey(
        java.lang.Long modelGlobalPreferencePK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelGlobalPreferencePK);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference findByModelId(
        java.lang.Long modelId)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference fetchByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByModelId(modelId);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference fetchByModelId(
        java.lang.Long modelId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByModelId(modelId, retrieveFromCache);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findByModelCategoryId(
        java.lang.Long modelCategoryId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByModelCategoryId(modelCategoryId);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findByModelCategoryId(
        java.lang.Long modelCategoryId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByModelCategoryId(modelCategoryId, start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findByModelCategoryId(
        java.lang.Long modelCategoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByModelCategoryId(modelCategoryId, start, end, obc);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference findByModelCategoryId_First(
        java.lang.Long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelCategoryId_First(modelCategoryId, obc);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference findByModelCategoryId_Last(
        java.lang.Long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelCategoryId_Last(modelCategoryId, obc);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference[] findByModelCategoryId_PrevAndNext(
        java.lang.Long modelGlobalPreferencePK, java.lang.Long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByModelCategoryId_PrevAndNext(modelGlobalPreferencePK,
            modelCategoryId, obc);
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

    public static java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByModelId(java.lang.Long modelId)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException {
        getPersistence().removeByModelId(modelId);
    }

    public static void removeByModelCategoryId(java.lang.Long modelCategoryId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByModelCategoryId(modelCategoryId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByModelId(modelId);
    }

    public static int countByModelCategoryId(java.lang.Long modelCategoryId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByModelCategoryId(modelCategoryId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ModelGlobalPreferencePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelGlobalPreferencePersistence persistence) {
        _persistence = persistence;
    }
}
