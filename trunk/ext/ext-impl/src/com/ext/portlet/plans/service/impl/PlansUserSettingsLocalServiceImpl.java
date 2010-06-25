/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.service.impl;

import java.util.Map;

import com.ext.portlet.plans.NoSuchUserSettingsException;
import com.ext.portlet.plans.model.PlanColumnSettings;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanColumnSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlansUserSettingsLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

public class PlansUserSettingsLocalServiceImpl extends PlansUserSettingsLocalServiceBaseImpl {

    private static final String USER_SETTINGS = "USER_SETTINGS_";

    public PlansUserSettings getByUserIdPlanTypeId(Long userId, Long planTypeId) throws NoSuchUserSettingsException,
            SystemException {
        return plansUserSettingsPersistence.findByuserIdPlanTypeId(userId, planTypeId);
    }

    private String getUserSettingsParamName(long planTypeId) {
        return USER_SETTINGS + String.valueOf(planTypeId);
    }

    public PlansUserSettings getPlanUserSettings(Map sessionMap, Map requestMap, PlanType planType)
            throws PortalException, SystemException {
        String settingsParamName = getUserSettingsParamName(planType.getPlanTypeId());
        PlansUserSettings userSettings = (PlansUserSettings) sessionMap.get(settingsParamName);
        if (userSettings == null) {
            ThemeDisplay themeDisplay = (ThemeDisplay) requestMap.get(WebKeys.THEME_DISPLAY);
            long userId = themeDisplay.getUserId();
            User user = themeDisplay.getUser();

            if (!user.isDefaultUser()) {
                try {
                    userSettings = PlansUserSettingsLocalServiceUtil.getByUserIdPlanTypeId(userId, planType
                            .getPlanTypeId());
                    if (userSettings != null) {
                        // setPlansFilterPositionsIds(userSettings);
                    }

                } catch (NoSuchUserSettingsException e) {
                    System.out.print(e);
                }
            }

            if (userSettings == null || user.isDefaultUser()) {
                userSettings = PlansUserSettingsLocalServiceUtil.createPlansUserSettings(null);
                userSettings.setPlanTypeId(planType.getPlanTypeId());
                userSettings.setUserId(userId);
                userSettings.setFilterEnabled(false);
                userSettings.setFilterPositionsAll(false);

                for (PlanTypeColumn planTypeColumn : planType.getColumns()) {
                    PlanColumnSettings settings = PlanColumnSettingsLocalServiceUtil.createPlanColumnSettings(null);
                    settings.setColumnName(planTypeColumn.getColumnName());
                    settings.setPlanUserSettingsId(userSettings.getPlanUserSettingsId());
                    settings.setVisible(planTypeColumn.getVisibleByDefault());

                    userSettings.addColumnSettings(settings);
                }

            }
            sessionMap.put(settingsParamName, userSettings);
        }

        return userSettings;

    }

    public PlansUserSettings getPlanUserSettings(Map sessionMap, Map requestMap, long planTypeId)
            throws PortalException, SystemException {
        return getPlanUserSettings(sessionMap, requestMap, PlanTypeLocalServiceUtil.getPlanType(planTypeId));
    }
}
