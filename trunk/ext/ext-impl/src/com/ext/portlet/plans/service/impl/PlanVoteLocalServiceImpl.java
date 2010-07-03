/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.service.impl;

import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanVoteLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class PlanVoteLocalServiceImpl extends PlanVoteLocalServiceBaseImpl {
    
    public void voteForPlan(Long planId, Long userId) throws SystemException {
        PlanVote vote = null;
        try {
            vote = planVotePersistence.findByuserId(userId);
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
        if (vote == null) {
            vote = createPlanVote(userId);
        }
        vote.setPlanId(planId);
        vote.store();
    }
    
    public void unvote(Long userId) throws SystemException, PortalException {
        try {
            PlanVote vote = planVotePersistence.findByuserId(userId);
            PlanVoteLocalServiceUtil.deletePlanVote(vote);
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
    }
}
