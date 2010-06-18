package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.EntityState;
import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanItemLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class PlanItemLocalServiceImpl extends PlanItemLocalServiceBaseImpl {
    /*
     * Suffix that should be added to PlanItem class name to get name for plan id counter
     */
    private final String PLAN_ID_NAME_SUFFIX = ".Plan";
    
    /**
     * Creates and initializes new instance of a PlanItem. 
     * 
     * All necessary id's are generated, version is set to 0.
     * @param authorId Id of user that is creating new plan
     */
    public PlanItem createPlan(Long authorId) throws SystemException {
        long planItemId = CounterUtil.increment(PlanItem.class.getName());
        long planId = CounterUtil.increment(PlanItem.class.getName() + PLAN_ID_NAME_SUFFIX);
        
        PlanItem planItem = PlanItemLocalServiceUtil.createPlanItem(planItemId);
        planItem.setPlanId(planId);
        planItem.setVersion(0L);
        planItem.setUpdated(new Date());
        planItem.setUpdateAuthorId(authorId);
        planItem.setState(EntityState.ACTIVE.name());
        planItem.setUpdateType(UpdateType.CREATED.name());
        
        planItem = PlanItemLocalServiceUtil.addPlanItem(planItem);
        // create related entities, plan description, meta, model run
        PlanDescriptionLocalServiceUtil.createPlanDescription(planItem);
        PlanModelRunLocalServiceUtil.createPlanModelRun(planItem);
        PlanMetaLocalServiceUtil.createPlanMeta(planItem);
        
        return planItem;
    }
    
    public List<PlanItem> getPlans() throws SystemException {
        return this.planItemFinder.getPlans();
    }
    
    public void removePlanWithEntireHistory(Long planId) {
        this.planItemFinder.removePlanWithHistory(planId);
    }
}
