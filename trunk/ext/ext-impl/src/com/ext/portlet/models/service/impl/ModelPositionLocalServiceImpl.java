/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.impl;

import java.util.List;

import com.ext.portlet.models.model.ModelPosition;
import com.ext.portlet.models.service.base.ModelPositionLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class ModelPositionLocalServiceImpl
    extends ModelPositionLocalServiceBaseImpl {
    
    public List<ModelPosition> getModelPositionsByModelId(Long modelId) throws SystemException {
        return modelPositionPersistence.findByModelId(modelId);
    }
    
    public void setModelPositions(Long modelId, List<Long> positionIds) throws SystemException {
        modelPositionPersistence.removeByModelId(modelId);
        
        for (Long positionId: positionIds) {
            Long id = CounterLocalServiceUtil.increment(ModelPosition.class.getName());
            ModelPosition modelPosition = createModelPosition(id);
            modelPosition.setModelId(modelId);
            modelPosition.setPositionId(positionId);
            
            addModelPosition(modelPosition);
        }
    }
}
