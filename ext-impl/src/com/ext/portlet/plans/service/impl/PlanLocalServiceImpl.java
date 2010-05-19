/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.service.impl;

import java.util.List;

import com.ext.portlet.plans.NoSuchPlanAttributeFilterException;
import com.ext.portlet.plans.NoSuchPlanTypeException;
import com.ext.portlet.plans.PlanFinder;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanPosition;
import com.ext.portlet.plans.model.PlansFilter;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.base.PlanLocalServiceBaseImpl;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;


public class PlanLocalServiceImpl extends PlanLocalServiceBaseImpl {

    @BeanReference(name = "com.ext.portlet.plans.PlanFinder.impl")
    protected PlanFinder planFinder;
    
    public List<Plan> getPlans(long planTypeId, int start, int end, String sortColumn, String sortDirection) throws NoSuchPlanAttributeFilterException, SystemException {
        return planFinder.getPlans(planTypeId, start, end, sortColumn, sortDirection);
    }
    
    public int countPlans(long planTypeId) {
        return planFinder.countPlans(planTypeId);
    }
    
    public List<Plan> getFilteredPlans(PlansUserSettings filter, int start, int end, String sortColumn, 
            String sortDirection) throws Exception {
        return planFinder.getFilteredPlans(filter, start, end, sortColumn, sortDirection);
    }
    
    public int countFilteredPlans(PlansUserSettings filter) throws Exception {
        return planFinder.countFilteredPlans(filter);
    }
    
    public int getUserVotePosition(long userId, String sortColumn, String sortOrder) throws NoSuchPlanTypeException, SystemException, NoSuchPlanAttributeFilterException {
        return planFinder.getUserVotePosition(userId, sortColumn, sortOrder);
    }
    
    public int getFilteredUserVotePosition(PlansUserSettings filter,  long userId, String sortColumn, String sortOrder) throws Exception {
        return planFinder.getFilteredUserVotePosition(filter, userId, sortColumn, sortOrder);
    }
    
    public List < PlanPosition > getPlansPositions(List < Plan > plans) {
        return planFinder.getPlansPositions(plans);
    }
}
