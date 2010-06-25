package com.ext.portlet.plans.service.impl;

import java.util.List;

import com.ext.portlet.plans.model.PlanPositionItem;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.service.base.PlanPositionItemLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class PlanPositionItemLocalServiceImpl
    extends PlanPositionItemLocalServiceBaseImpl {
    
    public List<PlanPositionItem> getAllIdsForPlanPositions(PlanPositions planPositions) throws SystemException {
        return planPositionItemPersistence.findByAllByPlanPositionsId(planPositions.getId());
    }
}
