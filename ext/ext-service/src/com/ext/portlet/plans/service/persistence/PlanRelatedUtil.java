package com.ext.portlet.plans.service.persistence;

public class PlanRelatedUtil {
    private static PlanRelatedPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanRelated planRelated) {
        getPersistence().cacheResult(planRelated);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanRelated> planRelateds) {
        getPersistence().cacheResult(planRelateds);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanRelated create(
        PlanRelatedPK planRelatedPK) {
        return getPersistence().create(planRelatedPK);
    }

    public static com.ext.portlet.plans.model.PlanRelated remove(
        PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planRelatedPK);
    }

    public static com.ext.portlet.plans.model.PlanRelated remove(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planRelated);
    }

    /**
     * @deprecated Use <code>update(PlanRelated planRelated, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanRelated update(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planRelated);
    }

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
    public static com.ext.portlet.plans.model.PlanRelated update(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planRelated, merge);
    }

    public static com.ext.portlet.plans.model.PlanRelated updateImpl(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planRelated, merge);
    }

    public static com.ext.portlet.plans.model.PlanRelated findByPrimaryKey(
        PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planRelatedPK);
    }

    public static com.ext.portlet.plans.model.PlanRelated fetchByPrimaryKey(
        PlanRelatedPK planRelatedPK) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planRelatedPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId) throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(relatedPlanId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(relatedPlanId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(relatedPlanId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanRelated findByPlanId_First(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_First(relatedPlanId, obc);
    }

    public static com.ext.portlet.plans.model.PlanRelated findByPlanId_Last(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_Last(relatedPlanId, obc);
    }

    public static com.ext.portlet.plans.model.PlanRelated[] findByPlanId_PrevAndNext(
        PlanRelatedPK planRelatedPK, java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanId_PrevAndNext(planRelatedPK, relatedPlanId, obc);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByPlanId(relatedPlanId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByPlanId(relatedPlanId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanRelatedPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanRelatedPersistence persistence) {
        _persistence = persistence;
    }
}
