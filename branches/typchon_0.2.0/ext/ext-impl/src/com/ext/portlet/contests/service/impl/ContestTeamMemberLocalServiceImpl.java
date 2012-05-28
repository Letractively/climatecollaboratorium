package com.ext.portlet.contests.service.impl;

import java.util.List;

import com.ext.portlet.contests.model.ContestTeamMember;
import com.ext.portlet.contests.model.impl.ContestTeamMemberImpl;
import com.ext.portlet.contests.service.base.ContestTeamMemberLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class ContestTeamMemberLocalServiceImpl
    extends ContestTeamMemberLocalServiceBaseImpl {
    
    public ContestTeamMember addContestTeamMember(Long userId, Long contestPk, String role) throws SystemException {
        ContestTeamMember member = new ContestTeamMemberImpl();
        member.setUserId(userId);
        member.setContestId(contestPk);
        member.setRole(role);
        member.store();
        
        return member;
    }
    
    public List<ContestTeamMember> findForContest(Long contestPk) throws SystemException {
        return contestTeamMemberPersistence.findByContestId(contestPk);
    }
}
