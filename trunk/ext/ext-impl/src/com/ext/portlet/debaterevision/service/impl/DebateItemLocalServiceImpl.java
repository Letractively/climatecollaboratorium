package com.ext.portlet.debaterevision.service.impl;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.DebateItemVote;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateItemVoteLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateItemVoteStatsLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.debaterevision.service.base.DebateItemLocalServiceBaseImpl;
import com.ext.portlet.debaterevision.service.persistence.DebateItemFinderUtil;
import com.ext.portlet.debaterevision.util.Indexer;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.search.SearchException;

import java.util.List;

public class DebateItemLocalServiceImpl extends DebateItemLocalServiceBaseImpl {

    private static Object voteSynchro = new Object();

    public List<DebateItem> getChildren(DebateItem debateItem) {
        List<DebateItem> result = DebateItemFinderUtil.findByParentInVersion(debateItem.getDebateVersion(), debateItem
                .getDebateItemId());
        for (DebateItem item : result) {
            item.setDebateVersion(debateItem.getDebateVersion());
        }
        return result;
    }

    public DebateItem getParent(DebateItem debateItem) throws SystemException, PortalException {

        Long parentId = debateItem.getDebateItemParentId();
        if (parentId == null || parentId < 0) {
            return null;
        }

        else {
            DebateItem result = DebateItemFinderUtil.getLastItemInVersion(debateItem.getDebateVersion(), parentId);
            result.setDebateVersion(debateItem.getDebateVersion());
            return result;
        }
    }

    public void voteForDebateItem(DebateItem debateItem, Long userId) throws PortalException, SystemException {
        // if user has voted for other position, unvote
        synchronized (voteSynchro) {

            for (DebateItem position : debateItem.getParent().getChildren()) {
                if (hasUserVotedForItem(position, userId)) {
                    unvoteDebateItem(position, userId);
                }
            }

            
            // add vote
            long voteId = CounterLocalServiceUtil.increment(DebateItemVote.class.getName());
            DebateItemVote itemVote = DebateItemVoteLocalServiceUtil.createDebateItemVote(voteId);
            itemVote.setDebateItemId(debateItem.getDebateItemId());
            itemVote.setUserId(userId);

            DebateItemVoteStatsLocalServiceUtil.incrementStats(debateItem.getDebateItemId());

            DebateItemVoteLocalServiceUtil.addDebateItemVote(itemVote);
            DebateItemVoteLocalServiceUtil.flush();
        }
    }

    public void unvoteDebateItem(DebateItem debateItem, Long userId) throws PortalException, SystemException {
        synchronized (voteSynchro) {
            DebateItemVote itemVote = DebateItemVoteLocalServiceUtil.getVotesByDebateItemIdUserId(
                    debateItem.getDebateItemId(), userId).get(0);

            DebateItemVoteLocalServiceUtil.deleteDebateItemVote(itemVote);
            DebateItemVoteStatsLocalServiceUtil.decrementStats(debateItem.getDebateItemId());
            if (hasUserVotedForItem(debateItem, userId)) {
                DebateItemVoteLocalServiceUtil.removeVote(debateItem.getDebateItemId(), userId);
            }
            DebateItemVoteLocalServiceUtil.flush();
            
        }
    }

    public boolean hasUserVotedForItem(DebateItem debateItem, Long userId) throws PortalException, SystemException {
        return DebateItemVoteLocalServiceUtil.getVotesByDebateItemIdUserId(debateItem.getDebateItemId(), userId).size() != 0;
    }

    public DebateItem getLastItemInVersion(long treeVersion, long itemId) {
        return DebateItemFinderUtil.getLastItemInVersion(treeVersion, itemId);
    }

    public DebateItem getLastItem(long itemid) {
        DebateItem item =  DebateItemFinderUtil.getLastItem(itemid);
        if (item == null) return null;
        item.setDebateVersion(DebateLocalServiceUtil.getLastTreeVersion(item.getDebateId()));
        return item;
    }

    public DebateItem getLastActiveItem(long itemid) {
        DebateItem item =  DebateItemFinderUtil.getLastActiveItem(itemid);
        if (item == null) return null;
        //TODO This could be problematic if the last active item is not in the current tree version
        item.setDebateVersion(DebateLocalServiceUtil.getLastTreeVersion(item.getDebateId()));
        return item;

    }

    public List<DebateItem> getHistory(long treeVersion, long itemId) {
        return DebateItemFinderUtil.getHistory(treeVersion, itemId);
    }
    
    public int getItemCommentsCount(long itemId) {
        return debateItemFinder.getDebateItemCommentsCount(itemId);
    }
    
    private final static long defaultCompanyId = 10112L;
    
    public void reIndex() throws SearchException, SystemException {
        for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            for (Debate debate: contest.getDebates()) {
                // index all items
                reindexAllItems(debate.getCurrentRoot());
            }
        
        } 
    }
    
    public void reIndex(long debateItemId) throws SearchException, SystemException {
        Indexer.updateEntry(defaultCompanyId, DebateItemLocalServiceUtil.getLastItem(debateItemId));
    }
    
    private void reindexAllItems(DebateItem debateItem) throws SearchException, SystemException {
        Indexer.updateEntry(defaultCompanyId, debateItem);
        for (DebateItem child: debateItem.getChildren()) {
            reindexAllItems(child);
        }
    }

}
