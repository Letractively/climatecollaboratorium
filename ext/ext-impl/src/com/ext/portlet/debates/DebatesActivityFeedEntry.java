/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debates;




import javax.portlet.PortletException;

import org.apache.taglibs.standard.lang.jpath.encoding.HtmlEncoder;

import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.debates.action.EditDebateMessageAction;
import com.ext.portlet.plans.PlansActivityFeedEntry;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.portal.theme.ThemeDisplay;

import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

import freemarker.template.utility.HtmlEscape;


public class DebatesActivityFeedEntry extends BaseSocialActivityInterpreter{
	
	
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}
	
	private static Log _log = LogFactoryUtil.getLog(DebatesActivityFeedEntry.class);
	
	
	public static String QUESTION_ADDED = "%s added debate question %s"; //user, question
	public static String QUESTION_EDITED = "%s edited debate question %s"; //user, question
	public static String QUESTION_REMOVED="%s removed debate question %s"; //user, question
	public static String POSITION_ADDED = "%s added position %s to debate question %s"; //user, position, question
	public static String POSITION_EDITED = "%s edited position %s in debate question %s"; //user, position, question
	public static String POSITION_REMOVED = "%s removed position %s from debate question %s"; //user, position, question
	public static String VOTED_POSITION="%s voted for position %s on question %s";//user, position, question
	public static String UNVOTED_POSITION="%s retracted vote for position %s on question %s";//user, position, question
	public static String ARG_ADDED = "%s added argument %s in position %s on question %s"; //user, argument, position, question
	public static String ARG_EDITED = "%s edited argument %s in position %s on question %s"; //user, argument, position, question
	public static String ARG_REMOVED = "%s removed argument %s in position %s on question %s";//user, argument, position, question
	
	
	public static String hyperlink = "<a href=\"%s\">%s</a>";
		
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {
		
				
		DebateActivityKeys activityType = DebateActivityKeys.fromId(activity.getType());
		
		String body =  "";
		String title=activityType.getPrettyName();
		
		if (activityType == DebateActivityKeys.ADD_POSITION) {	
			body = String.format(POSITION_ADDED, getUser(activity),getPosition(activity),getQuestion(activity));
		
		} else if (activityType == DebateActivityKeys.EDIT_POSITION) {
			body = String.format(POSITION_EDITED, getUser(activity),getPosition(activity),getQuestion(activity));
		} else if (activityType == DebateActivityKeys.REMOVE_POSITION) {
			body = String.format(POSITION_REMOVED, getUser(activity),getPosition(activity),getQuestion(activity));
		} else if  (activityType == DebateActivityKeys.ADD_QUESTION) {
			body = String.format(QUESTION_ADDED,getUser(activity),getQuestion(activity));
		} else if (activityType == DebateActivityKeys.EDIT_QUESTION) {
			body = String.format(QUESTION_EDITED,getUser(activity),getQuestion(activity));
		} else if (activityType == DebateActivityKeys.REMOVE_QUESTION) {
			body = String.format(QUESTION_REMOVED, getUser(activity),getQuestion(activity));
		} else if (activityType ==  DebateActivityKeys.VOTE_FOR_POSITION) {
			body = String.format(VOTED_POSITION, getUser(activity),getPosition(activity),getQuestion(activity));
		} else if  (activityType == DebateActivityKeys.RETRACT_VOTE_FOR_POSITION) {
			body = String.format(UNVOTED_POSITION, getUser(activity),getPosition(activity),getQuestion(activity));
		} else if (activityType == DebateActivityKeys.ADD_ARGUMENT) {
			body = String.format(ARG_ADDED, getUser(activity),getArgument(activity),getPosition(activity),getQuestion(activity));	
		} else if (activityType == DebateActivityKeys.EDIT_ARGUMENT) {
			body = String.format(ARG_EDITED, getUser(activity),getArgument(activity),getPosition(activity),getQuestion(activity));	
		} else if (activityType == DebateActivityKeys.REMOVE_ARGUMENT) {
			body = String.format(ARG_REMOVED,getUser(activity),getArgument(activity),getPosition(activity),getQuestion(activity));
		}
		
		return new SocialActivityFeedEntry("", title, body);
	}
		
	private String getUser(SocialActivity activity) {
		String result = "<user removed>";
		try {
			return CommunityUtil.generateUserURL(activity.getUserId());
		} catch (PortalException e) {
			_log.info(e.getMessage());
		} catch (SystemException e) {
			_log.info(e.getMessage());
		}
		return result;
	}
	
	private String getPosition(SocialActivity activity) {
		
		
		
		String result = "<position removed>";
		try {
			MBMessage msg = MBMessageLocalServiceUtil.getMBMessage(Long.parseLong(activity.getExtraData()));;
			while (msg!=null && DebatesUtil.getMessageType(msg.getMessageId())!=DebatesConstants.POSITION_MSG_TYPE) {
				msg = MBMessageLocalServiceUtil.getMBMessage(msg.getParentMessageId());
			}
			result=String.format(hyperlink,DebatesUtil.getMessageURL(msg),msg.getSubject());
		} catch (NumberFormatException e) {
			_log.error(e);
		} catch (PortalException e) {
			_log.info(e.getMessage());
		} catch (SystemException e) {
			_log.info(e.getMessage());
		}
		return result;
	}
	
	private String getArgument(SocialActivity activity) {
		String result = "<argument removed>";
		try {
			MBMessage msg = MBMessageLocalServiceUtil.getMBMessage(Long.parseLong(activity.getExtraData()));;
			result=String.format(hyperlink,DebatesUtil.getMessageURL(msg),msg.getSubject());
		} catch (NumberFormatException e) {
			_log.error(e);
		} catch (PortalException e) {
			_log.info(e.getMessage());
		} catch (SystemException e) {
			_log.info(e.getMessage());
		}
		return result;
	}
	
	private String getQuestion(SocialActivity activity) {
		String result = "<question removed>";
		try {
			MBCategory cat = MBCategoryLocalServiceUtil.getMBCategory(activity.getClassPK());
			result = String.format(hyperlink,HtmlEncoder.encode(DebatesUtil.getCategoryURL(cat)),cat.getName());
		} catch (PortalException e) {
			_log.info(e.getMessage());
		} catch (SystemException e) {
			_log.info(e.getMessage());
		}
		return result;
	}
	
		
	

	private static final String[] _CLASS_NAMES = new String[] {
		EditDebateMessageAction.class.getName()
	};

}
