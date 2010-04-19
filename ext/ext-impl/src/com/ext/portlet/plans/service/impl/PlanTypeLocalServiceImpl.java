package com.ext.portlet.plans.service.impl;

import java.util.List;

import com.ext.portlet.plans.NoSuchPlanTypeException;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.service.base.PlanTypeLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class PlanTypeLocalServiceImpl extends PlanTypeLocalServiceBaseImpl {
    public PlanType getDefaultPlanType() throws NoSuchPlanTypeException, SystemException {
        return planTypePersistence.findBydefault(true);
    }
    
    public List<PlanTypeColumn> getColumnsByPlanTypeId(long planTypeId) throws SystemException {
        return planTypePersistence.getPlanTypeColumns(planTypeId);
    }
    
    public List<PlanTypeAttribute> getAttributesByPlanTypeId(long planTypeId) throws SystemException {
        return planTypePersistence.getPlanTypeAttributes(planTypeId);
    }
}
