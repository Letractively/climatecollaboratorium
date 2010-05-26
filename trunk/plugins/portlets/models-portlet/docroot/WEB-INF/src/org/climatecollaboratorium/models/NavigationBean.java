package org.climatecollaboratorium.models;

import java.io.IOException;

import javax.faces.event.ActionEvent;

import mit.simulation.climate.client.Simulation;

import org.climatecollaboratorium.models.support.SimulationsHelper;

public class NavigationBean {
    private final static String INDEX_PAGE = "index";
    private final static String DETAILS_PAGE = "details";

    private String currentPage = INDEX_PAGE;

    private BreadcrumbBean breadcrumbBean;
    private SimulationBean simulationBean;
    private Simulation selectedSimulation;

    private long modelId;

    public BreadcrumbBean getBreadcrumbBean() {
        return breadcrumbBean;
    }

    public void setBreadcrumbBean(BreadcrumbBean breadcrumbBean){
        this.breadcrumbBean = breadcrumbBean;
        updateBreadcrumb();

    }

    public SimulationBean getSimulationBean() {
        return simulationBean;
    }

    public void setSimulationBean(SimulationBean simulationBean) throws IOException  {
        this.simulationBean = simulationBean;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public void update(ActionEvent event) throws IOException {
        selectedSimulation = SimulationsHelper.getInstance().getSimulationById(modelId);
        if (selectedSimulation != null) {
            simulationBean.setSimulation(selectedSimulation);
            currentPage = DETAILS_PAGE;
        }
        else {
            currentPage = INDEX_PAGE;
        }
        updateBreadcrumb();
    }

    private void updateBreadcrumb() {
        breadcrumbBean.clear();
        breadcrumbBean.addItem("Model index", "#");

        if (selectedSimulation != null) {
            breadcrumbBean.addItem(selectedSimulation.getName(), "#modelId=" + selectedSimulation.getId());
        }
    }


}