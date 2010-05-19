/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.Activity.ActivityConstants;
import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.Activity.CompositeSubscriptionHolder;
import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalService;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;

public class ManageSubscriptionsAction extends PortletAction {
	
	
	public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		
		Long userId = ((ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY)).getUserId();		
		renderRequest.setAttribute(ActivityConstants.SUBSCRIPTIONS_PARAMETER, ActivityUtil.getCompositeSubscriptions(userId).values());	
        return mapping.findForward(ActivityConstants.MANAGE_SUBSCRIPTIONS_FORWARD);
    }
	
	public void processAction(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {
		
		Long userId = ((ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY)).getUserId();
		Map<String,CompositeSubscriptionHolder> subs = ActivityUtil.getCompositeSubscriptions(userId);
		for (Map.Entry<String, CompositeSubscriptionHolder> ent:subs.entrySet()) {
			if (!ParamUtil.getBoolean(actionRequest, ent.getKey())) {
				for (ActivitySubscription sub:ent.getValue().getSubscriptions()) {
					ActivitySubscriptionLocalServiceUtil.deleteActivitySubscription(sub);
				}
			}
		}
		
		String redirect = ParamUtil.getString(actionRequest,ActivityConstants.REDIRECT);
		actionResponse.sendRedirect(redirect);
		
	}
	

}
