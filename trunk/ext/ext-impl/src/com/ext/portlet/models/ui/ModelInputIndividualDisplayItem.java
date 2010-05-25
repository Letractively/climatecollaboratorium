/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.Variable;

/**
 * @author: jintrone
 * @date: May 25, 2010
 */
public class ModelInputIndividualDisplayItem extends ModelInputDisplayItem {

    ModelInputWidgetType type = ModelInputWidgetType.TEXT_FIELD;
    ModelInputItem item;


    public ModelInputIndividualDisplayItem(ModelInputItem item) throws SystemException {
        super(item.getModel(),item.getMetaData());
        this.item = item;
    }

     public Variable getVariable() {
        if (getScenario()!=null) {
            return ModelUIFactory.getVariableForMetaData(getScenario(),getMetaData(),true);
        }
        return null;
    }


    public ModelInputWidgetType getType() {
      return item.getType()==null?ModelInputWidgetType.TEXT_FIELD:ModelInputWidgetType.valueOf(item.getType());
    }

    public void setType(ModelInputWidgetType type) throws SystemException {
         item.setType(type.name());
         ModelInputItemLocalServiceUtil.updateModelInputItem(item);
    }

    public int order() {
        return item.getOrder()==null?-1:item.getOrder();
    }

    public void setOrder(int order) throws SystemException {
       item.setOrder(order);
       ModelInputItemLocalServiceUtil.updateModelInputItem(item);
    }

    public static ModelInputIndividualDisplayItem create(Simulation sim, MetaData md, ModelInputWidgetType type) throws SystemException {
         Long pk = CounterLocalServiceUtil.increment(ModelInputItem.class.getName());
            ModelInputItem item = ModelInputItemLocalServiceUtil.createModelInputItem(pk);
            item.setModelId(sim.getId());
            item.setModelInputItemID(md.getId());
            item.setType(type.name());
            ModelInputItemLocalServiceUtil.updateModelInputItem(item);
        return new ModelInputIndividualDisplayItem(item);
    }
}
