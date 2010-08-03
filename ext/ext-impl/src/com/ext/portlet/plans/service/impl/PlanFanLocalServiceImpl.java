package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.service.base.PlanFanLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class PlanFanLocalServiceImpl extends PlanFanLocalServiceBaseImpl {
    
    public List<PlanFan> getPlanFansForPlan(Long planId) throws SystemException {
        return planFanPersistence.findByPlanId(planId);
    }
    
    public List<PlanFan> getPlanFansForUser(Long userId) throws SystemException {
        return planFanPersistence.findByUserId(userId);
    }
    
    public PlanFan addFan(Long planId, Long userId) throws SystemException {
        long planFanId = CounterUtil.increment(PlanFan.class.getName());
        
        PlanFan planFan = createPlanFan(planFanId);
        
        planFan.setPlanId(planId);
        planFan.setUserId(userId);
        planFan.setCreated(new Date());
        
        planFan.store();
        
        return planFan;
    }
}
