package org.climatecollaboratorium.plans;

import java.util.Map;

import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.wrappers.ContestPhaseWrapper;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ContestsBean {
    
    private ContestWrapper contest;
    private boolean activeContest = true; 
    private ContestSubView subView = ContestSubView.PROPOSALS;
    private final static String PLANS_SOURCE = "plans"; 
    private final static String SUBVIEW_PARAM = "subview";
    private final static String CONTESTS_PARAM = "contests";
    private final static String PAST_CONTESTS_PARAM_VAL = "past";
    

    private final static Log _log = LogFactoryUtil.getLog(ContestsBean.class);
    
    public ContestsBean() throws SystemException, PortalException {
        contest = new ContestWrapper(ContestLocalServiceUtil.getContestByActiveFlag(activeContest));
        activeContest = true;
    }
    
    public void init(NavigationEvent event) throws SystemException, NoSuchContestPhaseException, PortalException {
        Map<String, String> params = event.getParameters(PLANS_SOURCE);
        if (params == null) {
            return;
        }
        if (params.containsKey(SUBVIEW_PARAM)) {
            ContestSubView tmp = ContestSubView.valueOf(params.get(SUBVIEW_PARAM).toUpperCase());
            if (tmp != subView) {
                subView = tmp;
            }
        }
        
        String contestsStr = params.get(CONTESTS_PARAM);
        // we use past contests only when contests parameter of the navigation event is set to past
        boolean curActiveContests = contestsStr != null && contestsStr.equals(PAST_CONTESTS_PARAM_VAL) ? false : true;
        
        if (this.activeContest != curActiveContests) {
            this.activeContest = curActiveContests;
            subView = ContestSubView.PROPOSALS;
            
            contest = new ContestWrapper(ContestLocalServiceUtil.getContestByActiveFlag(activeContest));
        }
    }
    
    
    public ContestWrapper getContest() {
        return contest;
    }
    
    public boolean isActiveContest() {
        return activeContest;
    }
    
    public ContestSubView getSubView() {
        return subView;
    }
    
    public ContestPhaseWrapper getCurrentPhase() throws SystemException, NoSuchContestPhaseException {
        // FIXME logic for fetching context phase should be more complex I guess
        if (contest == null) {
            return null;
        }
        if (contest.getContest().isActive()) {
            return new ContestPhaseWrapper(contest, contest.getContest().getActivePhase());
        }
        return new ContestPhaseWrapper(contest, contest.getContest().getPhases().get(0));
    }
    
    public Long getModelId() throws PortalException, SystemException {
        return contest.getContest().getPlanType().getDefaultModelId();
    }

}
