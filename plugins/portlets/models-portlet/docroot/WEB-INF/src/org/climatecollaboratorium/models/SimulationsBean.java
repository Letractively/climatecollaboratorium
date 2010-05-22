package org.climatecollaboratorium.models;

import java.io.IOException;
import java.util.Set;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ClientRepository;

import com.liferay.portal.SystemException;

public class SimulationsBean {
    
    private ClientRepository r;
    private Set<Simulation> simulations;
    
    public SimulationsBean() throws SystemException, IOException {
        r = ClientRepository.instance("localhost", 8080);
        simulations = r.getAllSimulations();
    }
    
    public Set<Simulation> getSimulations() {
        return simulations;
    }
}
