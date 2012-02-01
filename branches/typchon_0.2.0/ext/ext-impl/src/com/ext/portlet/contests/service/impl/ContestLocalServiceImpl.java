package com.ext.portlet.contests.service.impl;

import com.ext.portlet.contests.NoSuchContestException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.base.ContestLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class ContestLocalServiceImpl extends ContestLocalServiceBaseImpl {
    
    public Contest getContestByActiveFlag(boolean contestActive) throws NoSuchContestException, SystemException {
        return contestPersistence.findBycontestActive(contestActive);
    }
}
