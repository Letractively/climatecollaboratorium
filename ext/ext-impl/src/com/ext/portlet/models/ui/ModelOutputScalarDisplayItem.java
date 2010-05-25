/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.Variable;

/**
 * @author: jintrone
 * @date: May 25, 2010
 */
public class ModelOutputScalarDisplayItem extends ModelOutputDisplayItem {

    private MetaData md;

    private static Log _log = LogFactoryUtil.getLog(ModelOutputScalarDisplayItem.class);


    public ModelOutputScalarDisplayItem(Simulation s, MetaData d) {
        super(s);
        this.md = d;
    }

    public MetaData getMetaData() {
        return md;
    }

    public Variable getVariable() {
        if (getScenario()!=null) {
            return ModelUIFactory.getVariableForMetaData(getScenario(),getMetaData(),false);
        }
        return null;

    }

    public String getName() {
        return md.getName();
    }

    @Override
    public int order() {
       return -1;
    }

    @Override
    public void setOrder(int o) throws SystemException {
       _log.warn("Setting order on scalar items is not currently supported");
    }
}
