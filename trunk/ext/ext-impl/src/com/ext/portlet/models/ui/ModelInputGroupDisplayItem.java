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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;

import java.util.*;

/**
 * @author: jintrone
 * @date: May 24, 2010
 */
public class ModelInputGroupDisplayItem extends ModelInputDisplayItem {

    private static Log _log = LogFactoryUtil.getLog(ModelInputGroupDisplayItem.class);


    ModelInputGroup group;

    List<ModelInputDisplayItem> items = new ArrayList<ModelInputDisplayItem>();
    Set<MetaData> knownmd = new HashSet<MetaData>();


    public ModelInputGroupDisplayItem(ModelInputGroup group) throws SystemException {
        super(group.getModel(), group.getMetaData());
        this.group = group;
    }


    private void populateChildren() throws SystemException {
        knownmd = new HashSet<MetaData>();
        items = new ArrayList<ModelInputDisplayItem>();
        for (ModelInputItem item : group.getInputItems()) {
            knownmd.add(item.getMetaData());
            items.add(ModelUIFactory.getInstance().getInputItem(item));
        }
    }


    @Override
    public String getName() {
        try {
            return group.getName() == null ? group.getMetaData().getName() : group.getName();
        } catch (SystemException e) {
            _log.error("Could not retrive group name", e);
        }
        return null;
    }

    @Override
    public String getDescription() {
        try {
            return group.getDescription() == null ? group.getMetaData().getDescription() : group.getDescription();
        } catch (SystemException e) {
            _log.error("Could not retrive group description", e);
        }
        return null;
    }

    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        super.setScenario(s);
        for (ModelInputDisplayItem item : getDisplayItems()) {
            item.setScenario(s);
        }
    }

    @Override
    public int order() {
        return group.getOrder();
    }

   
    public void setOrder(int o) throws SystemException {
        group.setOrder(o);
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
    }


    public List<ModelInputDisplayItem> getDisplayItems() {
        Collections.sort(items);
        return items;
     }

    public void addDisplayItem(MetaData d, ModelInputWidgetType type) throws SystemException {
        if (!knownmd.contains(d)) {
            items.add(ModelInputIndividualDisplayItem.create(getSimulation(), d, type));
        }
    }

    public void removeDisplayItem(MetaData d) throws SystemException {
        ModelInputIndividualDisplayItem toremove = null;
        for (ModelInputDisplayItem item : getDisplayItems()) {
            if (item.getMetaData().equals(d)) {
                toremove = (ModelInputIndividualDisplayItem) item;
                break;
            }
        }
        if (toremove != null) {
            ModelInputItemLocalServiceUtil.deleteModelInputItem(toremove.item);

        }
        populateChildren();

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

}
