package com.ext.portlet.plans.service.persistence;

public class PlansFilterPositionUtil {
    private static PlansFilterPositionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition) {
        getPersistence().cacheResult(plansFilterPosition);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> plansFilterPositions) {
        getPersistence().cacheResult(plansFilterPositions);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition create(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK) {
        return getPersistence().create(plansFilterPositionPK);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition remove(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.ext.portlet.plans.NoSuchFilterPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(plansFilterPositionPK);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition remove(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(plansFilterPosition);
    }

    /**
     * @deprecated Use <code>update(PlansFilterPosition plansFilterPosition, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlansFilterPosition update(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(plansFilterPosition);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plansFilterPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plansFilterPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlansFilterPosition update(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(plansFilterPosition, merge);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition updateImpl(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(plansFilterPosition, merge);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.ext.portlet.plans.NoSuchFilterPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(plansFilterPositionPK);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(plansFilterPositionPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> findByUserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserIdPlanTypeId(userId, planTypeId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> findByUserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdPlanTypeId(userId, planTypeId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> findByUserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdPlanTypeId(userId, planTypeId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition findByUserIdPlanTypeId_First(
        java.lang.Long userId, java.lang.Long planTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchFilterPositionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdPlanTypeId_First(userId, planTypeId, obc);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition findByUserIdPlanTypeId_Last(
        java.lang.Long userId, java.lang.Long planTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchFilterPositionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdPlanTypeId_Last(userId, planTypeId, obc);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition[] findByUserIdPlanTypeId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK,
        java.lang.Long userId, java.lang.Long planTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchFilterPositionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdPlanTypeId_PrevAndNext(plansFilterPositionPK,
            userId, planTypeId, obc);
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

    public static java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByUserIdPlanTypeId(java.lang.Long userId,
        java.lang.Long planTypeId) throws com.liferay.portal.SystemException {
        getPersistence().removeByUserIdPlanTypeId(userId, planTypeId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByUserIdPlanTypeId(java.lang.Long userId,
        java.lang.Long planTypeId) throws com.liferay.portal.SystemException {
        return getPersistence().countByUserIdPlanTypeId(userId, planTypeId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlansFilterPositionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlansFilterPositionPersistence persistence) {
        _persistence = persistence;
    }
}
