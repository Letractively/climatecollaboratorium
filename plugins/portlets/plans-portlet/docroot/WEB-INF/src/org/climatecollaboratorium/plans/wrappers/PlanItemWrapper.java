package org.climatecollaboratorium.plans.wrappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.compass.core.util.backport.java.util.Arrays;

import com.ext.portlet.PlanStatus;
import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.utils.userInput.UserInputException;
import com.ext.utils.userInput.service.UserInputFilterUtil;
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
    private boolean promoted;
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
    private Map<String, String> planAttributes;
    private boolean subscribed;
    

    private final static String[] regionsDevelopedArr = {"United States", "European Union", "Russia/Former Soviet Union", "OECD Asia", "Canada"};
    private final static String[] regionsRapidlyDevelopingArr = {"China", "India", "Brazil", "South Africa", "Mexico", "Rapidly developing Asia", };
    private final static String[] regionsOtherDevelopingArr = {"Middle East",  "Latin America", "Africa", "Other developing Asia"};
    
    private final static Set<String> regionsDeveloped = new HashSet<String>(Arrays.asList(regionsDevelopedArr));
    private final static Set<String> regionsRapidlyDeveloping = new HashSet<String>(Arrays.asList(regionsRapidlyDevelopingArr));
    private final static Set<String> regionsOtherDeveloping = new HashSet<String>(Arrays.asList(regionsOtherDevelopingArr));
    private final static List<SelectItem> availableRegions = new ArrayList<SelectItem>();
    
    static {
        for (String region: regionsDevelopedArr) {
            availableRegions.add(new SelectItem(region));
        }

        for (String region: regionsRapidlyDevelopingArr) {
            availableRegions.add(new SelectItem(region));
        }
        
        for (String region: regionsOtherDevelopingArr) {
            availableRegions.add(new SelectItem(region));
        }
    }
    
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
        promoted = wrapped.getPromoted();
        
        getPlanModelRunVersionItems();
        
        setDescriptionSet(plan.getDescription().trim().length() != 0);
        name = plan.getName();
        description = plan.getDescription();
        
        planMode = PlanMode.getMode(wrapped);
        planStatus = PlanStatusSelection.getStatus(wrapped);
        helpStatus = wrapped.isSeekingAssistance();
        
        scenarioId = wrapped.getScenarioId();
        
        if (Helper.isUserLoggedIn()) {
            subscribed = ActivitySubscriptionLocalServiceUtil.isSubscribed(Helper.getLiferayUser().getUserId(), PlanItem.class, wrapped.getPlanId(), null, "");
        }
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
    
    public void saveDescription(ActionEvent e) throws SystemException, PortalException, UserInputException {
        if (Helper.isUserLoggedIn()) {
            String savedDescription = UserInputFilterUtil.filterHtml(description);
            if (savedDescription != null) {
                wrapped.setDescription(savedDescription, Helper.getLiferayUser().getUserId());
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.EDIT_DESCRIPTION.id(),null, 0);
                eventBus.fireEvent(new PlanUpdatedEvent(wrapped));
            }
        }
        planBean.setEditingDescription(false);
    }
    
    public void saveName(ActionEvent e) throws SystemException, PortalException, UserInputException {
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

    public void vote(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            PlanActivityKeys activityKey = PlanActivityKeys.VOTE_FOR_PLAN;
            try {
                if (PlanVoteLocalServiceUtil.getPlanVote(Helper.getLiferayUser().getUserId(), wrapped.getContest().getContestPK()) != null) {
                    activityKey = PlanActivityKeys.SWICTH_VOTE_FOR_PLAN;
                }
               
            }
            catch (Throwable ex) {
                // backend can throw no such vote exception, it should be ignored as this is a normal case
            }
            wrapped.vote(Helper.getLiferayUser().getUserId());
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
    
    public void subscribe(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (isSubscribed()) {
                ActivitySubscriptionLocalServiceUtil.deleteSubscription(Helper.getLiferayUser().getUserId(), PlanItem.class, wrapped.getPlanId(), null, "");
            }
            else {
                ActivitySubscriptionLocalServiceUtil.addSubscription(PlanItem.class, wrapped.getPlanId(), null, "", Helper.getLiferayUser().getUserId());
            }
            ActivitySubscriptionLocalServiceUtil.isSubscribed(Helper.getLiferayUser().getUserId(), PlanItem.class, wrapped.getPlanId(), null, "");
            subscribed = !subscribed;
        }
    }
    
    public boolean isSubscribed() throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            return ActivitySubscriptionLocalServiceUtil.isSubscribed(Helper.getLiferayUser().getUserId(), PlanItem.class, wrapped.getPlanId(), null, "");
        }
        return false;
    }

    public PlanItem getWrapped() {

        return wrapped;
    }

    public Long getContestPhaseId() throws SystemException, PortalException {
        return wrapped.getContestPhase().getContestPhasePK();
    }
    
    public ContestPhase getContestPhase() throws PortalException, SystemException {
        return wrapped.getContestPhase();
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
    
    public void promote(ActionEvent e) throws PortalException, SystemException {
        if (permissions.getCanAdminAll()) {
            wrapped.promote(Helper.getLiferayUser());
            
           //SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
           //         PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.PROMOTE_PLAN.id(),null, 0);
            
            this.promoted = true;
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
    
    public List<PlanFan> getPlanFansHalf1() throws SystemException {
        return wrapped.getFans().subList(0, wrapped.getFans().size() / 2 + wrapped.getFans().size() % 2);
    }
    
    public List<PlanFan> getPlanFansHalf2() throws SystemException {
        return wrapped.getFans().subList(wrapped.getFans().size() / 2 + wrapped.getFans().size() % 2, wrapped.getFans().size());
    }
    
    public void becomeAFan(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            wrapped.addFan(Helper.getLiferayUser().getUserId());

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.BECOME_A_SUPPORTER.id(),null, 0);
            subscribed = true;
        }
    }
    
    public void unfan(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            wrapped.removeFan(Helper.getLiferayUser().getUserId());
            
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), wrapped.getPlanId(), PlanActivityKeys.STOPPED_BEEING_A_SUPPORTER.id(),null, 0);
            subscribed = false;
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
    
    public long getVotesPercent() throws SystemException, PortalException {
        Integer totalVotes = wrapped.getContest().getTotalVotes();
        Integer planVotes = wrapped.getVotes();
        if (totalVotes == null || totalVotes == 0 || planVotes == null || planVotes <= 0) {
            return 0;
        }
        return Math.round( ((double) planVotes * 100)/ totalVotes);
    }
    
    public Integer getVotes() throws SystemException, PortalException {
        Integer planVotes = wrapped.getVotes();
        return planVotes != null ? planVotes : 0;
    }
    
    public Map<String, String> getAttributes() throws SystemException, PortalException {
        //if (planAttributes == null) {
            planAttributes = new HashMap<String, String>();
            for (PlanAttribute attr: wrapped.getPlanAttributes()) {
                planAttributes.put(attr.getAttributeName(), attr.getAttributeValue());
            }
            
            for (PlanConstants.Columns column: PlanConstants.Columns.values()) {
                planAttributes.put(column.name(), column.getValue(wrapped));
            }
        //}
        return planAttributes;
        
    }
    
    public int getPositionsCount() throws NoSuchPlanPositionsException, SystemException {
        return wrapped.getPositionsIds().size();
    }
    
    public int getMembersCount() throws SystemException {
        return wrapped.getMembers().size();
    }
    
    public int getCommentsCount() throws PortalException, SystemException {
        return wrapped.getDiscussionCategoryGroup().getCommentsCount();
    }
    
    public boolean isPromoted() {
        return promoted;
    }
    
    public int getPlace() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.PLAN_PLACE.name());
        return attr != null ? (Integer) attr.getTypedValue() : -1;   
    }
    
    public Integer getRibbon() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.PLAN_RIBBON.name());
        try {
            return attr != null && attr.getAttributeValue() != null && attr.getAttributeValue().trim().length() > 0 ? 
                    Integer.parseInt(attr.getAttributeValue()) : null;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
    
    public String getRibbonText() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.PLAN_RIBBON_TEXT.name());
        return attr != null ? attr.getAttributeValue() : null;
    }
    
    
    public void markAsSemiFinalist(ActionEvent e) throws PortalException, SystemException {
        if (permissions.getCanAdminAll()) {
            wrapped.setPlace(2);
        }
    }
    
    public void removeSemiFinalistRibbon(ActionEvent e) throws PortalException, SystemException {
        if (permissions.getCanAdminAll()) {
            wrapped.removePlace();
        }
    }    
    
    public void setRibbon(Integer ribbon) throws SystemException {
        wrapped.setRibbon(ribbon);
    }
    
    public void setRibbonText(String ribbonText) throws SystemException {
        wrapped.setRibbonText(ribbonText);
    }
    
    public boolean getHasModel() throws PortalException, SystemException {
        return wrapped.getPlanType().getDefaultModelId() != null && wrapped.getPlanType().getDefaultModelId()  > 0L; 
    }
    
    public boolean isRegional() throws PortalException, SystemException {
        return wrapped.getPlanType().isRegional();
    }
    

    public String getRegionEconomy() throws SystemException {
        String region = getRegion();
        if (region != null) {
            if (regionsDeveloped.contains(region)) {
                return "Developed";
            }
            else if (regionsRapidlyDeveloping.contains(region)) {
                return "Rapidly Developing";
            }

            else if (regionsOtherDeveloping.contains(region)) {
                return "Other Developing";
            }
        }
        return null;
                
    }
    
    public String getRegion() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute("REGION");
        if (attr != null) {
            return attr.getAttributeValue();
        }
        return null;
    }
    
    public void setRegion(String region) throws SystemException {
        wrapped.setAttribute("REGION", region);
    }
    
    public void setSubregion(String subregion) throws SystemException {
        wrapped.setAttribute("SUBREGION", subregion);
    }
    
    public String getSubregion() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute("SUBREGION");
        if (attr != null) {
            return attr.getAttributeValue();
        }
        return null;
    }
    
    public List<SelectItem> getAvailableRegions() {
        return availableRegions;
    }
    
    public boolean isScrapbook() throws SystemException {
        return wrapped.getPlanAttribute("SCRAPBOOK") != null;
    }
    
    public void toggleScrapbook(ActionEvent e) throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute("SCRAPBOOK");
        if (attr != null) {
            wrapped.removeAttribute("SCRAPBOOK");
        }
        else {
            wrapped.setAttribute("SCRAPBOOK", "true");
        }
        
    }

    public void refresh() throws SystemException {
        name = wrapped.getName();
        description = wrapped.getDescription();
    }
    
    
    public void setAbstract(String abstractStr) throws SystemException {
        wrapped.setAttribute("ABSTRACT", abstractStr);
    }
    
    public String getAbstract() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute("ABSTRACT");
        if (attr != null) {
            return attr.getAttributeValue();
        }
        return null;
    }

}
