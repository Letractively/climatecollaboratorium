package org.climatecollaboratorium.plans;

import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portal.theme.ThemeDisplay;
import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.events.PlanDeletedEvent;
import org.climatecollaboratorium.plans.events.PlanUpdatedEvent;
import org.climatecollaboratorium.plans.exceptions.BeanInitializationException;
import org.climatecollaboratorium.plans.wrappers.PlanItemWrapper;


import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanBean {
    private PlanItemWrapper plan;
    private PlanItem planItem;
    private PlanPositionsBean planPositionsBean;
    private SimulationBean simulationBean = new SimulationBean();
    private PlanModelBean modelBean;
    private boolean editingDescription;
    private boolean editingName;
    private CreatePlanBean createPlanBean;
    private PlanMembershipBean membershipBean;
    private PlansPermissionsBean permissions;
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private static ThemeDisplay td = Helper.getThemeDisplay();
    private int selectedTabIndex = 0;
    private Long planId = -1L;
    private DiscussionBean commentsBean = new DiscussionBean();

    private static final Map<String, Integer> tabNameIndexMap = new HashMap<String, Integer>();
    private final static String PLANS_SOURCE = "plans";
    private final static String NEW_PLAN_PARAM = "newPlan";

    private final static String DEFAULT_TAB = "actionsimpacts";
    private boolean planOpenForEditing = false;
    // this is ver bad solution, we should use event bus for communication
    // between beans instead of direct references
    private PlansIndexBean plansIndexBean;
    private org.climatecollaboratorium.facelets.simulations.SimulationBean externalSimulationBean;
    private NavigationEvent lastNavEvent;
    static {

        tabNameIndexMap.put("admin", tabNameIndexMap.size());
        tabNameIndexMap.put("description", tabNameIndexMap.size());
        tabNameIndexMap.put("actionsimpacts", tabNameIndexMap.size());
        tabNameIndexMap.put("sections", tabNameIndexMap.size());
        tabNameIndexMap.put("comments", tabNameIndexMap.size());
        tabNameIndexMap.put("team", tabNameIndexMap.size());
        tabNameIndexMap.put("discussion", tabNameIndexMap.size());
    }

    private static Log _log = LogFactoryUtil.getLog(PlanBean.class);

    public PlanBean(Map<String, String> params, PlansPermissionsBean permissions) throws SystemException, PortalException, BeanInitializationException {

        this.permissions = permissions;
        
        String planIdStr = params.get("planId");

        if (planIdStr != null && planIdStr.trim().length() > 0) {
            try {
                planId = Long.parseLong(planIdStr);
                refresh();
                selectedTabIndex = getDefaultTab();
            } catch (NumberFormatException e) {
                throw new BeanInitializationException("Can't parse plan id", e);
            }
        }
        

    }

    public void init(NavigationEvent event) throws SystemException, PortalException, IllegalUIConfigurationException, IOException {
        lastNavEvent = event;
        Map<String, String> parameters = event.getParameters(PLANS_SOURCE);
        if (parameters == null) {
            selectedTabIndex = getDefaultTab();
            return;
        }

        if (parameters.containsKey("tab")) {
            try {
                Integer tmp = tabNameIndexMap.get(parameters.get("tab"));
                selectedTabIndex = tmp != null ? tmp : getDefaultTab();
            } catch (NumberFormatException e) {
                _log.error("Can't parse tab number: " + parameters.get("tab"), e);
            }
        } else {
            // default tab if no tab is set
            selectedTabIndex = getDefaultTab();
        }

        if (parameters.containsKey(NEW_PLAN_PARAM) && permissions.getCanEdit()) {
            // 
            if (planItem.getPlanDescriptions().get(0).getVersion() == 1) {
                editingDescription = true;
                editingName = true;
            }
            if (planItem.getAllPlanModelRuns().get(0).getVersion() == 0) {
                if (!simulationBean.isEditing()) {
                    simulationBean.edit(null);
                    externalSimulationBean.editActions(null);
                }
                planOpenForEditing = true;
            }
            if (planItem.getPlanPositions().getVersion() == 0) {
                if (!planPositionsBean.isEditing()) {
                    planPositionsBean.edit(null);
                }
            }
        } else {
            planOpenForEditing = false;
            editingDescription = false;
            editingName = false;
            if (simulationBean != null && simulationBean.isEditing()) {
                simulationBean.edit(null);
            }
            if (planPositionsBean != null && planPositionsBean.isEditing()) {
                planPositionsBean.edit(null);
            }
        }
    }

    public int getDefaultTab() throws SystemException, PortalException {
        if (planItem != null && planItem.getAllPlanModelRuns().get(0).getVersion() == 0 && permissions.getCanEdit() && planItem.getPlanType().getModelId() > 0) {
            return tabNameIndexMap.get("actionsimpacts");
        } else
            return tabNameIndexMap.get("description");
    }

    public void clear() {
        planId = -1L;
    }

    public Long getPlanId() {
        return planId;
    }

    public void refreshFull() throws SystemException, PortalException, IllegalUIConfigurationException, IOException {
        init(lastNavEvent);
    }
    
    public void refresh() throws SystemException, PortalException {
        /*
         * if (simulationBean != null) { simulationBean.cleanup(); }
         */
        if (planId != null && planId > 0) {

            planItem = PlanItemLocalServiceUtil.getPlan(planId);
            modelBean = new PlanModelBean(planItem, this);
            plan = new PlanItemWrapper(planItem, this, permissions);
            plan.setEventBus(eventBus);
            permissions.setPlan(planItem);
            planPositionsBean = new PlanPositionsBean(planItem);
            planPositionsBean.setEventBus(eventBus);
            if (simulationBean.isSaved()) {
                planOpenForEditing = false;
            }

            simulationBean.setPlan(planItem, this);
            // simulationBean = new SimulationBean(planItem, this, eventBus);
            membershipBean = new PlanMembershipBean(planItem, this, permissions);

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

    public void editDescription(ActionEvent e) throws SystemException {
        editingDescription = !editingDescription;
        editingName = !editingName;
        
        plan.setName(plan.getWrapped().getName());
        plan.setDescription(plan.getWrapped().getDescription());
        
        plan.refresh();
    }

    public void setEditingName(boolean editingName) throws SystemException, PortalException {
        this.editingName = editingName;
        refresh();
    }

    public boolean isEditingName() {
        return editingName;
    }

    public void editName(ActionEvent e) throws SystemException {
        plan.setName(plan.getWrapped().getName());
        plan.setDescription(plan.getWrapped().getDescription());
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

    public int getVotesPercent() throws SystemException, PortalException {
        int votes = plan.getVotes();
        int votesTotal = PlanVoteLocalServiceUtil.getPlanVotesCount();
        if (votes == 0 || votesTotal == 0) {
            return 0;
        }

        return (100 * votes) / votesTotal;
    }

    public CreatePlanBean getCreatePlanBean() throws SystemException {
        if (createPlanBean == null) {
            createPlanBean = new CreatePlanBean(this);
            if (eventBus != null) {
                createPlanBean.setEventBus(eventBus);
            }
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
        _log.debug("Should upload file");
    }

    public void setPermissions(PlansPermissionsBean permissions) {
        this.permissions = permissions;

    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        simulationBean.setEventBus(eventBus);
        if (plan != null) {
            plan.setEventBus(eventBus);
        }
        if (createPlanBean != null) {
            createPlanBean.setEventBus(eventBus);
        }
        if (planPositionsBean != null) {
            planPositionsBean.setEventBus(eventBus);
        }

        bind();
    }

    public void setPlansIndexBean(PlansIndexBean plansIndexBean) {
        this.plansIndexBean = plansIndexBean;
    }

    public void refreshIndex() throws PortalException, SystemException {
        plansIndexBean.refresh();
    }

    public void modelChanged() {
        plan.modelChanged();

    }

    public boolean isPlanOpenForEditing() {
        return planOpenForEditing;
    }

    public String getRelativeUrl() throws SystemException, PortalException, UnsupportedEncodingException {
        String result;
        if (plan != null) {
            result = "http://climatecolab.org/web/guest/plans/-/plans/contestId/" + 
                plan.getWrapped().getContest().getContestPK() + "/planId/" + getPlanId();
        }
        else {
            result = "http://climatecolab.org/web/guest/plans/-/plans/";
        }
        return URLEncoder.encode(result,"UTF-8");
    }
    
    
    private void bind() {
        for (HandlerRegistration reg : handlerRegistrations) {
            reg.unregister();
        }

        handlerRegistrations.clear();

        handlerRegistrations.add(eventBus.registerHandler(PlanUpdatedEvent.class, new EventHandler<PlanUpdatedEvent>() {

            @Override
            public void onEvent(PlanUpdatedEvent event) {
                // refresh plan item wrapper
                try {
                    refresh();
                } catch (SystemException e) {
                    _log.error("Can't refresh plan item wrapper after plan update: " + event.getPlan().getPlanId(), e);
                } catch (PortalException e) {
                    _log.error("Can't refresh plan item wrapper after plan update: " + event.getPlan().getPlanId(), e);
                }

            }
        }));
    }
    
    public DiscussionBean getCommentsBean() {
        return commentsBean;
    }

    public void setExternalSimulationBean(org.climatecollaboratorium.facelets.simulations.SimulationBean externalSimulationBean) {
        this.externalSimulationBean = externalSimulationBean;
        
    }
    
    public void cancelSimulationEdit(ActionEvent e) throws SystemException, IllegalUIConfigurationException, PortalException, IOException {
        externalSimulationBean.editActions(e);
        refresh();
    }
    
    private Boolean hasAbstract;
    public boolean getHasAbstract() throws SystemException, PortalException {
        if (hasAbstract == null) {
            hasAbstract = false;
            for (PlanTypeAttribute a:  planItem.getPlanType().getAttributes()) {
                if (a.getAttributeName().equals(PlanConstants.Columns.ABSTRACT.name())) {
                    hasAbstract = true; 
                    break;
                }
                
            }
            
        }
        
        return hasAbstract;
    }
        
}
