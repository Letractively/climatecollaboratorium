/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.user_info.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ext.recaptcha.ReCaptchaUtils;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.login.util.LoginUtil;

/**
 * Class responsible for handling Create Account (register) action.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class CreateAccountAction extends com.liferay.portlet.login.action.CreateAccountAction {
    
    /**
     * Processes create account action (submission of register form). 
     *
     *  @param mapping action mapping
     *  @param form action form
     *  @param portletConfig portlet config
     *  @param actionRequest action request
     *  @param actionResponse action response
     *  @throws Exception in case of any error
     */
    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
    	ReCaptchaUtils.validateCaptcha(actionRequest);
        super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);

        if (SessionErrors.isEmpty(actionRequest)) {

            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest);
            HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
            HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);

            String login = ParamUtil.getString(actionRequest, "screenName");
            String password = ParamUtil.getString(actionRequest, "password1");
            boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");

            String authType = preferences.getValue("authType", null);
            try {
                LoginUtil.login(request, response, login, password, rememberMe, authType);
            } catch (Exception e) {
                System.out.println(e);
            }

            if (PropsValues.PORTAL_JAAS_ENABLE) {
                actionResponse.sendRedirect(themeDisplay.getPathMain() + "/portal/protected");
            } else {
                String redirect = ParamUtil.getString(actionRequest, "redirect");

                if (Validator.isNotNull(redirect)) {
                    actionResponse.sendRedirect(redirect);
                } else {
                    actionResponse.sendRedirect("/web/guest");
                }
            }
        }
    }
}
