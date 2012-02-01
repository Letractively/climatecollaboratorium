/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.liferay.portal.SystemException;


public class PlanMetaImpl extends PlanMetaModelImpl implements PlanMeta {
    public PlanMetaImpl() {
    }
    
    public void store() throws SystemException {
        if (this.isNew()) {
            PlanMetaLocalServiceUtil.addPlanMeta(this);
        }
        else {
            PlanMetaLocalServiceUtil.updatePlanMeta(this);
        }
    }
    
    public void vote() throws SystemException {
        int votes = this.getVotes();
        this.setVotes(votes + 1);
        store();
    }
    
    public void unvote() throws SystemException {
        int votes = this.getVotes();
        this.setVotes(Math.max(votes - 1, 0));
        store();
    }
}
