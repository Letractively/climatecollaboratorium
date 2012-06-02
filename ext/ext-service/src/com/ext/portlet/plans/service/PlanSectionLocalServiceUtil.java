package com.ext.portlet.plans.service;


/**
 * <a href="PlanSectionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanSectionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanSectionLocalService
 *
 */
public class PlanSectionLocalServiceUtil {
    private static PlanSectionLocalService _service;

    public static com.ext.portlet.plans.model.PlanSection addPlanSection(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException {
        return getService().addPlanSection(planSection);
    }

    public static com.ext.portlet.plans.model.PlanSection createPlanSection(
        java.lang.Long id) {
        return getService().createPlanSection(id);
    }

    public static void deletePlanSection(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanSection(id);
    }

    public static void deletePlanSection(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException {
        getService().deletePlanSection(planSection);
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

    public static com.ext.portlet.plans.model.PlanSection getPlanSection(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanSection(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> getPlanSections(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanSections(start, end);
    }

    public static int getPlanSectionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanSectionsCount();
    }

    public static com.ext.portlet.plans.model.PlanSection updatePlanSection(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanSection(planSection);
    }

    public static com.ext.portlet.plans.model.PlanSection updatePlanSection(
        com.ext.portlet.plans.model.PlanSection planSection, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanSection(planSection, merge);
    }

    public static com.ext.portlet.plans.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def)
        throws com.liferay.portal.SystemException {
        return getService().getCurrentForPlanSectionDef(plan, def);
    }

    public static com.ext.portlet.plans.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def,
        boolean createOnEmpty) throws com.liferay.portal.SystemException {
        return getService().getCurrentForPlanSectionDef(plan, def, createOnEmpty);
    }

    public static com.ext.portlet.plans.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def)
        throws com.liferay.portal.SystemException {
        return getService().getForPlanSectionDef(plan, def);
    }

    public static com.ext.portlet.plans.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def, boolean current,
        boolean createOnEmpty) throws com.liferay.portal.SystemException {
        return getService()
                   .getForPlanSectionDef(plan, def, current, createOnEmpty);
    }

    public static com.ext.portlet.plans.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlanSectionDefinition(plan, def);
    }

    public static com.ext.portlet.plans.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def, boolean store)
        throws com.liferay.portal.SystemException {
        return getService()
                   .createNewVersionForPlanSectionDefinition(plan, def, store);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSection> getAllForPlanDefinition(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def)
        throws com.liferay.portal.SystemException {
        return getService().getAllForPlanDefinition(plan, def);
    }

    public static PlanSectionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanSectionLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanSectionLocalService service) {
        _service = service;
    }
}
