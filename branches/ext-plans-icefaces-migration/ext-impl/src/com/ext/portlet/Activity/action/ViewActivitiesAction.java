/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.action;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.Activity.ActivityConstants;
import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class ViewActivitiesAction extends PortletAction {

	public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		
		ThemeDisplay themedisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themedisplay.getUserId();
		int pagerStart = ParamUtil.getInteger(renderRequest, ActivityConstants.PAGER_START, 0);
		int pagerNext = pagerStart + ActivityConstants.PAGER_MAX_NUMBER;
		ActivityConstants.SubscriptionMode mode = null;
		
		
		try {
			mode = ActivityConstants.SubscriptionMode.valueOf(renderRequest.getPreferences().getValue(ActivityConstants.SUBSCRIPTION_MODE, ""));
		} catch (IllegalArgumentException e) {
			//don't worry about it
		}
		if (mode == null) {
			renderRequest.getPreferences().setValue(ActivityConstants.SUBSCRIPTION_MODE,ActivityConstants.SubscriptionMode.GLOBAL.toString());
			userId = UserLocalServiceUtil.getDefaultUserId(themedisplay.getCompanyId());
			checkDefaults(userId);
		} 
		
		List<SocialActivity> activities = Collections.emptyList();
		int activitiesCount =0;
		
		if (mode == ActivityConstants.SubscriptionMode.USER) {
			activitiesCount = SocialActivityLocalServiceUtil.getUserActivitiesCount(userId);
			activities = SocialActivityLocalServiceUtil.getUserActivities(userId, pagerStart,pagerNext);
			
		} else if (mode == ActivityConstants.SubscriptionMode.GLOBAL) {
			userId = UserLocalServiceUtil.getDefaultUserId(themedisplay.getCompanyId());
//			activities = ActivityUtil.retrieveActivities(userId, pagerStart, ActivityConstants.PAGER_MAX_NUMBER);
//			activitiesCount = ActivityUtil.getActivitiesCount(userId);
			activitiesCount = ActivityUtil.getAllActivitiesCount();
			activities = ActivityUtil.retrieveAllActivities( pagerStart, pagerNext);
			
		} else if (mode == ActivityConstants.SubscriptionMode.PERSONAL) {
			userId = themedisplay.getUserId();
			activities = ActivityUtil.retrieveActivities(userId,pagerStart,ActivityConstants.PAGER_MAX_NUMBER);
			activitiesCount = ActivityUtil.getActivitiesCount(userId);
			
		}
		
		renderRequest.setAttribute(ActivityConstants.SUBSCRIPTION_MODE, mode);
		renderRequest.setAttribute(ActivityConstants.ACTIVITIES_PARAMETER, activities);
		renderRequest.setAttribute(ActivityConstants.ACTIVITY_COUNT_PARAMETER, activitiesCount);
		renderRequest.setAttribute(ActivityConstants.SUBSCRIPTIONS_USER_PARAMETER, userId);
        return mapping.findForward(ActivityConstants.VIEW_FORWARD);
    }
	
	private void checkDefaults(long userid) throws SystemException {
		List<ActivitySubscription> subscriptions = ActivitySubscriptionLocalServiceUtil.findByUser(userid);
		if (subscriptions.size() == 0) {
			ActivityUtil.addDefaultSubscriptions(userid);
		}
	}
	
}
			