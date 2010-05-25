/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.Variable;

/**
 * @author: jintrone
 * @date: May 24, 2010
 */
public class ModelInputDisplayItem extends ModelDisplayItem {

    private MetaData md;

    public ModelInputDisplayItem(Simulation sim, MetaData md) {
      super(sim);
      this.md = md;
    }
   
    public MetaData getMetaData() {
     return md;
    }

    public Variable getVariable() {
        if (getScenario()!=null) {
            return ModelUIFactory.getVariableForMetaData(getScenario(),getMetaData(),true);
        }
        return null;
    }

    @Override
    public String getName() {
        return md.getName();
    }

    public String getDescription() {
       return md.getDescription();
    }
}
