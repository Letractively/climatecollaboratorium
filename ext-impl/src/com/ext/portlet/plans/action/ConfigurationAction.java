/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.plans.action;

import com.ext.portlet.debaterevision.NoSuchDebateCategoryException;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debates.DebatesConstants;
import com.ext.portlet.plans.PlanConstants;
import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Configuration action for Plans portlet. It is responsible for setting
 * default debates topic from which positions should be chosen.
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

        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        if (!cmd.equals(Constants.UPDATE)) {
            return;
        }

        long topicId = ParamUtil.getLong(actionRequest, PlanConstants.DEFAULT_TOPIC_ID);
        long modelId = ParamUtil.getLong(actionRequest, PlanConstants.DEFAULT_MODEL_ID);

        String portletResource = ParamUtil.getString(actionRequest, PlanConstants.PORTLET_RESOURCE);

        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

        if (topicId > 0) {
            preferences.setValue(PlanConstants.DEFAULT_TOPIC_ID, String.valueOf(topicId));
        }
        else if (modelId > 0) {
            preferences.setValue(PlanConstants.DEFAULT_MODEL_ID, String.valueOf(modelId));
        }
        else {
            Date dateMin = ParamUtil.getDate(actionRequest, PlanConstants.DATE_MIN, df);
            Date dateMax = ParamUtil.getDate(actionRequest, PlanConstants.DATE_MAX, df);
            double mitigationMin = ParamUtil.getDouble(actionRequest, PlanConstants.MITIGATION_COST_MIN);
            double mitigationMax = ParamUtil.getDouble(actionRequest, PlanConstants.MITIGATION_COST_MAX);
            double damageMin = ParamUtil.getDouble(actionRequest, PlanConstants.DAMAGE_COST_MIN);
            double damageMax = ParamUtil.getDouble(actionRequest, PlanConstants.DAMAGE_COST_MAX);
            double co2Min = ParamUtil.getDouble(actionRequest, PlanConstants.CO2_MIN);
            double co2Max = ParamUtil.getDouble(actionRequest, PlanConstants.CO2_MAX);

            preferences.setValue(PlanConstants.DATE_MIN, df.format(dateMin));
            preferences.setValue(PlanConstants.DATE_MAX, df.format(dateMax));
            preferences.setValue(PlanConstants.MITIGATION_COST_MIN, String.valueOf(mitigationMin));
            preferences.setValue(PlanConstants.MITIGATION_COST_MAX, String.valueOf(mitigationMax));
            preferences.setValue(PlanConstants.DAMAGE_COST_MIN, String.valueOf(damageMin));
            preferences.setValue(PlanConstants.DAMAGE_COST_MAX, String.valueOf(damageMax));
            preferences.setValue(PlanConstants.CO2_MIN, String.valueOf(co2Min));
            preferences.setValue(PlanConstants.CO2_MAX, String.valueOf(co2Max));
        }

        preferences.store();

        if (SessionErrors.isEmpty(actionRequest)) {
            SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        }
    }

    /**
     * Render action for configuring a Plans portlet.
     *
     * @param portletConfig
     *            portlet config
     * @param renderRequest
     *            render request
     * @param renderResponse
     *            render response
     * @return JSP file with configuration form
     * @throws Exception
     *             in case of any error
     */
    public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse)
        throws Exception {

        String portletResource = ParamUtil.getString(renderRequest, PlanConstants.PORTLET_RESOURCE);
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(renderRequest, portletResource);
        List <DebateCategory> topics = DebateCategoryLocalServiceUtil.getDebateCategories(0,Integer.MAX_VALUE);
        renderRequest.setAttribute(DebatesConstants.TOPICS, topics);
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

        long topicId = Long.parseLong(preferences.getValue(PlanConstants.DEFAULT_TOPIC_ID, "-1"));
        long modelId = Long.parseLong(preferences.getValue(PlanConstants.DEFAULT_MODEL_ID, "-1"));

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
        if (modelId >= 0) {
            renderRequest.setAttribute(PlanConstants.DEFAULT_MODEL_ID, modelId);
        }


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
        renderRequest.setAttribute(PlanConstants.DATE_MIN, df.format(dateMin));
        renderRequest.setAttribute(PlanConstants.DATE_MAX, df.format(dateMax));

        return PlanConstants.CONFIGURATION_JSP;
    }
}