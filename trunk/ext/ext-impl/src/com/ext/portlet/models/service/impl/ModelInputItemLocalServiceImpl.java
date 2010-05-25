/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.impl;

import com.ext.portlet.models.NoSuchModelInputItemException;
import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.service.base.ModelInputItemLocalServiceBaseImpl;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;

import java.util.Collections;
import java.util.List;


public class ModelInputItemLocalServiceImpl
    extends ModelInputItemLocalServiceBaseImpl {


    public List<ModelInputItem> getItemsForModel(Simulation sim) {
        try {
            return modelInputItemPersistence.findByModelId(sim.getId());
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return Collections.emptyList();

    }

    public ModelInputItem getItemForMetaData(MetaData md) {
        try {
            return modelInputItemPersistence.findByModelInputId(md.getId());
        } catch (NoSuchModelInputItemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;

    }

    public List<ModelInputItem> getItemForGroupId(Long groupid) {
        try {
            return modelInputItemPersistence.findByModelGroupId(groupid);
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;

    }
}
