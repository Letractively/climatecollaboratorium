/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.taglibs.standard.lang.jpath.encoding.HtmlEncoder;

import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.debates.DebatesUtil;
import com.ext.portlet.migration.MigrationHelper;
import com.ext.portlet.plans.NoSuchPlanException;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.permission.MBMessagePermission;
import com.liferay.portlet.messageboards.social.MBActivityKeys;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;


public class MyMBActivityInterpreter extends BaseSocialActivityInterpreter {

	private final static Log _log = LogFactoryUtil.getLog(MyMBActivityInterpreter.class);

	private final static String DEBATES_DISCUSSION_MESSAGE_LINK = "/web/guest/debates?p_p_id=debates&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_debates_struts_action=%2Fext%2Fdebates%2Fview_message&_debates_messageId=MESSAGE_ID&_debates_viewDebatesIndexTab=discussion#_debates_message_MESSAGE_ID";

	private final static String MODELS_DISCUSSION_MESSAGE_LINK = "/web/guest/models?p_p_id=models&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_models_struts_action=%2Fext%2Fmodels%2Fview_message&_models_messageId=MESSAGE_ID&_models_viewDebatesIndexTab=discussion&_models_modelId=MODEL_ID&_models_modelName=MODEL_NAME&_models_innerModelTabView=discussion#_models_message_MESSAGE_ID";

	private final static String PLANS_DISCUSSION_MESSAGE_LINK = "/web/guest/plans?p_p_id=plans&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_plans_struts_action=%2Fext%2Fplans%2Fview_plan&_plans_planId=PLAN_ID&_plans_viewPlanTabs=discussion&_plans_messageId=MESSAGE_ID";
	private final static String MODELS_MODEL_LINK = "/web/guest/models?p_p_id=models&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_models_struts_action=%2Fext%2Fmodels%2Frun_model&_models_modelId=MODEL_ID&_models_modelName=MODEL_NAME";

	private final static String MESSAGE_ID = "MESSAGE_ID";
	private final static String MODEL_ID = "MODEL_ID";
	private final static String MODEL_NAME = "MODEL_NAME";
	private final static String PLAN_ID = "PLAN_ID";

	private final static String ADD_DEBATES_DISCUSSION_MESSAGE = "%s posted new debates discussion thread %s";
	private final static String REPLY_DEBATES_DISCUSSION_MESSAGE = "%s sent reply %s to %s in debates discussion board";

	private final static String ADD_DEBATES_DEBATE_DISCUSSION_MESSAGE = "%s posted new discussion thread %s in %s debate discussion";
	private final static String REPLY_DEBATES_DEBATE_DISCUSSION_MESSAGE = "%s sent reply %s to %s in %s debate discussion board";

	private final static String ADD_PLANS_DISCUSSION_MESSAGE = "%s posted new discussion thread %s in %s plan's discussion board";
	private final static String REPLY_PLANS_DISCUSSION_MESSAGE = "%s sent reply %s to %s in %s plan's discussion board";

	private final static String ADD_MODELS_DISCUSSION_MESSAGE = "%s posted new discussion thread %s in %s model's discussion board";
	private final static String REPLY_MODELS_DISCUSSION_MESSAGE = "%s sent reply %s to %s in %s model's discussion board";

	private final static String UNAVAILABLE = "&lt;unavilable&gt;";

	private final static String DELETED = "&lt;deleted&gt;";

