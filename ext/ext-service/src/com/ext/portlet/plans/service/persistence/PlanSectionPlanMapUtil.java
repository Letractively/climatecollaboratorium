package com.ext.portlet.plans.service.persistence;

public class PlanSectionPlanMapUtil {
    private static PlanSectionPlanMapPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap) {
        getPersistence().cacheResult(planSectionPlanMap);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> planSectionPlanMaps) {
        getPersistence().cacheResult(planSectionPlanMaps);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap create(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK) {
        return getPersistence().create(planSectionPlanMapPK);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap remove(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planSectionPlanMapPK);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap remove(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planSectionPlanMap);
    }

    /**
     * @deprecated Use <code>update(PlanSectionPlanMap planSectionPlanMap, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanSectionPlanMap update(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planSectionPlanMap);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planSectionPlanMap the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planSectionPlanMap is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanSectionPlanMap update(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(planSectionPlanMap, merge);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap updateImpl(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planSectionPlanMap, merge);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planSectionPlanMapPK);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planSectionPlanMapPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findByPlanId(
        java.lang.Long relatedPlanId) throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(relatedPlanId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(relatedPlanId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(relatedPlanId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap findByPlanId_First(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_First(relatedPlanId, obc);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap findByPlanId_Last(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId_Last(relatedPlanId, obc);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap[] findByPlanId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK,
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanId_PrevAndNext(planSectionPlanMapPK,
            relatedPlanId, obc);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findBySectionId(
        java.lang.Long sectionId) throws com.liferay.portal.SystemException {
        return getPersistence().findBySectionId(sectionId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findBySectionId(
        java.lang.Long sectionId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBySectionId(sectionId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findBySectionId(
        java.lang.Long sectionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBySectionId(sectionId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap findBySectionId_First(
        java.lang.Long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException {
        return getPersistence().findBySectionId_First(sectionId, obc);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap findBySectionId_Last(
        java.lang.Long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException {
        return getPersistence().findBySectionId_Last(sectionId, obc);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap[] findBySectionId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK,
        java.lang.Long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBySectionId_PrevAndNext(planSectionPlanMapPK,
            sectionId, obc);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByPlanId(relatedPlanId);
    }

    public static void removeBySectionId(java.lang.Long sectionId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeBySectionId(sectionId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByPlanId(relatedPlanId);
    }

    public static int countBySectionId(java.lang.Long sectionId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBySectionId(sectionId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanSectionPlanMapPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanSectionPlanMapPersistence persistence) {
        _persistence = persistence;
    }
}
