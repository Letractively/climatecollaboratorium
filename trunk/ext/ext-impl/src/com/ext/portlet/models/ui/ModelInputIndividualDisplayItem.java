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
 * Layout information for individual (non-group) display elements.
 *
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


    /**
     * Returns the variable associated with this element if a "setScenario" has
     * been called with a valid scenario.
     *
     * @return
     */
     public Variable getVariable() {
        if (getScenario()!=null) {
            return ModelUIFactory.getVariableForMetaData(getScenario(),getMetaData(),true);
        }
        return null;
    }

    /**
     * The widget type for this element
     * @return
     */
    public ModelInputWidgetType getType() {
      return item.getType()==null?ModelInputWidgetType.TEXT_FIELD:ModelInputWidgetType.valueOf(item.getType());
    }


    /**
     * Sets the widget type for this element.  Updates the backing store.
     *
     * @param type
     * @throws SystemException
     */
    public void setType(ModelInputWidgetType type) throws SystemException {
         item.setType(type.name());
         ModelInputItemLocalServiceUtil.updateModelInputItem(item);
    }


    /**
     * The order of this element within its parent container, which may either be a group
     * or the top level display element.
     *
     * @return
     */
    public int order() {
        return item.getOrder()==null?-1:item.getOrder();
    }

    /**
     * Set the display position of this element.  Updates the backing store.
     *
     * @param order
     * @throws SystemException
     */
    public void setOrder(int order) throws SystemException {
       item.setOrder(order);
       ModelInputItemLocalServiceUtil.updateModelInputItem(item);
    }


    /**
     * This is the preferred means for creating a new, non-group display item.
     * Note that calling {@link com.ext.portlet.models.ui.ModelInputGroupDisplayItem#addDisplayItem(mit.simulation.climate.client.MetaData, ModelInputWidgetType)}
     * will call this code and then set the group id on the item.
     *
     * @param sim
     * @param md
     * @param type
     * @return
     * @throws SystemException
     */
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
