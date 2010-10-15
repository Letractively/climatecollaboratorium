package com.ext.portlet.plans.service.persistence;

public class PlanFanUtil {
    private static PlanFanPersistence _persistence;

    public static void cacheResult(com.ext.portlet.plans.model.PlanFan planFan) {
        getPersistence().cacheResult(planFan);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanFan> planFans) {
        getPersistence().cacheResult(planFans);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanFan create(java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.plans.model.PlanFan remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.plans.model.PlanFan remove(
        com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planFan);
    }

    /**
     * @deprecated Use <code>update(PlanFan planFan, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanFan update(
        com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planFan);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planFan the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planFan is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanFan update(
        com.ext.portlet.plans.model.PlanFan planFan, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planFan, merge);
    }

    public static com.ext.portlet.plans.model.PlanFan updateImpl(
        com.ext.portlet.plans.model.PlanFan planFan, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planFan, merge);
    }

    public static com.ext.portlet.plans.model.PlanFan findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.plans.model.PlanFan fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> findByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> findByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> findByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanFan findByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_First(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanFan findByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_Last(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanFan[] findByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_PrevAndNext(id, planId, obc);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> findByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> findByUserId(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanFan findByUserId_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_First(userId, obc);
    }

    public static com.ext.portlet.plans.model.PlanFan findByUserId_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_Last(userId, obc);
    }

    public static com.ext.portlet.plans.model.PlanFan[] findByUserId_PrevAndNext(
        java.lang.Long id, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_PrevAndNext(id, userId, obc);
    }

    public static com.ext.portlet.plans.model.PlanFan findByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanIdUserId(planId, userId);
    }

    public static com.ext.portlet.plans.model.PlanFan fetchByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPlanIdUserId(planId, userId);
    }

    public static com.ext.portlet.plans.model.PlanFan fetchByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanIdUserId(planId, userId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByPlanId(planId);
    }

    public static void removeByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByUserId(userId);
    }

    public static void removeByPlanIdUserId(java.lang.Long planId,
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        getPersistence().removeByPlanIdUserId(planId, userId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByPlanId(planId);
    }

    public static int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByUserId(userId);
    }

    public static int countByPlanIdUserId(java.lang.Long planId,
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().countByPlanIdUserId(planId, userId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanFanPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanFanPersistence persistence) {
        _persistence = persistence;
    }
}
