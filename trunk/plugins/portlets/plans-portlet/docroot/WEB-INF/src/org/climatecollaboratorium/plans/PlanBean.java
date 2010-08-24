package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.plans.events.PlanDeletedEvent;

import javax.faces.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class PlanBean {
    private PlanItemWrapper plan;
    private PlanItem planItem;
    private PlanPositionsBean planPositionsBean;
    private SimulationBean simulationBean;
    private PlanModelBean modelBean;
    private boolean editingDescription;
    private boolean editingName;
    private CreatePlanBean createPlanBean;
    private PlanMembershipBean membershipBean;
    private PlansPermissionsBean permissions;
    private EventBus eventBus;
    private HandlerRegistration scenarioSavedEventHandlerRegistration;
    private static ThemeDisplay td = Helper.getThemeDisplay();
    private int selectedTabIndex = 0;
    private Long planId = -1L;
    private static final Map<String, Integer> tabNameIndexMap = new HashMap<String, Integer>();
    static {
        tabNameIndexMap.put("description", 0);
        tabNameIndexMap.put("positions", 1);
        tabNameIndexMap.put("models", 2);
        tabNameIndexMap.put("actionsimpacts", 3);
        tabNameIndexMap.put("discussion", 4);
        tabNameIndexMap.put("team", 5);
        tabNameIndexMap.put("admin", 6);
    }

    
    private static Log _log = LogFactoryUtil.getLog(PlanBean.class);
    
    public PlanBean() throws SystemException, PortalException {
    }
    
    public void init(Long planId, EventBus eventBus, Map<String, String> parameters) throws SystemException, PortalException {
        this.planId = planId;
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
        else {
            // default tab if no tab is set
            selectedTabIndex = 0;
        }
        refresh();
    }
    
    public void clear() {
        planId = -1L;
    }
    
    public Long getPlanId() {
        return planId;
    }
    
    
    public void refresh() throws SystemException, PortalException {
        if (simulationBean != null) {
            simulationBean.cleanup();
        }
        if (planId != null && planId > 0) {
            planItem = PlanItemLocalServiceUtil.getPlan(planId);
            plan = new PlanItemWrapper(planItem, this, permissions);
            permissions.setPlan(planItem);
            planPositionsBean = new PlanPositionsBean(planItem, this);
            simulationBean = new SimulationBean(planItem, this, eventBus);
            membershipBean = new PlanMembershipBean(planItem, this, permissions);
            modelBean = new PlanModelBean(planItem,this);
        }
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

    public PlanModelBean getModelBean() {
        
        return modelBean;
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

    public void planDeleted() {
        eventBus.fireEvent(new PlanDeletedEvent(planItem));   
    }

    public void uploadFile(ActionEvent evt) {
        System.err.println("Should upload file");
    }

    public void setPermissions(PlansPermissionsBean permissions) {
        this.permissions = permissions;
        
    }
}
