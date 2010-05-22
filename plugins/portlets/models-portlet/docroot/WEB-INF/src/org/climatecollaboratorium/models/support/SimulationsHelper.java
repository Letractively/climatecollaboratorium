package org.climatecollaboratorium.models.support;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ClientRepository;

public class SimulationsHelper {
    
    private static SimulationsHelper instance;
    
    private ClientRepository repository;
    private Set<Simulation> simulations;
    private Map<Long, Simulation> simulationsById = new HashMap<Long, Simulation>();
    
    private SimulationsHelper() throws IOException {
        repository = ClientRepository.instance("localhost", 8080);
        simulations = repository.getAllSimulations();
        
        for (Simulation sim: simulations) {
            simulationsById.put(sim.getId(), sim);
        }
    }
    
    public static synchronized SimulationsHelper getInstance() throws IOException {
        if (instance == null) {
            instance = new SimulationsHelper();
        }
        return instance;
    }
    
    public Set<Simulation> getSimulations() {
        return simulations;
    }
    
    public Simulation getSimulationById(long id) {
        return simulationsById.get(id);
    }
}
