package com.ext.portlet.plans;

import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;

public class PlanDescriptionTest {
    
    PlanItem planItem;
    
    public void setUp() throws SystemException {
            Long planItemId = CounterLocalServiceUtil.increment(PlanItem.class.getName());
            Long planId = CounterLocalServiceUtil.increment(PlanItem.class.getName() + "PLAN");
            
            planItem = PlanItemLocalServiceUtil.createPlanItem(planItemId);
            planItem.setPlanId(planId);
            
            PlanItemLocalServiceUtil.createPlanItem(planItemId);
            PlanItemLocalServiceUtil.addPlanItem(planItem);
    }
    
    public void tearDown() throws SystemException {
        PlanItemLocalServiceUtil.deletePlanItem(planItem);
    }

    public void testCreation() throws SystemException {
        // check if there is a plan 
        Long planDescriptionId = CounterLocalServiceUtil.increment(PlanDescription.class.getName());
        //PlanDescription planDescription = 
    }
}
