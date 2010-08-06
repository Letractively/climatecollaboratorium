package com.ext.portlet.contests.service;


/**
 * <a href="ContestPhaseServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestPhaseService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestPhaseService
 *
 */
public class ContestPhaseServiceUtil {
    private static ContestPhaseService _service;

    public static ContestPhaseService getService() {
        if (_service == null) {
            throw new RuntimeException("ContestPhaseService is not set");
        }

        return _service;
    }

    public void setService(ContestPhaseService service) {
        _service = service;
    }
}
