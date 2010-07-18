/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;

public class EditMembersAction extends PortletAction {

	@Override
	public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		Plan plan = PlanLocalServiceUtil.getPlan(ParamUtil.getLong(actionRequest, PlanConstants.PLAN_ID));
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		if (PlanConstants.REQUEST_MEMBERSHIP.equals(cmd)) {
			MembershipRequestLocalServiceUtil.addMembershipRequest(themeDisplay.getUserId(), plan.getChildGroupId(), ParamUtil.getString(actionRequest, "comment"));
			
			
		} else if (PlanConstants.REQUEST_REPLY.equals(cmd)) {
			
			Long id = ParamUtil.getLong(actionRequest, PlanConstants.MEMBERSHIP_REQUEST_ID);
			MembershipRequestLocalServiceUtil.updateStatus(themeDisplay.getUserId(),id,
					ParamUtil.getString(actionRequest, "comment"),ParamUtil.getInteger(actionRequest, PlanConstants.REQUEST_STATUS));
				
		}
		
		String redirect = ParamUtil.getString(actionRequest,PlanConstants.REDIRECT);
		if (redirect!=null) {
			sendRedirect(actionRequest, actionResponse,redirect);
		}
		
	}

}
