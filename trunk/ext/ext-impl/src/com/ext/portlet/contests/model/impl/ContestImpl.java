package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.contests.service.impl.ContestPhaseLocalServiceImpl;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.impl.PlanTypeLocalServiceImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ContestImpl extends ContestModelImpl implements Contest {
    public ContestImpl() {
    }

    public List<ContestPhase> getPhases() throws SystemException {
        return ContestPhaseLocalServiceUtil.getPhasesForContest(this);
    }

    public PlanType getPlanType() throws SystemException, PortalException {
        return PlanTypeLocalServiceUtil.getPlanType(getPlanTypeId());
    }

    public List<ContestPhase> getActivePhases() throws SystemException {
        List<ContestPhase> result = getPhases();
        for (Iterator<ContestPhase> i=result.iterator();i.hasNext();) {
           if (!i.next().getContestStatus().isCanEdit()) {
               i.remove();
           }
        }
        return result;
    }

}
