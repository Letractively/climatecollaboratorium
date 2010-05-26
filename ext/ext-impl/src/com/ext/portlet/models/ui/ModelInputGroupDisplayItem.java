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
 * Encapsulates a "group" of input elements to be displayed together.  This
 * element is backed by an {@link ModelInputGroup}
 *
 *
 * @author: jintrone
 * @date: May 24, 2010
 */
public class ModelInputGroupDisplayItem extends ModelInputDisplayItem {

    private static Log _log = LogFactoryUtil.getLog(ModelInputGroupDisplayItem.class);


    ModelInputGroup group;

    List<ModelInputDisplayItem> items = new ArrayList<ModelInputDisplayItem>();
    Set<MetaData> knownmd = new HashSet<MetaData>();

    /**
     * Public constructor requires an existing backed dao. Generally, clients
     * will not call this directly, and the factory will take care of instantiating groups
     * or the static factory method on this class is called.
     *
     * @param group
     * @throws SystemException
     */
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
    /**
     * Returns the name on the metadata, or if present, the name stored in the backing data for this elemtn
     * A non-null name in the dao will always override the metadata name.
     *
     */
    public String getName() {
        try {
            return group.getName() == null ? group.getMetaData().getName() : group.getName();
        } catch (SystemException e) {
            _log.error("Could not retrive group name", e);
        }
        return null;
    }

/**
     * Sets the name for this element.  Will override any name in the metadata.  This method
     * sets a value in the backing store.
     *
     * @param name
     */
    public void setName(String name) throws SystemException {
        group.setName(name);
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
    }


    @Override
    /**
     * Returns the description on the metadata, or if present the description stored in the backing data for this element
     * A non-null description in the dao will always override the metadata description.
     *
     */
    public String getDescription() {
        try {
            return group.getDescription() == null ? group.getMetaData().getDescription() : group.getDescription();
        } catch (SystemException e) {
            _log.error("Could not retrive group description", e);
        }
        return null;
    }

    /**
     * Sets the description for this element.  Will override any description in the metadata.  This method
     * sets a value in the backing store.
     *
     * @param desc
     */
    public void setDescription(String desc) throws SystemException {
        group.setDescription(desc);
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
    }

    /**
     * Sets the scdenario for this display group; sets the scenario for all children
     * @param s
     * @throws IncompatibleScenarioException
     */
    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        super.setScenario(s);
        for (ModelInputDisplayItem item : getDisplayItems()) {
            item.setScenario(s);
        }
    }

    @Override
    public int order() {
        return group.getDisplayItemOrder();
    }

    /**
     * Set the index of this (group) element in the parent contains.  **Currently the
     * parent container is always the top most simulation display.
     *
     * @param o
     * @throws SystemException
     */
    public void setOrder(int o) throws SystemException {
        group.setDisplayItemOrder(o);
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
    }


    /**
     * Get all display items contained by this group.  Items sorted according to their order
     * **Currently, each of these will be
     * a {@link com.ext.portlet.models.ui.ModelInputIndividualDisplayItem}
     * @return
     */
    public List<ModelInputDisplayItem> getDisplayItems() {
        Collections.sort(items);
        return items;
     }


    /**
     * This method will add a new element to this group, and create a new dao for this item.
     * This is the primary means by which new groups can be formed.
     *
     * @param d
     * @param type
     * @throws SystemException
     */
    public ModelInputDisplayItem addDisplayItem(MetaData d, ModelInputWidgetType type) throws SystemException {
        if (!knownmd.contains(d)) {
            ModelInputIndividualDisplayItem item = ModelInputIndividualDisplayItem.create(getSimulation(), d, type);
            item.item.setModelGroupId(group.getModelInputGroupPK());
            ModelInputItemLocalServiceUtil.updateModelInputItem(item.item);
            items.add(item);
            return item;
        }
        return null;
    }

    /**
     * This method will remove a display item from this group, and update the backing store accordingly
     * @param d
     * @throws SystemException
     */
    public void removeDisplayItem(MetaData d) throws SystemException {
        ModelInputIndividualDisplayItem toremove = null;
        for (ModelInputDisplayItem item : getDisplayItems()) {
            if (item.getMetaData().equals(d)) {
                toremove = (ModelInputIndividualDisplayItem) item;
                break;
            }
        }
        if (toremove != null) {
             knownmd.remove(toremove.getMetaData());
            ModelInputItemLocalServiceUtil.deleteModelInputItem(toremove.item);

        }
        populateChildren();

    }

    /**
     * This is the preferred means for creating a new group with a specifc name / description
     *
     * @param s
     * @param name
     * @param description
     * @return
     * @throws SystemException
     */
    public static ModelInputGroupDisplayItem create(Simulation s, String name, String description) throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(ModelInputGroup.class.getName());
        ModelInputGroup group = ModelInputGroupLocalServiceUtil.createModelInputGroup(pk);
        group.setName(name);
        group.setDescription(description);
        group.setModelId(s.getId());

        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);

        return new ModelInputGroupDisplayItem(group);

    }


    /**
     * This is the preferred means for creating a new group with a piece of metadata to be used for the
     * name and description
     *
     */
    public static ModelInputGroupDisplayItem create(Simulation s, MetaData md) throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(ModelInputGroup.class.getName());
        ModelInputGroup group = ModelInputGroupLocalServiceUtil.createModelInputGroup(pk);
        group.setModelId(s.getId());
        group.setNameAndDescriptionMetaDataId(md.getId());
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
        return new ModelInputGroupDisplayItem(group);
    }

}
