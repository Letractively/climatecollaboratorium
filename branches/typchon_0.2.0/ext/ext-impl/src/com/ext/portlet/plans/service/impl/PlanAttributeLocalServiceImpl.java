/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.service.impl;

import java.util.List;

import com.ext.portlet.plans.NoSuchPlanAttributeException;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.service.base.PlanAttributeLocalServiceBaseImpl;
import com.ext.portlet.plans.service.persistence.PlanAttributePK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class PlanAttributeLocalServiceImpl
    extends PlanAttributeLocalServiceBaseImpl {
	
	
	public void addPlanAttribute(long planId, String attributeName, String attributeValue) throws SystemException {
		long id = CounterLocalServiceUtil.increment(PlanAttribute.class.getName());
		PlanAttribute att = planAttributeLocalService.createPlanAttribute(id);
		att.setPlanId(planId);
		att.setAttributeName(attributeName);
		att.setAttributeValue(attributeValue);
		planAttributeLocalService.addPlanAttribute(att);
	}
	
	public PlanAttribute findPlanAttribute(long planId, String attributeName) throws SystemException {
		try {
			return planAttributePersistence.findByattributeForPlan(planId, attributeName);
		} catch (NoSuchPlanAttributeException e) {
			return null;
		} 
	}
	
	public List<PlanAttribute> getPlanAttributes(Long planId) throws SystemException {
	    return planAttributePersistence.findByplanAttributes(planId);
	}
	
	public List<PlanAttribute> getPlanAttributesByNameValue(String attributeName, String attributeValue) throws SystemException {
	    return planAttributePersistence.findByattributeByNameValue(attributeName, attributeValue);
	}
	
	
}
