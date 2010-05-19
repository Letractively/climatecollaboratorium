package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanTypePersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.plans.model.PlanType planType);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanType> planTypes);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanType create(
        java.lang.Long planTypeId);

    public com.ext.portlet.plans.model.PlanType remove(
        java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType remove(
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanType planType, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanType update(
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planType the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planType is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanType update(
        com.ext.portlet.plans.model.PlanType planType, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType updateImpl(
        com.ext.portlet.plans.model.PlanType planType, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType findByPrimaryKey(
        java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType fetchByPrimaryKey(
        java.lang.Long planTypeId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType findBydefault(
        java.lang.Boolean isDefault)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType fetchBydefault(
        java.lang.Boolean isDefault) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType fetchBydefault(
        java.lang.Boolean isDefault, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanType> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanType> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanType> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeBydefault(java.lang.Boolean isDefault)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countBydefault(java.lang.Boolean isDefault)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        java.lang.Long pk) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public int getPlanTypeAttributesSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanTypeAttribute(java.lang.Long pk,
        java.lang.Long planTypeAttributePK)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanTypeAttributes(java.lang.Long pk)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        java.lang.Long pk) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public int getPlanTypeColumnsSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanTypeColumn(java.lang.Long pk,
        java.lang.Long planTypeColumnPK)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanTypeColumns(java.lang.Long pk)
        throws com.liferay.portal.SystemException;
}
