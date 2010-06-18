package com.ext.portlet.plans.service.persistence;

public interface PlanItemFinder {
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans();

    public void removePlanWithHistory(long planId);
}
