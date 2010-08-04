/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans;

import com.ext.portlet.models.CollaboratoriumModelingService;

import com.liferay.portal.SystemException;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.model.impl.ClientSimulation;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 27, 2010
 * Time: 5:05:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlanModelWrapper {

    static String DEFAULT_DESCRIPTION = "The Basic Disaggregation model uses three regions for emissions and global indicators for land use";
    static String DEFAULT_NAME="Basic Model";

    Simulation coreModel;
    Simulation disaggregationModel;
    String link = null;

    public PlanModelWrapper(Simulation sim) {
       coreModel = sim;
       disaggregationModel = lookupDisaggregationModel(sim);
        if (disaggregationModel!=null) {
            String[] parts = disaggregationModel.getURL().toExternalForm().split("/");
            link = "/excel_wrapper-servlet/rest/file/"+parts[parts.length-1];
        }
    }

    private Simulation lookupDisaggregationModel(Simulation sim) {
         String disaggid = ClientSimulation.parseTypes(sim).get("disagg");
        try {
            return disaggid==null?null: CollaboratoriumModelingService.repository().getSimulation(Long.parseLong(disaggid));
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public String getName() {
     return disaggregationModel==null?DEFAULT_NAME:disaggregationModel.getName();
    }

    public String getDescription() {
        return disaggregationModel==null?DEFAULT_DESCRIPTION:disaggregationModel.getDescription();
    }

    public Long getId() {
        return coreModel.getId();
    }

    public Simulation getCoreModel() {
        return coreModel;
    }

    public Simulation getDisaggregationModel() {
        return disaggregationModel;
    }

    public String getLink() {
        return link;
    }

}