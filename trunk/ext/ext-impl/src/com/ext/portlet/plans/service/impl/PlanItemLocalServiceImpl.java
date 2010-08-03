package com.ext.portlet.plans.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.plans.EntityState;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanItemLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.service.MBCategoryServiceUtil;

public class PlanItemLocalServiceImpl extends PlanItemLocalServiceBaseImpl {
    /**
     * Suffix that should be added to PlanItem class name to get name for plan id counter
     */
    private final String PLAN_ID_NAME_SUFFIX = ".Plan";
    
    /**
     * Default community permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_COMMUNITY_PERMISSIONS = {ActionKeys.VIEW, ActionKeys.SUBSCRIBE,
            ActionKeys.REPLY_TO_MESSAGE, ActionKeys.ADD_MESSAGE};

    /**
     * Default guest permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_GUEST_PERMISSIONS = {ActionKeys.VIEW, ActionKeys.SUBSCRIBE};

    /**
     * Default description of group working on a plan.
     */
    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on plan %s";

    /**
     * Default forum category name.
     */
    public static final String DEFAULT_FORUM_CATEGORY_NAME = "General discussion";

    /**
     * Default forum category description.
     */
    public static final String DEFAULT_FORUM_CATEGORY_DESCRIPTION = "General discussion about plan %s";

    
    /**
     * Creates and initializes new instance of a PlanItem. 
     * 
     * All necessary id's are generated, version is set to 0.
     * @param authorId Id of user that is creating new plan
     */
    public PlanItem createPlan(String name, Long planTypeId, Long authorId) throws SystemException, PortalException {
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
        
        // create community, Message Boards category
        initPlan(planItem);
        
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
        PlanItem plan = createPlan(name, type, authorId);
        PlanDescription description = PlanDescriptionLocalServiceUtil.getCurrentForPlan(plan);
        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.getCurrentForPlan(plan);
        PlanPositions planPositions = PlanPositionsLocalServiceUtil.getCurrentForPlan(plan);
        
        // copy description
        description.setDescription(basePlan.getDescription());
        description.store();
        
        // copy scenario id
        planModelRun.setScenarioId(basePlan.getScenarioId());
        planModelRun.store();
        
        // copy positions
        planPositions.setPositionsIds(basePlan.getPositionsIds());
        planPositions.store();
        
        if (basePlan.getScenarioId() != null) {
            // update all attributes
            plan.updateAllAttributes();
        }
        else {
            // update only attributes related to new values
            plan.updateAttribute(Attribute.DESCRIPTION.name());
            plan.updateAttribute(Attribute.POSITIONS.name());
        }
        
        return plan;
    }
    
    public PlanItem createPlan(Plan basePlan) throws SystemException, PortalException {
        long planItemId = CounterUtil.increment(PlanItem.class.getName());
        long planId = basePlan.getPlanId();
        long authorId = basePlan.getUserId();
        long planTypeId = basePlan.getPlanTypeId();
        String name = basePlan.getName();
        
        String description = "";
        if (basePlan.getShortcontent().trim().length() > 0) {
            description += "<div>" + basePlan.getShortcontent() + "</div>";   
        }
        if (basePlan.getContent().trim().length() > 0) {
            description += "<div>" + basePlan.getContent() + "</div>";
        }
        
        
        PlanItem planItem = PlanItemLocalServiceUtil.createPlanItem(planItemId);
        planItem.setPlanId(planId);
        planItem.setVersion(0L);
        planItem.setUpdated(new Date());
        planItem.setUpdateAuthorId(authorId);
        planItem.setState(EntityState.ACTIVE.name());
        planItem.setUpdateType(UpdateType.CREATED.name());
        
        planItem = PlanItemLocalServiceUtil.addPlanItem(planItem);
        
        // create related entities, plan description, meta, model run
        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createPlanDescription(planItem, name);
        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.createPlanModelRun(planItem);
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createPlanMeta(planItem, planTypeId);
        PlanPositions planPositions = PlanPositionsLocalServiceUtil.createPlanPositions(planItem);
        
        
        // description
        planDescription.setDescription(basePlan.getShortcontent() + basePlan.getContent());
        planDescription.setName(basePlan.getName());
        
        boolean hasScenario = false;

        planDescription.setDescription(description);

        // meta
        planMeta.setModelId(planItem.getPlanType().getModelId());
        planMeta.setMbCategoryId(basePlan.getMBCategoryId());
        planMeta.setPlanGroupId(basePlan.getChildGroupId());
        if (planItem.getPlanType().getPublished()) {
            planMeta.setCreated(basePlan.getPublishDate());
        } else {
            planMeta.setCreated(basePlan.getCreateDate());
        }

        if (basePlan.getScenarioId().trim().length() > 0) {
            hasScenario = true;
            planModelRun.setScenarioId(Long.parseLong(basePlan.getScenarioId()));
        }
        planMeta.setVotes(PlanVoteLocalServiceUtil.coutPlanVotes(basePlan.getPlanId()));


        // positions
        List<Long> positions = new ArrayList<Long>();
        for (DebateItem position :PlanLocalServiceHelper.getPlanPositionsDebateRevision(basePlan.getPlanId())) {
            positions.add(position.getDebateItemId());
        }
        planPositions.setPositionsIds(positions);
        

        planItem.store();
        planDescription.store();
        planMeta.store();
        planModelRun.store();
        planPositions.store();

        // attributes
        if (hasScenario) {
            planItem.updateAllAttributes();
        } else {
            planItem.updateAttribute(Attribute.CREATOR.name());
            planItem.updateAttribute(Attribute.NAME.name());
            planItem.updateAttribute(Attribute.DESCRIPTION.name());
            planItem.updateAttribute(Attribute.CREATE_DATE.name());
            planItem.updateAttribute(Attribute.PUBLISH_DATE.name());
            planItem.updateAttribute(Attribute.VOTES.name());
            planItem.updateAttribute(Attribute.POSITIONS.name());
        }
        
        
        return planItem;
    }
    
