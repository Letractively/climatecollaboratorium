package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanRelatedPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.plans.model.PlanRelated planRelated);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanRelated> planRelateds);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanRelated create(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK);

    public com.ext.portlet.plans.model.PlanRelated remove(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanRelated remove(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanRelated planRelated, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanRelated update(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planRelated the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planRelated is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanRelated update(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanRelated updateImpl(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanRelated findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanRelated fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanRelated findByPlanId_First(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanRelated findByPlanId_Last(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanRelated[] findByPlanId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK,
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
