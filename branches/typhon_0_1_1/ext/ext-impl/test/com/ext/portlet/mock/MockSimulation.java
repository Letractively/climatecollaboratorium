/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mock;

import mit.simulation.climate.client.EntityState;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;

import java.net.URL;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 21, 2010
 * Time: 11:12:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockSimulation implements Simulation {


    Long id;
    String name;
    List<MetaData> inputs = new ArrayList<MetaData>();
    List<MetaData> outputs = new ArrayList<MetaData>();


    public MockSimulation(Long id, String name) {
        this.id = id;
        this.name = name;
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
        return inputs;
    }

    @Override
    public void addInput(MetaData md) {
        inputs.add(md);
    }

    @Override
    public List<MetaData> getOutputs() {
       return outputs;
    }

    @Override
    public void addOutput(MetaData md) {
        outputs.add(md);
    }

    @Override
    public List<MetaData> getCombinedOutputs() {
       return outputs;
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
    public void setState(EntityState name) {
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
    public String getType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
