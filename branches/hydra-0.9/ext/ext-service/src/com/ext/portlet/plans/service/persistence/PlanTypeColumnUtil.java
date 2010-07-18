package com.ext.portlet.plans.service.persistence;

public class PlanTypeColumnUtil {
    private static PlanTypeColumnPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn) {
        getPersistence().cacheResult(planTypeColumn);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> planTypeColumns) {
        getPersistence().cacheResult(planTypeColumns);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanTypeColumn create(
        java.lang.Long planTypeColumnId) {
        return getPersistence().create(planTypeColumnId);
    }

    public static com.ext.portlet.plans.model.PlanTypeColumn remove(
        java.lang.Long planTypeColumnId)
        throws com.ext.portlet.plans.NoSuchPlanTypeColumnException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planTypeColumnId);
    }

    public static com.ext.portlet.plans.model.PlanTypeColumn remove(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planTypeColumn);
    }

    /**
     * @deprecated Use <code>update(PlanTypeColumn planTypeColumn, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanTypeColumn update(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planTypeColumn);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTypeColumn the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTypeColumn is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanTypeColumn update(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planTypeColumn, merge);
    }

    public static com.ext.portlet.plans.model.PlanTypeColumn updateImpl(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planTypeColumn, merge);
    }

    public static com.ext.portlet.plans.model.PlanTypeColumn findByPrimaryKey(
        java.lang.Long planTypeColumnId)
        throws com.ext.portlet.plans.NoSuchPlanTypeColumnException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planTypeColumnId);
    }

    public static com.ext.portlet.plans.model.PlanTypeColumn fetchByPrimaryKey(
        java.lang.Long planTypeColumnId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planTypeColumnId);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanTypeColumnPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanTypeColumnPersistence persistence) {
        _persistence = persistence;
    }
}
