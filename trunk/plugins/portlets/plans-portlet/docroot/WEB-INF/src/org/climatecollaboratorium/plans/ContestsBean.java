package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.List;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.PortalException;

public class ContestsBean {
    private List<ContestWrapper> contests = new ArrayList<ContestWrapper>();
    private EventBus eventBus;
    
    
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
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        for (ContestWrapper contest: contests) {
            contest.setEventBus(eventBus);
        }
        
    }

}
