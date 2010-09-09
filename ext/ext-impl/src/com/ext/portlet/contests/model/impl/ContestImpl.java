package com.ext.portlet.contests.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestDebate;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestDebateLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class ContestImpl extends ContestModelImpl implements Contest {
    public ContestImpl() {
    }

    public List<ContestPhase> getPhases() throws SystemException {
        return ContestPhaseLocalServiceUtil.getPhasesForContest(this);
    }

    public PlanType getPlanType() throws SystemException, PortalException {
        return PlanTypeLocalServiceUtil.getPlanType(getPlanTypeId());
    }

    public List<ContestPhase> getActivePhases() throws SystemException {
        List<ContestPhase> result = getPhases();
        for (Iterator<ContestPhase> i=result.iterator();i.hasNext();) {
           if (!i.next().getContestStatus().isCanEdit()) {
               i.remove();
           }
        }
        return result;
    }
    
    public ContestPhase getActivePhase() throws NoSuchContestPhaseException, SystemException {
        return ContestPhaseLocalServiceUtil.getActivePhaseForContest(this);
    }
    
    public boolean isActive() throws SystemException {
        try {
            ContestPhaseLocalServiceUtil.getActivePhaseForContest(this);
            return true;
        }
        catch (NoSuchContestPhaseException e) {
            // ignore
        }
        return false;
    }
    
    public List<Long> getDebatesIds() throws SystemException  {
        List<Long> ret = new ArrayList<Long>();
        for (ContestDebate pos: ContestDebateLocalServiceUtil.getContestDebates(getContestPK())) {
            ret.add(pos.getDebateId());
        }
        return ret;
    }
    
    public void setDebates(List<Long> debatesIds) throws SystemException  {
        for (ContestDebate pos: ContestDebateLocalServiceUtil.getContestDebates(getContestPK())) {
            pos.delete();
        }   
        for (Long debatesId: debatesIds) {
            ContestDebateLocalServiceUtil.createContestDebate(debatesId, getContestPK());
        }
    }

}
