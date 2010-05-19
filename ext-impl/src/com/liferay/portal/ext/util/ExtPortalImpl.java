/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.liferay.portal.ext.util;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;

import com.ext.portlet.community.CommunityUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalImpl;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletURLImpl;
import com.liferay.portlet.UserAttributes;

public class ExtPortalImpl extends PortalImpl {

	public ExtPortalImpl() {
		super();
	}

	public String getUserName(long userId, String defaultUserName) {
		return getUserName(userId, defaultUserName, UserAttributes.USER_NAME_NICKNAME);
	}

	public String getUserName(long userId, String defaultUserName, HttpServletRequest request) {

		return getUserName(userId, defaultUserName, UserAttributes.USER_NAME_NICKNAME, request);
	}
	
	public String getUserName(
			long userId, String defaultUserName, String userAttribute,
			HttpServletRequest request) {

			String userName = defaultUserName;

			try {
				User user = UserLocalServiceUtil.getUserById(userId);

				if (userAttribute.equals(UserAttributes.USER_NAME_FULL)) {
					userName = user.getFullName();
				}
				else {
					userName = user.getScreenName();
				}

				if (request != null) {
					Layout layout = (Layout)request.getAttribute(WebKeys.LAYOUT);
					
					String url = CommunityUtil.generateUserHref(user.getUserId());
					userName =
						"<a href=\"" + url + "\">" + userName +
							"</a>";
				}
			}
			catch (Exception e) {
			}

			return userName;
		}
	
	

}
