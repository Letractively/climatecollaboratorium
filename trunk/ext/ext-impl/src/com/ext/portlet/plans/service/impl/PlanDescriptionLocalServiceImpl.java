package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.NoSuchPlanDescriptionException;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanDescriptionLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class PlanDescriptionLocalServiceImpl
    extends PlanDescriptionLocalServiceBaseImpl {
    
    public PlanDescription createPlanDescription(PlanItem plan) throws SystemException {
        return createPlanDescription(plan, true);
    }
        
    public PlanDescription createPlanDescription(PlanItem plan, boolean store) throws SystemException {
        long planDescriptionId = CounterUtil.increment(PlanDescription.class.getName());
        PlanDescription description = PlanDescriptionLocalServiceUtil.createPlanDescription(planDescriptionId);
        description.setPlanId(plan.getPlanId());
        description.setPlanVersion(plan.getVersion());
        description.setCreated(new Date());
        description.setVersion(0L);
        if (store) {
            description = PlanDescriptionLocalServiceUtil.addPlanDescription(description);
        }
        return description;
    }
    
    public PlanDescription getCurrentForPlan(PlanItem plan) throws SystemException {
        return this.planDescriptionPersistence.fetchByCurrentByPlanId(plan.getPlanId());
    }
    
    public List<PlanDescription> getAllForPlan(PlanItem plan) throws SystemException {
        return this.planDescriptionPersistence.findByAllByPlanId(plan.getPlanId());
    }
    

    public PlanDescription createNewVersionForPlan(PlanItem plan) throws SystemException {
        return createNewVersionForPlan(plan, true);
    }
    
    public PlanDescription createNewVersionForPlan(PlanItem plan, boolean store) throws SystemException {
        PlanDescription currentDescription = this.planDescriptionPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        PlanDescription newDescription = (PlanDescription) currentDescription.clone();
        
        newDescription.setVersion(currentDescription.getVersion()+1);
        newDescription.setId(CounterUtil.increment(PlanDescription.class.getName()));
        newDescription.setPlanVersion(plan.getVersion());

        if (store) {
            newDescription = PlanDescriptionLocalServiceUtil.addPlanDescription(newDescription);
        }
        
        return newDescription;
    }
}
