/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models.action;

import com.ext.portlet.debaterevision.NoSuchDebateCategoryException;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.models.DebatesConstants;
import com.ext.portlet.models.DebatesUtil;
import com.ext.portlet.plans.PlanConstants;
import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.messageboards.model.MBCategory;

import javax.portlet.*;
import java.util.List;

/**
 * Configuration action for Debates portlet. It is responsible for setting
 * parent category name that will contain all debates informations.
 *
 * @author janusz.p
 * @version 1.0
 */
public class ConfigurationAction extends BaseConfigurationAction {

	/**
	 * Processes form submission action by updating portlet preferences.
	 *
	 * @param portletConfig
	 *            portlet config
	 * @param actionRequest
	 *            action request
	 * @param actionResponse
	 *            action response
	 * @throws Exception
	 *             in case of any error
	 */
	public void processAction(PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		long categoryId = ParamUtil.getLong(actionRequest, DebatesConstants.MAIN_CATEGORY);

		long topicId = ParamUtil.getLong(actionRequest,
				PlanConstants.DEFAULT_TOPIC_ID);
		long modelId = ParamUtil.getLong(actionRequest,
				PlanConstants.DEFAULT_MODEL_ID);

		 String portletResource = ParamUtil.getString(actionRequest, PlanConstants.PORTLET_RESOURCE);

	        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

		if (categoryId > 0) {

			if (categoryId != Long.valueOf(preferences.getValue(DebatesConstants.CATEGORY_ID, "-1"))) {
				topicId = -1;
				preferences.setValue(DebatesConstants.CATEGORY_ID,String.valueOf(categoryId));
				preferences.setValue(PlanConstants.DEFAULT_TOPIC_ID,String.valueOf(topicId));
				preferences.store();
			}

		}

		if (topicId > 0) {
			preferences.setValue(PlanConstants.DEFAULT_TOPIC_ID, String
					.valueOf(topicId));
		}

		if (modelId > 0) {
			preferences.setValue(PlanConstants.DEFAULT_MODEL_ID, String
					.valueOf(modelId));
		}
		preferences.store();

		if (SessionErrors.isEmpty(actionRequest)) {
			SessionMessages.add(actionRequest, portletConfig.getPortletName()
					+ ".doConfigure");
		}
	}

	/**
	 * Render action for configuring a Debates portlet.
	 *
	 * @param portletConfig
	 *            portlet config
	 * @param renderRequest
	 *            render request
	 * @param renderResponse
	 *            render response
	 * @throws Exception
	 *             in case of any error
	 */
	public String render(PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws Exception {

		 String portletResource = ParamUtil.getString(renderRequest, PlanConstants.PORTLET_RESOURCE);
		 PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(renderRequest, portletResource);

		// List < MBCategory > topics
		// =MBCategoryLocalServiceUtil.getCategories(topicsCategory.getGroupId(),
		// topicsCategory.getCategoryId());
		List <DebateCategory> topics = DebateCategoryLocalServiceUtil.getDebateCategories(0,Integer.MAX_VALUE);

        List<MBCategory> candidates = DebatesUtil
				.getCategoryCandidates(renderRequest);
		// System.out.println("inside configuration action"+topics);
		renderRequest.setAttribute(DebatesConstants.MAIN_CATEGORY, DebatesUtil
				.getMainCategory(renderRequest));
		renderRequest.setAttribute(DebatesConstants.MAIN_CATEGORY_CANDIDATES,
				candidates);
		renderRequest.setAttribute(DebatesConstants.TOPICS, topics);

        long topicId = Long.parseLong(preferences.getValue(
				PlanConstants.DEFAULT_TOPIC_ID, "-1"));

		long modelId = Long.parseLong(preferences.getValue(
				PlanConstants.DEFAULT_MODEL_ID, "-1"));

		if (topicId >= 0) {
        	DebateCategory defaultTopic = null;
        	try {
        		defaultTopic = DebateCategoryLocalServiceUtil.getDebateCategory(topicId);

        	} catch (NoSuchDebateCategoryException e) {
        		preferences.setValue(PlanConstants.DEFAULT_TOPIC_ID, "-1");
        	}
            if (defaultTopic == null) {
                preferences.setValue(PlanConstants.DEFAULT_TOPIC_ID, "-1");

            } else {
                renderRequest.setAttribute(PlanConstants.DEFAULT_TOPIC, defaultTopic);
            }

        }

		renderRequest.setAttribute(PlanConstants.DEFAULT_MODEL_ID, modelId);
		System.err.println("Setting modelId to " + modelId);

		return DebatesConstants.CONFIGURATION_JSP;
	}
}