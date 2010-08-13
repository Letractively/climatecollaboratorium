/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.faces.FacesException;
import javax.faces.event.ActionEvent;

import mit.simulation.climate.client.Simulation;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 23, 2010
 * Time: 11:28:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlanModelBean {



    private PlanBean planBean;
    private PlanItem planItem;
    private boolean editing = false;
    private ThemeDisplay td = Helper.getThemeDisplay();

    private List<PlanModelWrapper> availableItems = new ArrayList<PlanModelWrapper>();
    private Map<Long,PlanModelWrapper> availableMap = new HashMap<Long,PlanModelWrapper>();

    public PlanModelWrapper selectedModel;

    public PlanModelWrapper currentModel;

    public PlanModelBean(PlanItem item, PlanBean plan) {
        this.planBean = plan;
        this.planItem = item;

        try {
            for (Simulation sim : planItem.getPlanType().getAvailableModels()) {
                PlanModelWrapper wrapper = new PlanModelWrapper(sim);
                availableItems.add(wrapper);
                availableMap.put(wrapper.getId(),wrapper);
                if (sim.getId().equals(planItem.getPlanMeta().getModelId())) {
                    currentModel = wrapper;
                    selectedModel = currentModel;
                }
            }
         } catch (PortalException e) {
            throw new FacesException(e);
        } catch (SystemException e) {
           throw new FacesException(e);
        }
    }

    public boolean getEditing() {
        return editing;
    }

    public void setEditing(boolean b) {
        this.editing = b;
    }

    public PlanModelWrapper getCurrentModel() {
        return currentModel;
    }

    public List<PlanModelWrapper> getAvailableModels() {
       return availableItems;
    }

    public void setSelectedId(Long id) {
      selectedModel = availableMap.get(id);
    }

    public Long getSelectedId() {
        return selectedModel !=null?selectedModel.getId():-1;
    }


    public void startEdit(ActionEvent e) {
        setEditing(true);
    }

    public void cancelEdit(ActionEvent e) {
        setEditing(false);
        selectedModel = currentModel;
    }

    public void submit(ActionEvent e) {
        currentModel = selectedModel;
        try {
            planItem.setModelId(currentModel.getCoreModel().getId(),td.getUserId());
        } catch (PortalException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SystemException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        setEditing(false);

    }

    public boolean isSelectable() {
        return availableItems!=null && availableItems.size() > 1;  
    }



}