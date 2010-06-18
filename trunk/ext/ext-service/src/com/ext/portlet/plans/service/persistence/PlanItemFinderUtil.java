package com.ext.portlet.plans.service.persistence;

public class PlanItemFinderUtil {
    private static PlanItemFinder _finder;

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans() {
        return getFinder().getPlans();
    }

    public static void removePlanWithHistory(long planId) {
        getFinder().removePlanWithHistory(planId);
    }

    public static PlanItemFinder getFinder() {
        return _finder;
    }

    public void setFinder(PlanItemFinder finder) {
        _finder = finder;
    }
}