    private void initPlan(PlanItem plan) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUser(plan.getUpdateAuthorId());
        PermissionCheckerUtil.setThreadValues(user);
        
        ServiceContext categoryServiceContext = new ServiceContext();
        categoryServiceContext.setUserId(plan.getUpdateAuthorId());

        ServiceContext groupServiceContext = new ServiceContext(); 
        groupServiceContext.setUserId(plan.getUpdateAuthorId());
        
        // in order to prevent group name conflicts, 
        // add timestamp and random long value to plan name when setting group name
        Random rand = new Random();
        String groupName = plan.getName() + "_" + System.currentTimeMillis() + "_" + rand.nextLong();
        Group group = GroupServiceUtil.addGroup(plan.getName(), String.format(DEFAULT_GROUP_DESCRIPTION, groupName),
                GroupConstants.TYPE_COMMUNITY_RESTRICTED, null, true, groupServiceContext);

        Long parentCategoryId = 0L;
        DiscussionCategoryGroup categoryGroup = 
            DiscussionCategoryGroupLocalServiceUtil.createDiscussionCategoryGroup("Category group for plan: " + plan.getPlanId());
        DiscussionCategory category = categoryGroup.addCategory("General discussion", null, UserLocalServiceUtil.getUser(plan.getAuthorId()));
        

        // create new category in group's forum
        /*
        categoryServiceContext.setCommunityPermissions(DEFAULT_CATEGORY_COMMUNITY_PERMISSIONS);
        categoryServiceContext.setGuestPermissions(DEFAULT_CATEGORY_GUEST_PERMISSIONS);
        categoryServiceContext.setScopeGroupId(group.getGroupId());

        MBCategory category = MBCategoryServiceUtil.addCategory(parentCategoryId, DEFAULT_FORUM_CATEGORY_NAME,
                String.format(DEFAULT_FORUM_CATEGORY_DESCRIPTION, plan.getName()), null, null, null, 0, false, null,
                null, 0, null, false, null, 0, false, null, null, false, categoryServiceContext);
         */
        
        // populate plan with id of created group, category
        PlanMeta planMeta = plan.getPlanMeta();
        
        planMeta.setCategoryGroupId(categoryGroup.getId());
        planMeta.setPlanGroupId(group.getGroupId());
        planMeta.store();
    }
    
    public List<PlanItem> getPlans() throws SystemException {
        return this.planItemFinder.getPlans();
    }
    
    public PlanItem getPlan(Long planId) throws NoSuchPlanItemException, SystemException {
        return this.planItemPersistence.findByPlanId(planId);
    }
    
    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, PlanType planType, int start, int end, 
            final String sortColumn, String sortDirection) throws SystemException, PortalException {
        return getPlans(sessionMap, requestMap, planType, start, end, sortColumn, sortDirection, true);
    }
    
    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, PlanType planType, int start, int end, 
            final String sortColumn, String sortDirection, boolean applyFilters) 
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
        if (applyFilters) {
            return applyFilters(sessionMap, requestMap, planType, plans);
        }
        else { 
            return plans;
        }
    }
    
    /*
    public int getPlansCount(Map sessionMap, Map requestMap, PlanType planType) throws SystemException, PortalException  {
        PlansUserSettings planUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(sessionMap, requestMap, planType);
        return  planItemFinder.countPlans(planUserSettings, planType);
    }
    */
    
    public boolean isNameAvailable(String planName) throws SystemException {
        return PlanAttributeLocalServiceUtil.getPlanAttributesByNameValue(Attribute.NAME.name(), planName).size() == 0;
    }
    
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
