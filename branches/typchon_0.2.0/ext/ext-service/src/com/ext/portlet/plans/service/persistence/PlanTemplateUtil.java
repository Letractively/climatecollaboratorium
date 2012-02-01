package com.ext.portlet.plans.service.persistence;

public class PlanTemplateUtil {
    private static PlanTemplatePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanTemplate planTemplate) {
        getPersistence().cacheResult(planTemplate);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTemplate> planTemplates) {
        getPersistence().cacheResult(planTemplates);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanTemplate create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.plans.model.PlanTemplate remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTemplateException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.plans.model.PlanTemplate remove(
        com.ext.portlet.plans.model.PlanTemplate planTemplate)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planTemplate);
    }

    /**
     * @deprecated Use <code>update(PlanTemplate planTemplate, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanTemplate update(
        com.ext.portlet.plans.model.PlanTemplate planTemplate)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planTemplate);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTemplate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTemplate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanTemplate update(
        com.ext.portlet.plans.model.PlanTemplate planTemplate, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planTemplate, merge);
    }

    public static com.ext.portlet.plans.model.PlanTemplate updateImpl(
        com.ext.portlet.plans.model.PlanTemplate planTemplate, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planTemplate, merge);
    }

    public static com.ext.portlet.plans.model.PlanTemplate findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTemplateException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.plans.model.PlanTemplate fetchByPrimaryKey(
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

    public static java.util.List<com.ext.portlet.plans.model.PlanTemplate> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTemplate> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTemplate> findAll(
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

    public static PlanTemplatePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanTemplatePersistence persistence) {
        _persistence = persistence;
    }
}
