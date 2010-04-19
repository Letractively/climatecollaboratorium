/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.service.impl;

import com.ext.portlet.plans.NoSuchUserSettingsException;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.base.PlansUserSettingsLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class PlansUserSettingsLocalServiceImpl
    extends PlansUserSettingsLocalServiceBaseImpl {
    
    public PlansUserSettings getByUserIdPlanTypeId(Long userId, Long planTypeId) throws NoSuchUserSettingsException, SystemException {
        return plansUserSettingsPersistence.findByuserIdPlanTypeId(userId, planTypeId);
    }
}
