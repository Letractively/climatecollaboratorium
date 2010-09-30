package com.ext.portlet.contests.service.impl;

import java.util.List;

import com.ext.portlet.contests.model.ContestDebate;
import com.ext.portlet.contests.service.base.ContestDebateLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class ContestDebateLocalServiceImpl
    extends ContestDebateLocalServiceBaseImpl {
    

    public ContestDebate createContestDebate(Long debateId, Long contestId) throws SystemException {
        Long id = CounterLocalServiceUtil.increment(ContestDebate.class.getName());
        
        ContestDebate contestDebate = createContestDebate(id);
        contestDebate.setDebateId(debateId);
        contestDebate.setContestPK(contestId);
        
        contestDebate.store();
        
        return contestDebate;
    }
    
    public List<ContestDebate> getContestDebates(Long contestId) throws SystemException {
        return contestDebatePersistence.findByContestPK(contestId);
    }
}
