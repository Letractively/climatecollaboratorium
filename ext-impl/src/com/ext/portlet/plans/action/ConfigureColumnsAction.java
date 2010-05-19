/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.action;

import java.util.HashSet;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * Class responsible for handling Configure Columns action.
 *
 * @author janusz.p
 * @version 1.0
 */
public class ConfigureColumnsAction extends ViewPlansAction {
    
    /**
     * Processes configure columns action (submission of configure columns form). 
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

        long planTypeId = ParamUtil.getLong(actionRequest, PlanConstants.PLAN_TYPE_ID);
        PlansUserSettings userSettings = PlanLocalServiceHelper.getPlanUserSettings(actionRequest, planTypeId);
        Set<String> cols = new HashSet<String>();
        if (actionRequest.getParameter("columns") == null) {
            SessionErrors.add(actionRequest, "columns can't be empty");
        }
        else {
            for (String s:actionRequest.getParameterMap().get("columns")) {
        	    cols.add(s);
            }
            PlanType planType = PlanTypeLocalServiceUtil.getPlanType(planTypeId);
            for (Columns c: Columns.getPlanTypeColumns(planType)) {
        	    String name = c.name();
        	    c.setUserSetting(userSettings, cols.contains(name));
            }

            PlanLocalServiceHelper.saveUserSettings(actionRequest, userSettings);
        }

        
        
        String redirect = ParamUtil.getString(actionRequest, PlanConstants.REDIRECT);
        actionResponse.sendRedirect(redirect);
    }
}