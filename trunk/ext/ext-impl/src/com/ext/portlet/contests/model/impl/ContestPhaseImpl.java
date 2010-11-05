package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhaseColumn;
import com.ext.portlet.contests.model.ContestStatus;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseColumnLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContestPhaseImpl extends ContestPhaseModelImpl
    implements ContestPhase {
    public ContestPhaseImpl() {
    }

    public Contest getContest() throws SystemException, PortalException {
        return ContestLocalServiceUtil.getContest(this.getContestPK());
    }

    public List<PlanItem> getPlans() throws SystemException, PortalException {
        return PlanItemLocalServiceUtil.getPlans(Collections.emptyMap(),Collections.emptyMap(),null,this,0,Integer.MAX_VALUE,"","",false);
        //return PlanItemLocalServiceUtil.getPlansInContestPhase(this);
    }

    public ContestStatus getContestStatus() {
        String status = getContestPhaseStatus();
        return status == null?null:ContestStatus.valueOf(status);
    }
    
    public List<String> getPhaseColumns() throws SystemException {
        List<String> ret = new ArrayList<String>();
        for (ContestPhaseColumn phaseColumn: ContestPhaseColumnLocalServiceUtil.getPhaseColumns(getContestPhasePK())) {
            ret.add(phaseColumn.getColumnName());
        }
        return ret;
    }
    

    public List<ContestPhase> getPreviousPhases() throws SystemException, PortalException {
        List<ContestPhase> phases = ContestPhaseLocalServiceUtil.getPhasesForContest(getContest());
        List<ContestPhase> ret = new ArrayList<ContestPhase>();
        for (ContestPhase phase: phases) {
            if (phase.getPhaseStartDate().before(getPhaseStartDate())) {
                ret.add(phase);
            }
        }
        return ret;
    }
    
    public ContestPhase getNextContestPhase() throws SystemException, PortalException {
        boolean currentFound = false;
        for (ContestPhase phase: ContestPhaseLocalServiceUtil.getPhasesForContest(getContest())) {
            if (currentFound) {
                return phase;
            }
            if (phase.getContestPhasePK().equals(getContestPhasePK())) {
                currentFound = true;
            }
        }
        throw new SystemException("Can't find next phase for phase with id: " + getContestPhasePK());
    }
    
}
