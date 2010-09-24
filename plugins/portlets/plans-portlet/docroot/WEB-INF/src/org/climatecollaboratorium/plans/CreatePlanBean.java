package org.climatecollaboratorium.plans;

import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.wrappers.PlanItemWrapper;

import javax.faces.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class CreatePlanBean {

    private static Log _log = LogFactoryUtil.getLog(CreatePlanBean.class);

    private PlansIndexBean plansIndexBean;
    private String name;
    private PlanBean planBean;
    private Long planId;
    private boolean navigateToPlan;
    private PlansPreferencesBean preferences;
    
    private final static String PLANS_SOURCE = "plans"; 
    private final static String CREATE_PLAN_PARAM = "createPlan"; 
    

    public CreatePlanBean(PlansIndexBean plansIndexBean) throws SystemException {
        this.plansIndexBean = plansIndexBean;
        name = "";
        preferences = new PlansPreferencesBean();
    }

    public CreatePlanBean(PlanBean planBean) {
        this.planBean = planBean;
        name = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void createPlan(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            PlanItem planItem = null;
            if (plansIndexBean != null) {
                // get default description
                String defaultDescription = preferences.getDefaultDescription();
                planItem = PlanItemLocalServiceUtil.createPlan(plansIndexBean.getContestPhase().getPhase(), Helper.getLiferayUser().getUserId());
                planItem.setDescription(defaultDescription, Helper.getLiferayUser().getUserId());
                plansIndexBean.refresh();
            }
            else if (planBean != null) {
                // we need to create a plan based on a plan that is currently visible
                PlanItemWrapper wrapper = planBean.getPlan();
                PlanItem item = wrapper.getWrapped();
                ContestPhase phase = item.getContestPhase();
                if (!phase.getContestStatus().isCanEdit()) {
                    List<ContestPhase> active = phase.getContest().getActivePhases();
                    if (active == null || active.isEmpty()) {
                        _log.warn("Connect create plan ");
                        return;
                    } else {
                        phase = active.get(0);
                    }
                }
                planItem = PlanItemLocalServiceUtil.createPlan(planBean.getPlan().getWrapped(), phase, Helper.getLiferayUser().getUserId());
            }
            planId = planItem.getPlanId();
            navigateToPlan = true;
        }
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    // redirection should occur only once
    public boolean isNavigateToPlan() {
        if (navigateToPlan) {
            navigateToPlan = false;
            return true;
        }
        return false;
    }

    public void setNavigateToPlan(boolean navigateToPlan) {
        this.navigateToPlan = navigateToPlan;
    }

    public void setPlansIndex(PlansIndexBean bean) {
        this.plansIndexBean=bean;
    }

    public PlansIndexBean getPlansIndex() {
        return plansIndexBean;
    }

    public void init(NavigationEvent event) throws SystemException, PortalException {
        Map<String, String> params = event.getParameters(PLANS_SOURCE);
        if (params != null) {
            String createPlanStr = params.get(CREATE_PLAN_PARAM);
            if ("true".equals(createPlanStr)) {
                planBean = null;
                createPlan(null);
            }
        }
    }


}