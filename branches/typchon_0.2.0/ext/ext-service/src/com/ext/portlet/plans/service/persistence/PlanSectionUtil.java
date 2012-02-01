package com.ext.portlet.plans.service.persistence;

public class PlanSectionUtil {
    private static PlanSectionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanSection planSection) {
        getPersistence().cacheResult(planSection);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSection> planSections) {
        getPersistence().cacheResult(planSections);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanSection create(
        PlanSectionPK planSectionPK) {
        return getPersistence().create(planSectionPK);
    }

    public static com.ext.portlet.plans.model.PlanSection remove(
        PlanSectionPK planSectionPK)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planSectionPK);
    }

    public static com.ext.portlet.plans.model.PlanSection remove(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planSection);
    }

    /**
     * @deprecated Use <code>update(PlanSection planSection, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanSection update(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planSection);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planSection the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planSection is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanSection update(
        com.ext.portlet.plans.model.PlanSection planSection, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planSection, merge);
    }

    public static com.ext.portlet.plans.model.PlanSection updateImpl(
        com.ext.portlet.plans.model.PlanSection planSection, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planSection, merge);
    }

    public static com.ext.portlet.plans.model.PlanSection findByPrimaryKey(
        PlanSectionPK planSectionPK)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planSectionPK);
    }

    public static com.ext.portlet.plans.model.PlanSection fetchByPrimaryKey(
        PlanSectionPK planSectionPK) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planSectionPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanSection findByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_First(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanSection findByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_Last(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanSection[] findByPlanId_PrevAndNext(
        PlanSectionPK planSectionPK, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanId_PrevAndNext(planSectionPK, planId, obc);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByPlanId(planId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByPlanId(planId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanSectionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanSectionPersistence persistence) {
        _persistence = persistence;
    }
}
