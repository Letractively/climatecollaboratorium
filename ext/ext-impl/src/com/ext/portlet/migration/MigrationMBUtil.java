/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.ContentUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.messageboards.model.MBBan;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBStatsUser;
import com.liferay.portlet.messageboards.model.impl.MBCategoryImpl;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.util.MBMailMessage;
import com.liferay.util.LocalizationUtil;
import com.liferay.util.mail.JavaMailUtil;

import java.io.InputStream;

import java.util.Calendar;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.jsp.PageContext;

/**
 * <a href="MBUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MigrationMBUtil {

	public static final String POP_PORTLET_PREFIX = "mb.";

	public static final int POP_SERVER_SUBDOMAIN_LENGTH =
		PropsValues.POP_SERVER_SUBDOMAIN.length();

	public static void collectMultipartContent(
			MimeMultipart multipart, MBMailMessage collector)
		throws Exception {

		for (int i = 0; i < multipart.getCount(); i++) {
			BodyPart part = multipart.getBodyPart(i);

			collectPartContent(part, collector);
		}
	}

	public static void collectPartContent(Part part, MBMailMessage collector)
		throws Exception {

		Object partContent = part.getContent();

		String contentType = part.getContentType().toLowerCase();

		if ((part.getDisposition() != null) &&
			 (part.getDisposition().equalsIgnoreCase(MimeMessage.ATTACHMENT))) {

			if (_log.isDebugEnabled()) {
				_log.debug("Processing attachment");
			}

			byte[] bytes = null;

			if (partContent instanceof String) {
				bytes = ((String)partContent).getBytes();
			}
			else if (partContent instanceof InputStream) {
				bytes = JavaMailUtil.getBytes(part);
			}

			collector.addFile(part.getFileName(), bytes);
		}
		else {
			if (partContent instanceof MimeMultipart) {
				collectMultipartContent((MimeMultipart)partContent, collector);
			}
			else if (partContent instanceof String) {
				if (contentType.startsWith("text/html")) {
					collector.setHtmlBody((String)partContent);
				}
				else {
					collector.setPlainBody((String)partContent);
				}
			}
		}
	}

	public static String getBreadcrumbs(
			long categoryId, long messageId, PageContext pageContext,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		if (messageId > 0) {
			MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);

			return getBreadcrumbs(
				null, message, pageContext, renderRequest, renderResponse);
		}
		else {
			MBCategory category = null;

			try {
				if ((categoryId > 0) &&
					(categoryId != MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID)) {

					category = MBCategoryLocalServiceUtil.getCategory(
						categoryId);
				}
			}
			catch (Exception e) {
				_log.error("Unable to retrieve category " + categoryId, e);
			}

			return getBreadcrumbs(
				category, null, pageContext, renderRequest, renderResponse);
		}
	}

	public static String getBreadcrumbs(
			MBCategory category, MBMessage message, PageContext pageContext,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		String questionId = ParamUtil.getString(renderRequest, "questionId");
		String strutsAction = ParamUtil.getString(
			renderRequest, "struts_action");

		boolean selectCategory = strutsAction.equals(
			"/ext/migration/select_category");

		if ((message != null) && (category == null)) {
			category = message.getCategory();
		}

		PortletURL categoriesURL = renderResponse.createRenderURL();
		categoriesURL.setParameter("questionId", questionId);

		if (selectCategory) {
			categoriesURL.setWindowState(LiferayWindowState.POP_UP);

			categoriesURL.setParameter(
				"struts_action", "/ext/migration/select_category");
		}
		else {
			//categoriesURL.setWindowState(WindowState.MAXIMIZED);

			categoriesURL.setParameter("struts_action", "/ext/migration/view");
			categoriesURL.setParameter(
				"categoryId",
				String.valueOf(MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID));
		}

		String categoriesLink =
			"<a href=\"" + categoriesURL.toString() + "\">" +
				LanguageUtil.get(pageContext, "categories") + "</a>";

		if (category == null) {
			return "<span class=\"first last\">" + categoriesLink + "</span>";
		}

		String breadcrumbs = StringPool.BLANK;

		for (int i = 0;; i++) {
			category = category.toEscapedModel();

			PortletURL portletURL = renderResponse.createRenderURL();

			if (selectCategory) {
				portletURL.setWindowState(LiferayWindowState.POP_UP);

				portletURL.setParameter(
					"struts_action", "/ext/migration/select_category");
				portletURL.setParameter(
					"categoryId", String.valueOf(category.getCategoryId()));
				portletURL.setParameter("questionId", questionId);
			}
			else {
				//portletURL.setWindowState(WindowState.MAXIMIZED);

				portletURL.setParameter(
					"struts_action", "/ext/migration/view");
				portletURL.setParameter(
					"categoryId", String.valueOf(category.getCategoryId()));
				portletURL.setParameter("questionId", questionId);
			}

			String categoryLink =
				"<a href=\"" + portletURL.toString() + "\">" +
					category.getName() + "</a>";

			if (i == 0) {
				if (message != null) {
					breadcrumbs += categoryLink;
				}
				else {
					breadcrumbs =
						"<span class=\"last\">" + categoryLink + "</span>";
				}
			}
			else {
				breadcrumbs = categoryLink + " &raquo; " + breadcrumbs;
			}

			if (category.isRoot()) {
				break;
			}

			category = MBCategoryLocalServiceUtil.getCategory(
				category.getParentCategoryId());
		}

		breadcrumbs =
			"<span class=\"first\">" + categoriesLink + " &raquo; </span>" +
				breadcrumbs;

		if (message != null) {
			message = message.toEscapedModel();

			PortletURL messageURL = renderResponse.createRenderURL();

			//messageURL.setWindowState(WindowState.MAXIMIZED);

			messageURL.setParameter(
				"struts_action", "/ext/migration/view_message");
			messageURL.setParameter(
				"messageId", String.valueOf(message.getMessageId()));
			messageURL.setParameter("questionId", questionId);

			String messageLink =
				"<span class=\"last\"><a href=\"" + messageURL.toString() +
					"\">" + message.getSubject() + "</a></span>";

			breadcrumbs = breadcrumbs + " &raquo; " + messageLink;
		}

		return breadcrumbs;
	}

	public static String getEmailFromAddress(PortletPreferences preferences) {
		String emailFromAddress = PropsValues.MESSAGE_BOARDS_EMAIL_FROM_ADDRESS;

		return preferences.getValue("email-from-address", emailFromAddress);
	}

	public static String getEmailFromName(PortletPreferences preferences) {
		String emailFromName = PropsValues.MESSAGE_BOARDS_EMAIL_FROM_NAME;

		return preferences.getValue("email-from-name", emailFromName);
	}

	public static boolean getEmailHtmlFormat(PortletPreferences preferences) {
		String emailHtmlFormat = preferences.getValue(
			"email-html-format", StringPool.BLANK);

		if (Validator.isNotNull(emailHtmlFormat)) {
			return GetterUtil.getBoolean(emailHtmlFormat);
		}
		else {
			return PropsValues.MESSAGE_BOARDS_EMAIL_HTML_FORMAT;
		}
	}

	public static boolean getEmailMessageAddedEnabled(
		PortletPreferences preferences) {

		String emailMessageAddedEnabled = preferences.getValue(
			"email-message-added-enabled", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageAddedEnabled)) {
			return GetterUtil.getBoolean(emailMessageAddedEnabled);
		}
		else {
			return PropsValues.MESSAGE_BOARDS_EMAIL_MESSAGE_ADDED_ENABLED;
		}
	}

	public static String getEmailMessageAddedBody(
		PortletPreferences preferences) {

		String emailMessageAddedBody = preferences.getValue(
			"email-message-added-body", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageAddedBody)) {
			return emailMessageAddedBody;
		}
		else {
			return ContentUtil.get(
				PropsValues.MESSAGE_BOARDS_EMAIL_MESSAGE_ADDED_BODY);
		}
	}

	public static String getEmailMessageAddedSignature(
		PortletPreferences preferences) {

		String emailMessageAddedSignature = preferences.getValue(
			"email-message-added-signature", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageAddedSignature)) {
			return emailMessageAddedSignature;
		}
		else {
			return ContentUtil.get(
				PropsValues.MESSAGE_BOARDS_EMAIL_MESSAGE_ADDED_SIGNATURE);
		}
	}

	public static String getEmailMessageAddedSubjectPrefix(
		PortletPreferences preferences) {

		String emailMessageAddedSubjectPrefix = preferences.getValue(
			"email-message-added-subject-prefix", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageAddedSubjectPrefix)) {
			return emailMessageAddedSubjectPrefix;
		}
		else {
			return ContentUtil.get(
				PropsValues.MESSAGE_BOARDS_EMAIL_MESSAGE_ADDED_SUBJECT_PREFIX);
		}
	}

	public static boolean getEmailMessageUpdatedEnabled(
		PortletPreferences preferences) {

		String emailMessageUpdatedEnabled = preferences.getValue(
			"email-message-updated-enabled", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageUpdatedEnabled)) {
			return GetterUtil.getBoolean(emailMessageUpdatedEnabled);
		}
		else {
			return PropsValues.MESSAGE_BOARDS_EMAIL_MESSAGE_UPDATED_ENABLED;
		}
	}

	public static String getEmailMessageUpdatedBody(
		PortletPreferences preferences) {

		String emailMessageUpdatedBody = preferences.getValue(
			"email-message-updated-body", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageUpdatedBody)) {
			return emailMessageUpdatedBody;
		}
		else {
			return ContentUtil.get(
				PropsValues.MESSAGE_BOARDS_EMAIL_MESSAGE_UPDATED_BODY);
		}
	}

	public static String getEmailMessageUpdatedSignature(
		PortletPreferences preferences) {

		String emailMessageUpdatedSignature = preferences.getValue(
			"email-message-updated-signature", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageUpdatedSignature)) {
			return emailMessageUpdatedSignature;
		}
		else {
			return ContentUtil.get(
				PropsValues.MESSAGE_BOARDS_EMAIL_MESSAGE_UPDATED_SIGNATURE);
		}
	}

	public static String getEmailMessageUpdatedSubjectPrefix(
		PortletPreferences preferences) {

		String emailMessageUpdatedSubject = preferences.getValue(
			"email-message-updated-subject-prefix", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageUpdatedSubject)) {
			return emailMessageUpdatedSubject;
		}
		else {
			return ContentUtil.get(
				PropsValues.
					MESSAGE_BOARDS_EMAIL_MESSAGE_UPDATED_SUBJECT_PREFIX);
		}
	}

	public static String getMailId(String mx, long categoryId, long messageId) {
		StringBuilder sb = new StringBuilder();

		sb.append(StringPool.LESS_THAN);
		sb.append(POP_PORTLET_PREFIX);
		sb.append(categoryId);
		sb.append(StringPool.PERIOD);
		sb.append(messageId);
		sb.append(StringPool.AT);
		sb.append(PropsValues.POP_SERVER_SUBDOMAIN);
		sb.append(StringPool.PERIOD);
		sb.append(mx);
		sb.append(StringPool.GREATER_THAN);

		return sb.toString();
	}

	public static String getMailingListAddress(
		long categoryId, long messageId, String mx,
		String defaultMailingListAddress) {

		if (POP_SERVER_SUBDOMAIN_LENGTH <= 0) {
			return defaultMailingListAddress;
		}

		StringBuilder sb = new StringBuilder();

		sb.append(POP_PORTLET_PREFIX);
		sb.append(categoryId);
		sb.append(StringPool.PERIOD);
		sb.append(messageId);
		sb.append(StringPool.AT);
		sb.append(PropsValues.POP_SERVER_SUBDOMAIN);
		sb.append(StringPool.PERIOD);
		sb.append(mx);

		return sb.toString();
	}

	public static long getMessageId(String mailId) {
		int x = mailId.indexOf(StringPool.LESS_THAN) + 1;
		int y = mailId.indexOf(StringPool.AT);

		long messageId = 0;

		if ((x > 0 ) && (y != -1)) {
			String temp = mailId.substring(x, y);

			int z = temp.lastIndexOf(StringPool.PERIOD);

			if (z != -1) {
				messageId = GetterUtil.getLong(temp.substring(z + 1));
			}
		}

		return messageId;
	}

	public static long getParentMessageId(Message message) throws Exception {
		long parentMessageId = -1;

		String parentHeader = getParentMessageIdString(message);

		if (parentHeader != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Parent header " + parentHeader);
			}

			parentMessageId = getMessageId(parentHeader);

			if (_log.isDebugEnabled()) {
				_log.debug("Previous message id " + parentMessageId);
			}
		}

		return parentMessageId;
	}

	public static String getParentMessageIdString(Message message)
		throws Exception {

		// If the previous block failed, try to get the parent message ID from
		// the "References" header as explained in
		// http://cr.yp.to/immhf/thread.html. Some mail clients such as Yahoo!
		// Mail use the "In-Reply-To" header, so we check that as well.

		String parentHeader = null;

		String[] references = message.getHeader("References");

		if ((references != null) && (references.length > 0)) {
			parentHeader = references[0].substring(
				references[0].lastIndexOf("<"));
		}

		if (parentHeader == null) {
			String[] inReplyToHeaders = message.getHeader("In-Reply-To");

			if ((inReplyToHeaders != null) && (inReplyToHeaders.length > 0)) {
				parentHeader = inReplyToHeaders[0];
			}
		}

		if (parentHeader == null) {
			parentHeader = _getParentMessageIdFromSubject(message);
		}

		return parentHeader;
	}

	public static String getSubjectWithoutMessageId(Message message)
		throws Exception {

		String subject = message.getSubject();

		String parentMessageId = _getParentMessageIdFromSubject(message);

		if (Validator.isNotNull(parentMessageId)) {
			int pos = subject.indexOf(parentMessageId);

			if (pos != -1) {
				subject = subject.substring(0, pos);
			}
		}

		return subject;
	}

	public static String[] getThreadPriority(
			PortletPreferences preferences, String languageId, double value,
			ThemeDisplay themeDisplay)
		throws Exception {

		String[] priorities = LocalizationUtil.getPreferencesValues(
			preferences, "priorities", languageId);

		String[] priorityPair = _findThreadPriority(
			value, themeDisplay, priorities);

		if (priorityPair == null) {
			String defaultLanguageId = LocaleUtil.toLanguageId(
				LocaleUtil.getDefault());

			priorities = LocalizationUtil.getPreferencesValues(
				preferences, "priorities", defaultLanguageId);

			priorityPair = _findThreadPriority(value, themeDisplay, priorities);
		}

		return priorityPair;
	}

	public static Date getUnbanDate(MBBan ban, int expireInterval) {
		Date banDate = ban.getCreateDate();

		Calendar cal = Calendar.getInstance();

		cal.setTime(banDate);

		cal.add(Calendar.DATE, expireInterval);

		return cal.getTime();
	}

	public static String getUserRank(
			PortletPreferences preferences, String languageId, int posts)
		throws Exception {

		String rank = StringPool.BLANK;

		String[] ranks = LocalizationUtil.getPreferencesValues(
			preferences, "ranks", languageId);

		for (int i = 0; i < ranks.length; i++) {
			String[] kvp = StringUtil.split(ranks[i], StringPool.EQUAL);

			String kvpName = kvp[0];
			int kvpPosts = GetterUtil.getInteger(kvp[1]);

			if (posts >= kvpPosts) {
				rank = kvpName;
			}
			else {
				break;
			}
		}

		return rank;
	}

	public static String[] getUserRank(
			PortletPreferences preferences, String languageId,
			MBStatsUser statsUser)
		throws Exception {

		String[] rank = {StringPool.BLANK, StringPool.BLANK};

		int maxPosts = 0;

		Group group = GroupLocalServiceUtil.getGroup(
			statsUser.getGroupId());

		long companyId = group.getCompanyId();

		String[] ranks = LocalizationUtil.getPreferencesValues(
			preferences, "ranks", languageId);

		for (int i = 0; i < ranks.length; i++) {
			String[] kvp = StringUtil.split(ranks[i], StringPool.EQUAL);

			String curRank = kvp[0];
			String curRankValue = kvp[1];

			String[] curRankValueKvp = StringUtil.split(
				curRankValue, StringPool.COLON);

			if (curRankValueKvp.length <= 1) {
				int posts = GetterUtil.getInteger(curRankValue);

				if ((posts <= statsUser.getMessageCount()) &&
					(posts >= maxPosts)) {

					rank[0] = curRank;
					maxPosts = posts;
				}

			}
			else {
				String entityType = curRankValueKvp[0];
				String entityValue = curRankValueKvp[1];

				try {
					if (_isEntityRank(
							companyId, statsUser, entityType, entityValue)) {

						rank[1] = curRank;

						break;
					}
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(e);
					}
				}
			}
		}

		return rank;
	}

	public static boolean hasMailIdHeader(Message message) throws Exception {
		String[] messageIds = message.getHeader("Message-ID");

		if (messageIds == null) {
			return false;
		}

		for (String messageId : messageIds) {
			if (messageId.contains(PropsValues.POP_SERVER_SUBDOMAIN)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isAllowAnonymousPosting(
		PortletPreferences preferences) {

		String allowAnonymousPosting = preferences.getValue(
			"allow-anonymous-posting", StringPool.BLANK);

		if (Validator.isNotNull(allowAnonymousPosting)) {
			return GetterUtil.getBoolean(allowAnonymousPosting);
		}
		else {
			return PropsValues.MESSAGE_BOARDS_ANONYMOUS_POSTING_ENABLED;
		}
	}

	private static String[] _findThreadPriority(
		double value, ThemeDisplay themeDisplay, String[] priorities) {

		for (int i = 0; i < priorities.length; i++) {
			String[] priority = StringUtil.split(priorities[i]);

			try {
				String priorityName = priority[0];
				String priorityImage = priority[1];
				double priorityValue = GetterUtil.getDouble(priority[2]);

				if (value == priorityValue) {
					if (!priorityImage.startsWith(Http.HTTP)) {
						priorityImage =
							themeDisplay.getPathThemeImages() + priorityImage;
					}

					return new String[] {priorityName, priorityImage};
				}
			}
			catch (Exception e) {
				_log.error("Unable to determine thread priority", e);
			}
		}

		return null;
	}

	private static String _getParentMessageIdFromSubject(Message message)
		throws Exception {

		String parentMessageId = null;

		String subject = StringUtil.reverse(message.getSubject());

		int pos = subject.indexOf(StringPool.LESS_THAN);

		if (pos != -1) {
			parentMessageId = StringUtil.reverse(subject.substring(0, pos + 1));
		}

		return parentMessageId;
	}

	private static boolean _isEntityRank(
			long companyId, MBStatsUser statsUser, String entityType,
			String entityValue)
		throws Exception {

		long groupId = statsUser.getGroupId();
		long userId = statsUser.getUserId();

		if (entityType.equals("community-role") ||
			entityType.equals("organization-role")) {

			Role role = RoleLocalServiceUtil.getRole(companyId, entityValue);

			if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
					userId, groupId, role.getRoleId())) {

				return true;
			}
		}
		else if (entityType.equals("organization")) {
			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(
					companyId, entityValue);

			if (OrganizationLocalServiceUtil.hasUserOrganization(
					userId, organization.getOrganizationId())) {

				return true;
			}
		}
		else if (entityType.equals("regular-role")) {
			if (RoleLocalServiceUtil.hasUserRole(
					userId, companyId, entityValue, true)) {

				return true;
			}
		}
		else if (entityType.equals("user-group")) {
			UserGroup userGroup = UserGroupLocalServiceUtil.getUserGroup(
				companyId, entityValue);

			if (UserLocalServiceUtil.hasUserGroupUser(
					userGroup.getUserGroupId(), userId)) {

				return true;
			}
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(MigrationMBUtil.class);

}