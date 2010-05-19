package com.ext.portlet.debaterevision.service.impl;

import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.service.base.DebateCommentLocalServiceBaseImpl;
import com.liferay.portal.SystemException;

import java.util.List;


public class DebateCommentLocalServiceImpl
    extends DebateCommentLocalServiceBaseImpl {


    public List<DebateComment> getCommentsForItem(long debateItemId) throws SystemException {
      return debateCommentPersistence.findBycommentForDebateItem(debateItemId);
    }
}
