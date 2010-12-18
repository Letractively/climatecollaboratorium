/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.liferay.documentlibrary.DuplicateDirectoryException;
import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.social.BlogsActivityKeys;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.messageboards.MessageBodyException;
import com.liferay.portlet.messageboards.MessageSubjectException;
import com.liferay.portlet.messageboards.NoSuchDiscussionException;
import com.liferay.portlet.messageboards.RequiredMessageException;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBDiscussion;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.model.impl.MBMessageDisplayImpl;
import com.liferay.portlet.messageboards.model.impl.MBMessageImpl;
import com.liferay.portlet.messageboards.model.impl.MBThreadImpl;
import com.liferay.portlet.messageboards.service.base.MBMessageLocalServiceBaseImpl;
import com.liferay.portlet.messageboards.service.impl.MBMessageLocalServiceImpl;
import com.liferay.portlet.messageboards.social.MBActivityKeys;
import com.liferay.portlet.messageboards.util.Indexer;
import com.liferay.portlet.messageboards.util.MBUtil;
import com.liferay.portlet.messageboards.util.MailingListThreadLocal;
import com.liferay.portlet.messageboards.util.comparator.MessageThreadComparator;
import com.liferay.portlet.messageboards.util.comparator.ThreadLastPostDateComparator;
import com.liferay.portlet.social.model.SocialActivity;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

import org.apache.commons.lang.time.StopWatch;


public class CustomMBMessageLocalServiceImpl extends MBMessageLocalServiceImpl {

	public MBMessage addMessage(
			long userId, String userName, long categoryId, String subject,
			String body, List<ObjectValuePair<String, byte[]>> files,
			boolean anonymous, double priority, ServiceContext serviceContext)
		throws PortalException, SystemException {

		long threadId = 0;
		long parentMessageId = 0;

		return addMessage(
			null, userId, userName, categoryId, threadId, parentMessageId,
			subject, body, files, anonymous, priority, serviceContext);
	}

	public MBMessage addMessage(
			long userId, String userName, long categoryId, long threadId,
			long parentMessageId, String subject, String body,
			List<ObjectValuePair<String, byte[]>> files, boolean anonymous,
			double priority, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return addMessage(
			null, userId, userName, categoryId, threadId, parentMessageId,
			subject, body, files, anonymous, priority, serviceContext);
	}

