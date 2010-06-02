/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.TupleStatus;

/**
 * All model output display items (charts, scalar items, and series items) extend
 * from this class.
 * 
 *
 * @author: jintrone
 * @date: May 25, 2010
 */
public abstract class ModelOutputDisplayItem extends ModelDisplayItem {

    public ModelOutputDisplayItem(Simulation s) {
        super(s);
    }
    
    public abstract ModelOutputDisplayItemType getDisplayItemType();


    public abstract ModelOutputErrorBehavior getErrorBehavior(TupleStatus status);

    public abstract boolean isVisible();

   

    
    public ModelOutputChartType getChartType() {
        return ModelOutputChartType.FREE;
    }
}
