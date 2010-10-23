/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.action;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.plans.PlanActivityKeys;
import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanVotePK;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

/**
 * Class responsible for handling Plan Vote action.
 *
 * @author janusz.p
 * @version 1.0
 */
public class PlanVoteAction extends ViewPlansAction {
    /**
     * Processes plan vote action (either plan voting or unvoting).
     *
     *  @param mapping action mapping
     *  @param form action form
     *  @param portletConfig portlet config
     *  @param actionRequest action request
     *  @param actionResponse action response
     *  @throws Exception in case of any error
     */
    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        String updateType = ParamUtil.getString(actionRequest, PlanConstants.UPDATE_TYPE);
        long planId = ParamUtil.getLong(actionRequest, PlanConstants.PLAN_ID);
       
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();
        PlanVote currentVote = null;
        try {
            currentVote = PlanVoteLocalServiceUtil.getPlanVote(userId, 1L);
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
        boolean switchvote=false;
        // decrease votes count for current plan
        if (currentVote != null) {
            Plan plan = PlanLocalServiceUtil.getPlan(currentVote.getPlanId());
            plan.setVotes(plan.getVotes() - 1);
            PlanLocalServiceUtil.updatePlan(plan);
            if (updateType.equals(PlanConstants.VOTE))
            {
            	SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", planId,PlanActivityKeys.SWICTH_VOTE_FOR_PLAN.id(), StringPool.BLANK, 0);
            }
        }
        else if (updateType.equals(PlanConstants.VOTE)) 	
        {
        	SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", planId,PlanActivityKeys.VOTE_FOR_PLAN.id(), StringPool.BLANK, 0);
        }
        if (updateType.equals(PlanConstants.VOTE)) {
            PlanVote planVote = PlanVoteLocalServiceUtil.createPlanVote(new PlanVotePK(userId, 1L));
            planVote.setPlanId(planId);
            planVote.setCreateDate(new Date());
            PlanVoteLocalServiceUtil.updatePlanVote(planVote);
            Plan plan = PlanLocalServiceUtil.getPlan(planId);
            plan.setVotes(plan.getVotes() + 1);
            PlanLocalServiceUtil.updatePlan(plan);
            
        } 
        else {
            try {
                PlanVoteLocalServiceUtil.deletePlanVote(new PlanVotePK(userId, 1L));
                SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", planId,PlanActivityKeys.RETRACT_VOTE_FOR_PLAN.id(), StringPool.BLANK, 0);
            } catch (NoSuchPlanVoteException e) {
                // ignore
            }            
        }
        
        String redirect = ParamUtil.getString(actionRequest, PlanConstants.REDIRECT);
        actionResponse.sendRedirect(redirect);
    }
}
