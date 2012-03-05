package com.ext.portlet.plans.service;


/**
 * <a href="PlanSectionPlanMapLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanSectionPlanMapLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanSectionPlanMapLocalService
 *
 */
public class PlanSectionPlanMapLocalServiceUtil {
    private static PlanSectionPlanMapLocalService _service;

    public static com.ext.portlet.plans.model.PlanSectionPlanMap addPlanSectionPlanMap(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException {
        return getService().addPlanSectionPlanMap(planSectionPlanMap);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap createPlanSectionPlanMap(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK) {
        return getService().createPlanSectionPlanMap(planSectionPlanMapPK);
    }

    public static void deletePlanSectionPlanMap(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanSectionPlanMap(planSectionPlanMapPK);
    }

    public static void deletePlanSectionPlanMap(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException {
        getService().deletePlanSectionPlanMap(planSectionPlanMap);
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

    public static com.ext.portlet.plans.model.PlanSectionPlanMap getPlanSectionPlanMap(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanSectionPlanMap(planSectionPlanMapPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> getPlanSectionPlanMaps(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanSectionPlanMaps(start, end);
    }

    public static int getPlanSectionPlanMapsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanSectionPlanMapsCount();
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap updatePlanSectionPlanMap(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanSectionPlanMap(planSectionPlanMap);
    }

    public static com.ext.portlet.plans.model.PlanSectionPlanMap updatePlanSectionPlanMap(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlanSectionPlanMap(planSectionPlanMap, merge);
    }

    public static java.util.List<Long> findPlanIdsForSection(
        java.lang.Long sectionId) throws com.liferay.portal.SystemException {
        return getService().findPlanIdsForSection(sectionId);
    }

    public static PlanSectionPlanMapLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlanSectionPlanMapLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanSectionPlanMapLocalService service) {
        _service = service;
    }
}
