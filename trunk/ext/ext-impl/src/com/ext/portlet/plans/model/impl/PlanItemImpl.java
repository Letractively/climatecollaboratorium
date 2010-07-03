/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class PlanItemImpl extends PlanItemModelImpl implements PlanItem {
    
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
        updateAttribute(Attribute.DESCRIPTION);
    }
    
    public void setName(String name, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.NAME_UPDATED, updateAuthorId);
        // update plan name attribute
        
        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(this);
        planDescription.setName(name);
        planDescription.store();
        updateAttribute(Attribute.NAME);
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
    
    public void setScenarioId(Long scenarioId, Long authorId) throws SystemException, PortalException {
        newVersion(UpdateType.SCENARIO_UPDATED, authorId);
        
        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.createNewVersionForPlan(this);
        planModelRun.setScenarioId(scenarioId);
        planModelRun.store();
        
        // update plan attributes to reflect values from new scenario
        PlanType planType = getPlanType();
        
        for (PlanConstants.Attribute attribute:PlanConstants.Attribute.getPlanTypeAttributes(planType)) {
            updateAttribute(attribute);
        }
    }
    
    /*
     * Plan meta informations.
     */
    
    public PlanMeta getPlanMeta() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this);
    }
    
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
       return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getAuthorId();
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
   
   public Date getCreateDate() throws SystemException {
       return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getCreated();
   }
   
   public Date getPublishDate() throws SystemException {
       return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getCreated();
   }
   
   public String getCreator() throws PortalException, SystemException {
       return getAuthor().getScreenName();
       
   }
   
   
   public Integer getVotes() throws SystemException {
       return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getVotes();
   }
   
   
    
    
   /*
    * POSITIONS
    */
    
   public List<Long> getPositionsIds() throws SystemException, NoSuchPlanPositionsException {
       PlanPositions x = PlanPositionsLocalServiceUtil.getCurrentForPlan(this);
       return x.getPositionsIds();
   }
   
   public void setPositions(List<Long> positionsIds, Long updateAuthorId) throws PortalException, SystemException {        
       newVersion(UpdateType.PLAN_POSITIONS_UPDATED, updateAuthorId);
       
       PlanPositions planPositions = PlanPositionsLocalServiceUtil.createNewVersionForPlan(this);
       planPositions.store();
       planPositions.setPositionsIds(positionsIds);
   } 
    
    
    /*
     * VOTES
     */
   public boolean hasUserVoted(Long userId) throws PortalException, SystemException {
       try {
           PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(userId);
           return vote.getPlanId().equals(getPlanId());
       } catch (NoSuchPlanVoteException e) {
           // ignore
       }
       return false;
   }
   
   public void vote(Long userId) throws PortalException, SystemException  {
       PlanVoteLocalServiceUtil.voteForPlan(getPlanId(), userId);
       updateAttribute(Attribute.VOTES);
   }
   
   public void unvote(Long userId) throws PortalException, SystemException  {
       PlanVoteLocalServiceUtil.unvote(userId);
       updateAttribute(Attribute.VOTES);
   }
    
    
    
    
    public List<PlanItem> getAllVersions() throws SystemException {
        return PlanItemLocalServiceUtil.getAllVersions(this);
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
    
    /**
     * Updates values of all available attributes.
     * @throws SystemException
     */
    
    public void updateAllAttributes() throws SystemException {
        for (Attribute attribute: Attribute.values()) {
            updateAttribute(attribute);
        }
    }

    /**
     * Updates value of a given attribute, should be used only for property attributes.
     * @param attribute attribute which value should be updated
     * @throws SystemException in case of any error
     */
    public void updateAttribute(String attributeName) throws SystemException {
        updateAttribute(Attribute.valueOf(attributeName));
    }
    
    /**
     * Updates value of a given attribute, should be used only for property attributes.
     * @param attribute attribute which value should be updated
     * @throws SystemException in case of any error
     */
    private void updateAttribute(Attribute attribute) throws SystemException {
        String value = attribute.calculateValue(this).toString();
        PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(getPlanId(), attribute.name());
        if (att!=null) {
            att.setAttributeValue(value);
            PlanAttributeLocalServiceUtil.updatePlanAttribute(att);
        } else {
            PlanAttributeLocalServiceUtil.addPlanAttribute(getPlanId(), attribute.name(),value);
        }
    }
    
    
    
    public List<PlanAttribute> getPlanAttributes() throws SystemException {
        return PlanItemLocalServiceUtil.getPlanAttributes(this);
    }
    
    public PlanAttribute getPlanAttribute(String name) throws SystemException {
        return PlanItemLocalServiceUtil.getPlanAttribute(this, name);
    }
    

    
    
}
