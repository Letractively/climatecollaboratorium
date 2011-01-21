package org.climatecollaboratorium.facelets.debates.support;

import java.util.Date;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.facelets.debates.backing.DebatesPermissionsBean;
import org.climatecollaboratorium.facelets.debates.backing.Helper;

import com.ext.portlet.debaterevision.model.DebateComment;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;

public class DebateCommentWrapper {
    private DebateComment comment;
    private DebateItemWrapper item;
    

    public DebateCommentWrapper(DebateComment comment, DebateItemWrapper item) {
        this.comment = comment;
        this.item = item;
    }

    public DebateComment getComment() {
        return comment;
    }

    public void setComment(DebateComment comment) {
        this.comment = comment;
    }
    
    public void delete(ActionEvent event) throws SystemException {
        comment.delete();
        item.commentRemoved(this);
    }

    
    public String getDebateCommentDetail() {
        return Helper.filterContent(comment.getDebateCommentDetail());
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return comment.getAuthor();
    }
    
    public Date getUpdated() {
        return comment.getUpdated();
    }
}