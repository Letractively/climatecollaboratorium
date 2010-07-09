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
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanVoteLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class PlanVoteLocalServiceImpl extends PlanVoteLocalServiceBaseImpl {
    /**
     * Votes for a plan, if user has already voted for given plan returns false, true otherwise.
     */
    public boolean voteForPlan(Long planId, Long userId) throws SystemException, PortalException {
        try {
            PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(userId);
            if (vote.getPlanId() == planId) {
                return false;
            }
            try {
                PlanItem plan = PlanItemLocalServiceUtil.getPlan(vote.getPlanId());
                plan.unvote(userId);
            }
            catch (NoSuchPlanItemException e) {
                // ignore
            }
            deletePlanVote(userId);
        }
        catch (NoSuchPlanVoteException e) {
            // ignore
        }
        PlanVote vote = createPlanVote(userId);
        vote.setPlanId(planId);
        vote.setCreateDate(new Date());
        addPlanVote(vote);
        return true;
    }
    
    public boolean unvote(Long userId) throws SystemException, PortalException {
        try {
            PlanVoteLocalServiceUtil.deletePlanVote(userId);
            return true;
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
        return false;
    }
}
