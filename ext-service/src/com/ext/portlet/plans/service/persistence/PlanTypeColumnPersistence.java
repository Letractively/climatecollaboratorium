package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanTypeColumnPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> planTypeColumns);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanTypeColumn create(
        java.lang.Long planTypeColumnId);

    public com.ext.portlet.plans.model.PlanTypeColumn remove(
        java.lang.Long planTypeColumnId)
        throws com.ext.portlet.plans.NoSuchPlanTypeColumnException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeColumn remove(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanTypeColumn planTypeColumn, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanTypeColumn update(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTypeColumn the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTypeColumn is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanTypeColumn update(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeColumn updateImpl(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeColumn findByPrimaryKey(
        java.lang.Long planTypeColumnId)
        throws com.ext.portlet.plans.NoSuchPlanTypeColumnException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeColumn fetchByPrimaryKey(
        java.lang.Long planTypeColumnId)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
