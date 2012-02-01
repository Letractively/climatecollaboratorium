package com.ext.portlet.plans.service.persistence;

public class PlanSectionDefinitionUtil {
    private static PlanSectionDefinitionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition) {
        getPersistence().cacheResult(planSectionDefinition);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> planSectionDefinitions) {
        getPersistence().cacheResult(planSectionDefinitions);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition remove(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planSectionDefinition);
    }

    /**
     * @deprecated Use <code>update(PlanSectionDefinition planSectionDefinition, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanSectionDefinition update(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planSectionDefinition);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planSectionDefinition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planSectionDefinition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanSectionDefinition update(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(planSectionDefinition, merge);
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition updateImpl(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planSectionDefinition, merge);
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> findAll(
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

    public static PlanSectionDefinitionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanSectionDefinitionPersistence persistence) {
        _persistence = persistence;
    }
}
