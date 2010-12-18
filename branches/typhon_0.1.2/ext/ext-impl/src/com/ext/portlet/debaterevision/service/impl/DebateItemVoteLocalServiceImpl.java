package com.ext.portlet.debaterevision.service.impl;

import java.util.List;

import com.ext.portlet.debaterevision.NoSuchDebateItemVoteException;
import com.ext.portlet.debaterevision.model.DebateItemVote;
import com.ext.portlet.debaterevision.service.base.DebateItemVoteLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class DebateItemVoteLocalServiceImpl
    extends DebateItemVoteLocalServiceBaseImpl {
    
    public List<DebateItemVote> getVotesByDebateItemIdUserId(long debateItemId, long userId) throws NoSuchDebateItemVoteException, SystemException {
        debateItemVotePersistence.clearCache();
        return debateItemVotePersistence.findByDebateItemIdUserId(debateItemId, userId);
    }
    
    public List<DebateItemVote> getVotesByUserId(long userId) throws NoSuchDebateItemVoteException, SystemException {
        debateItemVotePersistence.clearCache();
        return debateItemVotePersistence.findByUserId(userId);
    }
    
    public List<DebateItemVote> getVotesByDebateItemId(long debateItemId) throws SystemException {
        debateItemVotePersistence.clearCache();
        return debateItemVotePersistence.findByDebateItemId(debateItemId);
    }
    
    public void flush() {
        debateItemVotePersistence.clearCache();
    }
    
    public void removeVote(long debateItemId, long userId) {
        try {
            List<DebateItemVote> votes = getVotesByDebateItemIdUserId(debateItemId, userId);
            for (DebateItemVote vote: votes) {
                debateItemVotePersistence.remove(vote.getDebateItemVoteId());
            }
            debateItemVotePersistence.removeByDebateItemIdUserId(debateItemId, userId);
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchDebateItemVoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
}
