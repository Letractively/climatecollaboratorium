/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mock;

import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 21, 2010
 * Time: 11:26:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class ModelMockFactory {

    public static Long id = 0l;

    public static Simulation makeSimulation(String name) {
        return new MockSimulation(id++,name);
    }

    public static MetaData addInput(Simulation sim, String name) {
        MetaData md = new MockMetaData(id++,name,null, MetaData.VarContext.SCALAR, MetaData.VarType.RANGE);
        sim.addInput(md);
        return md;
    }

    public static MetaData addOutput(Simulation sim, String name) {
        MetaData result = null;
        sim.addOutput(result = new MockMetaData(id++,name,null,MetaData.VarContext.SCALAR,MetaData.VarType.RANGE));
        return result;
    }

    public static MetaData createIndexedOutput(Simulation sim, MetaData indexingmd, String name) {
        MetaData result;
        sim.addOutput(result = new MockMetaData(id++,name,indexingmd, MetaData.VarContext.INDEXED, MetaData.VarType.RANGE));
        indexingmd.setVarContext(MetaData.VarContext.INDEX);
        return result;
    }


}
