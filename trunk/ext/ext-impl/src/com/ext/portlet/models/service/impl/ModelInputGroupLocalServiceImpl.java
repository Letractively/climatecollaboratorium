/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.impl;

import com.ext.portlet.models.model.ModelInputGroup;
import com.ext.portlet.models.service.base.ModelInputGroupLocalServiceBaseImpl;
import com.liferay.portal.SystemException;
import edu.mit.cci.simulation.client.Simulation;

import java.util.Collections;
import java.util.List;


public class ModelInputGroupLocalServiceImpl
    extends ModelInputGroupLocalServiceBaseImpl {

    public List<ModelInputGroup> getInputGroups(Simulation sim) {
        try {
            return modelInputGroupPersistence.findByModelId(sim.getId());
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return Collections.emptyList();
    }

     public List<ModelInputGroup> getChildGroups(ModelInputGroup group) {
         try {
             return modelInputGroupPersistence.findByparentModelId(group.getModelInputGroupPK());
         } catch (SystemException e) {
             e.printStackTrace();

         }
         return Collections.emptyList();
     }
}
