/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.facelets.discussions.activity;


import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;


public class DiscussionActivityFeedEntry extends BaseSocialActivityInterpreter{
	
    private static final String[] _CLASS_NAMES = {
        DiscussionCategoryGroup.class.getName(),
        DiscussionCategory.class.getName(),
        DiscussionMessage.class.getName()
    };
	
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}
	
	private static Log _log = LogFactoryUtil.getLog(DiscussionActivityFeedEntry.class);
	
	public static String CATEGORY_ADDED = "%s added category %s to %s"; // user, categorygroup
	public static String DISCUSSION_ADDED = "%s started new discussion %s in %s"; // user, thread, categorygroup
    public static String COMMENT_ADDED = "%s added comment %s to discussion %s in %s"; // user, comment, thread, categorygroup
	
	public static String hyperlink = "<a href=\"%s\">%s</a>";
		
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {
		
				
		DiscussionActivityKeys activityType = DiscussionActivityKeys.fromId(activity.getType());
		
		String body =  "";
		String title=activityType.getPrettyName();
		
		NavigationUrl navUrl = new NavigationUrl("http://moto-opinie.pl");
		
		
		if (activityType == DiscussionActivityKeys.ADD_CATEGORY) {
		    DiscussionCategory category = DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(activity.getClassPK());
		    DiscussionCategoryGroup categoryGroup = category.getCategoryGroup();
		    
		    body = String.format(CATEGORY_ADDED, getUser(activity), getCategory(category), getCategoryGroup(categoryGroup));
            //body = String.format(CATEGORY_ADDED, navUrl.getUrlWithParameters("discussion", keyValue)getUser(activity), getCategory(category), getCategoryGroup(categoryGroup));
		}
		else if (activityType == DiscussionActivityKeys.ADD_DISCUSSION) {
            DiscussionMessage discussion = DiscussionMessageLocalServiceUtil.getThreadByThreadId(activity.getClassPK());
            DiscussionCategoryGroup categoryGroup = discussion.getCategoryGroup();
            
		    body = String.format(DISCUSSION_ADDED, getUser(activity), getDiscussion(discussion), getCategoryGroup(categoryGroup));
		}
		else if (activityType == DiscussionActivityKeys.ADD_COMMENT) {
            DiscussionMessage comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(activity.getClassPK());
            DiscussionMessage discussion = comment.getThread();
            DiscussionCategoryGroup categoryGroup = comment.getCategoryGroup();
		    
            body = String.format(COMMENT_ADDED, getUser(activity), getComment(comment), getDiscussion(discussion), getCategoryGroup(categoryGroup));
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
	
	public String getCategoryGroup(DiscussionCategoryGroup categoryGroup) {

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
	    return String.format(hyperlink, navUrl.toString(), categoryGroup.getDescription());
	}
	
	public String getCategory(DiscussionCategory category) throws PortalException, SystemException {
	    DiscussionCategoryGroup categoryGroup = category.getCategoryGroup();

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
        
        return String.format(hyperlink, 
                navUrl.getUrlWithParameters("discussion", "pageType", "CATEGORY", "categoryId", category.getCategoryId().toString()).toString(), 
                category.getName());
    }
	
	public String getDiscussion(DiscussionMessage discussion) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = discussion.getCategoryGroup();

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
	    return String.format(hyperlink, 
	            navUrl.getUrlWithParameters("discussion", "pageType", "THREAD", "threadId", discussion.getMessageId().toString()).toString(), 
	            discussion.getSubject());
	}
	
	public String getComment(DiscussionMessage comment) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = comment.getCategoryGroup();
        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
        
        return String.format(hyperlink,
                navUrl.getUrlWithParameters("discussion", "pageType", "THREAD", "threadId", comment.getThreadId().toString(), "messageId", comment.getMessageId().toString()).toString(), 
                comment.getSubject());
    }
	
	
	

}
