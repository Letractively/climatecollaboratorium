package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanModelRunLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class PlanModelRunLocalServiceImpl
    extends PlanModelRunLocalServiceBaseImpl {
    
    public PlanModelRun createPlanModelRun(PlanItem plan) throws SystemException {
        Long id = CounterUtil.increment(PlanModelRun.class.getName());
        
        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.createPlanModelRun(id);
        planModelRun.setPlanId(plan.getPlanId());
        planModelRun.setPlanVersion(plan.getVersion());
        planModelRun.setVersion(0L);
        planModelRun.setUpdateAuthorId(plan.getUpdateAuthorId());
        planModelRun.setCreated(new Date());
        
        return PlanModelRunLocalServiceUtil.addPlanModelRun(planModelRun);
    }
    
    public PlanModelRun getCurrentForPlan(PlanItem plan) throws SystemException {
        return this.planModelRunPersistence.fetchByCurrentByPlanId(plan.getPlanId(), false);
    }
    
    public List<PlanModelRun> getAllForPlan(PlanItem plan) throws SystemException {
        return this.planModelRunPersistence.findByAllByPlanId(plan.getPlanId());
    }
    

    public PlanModelRun createNewVersionForPlan(PlanItem plan) throws SystemException {
        return createNewVersionForPlan(plan, true);
    }
    
    public PlanModelRun createNewVersionForPlan(PlanItem plan, boolean store) throws SystemException {
        PlanModelRun currentModelRun = this.planModelRunPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        PlanModelRun newModelRun = (PlanModelRun) currentModelRun.clone();
        
        newModelRun.setVersion(currentModelRun.getVersion()+1);
        newModelRun.setId(CounterUtil.increment(PlanModelRun.class.getName()));
        newModelRun.setPlanVersion(plan.getVersion());
        newModelRun.setUpdateAuthorId(plan.getUpdateAuthorId());

        if (store) {
            newModelRun = PlanModelRunLocalServiceUtil.addPlanModelRun(newModelRun);
        }
        
        return newModelRun;
    }
}
