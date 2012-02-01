/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.model.impl;

import com.ext.portlet.Activity.ICollabActivityInterpreter;
import com.ext.portlet.Activity.SubscriptionType;
import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.SystemException;


public class ActivitySubscriptionImpl extends ActivitySubscriptionModelImpl
    implements ActivitySubscription {
    public ActivitySubscriptionImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            ActivitySubscriptionLocalServiceUtil.addActivitySubscription(this);
        }
        else {
            ActivitySubscriptionLocalServiceUtil.updateActivitySubscription(this);
        }
    }
    
    public ICollabActivityInterpreter getInterpreter() {
        return ActivitySubscriptionLocalServiceUtil.getInterpreterForClass(getClassNameId());   
    }
    
    public String getName() {
        return getInterpreter().getName(getClassNameId(), getClassPK(), getType(), getExtraData());
    }
    
    public SubscriptionType getSubscriptionType() {
        return SubscriptionType.getSubscriptionType(getInterpreter());
    }
    
    public void delete() throws SystemException {
        ActivitySubscriptionLocalServiceUtil.deleteActivitySubscription(this);
    }
    
}
