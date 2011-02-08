package com.ext.portlet.Activity;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.plans.model.PlanItem;

public enum SubscriptionType {
    DEBATE(Debate.class.getName(), "Debate"),
    DISCUSSION(DiscussionCategoryGroup.class.getName(), "Discussion"),
    PLAN(PlanItem.class.getName(), "Proposal");
    
    private String className;
    private String printName;
    
    SubscriptionType(String className, String printName) {
        this.className = className;
        this.printName = printName;
    }
    
    public static SubscriptionType getSubscriptionType(ICollabActivityInterpreter interpreter) {

        for (String className: interpreter.getClassNames()) {
            for (SubscriptionType type: SubscriptionType.values()) {
                if (type.className.equals(className)) {
                    return type;
                }
            
            }
        }
        return null;
    }
    
    public String getPrintName() {
        return printName;
    }

}
