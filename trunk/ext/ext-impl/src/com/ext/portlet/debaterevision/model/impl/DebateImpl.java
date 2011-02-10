package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DebateImpl extends DebateModelImpl implements Debate {
    public DebateImpl() {

   }

    public DebateItem getCurrentRoot() {
        DebateItem item = DebateLocalServiceUtil.getCurrentRoot(this);
        item.setDebateVersion(this.getTreeVersion());
        System.out.println("Current root dla, tree version: " + getTreeVersion() + "\titem,debateVersion: " + item.getDebateVersion());
        return item;
    }

    public Debate copyBackward() throws SystemException {
        long pk = CounterLocalServiceUtil.increment(DebateImpl.class.getName());
        Debate d = DebateLocalServiceUtil.createDebate(pk);
        d.setTreeVersion(this.getTreeVersion()+1);
        d.setDebateId(this.getDebateId());
        d.setRootCommentId(this.getRootCommentId());
        d.setStatus(this.getStatus());
        d.setUpdated(new Date());
        return d;
    }

    public void moveForward() throws SystemException {
        long pk = CounterLocalServiceUtil.increment(DebateImpl.class.getName());
        Debate d = DebateLocalServiceUtil.createDebate(pk);
        d.setTreeVersion(this.getTreeVersion());
        d.setDebateId(this.getDebateId());
        d.setRootCommentId(this.getRootCommentId());
        d.setStatus(this.getStatus());
        d.setUpdated(getUpdated());
        DebateLocalServiceUtil.updateDebate(d);

        setTreeVersion(getTreeVersion()+1);
        setUpdated(new Date());
        DebateLocalServiceUtil.updateDebate(this);
    }

    public int getNumberOfComments() throws SystemException {
       return DebateLocalServiceUtil.getNumberOfComments(this.getDebateId());
    }

    public DebateComment getMostRecentComment() throws SystemException {
       return DebateLocalServiceUtil.getMostRecentComment(this.getDebateId());
    }

   

    public List<DebateItem> getLeadingPositions() throws SystemException, PortalException {

        Long max = -1L;
        List<DebateItem> items = new ArrayList<DebateItem>();
        DebateItem root = this.getCurrentRoot();
        for (DebateItem position: root.getChildren()) {
            if (position.getVotesCount() > max) {
                max = position.getVotesCount();
                items.clear();
                items.add(position);
            } else if (position.getVotesCount() == max && position.getVotesCount() > 0) {
                items.add(position);
            }
        }
        return items;
    }
    
    public int getTotalVotesCount() throws SystemException, PortalException {
        int totalVotes = 0;
        DebateItem root = this.getCurrentRoot();
        for (DebateItem position: root.getChildren()) {
            totalVotes += position.getVotesCount();
        }
        return totalVotes;
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(this.getAuthorId());
    }
}

