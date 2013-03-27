/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.user_info.action;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.community.action.CommunityConstants;
import com.ext.recaptcha.ReCaptchaUtils;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.expando.NoSuchColumnException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.login.util.LoginUtil;

/**
 * Class responsible for handling Create Account (register) action.
 * 
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class CreateAccountAction extends com.liferay.portlet.login.action.CreateAccountAction {
    private final static String REDIRECT_AFTER_ACCOUNT_CREATION_PARAM = "redirectUserAfterAccountCreation";

    @Override
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
        
        

        String redirect = ParamUtil.getString(renderRequest, "redirect");
        
        if (redirect == null || redirect.trim().length() == 0) {
            redirect = PortalUtil.getHttpServletRequest(renderRequest).getParameter("redirect");
            if (redirect == null) {
                redirect = PortalUtil.getHttpServletRequest(renderRequest).getHeader("referer");
            }
        }
        
        renderRequest.setAttribute("redirect", redirect);
        
        
        super.render(mapping, form, portletConfig, renderRequest, renderResponse);
        return mapping.findForward("portlet.login.create_account");
    }

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
        
        if (redirect == null || redirect.trim().length() == 0) {
            Object redirectObj = actionRequest.getPortletSession().getAttribute(REDIRECT_AFTER_ACCOUNT_CREATION_PARAM);
            redirect = redirectObj == null ? null : redirectObj.toString();
            if (redirect == null) {
                redirect = PortalUtil.getHttpServletRequest(actionRequest).getHeader("referer");
            }
        }
       
        
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
            User user = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), login);
            
            long imageId = ParamUtil.getLong(request, "imageId", -1);
            String country = ParamUtil.getString(request,  "country", null);
            if (imageId > 0) {
            	//Image img = ImageLocalServiceUtil.getImage(imageId);
            	user.setPortraitId(imageId);
            	UserLocalServiceUtil.updateUser(user, true);
            }
            if (country != null) {
            	try {
            		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(User.class.getName(), CommunityConstants.EXPANDO);
            		ExpandoColumn column = null;
            		try {
            			column = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), "country");	
            		}
            		catch (NoSuchColumnException e) {
            		}
            		
            		
            		if (column == null) {
                        ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), "country", ExpandoColumnConstants.STRING);
            		}
            		
            		ExpandoValueLocalServiceUtil.addValue(User.class.getName(), CommunityConstants.EXPANDO, 
            				"country", user.getUserId(), country);
            	}
            	catch (Throwable t) {
            		t.printStackTrace();
            	}
            }

            String bio = ParamUtil.getString(request,  "bio", null);
            if (StringUtils.isNotBlank(bio)) {
            	ExpandoValueLocalServiceUtil.addValue(User.class.getName(), CommunityConstants.EXPANDO, 
            			CommunityConstants.BIO, user.getUserId(), bio);
            }
            
            
            

            if (PropsValues.PORTAL_JAAS_ENABLE) {
                actionResponse.sendRedirect(themeDisplay.getPathMain() + "/portal/protected");
            }
            request.getSession().setAttribute("collab_user_has_registered", true);
            
            if (Validator.isNotNull(redirect)) {
                actionResponse.sendRedirect(redirect);
            } else {
                actionResponse.sendRedirect("/web/guest");
            }
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
            
            /*actionRequest.getP*/
            
        }
        
    }

}
