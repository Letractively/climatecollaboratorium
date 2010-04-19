/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.PlanConstants.Property;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.PlanPropertyFilter;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlansFilter;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanAttributeFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPropertyFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlansFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlansFilterPK;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

/**
 * Class responsible for handling Update Filters action.
 * 
 * 
 * @author janusz.p
 * @version 1.0
 */
public class UpdateFiltersAction extends ViewPlansAction {
    /**
     * Processes update filters action (submission of configure filters
     * form).
     * 
     * @param mapping
     *            action mapping
     * @param form
     *            action form
     * @param portletConfig
     *            portlet config
     * @param actionRequest
     *            action request
     * @param actionResponse
     *            action response
     * @throws Exception
     *             in case of any error
     */
    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        long planTypeId = ParamUtil.getLong(actionRequest, PlanConstants.PLAN_TYPE_ID);
        PlanType planType = PlanTypeLocalServiceUtil.getPlanType(planTypeId);
        String updateType = ParamUtil.getString(actionRequest, PlanConstants.UPDATE_TYPE);
        PlansUserSettings planUserSettings = PlanLocalServiceHelper.getPlanUserSettings(actionRequest, planTypeId);

        if (updateType.equals(PlanConstants.TOGGLE_FILTERS)) {
            if (planUserSettings != null && planUserSettings.isFiltersDefined()) {
                planUserSettings.setFilterEnabled(!planUserSettings.getFilterEnabled());
                //PlanLocalServiceHelper.saveFilter(actionRequest, planUserSettings);
                PlanLocalServiceHelper.saveUserSettings(actionRequest, planUserSettings);
            }
        } else {
            
            String positionsStr = ParamUtil.getString(actionRequest, PlanConstants.POSITIONS);
            String positionsOperator = ParamUtil.getString(actionRequest, PlanConstants.POSITIONS_FILTER_OPERATOR);
            List<Long> positionsIds = new ArrayList<Long>();

            Map<Long, Boolean> usedPositions = new HashMap<Long, Boolean>();
            String[] positionsArray = positionsStr.split(PlanConstants.PLAN_POSITIONS_SEPARATOR);
            for (String position : positionsArray) {
                if (!position.trim().equals("")) {
                    long positionId = Long.parseLong(position);
                    if (!usedPositions.containsKey(positionId)) {
                        positionsIds.add(positionId);
                        usedPositions.put(positionId, true);
                    }
                }
            }

            Map<String, String[]> parametersMap = actionRequest.getParameterMap();
            for(Attribute attribute: Attribute.values()) {
                if (attribute.isFiltered()) {
                    PlanAttributeFilter filter = planUserSettings.getAttributeFilter(attribute.toString());
                    if (filter == null) {
                        filter = PlanAttributeFilterLocalServiceUtil.createPlanAttributeFilter(null);
                        filter.setAttributeName(attribute.toString());
                        filter.setPlanUserSettingsId(planUserSettings.getPlanUserSettingsId());
                    }
                    
                    if (! parametersMap.containsKey(attribute.toString()) && 
                            (!parametersMap.containsKey(attribute.toString() + "_MIN") || !parametersMap.containsKey(attribute.toString() + "_MAX"))) {
                        throw new Exception("Filter value for attribute " + attribute.toString() + " not present");
                    }
                    if (attribute.isFilterSingleValue()) {
                        String value = ParamUtil.getString(actionRequest, attribute.toString());
                        filter.setStringVal(value);
                    } else {
                        double min = ParamUtil.getDouble(actionRequest, attribute.toString() + "_MIN");
                        double max = ParamUtil.getDouble(actionRequest, attribute.toString() + "_MAX");
                        filter.setMin(min);
                        filter.setMax(max);
                    }
                    planUserSettings.addPlanAttributeFilter(filter);
                }
            }
            
            for (Property property: Property.values()) {
                if (property.isForPublished(planType.getPublished())) {
                    PlanPropertyFilter filter = planUserSettings.getPropertyFilter(property.toString());
                    if (filter == null) {
                        filter = PlanPropertyFilterLocalServiceUtil.createPlanPropertyFilter(null);
                        filter.setPropertyName(property.toString());
                        filter.setPlanUserSettingsId(planUserSettings.getPlanUserSettingsId());
                    }
                    
                    if (! parametersMap.containsKey(property.toString())) {
                        throw new Exception("Filter value for property " + property.toString() + " not present");
                    }
                    String value = ParamUtil.getString(actionRequest, property.toString());
                    filter.setValue(value);
                    planUserSettings.addPlanPropertyFilter(filter);
                }
            }
            
            planUserSettings.setFilterPositionsAll(positionsOperator.equals(PlanConstants.ALL));
            planUserSettings.setPositionsIds(positionsIds);
            planUserSettings.setFilterEnabled(true);
            PlanLocalServiceHelper.saveUserSettings(actionRequest, planUserSettings);
            /*
            PlansFilter plansFilter = PlanLocalServiceHelper.getFilter(actionRequest, planTypeId);
            if (plansFilter == null) {
                ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
                long userId = themeDisplay.getUserId();
                PlansFilterPK plansFilterPK = new PlansFilterPK();
                plansFilterPK.setUserId(userId);
                plansFilterPK.setPlanTypeId(planTypeId);

                plansFilter = PlansFilterLocalServiceUtil.createPlansFilter(plansFilterPK);
            }

            plansFilter.setDateFrom(dateFrom);
            plansFilter.setDateTo(dateTo);
            plansFilter.setVotesFrom(votesFrom);
            plansFilter.setVotesTo(votesTo);
            plansFilter.setMitigationFrom(mitigationFrom);
            plansFilter.setMitigationTo(mitigationTo);
            plansFilter.setDamageFrom(damageFrom);
            plansFilter.setDamageTo(damageTo);
            plansFilter.setCO2From(CO2From);
            plansFilter.setCO2To(CO2To);
            plansFilter.setName(name);
            plansFilter.setDescription(description);
            plansFilter.setCreator(creator);
            plansFilter.setPositionsIds(positionsIds);
            plansFilter.setFilterPositionsAll(positionsOperator.equals(PlanConstants.ALL));
            plansFilter.setEnabled(true);

            PlanLocalServiceHelper.saveFilter(actionRequest, plansFilter);
            */
        }
        String redirect = ParamUtil.getString(actionRequest, PlanConstants.REDIRECT);
        actionResponse.sendRedirect(redirect);
    }
}