package com.ext.portlet.twitter.service;


/**
 * <a href="UserTwitterMappingServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.twitter.service.UserTwitterMappingService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.twitter.service.UserTwitterMappingService
 *
 */
public class UserTwitterMappingServiceUtil {
    private static UserTwitterMappingService _service;

    public static UserTwitterMappingService getService() {
        if (_service == null) {
            throw new RuntimeException("UserTwitterMappingService is not set");
        }

        return _service;
    }

    public void setService(UserTwitterMappingService service) {
        _service = service;
    }
}
