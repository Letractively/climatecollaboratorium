/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.impl;

import com.ext.portlet.models.NoSuchModelGlobalPreferenceException;
import com.ext.portlet.models.model.ModelGlobalPreference;
import com.ext.portlet.models.service.base.ModelGlobalPreferenceLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.Simulation;


public class ModelGlobalPreferenceLocalServiceImpl
    extends ModelGlobalPreferenceLocalServiceBaseImpl {


    public boolean isVisible(Simulation s) throws SystemException {
        ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
        } catch (NoSuchModelGlobalPreferenceException e) {
          //no worries
        }
        return (pref !=null && pref.getVisible()!=null && pref.getVisible());
    }

    public void setVisible(Simulation s, boolean visible) throws SystemException {
         ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
        } catch (NoSuchModelGlobalPreferenceException e) {
          //no worries
        }
        if (pref == null) {

            Long pk = CounterLocalServiceUtil.increment(ModelGlobalPreference.class.getName());
            pref = createModelGlobalPreference(pk);
            pref.setModelId(s.getId());
            pref.setWeight(0);
            pref.setVisible(false);
            addModelGlobalPreference(pref);
            
        }
        pref.setVisible(visible);
        updateModelGlobalPreference(pref);

    }
    

    public int getWeight(Simulation s) throws SystemException {
        ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
        } catch (NoSuchModelGlobalPreferenceException e) {
          //no worries
        }
        int weight = 0;
        if (pref !=null && pref.getWeight()!=null) {
            weight = pref.getWeight();
        }
        return weight;
    }
    
    public void setWeight(Simulation s, int weight) throws SystemException {
        ModelGlobalPreference pref = null;
       try {
           pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
       } catch (NoSuchModelGlobalPreferenceException e) {
         //no worries
       }
       if (pref == null) {
           Long pk = CounterLocalServiceUtil.increment(ModelGlobalPreference.class.getName());
           pref = createModelGlobalPreference(pk);
           pref.setModelId(s.getId());
           pref.setWeight(0);
           pref.setVisible(false);
           addModelGlobalPreference(pref);
       }
       pref.setWeight(weight);
       updateModelGlobalPreference(pref);
   }
    
    public Long getExpertEvaluationPageId(Simulation s) throws SystemException {
        ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
        } catch (NoSuchModelGlobalPreferenceException e) {
          //no worries
        }
        return pref != null ? pref.getExpertEvaluationPageId() : null;
    }
    
   public void setExpertEvaluationPageId(Simulation s, Long pageId) throws SystemException {
        ModelGlobalPreference pref = null;
       try {
           pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
       } catch (NoSuchModelGlobalPreferenceException e) {
         //no worries
       }
       if (pref == null) {
           Long pk = CounterLocalServiceUtil.increment(ModelGlobalPreference.class.getName());
           pref = createModelGlobalPreference(pk);
           pref.setModelId(s.getId());
           pref.setWeight(0);
           pref.setVisible(false);
           addModelGlobalPreference(pref);
       }
       pref.setExpertEvaluationPageId(pageId);
       updateModelGlobalPreference(pref);
   }

}
