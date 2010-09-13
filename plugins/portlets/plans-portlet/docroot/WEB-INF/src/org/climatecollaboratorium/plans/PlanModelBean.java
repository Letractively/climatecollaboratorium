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
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.plans.wrappers.PlanModelWrapper;

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

    private Map<Long,PlanModelWrapper> availableMap = new HashMap<Long,PlanModelWrapper>();



    private List<SelectItem> availableItems = new ArrayList<SelectItem>();

    public Long selectedItem;

    public Long currentItem;

    public PlanModelBean(PlanItem item, PlanBean plan) throws SystemException, PortalException {
        this.planBean = plan;
        this.planItem = item;

        try {
            List<Simulation> available = planItem.getPlanType().getAvailableModels();
            boolean simple = (available.size() == 1);


            for (Simulation sim : planItem.getPlanType().getAvailableModels()) {
                PlanModelWrapper wrapper = new PlanModelWrapper(sim,simple);
                SelectItem sitem = new SelectItem(wrapper.getId(),wrapper.getName());
                availableItems.add(sitem);
                availableMap.put(wrapper.getId(),wrapper);
                if (sim.getId().equals(planItem.getPlanMeta().getModelId())) {
                    selectedItem = wrapper.getId();
                    currentItem = wrapper.getId();
                }
            }
         } catch (PortalException e) {
            throw new FacesException(e);
        } catch (SystemException e) {
           throw new FacesException(e);
        }

        //This is terrible - shouldn't happen, but if it does, just reset to the default model.
        if (currentItem == null) {
           Long id = planItem.getPlanType().getDefaultModelId();
           for (SelectItem sitem:availableItems) {
              if (sitem.getValue().equals(id)) {
                  currentItem = selectedItem = (Long) sitem.getValue();
                  planItem.setModelId(id,10144L);
                  planItem.store();
              }
           }
        }
    }

    public boolean getEditing() {
        return editing;
    }

    public void setEditing(boolean b) {
        this.editing = b;
    }

    public PlanModelWrapper getCurrentModel() {
        return availableMap.get(currentItem!=null?currentItem:null);
    }

    public PlanModelWrapper getSelectedModel() {
        return availableMap.get(selectedItem!=null?selectedItem:null);
    }

    public List<SelectItem> getAvailableModels() {
       return availableItems;
    }

    public void setSelectedItem(Long item) {
         selectedItem = item;
         planBean.modelChanged();
    }

    public Long getSelectedItem() {
        return selectedItem;
    }

   
    public void startEdit(ActionEvent e) {
        setEditing(true);
    }

    public void cancelEdit(ActionEvent e) {
        setEditing(false);
        selectedItem = currentItem;
    }

    public void submit(ActionEvent e) {
        currentItem = selectedItem;
        try {
            planItem.setModelId(getCurrentModel().getCoreModel().getId(),td.getUserId());
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