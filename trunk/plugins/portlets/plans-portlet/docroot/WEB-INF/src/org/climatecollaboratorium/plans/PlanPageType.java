package org.climatecollaboratorium.plans;

import org.climatecollaboratorium.navigation.NavigationEvent;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum PlanPageType {
    
    PLAN_DETAILS(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(PLANS_SOURCE);
            return params != null && e.getParameters(DEBATE_SOURCE) == null;
        }

        @Override
        public boolean determine(Map<String, String> params) {
            // TODO Auto-generated method stub
            return params.containsKey(CONTESTID_PARAM) && params.containsKey(PLANID_PARAM);
        }
        
    }),
    ISSUE_DETAILS(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(DEBATE_SOURCE);
            return params == null ? false : params.get(DEBATEID_PARAM) != null;
        }

        @Override
        public boolean determine(Map<String, String> params) {
            return params.get(DEBATEID_PARAM) != null;
        }
    
    }),
    CONTEST_ISSUES(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(PLANS_SOURCE);
            return params == null ? false : params.get(SUBVIEW_PARAM) != null && SUBVIEW_ISSUES_NAME.equals(params.get(SUBVIEW_PARAM));
        }

        @Override
        public boolean determine(Map<String, String> params) {
            return params.containsKey("contestId") && params.get(SUBVIEW_PARAM) != null && SUBVIEW_ISSUES_NAME.equals(params.get(SUBVIEW_PARAM));
        }
    
    }),
    CONTEST_MODEL(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(PLANS_SOURCE);
            return params == null ? false : params.get(SUBVIEW_PARAM) != null && SUBVIEW_MODEL_NAME.equals(params.get(SUBVIEW_PARAM));
        }

        @Override
        public boolean determine(Map<String, String> params) {
            // TODO Auto-generated method stub
            return params.containsKey("contestId") && params.get(SUBVIEW_PARAM) != null && SUBVIEW_MODEL_NAME.equals(params.get(SUBVIEW_PARAM));
        }
    
    }),
    CONTEST_PROPOSALS(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            Map<String, String> params = e.getParameters(PLANS_SOURCE);
            return params == null ? true : params.containsKey("contests") || params.get(SUBVIEW_PARAM) != null && params.get(SUBVIEW_PARAM).equals(SUBVIEW_PROPOSALS_NAME);
        }

        @Override
        public boolean determine(Map<String, String> params) {
            return params.containsKey(CONTESTID_PARAM);
        }
    
    }),
    
    // contests index is default view, it will be checked last so it can simply return true
    CONTESTS(new DeterminePageTypeForNavigationEvent() {

        @Override
        public boolean determine(NavigationEvent e) {
            return true;
        }

        @Override
        public boolean determine(Map<String, String> params) {
            return true;
        }
    
    });


    private final static String DEBATE_SOURCE = "debate";
    private final static String DEBATEID_PARAM = "debateId";
    
    private final static String PLANS_SOURCE = "plans";
    private final static String PLANID_PARAM = "planId";
    private final static String CONTESTID_PARAM = "contestId";
    private final static String CONTESTS_PARAM = "contests";
    private final static String SUBVIEW_PARAM = "subview";
    private final static String SUBVIEW_PROPOSALS_NAME = "proposals";
    private final static String SUBVIEW_ISSUES_NAME = "issues";
    private final static String SUBVIEW_MODEL_NAME = "model";
    private final static PlanPageType defaultType = CONTESTS;
    
    private final static Map<PlanPageType, Set<PlanPageType>> allowedPredecessorsMap = new HashMap<PlanPageType, Set<PlanPageType>>();
    static {
        allowedPredecessorsMap.put(PLAN_DETAILS, new HashSet<PlanPageType>( Arrays.asList(new PlanPageType[] {ISSUE_DETAILS})));
        allowedPredecessorsMap.put(ISSUE_DETAILS, new HashSet<PlanPageType>( Arrays.asList(new PlanPageType[] {CONTEST_ISSUES, PLAN_DETAILS, ISSUE_DETAILS, CONTEST_PROPOSALS})));
        allowedPredecessorsMap.put(CONTEST_ISSUES, new HashSet<PlanPageType>( Arrays.asList(new PlanPageType[] {ISSUE_DETAILS, CONTEST_ISSUES, CONTEST_MODEL, CONTEST_PROPOSALS})));
        allowedPredecessorsMap.put(CONTEST_MODEL, new HashSet<PlanPageType>( Arrays.asList(new PlanPageType[] {CONTEST_ISSUES, CONTEST_MODEL, CONTEST_PROPOSALS})));
        allowedPredecessorsMap.put(CONTEST_PROPOSALS, new HashSet<PlanPageType>( Arrays.asList(new PlanPageType[] {CONTEST_ISSUES, CONTEST_MODEL, CONTEST_PROPOSALS})));
        allowedPredecessorsMap.put(CONTESTS, new HashSet<PlanPageType>( Arrays.asList(new PlanPageType[] {} )));
    }
    
    private final DeterminePageTypeForNavigationEvent pageDeterminator;
    
    
    PlanPageType(DeterminePageTypeForNavigationEvent pageDeterminator) {
        this.pageDeterminator = pageDeterminator;
    }
    
    public static PlanPageType getPageTypeForNavEvent(NavigationEvent e, PlanPageType oldType) {
        for (PlanPageType type: values()) {
            if (type.allowedPredecessorsMap.get(type).contains(oldType) && type.pageDeterminator.determine(e)) {
                return type;
            }
        }
        return oldType;
    }
    
    public static PlanPageType getPageTypeForParams(Map<String, String> params) {
        for (PlanPageType type: values()) {
            if (type.pageDeterminator.determine(params)) {
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
        boolean determine(Map<String, String> params);
    }

}
