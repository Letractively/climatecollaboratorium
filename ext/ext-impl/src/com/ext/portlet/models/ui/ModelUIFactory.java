/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;


import com.ext.portlet.models.model.ModelInputGroup;
import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.Variable;

import java.util.*;

/**
 * @author: jintrone
 * @date: May 24, 2010
 */
public class ModelUIFactory {
    private static ModelUIFactory ourInstance = new ModelUIFactory();

    private static Log _log = LogFactoryUtil.getLog(ModelUIFactory.class);
    
    public static ModelUIFactory getInstance() {
        return ourInstance;
    }

    private ModelUIFactory() {

    }

    /**
     * Returns an ordered list of the input groups for this
     *
     * @param s
     * @return
     */
    public ModelDisplay getDisplay(Simulation s) {
        return new ModelDisplay(s);
    }

    public ModelDisplay getDisplay(Scenario s) {
        return new ModelDisplay(s);
    }

    public List<ModelOutputDisplayItem> parseOutputs(Simulation s) {
        Map<String, ModelOutputDisplayItem> found = new HashMap<String, ModelOutputDisplayItem>();
        for (MetaData md : s.getOutputs()) {
            if (md.getVarContext() == MetaData.VarContext.INDEXED) {
                ModelOutputIndexedDisplayItem item = null;
                if (md.getVarType() == MetaData.VarType.FREE) {
                    item = (ModelOutputIndexedDisplayItem) found.get(md.getName());
                    if (item == null) {
                        try {
                            item = new ModelOutputIndexedDisplayItem(s, md.getName());
                        } catch (SystemException e) {
                            _log.error(e);
                        }
                        item.setChartType(ModelOutputChartType.FREE);
                        found.put(md.getName(), item);
                    }
                    try {
                        item.addSeriesData(md);
                    } catch (SystemException e) {
                        _log.error(e);
                    }
                } else if (md.getVarType() == MetaData.VarType.RANGE) {
                    item = (ModelOutputIndexedDisplayItem) found.get(md.getLabels()[1]);
                    if (item == null) {
                        try {
                            item = new ModelOutputIndexedDisplayItem(s, md.getLabels()[1]);
                        } catch (SystemException e) {
                            _log.error(e);
                        }
                        item.setChartType(ModelOutputChartType.FREE);
                        found.put(md.getName(), item);
                    }
                    try {
                        item.addSeriesData(md);
                    } catch (SystemException e) {
                        _log.error(e);
                    }

                } else {
                  _log.warn("Unknown variable type "+md.getVarType());
                   continue;
                }
                if (item.getIndex() == null) {
                    item.setIndex(md.getIndexingMetaData());
                }
            } else if (md.getVarContext() == MetaData.VarContext.SCALAR) {
                found.put(md.getName(), new ModelOutputScalarDisplayItem(s, md));

            }

        }

        return new ArrayList<ModelOutputDisplayItem>(found.values());
    }

    public List<ModelInputDisplayItem> parseInputs(Simulation s) {
        List<ModelInputDisplayItem> result = new ArrayList<ModelInputDisplayItem>();
        Set<MetaData> grouped = new HashSet<MetaData>();
        for (ModelInputGroup group : ModelInputGroupLocalServiceUtil.getInputGroups(s)) {
            for (ModelInputItem item : group.getInputItems()) {
                try {
                    grouped.add(item.getMetaData());
                } catch (SystemException e) {
                    _log.error(e);
                }
            }
            try {
                result.add(new ModelInputGroupDisplayItem(group));
            } catch (SystemException e) {
                _log.error(e);
            }
        }
        for (MetaData md : s.getInputs()) {
            if (!grouped.contains(md)) {
                result.add(getInputItem(ModelInputItemLocalServiceUtil.getItemForMetaData(md)));
            }
        }
        return result;
    }

    public ModelInputDisplayItem getInputItem(ModelInputItem item) {
        try {
            return new ModelInputIndividualDisplayItem(item);
        } catch (SystemException e) {
            _log.error(e);
        }
        return null;

    }


    public static Variable getVariableForMetaData(Scenario s, MetaData md, boolean isInput) {
        Variable result = null;
        for (Variable var : (isInput ? s.getInputSet() : s.getOutputSet())) {
            if (var.getMetaData().equals(md)) {
                result = var;
                break;
            }

        }
        return result;
    }


}
