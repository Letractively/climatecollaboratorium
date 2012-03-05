package com.ext.portlet.plans.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.plans.model.PlanSectionPlanMap;
import com.ext.portlet.plans.service.base.PlanSectionPlanMapLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class PlanSectionPlanMapLocalServiceImpl
    extends PlanSectionPlanMapLocalServiceBaseImpl {
    
    public List<Long> findPlanIdsForSection(Long sectionId) throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        for (PlanSectionPlanMap pspm: planSectionPlanMapPersistence.findBySectionId(sectionId)) {
            ret.add(pspm.getRelatedPlanId());
        }
        
        return ret;
    }
}
