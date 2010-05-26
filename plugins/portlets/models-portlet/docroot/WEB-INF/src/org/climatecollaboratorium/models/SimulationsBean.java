package org.climatecollaboratorium.models;

import java.io.IOException;

import java.util.List;

import mit.simulation.climate.client.comm.ClientRepository;

import org.climatecollaboratorium.models.support.SimulationDecorator;
import org.climatecollaboratorium.models.support.SimulationsHelper;

public class SimulationsBean {

    private ClientRepository r;
    private List<SimulationDecorator> simulations;

    public SimulationsBean() throws IOException {
        simulations = SimulationsHelper.getInstance().getSimulations();
    }

    public List<SimulationDecorator> getSimulations() {
        return simulations;
    }
}