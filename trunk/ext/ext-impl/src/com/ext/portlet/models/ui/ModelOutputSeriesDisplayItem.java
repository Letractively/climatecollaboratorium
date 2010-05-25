/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import com.ext.portlet.models.NoSuchModelOutputItemException;
import com.ext.portlet.models.model.ModelOutputItem;
import com.ext.portlet.models.service.ModelOutputItemLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;

/**
 * @author: jintrone
 * @date: May 25, 2010
 */
public class ModelOutputSeriesDisplayItem extends ModelOutputDisplayItem{


    ModelOutputItem item;

    private MetaData md;


    private static Log _log = LogFactoryUtil.getLog(ModelOutputSeriesDisplayItem.class);

    public ModelOutputSeriesDisplayItem(Simulation s, MetaData md) throws SystemException {
        super(s);
        this.md = md;
        try {
            ModelOutputItemLocalServiceUtil.getOutputItem(md);
        } catch (NoSuchModelOutputItemException e) {
            createPersistence();
        } catch (SystemException e) {
           _log.error(e);
            throw(e);
        }
    }

    private void createPersistence() throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(ModelOutputItem.class.getName());
        item = ModelOutputItemLocalServiceUtil.createModelOutputItem(pk);
    }

    public MetaData getMetaData() {
        return md;
    }

    @Override
    public String getName() {
      return md.getName();
    }


    @Override
    public int order() {
        return item!=null && item.getModelOutputItemOrder()!=null?item.getModelOutputItemOrder():-1;
    }

    public void setOrder(int i) throws SystemException {
        item.setModelOutputItemOrder(i);
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
    }

    public ModelOutputSeriesType getSeriesType() {
        return (item.getItemType() == null?ModelOutputSeriesType.NORMAL:ModelOutputSeriesType.valueOf(item.getItemType()));
    }

    public void setSeriesType(ModelOutputSeriesType type) throws SystemException {
        if (item!=null) {
            item.setItemType(type.name());
            ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
        }
    }


}
