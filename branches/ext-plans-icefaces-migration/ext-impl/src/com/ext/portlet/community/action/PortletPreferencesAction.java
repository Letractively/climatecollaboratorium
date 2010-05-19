/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.community.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.plans.PlanConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.PortletAction;

public class PortletPreferencesAction extends PortletAction {
	
	private static Log _log = LogFactoryUtil.getLog(PortletPreferencesAction.class);
	
	public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
	

		ActionForward result = mapping.findForward(CommunityConstants.PORTLET_PREFS_FORWARD);
		return result;
	}
	
	public void processAction(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {
	
		Boolean enableEdit = ParamUtil.getBoolean(actionRequest, CommunityConstants.EDIT_ENABLED_PARAMETER);
		 String redirect = ParamUtil.getString(actionRequest, CommunityConstants.REDIRECT);
		 actionRequest.getPreferences().setValue(CommunityConstants.EDIT_ENABLED_PARAMETER, String.valueOf(enableEdit));
		 actionRequest.getPreferences().store();
		 actionResponse.sendRedirect(redirect);
		
	}
	
	

}
