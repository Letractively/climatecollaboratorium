package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateService
 *
 */
public class DebateServiceUtil {
    private static DebateService _service;

    public static DebateService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateService is not set");
        }

        return _service;
    }

    public void setService(DebateService service) {
        _service = service;
    }
}
