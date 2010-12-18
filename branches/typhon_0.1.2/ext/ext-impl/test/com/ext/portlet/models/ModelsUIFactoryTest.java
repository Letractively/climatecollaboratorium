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

    public void testModel() throws IOException, ScenarioNotFoundException, ModelNotFoundException, SystemException, IllegalUIConfigurationException {

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

    public void testErrorHandling() throws IOException, SystemException, ScenarioNotFoundException, ModelNotFoundException, IllegalUIConfigurationException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Simulation sim = repo.getSimulation(760L);
        Scenario scenario = repo.runModel(sim, getErrorProducingInput(), 1L, false);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(scenario);
        Map<Long,ModelOutputErrorBehavior[]> cleanup = new HashMap<Long,ModelOutputErrorBehavior[]>();
        TupleStatus[] statuses = new TupleStatus[] {TupleStatus.OUT_OF_RANGE,TupleStatus.INVALID};

        for (ModelOutputDisplayItem item:modelDisplay.getOutputs()) {
            if (item instanceof ModelOutputIndexedDisplayItem) {
                ModelOutputIndexedDisplayItem moid = (ModelOutputIndexedDisplayItem)item;
                cleanup.put(moid.getIndex().getId(),new ModelOutputErrorBehavior[] {moid.getErrorBehavior(statuses[0]),moid.getErrorBehavior(statuses[1])});
                moid.setErrorBehavior(TupleStatus.OUT_OF_RANGE,ErrorPolicy.NO_DISPLAY_WITH_MSG,"Indexed range error");
                moid.setErrorBehavior(TupleStatus.INVALID,ErrorPolicy.NO_DISPLAY_WITH_MSG,"Indexed invalid error");

                if (moid.getRangeError() != null) {
                    debug("Range error in index : "+moid.getName());
                    debug("Message "+moid.getRangeError().getMessage());
                }

                if (moid.getInvalidError() != null) {
                    debug("Invalid error in index : "+moid.getName());
                    debug("Message "+moid.getInvalidError().getMessage());
                }


                Set<ModelOutputSeriesDisplayItem> rangeerrors = new HashSet<ModelOutputSeriesDisplayItem>();
                Set<ModelOutputSeriesDisplayItem> invaliderrors = new HashSet<ModelOutputSeriesDisplayItem>();


                for (ModelOutputDisplayItem subitem:moid.getSeries()) {
                    if (subitem instanceof ModelOutputSeriesDisplayItem) {
                        ModelOutputSeriesDisplayItem mosdi = (ModelOutputSeriesDisplayItem)subitem;
                        cleanup.put(mosdi.getMetaData().getId(),new ModelOutputErrorBehavior[] {mosdi.getErrorBehavior(statuses[0]),mosdi.getErrorBehavior(statuses[1])});
                        mosdi.setErrorBehavior(TupleStatus.OUT_OF_RANGE,ErrorPolicy.NO_DISPLAY_WITH_MSG,"Series range error");
                        mosdi.setErrorBehavior(TupleStatus.INVALID,ErrorPolicy.NO_DISPLAY_WITH_MSG,"Series invalid error");

                       if (mosdi.getRangeError() != null) {
                            debug("Range error in series : "+mosdi.getName());
                            debug("Message "+mosdi.getRangeError().getMessage());
                           rangeerrors.add(mosdi);
                       }

                        if (mosdi.getInvalidError() != null) {
                            debug("Invalid error in series : "+mosdi.getName());
                            debug("Message "+mosdi.getInvalidError().getMessage());
                            invaliderrors.add(mosdi);
                        }
                    }
                }

                assertTrue(moid.getSeriesWithInvalidError().containsAll(invaliderrors));
                assertTrue(moid.getSeriesWithOutOfRangeError().containsAll(rangeerrors));
                assertTrue(rangeerrors.containsAll(moid.getSeriesWithOutOfRangeError()));
                assertTrue(invaliderrors.containsAll(moid.getSeriesWithInvalidError()));


            }
        }

         for (ModelOutputDisplayItem item:modelDisplay.getOutputs()) {
            if (item instanceof ModelOutputIndexedDisplayItem) {
                ModelOutputIndexedDisplayItem moid = (ModelOutputIndexedDisplayItem)item;
                ModelOutputErrorBehavior[] behaviors = cleanup.get(moid.getIndex().getId());
                moid.setErrorBehavior(statuses[0],behaviors[0]!=null?behaviors[0].getPolicy():null,behaviors[0]!=null?behaviors[0].getMessage():null);
                moid.setErrorBehavior(statuses[1],behaviors[1]!=null?behaviors[1].getPolicy():null,behaviors[1]!=null?behaviors[1].getMessage():null);

                for (ModelOutputDisplayItem subitem:moid.getSeries()) {
                    if (subitem instanceof ModelOutputSeriesDisplayItem) {
                        ModelOutputSeriesDisplayItem mosdi = (ModelOutputSeriesDisplayItem)subitem;
                        behaviors = cleanup.get(mosdi.getMetaData().getId());
                        mosdi.setErrorBehavior(statuses[0],behaviors[0]!=null?behaviors[0].getPolicy():null,behaviors[0]!=null?behaviors[0].getMessage():null);
                        mosdi.setErrorBehavior(statuses[1],behaviors[1]!=null?behaviors[1].getPolicy():null,behaviors[1]!=null?behaviors[1].getMessage():null);
                    }
                }

            }
        }


    }

    private static void debug(String msg) {
      System.out.println(msg);
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

    private static Map<Long,Object> getErrorProducingInput() {
        Map<Long, Object> inputs = new HashMap<Long, Object>();
                //developed
                inputs.put(3027L, "2012");  //start year
                inputs.put(3022L, "2013");  //target year
                inputs.put(3028L, "-.5");   //target

                //developing a
                inputs.put(3024L, "2012");
                inputs.put(3020L, "2013");
                inputs.put(3029L, "-.5");

                //developing b
                inputs.put(3031L, "2012");
                inputs.put(3026L, "2013");
                inputs.put(3032L, "0");


                inputs.put(3034L, "0.50");  //sequestration (afforestation)
                inputs.put(3030L, "0.50");  //deforestation
        return inputs;
    }

    public void testExistingScenario() throws IOException, ScenarioNotFoundException, ModelNotFoundException, SystemException, IllegalUIConfigurationException {

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
