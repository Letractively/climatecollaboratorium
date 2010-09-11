package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.events.PlanDeletedEvent;
import org.climatecollaboratorium.plans.utils.PagedListDataModel;
import org.climatecollaboratorium.plans.wrappers.ContestPhaseWrapper;
import org.climatecollaboratorium.plans.wrappers.PlanIndexItemWrapper;

import com.ext.portlet.contests.ContestPhaseHelper;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.icesoft.faces.component.datapaginator.DataPaginator;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class PlansIndexBean {

    private List<Columns> columns;

    private String sortColumn = "NAME";
    private boolean sortAscending = true;
    private boolean updatePlansList = true;
    private int pageSize = 50;
    // Current items in ui
    private List uiCustomerBeans = new ArrayList(pageSize);

    private PagedListDataModel plansDataModel;



    private ContestPhaseWrapper contestPhase;

    private ConfigureColumnsBean columnsConfiguration;

    private FilterPlansBean filterPlansBean;
    private PlansUserSettings plansUserSettings;

    private Long userVote;

    private boolean findUserVote;
    private boolean updateErrorNotes;

    private List<PlanItem> notFilteredPlans = new ArrayList<PlanItem>();
    private List<PlanIndexItemWrapper> plans = new ArrayList<PlanIndexItemWrapper>();

    private DataPaginator dataPaginator;
    private List<Debate> availableDebates;
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();

    private PlanTypeIndexBean contestIndexBean;

    private List<TestTab> testTabs = Arrays.asList(new TestTab("one"),new TestTab("two"),new TestTab("three"));

    private int tabindex = 0;

    private static final String CONTEST_PHASE_SESSION_PARAM = "CONTEST_PHASE_SESSION_PARAM";
    private final static String PLANS_SOURCE = "plans"; 
    private final static String VIEW_PROPOSALS_PARAM = "viewProposals";
    private final static String ACTIVE_PROPOSALS = "active";
    private final static String PREVIOUS_PROPOSALS = "previous";
    
    private static Log _log = LogFactoryUtil.getLog(PlansIndexBean.class);
    private boolean showPreviousProposals = false;

    private boolean showProposalsThatUserOwns;

    private boolean showProposalsThatUserSupports;

    public PlansIndexBean(ContestPhaseWrapper contestPhaseWrapper) throws PortalException, SystemException {
        this.contestPhase = contestPhaseWrapper;
        dataPaginator = new DataPaginator();
        
        updatePlansList = true;
        filterPlansBean = null;
        columnsConfiguration = null;
        columnsUpdate();
    }


    public void init(ContestPhaseWrapper currentPhase, NavigationEvent event) throws PortalException, SystemException {
        boolean isDirty = false;
        
        if (currentPhase == null) {
            contestPhase = null;
            return;
        }
        
        if (contestPhase !=null && contestPhase.getPhaseId().equals(currentPhase.getPhaseId())) {
            // ignore
        }
        else {
            contestPhase = currentPhase;
            isDirty = true;
        }

        Map<String, String> params = event.getParameters(PLANS_SOURCE);
        if (params != null && params.containsKey(VIEW_PROPOSALS_PARAM)) {
            boolean tmp = params.get(VIEW_PROPOSALS_PARAM).equals(PREVIOUS_PROPOSALS);
            if (tmp != showPreviousProposals) {
                isDirty = true;
                showPreviousProposals = tmp;

                if (showPreviousProposals) {
                    contestPhase = new ContestPhaseWrapper(contestPhase.getContest(), contestPhase.getPhase().getPreviousPhases().get(0));
                }
            }
        }
        else {
            showPreviousProposals = false;
        }
        
        if (isDirty) {
            refresh();
            sortColumn = PlanConstants.Attribute.SUPPORTERS.name();
            sortAscending = false;
        }
    }

    public ContestPhaseWrapper getContestPhase() {
        return contestPhase;
    }

    public List<ContestPhaseWrapper> getContestPhases() {
        List<ContestPhaseWrapper> result = contestPhase == null?Collections.<ContestPhaseWrapper>emptyList():contestPhase.getContest().getPhases();
        return result;
    }

    public int getContestPhaseIndex() {
        int result =  contestPhase == null?-1:contestPhase.getContest().getPhases().indexOf(contestPhase);
        return result;
    }


    /*public void setContestPhaseIndex(int i) throws SystemException, PortalException {
        if (contestPhase!=null) init(contestPhase.getContest().getPhases().get(i).getPhaseId(), Collections.<String, String>emptyMap());
    }
    */
    
    public void setEventBus(EventBus eventBus) {
        if (this.eventBus != eventBus) {
            this.eventBus = eventBus;
            bindEvents();
        }
    }

    public List<TestTab> getTabs() {
        return testTabs;
    }

    public int getTabIndex() {
        return tabindex;
    }

    public void setTabIndex(int idx) {
        this.tabindex = idx;    
    }




    private void bindEvents() {
        for (HandlerRegistration registration: handlerRegistrations) {
            registration.unregister();
        }

         handlerRegistrations.clear();
        
        handlerRegistrations.add(eventBus.registerHandler(PlanDeletedEvent.class, new EventHandler<PlanDeletedEvent>() {

            @Override
            public void onEvent(PlanDeletedEvent event) {
                try {
                    PlansIndexBean.this.refresh();
                } catch (Exception e) {
                    // ignore
                }
            }
            
        }));
    }

    public List<PlanIndexItemWrapper> getPlans() throws SystemException, PortalException {
        if (contestPhase == null) {
            return null;
        }
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        availableDebates = PlansPreferencesBean.getQuestionDebates();
        if (updatePlansList) {
            plans.clear();
            updatePlansList = false;
            Columns sortCol = Columns.valueOf(sortColumn);

            String sortAttribute = Attribute.VOTES.name();
            if (sortCol.isSortable()) {
                sortAttribute = sortCol.getSortAttribute().name();
            }
            if (showPreviousProposals) {
                List<ContestPhase> previousPhases = contestPhase.getPhase().getPreviousPhases();
                notFilteredPlans = PlanItemLocalServiceUtil.getPlans(ectx.getSessionMap(), ectx.getRequestMap(), null, previousPhases, 0, 1000, sortAttribute, sortAscending ? "ASC" : "DESC", false);
            }
            else {
                notFilteredPlans = PlanItemLocalServiceUtil.getPlans(ectx.getSessionMap(), ectx.getRequestMap(), null, contestPhase.getPhase(), 0, 1000, sortAttribute, sortAscending ? "ASC" : "DESC", false);
            }

            for(PlanItem plan: PlanItemLocalServiceUtil.applyFilters(ectx.getSessionMap(), ectx.getRequestMap(), contestPhase.getPhase().getContest().getPlanType(), notFilteredPlans)) {
                if (Helper.isUserLoggedIn()) {

                    final Long userId = Helper.getLiferayUser().getUserId();
                    if (showProposalsThatUserOwns) {
                        System.out.println("userId: " + userId + "\t" + plan.getAuthorId());
                        if (! plan.getAuthorId().equals(userId)) {
                            continue;
                        }
                    }
                    if (showProposalsThatUserSupports) {
                        if (! plan.isUserAFan(userId)) {
                            continue;
                        }
                    }

                }
                plans.add(new PlanIndexItemWrapper(plan, this, availableDebates));
            }
            updateErrorNotes = true;
        }
        return plans;
    }

    public List<Columns> getColumns() {
        return columns;
    }

    public DataPaginator getDataPaginator() {
        return dataPaginator;
    }
    public void setDataPaginator(DataPaginator dataPaginator) {
        this.dataPaginator = dataPaginator;
    }


    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
        updatePlansList = true;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
        updatePlansList = true;
    }


    public void filtersUpdate() {
        updatePlansList = true;
    }


    public ConfigureColumnsBean getColumnsConfiguration() throws SystemException, PortalException {
        if (columnsConfiguration == null) {
            columnsConfiguration = new ConfigureColumnsBean(this);
        }
        return columnsConfiguration;
    }

    public FilterPlansBean getFilterPlansBean() throws PortalException, SystemException {
        if (filterPlansBean == null) {
            filterPlansBean = new FilterPlansBean(this);
        }
        return filterPlansBean;
    }

    private void columnsUpdate() throws PortalException, SystemException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        plansUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), contestPhase.getPlanType());
        columns = new ArrayList<Columns>();
        for (Columns col: ContestPhaseHelper.getPhaseColumns(contestPhase.getPhase())) {
            /*if (col.getUserSetting(plansUserSettings)) {
                columns.add(col);
            }
            */
            columns.add(col);
        }
        updatePlansList = true;
    }



    public void refresh() throws PortalException, SystemException {
        updatePlansList = true;
        filterPlansBean = null;
        columnsConfiguration = null;
        columnsUpdate();
        getPlans();
    }

    public int getVotesCount() throws SystemException, PortalException {
        return PlanVoteLocalServiceUtil.countPlanVotes(contestPhase.getPlanType());
    }

    public int getPlansCount() {
        return notFilteredPlans.size();
    }

    public void findUserVote(ActionEvent e) throws PortalException, SystemException {
        findUserVote = true;
        refresh();
    }

    public boolean isFindUserVote() {
        if (findUserVote) {
            findUserVote = false;
            return true;
        }
        return false;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean getHasUserVoted() throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            try {
                PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(Helper.getLiferayUser().getUserId());
                return vote != null;
            }
            catch (NoSuchPlanVoteException e) {
            }
        }
        return false;
    }

    public boolean isUpdateErrorNotes() {
        if (updateErrorNotes) {
            updateErrorNotes = false;
            return true;
        }
        return updateErrorNotes;
    }

    public static class TestTab {

        String label;

        public TestTab(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
    
    public void clear() {
        contestPhase = null;
    }

     public boolean isShowPreviousProposals() {
         return showPreviousProposals;
     }
     
     public boolean isShowProposalsThatUserOwns() {
         return showProposalsThatUserOwns;
     }
     
     public boolean isShowProposalsThatUserSupports() {
         return showProposalsThatUserSupports;
     }
     
     public void setShowProposalsThatUserOwns(boolean show) throws PortalException, SystemException {
         showProposalsThatUserOwns = show;
         refresh();
     }
     
     public void setShowProposalsThatUserSupports(boolean show) throws PortalException, SystemException {
         showProposalsThatUserSupports = show;
     }

}
