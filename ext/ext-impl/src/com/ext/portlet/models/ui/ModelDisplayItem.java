/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;

/**
 * @author: jintrone
 * @date: May 24, 2010
 */
public abstract class ModelDisplayItem {

    public abstract String getName();


    private Simulation sim;
    private Scenario scen;

    public ModelDisplayItem(Simulation s) {
      this.sim = s;
    }

    public Simulation getSimulation() {
       return sim;
    }

    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        if (!scen.getSimulation().equals(sim)) {
            throw new IncompatibleScenarioException("Simulation "+sim.getName()+" does not generate scenario "+scen.getId());
        }
      this.scen = s;
    }

    public Scenario getScenario() {
      return scen;
    }
}
