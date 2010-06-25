package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ext.portlet.plans.EntityState;
import com.ext.portlet.plans.UpdateType;
//import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanItemLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;


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
    public PlanItem createPlan(String name, Long planTypeId, Long authorId) throws SystemException {
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
        PlanDescriptionLocalServiceUtil.createPlanDescription(planItem, name);
        PlanModelRunLocalServiceUtil.createPlanModelRun(planItem);
        PlanMetaLocalServiceUtil.createPlanMeta(planItem, planTypeId);
        PlanPositionsLocalServiceUtil.createPlanPositions(planItem);
        
        // update default attributes
        planItem.updateAttribute(Attribute.CREATOR.name());
        planItem.updateAttribute(Attribute.NAME.name());
        planItem.updateAttribute(Attribute.CREATE_DATE.name());
        
        
        // populate fields with default values
        
        return planItem;
    }
    
    public List<PlanItem> getPlans() throws SystemException {
        return this.planItemFinder.getPlans();
    }
    
    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, PlanType planType, int start, int end, 
            String sortColumn, String sortDirection) 
    throws SystemException, PortalException  {
        PlansUserSettings planUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(sessionMap, requestMap, planType);
        
        List<PlanItem> plans = null;
        if (planUserSettings == null || !planUserSettings.getFilterEnabled()) {
            /*
            plans = PlanItemLocalServiceUtil.get
            */
            plans = planItemFinder.getPlans(planType.getPlanTypeId(), start, end, sortColumn, sortDirection);
            
        } else {
            /*
            plans = PlanLocalServiceUtil.getFilteredPlans(planUserSettings, start, end, sortColumn, sortDirection);
            */
        }
        return plans;
    }
    
    public void removePlanWithEntireHistory(Long planId) {
        this.planItemFinder.removePlanWithHistory(planId);
    }
    
    public List<PlanItem> getAllVersions(PlanItem plan) throws SystemException {
        return this.planItemPersistence.findByAllByPlanId(plan.getPlanId());
    }
    
    public List<PlanAttribute> getPlanAttributes(PlanItem plan) throws SystemException {
        return PlanAttributeLocalServiceUtil.getPlanAttributes(plan.getPlanId());
    }
}
