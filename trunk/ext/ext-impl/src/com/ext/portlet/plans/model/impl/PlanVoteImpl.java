/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.SystemException;


public class PlanVoteImpl extends PlanVoteModelImpl implements PlanVote {
    public PlanVoteImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            PlanVoteLocalServiceUtil.addPlanVote(this);
        }
        else {
            PlanVoteLocalServiceUtil.updatePlanVote(this);
        }
    }
}
