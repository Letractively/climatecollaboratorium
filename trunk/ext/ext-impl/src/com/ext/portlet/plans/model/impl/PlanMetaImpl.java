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
}
