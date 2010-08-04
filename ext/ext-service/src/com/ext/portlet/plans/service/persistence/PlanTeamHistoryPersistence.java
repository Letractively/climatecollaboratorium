package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanTeamHistoryPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> planTeamHistories);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanTeamHistory create(java.lang.Long id);

    public com.ext.portlet.plans.model.PlanTeamHistory remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory remove(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanTeamHistory planTeamHistory, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanTeamHistory update(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTeamHistory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTeamHistory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanTeamHistory update(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory updateImpl(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory findByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory findByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory[] findByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory findByLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory fetchByLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory fetchByLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public void removeByLastUserActionInPlan(java.lang.Long planId,
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countByLastUserActionInPlan(java.lang.Long planId,
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
