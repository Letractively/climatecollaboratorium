package com.ext.portlet.plans.service;


/**
 * <a href="PlanTeamHistoryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanTeamHistoryLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanTeamHistoryLocalService
 *
 */
public class PlanTeamHistoryLocalServiceUtil {
    private static PlanTeamHistoryLocalService _service;

    public static com.ext.portlet.plans.model.PlanTeamHistory addPlanTeamHistory(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.SystemException {
        return getService().addPlanTeamHistory(planTeamHistory);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory createPlanTeamHistory(
        java.lang.Long id) {
        return getService().createPlanTeamHistory(id);
    }

    public static void deletePlanTeamHistory(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanTeamHistory(id);
    }

    public static void deletePlanTeamHistory(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.SystemException {
        getService().deletePlanTeamHistory(planTeamHistory);
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

    public static com.ext.portlet.plans.model.PlanTeamHistory getPlanTeamHistory(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanTeamHistory(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> getPlanTeamHistories(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanTeamHistories(start, end);
    }

    public static int getPlanTeamHistoriesCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanTeamHistoriesCount();
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory updatePlanTeamHistory(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanTeamHistory(planTeamHistory);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory updatePlanTeamHistory(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlanTeamHistory(planTeamHistory, merge);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory newHistoryItem(
        java.lang.Long planId, java.lang.Long userId, java.lang.String action,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException {
        return getService()
                   .newHistoryItem(planId, userId, action, updateAuthorId);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory newHistoryItem(
        java.lang.Long planId, java.lang.Long userId, java.lang.String action,
        java.lang.String payload, java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException {
        return getService()
                   .newHistoryItem(planId, userId, action, payload,
            updateAuthorId);
    }

    public static com.ext.portlet.plans.model.PlanTeamHistory getLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.SystemException {
        return getService().getLastUserActionInPlan(planId, userId);
    }

    public static PlanTeamHistoryLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanTeamHistoryLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanTeamHistoryLocalService service) {
        _service = service;
    }
}
