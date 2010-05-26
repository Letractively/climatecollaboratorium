package com.ext.portlet.models.service.persistence;

public class ModelOutputItemUtil {
    private static ModelOutputItemPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem) {
        getPersistence().cacheResult(modelOutputItem);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelOutputItem> modelOutputItems) {
        getPersistence().cacheResult(modelOutputItems);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelOutputItem create(
        java.lang.Long modelOutputItemModifierPK) {
        return getPersistence().create(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItem remove(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItem remove(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelOutputItem);
    }

    /**
     * @deprecated Use <code>update(ModelOutputItem modelOutputItem, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelOutputItem update(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelOutputItem);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelOutputItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelOutputItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.models.model.ModelOutputItem update(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(modelOutputItem, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputItem updateImpl(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelOutputItem, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputItem findByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItem fetchByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItem findByModelOutputId(
        java.lang.Long modelOutputItemId)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelOutputId(modelOutputItemId);
    }

    public static com.ext.portlet.models.model.ModelOutputItem fetchByModelOutputId(
        java.lang.Long modelOutputItemId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByModelOutputId(modelOutputItemId);
    }

    public static com.ext.portlet.models.model.ModelOutputItem fetchByModelOutputId(
        java.lang.Long modelOutputItemId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByModelOutputId(modelOutputItemId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItem> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByModelOutputId(java.lang.Long modelOutputItemId)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.SystemException {
        getPersistence().removeByModelOutputId(modelOutputItemId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByModelOutputId(java.lang.Long modelOutputItemId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByModelOutputId(modelOutputItemId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ModelOutputItemPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelOutputItemPersistence persistence) {
        _persistence = persistence;
    }
}
