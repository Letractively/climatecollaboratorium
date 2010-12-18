/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mock;

import mit.simulation.climate.client.MetaData;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 21, 2010
 * Time: 11:13:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockMetaData implements MetaData {

    Long id;
    String name;
    MetaData indexingMetaData;
    VarContext context;
    VarType type;



    public MockMetaData(Long id, String name, MetaData indexingMetaData, VarContext context, VarType type) {
        this.id = id;
        this.name = name;
        this.indexingMetaData = indexingMetaData;
        this.context = context;
        this.type = type;
    }


    @Override
    public Long getId() {
      return id;
    }

    @Override
    public MetaData getIndexingMetaData() {
        return indexingMetaData;
    }

    @Override
    public void setIndexingMetaData(MetaData md) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public void setName(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getInternalName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setInternalName(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] getUnits() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setUnits(String[] units) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Class<Object>[] getProfile() {
        return new Class[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setProfile(Class<Object>[] profile) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] getLabels() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLabels(String[] lables) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] getMax() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMax(String[] n) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] getMin() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMin(String[] n) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] getDefault() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDefault(String[] n) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] getCategories() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getDescription() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDescription(String desc) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public VarContext getVarContext() {
        return context;
    }

    @Override
    public void setVarContext(VarContext t) {
        this.context = t;
    }

    @Override
    public VarType getVarType() {
        return type;
    }

    @Override
    public void setVarType(VarType t) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setExternalInfo(String info) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getExternalInfo() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getIndex() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setIndex(boolean b) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCategories(String[] categories) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
