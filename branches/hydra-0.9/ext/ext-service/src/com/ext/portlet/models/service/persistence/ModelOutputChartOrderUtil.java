package com.ext.portlet.models.service.persistence;

public class ModelOutputChartOrderUtil {
    private static ModelOutputChartOrderPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder) {
        getPersistence().cacheResult(modelOutputChartOrder);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> modelOutputChartOrders) {
        getPersistence().cacheResult(modelOutputChartOrders);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder create(
        java.lang.Long modelOutputChartOrderPK) {
        return getPersistence().create(modelOutputChartOrderPK);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder remove(
        java.lang.Long modelOutputChartOrderPK)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelOutputChartOrderPK);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder remove(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelOutputChartOrder);
    }

    /**
     * @deprecated Use <code>update(ModelOutputChartOrder modelOutputChartOrder, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelOutputChartOrder update(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelOutputChartOrder);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelOutputChartOrder the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelOutputChartOrder is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.models.model.ModelOutputChartOrder update(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(modelOutputChartOrder, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder updateImpl(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelOutputChartOrder, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder findByPrimaryKey(
        java.lang.Long modelOutputChartOrderPK)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelOutputChartOrderPK);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder fetchByPrimaryKey(
        java.lang.Long modelOutputChartOrderPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelOutputChartOrderPK);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder findByModelIdAndLabel(
        java.lang.Long modelId, java.lang.String modelOutputLabel)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelIdAndLabel(modelId, modelOutputLabel);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder fetchByModelIdAndLabel(
        java.lang.Long modelId, java.lang.String modelOutputLabel)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByModelIdAndLabel(modelId, modelOutputLabel);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder fetchByModelIdAndLabel(
        java.lang.Long modelId, java.lang.String modelOutputLabel,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByModelIdAndLabel(modelId, modelOutputLabel,
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

    public static java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByModelIdAndLabel(java.lang.Long modelId,
        java.lang.String modelOutputLabel)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.SystemException {
        getPersistence().removeByModelIdAndLabel(modelId, modelOutputLabel);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByModelIdAndLabel(java.lang.Long modelId,
        java.lang.String modelOutputLabel)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByModelIdAndLabel(modelId, modelOutputLabel);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ModelOutputChartOrderPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelOutputChartOrderPersistence persistence) {
        _persistence = persistence;
    }
}
