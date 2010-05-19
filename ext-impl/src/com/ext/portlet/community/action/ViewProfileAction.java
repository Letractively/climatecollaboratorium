/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.community.action;

import java.util.List;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.Activity.ActivityConstants;
import com.ext.portlet.plans.PlanConstants;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Theme;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class ViewProfileAction extends PortletAction {

	private static Log _log = LogFactoryUtil.getLog(ViewProfileAction.class);

	public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		PortletPreferences preferences = renderRequest.getPreferences();
		Long userId = -1l;
		ActionForward result = mapping.findForward(CommunityConstants.SEARCH_COMMUNITY_FORWARD);

		if (Boolean.valueOf(preferences.getValue(CommunityConstants.EDIT_ENABLED_PARAMETER, "false"))) {
			ThemeDisplay theme = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			userId = theme.getUserId();
		
			if (userId < 0) {
				return mapping.findForward(CommunityConstants.LOGIN_MESSAGE_FORWARD);
			}
			renderRequest.setAttribute(CommunityConstants.EDIT_ENABLED_PARAMETER, true);
			renderRequest.setAttribute(CommunityConstants.USER_PARAMETER, UserLocalServiceUtil.getUserById(userId));
			result = mapping.findForward(CommunityConstants.VIEW_PROFILE_FORWARD);
			
			User user = UserLocalServiceUtil.getUserById(userId);
			setUserActivities(renderRequest,user);
			
		
		} else {
			renderRequest.setAttribute(CommunityConstants.EDIT_ENABLED_PARAMETER, false);
			userId = ParamUtil.getLong(renderRequest, "userId", -1);
			if (userId < 0) {
				String queryString = (String) renderRequest.getAttribute("javax.servlet.forward.query_string");
				if (queryString != null) {
					String[] params = queryString.split("&");
					for (int i = 0; i < params.length; i++) {
						if (params[i].startsWith("userId")) {
							int start = params[i].indexOf("=") + 1;
							userId = (start > -1 && start < params[i].length()) ? Long.parseLong(params[i]
									.substring(start)) : -1;
							break;
						}
					}
				}
			}

			if (userId > -1) {
				try {
					
					User user = UserLocalServiceUtil.getUserById(userId);
					renderRequest.setAttribute(CommunityConstants.USER_PARAMETER, user);
					//result = mapping.findForward(CommunityConstants.VIEW_PROFILE_FORWARD);
					setUserActivities(renderRequest,user);
					
				} catch (Exception e) {
					_log.error(e);
				}
			}
		}
		return result;
	}
	
	private void setUserActivities(RenderRequest renderRequest, User user) throws SystemException {
		int pagerStart = ParamUtil.getInteger(renderRequest,ActivityConstants.PAGER_START,0);
		int pagerNext = pagerStart + ActivityConstants.PAGER_MAX_NUMBER;
		int count = SocialActivityLocalServiceUtil.getUserActivitiesCount(user.getUserId());
		List<SocialActivity> activities = SocialActivityLocalServiceUtil.getUserActivities(user.getUserId(), pagerStart, pagerNext);
		renderRequest.setAttribute(ActivityConstants.ACTIVITIES_PARAMETER, activities);
		renderRequest.setAttribute(ActivityConstants.ACTIVITY_COUNT_PARAMETER, count);
		
		
	}
	
	
	
	
}
