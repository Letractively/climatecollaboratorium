package org.climatecollaboratorium.plans;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.facelets.simulations.ScenarioSavedEvent;
import org.climatecollaboratorium.facelets.simulations.support.SimulationSupportTag;
import org.climatecollaboratorium.plans.activity.PlanActivityKeys;

import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class PlanBean {
    private PlanItemWrapper plan;
    private PlanItem planItem;
    private PlanPositionsBean planPositionsBean;
    private SimulationBean simulationBean;

    private boolean editingDescription;
    private boolean editingName;
    private CreatePlanBean createPlanBean;
    private PlanMembershipBean membershipBean;
    private PlansPermissionsBean permissions;
    private EventBus eventBus;
    private HandlerRegistration scenarioSavedEventHandlerRegistration;
    private static ThemeDisplay td = Helper.getThemeDisplay();
    private int selectedTabIndex = 0;
    private static final Map<String, Integer> tabNameIndexMap = new HashMap<String, Integer>();
    static {
        tabNameIndexMap.put("description", 0);
        tabNameIndexMap.put("positions", 1);
        tabNameIndexMap.put("models", 2);
        tabNameIndexMap.put("actionsimpacts", 3);
        tabNameIndexMap.put("discussion", 4);
        tabNameIndexMap.put("team", 5);
    }

    
    private static Log _log = LogFactoryUtil.getLog(PlanBean.class);
    
    
    public PlanBean(final PlanItem planItem, EventBus eventBus, Map<String, String> parameters) throws SystemException, PortalException {
        this.planItem = planItem;
        this.permissions = new PlansPermissionsBean(planItem);
        planPositionsBean = new PlanPositionsBean(planItem, this);
        plan = new PlanItemWrapper(planItem, this, permissions);
        simulationBean = new SimulationBean(planItem, this, eventBus);
        this.eventBus = eventBus;
        if (parameters.containsKey("tab")) {
            try {
                Integer tmp = tabNameIndexMap.get( parameters.get("tab") );
                selectedTabIndex = tmp != null ? tmp : 0;
            }
            catch (NumberFormatException e) {
                _log.error("Can't parse tab number: " + parameters.get("tab"), e);
            }
        }
    }
    
    public void refresh() throws SystemException, PortalException {
        planItem = PlanItemLocalServiceUtil.getPlan(planItem.getPlanId());
        planPositionsBean = new PlanPositionsBean(planItem, this);
        plan = new PlanItemWrapper(planItem, this, permissions);
        if (simulationBean != null) {
            simulationBean.cleanup();
        }
        simulationBean = new SimulationBean(planItem, this, eventBus);
        membershipBean = new PlanMembershipBean(planItem, this, permissions);
    }

    public PlanItemWrapper getPlan() {
        return plan;
    }

    public void setEditingDescription(boolean editingDescription) throws SystemException, PortalException {
        this.editingDescription = editingDescription;
        refresh();
    }

    public boolean isEditingDescription() {
        return editingDescription;
    }

    public void editDescription(ActionEvent e) {
        editingDescription = !editingDescription;
    }

    public void setEditingName(boolean editingName) throws SystemException, PortalException {
        this.editingName = editingName;
        refresh();
    }

    public boolean isEditingName() {
        return editingName;
    }
    
    public void editName(ActionEvent e) {
        editingName = !editingName;
    }
    
    public PlanPositionsBean getPlanPositionsBean() {
        return planPositionsBean;
    }

    public SimulationBean getSimulationBean() {
        return simulationBean;
    }
    
    public int getVotesPercent() throws SystemException {
        int votes = plan.getVotes();
        int votesTotal = PlanVoteLocalServiceUtil.getPlanVotesCount();
        if (votes == 0 || votesTotal == 0) {
            return 0;
        }
        
        return (100 * votes) / votesTotal;
    }
    
    
    public CreatePlanBean getCreatePlanBean() {
        if (createPlanBean == null) {
            createPlanBean = new CreatePlanBean(this);
        }
        return createPlanBean;
    }
    
    public PlanMembershipBean getMembershipBean() throws PortalException, SystemException {
        if (membershipBean == null) {
            membershipBean = new PlanMembershipBean(plan.getWrapped(), this, permissions);
        }
        return membershipBean;
    }
    
    public void cleanup() {
        simulationBean.cleanup();
    }
    
    public int getSelectedTab() {
        return selectedTabIndex;
    }
    
    public void setSelectedTab(int selectedTab) {
        selectedTabIndex = selectedTab;
    }

}
