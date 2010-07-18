package com.ext.portlet.models.service.persistence;

public class ModelOutputItemOrderUtil {
    private static ModelOutputItemOrderPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder) {
        getPersistence().cacheResult(modelOutputItemOrder);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelOutputItemOrder> modelOutputItemOrders) {
        getPersistence().cacheResult(modelOutputItemOrders);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelOutputItemOrder create(
        java.lang.Long modelOutputItemModifierPK) {
        return getPersistence().create(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItemOrder remove(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemOrderException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItemOrder remove(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelOutputItemOrder);
    }

    /**
     * @deprecated Use <code>update(ModelOutputItemOrder modelOutputItemOrder, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelOutputItemOrder update(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelOutputItemOrder);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelOutputItemOrder the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelOutputItemOrder is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.models.model.ModelOutputItemOrder update(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(modelOutputItemOrder, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputItemOrder updateImpl(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelOutputItemOrder, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputItemOrder findByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemOrderException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItemOrder fetchByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelOutputItemModifierPK);
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

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItemOrder> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItemOrder> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItemOrder> findAll(
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

    public static ModelOutputItemOrderPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelOutputItemOrderPersistence persistence) {
        _persistence = persistence;
    }
}
