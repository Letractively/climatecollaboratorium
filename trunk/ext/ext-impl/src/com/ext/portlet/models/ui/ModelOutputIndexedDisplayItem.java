/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import com.ext.portlet.models.NoSuchModelOutputChartOrderException;
import com.ext.portlet.models.model.ModelOutputChartOrder;
import com.ext.portlet.models.service.ModelOutputChartOrderLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import mit.simulation.climate.client.*;

import java.util.*;

/**
 * Display holder for a data set that consists of an index ({@link MetaData} with
 * varcontext of {@link mit.simulation.climate.client.MetaData.VarContext#INDEX}) and series data
 * ({@link MetaData} with varcontext of {@link mit.simulation.climate.client.MetaData.VarContext#INDEXED})
 *
 * Currently, the only backing information for this element is display order.  In the future, additional
 * data may be added
 *
 *
 * @author: jintrone
 * @date: May 24, 2010
 */
public class ModelOutputIndexedDisplayItem extends ModelOutputDisplayItem {


    private Simulation sim;
    private Scenario scenario;
    private String name;
    private MetaData index;
    private List<ModelOutputSeriesDisplayItem> series = new ArrayList<ModelOutputSeriesDisplayItem>();
    private Map<MetaData, Variable> varmap = new HashMap<MetaData,Variable>();
    private Map<MetaData, ModelOutputSeriesDisplayItem> seriesmap = new HashMap<MetaData,ModelOutputSeriesDisplayItem>();

    private ModelOutputChartType type = ModelOutputChartType.TIME_SERIES;

    private static Log _log = LogFactoryUtil.getLog(ModelOutputIndexedDisplayItem.class);

    private ModelOutputChartOrder chartModel;

    private Map<TupleStatus,ModelOutputErrorBehavior> errorBehaviors = new HashMap<TupleStatus,ModelOutputErrorBehavior>();


    /**
     * A model output group is uniquely identified by a name in a simulation.  Currently this may be
     * derived from {@link mit.simulation.climate.client.MetaData#getLabels()} or {@link mit.simulation.climate.client.MetaData#getName()}.
     * Future iterations will make this more consistent.
     *
     * Note that this constructor implicitly creates a backing store.  Clients should not call this directly,
     * but instead retrieve this element from an {@link ModelDisplay} instance that has been generated by the
     * {@link com.ext.portlet.models.ui.ModelUIFactory}
     *
     *
     * @param s
     * @param name
     * @throws SystemException
     */
    ModelOutputIndexedDisplayItem(Simulation s, String name) throws SystemException {
        super(s);
        this.name = name;

        try {
            chartModel = ModelOutputChartOrderLocalServiceUtil.getChartOrder(s,name);
        } catch (NoSuchModelOutputChartOrderException e) {
            createPersistence();
        } catch (SystemException e) {
            _log.error(e);
            throw(e);
        }


    }

