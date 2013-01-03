/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans.wrappers;

import com.ext.portlet.contests.NoSuchContestPhaseStatusException;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.model.ContestStatus;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseStatusLocalServiceUtil;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanType;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 6, 2010
 * Time: 2:53:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestPhaseWrapper {
    private ContestPhase phase;
    private ContestWrapper contestWrapper;
    private boolean last;

    public ContestPhaseWrapper(ContestWrapper wrapper, ContestPhase phase, boolean last) {
        this.phase = phase;
        this.contestWrapper = wrapper;
        this.last = last;
    }

    public ContestPhase getPhase() {
        return phase;
    }

    public PlanType getPlanType() throws SystemException, PortalException {
        return phase.getContest().getPlanType();
    }

    public String getName() {
        return phase.getContestPhaseName();
        //return "fooey";
    }

    public Date getStartDate() {
        return phase.getPhaseStartDate();
    }

    public Date getEndDate() {
       return phase.getPhaseEndDate();
       // return new Date();
    }
    
    public boolean isAlreadyStarted() {
    	return phase.getPhaseStartDate().before(new Date());
    }

    public ContestStatus getStatus() {
        return phase.getContestStatus();
    }

    public boolean getCanVote() {
        return getStatus().isCanVote();
    }

    public boolean getCanEdit() {
        return getStatus().isCanEdit();
    }

    public Long getPhaseId() {
        return phase.getContestPhasePK();
    }

    public ContestWrapper getContest() {
        return contestWrapper;
    }
    
    public String getDescription() {
        return phase.getContestPhaseDescription();
    }

    public List<PlanItem> getPlans() throws SystemException, PortalException {

        try {
            List<PlanItem> result =  phase.getPlans();
            return result;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return Collections.emptyList();
    }

   
    public boolean isFirstPhase() {
        for (ContestPhaseWrapper phase: contestWrapper.getPhases()) {
            if (phase.getStartDate().compareTo(getStartDate()) < 0) {
                return false ;
            }
        }
        return true;
    }
    
    public boolean isActive() {
        return phase.getPhaseActive();
    }
    

    
    public boolean getHasNextPhase() {
        boolean hasNext = false;
        boolean currentFound = false;
        for (ContestPhaseWrapper phase: contestWrapper.getPhases()) {
            if (currentFound) {
                hasNext = true;
            }
            if (phase.getPhaseId().equals(this.phase.getContestPhasePK())) {
                currentFound = true;
            }
        }
        return hasNext;
    }
    
    public long getDaysTillEnd() {
        //Interval i = new Interval(arg0, arg1)
        long nowTime = new Date().getTime();
        if (phase.getPhaseEndDate() != null) {
            long endTime = phase.getPhaseEndDate().getTime();
            return (endTime - nowTime) / (24 * 60 * 60 * 1000);
        }
        return 0;
    }
    
    public long getHoursTillEnd() {
        //Interval i = new Interval(arg0, arg1)
        long nowTime = new Date().getTime();
        if (phase.getPhaseEndDate() != null) {
            long endTime = phase.getPhaseEndDate().getTime();
            return (endTime - nowTime) / (60 * 60 * 1000);
        }
        return 0;
    }
    
    public long getMinutesTillEnd() {
        //Interval i = new Interval(arg0, arg1)
        long nowTime = new Date().getTime();
        if (phase.getPhaseEndDate() != null) {
            long endTime = phase.getPhaseEndDate().getTime();
            return (endTime - nowTime) / (60 * 1000);
        }
        return 0;
    }
    
    public boolean isLast() {
    	return last;
    }
    
    public boolean isEnded() {
    	return phase.getPhaseEndDate().before(new Date());
    }
    
    public String getPhaseStatusDescription() throws PortalException, SystemException {
    	if (StringUtils.isBlank(phase.getPhaseStatusDescription())) {
    		try {
    			return ContestPhaseStatusLocalServiceUtil.getContestPhaseStatus(phase.getContestPhaseStatus()).getDescription();
    		}
    		catch (NoSuchContestPhaseStatusException e) {
    			// ignore
    		}
    		return null;
    	}
    	return phase.getPhaseStatusDescription();
    }
    
}
