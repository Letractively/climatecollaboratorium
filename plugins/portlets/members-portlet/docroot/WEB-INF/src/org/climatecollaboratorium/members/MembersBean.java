package org.climatecollaboratorium.members;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;


import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.login.util.LoginUtil;

public class MembersBean {
    
    private int startFrom = 0;
    private int RESULTS_PER_PAGE = 25;
    private final static long DEFAULT_COMPANY_ID = 10112L;
    private String sortColumnStr;
    private MembersListColumns sortColumn = MembersListColumns.MEMBER_SINCE;
    private boolean sortAscending = false;
    private String searchPhrase = "";
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    

    private MemberCategory categoryFilter = null;
    
    /**
     * Represents a mapping from member category to a role that represents this category. 
     * This is used for filtering when doing user search by category.
     */
    private final Map<MemberCategory, Role> categoryRoleMap;
    
    private List<MemberListItemBean> searchResults = new ArrayList<MemberListItemBean>();
    
    // bound to rows attribute in dataTable
    protected int pageSize = 25;
    private final static Log _log = LogFactoryUtil.getLog(MembersBean.class);
    
    public MembersBean() throws SystemException, PortalException, NumberFormatException, ParseException {
        categoryRoleMap = new HashMap<MemberCategory, Role>();

        Object filterObj = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("filter");
        if (filterObj != null) {
            try {
                categoryFilter = MemberCategory.valueOf(filterObj.toString());
            }
            catch (Exception e) {
                // ignore
            }
        }
        for (MemberCategory category: MemberCategory.values()) {
            try {
                categoryRoleMap.put(category, RoleLocalServiceUtil.getRole(DEFAULT_COMPANY_ID, category.getRoleName()));
            }
            catch (com.liferay.portal.NoSuchRoleException e) {
                _log.warn("Can't find role for user category: " + category.name());
            }
        }
        
        updateSearchResults();
    }

    public int getStartFrom() {
        return startFrom;
    }
    
    public int getResultsPerPage() {
        return RESULTS_PER_PAGE;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public List<MemberListItemBean> getData() {
        return searchResults;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
        sortSearchResults();
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumnStr = sortColumn;
        try {
            this.sortColumn = MembersListColumns.valueOf(sortColumn);
        }
        catch (Exception e) {
            // ignore
        }
        sortSearchResults();
    }

    public String getSortColumn() {
        return sortColumnStr;
    }

    public void setSearchPhrase(String searchPhrase) throws SystemException, NumberFormatException, PortalException, ParseException {
        this.searchPhrase = searchPhrase;
        updateSearchResults();
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }
    
    public void doSearch(ActionEvent e) throws SystemException, NumberFormatException, PortalException, ParseException {
        updateSearchResults();
    }
    
    public void updateCategoryFilter(ActionEvent e) throws SystemException, NumberFormatException, PortalException, ParseException {
        MemberCategory category = null;
        try {
            category = MemberCategory.valueOf(e.getComponent().getAttributes().get("category").toString());
        }
        catch (Exception ex) {
            // ignore, enum value parsing error
        }
        
        if (category != null && ! category.equals(categoryFilter)) {
            categoryFilter = category;
            updateSearchResults();
        }
        
    }

    private void updateSearchResults() throws SystemException, NumberFormatException, PortalException, ParseException {
      searchResults.clear();
      
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
        
        // apply category filters
        if (categoryFilter != null) {
            params.put("memberCategory", categoryFilter.name());
        }
        
        Sort sorting = new Sort();
        Hits hits = UserLocalServiceUtil.search(DEFAULT_COMPANY_ID, searchPhrase, true, params, 0, Integer.MAX_VALUE, sorting);
        
        
        for (Document userDoc: hits.getDocs()) {
            if (categoryFilter != null && !categoryFilter.equals(MemberCategory.MEMBER)) {
                // user has enabled category filter, show results from given category and mark them as
                // from that category
                String[] categories = userDoc.getValues("memberCategory");
                for (String s: categories) {
                    if (s.equals(categoryFilter.name())) {
                        searchResults.add(new MemberListItemBean(userDoc, categoryFilter));
                        break;
                    }
                }
            }
            else {
                // autodetect member category
                searchResults.add(new MemberListItemBean(userDoc));
            }
        }
        
        sortSearchResults();
       
        
    }
    
    public int getUsersCount() {
        return searchResults.size();
    }
    
    public boolean isHasMembers() {
        return searchResults.size() > 0;
    }
    
    /**
     * Sorting search results is done manually and not with lucene because we can't track
     * user activity count with lucene. 
     */
    private void sortSearchResults() {
        Collections.sort(searchResults, new Comparator<MemberListItemBean>() {
            @Override
            public int compare(MemberListItemBean o1, MemberListItemBean o2) {
                Comparable o1Val = o1.getColumnVal(sortColumn);
                Comparable o2Val = o2.getColumnVal(sortColumn);
                int ret = 0;
                if (o1Val instanceof String && o2Val instanceof String) {
                    ret = ((String) o1Val).compareToIgnoreCase((String) o2Val);
                }
                else {
                    ret = o1Val.compareTo(o2Val);
                }
                
                return sortAscending ? ret : -ret;
            }
        });
    }

    public MemberCategory getCategoryFilter() {
        return categoryFilter;
    }
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bind();
    }
    
    private void bind() {
        for (HandlerRegistration reg: handlerRegistrations) {
            reg.unregister();
        }
        
        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                if (event.hasSource("members")) {
                    try {
                        String filter = event.getParameters("members").get("filter");
                        
                        if (filter != null) {
                            MemberCategory filterCat = MemberCategory.valueOf(filter);
                            if (filterCat != categoryFilter) {
                                categoryFilter = filterCat;
                                updateSearchResults();
                            }
                        }
                    } catch (Exception e) {
                        _log.error("error when trying to filter users", e);
                        
                    }
                }
            }
            
        }));
        
    }
}
