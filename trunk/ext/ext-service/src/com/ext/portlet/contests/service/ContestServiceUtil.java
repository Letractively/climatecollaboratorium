package com.ext.portlet.contests.service;


/**
 * <a href="ContestServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestService
 *
 */
public class ContestServiceUtil {
    private static ContestService _service;

    public static ContestService getService() {
        if (_service == null) {
            throw new RuntimeException("ContestService is not set");
        }

        return _service;
    }

    public void setService(ContestService service) {
        _service = service;
    }
}
