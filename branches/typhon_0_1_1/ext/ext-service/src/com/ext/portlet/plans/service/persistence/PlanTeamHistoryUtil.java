package com.ext.portlet.plans.service.persistence;

public class PlanTeamHistoryUtil {
    private static PlanTeamHistoryPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory) {
        getPersistence().cacheResult(planTeamHistory);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> planTeamHistories) {
        getPersistence().cacheResult(planTeamHistories);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory remove(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planTeamHistory);
    }

    /**
     * @deprecated Use <code>update(PlanTeamHistory planTeamHistory, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanTeamHistory update(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planTeamHistory);
    }

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
    public static com.ext.portlet.plans.model.PlanTeamHistory update(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(planTeamHistory, merge);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory updateImpl(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planTeamHistory, merge);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory findByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_First(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory findByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_Last(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory[] findByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_PrevAndNext(id, planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory findByLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByLastUserActionInPlan(planId, userId);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory fetchByLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByLastUserActionInPlan(planId, userId);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory fetchByLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByLastUserActionInPlan(planId, userId,
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

    public static java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByPlanId(planId);
    }

    public static void removeByLastUserActionInPlan(java.lang.Long planId,
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException {
        getPersistence().removeByLastUserActionInPlan(planId, userId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByPlanId(planId);
    }

    public static int countByLastUserActionInPlan(java.lang.Long planId,
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().countByLastUserActionInPlan(planId, userId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanTeamHistoryPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanTeamHistoryPersistence persistence) {
        _persistence = persistence;
    }
}
