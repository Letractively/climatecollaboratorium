package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanDescriptionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanDescription planDescription);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanDescription> planDescriptions);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanDescription create(java.lang.Long id);

    public com.ext.portlet.plans.model.PlanDescription remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanDescriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription remove(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanDescription planDescription, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanDescription update(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planDescription the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planDescription is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanDescription update(
        com.ext.portlet.plans.model.PlanDescription planDescription,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription updateImpl(
        com.ext.portlet.plans.model.PlanDescription planDescription,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanDescriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription findByCurrentByPlanId(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanDescriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription fetchByCurrentByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription fetchByCurrentByPlanId(
        java.lang.Long planId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> findByAllByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> findByAllByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> findByAllByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription findByAllByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanDescriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription findByAllByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanDescriptionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription[] findByAllByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanDescriptionException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByCurrentByPlanId(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanDescriptionException,
            com.liferay.portal.SystemException;

    public void removeByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByCurrentByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
