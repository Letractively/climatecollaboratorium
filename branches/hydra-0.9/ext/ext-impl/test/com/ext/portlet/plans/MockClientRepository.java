/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import mit.simulation.climate.client.HasId;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ClientRepository;
import mit.simulation.climate.client.comm.MetaDataNotFoundException;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import mit.simulation.climate.client.comm.ScenarioNotFoundException;
import mit.simulation.climate.client.model.jaxb.ClientJaxbReference;
import mit.simulation.climate.client.model.jaxb.ResponseWrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 7, 2010
 * Time: 10:33:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockClientRepository extends ClientRepository {


    Map<Long,Simulation> sims = new HashMap<Long,Simulation>();

    public MockClientRepository(List<Simulation> available) {
      for (Simulation sim:available) {
         sims.put(sim.getId(),sim); 
      }
    }

   

    @Override
    public <T> T resolveReference(ClientJaxbReference ref, Class<T> clz) {
        return null;
    }

    @Override
    public <T extends HasId> T resolveOrDefer(ClientJaxbReference ref, Class clz) {
       return null;
    }

    @Override
    public <T> T requestObject(ClientJaxbReference ref, Class<T> type) {
       return null;
    }

    @Override
    public void register(HasId object) {
    }

    @Override
    public ResponseWrapper handleResponse(InputStream stream) throws JAXBException {
        return null;
    }

    @Override
    public void refreshSimulations() throws IOException {
    }

    @Override
    public Set<Simulation> getAllSimulations() {
        return new HashSet<Simulation>(sims.values());
    }

    @Override
    public Set<Simulation> getSimulationsOfType(String type) {
       Set<Simulation> result = getAllSimulations();
        for (Iterator<Simulation> i = result.iterator();i.hasNext();) {
            if (!i.next().getType().equals(type)) {
                i.remove();
            }
        }
        return result;
    }

    @Override
    public Simulation getSimulation(Long id) {
       return sims.get(id);
    }

    @Override
    public MetaData getMetaData(Long id) {
       return null;
    }

    @Override
    public void updateSimulation(Simulation s) throws ModelNotFoundException, IOException {
    }

    @Override
    public void saveScenario(Scenario s) throws ScenarioNotFoundException, IOException {
    }

    @Override
    public Scenario runModel(Simulation s, Map<Long, Object> inputs, Long userid, boolean save) throws ModelNotFoundException, IOException, ScenarioNotFoundException {
        return null;
    }

    @Override
    public Scenario runModelWithInputNames(Simulation s, Map<String, Object> inputs, Long userid, boolean save) throws ModelNotFoundException, IOException, ScenarioNotFoundException, MetaDataNotFoundException {
        return null;
    }

    @Override
    public Scenario getScenario(Long id) throws IOException {
        return null;
    }

    @Override
    public ResponseWrapper deserialize(InputStream stream) {
      return null;
    }
}
