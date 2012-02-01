/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.impl;

import com.ext.portlet.models.NoSuchModelOutputChartOrderException;
import com.ext.portlet.models.model.ModelOutputChartOrder;
import com.ext.portlet.models.service.base.ModelOutputChartOrderLocalServiceBaseImpl;
import com.liferay.portal.SystemException;
import edu.mit.cci.simulation.client.Simulation;


public class ModelOutputChartOrderLocalServiceImpl
    extends ModelOutputChartOrderLocalServiceBaseImpl {

    public ModelOutputChartOrder getChartOrder(Simulation sim, String label) throws SystemException, NoSuchModelOutputChartOrderException {
        return modelOutputChartOrderPersistence.findByModelIdAndLabel(sim.getId(),label);
    }

}
