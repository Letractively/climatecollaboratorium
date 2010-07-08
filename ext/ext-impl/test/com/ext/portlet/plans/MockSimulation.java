/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import mit.simulation.climate.client.*;
import mit.simulation.climate.client.EntityState;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 7, 2010
 * Time: 10:30:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockSimulation implements Simulation {

    Long id;
    String type;

    public MockSimulation(Long id, String type) {
       this.id = id;
        this.type = type;
    }

@Override
    public String getType() {
        return type;
    }



    public Long getId() {
       return id;
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
    public URL getURL() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setURL(String url) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setURL(URL url) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Date getCreation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCreation(Date d) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<MetaData> getInputs() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addInput(MetaData md) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<MetaData> getOutputs() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addOutput(MetaData md) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<MetaData> getCombinedOutputs() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
    public void setState(mit.simulation.climate.client.EntityState name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EntityState getState() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setType(String type) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public Set<Simulation> getChildren() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, String> getUpdate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isDirty() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
