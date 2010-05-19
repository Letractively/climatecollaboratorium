/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.ext.portlet.community.CommunityUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;
import com.liferay.portlet.wiki.service.permission.WikiPagePermission;
import com.liferay.portlet.wiki.social.WikiActivityKeys;


public class MyWikiActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!WikiPagePermission.contains(
				permissionChecker, activity.getClassPK(), ActionKeys.VIEW)) {

			return null;
		}

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String creatorUserName = CommunityUtil.generateUserURL(activity.getUserId());

		int activityType = activity.getType();

		// Link

		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.getPageResource(
				activity.getClassPK());

		String link =
			themeDisplay.getURLPortal() + themeDisplay.getPathMain() +
				"/wiki/find_page?pageResourcePrimKey=" + activity.getClassPK();

		// Title

		String titlePattern = null;

		if (activityType == WikiActivityKeys.ADD_PAGE) {
			titlePattern = "activity-wiki-add-page";
		}
		else if (activityType == WikiActivityKeys.UPDATE_PAGE) {
			titlePattern = "activity-wiki-update-page";
		}

		if (Validator.isNotNull(groupName)) {
			titlePattern += "-in";
		}

		StringBuilder sb = new StringBuilder();

		String pageTitle = wrapLink(
			link, cleanContent(pageResource.getTitle()));

		Object[] titleArguments = new Object[] {
			groupName, creatorUserName, pageTitle
		};

		String title = themeDisplay.translate(titlePattern, titleArguments);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		WikiPage.class.getName()
	};

}