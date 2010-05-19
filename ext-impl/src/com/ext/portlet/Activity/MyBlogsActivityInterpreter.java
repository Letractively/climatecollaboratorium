/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.ext.portlet.community.CommunityUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.blogs.service.permission.BlogsEntryPermission;
import com.liferay.portlet.blogs.social.BlogsActivityKeys;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;


public class MyBlogsActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!BlogsEntryPermission.contains(
				permissionChecker, activity.getClassPK(), ActionKeys.VIEW)) {

			return null;
		}

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String creatorUserName = CommunityUtil.generateUserURL(activity.getUserId());
		
		
		String receiverUserName = CommunityUtil.generateUserURL(activity.getReceiverUserId());
		

		int activityType = activity.getType();

		JSONObject extraData = null;

		if (Validator.isNotNull(activity.getExtraData())) {
			extraData = JSONFactoryUtil.createJSONObject(
				activity.getExtraData());
		}

		// Link

		BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(
			activity.getClassPK());

		String link =
			themeDisplay.getURLPortal() + themeDisplay.getPathMain() +
				"/blogs/find_entry?entryId=" + activity.getClassPK();

		// Title

		String titlePattern = null;

		if (activityType == BlogsActivityKeys.ADD_COMMENT) {
			titlePattern = "activity-blogs-add-comment";
		}
		else if (activityType == BlogsActivityKeys.ADD_ENTRY) {
			titlePattern = "activity-blogs-add-entry";
		}

		if (Validator.isNotNull(groupName)) {
			titlePattern += "-in";
		}

		String entryTitle = wrapLink(link, cleanContent(entry.getTitle()));

		Object[] titleArguments = new Object[] {
			groupName, creatorUserName, receiverUserName, entryTitle
		};

		String title = themeDisplay.translate(titlePattern, titleArguments);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		BlogsEntry.class.getName()
	};

}