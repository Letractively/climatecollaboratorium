/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ModelInputItemImpl extends ModelInputItemModelImpl
    implements ModelInputItem {
    public ModelInputItemImpl() {
    }

    public MetaData getMetaData() throws SystemException {
        return CollaboratoriumModelingService.repository().getMetaData(getModelInputItemID());
    }

    public Simulation getModel() throws SystemException {
        return CollaboratoriumModelingService.repository().getSimulation(getModelId());
    }

    public Map<String,String> getPropertyMap() {
        return parseTypes(getProperties());
    }


     public static Map<String,String> parseTypes(String props) {
        if (props == null) return Collections.emptyMap();
        Map<String,String> result = new HashMap<String,String>();
        for (String type:props.split(";")) {
            String[] kv = type.split("=");
            if (kv.length>1) {
                result.put(kv[0],kv[1]);
            }
        }
        return result;
    }
     
    public void saveProperties(Map<String, String> props) throws SystemException {
        StringBuilder sb = new StringBuilder();
        
        for (String key: props.keySet()) {
            sb.append(key);
            sb.append("=");
            sb.append(props.get(key));
            sb.append(";");
        }
        
        setProperties(sb.toString());
        store();
        
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            ModelInputItemLocalServiceUtil.addModelInputItem(this);
        }
        else {
            ModelInputItemLocalServiceUtil.updateModelInputItem(this);
        }
    }
}



