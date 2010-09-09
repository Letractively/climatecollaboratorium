package org.climatecollaboratorium.plans;

import org.climatecollaboratorium.navigation.NavigationEvent;
import java.util.Map;

public enum PlanPageType {
    
    PLAN_DETAILS(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(PLANS_SOURCE);
            return params == null ? false : params.get(PLANID_PARAM) != null;
        }
        
    }),
    ISSUE_DETAILS(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(DEBATE_SOURCE);
            return params == null ? false : params.get(DEBATEID_PARAM) != null;
        }
    
    }),
    // proposals are default view so return them when subview parameter is set or not
    CONTEST_PROPOSALS(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(PLANS_SOURCE);
            return params == null ? false : params.get(SUBVIEW_PARAM) == null || 
                    params.get(SUBVIEW_PARAM).equals(SUBVIEW_PROPOSALS_NAME);
        }
    
    }),
    CONTEST_ISSUES(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(PLANS_SOURCE);
            return params == null ? false : SUBVIEW_ISSUES_NAME.equals(params.get(SUBVIEW_PARAM));
        }
    
    }),
    CONTEST_MODEL(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(PLANS_SOURCE);
            return params == null ? false : SUBVIEW_MODEL_NAME.equals(params.get(SUBVIEW_PARAM));
        }
    
    });

    private final static String DEBATE_SOURCE = "debate";
    private final static String DEBATEID_PARAM = "debateId";
    
    private final static String PLANS_SOURCE = "plans";
    private final static String PLANID_PARAM = "planId";
    private final static String CONTESTS_PARAM = "contests";
    private final static String SUBVIEW_PARAM = "subview";
    private final static String SUBVIEW_PROPOSALS_NAME = "proposals";
    private final static String SUBVIEW_ISSUES_NAME = "issues";
    private final static String SUBVIEW_MODEL_NAME = "model";
    
    private DeterminePageTypeForNavigationEvent pageDeterminator;
    
    private final static PlanPageType defaultType = CONTEST_PROPOSALS;
    
    PlanPageType(DeterminePageTypeForNavigationEvent pageDeterminator) {
        this.pageDeterminator = pageDeterminator;
    }
    
    public static PlanPageType getPageTypeForNavEvent(NavigationEvent e) {
        for (PlanPageType type: values()) {
            if (type.pageDeterminator.determine(e)) {
                return type;
            }
        }
        return defaultType;
    }
    
    public static PlanPageType getDefaultPageType() {
        return defaultType;
    }
    
    interface DeterminePageTypeForNavigationEvent {
        boolean determine(NavigationEvent e);
    }

}
