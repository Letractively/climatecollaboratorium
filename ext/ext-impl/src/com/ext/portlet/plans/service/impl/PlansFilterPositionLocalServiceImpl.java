/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.ext.portlet.plans.model.PlansFilterPosition;
import com.ext.portlet.plans.service.PlansFilterPositionLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlansFilterPositionLocalServiceBaseImpl;
import com.ext.portlet.plans.service.persistence.PlansFilterPositionPK;
import com.liferay.portal.SystemException;


public class PlansFilterPositionLocalServiceImpl
    extends PlansFilterPositionLocalServiceBaseImpl {
    
    public List<PlansFilterPosition> getPositionsForUserPlanType(Long userId, Long planTypeId) throws SystemException {
        return plansFilterPositionPersistence.findByUserIdPlanTypeId(userId, planTypeId);
    }
    
    public List<Long> getPositionsIds(Long userId, Long planTypeId) throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        for (PlansFilterPosition pos: getPositionsForUserPlanType(userId, planTypeId)) {
            ret.add(pos.getPositionId());
        }
        return ret;
    }
    
    public void storeFilterPositionsIds(Long userId, Long planTypeId, List<Long> positionsIds) throws SystemException {
        // remove positions for given user id/plan type
        plansFilterPositionPersistence.removeByUserIdPlanTypeId(userId, planTypeId);
        
        // save all positions
        for (Long posId: positionsIds) {
            PlansFilterPosition pos = createPlansFilterPosition(new PlansFilterPositionPK(userId, planTypeId, posId));
            addPlansFilterPosition(pos);
        }
    }
}
