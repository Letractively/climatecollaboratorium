package com.ext.portlet.models;

import java.io.IOException;
import java.util.*;

import com.ext.portlet.models.ui.*;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.*;
import mit.simulation.climate.client.comm.ClientRepository;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import mit.simulation.climate.client.comm.ScenarioNotFoundException;

import org.climatecollaboratorium.test.BaseCollabTest;

public class ModelsUIFactoryTest extends BaseCollabTest {

    public void testModel() throws IOException, ScenarioNotFoundException, ModelNotFoundException {

           ClientRepository repository = ClientRepository.instance("localhost", 8080);

//        Simulation sim = repository.getSimulation(760L);
//        Map<Long, Object> inputs = new HashMap<Long, Object>();
//        inputs.put(262L, "2050");
//        inputs.put(248L, "1.26");
//        inputs.put(266L, "2012");
//        inputs.put(247L, "1.58");
//        inputs.put(264L, "2012");
//        inputs.put(244L, "0.50");
//        inputs.put(265L, "2012");
//        inputs.put(270L, "2050");
//        inputs.put(243L, "0.63");
//        inputs.put(268L, "2050");
//        inputs.put(240L, "0.50");

           //Scenario scenario = repository.runModel(sim, inputs, 1L, true);

           Scenario scenario = ClientRepository.instance().getScenario(4187l);
           ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(scenario);

           for (ModelOutputDisplayItem item: modelDisplay.getOutputs()) {
               if (item instanceof ModelOutputIndexedDisplayItem) {
                   ModelOutputIndexedDisplayItem indexedItem = (ModelOutputIndexedDisplayItem) item;
                   System.out.println("Display item : "+indexedItem.getName());
                   List<MetaData> seriesmetadata=indexedItem.getSeriesMetaData();


                   assertTrue(seriesmetadata.size() > 0);
                   System.out.println(seriesmetadata.size()+" variables");
                   for (MetaData v:seriesmetadata) {
                      if (v == null) {
                         System.out.println("---->Missing metadata for "+indexedItem.getName());
                      } else {
                          System.out.print(v.getName()+"("+v.getId()+"):");
                          Variable v1 = indexedItem.getSeriesForMetaData(v).getVariable();
                          if (v1 ==null) {
                              System.out.println("Missing");
                          } else {
                              String out = "OK";
                              for (Tuple t:v1.getValue()) {
                                  if (t.getAllStatuses().contains(TupleStatus.OUT_OF_RANGE)) {
                                     out+=" : RangeError";
                                  }
                                  if (t.getAllStatuses().contains(TupleStatus.INVALID)) {
                                      out+=" : ComputationError";
                                  }
                              }
                              System.out.println(out);
                          }

                      }
                   }

               }
           }


       }


    public void testModelGlobalPreference() throws IOException, SystemException {
        ClientRepository repository = ClientRepository.instance("localhost", 8080);


        Set<Simulation> sims = repository.getAllSimulations();
        assertTrue("Need at least 2 simulations to run test",sims.size()>=2);

        //check one simulation
        Iterator<Simulation> i = sims.iterator();
        Simulation sim = i.next();

        boolean vis1 = ModelUIFactory.isSimulationVisible(sim);

        ModelUIFactory.setSimulationVisible(sim,!vis1);
        boolean vis2 = ModelUIFactory.isSimulationVisible(sim);

        assertTrue(vis1!=vis2);
        ModelUIFactory.setSimulationVisible(sim,vis1);
        vis2 = ModelUIFactory.isSimulationVisible(sim);
        assertTrue(vis1==vis2);

        //check another one, to make sure persistence creation is working
        Simulation sim2 = i.next();
        boolean vis1a = ModelUIFactory.isSimulationVisible(sim2);
        ModelUIFactory.setSimulationVisible(sim2,!ModelUIFactory.isSimulationVisible(sim));

        
        assertTrue(ModelUIFactory.isSimulationVisible(sim)!=ModelUIFactory.isSimulationVisible(sim2));
        ModelUIFactory.setSimulationVisible(sim2,vis1a);
        assertTrue(ModelUIFactory.isSimulationVisible(sim2)==vis1a);

    }

