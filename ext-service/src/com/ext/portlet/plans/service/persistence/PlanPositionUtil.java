package com.ext.portlet.plans.service.persistence;

public class PlanPositionUtil {
    private static PlanPositionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanPosition planPosition) {
        getPersistence().cacheResult(planPosition);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPosition> planPositions) {
        getPersistence().cacheResult(planPositions);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanPosition create(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK) {
        return getPersistence().create(planPositionPK);
    }

    public static com.ext.portlet.plans.model.PlanPosition remove(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planPositionPK);
    }

    public static com.ext.portlet.plans.model.PlanPosition remove(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planPosition);
    }

    /**
     * @deprecated Use <code>update(PlanPosition planPosition, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanPosition update(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planPosition);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanPosition update(
        com.ext.portlet.plans.model.PlanPosition planPosition, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planPosition, merge);
    }

    public static com.ext.portlet.plans.model.PlanPosition updateImpl(
        com.ext.portlet.plans.model.PlanPosition planPosition, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planPosition, merge);
    }

    public static com.ext.portlet.plans.model.PlanPosition findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planPositionPK);
    }

    public static com.ext.portlet.plans.model.PlanPosition fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planPositionPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> findByPositionId(
        java.lang.Long positionId) throws com.liferay.portal.SystemException {
        return getPersistence().findByPositionId(positionId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> findByPositionId(
        java.lang.Long positionId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPositionId(positionId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> findByPositionId(
        java.lang.Long positionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPositionId(positionId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanPosition findByPositionId_First(
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPositionId_First(positionId, obc);
    }

    public static com.ext.portlet.plans.model.PlanPosition findByPositionId_Last(
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPositionId_Last(positionId, obc);
    }

    public static com.ext.portlet.plans.model.PlanPosition[] findByPositionId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK,
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPositionId_PrevAndNext(planPositionPK, positionId, obc);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPositionId(java.lang.Long positionId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByPositionId(positionId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPositionId(java.lang.Long positionId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByPositionId(positionId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanPositionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanPositionPersistence persistence) {
        _persistence = persistence;
    }
}
