package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestStatus;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;

import java.util.Collections;
import java.util.List;


public class ContestPhaseImpl extends ContestPhaseModelImpl
    implements ContestPhase {
    public ContestPhaseImpl() {
    }

    public List<PlanItem> getPlans() {
       return PlanItemLocalServiceUtil.getPlansInContestPhase(this.getContestPhasePK());
    }

    public ContestStatus getContestStatus() {
        String status = getContestPhaseStatus();
        return status == null?null:ContestStatus.valueOf(status);
    }
}
