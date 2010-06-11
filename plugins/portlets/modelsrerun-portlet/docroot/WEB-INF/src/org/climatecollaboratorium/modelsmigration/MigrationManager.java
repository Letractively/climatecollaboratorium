package org.climatecollaboratorium.modelsmigration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.Variable;
import mit.simulation.climate.client.comm.ClientRepository;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import mit.simulation.climate.client.comm.ScenarioNotFoundException;

import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class MigrationManager {
    private static final String REPOSITORY_HOST = "localhost";
    private static final int REPOSITORY_PORT = 8080;
    private List<PlanTypeWrapper> wrappedPlanTypes;
    Map<Long, Boolean> migrationStatuses = new HashMap<Long, Boolean>();
    Map<Long, String> migrationMessages = new HashMap<Long, String>();
    
    private Long targetModelId = 623L;
    private String serviceHost = REPOSITORY_HOST;
    private Integer servicePort = REPOSITORY_PORT;
    
    public List<Plan> getPlans() throws SystemException {
        return PlanLocalServiceUtil.getPlans(0, Integer.MAX_VALUE);
    }
    
    public Set<Simulation> getSimulations() throws IOException {
        ClientRepository repository = ClientRepository.instance(serviceHost, servicePort);
        return repository.getAllSimulations();
    }
    
    public List<SelectItem> getSimulationsItems() throws IOException {
        List<Simulation> simulations = new ArrayList<Simulation>(getSimulations());
        Collections.sort(simulations, new Comparator<Simulation>() {

            @Override
            public int compare(Simulation o1, Simulation o2) {
                return (int) (o1.getId() - o2.getId());
            }
        });
        List<SelectItem> items = new ArrayList<SelectItem>();
        for (Simulation s: simulations) {
            items.add(new SelectItem(s.getId(), s.getId() + ": " + s.getName()));
        }
        return items;
    }
    
    public Map<Long, Boolean>getMigrationStatuses() {
        return migrationStatuses;
    }
    
    public Long getTargetModelId() {
        return targetModelId;
    }

    public void setTargetModelId(Long targetModelId) {
        this.targetModelId = targetModelId;
    }

    public void startMigration(ActionEvent e) throws IOException, SystemException, PortalException, ScenarioNotFoundException, ModelNotFoundException {
        migrationMessages = new HashMap<Long, String>();
        migrationStatuses = new HashMap<Long, Boolean>();
        // sadly this doesn't work ;/
        // ClientRepository repository = CollaboratoriumModelingService.repository();
        ClientRepository repository = ClientRepository.instance(serviceHost, servicePort);
        
        Simulation targetSimulation = repository.getSimulation(targetModelId);
        List<Plan> plans = getPlans();
        for (Plan plan: plans) {
            if (plan.getScenarioId() == null || plan.getScenarioId().trim().equals("")) {
                migrationMessages.put(plan.getPlanId(), "Plan " + plan.getName() + " (" + plan.getPlanId() + ") doesn't have scenario defined");
                migrationStatuses.put(plan.getPlanId(), false);
                continue;   
            }
            try {
                Long scenarioId = Long.parseLong(plan.getScenarioId());
                Scenario scenario = repository.getScenario(scenarioId);
                Simulation fromSimulation = scenario.getSimulation();
                
                if (!checkIfSimulationsCompatible(fromSimulation, targetSimulation)) {
                    migrationMessages.put(plan.getPlanId(), "Simulations aren't compatible (target simulation has inputs not defined in source simulation)");
                    migrationStatuses.put(plan.getPlanId(), false);
                    continue;
                }
                
                Map<Long, Long> inputsMapping = getSourceToTargetInputsMapping(fromSimulation, targetSimulation);
           
                Map<Long, Object> values = new HashMap<Long, Object>();
                for (Variable v: scenario.getInputSet()) {
                    values.put(inputsMapping.get(v.getMetaData().getId()), v.getValue().get(0).getValues()[0]);
                }
                Scenario newScenario = repository.runModel(targetSimulation, values, plan.getUserId(), true);
                plan.setScenarioId(newScenario.getId().toString());
                PlanLocalServiceUtil.updatePlan(plan);
                
            } catch (Throwable exception) {
                migrationMessages.put(plan.getPlanId(), "Exception has been thrown: " + exception.getClass().getName() + exception.getMessage());
                migrationStatuses.put(plan.getPlanId(), false);
                exception.printStackTrace();
            }
            migrationStatuses.put(plan.getPlanId(), true);
        }
    }
    
    private boolean checkIfSimulationsCompatible(Simulation from, Simulation to) {
        Set<String> toInputsNames = new HashSet<String>();
        for (MetaData md: to.getInputs()) {
            toInputsNames.add(md.getName());
        }
        
        // check if all inputs have been defined
        for (MetaData md: from.getInputs()) {
            if (!toInputsNames.contains(md.getName())) {
                return false;
            }
        }
        return true;
    }
    
    private Map<Long, Long> getSourceToTargetInputsMapping(Simulation from, Simulation to) {
        Map<Long, Long> fromIdsToIds = new HashMap<Long, Long>();
        Map<String, MetaData> toInputs = new HashMap<String, MetaData>();
        for (MetaData md: to.getInputs()) {
            toInputs.put(md.getName(), md);
        }
        
        // check if all inputs have been defined
        for (MetaData md: from.getInputs()) {
            fromIdsToIds.put(md.getId(), toInputs.get(md.getName()).getId());
        }
        return fromIdsToIds;
    }

    public Map<Long, String> getMigrationMessages() {
        return migrationMessages;
    }
    
    public List<PlanTypeWrapper> getPlanTypes() throws SystemException {
        if (wrappedPlanTypes == null) {
            wrappedPlanTypes = new ArrayList<PlanTypeWrapper>();
            for (PlanType planType :PlanTypeLocalServiceUtil.getPlanTypes(0, Integer.MAX_VALUE)) {
                wrappedPlanTypes.add(new PlanTypeWrapper(planType));
            }
        }
        return wrappedPlanTypes;
    }
    
    public List<SelectItem> getAvailableModels() throws SystemException, IOException {
        ClientRepository repository = ClientRepository.instance(serviceHost, servicePort);
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        for (Simulation simulation: repository.getAllSimulations()) {
            ret.add(new SelectItem(simulation.getId(), simulation.getId() + ": " + simulation.getName()));
        }
        return ret;
    }
    
    public String getServiceHost() {
        return serviceHost;
    }
    
    public Integer getServicePort() {
        return servicePort;
    }
    
    public void setServiceHost(String host) {
        serviceHost = host;
    }
    
    public void setServicePort(Integer port) {
        servicePort = port;
    }
    
    public void updateServiceHost(ActionEvent e) {
        // do nothing
    }
    
}
