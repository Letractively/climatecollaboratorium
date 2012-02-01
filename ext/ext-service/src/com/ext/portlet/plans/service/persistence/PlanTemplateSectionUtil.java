package com.ext.portlet.plans.service.persistence;

public class PlanTemplateSectionUtil {
    private static PlanTemplateSectionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection) {
        getPersistence().cacheResult(planTemplateSection);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> planTemplateSections) {
        getPersistence().cacheResult(planTemplateSections);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection create(
        PlanTemplateSectionPK planTemplateSectionPK) {
        return getPersistence().create(planTemplateSectionPK);
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection remove(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planTemplateSectionPK);
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection remove(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planTemplateSection);
    }

    /**
     * @deprecated Use <code>update(PlanTemplateSection planTemplateSection, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanTemplateSection update(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planTemplateSection);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTemplateSection the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTemplateSection is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanTemplateSection update(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(planTemplateSection, merge);
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection updateImpl(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planTemplateSection, merge);
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection findByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planTemplateSectionPK);
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection fetchByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planTemplateSectionPK);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> findAll(
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

    public static PlanTemplateSectionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanTemplateSectionPersistence persistence) {
        _persistence = persistence;
    }
}
