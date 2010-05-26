package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelOutputChartOrderPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> modelOutputChartOrders);

    public void clearCache();

    public com.ext.portlet.models.model.ModelOutputChartOrder create(
        java.lang.Long modelOutputChartOrderPK);

    public com.ext.portlet.models.model.ModelOutputChartOrder remove(
        java.lang.Long modelOutputChartOrderPK)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputChartOrder remove(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelOutputChartOrder modelOutputChartOrder, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelOutputChartOrder update(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.models.model.ModelOutputChartOrder update(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputChartOrder updateImpl(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputChartOrder findByPrimaryKey(
        java.lang.Long modelOutputChartOrderPK)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputChartOrder fetchByPrimaryKey(
        java.lang.Long modelOutputChartOrderPK)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputChartOrder findByModelIdAndLabel(
        java.lang.Long modelId, java.lang.String modelOutputLabel)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputChartOrder fetchByModelIdAndLabel(
        java.lang.Long modelId, java.lang.String modelOutputLabel)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputChartOrder fetchByModelIdAndLabel(
        java.lang.Long modelId, java.lang.String modelOutputLabel,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByModelIdAndLabel(java.lang.Long modelId,
        java.lang.String modelOutputLabel)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByModelIdAndLabel(java.lang.Long modelId,
        java.lang.String modelOutputLabel)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
