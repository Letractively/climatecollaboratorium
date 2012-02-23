package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.NoSuchPlanSectionException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanSectionLocalServiceBaseImpl;
import com.ext.portlet.plans.service.persistence.PlanSectionPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class PlanSectionLocalServiceImpl extends PlanSectionLocalServiceBaseImpl {
    
    public PlanSection getCurrentForPlanSectionDef(PlanItem plan, PlanSectionDefinition def) throws SystemException {
        return getCurrentForPlanSectionDef(plan, def, true);
    }
    
    public PlanSection getCurrentForPlanSectionDef(PlanItem plan, PlanSectionDefinition def, boolean createOnEmpty) 
    throws SystemException {
        PlanSection ps = null;
        try {
            ps = this.planSectionPersistence.findByCurrentPlanIdSectionDefinitionId(plan.getPlanId(), def.getId());
        }
        catch (NoSuchPlanSectionException e) {
            // ignore
        }
        if (ps == null && createOnEmpty) {
            ps = createPlanSection(null);
            ps.setPlanId(plan.getPlanId());
            ps.setPlanSectionDefinitionId(def.getId());
            
            ps.setContent(def.getDefaultText());
            
            ps.setVersion(0L);
            ps.setPlanVersion(plan.getVersion());
            ps.setCreated(new Date());
            ps.setUpdateAuthorId(0L);
        }
        return ps;
    }
    

    public PlanSection createNewVersionForPlanSectionDefinition(PlanItem plan, PlanSectionDefinition def) throws SystemException {
        return createNewVersionForPlanSectionDefinition(plan, def, true);
    }
    
    public PlanSection createNewVersionForPlanSectionDefinition(PlanItem plan, PlanSectionDefinition def, boolean store) throws SystemException {
        PlanSection current = getCurrentForPlanSectionDef(plan, def, true);
        
        PlanSection newSection = (PlanSection) current.clone();
        
        newSection.setVersion(current.getVersion()+1);
        newSection.setId(CounterLocalServiceUtil.increment(PlanSection.class.getName()));
        newSection.setPlanVersion(plan.getVersion());
        newSection.setUpdateAuthorId(plan.getUpdateAuthorId());
        newSection.setCreated(new Date());
        newSection.setNew(true);

        if (store) {
            newSection.store();
        }
        
        return newSection;
    }
    
    public List<PlanSection> getAllForPlanDefinition(PlanItem plan, PlanSectionDefinition def) throws SystemException {
        return this.planSectionPersistence.findByPlanIdSectionDefinitionId(plan.getPlanId(), def.getId());
    }
}
