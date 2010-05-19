/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import java.util.List;

import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portlet.messageboards.model.MBMessage;

/**
 * Class that extends base Liferay model class with new properties and
 * fields.
 * 
 * @author janusz.p
 * @version 1.0
 *
 */
public class PlanImpl extends PlanModelImpl implements Plan {
    /**
     * List of positions referenced by a plan.
     */
    private List<MBMessage> _positions;
    
    /**
     * Getter for positions.
     */
    public List<MBMessage> getPositions() {
        return _positions;
    }
    
    /**
     * Setter for positions.
     * 
     * @param positions new value for positions.
     * 
     */
    public void setPositions(List <MBMessage> positions) {
        _positions = positions;
    }
    
    /**
     * Retrieves plan type.
     * @throws SystemException 
     * @throws PortalException 
     */
    public PlanType getPlanType() throws PortalException, SystemException {
        return PlanTypeLocalServiceUtil.getPlanType(this.getPlanTypeId());
    }
    
    /**
     * Constructor, empty.
     */
    public PlanImpl() {
    }
}
