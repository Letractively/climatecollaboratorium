/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

import com.ext.portlet.mock.MockClientRepository;
import com.ext.portlet.mock.ModelMockFactory;
import com.ext.portlet.mock.Utils;
import com.ext.portlet.models.service.base.ModelInputGroupType;
import com.ext.portlet.models.ui.*;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ClientRepository;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import mit.simulation.climate.client.comm.ScenarioNotFoundException;
import org.climatecollaboratorium.test.BaseCollabTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelsUIFactoryTest2 extends BaseCollabTest {


    Simulation sim;
    List<MetaData> inputs = new ArrayList<MetaData>();
    List<MetaData> outputs = new ArrayList<MetaData>();

    public void setUp() throws Exception {
        sim = ModelMockFactory.makeSimulation("TestSim");
        inputs.add(ModelMockFactory.addInput(sim, "one"));
        inputs.add(ModelMockFactory.addInput(sim, "two"));
        inputs.add(ModelMockFactory.addInput(sim, "three"));
        inputs.add(ModelMockFactory.addInput(sim, "four"));
        inputs.add(ModelMockFactory.addInput(sim, "five"));
        inputs.add(ModelMockFactory.addInput(sim, "six"));
        outputs.add(ModelMockFactory.addOutput(sim, "seven"));
        ClientRepository repository = new MockClientRepository(Collections.singletonList(sim));
        Utils.setField(ClientRepository.class, null, "instance", repository);

    }

    public void testGetAllChildren() throws SystemException, IllegalUIConfigurationException {
      ModelInputGroupDisplayItem tab1 = ModelInputGroupDisplayItem.create(sim, "Tab1", "Tab1", ModelInputGroupType.TAB);
        ModelInputGroupDisplayItem tab2 = ModelInputGroupDisplayItem.create(sim, "Tab2", "Tab2", ModelInputGroupType.TAB);

        ModelInputGroupDisplayItem group1 = ModelInputGroupDisplayItem.create(sim, "Group1", "Group1", ModelInputGroupType.HORIZONTAL);
        ModelInputGroupDisplayItem group2 = ModelInputGroupDisplayItem.create(sim, "Group2", "Group2", ModelInputGroupType.HORIZONTAL);
        ModelInputGroupDisplayItem group3 = ModelInputGroupDisplayItem.create(sim, "Group3", "Group3", ModelInputGroupType.HORIZONTAL);


        for (int i = 0; i < 2; i++) {
            group1.addDisplayItem(inputs.get(i), ModelInputWidgetType.SLIDER);
        }

        group1.addChildGroup(group2);
        for (int i = 2; i < 4; i++) {
            group2.addDisplayItem(inputs.get(i), ModelInputWidgetType.SLIDER);
        }

        for (int i = 4; i < 6; i++) {
            group3.addDisplayItem(inputs.get(i), ModelInputWidgetType.SLIDER);
        }

         ModelDisplay display = ModelUIFactory.getInstance().getDisplay(sim);

        assertEquals(display.getAllIndividualInputs().size(),6);


    }

    public void testInputDisplay_containment() throws IOException, ScenarioNotFoundException, ModelNotFoundException, SystemException, IllegalUIConfigurationException {
        ModelInputGroupDisplayItem tab1 = ModelInputGroupDisplayItem.create(sim, "Tab1", "Tab1", ModelInputGroupType.TAB);
        ModelInputGroupDisplayItem tab2 = ModelInputGroupDisplayItem.create(sim, "Tab2", "Tab2", ModelInputGroupType.TAB);

        ModelInputGroupDisplayItem group1 = ModelInputGroupDisplayItem.create(sim, "Group1", "Group1", ModelInputGroupType.HORIZONTAL);
        ModelInputGroupDisplayItem group2 = ModelInputGroupDisplayItem.create(sim, "Group2", "Group2", ModelInputGroupType.HORIZONTAL);
        ModelInputGroupDisplayItem group3 = ModelInputGroupDisplayItem.create(sim, "Group3", "Group3", ModelInputGroupType.HORIZONTAL);


        for (int i = 0; i < 2; i++) {
            group1.addDisplayItem(inputs.get(i), ModelInputWidgetType.SLIDER);
        }

        group1.addChildGroup(group2);
        for (int i = 2; i < 4; i++) {
            group2.addDisplayItem(inputs.get(i), ModelInputWidgetType.SLIDER);
        }

        for (int i = 4; i < 6; i++) {
            group3.addDisplayItem(inputs.get(i), ModelInputWidgetType.SLIDER);
        }

        tab1.addChildGroup(group1);
        tab2.addChildGroup(group3);

        ModelDisplay display = ModelUIFactory.getInstance().getDisplay(sim);

        assertEquals(2, display.getInputs().size());

        for (ModelInputDisplayItem item : display.getInputs()) {
            assertTrue(item instanceof ModelInputGroupDisplayItem);
            ModelInputGroupDisplayItem gitem = (ModelInputGroupDisplayItem) item;
            assertTrue(gitem.getGroupType() == ModelInputGroupType.TAB);
            if ("Tab1".equals(gitem.getName())) {
                System.err.println(gitem.getName() + "-->");
                assertEquals(gitem.getChildGroups().size(), 1);
                ModelInputGroupDisplayItem subgroup = gitem.getChildGroups().get(0);
                System.err.println(subgroup.getName() + "-->");
                assertEquals(subgroup.getDisplayItems().size(), 2);
                assertEquals(subgroup.getChildGroups().size(), 1);
                subgroup = subgroup.getChildGroups().get(0);
                System.err.println(subgroup.getName());
                assertEquals(subgroup.getDisplayItems().size(), 2);

            }

            if ("Tab2".equals(gitem.getName())) {
                System.err.println(gitem.getName() + "-->");
                assertEquals(gitem.getChildGroups().size(), 1);
                ModelInputGroupDisplayItem subgroup = gitem.getChildGroups().get(0);
                System.err.println(subgroup.getName());
                assertEquals(subgroup.getDisplayItems().size(), 2);


            }

        }


    }


}