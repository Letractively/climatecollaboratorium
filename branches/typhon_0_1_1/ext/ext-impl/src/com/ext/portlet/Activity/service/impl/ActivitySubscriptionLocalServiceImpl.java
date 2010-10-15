/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service.impl;

import java.util.List;

import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.base.ActivitySubscriptionLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class ActivitySubscriptionLocalServiceImpl
    extends ActivitySubscriptionLocalServiceBaseImpl {
	
	public List<ActivitySubscription> findByUser(Long userId) throws SystemException {
		return activitySubscriptionPersistence.findByreceiverId(userId);
	}
}
