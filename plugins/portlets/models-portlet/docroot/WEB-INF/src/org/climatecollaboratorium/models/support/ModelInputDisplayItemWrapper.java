package org.climatecollaboratorium.models.support;

import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;

import com.ext.portlet.models.ui.IncompatibleScenarioException;
import com.ext.portlet.models.ui.ModelDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItemType;
import com.ext.portlet.models.ui.ModelInputWidgetType;
import com.liferay.portal.SystemException;

public class ModelInputDisplayItemWrapper extends ModelInputDisplayItem {
    private ModelInputDisplayItem wrappedItem;

    public ModelInputDisplayItemWrapper(Simulation sim, MetaData md) {
        super(sim, md);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ModelInputDisplayItemType getDisplayItemType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setOrder(int o) throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return super.getDescription();
    }

    @Override
    public MetaData getMetaData() {
        // TODO Auto-generated method stub
        return super.getMetaData();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public ModelInputWidgetType getType() {
        // TODO Auto-generated method stub
        return super.getType();
    }

    @Override
    public int compareTo(ModelDisplayItem o) {
        // TODO Auto-generated method stub
        return super.compareTo(o);
    }

    @Override
    public Scenario getScenario() {
        // TODO Auto-generated method stub
        return super.getScenario();
    }

    @Override
    public Simulation getSimulation() {
        // TODO Auto-generated method stub
        return super.getSimulation();
    }

    @Override
    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        // TODO Auto-generated method stub
        super.setScenario(s);
    }
    

}
