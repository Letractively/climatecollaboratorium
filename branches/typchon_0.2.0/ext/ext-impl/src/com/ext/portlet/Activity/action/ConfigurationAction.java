/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.ext.portlet.Activity.ActivityConstants;
import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.Activity.ActivityConstants.SubscriptionMode;
import com.ext.portlet.plans.PlanConstants;
import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.SubscriptionModel;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

public class ConfigurationAction extends BaseConfigurationAction {

	public void processAction(PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
		 	String portletResource = ParamUtil.getString(actionRequest, PlanConstants.PORTLET_RESOURCE);
	        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
	        
	        ActivityConstants.SubscriptionMode oldmode = null;
	        ActivityConstants.SubscriptionMode nmode = null;
	      
	        try {
	        	oldmode = ActivityConstants.SubscriptionMode.valueOf(preferences.getValue(ActivityConstants.SUBSCRIPTION_MODE, ""));
	        } catch (IllegalArgumentException e) {
	        	//don't care
	        }
	      try {
	        nmode = ActivityConstants.SubscriptionMode.valueOf(ParamUtil.getString(actionRequest,ActivityConstants.SUBSCRIPTION_MODE,""));
	      } catch (IllegalArgumentException e) {
	    	  //don't care.
	      }
	        if (nmode == null ) {
	    	 return;
	      }
	      
	      if (oldmode != nmode) {
	    	  preferences.setValue(ActivityConstants.SUBSCRIPTION_MODE, nmode.toString());
	    	  preferences.store();
	      }
	      
	      if (ParamUtil.getBoolean(actionRequest, ActivityConstants.RESET,false)) {
	    	  ThemeDisplay themedisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
	    	  long userId = UserLocalServiceUtil.getDefaultUserId(themedisplay.getCompanyId());
	    	  ActivityUtil.clearSubscriptions(userId);
	    	  ActivityUtil.addDefaultSubscriptions(userId);
	      }
	      
	}
	
	

	public String render(PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws Exception {
		 String portletResource = ParamUtil.getString(renderRequest, PlanConstants.PORTLET_RESOURCE);
		 PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(renderRequest, portletResource);
		 try {
			 SubscriptionMode mode = SubscriptionMode.valueOf(preferences.getValue(ActivityConstants.SUBSCRIPTION_MODE, ""));
			 renderRequest.setAttribute(ActivityConstants.SUBSCRIPTION_MODE,mode);
		 } catch (IllegalArgumentException e) {
			 //don't care;
		 }
		 return "/html/portlet/ext/wall/configuration.jsp";
    }
	
}
