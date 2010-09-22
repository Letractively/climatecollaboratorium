package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.service.DebateCommentLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.debaterevision.util.Indexer;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class DebateCommentImpl extends DebateCommentModelImpl
    implements DebateComment {
    
    private final static Log _log = LogFactoryUtil.getLog(DebateCommentImpl.class);
    public DebateCommentImpl() {
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(this.getAuthorId());
    }
    
    public void delete() throws SystemException {
        DebateCommentLocalServiceUtil.deleteDebateComment(this);
        try {
            Indexer.updateEntry(10112L, DebateItemLocalServiceUtil.getLastItem(this.getDebateItemId()));
        } catch (SearchException e) {
            _log.error("Can't refresh debate item search index", e);
        }
    }
}
