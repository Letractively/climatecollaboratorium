package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mit.simulation.climate.client.Simulation;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.exceptions.BeanInitializationException;
import org.climatecollaboratorium.plans.wrappers.ContestPhaseWrapper;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.climatecollaboratorium.plans.wrappers.PlanModelWrapper;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class ContestBean {
    
    private ContestWrapper contest;
    private boolean activeContest = true; 
    private ContestSubView subView = ContestSubView.PROPOSALS;
    
    private final static String PLANS_SOURCE = "plans"; 
    private final static String SUBVIEW_PARAM = "subview";
    private final static String CONTESTS_PARAM = "contests";
    private final static String PAST_CONTESTS_PARAM_VAL = "past";

    public List<SelectItem> availableModels = new ArrayList<SelectItem>();
    public Long modelId;
    public boolean flag = true;

    private final static Log _log = LogFactoryUtil.getLog(ContestBean.class);
    
    public ContestBean(Map<String, String> params) throws SystemException, PortalException, BeanInitializationException {
        Long contestId = null;
        if (params.containsKey("contestId")) {
            try {
                contestId = Long.parseLong(params.get("contestId"));
                contest = new ContestWrapper(ContestLocalServiceUtil.getContest(contestId));
            }
            catch (NumberFormatException e) {
                throw new BeanInitializationException("Can't parse contest id", e);
            }
        }
        else {
            contest = new ContestWrapper(ContestLocalServiceUtil.getContestByActiveFlag(true));
        }
        activeContest = contest.isContestActive();
        initModels();
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
    }
    

    public void initModels() throws SystemException, PortalException {
        modelId = contest.getContest().getPlanType().getDefaultModelId();
            availableModels.clear();
            for (Simulation sim:contest.getContest().getPlanType().getAvailableModels()) {

                availableModels.add(new SelectItem(sim.getId(), PlanModelWrapper.getDisaggregationName(sim)));
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

    public List<SelectItem> getAvailableModels() {
        return availableModels;
    }
    
    public Long getModelId() throws PortalException, SystemException {
        return modelId;
    }

    public void setModelId(Long id) {
        this.modelId = id;
    }
    
    public Long getContestId() {
        return contest.getContest().getContestPK();
    }

}
