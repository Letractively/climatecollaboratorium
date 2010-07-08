package org.climatecollaboratorium.plans;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.plans.PlanActivityKeys;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.PlanType;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

public class PlanItemWrapper {
    private PlanItem wrapped;
    private PlanBean planBean;
    private String candidateName;
    private String candidateDescription;
    private List<PlanDescription> planDescriptions;
    private List<PlanPositions> planPositions;
    private List<PlanModelRun> planModelRuns;
    private List<PlanHistoryItem> planVersions = new ArrayList<PlanHistoryItem>();
    
    private Map<Long, PlanDescription> planDescriptionsById = new HashMap<Long, PlanDescription>();
    private List<SelectItem> planDescriptionItems = new ArrayList<SelectItem>();
    private List<SelectItem> planModelRunItems = new ArrayList<SelectItem>();
    private Map<Long, PlanPositions> planPositionsById = new HashMap<Long, PlanPositions>();
    private Map<Long, PlanModelRun> planModelRunsById = new HashMap<Long, PlanModelRun>();
    private List<SelectItem> planPositionItems = new ArrayList<SelectItem>();

    private Long currentDescriptionVersion;
    private Long planPositionsVersion;
    private Long currentPlanModelRunVersion;
    private PlansPermissionsBean permissions;
    private boolean descriptionSet;
    
    public PlanItemWrapper(PlanItem plan, PlanBean planBean, PlansPermissionsBean permissions) throws SystemException, PortalException {
        wrapped = plan;
        this.planBean = planBean;
        planDescriptions = wrapped.getAllDescriptionVersions();
        
        for (PlanDescription planDescription: planDescriptions) {
            planDescriptionsById.put(planDescription.getId(), planDescription);
            planDescriptionItems.add(new SelectItem(planDescription.getId(), planDescription.getCreated() + " by " + planDescription.getUpdateAuthor().getScreenName()));
        }
        currentDescriptionVersion = planDescriptions.get(0).getId();
        
        planModelRuns = wrapped.getAllPlanModelRuns();
        planModelRunsById.clear();
        planModelRunItems.clear();
        
        for (PlanModelRun planModelRun: planModelRuns) {
            planModelRunsById.put(planModelRun.getId(), planModelRun);
            planModelRunItems.add(new SelectItem(planModelRun.getId(), planModelRun.getCreated() + " by " + planModelRun.getUpdateAuthor().getScreenName()));
        }
        currentPlanModelRunVersion = planModelRuns.get(0).getId();
        
        this.permissions = permissions;
        
        
        for (PlanItem planVersion: plan.getAllVersions()) {
            planVersions.add(new PlanHistoryItem(planVersion));
        }
        
        setDescriptionSet(plan.getDescription().trim().length() != 0);
    }
    

    public String getDescription() throws SystemException {
        return planDescriptionsById.get(currentDescriptionVersion).getDescription();
    }
    
    public void setDescription(String description) {
        candidateDescription = description;
    }

    public String getName() throws SystemException {
        return wrapped.getName();//planDescriptionsById.get(currentDescriptionVersion).getName();
    }

    public void setName(String name) {
        candidateName = name;
    }
    
    public void saveDescription(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (candidateDescription != null) {
                wrapped.setDescription(candidateDescription, Helper.getLiferayUser().getUserId());
            }
        }
        planBean.setEditingDescription(false);
    }
    
    public void saveName(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (candidateName != null) {
                wrapped.setName(candidateName, Helper.getLiferayUser().getUserId());
            }
        }
        planBean.setEditingName(false);
    }

    
    public int getVotes() throws SystemException {
        return wrapped.getVotes();
    }
    public void vote(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped.vote(Helper.getLiferayUser().getUserId());
        }
    }
    
    public void unvote(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped.unvote(Helper.getLiferayUser().getUserId());
        }
    }
    

    public boolean isVotedOn() throws PortalException, SystemException {
        boolean voted = false;
        if (Helper.isUserLoggedIn()) {
            voted = wrapped.hasUserVoted(Helper.getLiferayUser().getUserId());
        }
        return voted;
    }
    
    public void subscribe(ActionEvent e) throws SystemException {
        if (Helper.isUserLoggedIn()) {
            ActivityUtil.addSubscription(PlanActivityKeys.ALL, Helper.getLiferayUser().getUserId(), wrapped.getPlanId());
        }
    }

    public PlanItem getWrapped() {
        return wrapped;
    }
    
    public List<SelectItem> getAllDescriptionVersions() throws PortalException, SystemException {
        return planDescriptionItems;
    }
    
    
    public Long getDescriptionVersion() {
        return currentDescriptionVersion;
    }
    
    public void setDescriptionVersion(Long descriptionVersion) {
        currentDescriptionVersion = descriptionVersion;
        candidateName = planDescriptionsById.get(descriptionVersion).getName();
        candidateDescription = planDescriptionsById.get(descriptionVersion).getDescription();
        
    }
    
    public void descriptionVersion(ValueChangeEvent event) {
        candidateName = planDescriptionsById.get(currentDescriptionVersion).getName();
        candidateDescription = planDescriptionsById.get(currentDescriptionVersion).getDescription();
        System.out.println("currentDesc ver: " + currentDescriptionVersion + "\t " + candidateDescription);
        
    }
    
    public List<PlanHistoryItem> getAllVersions() throws SystemException {
        return planVersions;
    }
    
    public PlanType getPlanType() throws PortalException, SystemException {
        return wrapped.getPlanType();
    }
    
    public Long getScenarioId() throws SystemException {
        return wrapped.getScenarioId();
    }

    public List<SelectItem> getPlanModelRunVersionItems() throws PortalException, SystemException {
        return planModelRunItems;
    }
    
    public Long getCurrentPlanModelRunVersion() {
        return currentPlanModelRunVersion;
    }
    
    public void setCurrentPlanModelRunVersion(Long selectedVersion) {
        currentPlanModelRunVersion = selectedVersion;
    }
    
    public Long getPlanModelRunScenarioId() {
        return planModelRuns != null && currentPlanModelRunVersion != null ? planModelRunsById.get(currentPlanModelRunVersion).getScenarioId() : null; 
    }
    
    
    public Long getMbCategoryId() throws SystemException {
        return wrapped.getMBCategoryId();
    }

    
    public boolean isPublished() throws PortalException, SystemException {
        return wrapped.getPlanType().getPublished();
    }
    
    public void publish(ActionEvent e) throws PortalException, SystemException {
        if (permissions.getCanAdmin()) {
            wrapped.publish(Helper.getLiferayUser().getUserId());
        }
    }
    
    public void delete(ActionEvent e) throws SystemException {
        if (permissions.getCanAdmin()) {
            wrapped.delete(Helper.getLiferayUser().getUserId());
        }
    }


    public void setDescriptionSet(boolean descriptionSet) {
        this.descriptionSet = descriptionSet;
    }


    public boolean isDescriptionSet() {
        return descriptionSet;
    }

}
