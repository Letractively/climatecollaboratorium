package org.climatecollaboratorium.plans.wrappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.plans.Helper;
import org.climatecollaboratorium.plans.PlanBean;
import org.climatecollaboratorium.plans.PlanHistoryItem;
import org.climatecollaboratorium.plans.PlanHistoryWrapper;
import org.climatecollaboratorium.plans.PlansPermissionsBean;
import org.climatecollaboratorium.plans.activity.PlanActivityKeys;
import org.climatecollaboratorium.plans.events.PlanUpdatedEvent;

import com.ext.portlet.PlanStatus;
import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanModelRun;
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
    /*
     * Description related variables
     */
    private String name;
    private String description;
    private PlanHistoryWrapper<PlanDescription> planDescriptionHistoryItem;
    private List<PlanHistoryWrapper> planDescriptionsAll = new ArrayList<PlanHistoryWrapper>(); 

    private List<PlanHistoryItem> planVersions = new ArrayList<PlanHistoryItem>();

    /*
     * Model runs
     */
    private List<PlanModelRun> planModelRuns = new ArrayList<PlanModelRun>();
    private List<PlanHistoryWrapper> planModelRunAllItems = new ArrayList<PlanHistoryWrapper>();
    private PlanHistoryWrapper<PlanModelRun> planModelRunHistoryItem;
    private Long scenarioId;


    private PlansPermissionsBean permissions;
    private boolean descriptionSet;

    private ThemeDisplay td = Helper.getThemeDisplay();
    private boolean deleted;

    private static String[] EMPTY_ARRAY = new String[]{};
    
    private EventBus eventBus;

    public enum PlanStatusSelection {SUBMITTED(PlanStatus.SUBMITTED,"An entry in the contest"),
        DRAFT(PlanStatus.UNDER_DEVELOPMENT,"Just a draft");

        String description;
        PlanStatus status;

        PlanStatusSelection(PlanStatus status, String description) {
            this.description = description;
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public static PlanStatusSelection getStatus(PlanItem item) throws SystemException {
            return PlanStatus.SUBMITTED.name().equals(item.getStatus())?SUBMITTED:DRAFT;
        }

        public void apply(PlanItem item, PlanBean bean) throws SystemException {

            if (Helper.isUserLoggedIn() && !status.name().equals(item.getStatus())) {
                item.setStatus(status.name(),Helper.getLiferayUser().getUserId());
            }
        }
    }

    public enum PlanMode {CLOSED("Team members only",false),OPEN("Anyone",true);

        String description;
        boolean mode;

        PlanMode(String description, boolean mode) {
                           this.description = description;
            this.mode = mode;
        }

        public String getDescription() {
            return description;

        }

        public void apply(PlanItem item, PlanBean bean) throws SystemException, PortalException {
         if (Helper.isUserLoggedIn() && item.getOpen()!=mode) {
            item.setOpen(mode, Helper.getLiferayUser().getUserId());
            bean.refreshIndex();
        }
        }

        public static PlanMode getMode(PlanItem item) throws SystemException {
            return item.getOpen()?OPEN:CLOSED;
        }
    };

    private static SelectItem[] openOrClosed = new SelectItem[] {
            new SelectItem(PlanMode.OPEN.name(),PlanMode.OPEN.getDescription()),
            new SelectItem(PlanMode.CLOSED.name(),PlanMode.CLOSED.getDescription())
    };

    private static SelectItem[] draftOrSubmitted = new SelectItem[] {
            new SelectItem(PlanStatusSelection.DRAFT.name(),PlanStatusSelection.DRAFT.getDescription()),
            new SelectItem(PlanStatusSelection.SUBMITTED.name(),PlanStatusSelection.SUBMITTED.getDescription())
    };

    private static SelectItem[] askForHelp = new SelectItem[] {
            new SelectItem("help","Invite others to help with this proposal (displays an indicator in the proposal index)")
    };

    private static String[] statusValue = new String[] {askForHelp[0].getValue().toString()};


    private boolean helpStatus = false;

    private PlanStatusSelection planStatus = PlanStatusSelection.DRAFT;

    private PlanMode planMode = PlanMode.CLOSED;


    
    private static Log _log = LogFactoryUtil.getLog(PlanItemWrapper.class);
    
    public PlanItemWrapper(PlanItem plan, PlanBean planBean, PlansPermissionsBean permissions) throws SystemException, PortalException {
        wrapped = plan;
        this.planBean = planBean;
        
        this.permissions = permissions;
        
        getPlanModelRunVersionItems();
        
        setDescriptionSet(plan.getDescription().trim().length() != 0);
        name = plan.getName();
        description = plan.getDescription();
        
        planMode = PlanMode.getMode(wrapped);
        planStatus = PlanStatusSelection.getStatus(wrapped);
        helpStatus = wrapped.isSeekingAssistance();
        
        scenarioId = wrapped.getScenarioId();
    }

    public SelectItem[] getAllPlanModes() {
        return openOrClosed;
    }

    public void setPlanMode(String mode) throws SystemException, PortalException {
        planMode = PlanMode.valueOf(mode);
        planMode.apply(wrapped,planBean);
    }

    public String getPlanMode() {
       return planMode.name(); 
    }

    public boolean isOpen() {
        return planMode == PlanMode.OPEN;
    }

    public SelectItem[] getAllPlanStatusSelections() {
        return draftOrSubmitted;
    }

    public void setPlanStatusSelection(String status) throws SystemException {
        planStatus = PlanStatusSelection.valueOf(status);
        planStatus.apply(wrapped,planBean);
    }

    public String getPlanStatusSelection() {
        return planStatus.name();
    }

    public SelectItem[] getAllHelpStatuses() {
        return askForHelp;
    }

    public String[] getHelpStatus() {
        return helpStatus?statusValue: EMPTY_ARRAY;
    }

    public void setHelpStatus(String[] s) throws SystemException, PortalException {
       if (s.length > 0) {
          helpStatus = true;
       } else {
           helpStatus = false;
       }
        wrapped.setSeekingAssistance(helpStatus);
        planBean.refreshIndex();
    }
    

    public String getDescription() throws SystemException {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() throws SystemException {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void saveDescription(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (description != null) {
                wrapped.setDescription(description, Helper.getLiferayUser().getUserId());
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.EDIT_DESCRIPTION.id(),null, 0);
                eventBus.fireEvent(new PlanUpdatedEvent(wrapped));
            }
        }
        planBean.setEditingDescription(false);
    }
    
    public void saveName(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (name != null && !name.equals(wrapped.getName())) {
                if (! PlanItemLocalServiceUtil.isNameAvailable(name, wrapped.getContest())) {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Name \"" + name + "\" is already taken, please choose different one.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                    
                }
                wrapped.setName(name, Helper.getLiferayUser().getUserId());
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                        PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.EDIT_NAME.id(),null, 0);
                
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Name changed.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                
                eventBus.fireEvent(new PlanUpdatedEvent(wrapped));
                //planBean.refreshIndex();
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

            eventBus.fireEvent(new PlanUpdatedEvent(wrapped));
        }
        
    }
    
    public void unvote(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped.unvote(Helper.getLiferayUser().getUserId());

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.RETRACT_VOTE_FOR_PLAN.id(),null, 0);
            
            eventBus.fireEvent(new PlanUpdatedEvent(wrapped));
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
    
    public List<PlanHistoryWrapper> getAllDescriptionVersions() throws PortalException, SystemException {
        if (planDescriptionsAll.size() == 0) {
            boolean isLatest = true;
            for (PlanDescription desc: wrapped.getAllDescriptionVersions()) {
                planDescriptionsAll.add(PlanHistoryWrapper.getWrapper(desc, isLatest));
                isLatest = false;
            }
        }
        return planDescriptionsAll;
    }
    

    public void selectDescriptionVersion(ActionEvent evt) {
       planDescriptionHistoryItem = (PlanHistoryWrapper) evt.getComponent().getAttributes().get("item");
       
       description = planDescriptionHistoryItem.getWrapped().getDescription();
   }
    
    public PlanHistoryWrapper<PlanDescription> getPlanDescriptionHistoryItem() {
        return planDescriptionHistoryItem;
    }
    
    
    public List<PlanHistoryItem> getAllVersions() throws SystemException, PortalException {
        if (planVersions.size() < wrapped.getAllVersions().size()) {
            planVersions.clear();
            for (PlanItem planVersion: wrapped.getAllVersions()) {
                planVersions.add(new PlanHistoryItem(planVersion));
            }
        }
        return planVersions;
    }
    
    public PlanType getPlanType() throws PortalException, SystemException {
        return wrapped.getPlanType();
    }

    public Long getSelectedModel() throws SystemException {
	    return planBean.getModelBean().getSelectedItem();

    }
    
    public Long getScenarioId() throws SystemException {
        
        return wrapped.getScenarioId();
    }

    public List<PlanHistoryWrapper> getPlanModelRunVersionItems() throws PortalException, SystemException {
        if (planModelRunAllItems.size() == 0) {
            boolean isLatest = true;
            for (PlanModelRun planModelRun: wrapped.getAllPlanModelRuns()) {
                planModelRunAllItems.add(PlanHistoryWrapper.getWrapper(planModelRun, isLatest));
                isLatest = false;
            }
        }
        return planModelRunAllItems;
    }
    
    public void selectModelRunVersion(ActionEvent evt) {
        planModelRunHistoryItem = (PlanHistoryWrapper<PlanModelRun>) evt.getComponent().getAttributes().get("item");
        scenarioId = planModelRunHistoryItem.getWrapped().getScenarioId();
    }
    
    public Long getPlanModelRunScenarioId() {
        return scenarioId;
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
    
    
    public boolean isSimulationLatestVersion() {
        return planDescriptionHistoryItem == null || planDescriptionHistoryItem.isLatest();
    }
    
    public Date getSimulationVersionDate() {
        return planDescriptionHistoryItem.getUpdateDate();
    }
    
    public User getSimulationVersionAuthor() throws PortalException, SystemException {
        return planDescriptionHistoryItem.getUpdateAuthor();
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
    


    
    public Long getPlanGroupId() throws SystemException {
        return wrapped.getPlanGroupId();
    }
    
    public boolean isSeekingAssistance() throws SystemException {
        return wrapped.isSeekingAssistance();
    }
    
    public void setSeekingAssistance(boolean seekingAssistance) throws PortalException, SystemException {
        wrapped.setSeekingAssistance(seekingAssistance);
        planBean.refreshIndex();
    }


    public void modelChanged() {
        scenarioId = null;
        planModelRunHistoryItem = null;
    }
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

}
