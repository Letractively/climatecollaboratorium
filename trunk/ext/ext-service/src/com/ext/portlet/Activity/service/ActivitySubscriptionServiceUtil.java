/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service;


/**
 * <a href="ActivitySubscriptionServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.Activity.service.ActivitySubscriptionService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.Activity.service.ActivitySubscriptionService
 *
 */
public class ActivitySubscriptionServiceUtil {
    private static ActivitySubscriptionService _service;

    public static ActivitySubscriptionService getService() {
        if (_service == null) {
            throw new RuntimeException("ActivitySubscriptionService is not set");
        }

        return _service;
    }

    public void setService(ActivitySubscriptionService service) {
        _service = service;
    }
}
