package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestTeamMember;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.contests.service.ContestTeamMemberLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class ContestTeamMemberImpl extends ContestTeamMemberModelImpl
    implements ContestTeamMember {
    public ContestTeamMemberImpl() {
        
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            if (getId() == null) {
                setId(CounterLocalServiceUtil.increment(ContestTeamMember.class.getName()));
            }
            ContestTeamMemberLocalServiceUtil.addContestTeamMember(this);
        }
        else {
            ContestTeamMemberLocalServiceUtil.updateContestTeamMember(this);
        }
    }
    
    public void delete() throws SystemException {
        ContestTeamMemberLocalServiceUtil.deleteContestTeamMember(this);
    }
    
    public User getUser() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUserId());
    }
    
    public Contest getContest() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getContest(getContestId());
    }
}
