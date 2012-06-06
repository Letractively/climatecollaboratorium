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
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.plans.model.PlanSection remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
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
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.plans.model.PlanSection fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanSection findByPlanIdSectionDefinitionId_First(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId_First(planId,
            planSectionDefinitionId, obc);
    }

    public static com.ext.portlet.plans.model.PlanSection findByPlanIdSectionDefinitionId_Last(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId_Last(planId,
            planSectionDefinitionId, obc);
    }

    public static com.ext.portlet.plans.model.PlanSection[] findByPlanIdSectionDefinitionId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        java.lang.Long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId_PrevAndNext(id, planId,
            planSectionDefinitionId, obc);
    }

    public static com.ext.portlet.plans.model.PlanSection findByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    public static com.ext.portlet.plans.model.PlanSection fetchByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    public static com.ext.portlet.plans.model.PlanSection fetchByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId, retrieveFromCache);
    }

    public static com.ext.portlet.plans.model.PlanSection findByPlanIdPlanVersion(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        java.lang.Long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion);
    }

    public static com.ext.portlet.plans.model.PlanSection fetchByPlanIdPlanVersion(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        java.lang.Long planVersion) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion);
    }

    public static com.ext.portlet.plans.model.PlanSection fetchByPlanIdPlanVersion(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        java.lang.Long planVersion, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion, retrieveFromCache);
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

    public static void removeByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException {
        getPersistence()
            .removeByPlanIdSectionDefinitionId(planId, planSectionDefinitionId);
    }

    public static void removeByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        getPersistence()
            .removeByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    public static void removeByPlanIdPlanVersion(java.lang.Long planId,
        java.lang.Long planSectionDefinitionId, java.lang.Long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException {
        getPersistence()
            .removeByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanIdSectionDefinitionId(java.lang.Long planId,
        java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    public static int countByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    public static int countByPlanIdPlanVersion(java.lang.Long planId,
        java.lang.Long planSectionDefinitionId, java.lang.Long planVersion)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion);
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