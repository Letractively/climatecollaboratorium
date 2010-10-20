/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.community.action;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.plans.PlanConstants;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.InvokerPortletImpl;
import com.liferay.portlet.admin.util.AdminUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class EditProfileAction extends PortletAction {

	private static Log _log = LogFactoryUtil.getLog(EditProfileAction.class);

	public void processAction(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		// plan field values
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		
		String redirect = ParamUtil.getString(actionRequest,
				CommunityConstants.REDIRECT);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themeDisplay.getUserId();
		User user = UserLocalServiceUtil.getUser(userId);

		boolean changed = false;
		
		if (cmd.equals(CommunityConstants.UPDATE_PASSWORD)) {
			String oldPassword = AdminUtil.getUpdateUserPassword(
					actionRequest, user.getUserId());
				String newPassword1 = ParamUtil.getString(
					actionRequest, CommunityConstants.PASSWORD1);
				String newPassword2 = ParamUtil.getString(
					actionRequest, CommunityConstants.PASSWORD2);
			
			
			try {
			    UserServiceUtil.updatePassword(userId, newPassword1, newPassword2, false);
			}
			catch (Exception e) {
			    if (e instanceof UserPasswordException) {
			        SessionErrors.add(actionRequest, UserPasswordException.class.getName());
			    }
			    else {
			        throw e;
			    }
			}
			PortletSession portletSession =
				actionRequest.getPortletSession();

			InvokerPortletImpl.clearResponses(portletSession);
			if (Validator.isNotNull(newPassword1)) {
				portletSession.setAttribute(
					WebKeys.USER_PASSWORD, newPassword1,
					PortletSession.APPLICATION_SCOPE);
			}
		} else if (cmd.equals(Constants.UPDATE)) {
			
			String screenName = ParamUtil.getString(actionRequest,
					CommunityConstants.SCREENNAME);
			String firstName = ParamUtil.getString(actionRequest,
					CommunityConstants.FIRSTNAME);
			String lastName = ParamUtil.getString(actionRequest,
					CommunityConstants.LASTNAME);
            String email = ParamUtil.getString(actionRequest,CommunityConstants.EMAIL);
			String bio = ParamUtil.getString(actionRequest, CommunityConstants.BIO);
			
			if (!user.getScreenName().equals(screenName)) {
				user.setScreenName(screenName);
				changed = true;
			}
			if (!user.getFirstName().equals(firstName)) {
				user.setFirstName(firstName);
			}
			if (!user.getLastName().equals(lastName)) {
				user.setLastName(lastName);
			}
			if (!user.getEmailAddress().equals(email)) {
				user.setEmailAddress(email);
			}

			try {
				String existingBio = ExpandoValueLocalServiceUtil.getData(
						User.class.getName(), CommunityConstants.EXPANDO,
						CommunityConstants.BIO, user.getUserId(),
						StringPool.BLANK);
				if (!bio.equals(existingBio)) {
					changed = true;
					ExpandoValueLocalServiceUtil.addValue(User.class.getName(),
							CommunityConstants.EXPANDO, CommunityConstants.BIO,
							user.getUserId(), bio);
				}
			} catch (Exception e) {
				_log.error(e);
			}

			if (changed) {
				user.setModifiedDate(new Date());
			}
			try {
			    UserLocalServiceUtil.updateUser(user);
			}
			catch (Exception e) {
			    _log.error("Can't update user " + user.getScreenName(), e);
			    SessionErrors.add(actionRequest, e.getClass().getName());
			}
		}

		 if (redirect != null && !redirect.equals("")) {
               actionResponse.sendRedirect(redirect);
         }
	}
}