    public void testExistingScenario() throws IOException, ScenarioNotFoundException, ModelNotFoundException, SystemException {

        ClientRepository repository = ClientRepository.instance("localhost", 8080);

//        Simulation sim = repository.getSimulation(760L);
//        Map<Long, Object> inputs = new HashMap<Long, Object>();
//        inputs.put(262L, "2050");
//        inputs.put(248L, "1.26");
//        inputs.put(266L, "2012");
//        inputs.put(247L, "1.58");
//        inputs.put(264L, "2012");
//        inputs.put(244L, "0.50");
//        inputs.put(265L, "2012");
//        inputs.put(270L, "2050");
//        inputs.put(243L, "0.63");
//        inputs.put(268L, "2050");
//        inputs.put(240L, "0.50");

        //Scenario scenario = repository.runModel(sim, inputs, 1L, true);

        Scenario scenario = ClientRepository.instance().getScenario(4187l);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(scenario);
        String errmessage = "Some message";

        String nonvisiblechart = null;
        Long nonvisibleseriesid = null;

        for (ModelOutputDisplayItem item: modelDisplay.getOutputs()) {
            if (item instanceof ModelOutputIndexedDisplayItem) {
                ModelOutputIndexedDisplayItem indexedItem = (ModelOutputIndexedDisplayItem) item;
                if (nonvisiblechart == null) {
                    ((ModelOutputIndexedDisplayItem) item).setVisible(false);
                    nonvisiblechart = item.getName();
                    ((ModelOutputIndexedDisplayItem) item).setErrorBehavior(TupleStatus.OUT_OF_RANGE, ErrorPolicy.DISPLAY_AVAILABLE_WITH_MSG,errmessage);
                }
                if (nonvisibleseriesid == null) {
                    for (ModelOutputSeriesDisplayItem series:indexedItem.getSeries()) {
                        series.setVisible(false);
                        series.setErrorBehavior(TupleStatus.INVALID, ErrorPolicy.NO_DISPLAY_WITH_MSG, errmessage);
                        nonvisibleseriesid = series.getMetaData().getId();
                        break;
                    }
                }
            }

        }

        modelDisplay = ModelUIFactory.getInstance().getDisplay(scenario);
        for (ModelOutputDisplayItem item: modelDisplay.getOutputs()) {
            if (item instanceof ModelOutputIndexedDisplayItem && item.getName().equals(nonvisiblechart)) {
                assertTrue("Chart should not be visible",!item.isVisible());
                ModelOutputErrorBehavior behavior = item.getErrorBehavior(TupleStatus.OUT_OF_RANGE);
                assertNotNull(behavior);
                assertTrue(behavior.getStatus() == TupleStatus.OUT_OF_RANGE);
                assertTrue(errmessage.equals(behavior.getMessage()));
                assertTrue(behavior.getPolicy() == ErrorPolicy.DISPLAY_AVAILABLE_WITH_MSG );
            }

            for (ModelOutputSeriesDisplayItem series:((ModelOutputIndexedDisplayItem)item).getSeries()) {
                     if (series.getMetaData().getId().equals(nonvisibleseriesid)) {
                         assertTrue(!series.isVisible());
                         ModelOutputErrorBehavior behavior = series.getErrorBehavior(TupleStatus.INVALID);
                         assertNotNull(behavior);
                         assertTrue(errmessage.equals(behavior.getMessage()));
                         assertTrue(behavior.getPolicy() == ErrorPolicy.NO_DISPLAY_WITH_MSG);
                         assertTrue(behavior.getStatus()==TupleStatus.INVALID);
                         break;

                    }
            }
        }

         
    }


}
