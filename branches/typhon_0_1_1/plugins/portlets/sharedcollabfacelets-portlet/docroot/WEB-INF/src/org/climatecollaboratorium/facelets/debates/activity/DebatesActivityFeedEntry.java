/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.facelets.debates.activity;


import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import org.climatecollaboratorium.facelets.debates.DebatesUtil;
import org.climatecollaboratorium.facelets.debates.backing.EditDebateCategoryBean;
import org.climatecollaboratorium.facelets.debates.backing.EditDebateCommentBean;
import org.climatecollaboratorium.facelets.debates.backing.EditDebateItemBean;


public class DebatesActivityFeedEntry extends BaseSocialActivityInterpreter{
	
	
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}
	
	private static Log _log = LogFactoryUtil.getLog(DebatesActivityFeedEntry.class);
	
	
	public static String QUESTION_ADDED_TO_CATEGORY = "%s added debate question %s to category %s"; //user, question, category
	public static String QUESTION_EDITED = "%s edited debate question %s"; //user, question, category
	public static String QUESTION_REMOVED="%s removed debate question %s from category %s"; //user, question, category
    public static String QUESTION_REMOVED_FROM_CATEGORY="%s removed debate question %s from category %s"; //user, question, category
    public static String POSITION_ADDED = "%s added position %s to debate question %s"; //user, position, question
	public static String POSITION_EDITED = "%s edited position %s in debate question %s"; //user, position, question
	public static String POSITION_REMOVED = "%s removed position %s from debate question %s"; //user, position, question
	public static String VOTED_POSITION="%s voted for position %s on question %s";//user, position, question
	public static String UNVOTED_POSITION="%s retracted vote for position %s on question %s";//user, position, question
	public static String ARG_ADDED = "%s added argument %s in position %s on question %s"; //user, argument, position, question
	public static String ARG_EDITED = "%s edited argument %s in position %s on question %s"; //user, argument, position, question
	public static String ARG_REMOVED = "%s removed argument %s in position %s on question %s";//user, argument, position, question
    public static String COMMENT_QUESTION = "%s commented question %s";//user, question
    public static String COMMENT_POSITION = "%s commented position %s on question %s";//user, position, question
    public static String COMMENT_ARG = "%s commented argument %s in position %s on question %s";//user, argument, position, question
	
	
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
		} else if  (activityType == DebateActivityKeys.ADD_QUESTION_TO_CATEGORY) {
			body = String.format(QUESTION_ADDED_TO_CATEGORY,getUser(activity),getQuestionFromExtraData(activity),getCategory(activity));
		} else if (activityType == DebateActivityKeys.EDIT_QUESTION) {
			body = String.format(QUESTION_EDITED,getUser(activity),getQuestion(activity));
		} else if (activityType == DebateActivityKeys.REMOVE_QUESTION_FROM_CATEGORY) {
            body = String.format(QUESTION_REMOVED_FROM_CATEGORY, getUser(activity),getQuestionFromExtraData(activity),getCategory(activity));
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
		} else if (activityType == DebateActivityKeys.COMMENT_ON_ARGUMENT) {
		    body = String.format(COMMENT_ARG,getUser(activity),getArgument(activity),getPosition(activity),getQuestion(activity));
		} else if (activityType == DebateActivityKeys.COMMENT_ON_POSITION) {
            body = String.format(COMMENT_POSITION,getUser(activity),getPosition(activity),getQuestion(activity));
        } else if (activityType == DebateActivityKeys.COMMENT_ON_QUESTION) {
            body = String.format(COMMENT_QUESTION,getUser(activity),getQuestion(activity));
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
		    DebateItem item = getDebateitem(activity);
		    while (! item.getDebatePostType().equals(DebateItemType.POSITION.toString())) {
		        item = item.getParent();
		    }
		    result=String.format(hyperlink, DebatesUtil.getItemURL(item), item.getDebateSummary());
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
            DebateItem item = getDebateitem(activity);
            result=String.format(hyperlink, DebatesUtil.getItemURL(item), item.getDebateSummary());
		} catch (NumberFormatException e) {
			_log.error(e);
		}
		return result;
	}
	
	private String getQuestion(SocialActivity activity) {
		String result = "<question removed>";
		try {
            DebateItem item = getDebateitem(activity);
            item = item.getDebate().getCurrentRoot();

            result=String.format(hyperlink, DebatesUtil.getItemURL(item), item.getDebateSummary());
		} catch (SystemException e) {
			_log.info(e.getMessage());
		}
		return result;
	}

    private String getQuestionFromExtraData(SocialActivity activity) {
		String result = "<question removed>";
		try {
            DebateItem item = getDebateitemFromExtraData(activity);
            result=String.format(hyperlink, DebatesUtil.getItemURL(item), item.getDebateSummary());
		} catch (Exception e) {
			_log.info(e.getMessage());
		}
		return result;
	}

    private String getCategory(SocialActivity activity) {
		String result = "<category removed>";
		try {
            DebateCategory item= DebateCategoryLocalServiceUtil.getDebateCategory(activity.getClassPK());
            result=String.format(hyperlink, DebatesUtil.getCategoryURL(item), item.getTitle());
		} catch (Exception e) {
			_log.info(e.getMessage());
		}
		return result;
	}
	
		
	

	private static final String[] _CLASS_NAMES = new String[] {
	        DebateCategory.class.getName(),
            DebateItem.class.getName(),
	    DebateComment.class.getName(),
	    Debate.class.getName(),
		EditDebateItemBean.class.getName(),
		EditDebateCategoryBean.class.getName(),
		EditDebateCommentBean.class.getName()
	};

    private DebateItem getDebateitemFromExtraData(SocialActivity activity) {
      Long itemid = Long.parseLong(activity.getExtraData());
        try {
            return DebateItemLocalServiceUtil.getLastActiveItem(itemid);
        } catch (Exception e) {

            _log.info(e.getMessage());
        }
        return null;
    }

	private DebateItem getDebateitem(SocialActivity activity) {
        return DebateItemLocalServiceUtil.getLastActiveItem(activity.getClassPK());
	}

}