	public static String hyperlink = "<a href=\"%s\">%s</a>";

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialActivityFeedEntry doInterpret(SocialActivity activity, ThemeDisplay themeDisplay) throws Exception {

		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();

		if (!MBMessagePermission.contains(permissionChecker, activity.getClassPK(), ActionKeys.VIEW)) {

			return null;
		}

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String creatorUserName = CommunityUtil.generateUserURL(activity.getUserId());

		String receiverUserName = CommunityUtil.generateUserURL(activity.getReceiverUserId());

		int activityType = activity.getType();

		// Link

		MBMessage message = MBMessageLocalServiceUtil.getMessage(activity.getClassPK());

		String link = themeDisplay.getURLPortal() + themeDisplay.getPathMain()
				+ "/message_boards/find_message?messageId=" + message.getMessageId();

		// Title

		String titlePattern = null;

		if (activityType == MBActivityKeys.ADD_MESSAGE) {
			titlePattern = "activity-message-boards-add-message";
		} else if (activityType == MBActivityKeys.REPLY_MESSAGE) {
			titlePattern = "activity-message-boards-reply-message";
		}

		Map<String, String> parameters = ActivityUtil.decodeMap(activity.getExtraData());

		int messageType = ActivityConstants.PLAN_MESSAGE;
		if (parameters.containsKey(ActivityConstants.MESSAGE_TYPE)) {
			try {
				messageType = Integer.parseInt(parameters.get(ActivityConstants.MESSAGE_TYPE));
			} catch (Exception e) {
				_log.error(e);
			}
		}
		if (messageType == ActivityConstants.DEBATES_DEBATE_MESSAGE) {
			return new SocialActivityFeedEntry("", "Debate entry", "");
		}

		String messageLink = "";
		String body = "";
		switch (messageType) {
		case ActivityConstants.PLAN_MESSAGE:
			// get plan for message
			long planGroupId = message.getGroupId();
			Plan plan = null;
			try {
				PlanLocalServiceHelper.getPlanByGroupId(planGroupId);
			} catch (NoSuchPlanException ex) {
				// ignore
				_log.warn("No such plan for " + planGroupId);
			}

			messageLink = plan != null ? PLANS_DISCUSSION_MESSAGE_LINK.replaceAll(PLAN_ID, String.valueOf(plan
					.getPlanId())) : "<undefined>";

			if (activityType == MBActivityKeys.ADD_MESSAGE) {
				String messageText = UNAVAILABLE;
				if (plan != null) {
					messageLink = messageLink.replaceAll(MESSAGE_ID, String.valueOf(message.getMessageId()));
					messageText = String.format(hyperlink, HtmlEncoder.encode(messageLink), message.getSubject());
				}
				body = String.format(ADD_PLANS_DISCUSSION_MESSAGE, getUser(activity), messageText, getPlan(plan));
			} else if (activityType == MBActivityKeys.REPLY_MESSAGE) {
				String parentMessageText = UNAVAILABLE;
				String messageText = UNAVAILABLE;
				if (plan != null) {
					String parentMessageLink = messageLink.replaceAll(MESSAGE_ID, String.valueOf(message
							.getParentMessageId()));
					MBMessage parentMessage = MBMessageLocalServiceUtil.getMessage(message.getParentMessageId());
					messageLink = messageLink.replaceAll(MESSAGE_ID, String.valueOf(message.getMessageId()));

					messageText = String.format(hyperlink, HtmlEncoder.encode(messageLink), message.getSubject());
					parentMessageText = String.format(hyperlink, HtmlEncoder.encode(parentMessageLink), parentMessage
							.getSubject());
				}
				body = String.format(REPLY_PLANS_DISCUSSION_MESSAGE, getUser(activity), messageText, parentMessageText,
						getPlan(plan));
			}

			break;

		case ActivityConstants.MODEL_MESSAGE:
			String modelId = parameters.get(ActivityConstants.MODEL_ID);
			String modelName = parameters.get(ActivityConstants.MODEL_NAME);
			messageLink = MODELS_DISCUSSION_MESSAGE_LINK.replaceAll(MODEL_NAME, URLEncoder.encode(modelName, "UTF-8"));
			messageLink = messageLink.replaceAll(MODEL_ID, modelId);
			String parentMessageText = "";

			if (message.getParentMessageId() > 0) {
				String parentMessageLink = messageLink.replaceAll(MESSAGE_ID, String.valueOf(message
						.getParentMessageId()));
				MBMessage parentMessage = MBMessageLocalServiceUtil.getMessage(message.getParentMessageId());
				parentMessageText = String.format(hyperlink, HtmlEncoder.encode(parentMessageLink), parentMessage
						.getSubject());
			}
			messageLink = messageLink.replaceAll(MESSAGE_ID, String.valueOf(message.getMessageId()));
			String messageText = String.format(hyperlink, HtmlEncoder.encode(messageLink), message.getSubject());

			String modelLink = MODELS_MODEL_LINK.replaceAll(MODEL_NAME, modelName);
			modelLink = modelLink.replaceAll(MODEL_ID, modelId);
			String modelText = String.format(hyperlink, HtmlEncoder.encode(modelLink), modelName);

			if (activityType == MBActivityKeys.ADD_MESSAGE) {
				body = String.format(ADD_MODELS_DISCUSSION_MESSAGE, getUser(activity), messageText, modelText);
			} else if (activityType == MBActivityKeys.REPLY_MESSAGE) {
				body = String.format(REPLY_MODELS_DISCUSSION_MESSAGE, getUser(activity), messageText,
						parentMessageText, modelText);
			}
			break;

		case ActivityConstants.DEBATES_DISCUSSION_MESSAGE:
			messageLink = DEBATES_DISCUSSION_MESSAGE_LINK
					.replaceAll(MESSAGE_ID, String.valueOf(message.getMessageId()));

			parentMessageText = "";
			if (message.getParentMessageId() > 0) {
				String parentMessageLink = messageLink.replaceAll(MESSAGE_ID, String.valueOf(message
						.getParentMessageId()));
				MBMessage parentMessage = MBMessageLocalServiceUtil.getMessage(message.getParentMessageId());
				parentMessageText = String.format(hyperlink, HtmlEncoder.encode(parentMessageLink), parentMessage
						.getSubject());
			}
			messageText = String.format(hyperlink, HtmlEncoder.encode(messageLink), message.getSubject());
			if (activityType == MBActivityKeys.ADD_MESSAGE) {
				body = String.format(ADD_DEBATES_DISCUSSION_MESSAGE, getUser(activity), messageText);
			} else if (activityType == MBActivityKeys.REPLY_MESSAGE) {
				body = String.format(REPLY_DEBATES_DISCUSSION_MESSAGE, getUser(activity), messageText,
						parentMessageText);
			}
			break;

		}

		if (Validator.isNotNull(groupName)) {
			titlePattern += "-in";
		}

		String messageSubject = wrapLink(link, cleanContent(message.getSubject()));

		Object[] titleArguments = new Object[] { groupName, creatorUserName, receiverUserName, messageSubject };

		String title = themeDisplay.translate(titlePattern, titleArguments);
		/*
		 * // Body
		 * 
		 * //String categoryLink = // themeDisplay.getURLPortal() + themeDisplay.getPathMain() + //
		 * "/message_boards/find_category?categoryId=" + // message.getCategoryId();
		 * 
		 * String body = (messageLink, "Go to message", themeDisplay) : "");
		 */
		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] { MBMessage.class.getName() };

	private String getUser(SocialActivity activity) {
		String result = "&lt;user removed&gt;";
		try {
			return CommunityUtil.generateUserURL(activity.getUserId());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private String getQuestion(MBMessage msg) {
		String result = "&lt;question removed&gt;";
		try {
			MBCategory cat = MBCategoryLocalServiceUtil.getCategory(msg.getCategory().getParentCategoryId());
			result = String.format(hyperlink, HtmlEncoder.encode(DebatesUtil.getCategoryURL(cat)), cat.getName());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private String getPlan(Plan plan) {

		return plan == null ? "&lt;plan deleted&gt;" : String.format(hyperlink,
				PlanLocalServiceHelper.getPlanURL(plan), plan.getName());
	}

}