/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.model.impl.PlanTypeImpl;
import com.liferay.portal.SystemException;
import com.liferay.portlet.expando.model.ExpandoBridge;
import mit.simulation.climate.client.Simulation;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 7, 2010
 * Time: 10:17:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockPlanType extends PlanTypeImpl {


    Long modelId;
    String modelType;

    Long id;

    public MockPlanType(Long id, Long modelid) {
      this.id = id;
        this.modelId = modelid;

    }

    public MockPlanType(Long id, String modeltype) {
        this.id = id;
        this.modelType = modeltype;
    }

     @Override
    public Long getPlanTypeId() {
        return id;
     }

     @Override
    public List<Simulation> getAvailableModels() throws SystemException {
      return super.getAvailableModels();

    }

     @Override
    public String getModelTypeName() {
        return modelType;
    }

    public Long getModelId() {
        return modelId;
    }


    public Object clone() {
        return null;
    }

    //do nothing




    @Override
    public List<PlanTypeColumn> getColumns() throws SystemException {
        return Collections.<PlanTypeColumn>emptyList();
    }

    @Override
    public List<PlanTypeAttribute> getAttributes() throws SystemException {
        return Collections.<PlanTypeAttribute>emptyList();
    }


    @Override
    public Long getPrimaryKey() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPrimaryKey(Long pk) {
        //To change body of implemented methods use File | Settings | File Templates.
    }



    @Override
    public void setPlanTypeId(Long planTypeId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setName(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getDescription() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDescription(String description) {
        //To change body of implemented methods use File | Settings | File Templates.
    }



    @Override
    public void setModelId(Long modelId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }



    @Override
    public void setModelTypeName(String modelTypeName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean getPublished() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPublished(Boolean published) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long getPublishedCounterpartId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPublishedCounterpartId(Long publishedCounterpartId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean getIsDefault() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setIsDefault(Boolean isDefault) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PlanType toEscapedModel() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isNew() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean setNew(boolean b) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCachedModel() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCachedModel(boolean b) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEscapedModel() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setEscapedModel(boolean b) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toXmlString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int compareTo(PlanType o) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
