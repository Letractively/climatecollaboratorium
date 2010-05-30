/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;


import com.ext.portlet.models.CollaboratoriumModelingService;
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
import mit.simulation.climate.client.comm.ClientRepository;

import java.io.IOException;
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
     * Returns the layout information for the model
     *
     * @param s
     * @return
     */
    public ModelDisplay getDisplay(Simulation s) {
        return new ModelDisplay(s);
    }


    /**
     * Returns the layout information for the model, and also sets the scenario
     * on the display container (enabling variable retrieval functions through the
     * display classes
     *
     * @param s
     * @return
     */
    public ModelDisplay getDisplay(Scenario s) {
        return new ModelDisplay(s);
    }


    /**
     * Package scoped helper function, used to build the output layout classes for the Simulation
     *
     * @param s
     * @return
     */
    List<ModelOutputDisplayItem> parseOutputs(Simulation s) {
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
                        item.setChartType(ModelOutputChartType.TIME_SERIES);
                        found.put(md.getLabels()[1], item);
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

    /**
     * Package scoped helper function, used to build the input layout classes for the Simulation
     *
     * @param s
     * @return
     */
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
                try {
                    ModelInputItem item = ModelInputItemLocalServiceUtil.getItemForMetaData(s.getId(), md);
                    ModelInputDisplayItem toadd = item==null?ModelInputIndividualDisplayItem.create(s,md,ModelInputWidgetType.TEXT_FIELD):getInputItem(item);
                    result.add(toadd);


                } catch (SystemException e) {
                   _log.error(e);
                }
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

    /**
     * Helper function, returns variable for a scenario given its associated metadata
     *
     * @param s
     * @return
     */
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

    //this is just sample code - could be made into a test case with apppriate mocks
    // (unless of course 999 is actually the id of a model)
    private static void example() throws SystemException, IOException, IncompatibleScenarioException {

        //setting up a new group

        ClientRepository repo = CollaboratoriumModelingService.repository();
        Simulation s = repo.getSimulation(999L);


        //assume first three inputs to be grouped, with the first element serving
        //as name and description of group
        ModelInputGroupDisplayItem group = ModelInputGroupDisplayItem.create(s,s.getInputs().get(0));
        ModelInputDisplayItem item1 = group.addDisplayItem(s.getInputs().get(0),ModelInputWidgetType.SLIDER);
        ModelInputDisplayItem item2 = group.addDisplayItem(s.getInputs().get(1),ModelInputWidgetType.TEXT_FIELD);
        ModelInputDisplayItem item3 = group.addDisplayItem(s.getInputs().get(2),ModelInputWidgetType.TEXT_FIELD);

        //set the order of these items
        item1.setOrder(3);
        item2.setOrder(1);
        item3.setOrder(2);


        //changed my mind - I want a better description for this group!
        group.setDescription("A better description!");

        //ok, that's it now let's set the order for the inputs
        ModelDisplay display = ModelUIFactory.getInstance().getDisplay(s);
        List<Long> inputorder = Arrays.asList(123L,345L,456L,678L,901L);

        for (ModelInputDisplayItem input:display.getInputs()) {
           input.setOrder(input.getMetaData()!=null?inputorder.indexOf(input.getMetaData().getId()):999);
        }

        //order for outputs
        List<String> outputorder = Arrays.asList("foo","bar","baz","frank");

        for (ModelOutputDisplayItem output:display.getOutputs()) {
            output.setOrder(outputorder.indexOf(output.getName()));


            //Set the order of the elements in one of the charts
            if ("baz".equals(output.getName())) {
                ModelOutputIndexedDisplayItem item = (ModelOutputIndexedDisplayItem) output;
                List seriesorder = Arrays.asList(321L,432L,543L);
                for (ModelOutputSeriesDisplayItem seriesitem:item.getSeries()) {
                  seriesitem.setOrder(seriesorder.indexOf(seriesitem.getMetaData().getId()));
                }

                //set two of the series to be confidence intervals for a third
                ModelOutputSeriesDisplayItem conf5 = item.getSeriesForMetaData(repo.getMetaData(111L));
                ModelOutputSeriesDisplayItem conf95 = item.getSeriesForMetaData(repo.getMetaData(222L));
                ModelOutputSeriesDisplayItem base = item.getSeriesForMetaData(repo.getMetaData(321L));

                conf5.setAssociatedMetaData(base.getMetaData());
                conf5.setSeriesType(ModelOutputSeriesType.CONF_INTERVAL_5);

                conf95.setAssociatedMetaData(base.getMetaData());
                conf95.setSeriesType(ModelOutputSeriesType.CONF_INTERVAL_95);

            }
        }


        //set the scenario for easy access to underlying variables
        display.setScenario(repo.getScenario(888L));


        //and that should be it!
                        



    }


}
