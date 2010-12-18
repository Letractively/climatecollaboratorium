package com.ext.portlet.facebook.service;


/**
 * <a href="UserFacebookMappingServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.facebook.service.UserFacebookMappingService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.facebook.service.UserFacebookMappingService
 *
 */
public class UserFacebookMappingServiceUtil {
    private static UserFacebookMappingService _service;

    public static UserFacebookMappingService getService() {
        if (_service == null) {
            throw new RuntimeException("UserFacebookMappingService is not set");
        }

        return _service;
    }

    public void setService(UserFacebookMappingService service) {
        _service = service;
    }
}
