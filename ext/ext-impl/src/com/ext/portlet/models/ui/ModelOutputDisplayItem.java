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

    /**
     * Specifies the error behavior for this display item given an error status.
     * Returns null if no associated behavior.
     *
     * @param status
     * @return
     */
    public abstract ModelOutputErrorBehavior getErrorBehavior(TupleStatus status);


    /**
     * If the scenario is set, returns the current error on this set.  This method
     * walks the list of tuples, searching for errors and returns the appropriate behavior
     * if an error that has an associated behavior is encountered.
     *
     * Returns null if no error is found or no behavior is found for an encountered error
     *
     * @return
     */

    public abstract ModelOutputErrorBehavior getError();

    /**
     * Whether or not this display element should be displayed
     *
     * @return
     */
    public abstract boolean isVisible();

   

    
    public ModelOutputChartType getChartType() {
        return ModelOutputChartType.FREE;
    }


}
