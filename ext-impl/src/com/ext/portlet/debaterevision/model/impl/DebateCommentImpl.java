package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.DebateComment;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class DebateCommentImpl extends DebateCommentModelImpl
    implements DebateComment {
    public DebateCommentImpl() {
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(this.getAuthorId());
    }
}
