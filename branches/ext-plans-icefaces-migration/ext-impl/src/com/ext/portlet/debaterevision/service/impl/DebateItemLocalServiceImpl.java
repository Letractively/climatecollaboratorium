package com.ext.portlet.debaterevision.service.impl;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.DebateItemVote;
import com.ext.portlet.debaterevision.service.DebateItemVoteLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateItemVoteStatsLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.debaterevision.service.base.DebateItemLocalServiceBaseImpl;
import com.ext.portlet.debaterevision.service.persistence.DebateItemFinderUtil;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

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
            /*System.out.println("\n\nGłosuje na: " + debateItem.getDebateItemId());
            System.out.println("przed votes count: " + DebateItemVoteLocalServiceUtil.getVotesByUserId(userId).size());
            */
            for (DebateItem position : debateItem.getParent().getChildren()) {
                //System.out.println("czy glosowalem na: " + position.getDebateItemId() + ": " + hasUserVotedForItem(position, userId));
                if (hasUserVotedForItem(position, userId)) {
                    //System.out.println("usuwam glos: " + position.getDebateItemId());
                    unvoteDebateItem(position, userId);
                }
            }
            /*
            System.out.println("po votes count: " + DebateItemVoteLocalServiceUtil.getVotesByUserId(userId).size());
            for (DebateItemVote vote: DebateItemVoteLocalServiceUtil.getVotesByUserId(userId)) {
                System.out.println(" >>>>> " + vote.getDebateItemId());
            }
            */
            
            
            // add vote
            long voteId = CounterUtil.increment(DebateItemVote.class.getName());
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
            
            //System.out.println("removed: " + debateItem.getDebateItemId() + "\tczy sie udalo?: " + hasUserVotedForItem(debateItem, userId));
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

}
