package com.ext.portlet.contests.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestDebate;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestDebateLocalServiceUtil;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.debaterevision.util.Indexer;
import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchException;


public class ContestImpl extends ContestModelImpl implements Contest {
    private final static Log _log = LogFactoryUtil.getLog(ContestImpl.class);
    
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
        // refresh search index as debate items might have been referenced differently now
        try {
            DebateItemLocalServiceUtil.reIndex();
        } catch (SearchException e) {
            _log.error("Exception was thrown when reindexing debate items", e);
        }
    }
    
    public List<Debate> getDebates() throws SystemException {
        List<Debate> ret = new ArrayList<Debate>();
        for (ContestDebate pos: ContestDebateLocalServiceUtil.getContestDebates(getContestPK())) {
            ret.add(DebateLocalServiceUtil.findLastVersion(pos.getDebateId()));
        }
        return ret;
    }
    
    public Integer getTotalVotes() throws SystemException {
        return PlanVoteLocalServiceUtil.countPlanVotes(this);
    }
    
    public void updateDefaultPlanDescription(String description) throws SystemException {
        this.setDefaultPlanDescription(description);
        ContestLocalServiceUtil.updateContest(this);
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            if (getContestPK() == null) {
                setContestPK(CounterLocalServiceUtil.increment(Contest.class.getName()));
            }
            ContestLocalServiceUtil.addContest(this);
        }
        else {
            ContestLocalServiceUtil.updateContest(this);
        }
    }
    
    public PlanTemplate getPlanTemplate() throws PortalException, SystemException {
        return PlanTemplateLocalServiceUtil.getPlanTemplate(getPlanTemplateId());
    }
    
    public FocusArea getFocusArea() throws PortalException, SystemException {
        return FocusAreaLocalServiceUtil.getFocusArea(getFocusAreaId());
    }

}
