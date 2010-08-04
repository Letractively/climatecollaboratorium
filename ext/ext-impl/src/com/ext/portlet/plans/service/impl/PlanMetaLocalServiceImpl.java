package com.ext.portlet.plans.service.impl;

import com.ext.portlet.PlanStatus;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanMetaLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.Simulation;

import java.util.Date;
import java.util.List;


public class PlanMetaLocalServiceImpl extends PlanMetaLocalServiceBaseImpl {
    
    public PlanMeta createPlanMeta(PlanItem plan, Long planTypeId) throws SystemException, PortalException {
        Long planMetaId = CounterUtil.increment(PlanMeta.class.getName());
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createPlanMeta(planMetaId);
        planMeta.setPlanId(plan.getPlanId());
        planMeta.setPlanTypeId(planTypeId);
         //set a default model


            PlanType type = PlanTypeLocalServiceUtil.getPlanType(planTypeId);
            List<Simulation> models = type.getAvailableModels();
            if (models.size() > 0) planMeta.setModelId(models.get(0).getId());
        planMeta.setPlanVersion(plan.getVersion());
        planMeta.setCreated(new Date());
        planMeta.setVotes(0);
        planMeta.setOpen(true);
        planMeta.setUpdateAuthorId(plan.getUpdateAuthorId());
        planMeta.setAuthorId(plan.getUpdateAuthorId());
        planMeta.setVersion(0L);
        planMeta.setCategoryGroupId(0L);
        planMeta.setStatus(PlanStatus.UNDER_DEVELOPMENT.name());
        
        return PlanMetaLocalServiceUtil.addPlanMeta(planMeta);
    }
    
    public PlanMeta getCurrentForPlan(PlanItem plan) throws SystemException {
        return this.planMetaPersistence.fetchByCurrentByPlanId(plan.getPlanId(), false);
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
