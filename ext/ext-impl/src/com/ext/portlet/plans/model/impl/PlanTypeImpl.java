package com.ext.portlet.plans.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.service.PlanTypeColumnLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.model.impl.ClientSimulation;


public class PlanTypeImpl extends PlanTypeModelImpl implements PlanType {
    public PlanTypeImpl() {
    }

    public List<Simulation> getAvailableModels() throws SystemException {

        if (this.getModelTypeName()!=null) {
               return new ArrayList<Simulation>(CollaboratoriumModelingService.repository().getSimulationsOfType(this.getModelTypeName()));
        } else if (this.getModelId()>0) {
                return Collections.singletonList(CollaboratoriumModelingService.repository().getSimulation(this.getModelId()));
        } else return Collections.emptyList();
       }

    
    public List<PlanTypeColumn> getColumns() throws SystemException {
        List<PlanTypeColumn> cols= PlanTypeLocalServiceUtil.getColumnsByPlanTypeId(this.getPlanTypeId());
        return cols == null?Collections.<PlanTypeColumn>emptyList():cols;
    }
    
    public List<PlanTypeAttribute> getAttributes() throws SystemException {
        List<PlanTypeAttribute> atts = PlanTypeLocalServiceUtil.getAttributesByPlanTypeId(this.getPlanTypeId());
        return atts==null? Collections.<PlanTypeAttribute>emptyList() :atts;
    }
}
