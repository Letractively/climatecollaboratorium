/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service;


/**
 * <a href="MigrationDataServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.migration.service.MigrationDataService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.service.MigrationDataService
 *
 */
public class MigrationDataServiceUtil {
    private static MigrationDataService _service;

    public static MigrationDataService getService() {
        if (_service == null) {
            throw new RuntimeException("MigrationDataService is not set");
        }

        return _service;
    }

    public void setService(MigrationDataService service) {
        _service = service;
    }
}
