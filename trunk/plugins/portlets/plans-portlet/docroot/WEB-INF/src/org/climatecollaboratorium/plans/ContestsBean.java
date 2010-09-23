package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.List;

import org.climatecollaboratorium.plans.wrappers.ContestWrapper;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.SystemException;

public class ContestsBean {
    private List<ContestWrapper> contests;
    
    
    public List<ContestWrapper> getContests() throws SystemException {
        if (contests == null) {
            contests = new ArrayList<ContestWrapper>();
            for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
                contests.add(new ContestWrapper(contest));
            }
        }
        
        return contests;
    }

}
