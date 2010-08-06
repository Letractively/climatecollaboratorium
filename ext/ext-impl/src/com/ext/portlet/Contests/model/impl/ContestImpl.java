package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContestImpl extends ContestModelImpl implements Contest {
    public ContestImpl() {
    }

    public List<ContestPhase> getPhases() {

        return ContestPhaseLocalServiceUtil.getPhasesForContest(this);
    }

    public List<PlanItem> getPlans() {
        List<PlanItem> result = new ArrayList<PlanItem>();
        for (ContestPhase phase:getPhases()) {
            result.addAll(phase.getPlans());
        }
        return result;
    }
}
