package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.icesoft.faces.context.BridgeExternalContext;
import com.liferay.portal.SystemException;
import com.liferay.portal.PortalException;
import com.liferay.portal.util.PortalUtil;

public class ContestsBean {
    private List<ContestWrapper> contests = new ArrayList<ContestWrapper>();
    private List<ContestWrapper> contestsPart1;
    private List<ContestWrapper> contestsPart2;
    private List<ContestWrapper> contestsFeatured;
    private List<ContestWrapper> contestsNormal;
    private EventBus eventBus;
    private ViewType viewType = ViewType.GRID;
    
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
                ContestWrapper contestWrapper = new ContestWrapper(contest);
                if (eventBus != null) {
                    contestWrapper.setEventBus(eventBus);
                }
                contests.add(contestWrapper);
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
            contestsPart1 = new ArrayList<ContestWrapper>();
            contestsPart2 = new ArrayList<ContestWrapper>();
            contestsFeatured = new ArrayList<ContestWrapper>();
            contestsNormal = new ArrayList<ContestWrapper>();
            
            boolean addToFirst = true;
            
            for (ContestWrapper c: getContests()) {
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
    
    
    public static enum ViewType {
        GRID,
        LIST;
        
        public String getLowerCase() {
            return name().toLowerCase();
        }
    }

}
