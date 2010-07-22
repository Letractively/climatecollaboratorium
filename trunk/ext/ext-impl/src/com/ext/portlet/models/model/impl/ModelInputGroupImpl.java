/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.model.ModelInputGroup;
import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Simulation;

import java.util.List;


public class ModelInputGroupImpl extends ModelInputGroupModelImpl
    implements ModelInputGroup {
    public ModelInputGroupImpl() {
    }

    public List<ModelInputItem> getInputItems() {
        return ModelInputItemLocalServiceUtil.getItemForGroupId(getModelInputGroupPK());
       
    }

    public List<ModelInputGroup> getChildGroups() {
        return ModelInputGroupLocalServiceUtil.getChildGroups(this);
    }

    public ModelInputGroup getParent() {
        try {
            return ModelInputGroupLocalServiceUtil.getModelInputGroup(getParentGroupPK());
        } catch (PortalException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

     public Simulation getModel() throws SystemException {
        return CollaboratoriumModelingService.repository().getSimulation(getModelId());       
    }

    public MetaData getMetaData() throws SystemException {
        return CollaboratoriumModelingService.repository().getMetaData(getNameAndDescriptionMetaDataId());
    }

}
