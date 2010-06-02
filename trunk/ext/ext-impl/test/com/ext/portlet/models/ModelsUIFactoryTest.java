package com.ext.portlet.models;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mit.simulation.climate.client.*;
import mit.simulation.climate.client.comm.ClientRepository;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import mit.simulation.climate.client.comm.ScenarioNotFoundException;

import org.climatecollaboratorium.test.BaseCollabTest;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;

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
        System.out.println("##############: " + scenario.getId());
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


}
