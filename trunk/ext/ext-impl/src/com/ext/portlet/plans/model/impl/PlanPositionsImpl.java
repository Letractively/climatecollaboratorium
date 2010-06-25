package com.ext.portlet.plans.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.plans.model.PlanPositionItem;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanPositionItemPK;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class PlanPositionsImpl extends PlanPositionsModelImpl
    implements PlanPositions {
    public PlanPositionsImpl() {
    }
    
    public List<Long> getPositionsIds() throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        for (PlanPositionItem item: PlanPositionItemLocalServiceUtil.getAllIdsForPlanPositions(this)) {
            ret.add(item.getPositionId());
        }
        return ret;
    }
    
    
    public void store() throws SystemException {
        if (this.isNew()) {
            PlanPositionsLocalServiceUtil.addPlanPositions(this);
        }
        else {
            PlanPositionsLocalServiceUtil.updatePlanPositions(this);
        }
    }
    
    public void setPositionsIds(List<Long> positionsIds) throws PortalException, SystemException {
        // delete egzisting associations
        List<Long> actual = getPositionsIds();
        for (Long id: actual) {
            PlanPositionItem planPositionItem = PlanPositionItemLocalServiceUtil.getPlanPositionItem(new PlanPositionItemPK(this.getId(), id));
            PlanPositionItemLocalServiceUtil.deletePlanPositionItem(planPositionItem);
        }
        
        // add new associations) 
        for (Long id: positionsIds) {
            PlanPositionItem planPositionItem = PlanPositionItemLocalServiceUtil.createPlanPositionItem(new PlanPositionItemPK(this.getId(), id));
            PlanPositionItemLocalServiceUtil.addPlanPositionItem(planPositionItem);       
        }
        
    }
}
