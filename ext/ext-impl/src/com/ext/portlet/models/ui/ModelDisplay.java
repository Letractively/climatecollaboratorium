/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;

import java.util.*;

/**
 * @author: jintrone
 * @date: May 24, 2010
 */
public class ModelDisplay {

    List<ModelInputDisplayItem> inputs;
    List<ModelOutputDisplayItem> outputs;

    private static Log _log = LogFactoryUtil.getLog(ModelDisplay.class);


    private List<ModelInputGroupDisplayItem> groups = new ArrayList<ModelInputGroupDisplayItem>();
    private Map<MetaData,ModelInputIndividualDisplayItem> individuals = new HashMap<MetaData,ModelInputIndividualDisplayItem>();

            

    private Simulation sim;

    public ModelDisplay(Simulation sim) {
        this.sim = sim;
        inputs = ModelUIFactory.getInstance().parseInputs(sim);
        for (ModelInputDisplayItem item:inputs) {
            if (item instanceof ModelInputGroupDisplayItem) {
                groups.add((ModelInputGroupDisplayItem) item);
            } else if (item instanceof ModelInputIndividualDisplayItem) {
               individuals.put(item.getMetaData(),(ModelInputIndividualDisplayItem)item);
            }
        }
        outputs = ModelUIFactory.getInstance().parseOutputs(sim);

    }

    public ModelDisplay(Scenario scenario) {
        this(scenario.getSimulation());
        try {
            setScenario(scenario);
        } catch (IncompatibleScenarioException e) {
            _log.error("Cannot set scenario",e);

        }
    }

    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        for (ModelDisplayItem input : getInputs()) {
            input.setScenario(s);
        }
        for (ModelDisplayItem output : getOutputs()) {
            output.setScenario(s);
        }
    }

    public List<ModelInputDisplayItem> getInputs() {
        Collections.sort(inputs);
        return inputs;
    }





    public List<ModelOutputDisplayItem> getOutputs() {
        Collections.sort(outputs);
        return outputs;
    }

}
