/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanFinder;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.struts.PortletAction;

/**
 * Class responsible for handling View Plans action. It simply forwards request
 * to appropriate path.
 * 
 * version 1.1: added support for plan votes, plan filtering and columns configuration.
 *
 * @author janusz.p, janusz.p
 * @version 1.1
 * @since 1.0
 */
public class ViewPlansAction extends PortletAction {

    @BeanReference(name = "com.ext.portlet.plans.PlanFinder.impl")
    protected PlanFinder planFinder;
    
    /**
     * Method responsible for preparing data for View Plans page. It simply
     * forwards request to appropriate path.
     *
     * @param mapping action mapping
     * @param form action form
     * @param portletConfig portlet config
     * @param renderRequest render request
     * @param renderResponse render response
     * @return action forward path
     * @throws Exception
     *             in case of any error
     */
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
        PlanLocalServiceHelper.addQuestionsAndPositions(renderRequest, 
                Long.parseLong(PlanLocalServiceHelper.getDefaultTopicId(renderRequest)));
        
        PortletPreferences preferences = renderRequest.getPreferences();
        String positionURL = preferences.getValue(PlanConstants.PLAN_POSITION_URL, "");
        String questionURL = preferences.getValue(PlanConstants.PLAN_QUESTION_URL, "");
        
        renderRequest.setAttribute(PlanConstants.PLAN_POSITION_URL, positionURL);
        renderRequest.setAttribute(PlanConstants.PLAN_QUESTION_URL, questionURL);
        renderRequest.setAttribute(PlanConstants.VOTES, PlanVoteLocalServiceUtil.getPlanVotesCount());
        PlanLocalServiceHelper.addPlanVote(renderRequest);
        
        
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        double mitigationCostMin = Double.parseDouble(preferences.getValue(PlanConstants.MITIGATION_COST_MIN, "-1"));
        double mitigationCostMax = Double.parseDouble(preferences.getValue(PlanConstants.MITIGATION_COST_MAX, "-1"));
        double damageCostMin = Double.parseDouble(preferences.getValue(PlanConstants.DAMAGE_COST_MIN, "-1"));
        double damageCostMax = Double.parseDouble(preferences.getValue(PlanConstants.DAMAGE_COST_MAX, "-1"));
        double co2Min = Double.parseDouble(preferences.getValue(PlanConstants.CO2_MIN, "-1"));
        double co2Max = Double.parseDouble(preferences.getValue(PlanConstants.CO2_MAX, "-1"));
        Date dateMin = df.parse(preferences.getValue(PlanConstants.DATE_MIN, "01-01-2009"));
        Date dateMax = df.parse(preferences.getValue(PlanConstants.DATE_MAX, "01-01-2011"));
        
        renderRequest.setAttribute(PlanConstants.MITIGATION_COST_MIN, mitigationCostMin);
        renderRequest.setAttribute(PlanConstants.MITIGATION_COST_MAX, mitigationCostMax);
        renderRequest.setAttribute(PlanConstants.DAMAGE_COST_MIN, damageCostMin);
        renderRequest.setAttribute(PlanConstants.DAMAGE_COST_MAX, damageCostMax);
        renderRequest.setAttribute(PlanConstants.CO2_MIN, co2Min);
        renderRequest.setAttribute(PlanConstants.CO2_MAX, co2Max);
        renderRequest.setAttribute(PlanConstants.DATE_MIN, dateMin);
        renderRequest.setAttribute(PlanConstants.DATE_MAX, dateMax);
        
        
        //sort order
        //@TODO Need to make this user configurable
        
        String sortcol = renderRequest.getParameter(PlanConstants.ORDER_COLUMN);
        String sortdirection = renderRequest.getParameter(PlanConstants.ORDER_DIRECTION);
        
        
        renderRequest.setAttribute(PlanConstants.ORDER_COLUMN, sortcol==null?PlanConstants.Columns.VOTES.name():sortcol);
        renderRequest.setAttribute(PlanConstants.ORDER_DIRECTION, sortdirection==null?PlanFinder.DESC:sortdirection);
            
           
        return mapping.findForward(PlanConstants.VIEW_PLANS_FORWARD);
    }
}