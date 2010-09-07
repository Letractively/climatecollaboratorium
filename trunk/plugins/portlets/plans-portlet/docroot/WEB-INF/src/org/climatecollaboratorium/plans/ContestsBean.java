package org.climatecollaboratorium.plans;

import java.util.Map;

import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;

import com.ext.portlet.contests.NoSuchContestException;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ContestsBean {
    
    private ContestWrapper contest;
    private boolean activeContest = true; 
    private ContestSubView subView = ContestSubView.PROPOSALS; 
    private final static String SUBVIEW_PARAM = "subview";
    private IssuesBean issuesBean = new IssuesBean();
    

    private final static Log _log = LogFactoryUtil.getLog(ContestsBean.class);
    
    public ContestsBean() throws NoSuchContestException, SystemException {
        contest = new ContestWrapper(ContestLocalServiceUtil.getContestByActiveFlag(activeContest));
    }
    
    public void init(boolean activeContest, Map<String, String> navigationParameters, NavigationEvent event) throws NoSuchContestException, SystemException {
        if (navigationParameters.containsKey(SUBVIEW_PARAM)) {
            ContestSubView tmp = ContestSubView.valueOf(navigationParameters.get(SUBVIEW_PARAM).toUpperCase());
            if (tmp != subView) {
                subView = tmp;
            }
        }
        issuesBean.init(event);
        
        if (this.activeContest == activeContest) {
            return;
        }
        this.activeContest = activeContest;
        subView = ContestSubView.PROPOSALS;
        
        contest = new ContestWrapper(ContestLocalServiceUtil.getContestByActiveFlag(activeContest));
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
    
    public IssuesBean getIssuesBean() {
        return issuesBean;
    }

}
