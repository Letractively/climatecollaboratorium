package com.ext.portlet.plans.service.persistence;

public class PlanUtil {
    private static PlanPersistence _persistence;

    public static void cacheResult(com.ext.portlet.plans.model.Plan plan) {
        getPersistence().cacheResult(plan);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.Plan> plans) {
        getPersistence().cacheResult(plans);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.Plan create(java.lang.Long planId) {
        return getPersistence().create(planId);
    }

    public static com.ext.portlet.plans.model.Plan remove(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planId);
    }

    public static com.ext.portlet.plans.model.Plan remove(
        com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(plan);
    }

    /**
     * @deprecated Use <code>update(Plan plan, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.Plan update(
        com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(plan);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plan the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plan is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.Plan update(
        com.ext.portlet.plans.model.Plan plan, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(plan, merge);
    }

    public static com.ext.portlet.plans.model.Plan updateImpl(
        com.ext.portlet.plans.model.Plan plan, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(plan, merge);
    }

    public static com.ext.portlet.plans.model.Plan findByPrimaryKey(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planId);
    }

    public static com.ext.portlet.plans.model.Plan fetchByPrimaryKey(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.Plan> findByname(
        java.lang.String name) throws com.liferay.portal.SystemException {
        return getPersistence().findByname(name);
    }

    public static java.util.List<com.ext.portlet.plans.model.Plan> findByname(
        java.lang.String name, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByname(name, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.Plan> findByname(
        java.lang.String name, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByname(name, start, end, obc);
    }

    public static com.ext.portlet.plans.model.Plan findByname_First(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByname_First(name, obc);
    }

    public static com.ext.portlet.plans.model.Plan findByname_Last(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByname_Last(name, obc);
    }

    public static com.ext.portlet.plans.model.Plan[] findByname_PrevAndNext(
        java.lang.Long planId, java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanException,
            com.liferay.portal.SystemException {
        return getPersistence().findByname_PrevAndNext(planId, name, obc);
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

    public static java.util.List<com.ext.portlet.plans.model.Plan> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.Plan> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.Plan> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByname(java.lang.String name)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByname(name);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByname(java.lang.String name)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByname(name);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        java.lang.Long pk) throws com.liferay.portal.SystemException {
        return getPersistence().getPlanAttributes(pk);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanAttributes(pk, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanAttributes(pk, start, end, obc);
    }

    public static int getPlanAttributesSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanAttributesSize(pk);
    }

    public static boolean containsPlanAttribute(java.lang.Long pk,
        java.lang.Long planAttributePK)
        throws com.liferay.portal.SystemException {
        return getPersistence().containsPlanAttribute(pk, planAttributePK);
    }

    public static boolean containsPlanAttributes(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().containsPlanAttributes(pk);
    }

    public static PlanPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanPersistence persistence) {
        _persistence = persistence;
    }
}
