/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.service.impl;

import java.util.Date;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanVoteLocalServiceBaseImpl;
import com.ext.portlet.plans.service.persistence.PlanItemFinderUtil;
import com.ext.portlet.plans.service.persistence.PlanVotePK;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class PlanVoteLocalServiceImpl extends PlanVoteLocalServiceBaseImpl {
    /**
     * Votes for a plan, if user has already voted for given plan returns false, true otherwise.
     */
    public boolean voteForPlan(Long planId, Long userId) throws SystemException, PortalException {
        PlanItem plan = PlanItemLocalServiceUtil.getPlan(planId);
        PlanVotePK votePk = new PlanVotePK(userId, plan.getContest().getContestPK());
        try {
            PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(votePk);
            PlanVotePK oldVotePk = new PlanVotePK();
            oldVotePk.userId = userId;
            if (vote.getPlanId() == planId) {
                return false;
            }
            try {
                PlanItem planOldVote = PlanItemLocalServiceUtil.getPlan(vote.getPlanId());
                oldVotePk.setContestId(planOldVote.getContest().getContestPK());
                plan.unvote(userId);
            }
            catch (NoSuchPlanItemException e) {
                // ignore
            }
            deletePlanVote(oldVotePk);
        }
        catch (NoSuchPlanVoteException e) {
            // ignore
        }
        PlanVote vote = createPlanVote(votePk);
        vote.setPlanId(planId);
        vote.setCreateDate(new Date());
        addPlanVote(vote);
        
        return true;
    }
    
    public boolean unvote(Long userId, Long contestId) throws SystemException, PortalException {
        try {
            PlanVotePK votePk = new PlanVotePK(userId, contestId);
            PlanVoteLocalServiceUtil.deletePlanVote(votePk);
            return true;
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
        return false;
    }
    
    public PlanVote getPlanVote(Long userId, Long contestId) throws SystemException, PortalException {
        PlanVotePK votePk = new PlanVotePK(userId, contestId);
        return getPlanVote(votePk);
    }
    
    public int coutPlanVotes(Long planId) throws SystemException {
        return planVotePersistence.countByPlanId(planId);   
    }

    public int countPlanVotes(PlanType type) throws SystemException {
        return PlanItemFinderUtil.countVotesForPlanType(type);
    }
}
