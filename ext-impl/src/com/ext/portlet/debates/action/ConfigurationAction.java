/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.debates.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.ext.portlet.debates.DebatesConstants;
import com.ext.portlet.debates.DebatesUtil;
import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;

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
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        if (!cmd.equals(Constants.UPDATE)) {
            return;
        }

        long parentCategoryId = ParamUtil.getLong(actionRequest, DebatesConstants.CATEGORY_ID);
        DebatesUtil.initializePortlet(actionRequest, parentCategoryId);


        if (SessionErrors.isEmpty(actionRequest)) {
            SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
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
    public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse)
            throws Exception {

        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(renderRequest);

        String categoryIdStr = preferences.getValue(DebatesConstants.CATEGORY_ID, "0");
        long categoryId = categoryIdStr == null ? 0L : Long.parseLong(categoryIdStr);

        if (categoryId > 0) {
            MBCategory category = MBCategoryLocalServiceUtil.getCategory(categoryId);
            renderRequest.setAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY, category);
        }

        return DebatesConstants.CONFIGURATION_JSP;
    }
}