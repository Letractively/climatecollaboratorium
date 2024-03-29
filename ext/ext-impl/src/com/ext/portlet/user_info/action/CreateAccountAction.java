/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.user_info.action;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        boolean captchaValid = ReCaptchaUtils.validateCaptcha(actionRequest);
        
        String redirect = ParamUtil.getString(actionRequest, "redirect");
        
        
        redirect = Helper.removeParamFromRequestStr(redirect, "signinRegError");
        redirect = Helper.removeParamFromRequestStr(redirect, "isRegistering");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningIn");
        redirect = Helper.removeParamFromRequestStr(redirect, "isPasswordReminder");
        redirect = Helper.removeParamFromRequestStr(redirect, "firstName");
        redirect = Helper.removeParamFromRequestStr(redirect, "lastName");
        redirect = Helper.removeParamFromRequestStr(redirect, "screenName");
        redirect = Helper.removeParamFromRequestStr(redirect, "emailAddress");
        
        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);

        if (captchaValid) {
            super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
        }
        if (captchaValid && SessionErrors.isEmpty(actionRequest)) {

            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest);

            String login = ParamUtil.getString(actionRequest, "screenName");
            String password = ParamUtil.getString(actionRequest, "password1");
            boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");

            String authType = preferences.getValue("authType", null);
            //request.getSession().removeAttribute(arg0)
            try {
                LoginUtil.login(request, response, login, password, rememberMe, authType);
            } catch (Exception e) {
                System.out.println(e);
            }

            if (PropsValues.PORTAL_JAAS_ENABLE) {
                actionResponse.sendRedirect(themeDisplay.getPathMain() + "/portal/protected");
            }
            request.getSession().setAttribute("collab_user_has_registered", true);
        }
        else {
            // url parameters
            Map<String, String> parameters = new HashMap<String, String>();
            
            parameters.put("isRegistering", "true");
            
            String screenName = ParamUtil.getString(actionRequest, "screenName");
            String emailAddress = ParamUtil.getString(actionRequest, "emailAddress");
            String lastName = ParamUtil.getString(actionRequest, "lastName");
            String firstName = ParamUtil.getString(actionRequest, "firstName");
            
            if (screenName != null) {
                parameters.put("screenName", screenName);
            }
            if (lastName != null) {
                parameters.put("lastName", lastName);
            }
            if (firstName != null) {
                parameters.put("firstName", firstName);
            }     
            if (emailAddress != null) {
                parameters.put("emailAddress", emailAddress);
            }

            redirect = Helper.modifyRedirectUrl(redirect, actionRequest, parameters);
            
        }
        if (Validator.isNotNull(redirect)) {
            
            actionResponse.sendRedirect(redirect);
        } else {
            actionResponse.sendRedirect("/web/guest");
        }
    }

}
