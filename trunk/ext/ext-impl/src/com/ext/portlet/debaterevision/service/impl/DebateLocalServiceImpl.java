package com.ext.portlet.debaterevision.service.impl;

import com.ext.portlet.debaterevision.DebateItemStatus;
import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.impl.DebateImpl;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.debaterevision.service.base.DebateLocalServiceBaseImpl;
import com.ext.portlet.debaterevision.service.persistence.DebateFinderUtil;
import com.ext.portlet.debaterevision.service.persistence.DebateItemFinderUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;

import java.util.Date;
import java.util.List;


public class DebateLocalServiceImpl extends DebateLocalServiceBaseImpl {

    /**
     * Create a debate with root question attached.
     *
     * @return
     */
    public Debate createNewDebate(String title, String detail, long authorId) throws SystemException {
        long pk = CounterLocalServiceUtil.increment(DebateImpl.class.getName());
        Debate result = DebateLocalServiceUtil.createDebate(pk);
        Date now = new Date();

        result.setTreeVersion(0L);
        result.setAuthorId(authorId);
        result.setDebateId(pk);
        result.setStatus(DebateItemStatus.ACTIVE.name());
        result.setUpdated(now);

        


        pk = CounterLocalServiceUtil.increment(DebateItem.class.getName());
        DebateItem root = DebateItemLocalServiceUtil.createDebateItem(pk);
        root.setUpdated(now);
        root.setItemVersion(0L);
        root.setDebateItemId(pk);
        root.setDebateSummary(title);
        root.setDebateDetail(detail);
        root.setAuthorId(authorId);
        root.setDebateId(result.getDebateId());
        root.setTreeVersion(result.getTreeVersion());
        root.setStatus(DebateItemStatus.ACTIVE.name());

        root.setDebatePostType(DebateItemType.QUESTION.name());
        //root.setDebatePostType("QUESTION");

        result.setRootCommentId(root.getDebateItemId());

        DebateItemLocalServiceUtil.updateDebateItem(root);
        DebateLocalServiceUtil.updateDebate(result);
        return result;
     
    }

    public long getLastTreeVersion(long debateId) {
        return findLastVersion(debateId).getTreeVersion();
    }

    public Debate findLastVersion(long debateId) {
        return DebateFinderUtil.getMostRecentVersion(debateId);
    }

    public Debate findByVersion(long debateId, long treeVersion) throws SystemException {
        List<Debate> result = debatePersistence.findBybyIdVersion(debateId,treeVersion);
        if (result!= null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }
    
    public DebateItem getCurrentRoot(Debate debate) {
        return DebateItemFinderUtil.getLastItemInVersion(debate.getTreeVersion(), debate.getRootCommentId());
    }

    public DebateComment getMostRecentComment(long debateId) {
       return DebateFinderUtil.getMostRecentComment(debateId);
    }

    public int getNumberOfComments(long debateId) {
        return DebateFinderUtil.getNumberOfComments(debateId);
    }

    public List<Debate> getDebates() {
        return DebateFinderUtil.getAllRecentActiveDebates();
    }



}
