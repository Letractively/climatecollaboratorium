package com.ext.portlet.contests.service.impl;

import java.util.List;

import com.ext.portlet.contests.model.ContestPhaseColumn;
import com.ext.portlet.contests.service.base.ContestPhaseColumnLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class ContestPhaseColumnLocalServiceImpl
    extends ContestPhaseColumnLocalServiceBaseImpl {
    
    public List<ContestPhaseColumn> getPhaseColumns(Long contestPhasePK) throws SystemException {
        return contestPhaseColumnPersistence.findByContestPhasePK(contestPhasePK);
    }
    
}
