package com.ext.portlet.plans.model.impl;

import java.util.List;

import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.service.PlanTypeColumnLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.SystemException;


public class PlanTypeImpl extends PlanTypeModelImpl implements PlanType {
    public PlanTypeImpl() {
    }
    
    public List<PlanTypeColumn> getColumns() throws SystemException {
        return PlanTypeLocalServiceUtil.getColumnsByPlanTypeId(this.getPlanTypeId());
    }
    
    public List<PlanTypeAttribute> getAttributes() throws SystemException {
        return PlanTypeLocalServiceUtil.getAttributesByPlanTypeId(this.getPlanTypeId());
    }
}
