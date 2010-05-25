/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import com.ext.portlet.models.model.ModelInputItem;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;

/**
 * @author: jintrone
 * @date: May 25, 2010
 */
public class ModelInputIndividualDisplayItem extends ModelInputDisplayItem {

    ModelInputWidgetType type = ModelInputWidgetType.TEXT_FIELD;
    ModelInputItem item;

    public ModelInputIndividualDisplayItem(Simulation sim, MetaData md) {
        super(sim, md);
    }

    public ModelInputIndividualDisplayItem(ModelInputItem item) throws SystemException {
        this(item.getModel(),item.getMetaData());
        this.item = item;
        type = ModelInputWidgetType.valueOf(item.getType());

    }


    public ModelInputWidgetType getType() {
      return type;
    }

    public void setType(ModelInputWidgetType type) {
        this.type = type;
    }
}
