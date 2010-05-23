package org.climatecollaboratorium.models.support;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ClientRepository;

public class SimulationsHelper {
    
    private static SimulationsHelper instance;
    
    private ClientRepository repository;
    private List<SimulationDecorator> simulations;
    private Map<Long, SimulationDecorator> simulationsById = new HashMap<Long, SimulationDecorator>();
    
    private SimulationsHelper() throws IOException {
        repository = ClientRepository.instance("localhost", 8080);
        simulations = new ArrayList<SimulationDecorator>();
        for (Simulation sim: repository.getAllSimulations()) {
            simulations.add(new SimulationDecorator(sim));
        }
        
        for (SimulationDecorator sim: simulations) {
            simulationsById.put(sim.getId(), sim);
        }
        
        Collections.sort(simulations, new Comparator<SimulationDecorator>() {

            @Override
            public int compare(SimulationDecorator o1, SimulationDecorator o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
    
    public static synchronized SimulationsHelper getInstance() throws IOException {
        if (instance == null) {
            instance = new SimulationsHelper();
        }
        return instance;
    }
    
    public List<SimulationDecorator> getSimulations() {
        return simulations;
    }
    
    public SimulationDecorator getSimulationById(long id) {
        return simulationsById.get(id);
    }
    
    public ClientRepository getRepository() {
        return repository;
    }
}
