/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import com.ext.portlet.models.model.ModelInputGroup;
import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: jintrone
 * @date: May 24, 2010
 */
public class ModelInputGroupDisplayItem extends ModelInputDisplayItem {

    String name;
    String description;

    ModelInputGroup group;

    List<ModelInputDisplayItem> items = new ArrayList<ModelInputDisplayItem>();
    Set<MetaData> knownmd = new HashSet<MetaData>();

    public ModelInputGroupDisplayItem(Simulation s, String name, String description) {
        super(s,null);
        this.name = name;
        this.description = description;
    }

    public ModelInputGroupDisplayItem(Simulation s, MetaData m) {
       super(s,m);
        this.name = m.getName();
        this.description = m.getDescription();
    }

    public ModelInputGroupDisplayItem(ModelInputGroup group) throws SystemException {
       super(group.getModel(),group.getMetaData());
        this.group = group;
        if (group.getName()!=null) {
           this.name = group.getName();
       }
       if (group.getDescription() !=null) {
           this.description = group.getDescription();
       }

        for (ModelInputItem item:group.getInputItems()) {
            knownmd.add(item.getMetaData());
            items.add(ModelUIFactory.getInstance().getInputItem(item));
        }


    }


    public static ModelInputGroupDisplayItem create(Simulation s, String name, String description) throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(ModelInputGroup.class.getName());
        ModelInputGroup group = ModelInputGroupLocalServiceUtil.createModelInputGroup(pk);
        group.setName(name);
        group.setDescription(description);
        group.setModelId(s.getId());

        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);

        return new ModelInputGroupDisplayItem(group);

    }

    public static ModelInputGroupDisplayItem create(Simulation s, MetaData md) throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(ModelInputGroup.class.getName());
        ModelInputGroup group = ModelInputGroupLocalServiceUtil.createModelInputGroup(pk);
        group.setModelId(s.getId());
        group.setNameAndDescriptionMetaDataId(md.getId());
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
        return new ModelInputGroupDisplayItem(group);
    }




    @Override
    public String getName() {
        try {
            return group.getName()==null?group.getMetaData().getName():group.getName();
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    @Override
    public String getDescription() {
        try {
            return group.getDescription() == null?group.getMetaData().getDescription():group.getDescription();
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        super.setScenario(s);
        for (ModelInputDisplayItem item:getDisplayItems()) {
            item.setScenario(s);
        }
    }



    public List<ModelInputDisplayItem> getDisplayItems() {
       return items;
    }

    public void addDisplayItem(MetaData d, ModelInputWidgetType type) throws SystemException {
        if (!knownmd.contains(d)) {
            Long pk = CounterLocalServiceUtil.increment(ModelInputItem.class.getName());
            ModelInputItem item = ModelInputItemLocalServiceUtil.createModelInputItem(pk);
            item.setModelId(getSimulation().getId());
            item.setModelInputItemID(d.getId());
            item.setType(type.name());
            ModelInputItemLocalServiceUtil.updateModelInputItem(item);
            items.add(ModelUIFactory.getInstance().getInputItem(item));
        }
    }
}
