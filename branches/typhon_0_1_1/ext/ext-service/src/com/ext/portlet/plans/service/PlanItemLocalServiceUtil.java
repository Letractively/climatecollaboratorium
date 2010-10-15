package com.ext.portlet.plans.service;


/**
 * <a href="PlanItemLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanItemLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanItemLocalService
 *
 */
public class PlanItemLocalServiceUtil {
    private static PlanItemLocalService _service;

    public static com.ext.portlet.plans.model.PlanItem addPlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.SystemException {
        return getService().addPlanItem(planItem);
    }

    public static com.ext.portlet.plans.model.PlanItem createPlanItem(
        java.lang.Long id) {
        return getService().createPlanItem(id);
    }

    public static void deletePlanItem(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanItem(id);
    }

    public static void deletePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.SystemException {
        getService().deletePlanItem(planItem);
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

    public static com.ext.portlet.plans.model.PlanItem getPlanItem(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanItem(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlanItems(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanItems(start, end);
    }

    public static int getPlanItemsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanItemsCount();
    }

    public static com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanItem(planItem);
    }

    public static com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanItem(planItem, merge);
    }

    public static com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().createPlan(phase, authorId);
    }

    public static com.ext.portlet.plans.model.PlanItem createPlan(
        java.lang.Long planTypeId, java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().createPlan(planTypeId, authorId);
    }

    public static com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan, java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().createPlan(basePlan, authorId);
    }

    public static com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan,
        com.ext.portlet.contests.model.ContestPhase contestPhase,
        java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().createPlan(basePlan, contestPhase, authorId);
    }

    public static com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.Plan basePlan)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().createPlan(basePlan);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans()
        throws com.liferay.portal.SystemException {
        return getService().getPlans();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlansInContestPhase(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlansInContestPhase(contestPhase);
    }

    public static com.ext.portlet.plans.model.PlanItem getPlan(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException {
        return getService().getPlan(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phase, start,
            end, sortColumn, sortDirection);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection,
        boolean applyFilters)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phase, start,
            end, sortColumn, sortDirection, applyFilters);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phases, start,
            end, sortColumn, sortDirection);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phases, start,
            end, sortColumn, sortDirection, applyFilters);
    }

    public static boolean isNameAvailable(java.lang.String planName,
        com.ext.portlet.contests.model.Contest c)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().isNameAvailable(planName, c);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> applyFilters(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.plans.model.PlanItem> plans)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().applyFilters(sessionMap, requestMap, planType, plans);
    }

    public static void removePlanWithEntireHistory(java.lang.Long planId) {
        getService().removePlanWithEntireHistory(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getAllVersions(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getAllVersions(plan);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getPlanAttributes(plan);
    }

    public static com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.SystemException {
        return getService().getPlanAttribute(plan, name);
    }

    public static void reIndex() throws com.liferay.portal.SystemException {
        getService().reIndex();
    }

    public static void reIndex(long planId)
        throws com.liferay.portal.SystemException {
        getService().reIndex(planId);
    }

    public static PlanItemLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanItemLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanItemLocalService service) {
        _service = service;
    }
}
