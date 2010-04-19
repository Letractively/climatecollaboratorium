package com.ext.portlet.debaterevision.service.impl;

import com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException;
import com.ext.portlet.debaterevision.model.DebateItemVoteStats;
import com.ext.portlet.debaterevision.service.DebateItemVoteStatsLocalServiceUtil;
import com.ext.portlet.debaterevision.service.base.DebateItemVoteStatsLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class DebateItemVoteStatsLocalServiceImpl
    extends DebateItemVoteStatsLocalServiceBaseImpl {
    
    
    public void decrementStats(long debateItemId) throws SystemException, NoSuchDebateItemVoteStatsException {
        DebateItemVoteStats stats = getByDebateItemId(debateItemId);
        if (stats.getVotesCount() > 0) { 
            updateStats(debateItemId, stats.getVotesCount()-1);
        }
        
    }

    public DebateItemVoteStats getByDebateItemId(long debateItemId) throws SystemException {
        DebateItemVoteStats stats = null;
        try {
            stats = this.debateItemVoteStatsPersistence.findByDebateItemId(debateItemId);
        } 
        catch (NoSuchDebateItemVoteStatsException e) {
            long statsId = CounterUtil.increment(DebateItemVoteStats.class.getName());
            stats = DebateItemVoteStatsLocalServiceUtil.createDebateItemVoteStats(statsId);
            stats.setDebateItemId(debateItemId);
            stats.setVotesCount(0L);
            DebateItemVoteStatsLocalServiceUtil.addDebateItemVoteStats(stats);
        }
        return stats;
    }
    
    public void incrementStats(long debateItemId) throws SystemException {
        try {
            DebateItemVoteStats stats = this.debateItemVoteStatsPersistence.findByDebateItemId(debateItemId);
            updateStats(debateItemId, stats.getVotesCount()+1);
        } catch (NoSuchDebateItemVoteStatsException e) {
            updateStats(debateItemId, 1);
        }
    }
    
    private void updateStats(long debateItemId, long votesCount) throws SystemException {
        DebateItemVoteStats stats = null;
        try {
            stats = this.debateItemVoteStatsPersistence.findByDebateItemId(debateItemId);
        } 
        catch (NoSuchDebateItemVoteStatsException e) {
            long statsId = CounterUtil.increment(DebateItemVoteStats.class.getName());
            stats = DebateItemVoteStatsLocalServiceUtil.createDebateItemVoteStats(statsId);
            stats.setDebateItemId(debateItemId);
        }
        stats.setVotesCount(votesCount);
        DebateItemVoteStatsLocalServiceUtil.updateDebateItemVoteStats(stats);
    }
}
