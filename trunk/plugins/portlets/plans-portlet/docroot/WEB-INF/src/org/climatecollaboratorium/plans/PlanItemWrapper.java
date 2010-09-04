package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.plans.activity.PlanActivityKeys;

import com.ext.portlet.PlanStatus;
import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class PlanItemWrapper {
    private PlanItem wrapped;
    private PlanBean planBean;
    private String candidateName;
    private String candidateDescription;
    private List<PlanDescription> planDescriptions;
    private List<PlanPositions> planPositions;
    private List<PlanModelRun> planModelRuns = new ArrayList<PlanModelRun>();
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

    private ThemeDisplay td = Helper.getThemeDisplay();
    private boolean deleted;
    
    private static Log _log = LogFactoryUtil.getLog(PlanItemWrapper.class);
    
    public PlanItemWrapper(PlanItem plan, PlanBean planBean, PlansPermissionsBean permissions) throws SystemException, PortalException {
        wrapped = plan;
        this.planBean = planBean;
        planDescriptions = wrapped.getAllDescriptionVersions();
        
        for (PlanDescription planDescription: planDescriptions) {
            planDescriptionsById.put(planDescription.getId(), planDescription);
            planDescriptionItems.add(new SelectItem(planDescription.getId(), planDescription.getCreated() + " by " + planDescription.getUpdateAuthor().getScreenName()));
        }
        currentDescriptionVersion = planDescriptions.get(0).getId();

        
        this.permissions = permissions;
        
        

        
        setDescriptionSet(plan.getDescription().trim().length() != 0);
        candidateName = plan.getName();
    }
    

    public String getDescription() throws SystemException {
        return planDescriptionsById.get(currentDescriptionVersion).getDescription();
    }
    
    public void setDescription(String description) {
        candidateDescription = description;
    }

    public String getName() throws SystemException {
        return candidateName;//planDescriptionsById.get(currentDescriptionVersion).getName();
    }

    public void setName(String name) {
        candidateName = name;
    }
    
    public void saveDescription(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (candidateDescription != null) {
                wrapped.setDescription(candidateDescription, Helper.getLiferayUser().getUserId());
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.EDIT_DESCRIPTION.id(),null, 0);
            }
        }
        planBean.setEditingDescription(false);
    }
    
    public void saveName(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (candidateName != null) {
                if (! PlanItemLocalServiceUtil.isNameAvailable(candidateName)) {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Name \"" + candidateName + "\" is already taken, please choose different one.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                    
                }
                wrapped.setName(candidateName, Helper.getLiferayUser().getUserId());
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                        PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.EDIT_NAME.id(),null, 0);
                
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Name changed.");
                FacesContext.getCurrentInstance().addMessage(null, message);
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
            PlanActivityKeys activityKey = PlanActivityKeys.VOTE_FOR_PLAN;
            try {
                    if (PlanVoteLocalServiceUtil.getPlanVote(Helper.getLiferayUser().getUserId()) != null) {
                        activityKey = PlanActivityKeys.SWICTH_VOTE_FOR_PLAN;
                    }
            }
            catch (Throwable ex) {
                // ignore
            }
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), activityKey.id(),null, 0);
        }
        
    }
    
    public void unvote(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped.unvote(Helper.getLiferayUser().getUserId());

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.RETRACT_VOTE_FOR_PLAN.id(),null, 0);
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

    public Long getContestPhaseId() throws SystemException, PortalException {
        return wrapped.getContestPhase().getContestPhasePK();
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
    }
    
    public List<PlanHistoryItem> getAllVersions() throws SystemException, PortalException {
        planVersions.clear();
        for (PlanItem planVersion: wrapped.getAllVersions()) {
            planVersions.add(new PlanHistoryItem(planVersion));
        }
        Collections.sort(planVersions, new Comparator<PlanHistoryItem>() {

            @Override
            public int compare(PlanHistoryItem arg0, PlanHistoryItem arg1) {
                return arg1.getUpdateDate().compareTo(arg0.getUpdateDate());
            }
            
        });
        return planVersions;
    }
    
    public PlanType getPlanType() throws PortalException, SystemException {
        return wrapped.getPlanType();
    }

    public Long getSelectedModel() throws SystemException {
        return wrapped.getPlanMeta().getModelId();
    }
    
    public Long getScenarioId() throws SystemException {
        return wrapped.getScenarioId();
    }

    public List<SelectItem> getPlanModelRunVersionItems() throws PortalException, SystemException {
        List<PlanModelRun> tmp  = wrapped.getAllPlanModelRuns();
        if (tmp.size() != planModelRuns.size()) {
            planModelRuns = tmp;
            planModelRunsById.clear();
            planModelRunItems.clear();
        
            for (PlanModelRun planModelRun: planModelRuns) {
                planModelRunsById.put(planModelRun.getId(), planModelRun);
                planModelRunItems.add(new SelectItem(planModelRun.getId(), planModelRun.getCreated() + " by " + planModelRun.getUpdateAuthor().getScreenName()));
            }        

            currentPlanModelRunVersion = planModelRuns.get(0).getId();
        }
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

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.PUBLISH_UPDATES.id(),null, 0);
        }
    }
    
    public void delete(ActionEvent e) throws SystemException, PortalException {
        if (permissions.getCanAdmin()) {
            wrapped.delete(Helper.getLiferayUser().getUserId());

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.REMOVE_PLAN.id(),null, 0);
            this.deleted = true;
            planBean.planDeleted();
        }
    }


    public boolean getDeleted() {
        return deleted;
    }
    
    public void setDescriptionSet(boolean descriptionSet) {
        this.descriptionSet = descriptionSet;
    }


    public boolean isDescriptionSet() {
        return descriptionSet;
    }
    
    
    public boolean isDescriptionLatestVersion() {
        return currentDescriptionVersion.equals(planDescriptions.get(0).getId());
    }    
    
    public boolean isSimulationLatestVersion() {
        return currentPlanModelRunVersion.equals(planModelRuns.get(0).getId());
    }
    
    public Date getDescriptionVersionDate() {
        return planDescriptionsById.get(currentDescriptionVersion).getCreated();
    }
    
    public Date getSimulationVersionDate() {
        return planModelRunsById.get(currentPlanModelRunVersion).getCreated();
    }
    
    public User getDescriptionVersionAuthor() throws PortalException, SystemException {
        return planDescriptionsById.get(currentDescriptionVersion).getUpdateAuthor();
    }
    
    public User getSimulationVersionAuthor() throws PortalException, SystemException {
        return planModelRunsById.get(currentPlanModelRunVersion).getUpdateAuthor();
    }
    
        public Long getCategoryGroupId() throws SystemException {
        return wrapped.getCategoryGroupId();
    }
    
    public Long getGroupId() throws SystemException {
        return wrapped.getPlanGroupId();
    }
    
    public List<PlanFan> getPlanFans() throws SystemException {
        return wrapped.getFans();
    }
    
    public void becomeAFan(ActionEvent e) throws SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped.addFan(Helper.getLiferayUser().getUserId());
        }
    }
    
    public void unfan(ActionEvent e) throws SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped.removeFan(Helper.getLiferayUser().getUserId());
        }
    }
    
    public boolean isUserAFan() throws SystemException {
        if (Helper.isUserLoggedIn()) {
            return wrapped.isUserAFan(Helper.getLiferayUser().getUserId());
        }
        return false;
    }
    
    public boolean isPlanMember() throws SystemException {
        if (Helper.isUserLoggedIn()) {
            return wrapped.isUserAMember(Helper.getLiferayUser().getUserId());
        }
        return false;
    }
    
    public boolean isHasRequestedMembership() throws SystemException {
        if (Helper.isUserLoggedIn()) {
            return wrapped.hasUserRequestedMembership(Helper.getLiferayUser().getUserId());
        }
        return false;
    }
    
    public int getPlanFansCount() throws SystemException {
        return wrapped.getFans().size();
    }
    
    public int getPlanMembersCount() throws SystemException {
        return UserLocalServiceUtil.getGroupUsersCount(wrapped.getPlanGroupId());
    }
    
    public boolean isOpen() throws SystemException {
        return wrapped.getOpen();
    }
    
    public PlanStatus getStatus() throws SystemException {
        return PlanStatus.valueOf(wrapped.getStatus());
    }
    
    public void toggleSubmitted(ActionEvent e) throws SystemException {
        if (Helper.isUserLoggedIn()) {
            PlanStatus status = getStatus();
            String newStatus = PlanStatus.UNDER_DEVELOPMENT.name();
            if (status == PlanStatus.UNDER_DEVELOPMENT) {
                newStatus = PlanStatus.SUBMITTED.name();
            }
            wrapped.setStatus(newStatus, Helper.getLiferayUser().getUserId());
        }
    }
    
    public void toggleOpen(ActionEvent e) throws SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped.setOpen(! wrapped.getOpen(), Helper.getLiferayUser().getUserId());
        }       
    }
    
    public Long getPlanGroupId() throws SystemException {
        return wrapped.getPlanGroupId();
    }

}