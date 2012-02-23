package com.ext.portlet.plans.service;


/**
 * <a href="PlanTemplateSectionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanTemplateSectionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanTemplateSectionLocalService
 *
 */
public class PlanTemplateSectionLocalServiceUtil {
    private static PlanTemplateSectionLocalService _service;

    public static com.ext.portlet.plans.model.PlanTemplateSection addPlanTemplateSection(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException {
        return getService().addPlanTemplateSection(planTemplateSection);
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection createPlanTemplateSection(
        com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK planTemplateSectionPK) {
        return getService().createPlanTemplateSection(planTemplateSectionPK);
    }

    public static void deletePlanTemplateSection(
        com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK planTemplateSectionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanTemplateSection(planTemplateSectionPK);
    }

    public static void deletePlanTemplateSection(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException {
        getService().deletePlanTemplateSection(planTemplateSection);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection getPlanTemplateSection(
        com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK planTemplateSectionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanTemplateSection(planTemplateSectionPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> getPlanTemplateSections(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanTemplateSections(start, end);
    }

    public static int getPlanTemplateSectionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanTemplateSectionsCount();
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection updatePlanTemplateSection(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanTemplateSection(planTemplateSection);
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection updatePlanTemplateSection(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlanTemplateSection(planTemplateSection, merge);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> findByPlanTemplateId(
        java.lang.Long planTemplateId)
        throws com.liferay.portal.SystemException {
        return getService().findByPlanTemplateId(planTemplateId);
    }

    public static com.ext.portlet.plans.model.PlanTemplateSection addPlanTemplateSection(
        java.lang.Long planTemplateId, java.lang.Long sectionId, int weight)
        throws com.liferay.portal.SystemException {
        return getService()
                   .addPlanTemplateSection(planTemplateId, sectionId, weight);
    }

    public static void removePlanTemplateSection(
        java.lang.Long planTemplateId, java.lang.Long sectionId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().removePlanTemplateSection(planTemplateId, sectionId);
    }

    public static PlanTemplateSectionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlanTemplateSectionLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanTemplateSectionLocalService service) {
        _service = service;
    }
}
