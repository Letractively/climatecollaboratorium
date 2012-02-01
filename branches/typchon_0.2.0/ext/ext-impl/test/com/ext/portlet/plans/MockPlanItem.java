/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.ext.portlet.plans.model.*;
import com.ext.portlet.plans.model.impl.PlanItemImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 7, 2010
 * Time: 10:12:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockPlanItem extends PlanItemImpl {

     PlanItem base;
    PlanType type;

    public MockPlanItem(PlanItem base, PlanType type) {
       this.base = base;
        this.type = type;
    }

    @Override
       public Long getPlanTypeId() throws SystemException {
           return type.getPlanTypeId();
       }

       @Override
       public PlanType getPlanType() throws PortalException, SystemException {
           return type;
       }

       @Override
       public void setPlanTypeId(Long planTypeId, Long updateAuthorId) throws SystemException {
          //do nothing
       }


     @Override
    public void setModelId(Long simulationId, Long authorId) throws PortalException, SystemException {
        super.setModelId(simulationId,authorId);
    }


    @Override
    public String getDescription() throws SystemException {
        return base.getDescription();
    }

    @Override
    public String getName() throws SystemException {
        return base.getName();
    }

    @Override
    public void setDescription(String description, Long updateAuthorId) throws SystemException, PortalException {
        base.setDescription(description, updateAuthorId);
    }

    @Override
    public void setName(String name, Long updateAuthorId) throws SystemException, PortalException {
        base.setName(name, updateAuthorId);
    }

    @Override
    public List<PlanDescription> getAllDescriptionVersions() throws SystemException {
        return base.getAllDescriptionVersions();
    }

    @Override
    public List<PlanDescription> getPlanDescriptions() throws SystemException {
        return base.getPlanDescriptions();
    }

    @Override
    public Long getScenarioId() throws SystemException {
        return base.getScenarioId();
    }

    @Override
    public void setScenarioId(Long scenarioId, Long authorId) throws PortalException, SystemException {
        base.setScenarioId(scenarioId, authorId);
    }



    @Override
    public List<PlanModelRun> getAllPlanModelRuns() throws SystemException {
        return base.getAllPlanModelRuns();
    }

    @Override
    public PlanMeta getPlanMeta() throws SystemException {
        return base.getPlanMeta();
    }

    @Override
    public Long getMBCategoryId() throws SystemException {
        return base.getMBCategoryId();
    }

    @Override
    public void setMBCategoryId(Long mbCategoryId, Long updateAuthorId) throws SystemException {
        base.setMBCategoryId(mbCategoryId, updateAuthorId);
    }

    @Override
    public Long getPlanGroupId() throws SystemException {
        return base.getPlanGroupId();
    }

    @Override
    public void setPlanGroupId(Long groupId, Long updateAuthorId) throws SystemException {
        base.setPlanGroupId(groupId, updateAuthorId);
    }

    @Override
    public Long getAuthorId() throws SystemException {
        return base.getAuthorId();
    }

    @Override
    public User getAuthor() throws PortalException, SystemException {
        return base.getAuthor();
    }

    @Override
    public void setAuthorId(Long authorId, Long updateAuthorId) throws SystemException {
        base.setAuthorId(authorId, updateAuthorId);
    }

    @Override
    public Date getCreateDate() throws SystemException {
        return base.getCreateDate();
    }

    @Override
    public Date getPublishDate() throws SystemException {
        return base.getPublishDate();
    }

    @Override
    public String getCreator() throws PortalException, SystemException {
        return base.getCreator();
    }

    @Override
    public Integer getVotes() throws SystemException {
        return base.getVotes();
    }

    @Override
    public PlanPositions getPlanPositions() throws NoSuchPlanPositionsException, SystemException {
        return base.getPlanPositions();
    }

    @Override
    public List<Long> getPositionsIds() throws NoSuchPlanPositionsException, SystemException {
        return base.getPositionsIds();
    }

    @Override
    public void setPositions(List<Long> positionsIds, Long updateAuthorId) throws PortalException, SystemException {
        base.setPositions(positionsIds, updateAuthorId);
    }

    @Override
    public List<PlanPositions> getAllPositionsVersions() throws SystemException {
        return base.getAllPositionsVersions();
    }

    @Override
    public boolean hasUserVoted(Long userId) throws PortalException, SystemException {
        return base.hasUserVoted(userId);
    }

    @Override
    public void vote(Long userId) throws PortalException, SystemException {
        base.vote(userId);
    }

    @Override
    public void unvote(Long userId) throws PortalException, SystemException {
        base.unvote(userId);
    }

    @Override
    public List<PlanItem> getAllVersions() throws SystemException {
        return base.getAllVersions();
    }

    @Override
    public void store() throws SystemException {
        base.store();
    }

    @Override
    public void updateAllAttributes() throws SystemException {
        base.updateAllAttributes();
    }

    @Override
    public void updateAttribute(String attributeName) throws SystemException {
        base.updateAttribute(attributeName);
    }

    @Override
    public List<PlanAttribute> getPlanAttributes() throws SystemException {
        return base.getPlanAttributes();
    }

    @Override
    public PlanAttribute getPlanAttribute(String name) throws SystemException {
        return base.getPlanAttribute(name);
    }

    @Override
    public Long getPrimaryKey() {
        return base.getPrimaryKey();
    }

    @Override
    public void setPrimaryKey(Long pk) {
        base.setPrimaryKey(pk);
    }

    @Override
    public Long getId() {
        return base.getId();
    }

    @Override
    public void setId(Long id) {
        base.setId(id);
    }

    @Override
    public Long getPlanId() {
        return base.getPlanId();
    }

    @Override
    public void setPlanId(Long planId) {
        base.setPlanId(planId);
    }

    @Override
    public String getState() {
        return base.getState();
    }

    @Override
    public void setState(String state) {
        base.setState(state);
    }

    @Override
    public Date getUpdated() {
        return base.getUpdated();
    }

    @Override
    public void setUpdated(Date updated) {
        base.setUpdated(updated);
    }

    @Override
    public Long getUpdateAuthorId() {
        return base.getUpdateAuthorId();
    }

    @Override
    public void setUpdateAuthorId(Long updateAuthorId) {
        base.setUpdateAuthorId(updateAuthorId);
    }

    @Override
    public String getUpdateType() {
        return base.getUpdateType();
    }

    @Override
    public void setUpdateType(String updateType) {
        base.setUpdateType(updateType);
    }

    @Override
    public Long getVersion() {
        return base.getVersion();
    }

    @Override
    public void setVersion(Long version) {
        base.setVersion(version);
    }

    @Override
    public PlanItem toEscapedModel() {
        return base.toEscapedModel();
    }

    @Override
    public boolean isNew() {
        return base.isNew();
    }

    @Override
    public boolean setNew(boolean b) {
        return base.setNew(b);
    }

    @Override
    public boolean isCachedModel() {
        return base.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean b) {
        base.setCachedModel(b);
    }

    @Override
    public boolean isEscapedModel() {
        return base.isEscapedModel();
    }

    @Override
    public void setEscapedModel(boolean b) {
        base.setEscapedModel(b);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return base.getPrimaryKeyObj();
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return base.getExpandoBridge();
    }

    @Override
    public Object clone() {
        return base.clone();
    }

    @Override
    public String toXmlString() {
        return base.toXmlString();
    }

    @Override
    public int compareTo(PlanItem o) {
        return base.compareTo(o);
    }


}
