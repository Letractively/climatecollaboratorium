package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;
import org.compass.core.util.backport.java.util.Collections;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class ContestsBean {
    private List<ContestWrapper> contests = new ArrayList<ContestWrapper>();
    private List<ContestWrapper> contestsPart1;
    private List<ContestWrapper> contestsPart2;
    private List<ContestWrapper> contestsFeatured;
    private List<ContestWrapper> contestsNormal;
    private EventBus eventBus;
    private ViewType viewType = ViewType.GRID;
    private String filterString;
    
    private SortColumn sortColumn = null;
    
    private boolean sortAsc = true;
    private boolean showActiveOnly = true;
    public final static String PLANS_SOURCE = "plans";
    private static final String CONTEST_FILTER_PARAM = "contestFilter";
    
    
    public ContestsBean() {
        
        String viewTypeStr = Helper.getCookieValue("cc_contests_viewType");
        if (viewType != null) {
            try {
                viewType = ViewType.valueOf(viewTypeStr);
            }
            catch (Exception e) {
                // ignore
            }
        }
        
    }
    
    
    public List<ContestWrapper> getContests() throws SystemException, PortalException {
        if (contests.size() == 0) {
            for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
                boolean add = true;
                if (filterString != null) {
                    if (contest.getContestShortName().toLowerCase().contains(filterString) || 
                            contest.getContestName().toLowerCase().contains(filterString)) {
                        add = true;
                    
                    }
                    else {
                        add = false;
                        
                    }
                }
                add &= !(showActiveOnly ^ contest.isActive());
                if (add) {
                    ContestWrapper contestWrapper = new ContestWrapper(contest);
                    if (eventBus != null) {
                        contestWrapper.setEventBus(eventBus);
                    }
                    contests.add(contestWrapper);
                }
            }
            if (sortColumn != null) {
                Collections.sort(contests, sortColumn);
                if (sortAsc) Collections.reverse(contests);
            }
        }
        
        return contests;
    }
    
    public List<ContestWrapper> getContestsPart1() throws SystemException, PortalException {
        if (contestsPart1 == null) {
            splitContestsIntoParts();
        }
        return contestsPart1;
    }
    
    public List<ContestWrapper> getContestsPart2() throws SystemException, PortalException {
        if (contestsPart2 == null) {
            splitContestsIntoParts();
        }
        return contestsPart2;
    }
    
    public List<ContestWrapper> getContestsFeatured() throws SystemException, PortalException {
        if (contestsPart2 == null) {
            splitContestsIntoParts();
        }
        return contestsFeatured;
    }


    public List<ContestWrapper> getContestsNormal() throws SystemException, PortalException {
        if (contestsPart2 == null) {
            splitContestsIntoParts();
        }
        return contestsNormal;
    }


    private void splitContestsIntoParts() throws SystemException, PortalException {
        if (contestsPart1 == null || contestsPart2 == null) {
            List<ContestWrapper> contests = getContests();
            contestsPart1 = new ArrayList<ContestWrapper>();
            contestsPart2 = new ArrayList<ContestWrapper>();
            contestsFeatured = new ArrayList<ContestWrapper>();
            contestsNormal = new ArrayList<ContestWrapper>();
            
            boolean addToFirst = true;
            
            for (ContestWrapper c: contests) {
                if (addToFirst) contestsPart1.add(c);
                else contestsPart2.add(c);
                addToFirst = !addToFirst;
                
                if (c.isFeatured()) contestsFeatured.add(c);
                else contestsNormal.add(c);
            }
        }
    }


    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        for (ContestWrapper contest: contests) {
            contest.setEventBus(eventBus);
        }
    }
    
    public void setViewType(ActionEvent e) {
        Object newViewTypeObj = e.getComponent().getAttributes().get("viewType");
        if (newViewTypeObj != null) {
            try {
                viewType = ViewType.valueOf(newViewTypeObj.toString());
            }
            catch (Exception ex) {
                // ignore
            }
        }
    }
    
    public ViewType getViewType() {
        return viewType;
    }
    
    private void clearContestsList() {

        contestsPart1 = null;
        contestsPart2 = null;
        contestsFeatured = null;
        contestsNormal = null;
        contests.clear();
    }
    
    
    public void setFilterString(String filter) {
        this.filterString = filter.trim().length() == 0 ? null : filter.trim();
        clearContestsList();
    }


    public String getFilterString() {
        return filterString;
    }
    
    public void changeSortColumn(ActionEvent e) {
        SortColumn nSortColumn = SortColumn.valueOf(e.getComponent().getAttributes().get("sortColumn").toString());
        if (sortColumn == nSortColumn) sortAsc = !sortAsc;
        else sortColumn = nSortColumn;
        
        clearContestsList();
    }
    

    public void setShowActiveOnly(boolean showActiveOnly) {
        this.showActiveOnly = showActiveOnly;
        clearContestsList();
    }
    
    public void toggleActiveInactive(ActionEvent e) {
        showActiveOnly = !showActiveOnly;
        clearContestsList();
    }


    public boolean isShowActiveOnly() {
        return showActiveOnly;
    }


    public static enum ViewType {
        GRID,
        LIST;
        
        public String getLowerCase() {
            return name().toLowerCase();
        }
    }
    
    public static enum SortColumn implements Comparator<ContestWrapper> {
        CONTEST_NAME,
        PROPOSALS,
        COMMENTS,
        WHO,
        WHERE,
        WHAT
        ;

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            String w1 = ""; 
            String w2 = "";
            try {
                switch (this) {
                    case CONTEST_NAME:
                        return o1.getContest().getContestShortName().compareTo(o2.getContest().getContestShortName());
                    case PROPOSALS:
                        return (int) (o1.getProposalsCount() - o2.getProposalsCount());
                    case COMMENTS:
                        return (int) (o1.getCommentsCount() - o2.getCommentsCount());
                    case WHO:
                        w1 = o1.getWho() == null ? "" : o1.getWho().getName();
                        w2 = o2.getWho() == null ? "" : o2.getWho().getName();
                    
                        return w1.compareTo(w2);

                    case WHAT:
                        w1 = o1.getWhat() == null ? "" : o1.getWhat().getName();
                        w2 = o2.getWhat() == null ? "" : o2.getWhat().getName();
                    
                        return w1.compareTo(w2);

                    case WHERE:
                        w1 = o1.getWho() == null ? "" : o1.getWho().getName();
                        w2 = o2.getWho() == null ? "" : o2.getWho().getName();
                    
                        return w1.compareTo(w2);
                }
            
            }
            catch (Exception e) {
                // ignore
            }
            return o1.hashCode() - o2.hashCode();
                        
            
        }
        
    }

    public void init(NavigationEvent event) {
        Map<String, String> parameters = event.getParameters(PLANS_SOURCE);
        if (parameters == null || ! parameters.containsKey(CONTEST_FILTER_PARAM)) {
            filterString = null;
            clearContestsList();
            return;
        }
        filterString = parameters.get(CONTEST_FILTER_PARAM).trim();
        clearContestsList();
        
    }

}
