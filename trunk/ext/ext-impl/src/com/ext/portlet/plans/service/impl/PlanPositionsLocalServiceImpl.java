package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanPositionsLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class PlanPositionsLocalServiceImpl
    extends PlanPositionsLocalServiceBaseImpl {
    
    public PlanPositions getCurrentForPlan(PlanItem plan) throws SystemException, NoSuchPlanPositionsException {
        return this.planPositionsPersistence.fetchByCurrentByPlanId(plan.getPlanId(), false);
    }
    
    public PlanPositions createPlanPositions(PlanItem plan) throws SystemException {
        Long planPositionsId = CounterLocalServiceUtil.increment(PlanPositions.class.getName());
        PlanPositions planPositions = PlanPositionsLocalServiceUtil.createPlanPositions(planPositionsId);
        planPositions.setPlanId(plan.getPlanId());
        planPositions.setPlanVersion(plan.getVersion());
        planPositions.setCreated(new Date());
        planPositions.setUpdateAuthorId(plan.getUpdateAuthorId());
        planPositions.setVersion(0L);
        
        planPositions.store();
        return planPositions;
    }
    
    public List<PlanPositions> getAllForPlan(PlanItem plan) throws SystemException {
        return this.planPositionsPersistence.findByAllByPlanId(plan.getPlanId());
    }
    

    public PlanPositions createNewVersionForPlan(PlanItem plan) throws SystemException {
        return createNewVersionForPlan(plan, true);
    }
    
    public PlanPositions createNewVersionForPlan(PlanItem plan, boolean store) throws SystemException {
        PlanPositions currentPositions = this.planPositionsPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        PlanPositions newPositions = (PlanPositions) currentPositions.clone();
        
        newPositions.setVersion(currentPositions.getVersion()+1);
        newPositions.setId(CounterLocalServiceUtil.increment(PlanPositions.class.getName()));
        newPositions.setPlanVersion(plan.getVersion());
        newPositions.setUpdateAuthorId(plan.getUpdateAuthorId());
        newPositions.setNew(true);
        newPositions.setCreated(new Date());

        if (store) {
            newPositions.store();
        }
        
        return newPositions;
    }
}
