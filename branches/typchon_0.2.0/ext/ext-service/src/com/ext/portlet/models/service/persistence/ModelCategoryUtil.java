package com.ext.portlet.models.service.persistence;

public class ModelCategoryUtil {
    private static ModelCategoryPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelCategory modelCategory) {
        getPersistence().cacheResult(modelCategory);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelCategory> modelCategories) {
        getPersistence().cacheResult(modelCategories);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelCategory create(
        java.lang.Long modelCategoryPK) {
        return getPersistence().create(modelCategoryPK);
    }

    public static com.ext.portlet.models.model.ModelCategory remove(
        java.lang.Long modelCategoryPK)
        throws com.ext.portlet.models.NoSuchModelCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelCategoryPK);
    }

    public static com.ext.portlet.models.model.ModelCategory remove(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelCategory);
    }

    /**
     * @deprecated Use <code>update(ModelCategory modelCategory, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelCategory update(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelCategory);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.models.model.ModelCategory update(
        com.ext.portlet.models.model.ModelCategory modelCategory, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelCategory, merge);
    }

    public static com.ext.portlet.models.model.ModelCategory updateImpl(
        com.ext.portlet.models.model.ModelCategory modelCategory, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelCategory, merge);
    }

    public static com.ext.portlet.models.model.ModelCategory findByPrimaryKey(
        java.lang.Long modelCategoryPK)
        throws com.ext.portlet.models.NoSuchModelCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelCategoryPK);
    }

    public static com.ext.portlet.models.model.ModelCategory fetchByPrimaryKey(
        java.lang.Long modelCategoryPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelCategoryPK);
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

    public static java.util.List<com.ext.portlet.models.model.ModelCategory> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelCategory> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelCategory> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ModelCategoryPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelCategoryPersistence persistence) {
        _persistence = persistence;
    }
}
