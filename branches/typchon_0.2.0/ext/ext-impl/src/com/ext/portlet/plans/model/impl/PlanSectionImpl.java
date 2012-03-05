package com.ext.portlet.plans.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.PlanSectionPlanMap;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionPlanMapLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class PlanSectionImpl extends PlanSectionModelImpl implements PlanSection {
    public PlanSectionImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            if (getId() == null) {
                setId(CounterLocalServiceUtil.increment(PlanSection.class.getName()));
            }
            
            PlanSectionLocalServiceUtil.addPlanSection(this);
        }
        else {
            PlanSectionLocalServiceUtil.updatePlanSection(this);
        }
    }
    
    public PlanSectionDefinition getDefinition() throws PortalException, SystemException {
        return PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(getPlanSectionDefinitionId());
    }
    
    public void addPlanReference(Long planId) throws SystemException {
        PlanSectionPlanMap pspm = 
            PlanSectionPlanMapLocalServiceUtil.createPlanSectionPlanMap(new PlanSectionPlanMapPK(getId(), planId));
        pspm.store();
    }
    
    public List<PlanItem> getReferencedPlans() throws SystemException, NoSuchPlanItemException  {
        List<PlanItem> ret = new ArrayList<PlanItem>();
        
        for (Long planId: PlanSectionPlanMapLocalServiceUtil.findPlanIdsForSection(getId())) {
            ret.add(PlanItemLocalServiceUtil.getPlan(planId));
        }
        return ret;
    }
}
