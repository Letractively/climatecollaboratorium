package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanItemPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.plans.model.PlanItem planItem);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanItem> planItems);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanItem create(java.lang.Long id);

    public com.ext.portlet.plans.model.PlanItem remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem remove(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanItem planItem, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanItem update(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanItem update(
        com.ext.portlet.plans.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem updateImpl(
        com.ext.portlet.plans.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findByAllByContestPhase(
        java.lang.Long ContestPhase) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findByAllByContestPhase(
        java.lang.Long ContestPhase, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findByAllByContestPhase(
        java.lang.Long ContestPhase, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem findByAllByContestPhase_First(
        java.lang.Long ContestPhase,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem findByAllByContestPhase_Last(
        java.lang.Long ContestPhase,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem[] findByAllByContestPhase_PrevAndNext(
        java.lang.Long id, java.lang.Long ContestPhase,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findByAllByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findByAllByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findByAllByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem findByAllByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem findByAllByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem[] findByAllByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem findByPlanId(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem fetchByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem fetchByPlanId(
        java.lang.Long planId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByAllByContestPhase(java.lang.Long ContestPhase)
        throws com.liferay.portal.SystemException;

    public void removeByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public void removeByPlanId(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByAllByContestPhase(java.lang.Long ContestPhase)
        throws com.liferay.portal.SystemException;

    public int countByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
