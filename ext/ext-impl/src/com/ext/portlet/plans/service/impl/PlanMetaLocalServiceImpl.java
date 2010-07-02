package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanMetaLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class PlanMetaLocalServiceImpl extends PlanMetaLocalServiceBaseImpl {
    
    public PlanMeta createPlanMeta(PlanItem plan, Long planTypeId) throws SystemException {
        Long planMetaId = CounterUtil.increment(PlanMeta.class.getName());
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createPlanMeta(planMetaId);
        planMeta.setPlanId(plan.getPlanId());
        planMeta.setPlanTypeId(planTypeId);
        planMeta.setPlanVersion(plan.getVersion());
        planMeta.setCreated(new Date());
        planMeta.setVotes(0);
        planMeta.setUpdateAuthorId(plan.getUpdateAuthorId());
        planMeta.setAuthorId(plan.getUpdateAuthorId());
        planMeta.setVersion(0L);
        
        return PlanMetaLocalServiceUtil.addPlanMeta(planMeta);
    }
    
    public PlanMeta getCurrentForPlan(PlanItem plan) throws SystemException {
        return this.planMetaPersistence.fetchByCurrentByPlanId(plan.getPlanId());
    }
    
    public List<PlanMeta> getAllForPlan(PlanItem plan) throws SystemException {
        return this.planMetaPersistence.findByAllByPlanId(plan.getPlanId());
    }
    

    public PlanMeta createNewVersionForPlan(PlanItem plan) throws SystemException {
        return createNewVersionForPlan(plan, true);
    }
    
    public PlanMeta createNewVersionForPlan(PlanItem plan, boolean store) throws SystemException {
        PlanMeta currentMeta = this.planMetaPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        PlanMeta newMeta = (PlanMeta) currentMeta.clone();
        
        newMeta.setVersion(currentMeta.getVersion()+1);
        newMeta.setId(CounterUtil.increment(PlanMeta.class.getName()));
        newMeta.setPlanVersion(plan.getVersion());
        newMeta.setUpdateAuthorId(plan.getUpdateAuthorId());

        if (store) {
            newMeta = PlanMetaLocalServiceUtil.addPlanMeta(newMeta);
        }
        
        return newMeta;
    }
}
