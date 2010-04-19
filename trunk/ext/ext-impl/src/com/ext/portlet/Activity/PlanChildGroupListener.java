/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import java.util.List;

import com.ext.portlet.plans.PlanActivityKeys;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ModelListener;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class PlanChildGroupListener implements ModelListener<Object> {

	public void onAfterRemove(Object arg0) throws ModelListenerException {
		// TODO Auto-generated method stub

	}

	public void onAfterCreate(Object arg0) throws ModelListenerException {
		// TODO Auto-generated method stub

	}

	public void onBeforeCreate(Object arg0) throws ModelListenerException {
		// TODO Auto-generated method stub

	}

	public void onAfterUpdate(Object arg0) throws ModelListenerException {

	}

	public void onBeforeRemoveAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Plan.class);
		Criterion criterionPublished = RestrictionsFactoryUtil.eq("childGroupId", arg0);
		dynamicQuery.add(criterionPublished);
		try {
			List<Object> ret = PlanLocalServiceUtil.dynamicQuery(dynamicQuery);
			for (Object planObj : ret) {
				Plan plan = (Plan) planObj;
				ActivityUtil.removeSubscription(PlanActivityKeys.ALL, (Long)arg2, plan.getPlanId());
				SocialActivityLocalServiceUtil.addActivity(((Long) arg2).longValue(), ((Long) arg2).longValue(),
						"com.ext.portlet.Activity", plan.getPrimaryKey(), PlanActivityKeys.USER_REMOVED_FROM_PLAN.id(),
						StringPool.BLANK, 0);
			}
		} catch (Exception se) {

		}
	}

	public void onAfterRemoveAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
		// TODO Auto-generated method stub

	}

	public void onBeforeAddAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
		// TODO Auto-generated method stub
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Plan.class);
		Criterion criterionPublished = RestrictionsFactoryUtil.eq("childGroupId", arg0);
		dynamicQuery.add(criterionPublished);
		try {
			List<Object> ret = PlanLocalServiceUtil.dynamicQuery(dynamicQuery);
			for (Object positionObj : ret) {
				Plan plan = (Plan) positionObj;
				ActivityUtil.addSubscription(PlanActivityKeys.ALL, (Long)arg2,plan.getPlanId());
				SocialActivityLocalServiceUtil.addActivity(((Long) arg2).longValue(), ((Long) arg2).longValue(),
						"com.ext.portlet.Activity", plan.getPrimaryKey(), PlanActivityKeys.USER_ADDED_TO_PLAN.id(),
						StringPool.BLANK, 0);
			}
		} catch (Exception se) {
			se.printStackTrace();
		}
	}

	public void onAfterAddAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
		// Add your implementation here
	}

	public void onBeforeRemove(Object arg0) throws ModelListenerException {
		// TODO Auto-generated method stub

	}

	public void onBeforeUpdate(Object arg0) throws ModelListenerException {
	}
}
