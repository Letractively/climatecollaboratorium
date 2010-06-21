/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class PlanItemImpl extends PlanItemModelImpl implements PlanItem {
    
    public static PlanItem createPlan(Long authorId) throws SystemException {
        return PlanItemLocalServiceUtil.createPlan(1L);
    }
    
    public static List<PlanItem> getPlans() throws SystemException {
        return PlanItemLocalServiceUtil.getPlans();
    }
    
    public PlanItemImpl() {
    }
    /* Description related stuff */
    public String getDescription() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getCurrentForPlan(this).getDescription();
    }
    
    public String getName() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getCurrentForPlan(this).getName();
    }
    
    public void setDescription(String description, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.DESCRIPTION_UPDATED, updateAuthorId);
        
        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(this);
        planDescription.setDescription(description);
        planDescription.store();
    }
    
    public void setName(String name, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.NAME_UPDATED, updateAuthorId);
        
        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(this);
        planDescription.setName(name);
        planDescription.store();
    }
    
    /** 
     * List of all versions of PlanDescription objects related to given plan
     * @see com.ext.portlet.plans.model.PlanItem#getPlanDescriptions()
     */
    public List<PlanDescription> getPlanDescriptions() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getAllForPlan(this);
    }

    /*
     *
     * Scenarios
     * 
     */
    public Long getScenarioId() throws SystemException {
        return PlanModelRunLocalServiceUtil.getCurrentForPlan(this).getScenarioId();
    }
    
    public void setScenarioId(Long scenarioId, Long authorId) throws SystemException {
        newVersion(UpdateType.SCENARIO_UPDATED, authorId);
        
        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.createNewVersionForPlan(this);
        planModelRun.setScenarioId(scenarioId);
        planModelRun.store();
    }
    
    /*
     * Plan meta informations.
     */
    
    public Long getPlanTypeId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getPlanTypeId();
    }
    
    public PlanType getPlanType() throws SystemException, PortalException {
        return PlanTypeLocalServiceUtil.getPlanType(getPlanTypeId());
    }
    
    
    public void setPlanTypeId(Long planTypeId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_TYPE_UPDATED, updateAuthorId);
        
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setPlanTypeId(planTypeId);
        planMeta.store();
    }
    
    public Long getMBCategoryId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getMbCategoryId();
    }
    
    public void setMBCategoryId(Long mbCategoryId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.MB_GROUP_UPDATED, updateAuthorId);
        
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setMbCategoryId(mbCategoryId);
        planMeta.store();
    }
    
   public Long getPlanGroupId() throws SystemException {
       return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getPlanGroupId();
   }
   
   public void setPlanGroupId(Long groupId, Long updateAuthorId) throws SystemException {        
       newVersion(UpdateType.PLAN_GROUP_UPDATED, updateAuthorId);
   
       PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
       planMeta.setPlanGroupId(groupId);
       planMeta.store();
   }
   
   public Long getAuthorId() throws SystemException {
       return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getPlanGroupId();
   }
   
   public User getAuthor() throws PortalException, SystemException {
       return UserLocalServiceUtil.getUser(getAuthorId());
   }
   
   public void setAuthorId(Long authorId, Long updateAuthorId) throws SystemException {        
       newVersion(UpdateType.PLAN_GROUP_UPDATED, updateAuthorId);
   
       PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
       planMeta.setAuthorId(authorId);
       planMeta.store();
   }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private PlanItem newVersion(UpdateType updateType, Long updateAuthorId) throws SystemException {
        PlanItem newVersion = (PlanItem) this.clone();

        newVersion.setId(CounterUtil.increment(PlanItem.class.getName()));
        newVersion.setVersion(this.getVersion()+1);
        newVersion.setUpdated(new Date());
        newVersion.setUpdateType(updateType.name());
        newVersion.setUpdateAuthorId(updateAuthorId);
        
        newVersion.store();
        
        // promote current instance to new version
        this.setId(newVersion.getId());
        this.setPlanId(newVersion.getPlanId());
        this.setState(newVersion.getState());
        this.setUpdated(newVersion.getUpdated());
        this.setUpdateAuthorId(newVersion.getUpdateAuthorId());
        this.setUpdateType(newVersion.getUpdateType());
        this.setVersion(newVersion.getVersion());
        
        return newVersion;
    }
 
    
    public void store() throws SystemException {
        if (this.isNew()) {
            PlanItemLocalServiceUtil.addPlanItem(this);
        }
        else {
            PlanItemLocalServiceUtil.updatePlanItem(this);
        }
    }
}