    private void createPersistence() throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(ModelOutputChartOrder.class.getName());
        chartModel = ModelOutputChartOrderLocalServiceUtil.createModelOutputChartOrder(pk);
        chartModel.setModelId(getSimulation().getId());
        chartModel.setModelOutputLabel(name);
        chartModel.setModelChartIsVisible(true);
        ModelOutputChartOrderLocalServiceUtil.addModelOutputChartOrder(chartModel);
        

    }

    public String getName() {
      return name;
    }

    public ModelOutputChartType getChartType() {
       return type;
    }

    /**
     * Returns the variable for the index if the scenario has been set.
     *
     * @return
     */
    public Variable getIndexVariable() {
        return varmap.get(getIndex());
    }


    /**
     * Returns the metadata for the index data for this dataset.  Note that
     * unlike series data below, this element is not wrapper in a display class
     * because no additional information is necessary.
     * 
     * @return
     */
    public MetaData getIndex() {
      return index;
    }

    /**
     * If a scenario has been set, returns a list of variables in preferred display order 
     *
     * @return
     */
    public List<Variable> getSeriesVariables() {
        List<Variable> result = new ArrayList<Variable>();
        for (MetaData md:getSeriesMetaData()){
          result.add(varmap.get(md));
        }
        return result;
    }

    /**
     * Returns a list of metadata in preferred display order.
     *
     *
     * @return
     */
    public List<MetaData> getSeriesMetaData() {
        List<MetaData> result = new ArrayList<MetaData>();
        for (ModelOutputSeriesDisplayItem item:getSeries()) {
            result.add(item.getMetaData());
        }
        return result;
    }

    /**
     * Returns a list of the series metadata wrapped in {@link ModelOutputSeriesDisplayItem},
     * which provides some additional information for interpreting the series.  Series are returned
     * in preferred display order.
     *
     * @return
     */
    public List<ModelOutputSeriesDisplayItem> getSeries() {
        Collections.sort(series);
        return series;
    }

    public ModelOutputSeriesDisplayItem getSeriesForMetaData(MetaData md) {
      return seriesmap.get(md);  
    }


    public int getOrder() {
        
        return null == chartModel ? -1: (chartModel.getModelOutputChartOrder() == null ? -1 : chartModel.getModelOutputChartOrder());
     }

    @Override
    /**
     * Sets the order of this display item within the parent container.  This is the only
     * publicly accessable setter that is persisted in the backing store.
     *
     * @return
     */
    public void setOrder(int o) throws SystemException {
       if (null!= chartModel) {
           chartModel.setModelOutputChartOrder(o);
           ModelOutputChartOrderLocalServiceUtil.updateModelOutputChartOrder(chartModel);
       }
    }


    /**
     * Sets the scenario to enable access to underlying variable through these
     * layout classes.  Does not persist.
     *
     * @param s
     * @throws IncompatibleScenarioException
     */
    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        super.setScenario(s);
        if (sim == null) {
            sim = s.getSimulation();
        }
        if (!s.getSimulation().equals(sim)) {
            throw new IncompatibleScenarioException("Scenario was not generated by simulation "+sim.getName());
        }
        this.scenario = s;
        for (Variable v:scenario.getOutputSet()) {
            varmap.put(v.getMetaData(),v);
        }
        for (ModelOutputSeriesDisplayItem item:getSeries()) {
            item.setScenario(s);
        }

    }



    //following are determined directly from the underlying model, and
    //should not be called from outside of this package


    void setChartType(ModelOutputChartType type) {
       this.type=type;
    }

    void setIndex(MetaData md) {
        this.index = md;
    }

    void addSeriesData(MetaData md) throws SystemException {
        ModelOutputSeriesDisplayItem item = new ModelOutputSeriesDisplayItem(getSimulation(),md);
        series.add(item);
        seriesmap.put(md,item);

    }

    @Override
    public ModelOutputDisplayItemType getDisplayItemType() {
        return ModelOutputDisplayItemType.INDEXED;
    }


    public void setErrorBehavior(TupleStatus status, ErrorPolicy policy, String msg) throws SystemException {
      if (status == TupleStatus.OUT_OF_RANGE) {
          chartModel.setModelIndexRangeMessage(msg);
          chartModel.setModelIndexRangePolicy(policy!=null?policy.name():null);
      } else if (status == TupleStatus.INVALID) {
          chartModel.setModelIndexErrorMessage(msg);
          chartModel.setModelIndexErrorPolicy(policy!=null?policy.name():null);

      }
        errorBehaviors.remove(status);
        ModelOutputChartOrderLocalServiceUtil.updateModelOutputChartOrder(chartModel);
    }


    public ModelOutputErrorBehavior getErrorBehavior(TupleStatus status) {
       ModelOutputErrorBehavior behavior = null;
       if (!errorBehaviors.containsKey(status)) {
         behavior = ModelOutputErrorBehavior.getBehavior(status,chartModel);
         errorBehaviors.put(status,behavior);
       }
       return errorBehaviors.get(status);
    }

    public void setVisible(boolean b) throws SystemException {
        chartModel.setModelChartIsVisible(b);
        ModelOutputChartOrderLocalServiceUtil.updateModelOutputChartOrder(chartModel);

    }

    @Override
    public boolean isVisible() {
        if (chartModel.getModelChartIsVisible() == null) {
            try {
                setVisible(true);
            } catch(SystemException e) {
                _log.error("Error setting chart visibility to default of true",e);
            }
        }

        return chartModel.getModelChartIsVisible()==null || chartModel.getModelChartIsVisible();
    }


}
