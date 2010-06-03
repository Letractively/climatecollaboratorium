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
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class MigrationManager {
    Map<Long, Boolean> migrationStatuses = new HashMap<Long, Boolean>();
    Map<Long, String> migrationMessages = new HashMap<Long, String>();
    
    private Long targetModelId = 623L;
    
    public List<Plan> getPlans() throws SystemException {
        Plan p;
        return PlanLocalServiceUtil.getPlans(0, Integer.MAX_VALUE);
    }
    
    public Set<Simulation> getSimulations() throws IOException {
        ClientRepository repository = ClientRepository.instance("localhost", 8080);
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
        ClientRepository repository = ClientRepository.instance("localhost", 8080);
        
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
                    migrationMessages.put(plan.getPlanId(), "Simulations aren't compatible (target simulation has inputs not defined in source simulation");
                    migrationStatuses.put(plan.getPlanId(), false);
                    continue;
                }
            
                Map<Long, Object> values = new HashMap<Long, Object>();
                for (Variable v: scenario.getInputSet()) {
                    values.put(v.getMetaData().getId(), v.getValue().get(0).getValues()[0]);
                }
                repository.runModel(targetSimulation, values, plan.getUserId(), true);
            } catch (Throwable exception) {
                migrationMessages.put(plan.getPlanId(), "Exception has been thrown: " + exception.getClass().getName() + exception.getMessage());
                migrationStatuses.put(plan.getPlanId(), false);
                exception.printStackTrace();
            }
            migrationStatuses.put(plan.getPlanId(), true);
        }
    }
    
    private boolean checkIfSimulationsCompatible(Simulation from, Simulation to) {
        Set<Long> toInputsIds = new HashSet<Long>();
        for (MetaData md: to.getInputs()) {
            toInputsIds.add(md.getId());
        }
        
        // check if all inputs have been defined
        for (MetaData md: from.getInputs()) {
            if (!toInputsIds.contains(md.getId())) {
                return false;
            }
        }
        return true;
    }

    public Map<Long, String> getMigrationMessages() {
        return migrationMessages;
    }
    
}
