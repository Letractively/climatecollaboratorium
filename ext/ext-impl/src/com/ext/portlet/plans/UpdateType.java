package com.ext.portlet.plans;

public enum UpdateType {
    CREATED("Created"), 
    DESCRIPTION_UPDATED("Updated description"), 
    NAME_UPDATED("Updated name"), 
    SCENARIO_UPDATED("Updated scenario"), 
    PLAN_TYPE_UPDATED("Updated plan type"), 
    MB_GROUP_UPDATED("Updated discussion thread"), 
    PLAN_GROUP_UPDATED("Updated community"), 
    PLAN_POSITIONS_UPDATED("Updated positions"), 
    MODEL_UPDATED("Updated model"), 
    PLAN_PUBLISHED("Published"),
    PLAN_DELETED("Deleted"), 
    PLAN_OPENED("Opened"),
    PLAN_CLOSED("Closed"),
    PLAN_STATUS_UPDATED("Updated status"),
    PLAN_SECTION_UPDATED("Updated section");

    private final String description;
    UpdateType(String description) {
        this.description = description;
    }
    public String description() {
        return description;
    }
}
