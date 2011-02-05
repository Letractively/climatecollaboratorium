/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.facelets.debates.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.facelets.debates.DebatesUtil;
import org.climatecollaboratorium.facelets.debates.activity.DebateActivityKeys;
import org.climatecollaboratorium.facelets.debates.backing.Helper;

import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.DebateItemReference;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

/**
 * @author: jintrone
 * @date: Mar 23, 2010
 */
public class DebateItemWrapper {
    private DebateItem item;
    private List<DebateCommentWrapper> comments;
    private ThemeDisplay td = Helper.getThemeDisplay();
    private String newCommentText;

   public DebateItemWrapper(DebateItem item) {
       this.item = item;
   }

    public String getDebateDetail() {
        return Helper.filterContent(item.getDebateDetail());
    }

    public String getDebateSummary() {
        return item.getDebateSummary();
    }

    public User getAuthor() throws SystemException, PortalException {
        return item.getAuthor();
    }

    public Date getUpdated() {
        return item.getUpdated();
    }

    public DebateItem getItem() {
        return item;
    }

    public String getDebatePostType() {
        return item.getDebatePostType();
    }

    public Long getDebateId() {
        return item.getDebateId();
    }

    public Long getDebateItemId() {
        return item.getDebateItemId();
    }
    
    public List<DebateItemReference> getReferences() throws SystemException, PortalException  {
        return item.getReferences();
    }
    public DebateItem getParent() throws SystemException, PortalException {
        return item.getParent();
    }
    
    public int getVotesPercent() throws PortalException, SystemException {
        return item.getVotesPercent();
    }
    
    public Long getVotesCount() throws PortalException, SystemException {
        return item.getVotesCount();
    }
    
    public boolean getHasUserVoted() throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            return item.hasUserVotedForThisItem(Helper.getLiferayUser().getUserId());
        }
        return false;
    }
    
    public List<DebateCommentWrapper> getComments() throws SystemException {
        if (comments == null) {
            comments = new ArrayList<DebateCommentWrapper>();
            for (DebateComment comment: item.getComments()) {
                comments.add(new DebateCommentWrapper(comment, this));
            }
        }
        return comments;
    }
    

    public void vote(ActionEvent event) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            item.voteForThisItem(Helper.getLiferayUser().getUserId());

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    Debate.class.getName(), item.getDebateId(), DebateActivityKeys.VOTE_FOR_POSITION.id(),
                    DebatesUtil.getActivityExtraData(item), 0);
        }
    }

    public void unvote(ActionEvent event) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            item.unvoteThisItem(Helper.getLiferayUser().getUserId());
            
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    Debate.class.getName(), item.getDebateId(), DebateActivityKeys.RETRACT_VOTE_FOR_POSITION.id(),
                    DebatesUtil.getActivityExtraData(item), 0);
        }

    }



    public void setNewCommentText(String newCommentText) {
        this.newCommentText = newCommentText;
    }

    public String getNewCommentText() {
        return newCommentText;
    }
    
    public void addComment(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (newCommentText == null || newCommentText.trim().length() == 0) {
                return;
            }
            
            item.addComment("commentTitle", newCommentText, Helper.getLiferayUser().getUserId());
            newCommentText = null;
            comments = null;
            
            DebateItemType type = DebateItemType.valueOf(item.getDebatePostType());
            if (type.equals(DebateItemType.POSITION)) {
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), Debate.class.getName(),
                        item.getDebateId(), DebateActivityKeys.COMMENT_ON_POSITION.id(), 
                        DebatesUtil.getActivityExtraData(item), 0);

            } else if (type.equals(DebateItemType.ARGUMENT_CON) || type.equals(DebateItemType.ARGUMENT_PRO)) {
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), Debate.class.getName(), 
                        item.getDebateId(), DebateActivityKeys.COMMENT_ON_ARGUMENT.id(), 
                        DebatesUtil.getActivityExtraData(item),
                        0);
            }
            if (type.equals(DebateItemType.QUESTION)) {
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), Debate.class.getName(), 
                        item.getDebateId(), DebateActivityKeys.COMMENT_ON_QUESTION.id(), 
                        DebatesUtil.getActivityExtraData(item),
                        0);

            }
        }
    }



    public void commentRemoved(DebateCommentWrapper debateCommentWrapper) {
        comments.remove(debateCommentWrapper);
        
    }
    
    
    public boolean getCanHaveSubargument() throws PortalException, SystemException {
        DebateItemType type = DebateItemType.valueOf(item.getDebatePostType());
        if (type == DebateItemType.POSITION) {
            return true;
        }
        if (type == DebateItemType.ARGUMENT_CON || type == DebateItemType.ARGUMENT_PRO) {
            // arguments up to level 2 are supported (so there can't be something like position->argument->argument->argument
            return item.getParent().getDebatePostType().equals(DebateItemType.POSITION.name());
        }
        return false;
    }
    
    public boolean getCanHavePosition() {
        DebateItemType type = DebateItemType.valueOf(item.getDebatePostType());
        return type == DebateItemType.QUESTION;
    }
}