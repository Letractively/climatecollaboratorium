package com.ext.portlet.plans.service.persistence;

public class PlanModelRunUtil {
    private static PlanModelRunPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanModelRun planModelRun) {
        getPersistence().cacheResult(planModelRun);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanModelRun> planModelRuns) {
        getPersistence().cacheResult(planModelRuns);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanModelRun create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.plans.model.PlanModelRun remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.plans.model.PlanModelRun remove(
        com.ext.portlet.plans.model.PlanModelRun planModelRun)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planModelRun);
    }

    /**
     * @deprecated Use <code>update(PlanModelRun planModelRun, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanModelRun update(
        com.ext.portlet.plans.model.PlanModelRun planModelRun)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planModelRun);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planModelRun the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planModelRun is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanModelRun update(
        com.ext.portlet.plans.model.PlanModelRun planModelRun, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planModelRun, merge);
    }

    public static com.ext.portlet.plans.model.PlanModelRun updateImpl(
        com.ext.portlet.plans.model.PlanModelRun planModelRun, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planModelRun, merge);
    }

    public static com.ext.portlet.plans.model.PlanModelRun findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.plans.model.PlanModelRun fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static com.ext.portlet.plans.model.PlanModelRun findByCurrentByPlanId(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.SystemException {
        return getPersistence().findByCurrentByPlanId(planId);
    }

    public static com.ext.portlet.plans.model.PlanModelRun fetchByCurrentByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId);
    }

    public static com.ext.portlet.plans.model.PlanModelRun fetchByCurrentByPlanId(
        java.lang.Long planId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId, retrieveFromCache);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findByAllByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getPersistence().findByAllByPlanId(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findByAllByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByAllByPlanId(planId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findByAllByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByAllByPlanId(planId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanModelRun findByAllByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.SystemException {
        return getPersistence().findByAllByPlanId_First(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanModelRun findByAllByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.SystemException {
        return getPersistence().findByAllByPlanId_Last(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanModelRun[] findByAllByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.SystemException {
        return getPersistence().findByAllByPlanId_PrevAndNext(id, planId, obc);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByCurrentByPlanId(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.SystemException {
        getPersistence().removeByCurrentByPlanId(planId);
    }

    public static void removeByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByAllByPlanId(planId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByCurrentByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByCurrentByPlanId(planId);
    }

    public static int countByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByAllByPlanId(planId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanModelRunPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanModelRunPersistence persistence) {
        _persistence = persistence;
    }
}