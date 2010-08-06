package org.climatecollaboratorium.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.models.support.SimulationsHelper;
import org.climatecollaboratorium.navigation.NavigationEvent;

import com.liferay.portal.SystemException;

public class NavigationBean {
    private final static String INDEX_PAGE = "index";
    private final static String DETAILS_PAGE = "details";

    private String currentPage = INDEX_PAGE;

    private BreadcrumbBean breadcrumbBean;
    private SimulationBean simulationBean;
    private Simulation selectedSimulation;
    private Scenario selectedScenario;
    private String page = null;
    private String viewType = "default";

    private long modelId;
    private long scenarioId;
    private Long planId;
    private boolean embeddedCanEdit;
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    
    public NavigationBean() {
        // get request parameters
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();//.getRequestPathInfo())

        if (ctx.getRequestParameterMap().containsKey("modelId")) {
            try {
                modelId = Long.parseLong(ctx.getRequestParameterMap().get("modelId").toString());
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        
        if (ctx.getRequestParameterMap().containsKey("viewType")) {
            viewType = ctx.getRequestParameterMap().get("viewType").toString();
        }
        
        if (ctx.getRequestParameterMap().containsKey("scenarioId")) {
            try {
                scenarioId = Long.parseLong(ctx.getRequestParameterMap().get("scenarioId").toString());
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        
        if (ctx.getRequestParameterMap().containsKey("planId")) {
            try {
                planId = Long.parseLong(ctx.getRequestParameterMap().get("planId").toString());
            }
            catch (NumberFormatException e) {
                // ignore
            }
        }
        
        if (ctx.getRequestParameterMap().containsKey("page")) {
            page = ctx.getRequestParameterMap().get("page").toString();
        }
        if (ctx.getRequestParameterMap().containsKey("canEdit")) {
            embeddedCanEdit = Boolean.parseBoolean(ctx.getRequestParameterMap().get("canEdit").toString());
        }
        
    }

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

    public void setSimulationBean(SimulationBean simulationBean) throws IOException, SystemException  {
        this.simulationBean = simulationBean;
        useModel();
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

    public void update(ActionEvent event) throws IOException, SystemException {
        useModel();
    }
    
    private void useModel() throws IOException, SystemException {
        selectedScenario = SimulationsHelper.getInstance().getScenarioById(scenarioId);
        selectedSimulation = SimulationsHelper.getInstance().getSimulationById(modelId);
        
        if (selectedScenario != null) {
            simulationBean.setScenario(selectedScenario);
            currentPage = DETAILS_PAGE;
        }
        else if (selectedSimulation != null) {
            simulationBean.setSimulation(selectedSimulation);
            currentPage = DETAILS_PAGE;
        }
        else {
            simulationBean.setSimulation(null);
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

    public Simulation getSelectedSimulation() {
        return selectedSimulation;
    }
    
    public String getViewType() {
        return viewType;
    }
    
    public String getPage() {
        return page;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public boolean isEmbeddedCanEdit() {
        return embeddedCanEdit;
    }
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bindEvents();
    }

    private void bindEvents() {
        // unregister old handlers
        for (HandlerRegistration registration: handlerRegistrations) {
            registration.unregister();
        }
        handlerRegistrations.clear();

        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                // check if event
                if (event.getSource().equals("models")) {
                    // do something...
                }

            }

        }));
    }

}