package com.ext.portlet.models;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ClientRepository;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import mit.simulation.climate.client.comm.ScenarioNotFoundException;
import junit.framework.TestCase;

public class LightweightTest extends TestCase {
    public void testStupid() throws IOException, ScenarioNotFoundException, ModelNotFoundException {

        ClientRepository repository = ClientRepository.instance("localhost", 8080);
        
        Simulation sim = repository.getSimulation(623L);
        Map<Long, Object> inputs = new HashMap<Long, Object>();
        inputs.put(262L, "2050");
        inputs.put(248L, "1.26");
        inputs.put(266L, "2012");
        inputs.put(247L, "1.58");
        inputs.put(264L, "2012");
        inputs.put(244L, "0.50");
        inputs.put(265L, "2012");
        inputs.put(270L, "2050");
        inputs.put(243L, "0.63");
        inputs.put(268L, "2050");
        inputs.put(240L, "0.50");
        
        Scenario scenario = repository.runModel(sim, inputs, 1L, false);
        System.out.println("##############: " + scenario.getId());
    }
}
