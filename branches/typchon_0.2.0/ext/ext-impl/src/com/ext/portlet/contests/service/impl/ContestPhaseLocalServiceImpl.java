package com.ext.portlet.contests.service.impl;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.base.ContestPhaseLocalServiceBaseImpl;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ContestPhaseLocalServiceImpl
    extends ContestPhaseLocalServiceBaseImpl {


    public List<ContestPhase> getPhasesForContest(Contest contest) throws SystemException {
        List<ContestPhase> result = new ArrayList<ContestPhase>();
        return contestPhasePersistence.findByContestId(contest.getContestPK());
    }

    public ContestPhase getActivePhaseForContest(Contest contest) throws NoSuchContestPhaseException, SystemException {
        Date now = new Date();
        try {
        	contestPhasePersistence.findByPhaseActiveOverride_Last(contest.getContestPK(), true, new OrderByComparator() {
				
				@Override
				public int compare(Object arg0, Object arg1) {
					if (((ContestPhase) arg0).getPhaseStartDate().before(((ContestPhase) arg1).getPhaseStartDate())) {
						return -1;
					}
					return 1;
				}
			});
        }
        catch (NoSuchContestPhaseException e) {
        	// ignore
        }
        return contestPhasePersistence.findByContestIdStartEnd(contest.getContestPK(), now, now);
    }
}
