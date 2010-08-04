package com.ext.portlet.plans.service.impl;

import java.util.Date;

import com.ext.portlet.plans.NoSuchPlanTeamHistoryException;
import com.ext.portlet.plans.model.PlanTeamHistory;
import com.ext.portlet.plans.service.base.PlanTeamHistoryLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class PlanTeamHistoryLocalServiceImpl
    extends PlanTeamHistoryLocalServiceBaseImpl {
    
 
    public PlanTeamHistory newHistoryItem(Long planId, Long userId, String action, Long updateAuthorId) throws SystemException {
        return newHistoryItem(planId, userId, action, null, updateAuthorId);
    }
        
    public PlanTeamHistory newHistoryItem(Long planId, Long userId, String action, String payload, Long updateAuthorId) throws SystemException {
        long id = CounterUtil.increment(PlanTeamHistory.class.getName());
        
        PlanTeamHistory planTeamHistory = createPlanTeamHistory(id);
        
        planTeamHistory.setPlanId(planId);
        planTeamHistory.setUserId(userId);
        planTeamHistory.setAction(action);
        planTeamHistory.setPayload(payload);
        planTeamHistory.setUpdateAuthorId(updateAuthorId);
        planTeamHistory.setCreated(new Date());
        
        planTeamHistory.store();
        
        return planTeamHistory;
    }
    
    public PlanTeamHistory getLastUserActionInPlan(Long planId, Long userId) throws NoSuchPlanTeamHistoryException, SystemException {
        return planTeamHistoryPersistence.findByLastUserActionInPlan(planId, userId);
    }
}