	public MBMessage addMessage(
			String uuid, long userId, String userName, long categoryId,
			long threadId, long parentMessageId, String subject, String body,
			List<ObjectValuePair<String, byte[]>> files, boolean anonymous,
			double priority, ServiceContext serviceContext)
		throws PortalException, SystemException {

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		// Message

		User user = userPersistence.findByPrimaryKey(userId);
		userName = user.isDefaultUser() ? userName : user.getFullName();
		MBCategory category = mbCategoryPersistence.findByPrimaryKey(
			categoryId);
		subject = ModelHintsUtil.trimString(
			MBMessage.class.getName(), "subject", subject);

		PortletPreferences preferences =
			ServiceContextUtil.getPortletPreferences(serviceContext);

		if (preferences != null) {
			if (!MBUtil.isAllowAnonymousPosting(preferences)) {
				if (anonymous || user.isDefaultUser()) {
					throw new PrincipalException();
				}
			}
		}

		if (user.isDefaultUser()) {
			anonymous = true;
		}

		Date now = new Date();

		validate(subject, body);

		long messageId = counterLocalService.increment();

		logAddMessage(messageId, stopWatch, 1);

		MBMessage message = mbMessagePersistence.create(messageId);

		message.setUuid(uuid);
		message.setGroupId(category.getGroupId());
		message.setCompanyId(user.getCompanyId());
		message.setUserId(user.getUserId());
		message.setUserName(userName);
		message.setCreateDate(now);
		message.setModifiedDate(now);

		// Thread

		MBMessage parentMessage = mbMessagePersistence.fetchByPrimaryKey(
			parentMessageId);

		if (parentMessage == null) {
			parentMessageId = MBMessageImpl.DEFAULT_PARENT_MESSAGE_ID;
		}

		MBThread thread = null;

		if (threadId > 0) {
			thread = mbThreadPersistence.fetchByPrimaryKey(threadId);
		}

		if ((thread == null) ||
			(parentMessageId == MBMessageImpl.DEFAULT_PARENT_MESSAGE_ID)) {

			threadId = counterLocalService.increment();

			thread = mbThreadPersistence.create(threadId);

			thread.setGroupId(category.getGroupId());
			thread.setCategoryId(categoryId);
			thread.setRootMessageId(messageId);

			category.setThreadCount(category.getThreadCount() + 1);
		}

		thread.setMessageCount(thread.getMessageCount() + 1);

		if (anonymous) {
			thread.setLastPostByUserId(0);
		}
		else {
			thread.setLastPostByUserId(userId);
		}

		thread.setLastPostDate(now);

		if ((priority != MBThreadImpl.PRIORITY_NOT_GIVEN) &&
			(thread.getPriority() != priority)) {

			thread.setPriority(priority);

			updatePriorities(thread.getThreadId(), priority);
		}

		logAddMessage(messageId, stopWatch, 2);

		// Message

		message.setCategoryId(categoryId);
		message.setThreadId(threadId);
		message.setParentMessageId(parentMessageId);
		message.setSubject(subject);
		message.setBody(body);
		message.setAttachments(!files.isEmpty());
		message.setAnonymous(anonymous);

		if (priority != MBThreadImpl.PRIORITY_NOT_GIVEN) {
			message.setPriority(priority);
		}

		// Attachments

		if (files.size() > 0) {
			long companyId = message.getCompanyId();
			String portletId = CompanyConstants.SYSTEM_STRING;
			long groupId = GroupConstants.DEFAULT_PARENT_GROUP_ID;
			long repositoryId = CompanyConstants.SYSTEM;
			String dirName = message.getAttachmentsDir();

			try {
				dlService.deleteDirectory(
					companyId, portletId, repositoryId, dirName);
			}
			catch (NoSuchDirectoryException nsde) {
				if (_log.isDebugEnabled()) {
					_log.debug(nsde.getMessage());
				}
			}

			dlService.addDirectory(companyId, repositoryId, dirName);

			for (int i = 0; i < files.size(); i++) {
				ObjectValuePair<String, byte[]> ovp = files.get(i);

				String fileName = ovp.getKey();
				byte[] bytes = ovp.getValue();

				try {
					dlService.addFile(
						companyId, portletId, groupId, repositoryId,
						dirName + "/" + fileName, 0, StringPool.BLANK,
						message.getModifiedDate(), new String[0], new String[0],
						bytes);
				}
				catch (DuplicateFileException dfe) {
					if (_log.isDebugEnabled()) {
						_log.debug(dfe.getMessage());
					}
				}
			}
		}

		logAddMessage(messageId, stopWatch, 3);

		// Commit

		mbThreadPersistence.update(thread, false);
		mbMessagePersistence.update(message, false);

		logAddMessage(messageId, stopWatch, 4);

		// Resources

		if (!category.isDiscussion()) {
			if (user.isDefaultUser()) {
				addMessageResources(message, true, true);
			}
			if (serviceContext.getAddCommunityPermissions() ||
				serviceContext.getAddGuestPermissions()) {

				addMessageResources(
					message, serviceContext.getAddCommunityPermissions(),
					serviceContext.getAddGuestPermissions());
			}
			else {
				addMessageResources(
					message, serviceContext.getCommunityPermissions(),
					serviceContext.getGuestPermissions());
			}
		}

		logAddMessage(messageId, stopWatch, 5);

		// Statistics

		if (!category.isDiscussion()) {
			mbStatsUserLocalService.updateStatsUser(
				message.getGroupId(), userId, now);
		}

		logAddMessage(messageId, stopWatch, 6);

		// Category

		category.setMessageCount(category.getMessageCount() + 1);
		category.setLastPostDate(now);

		mbCategoryPersistence.update(category, false);

		logAddMessage(messageId, stopWatch, 7);

		// Subscriptions

		notifySubscribers(category, message, serviceContext, false);

		logAddMessage(messageId, stopWatch, 8);

		// Social
		Map<String, Serializable> attributes = serviceContext.getAttributes();
		int messageType = -1;
		if (attributes.containsKey(ActivityConstants.MESSAGE_TYPE)) {
		    try {
		        messageType = (Integer) attributes.get(ActivityConstants.MESSAGE_TYPE);
		    } catch (Exception e) {
		        _log.error(e);
		    }
		}

		if (!message.isDiscussion() && !message.isAnonymous() &&
			!user.isDefaultUser() && messageType != ActivityConstants.DEBATES_DEBATE_MESSAGE) {

			int activityType = MBActivityKeys.ADD_MESSAGE;
			long receiverUserId = 0;

			if (parentMessage != null) {
				activityType = MBActivityKeys.REPLY_MESSAGE;
				receiverUserId = parentMessage.getUserId();
			}

			socialActivityLocalService.addActivity(
				userId, message.getGroupId(), MBMessage.class.getName(),
				messageId, activityType, ActivityUtil.encodeMap(serviceContext.getAttributes()), receiverUserId);
		}

		logAddMessage(messageId, stopWatch, 9);

		// Tags

		updateTagsAsset(userId, message, serviceContext.getTagsEntries());

		logAddMessage(messageId, stopWatch, 10);

		// Testing roll back

		/*if (true) {
			throw new SystemException("Testing roll back");
		}*/

		// Indexer

		reIndex(message);

		logAddMessage(messageId, stopWatch, 11);

		return message;
	}

	private static Log _log =
		LogFactoryUtil.getLog(CustomMBMessageLocalServiceImpl.class);

}