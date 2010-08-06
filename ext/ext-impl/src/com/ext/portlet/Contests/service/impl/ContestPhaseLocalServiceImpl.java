package com.ext.portlet.contests.service.impl;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.base.ContestPhaseLocalServiceBaseImpl;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.List;


public class ContestPhaseLocalServiceImpl
    extends ContestPhaseLocalServiceBaseImpl {


    private static Log _log = LogFactoryUtil.getLog(ContestPhaseServiceImpl.class);

    public List<ContestPhase> getPhasesForContest(Contest contest) {
        try {
            return contestPhasePersistence.findByContestId(contest.getContestPK());
        } catch (SystemException e) {
            _log.error(e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return Collections.emptyList();

    }
}
