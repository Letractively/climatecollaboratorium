package com.ext.portlet.plans.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ext.portlet.plans.EntityState;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanItemLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.PortalException;
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
        
        /* update/create all attributes */
        //planItem.updateAllAttributes();
        planItem.updateAttribute(Attribute.CREATOR.name());
        planItem.updateAttribute(Attribute.NAME.name());
        planItem.updateAttribute(Attribute.DESCRIPTION.name());
        planItem.updateAttribute(Attribute.CREATE_DATE.name());
        planItem.updateAttribute(Attribute.PUBLISH_DATE.name());
        planItem.updateAttribute(Attribute.VOTES.name());
        
        
        // populate fields with default values
        
        return planItem;
    }
    
    public PlanItem createPlan(String name, PlanItem basePlan, Long authorId) throws SystemException, PortalException {
        long type = basePlan.getPlanTypeId();
        if (basePlan.getPlanType().getPublished()) {
            type = basePlan.getPlanType().getPublishedCounterpartId();
        }
        PlanItem plan = createPlan(basePlan.getName(), type, authorId);
        PlanDescription description = PlanDescriptionLocalServiceUtil.getCurrentForPlan(plan);
        description.setDescription(basePlan.getDescription());
        description.setName(name);
        plan.updateAttribute(Attribute.DESCRIPTION.name());
        plan.updateAttribute(Attribute.NAME.name());
        
        
        return plan;
    }
    
    public List<PlanItem> getPlans() throws SystemException {
        return this.planItemFinder.getPlans();
    }
    
    public PlanItem getPlan(Long planId) throws NoSuchPlanItemException, SystemException {
        return this.planItemPersistence.findByPlanId(planId);
    }
    
    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, PlanType planType, int start, int end, 
            final String sortColumn, String sortDirection) 
    throws SystemException, PortalException  {
        /*
        PlansUserSettings planUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(sessionMap, requestMap, planType);
        
        return planItemFinder.getPlans(planUserSettings, planType.getPlanTypeId(), start, end, sortColumn, sortDirection);
        
        */
        
        List<PlanItem> plans = new ArrayList<PlanItem>();
        for (PlanItem planItem: planItemFinder.getPlans()) {
            if (planItem.getPlanTypeId().equals(planType.getPlanTypeId())) {
                plans.add(planItem);
            }
        }
        final int directionModifier = sortDirection.equals("DESC") ? -1 : 1;
        Collections.sort(plans, new Comparator<PlanItem>() {

            @Override
            public int compare(PlanItem arg0, PlanItem arg1) {
                try {
                    PlanAttribute plan1Attr = arg0.getPlanAttribute(sortColumn);
                    PlanAttribute plan2Attr = arg1.getPlanAttribute(sortColumn);
                    Comparable val1 = (Comparable) (plan1Attr != null ? plan1Attr.getTypedValue() : null);
                    Comparable val2 = (Comparable) (plan2Attr != null ? plan2Attr.getTypedValue() : null);
                    if (val1 != null) {
                        if (val2 != null) {
                            return val1.compareTo(val2) * directionModifier;
                        }
                        else {
                            return -1 * directionModifier;
                        }
                    }
                    else {
                        return 1 * directionModifier;
                    } 
                } catch (SystemException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        return applyFilters(sessionMap, requestMap, planType, plans);
    }
    /*
    public int getPlansCount(Map sessionMap, Map requestMap, PlanType planType) throws SystemException, PortalException  {
        PlansUserSettings planUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(sessionMap, requestMap, planType);
        return  planItemFinder.countPlans(planUserSettings, planType);
    }
    */
    
    public List<PlanItem> applyFilters(Map sessionMap, Map requestMap, PlanType planType, List<PlanItem> plans) throws PortalException, SystemException {
        PlansUserSettings planUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(sessionMap, requestMap, planType);
        if (! planUserSettings.getFilterEnabled()) {
            return plans;
        }
        
        List<PlanItem> filteredPlans = new ArrayList<PlanItem>();
        
        for (PlanItem plan: plans) {
            boolean inFilteredSet = true;
            for (Attribute attribute: Attribute.values()) {
                if (!attribute.isInFilteredSet(planUserSettings, plan)) {
                    inFilteredSet = false;
                    break;
                }
            }
            if (inFilteredSet) {
                filteredPlans.add(plan);
            }
        }
        
        return filteredPlans;
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
    
    public PlanAttribute getPlanAttribute(PlanItem plan, String name) throws SystemException {
        return PlanAttributeLocalServiceUtil.findPlanAttribute(plan.getPlanId(), name);
    }
    
    private class PlanFilter {
        private Attribute attribute;
        private String valueStr;
        private Double valueDbl;
        private Double valueMin;
        private Double valueMax;
        private Integer valueInt;
        boolean doubleVal = false;
        boolean intVal = false;
        boolean stringVal = true;
        boolean singleValue = true;
        
        boolean enabled;
        
        public PlanFilter(PlanAttributeFilter filter) {
            this.attribute = Attribute.valueOf(filter.getAttributeName());
            if (attribute.isFiltered()) {
                enabled = false;
            }
            else {
                enabled = true;
                if (attribute.isFilterSingleValue()) {
                    if (attribute.getAttributeClass() == Double.class) {
                        valueDbl = Double.parseDouble((filter.getStringVal() != null ? filter.getStringVal() : String.valueOf(Double.MIN_VALUE)));
                        doubleVal = true;
                        stringVal = false;
                    }
                    else if (attribute.getAttributeClass() == Integer.class) {
                        valueInt = Integer.parseInt((filter.getStringVal() != null ? filter.getStringVal() : String.valueOf(Integer.MIN_VALUE)));
                        intVal = true;
                        stringVal = false;
                    }
                }
                else {
                    valueMin = filter.getMin();
                    valueMax = filter.getMax();
                    doubleVal = true;
                    stringVal = false;
                    singleValue = false;
                }
            }
            
            /*
            public boolean isFilteredOut(PlanItem plan) {
                PlanAttribute attriplan.getPlanAttribute(attribute.name());
            }
            */
            
        }
    }
}
