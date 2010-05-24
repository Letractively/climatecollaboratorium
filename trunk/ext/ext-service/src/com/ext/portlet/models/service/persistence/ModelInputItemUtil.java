package com.ext.portlet.models.service.persistence;

public class ModelInputItemUtil {
    private static ModelInputItemPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelInputItem modelInputItem) {
        getPersistence().cacheResult(modelInputItem);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelInputItem> modelInputItems) {
        getPersistence().cacheResult(modelInputItems);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelInputItem create(
        java.lang.Long modelInputItemPK) {
        return getPersistence().create(modelInputItemPK);
    }

    public static com.ext.portlet.models.model.ModelInputItem remove(
        java.lang.Long modelInputItemPK)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelInputItemPK);
    }

    public static com.ext.portlet.models.model.ModelInputItem remove(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelInputItem);
    }

    /**
     * @deprecated Use <code>update(ModelInputItem modelInputItem, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelInputItem update(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelInputItem);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelInputItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelInputItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.models.model.ModelInputItem update(
        com.ext.portlet.models.model.ModelInputItem modelInputItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(modelInputItem, merge);
    }

    public static com.ext.portlet.models.model.ModelInputItem updateImpl(
        com.ext.portlet.models.model.ModelInputItem modelInputItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelInputItem, merge);
    }

    public static com.ext.portlet.models.model.ModelInputItem findByPrimaryKey(
        java.lang.Long modelInputItemPK)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelInputItemPK);
    }

    public static com.ext.portlet.models.model.ModelInputItem fetchByPrimaryKey(
        java.lang.Long modelInputItemPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelInputItemPK);
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

    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findAll(
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

    public static ModelInputItemPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelInputItemPersistence persistence) {
        _persistence = persistence;
    }
}
