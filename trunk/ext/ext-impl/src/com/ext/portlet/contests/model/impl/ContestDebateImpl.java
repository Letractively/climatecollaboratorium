package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestDebate;
import com.ext.portlet.contests.service.ContestDebateLocalServiceUtil;
import com.liferay.portal.SystemException;


public class ContestDebateImpl extends ContestDebateModelImpl
    implements ContestDebate {
    public ContestDebateImpl() {
    }
    
    
    public void store() throws SystemException {
        if (isNew()) {
            ContestDebateLocalServiceUtil.addContestDebate(this);
        }
        else {
            ContestDebateLocalServiceUtil.updateContestDebate(this);
        }
    }
    
    public void delete() throws SystemException {
        if (isNew()) {
            // ignore
        }
        else {
            ContestDebateLocalServiceUtil.deleteContestDebate(this);
        }
    }
    
}
