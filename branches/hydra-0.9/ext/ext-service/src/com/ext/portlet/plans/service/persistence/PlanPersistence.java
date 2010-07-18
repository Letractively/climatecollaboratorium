package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.plans.model.Plan plan);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.Plan> plans);

    public void clearCache();

    public com.ext.portlet.plans.model.Plan create(java.lang.Long planId);

    public com.ext.portlet.plans.model.Plan remove(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan remove(
        com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(Plan plan, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.Plan update(
        com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plan the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plan is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.Plan update(
        com.ext.portlet.plans.model.Plan plan, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan updateImpl(
        com.ext.portlet.plans.model.Plan plan, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan findByPrimaryKey(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan fetchByPrimaryKey(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.Plan> findByname(
        java.lang.String name) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.Plan> findByname(
        java.lang.String name, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.Plan> findByname(
        java.lang.String name, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan findByname_First(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan findByname_Last(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan[] findByname_PrevAndNext(
        java.lang.Long planId, java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.Plan> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.Plan> findAll(int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.Plan> findAll(int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByname(java.lang.String name)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByname(java.lang.String name)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        java.lang.Long pk) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public int getPlanAttributesSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanAttribute(java.lang.Long pk,
        java.lang.Long planAttributePK)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanAttributes(java.lang.Long pk)
        throws com.liferay.portal.SystemException;
}
