/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import java.util.List;

import com.ext.portlet.plans.model.PlansFilter;

/**
 * Class that extends base Liferay model class with new properties and
 * fields.
 * 
 * @author janusz.p
 * @version 1.0
 *
 */
public class PlansFilterImpl extends PlansFilterModelImpl implements PlansFilter {
    private List<Long> positionsIds; 
    public PlansFilterImpl() {
    }
    
    /**
     * Getter for positionsIds.
     * @return the positionsIds
     */
    public List<Long> getPositionsIds() {
        return positionsIds;
    }
    /**
     * Setter for positionsIds.
     * @param positionsIds the positionsIds to set
     */
    public void setPositionsIds(List<Long> positionsIds) {
        this.positionsIds = positionsIds;
    }
}
