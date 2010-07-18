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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;

import java.util.Collections;
import java.util.List;


public class ModelInputItemLocalServiceImpl
    extends ModelInputItemLocalServiceBaseImpl {


    private static Log _log = LogFactoryUtil.getLog(ModelInputItemLocalServiceImpl.class);

    public List<ModelInputItem> getItemsForModel(Simulation sim) {
        try {
            return modelInputItemPersistence.findByModelId(sim.getId());
        } catch (SystemException e) {
            _log.error("WTF now",e);
        }
        return Collections.emptyList();

    }

    public ModelInputItem getItemForMetaData(Long modelId, MetaData md) {
        try {
            return modelInputItemPersistence.findByModelIdModelInputId(modelId, md.getId());
        } catch (NoSuchModelInputItemException e) {
            //ignore
        } catch (SystemException e) {
            _log.error("Unexpected error",e);
        }
        return null;

    }

    public List<ModelInputItem> getItemForGroupId(Long groupid) {
        try {
            return modelInputItemPersistence.findByModelGroupId(groupid);
        } catch (SystemException e) {
            _log.error("goddamn!",e);
        }
        return null;

    }
}
