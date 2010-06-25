package com.ext.portlet.plans.service.persistence;

public class PlanItemFinderUtil {
    private static PlanItemFinder _finder;

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans() {
        return getFinder().getPlans();
    }

    public static void removePlanWithHistory(long planId) {
        getFinder().removePlanWithHistory(planId);
    }

    public static int countFilteredPlans(
        com.ext.portlet.plans.model.PlansUserSettings planUserSettings)
        throws java.lang.Exception {
        return getFinder().countFilteredPlans(planUserSettings);
    }

    public static java.util.List<com.ext.portlet.plans.model.Plan> getFilteredPlans(
        com.ext.portlet.plans.model.PlansUserSettings planUserSettings,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection) throws java.lang.Exception {
        return getFinder()
                   .getFilteredPlans(planUserSettings, start, end, sortColumn,
            sortDirection);
    }

    public static int countPlans(long planTypeId) {
        return getFinder().countPlans(planTypeId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        long planTypeId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException {
        return getFinder()
                   .getPlans(planTypeId, start, end, sortColumn, sortDirection);
    }

    public static int getUserVotePosition(long userId,
        java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException {
        return getFinder().getUserVotePosition(userId, sortColumn, sortDirection);
    }

    public static int getFilteredUserVotePosition(
        com.ext.portlet.plans.model.PlansUserSettings planUserSettings,
        long userId, java.lang.String sortColumn, java.lang.String sortDirection)
        throws java.lang.Exception {
        return getFinder()
                   .getFilteredUserVotePosition(planUserSettings, userId,
            sortColumn, sortDirection);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> getPlansPositions(
        java.util.List<com.ext.portlet.plans.model.Plan> plans) {
        return getFinder().getPlansPositions(plans);
    }

    public static PlanItemFinder getFinder() {
        return _finder;
    }

    public void setFinder(PlanItemFinder finder) {
        _finder = finder;
    }
}
