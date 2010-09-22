package org.climatecollaboratorium.facelets.debates.support;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.facelets.debates.backing.DebatesPermissionsBean;
import org.climatecollaboratorium.facelets.debates.backing.Helper;

import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.service.DebateCommentLocalServiceUtil;
import com.liferay.portal.SystemException;

public class DebateCommentWrapper {
    private DebateComment comment;
    private DebatesPermissionsBean permissions;
    

    public DebateCommentWrapper(DebateComment comment) {
        this.comment = comment;
    }

    public DebateComment getComment() {
        return comment;
    }

    public void setComment(DebateComment comment) {
        this.comment = comment;
    }
    
    public void delete(ActionEvent event) throws SystemException {
        if (permissions.getCanDeleteComment()) {
            comment.delete();
        }
    }

    public DebatesPermissionsBean getPermissions() {
        return permissions;
    }
    public void setPermissions(DebatesPermissionsBean permissions) {
        this.permissions = permissions;
    }
    
    public String getDebateCommentDetail() {
        return Helper.filterContent(comment.getDebateCommentDetail());
    }
}