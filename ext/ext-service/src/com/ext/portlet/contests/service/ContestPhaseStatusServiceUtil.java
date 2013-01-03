package com.ext.portlet.contests.service;


/**
 * <a href="ContestPhaseStatusServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestPhaseStatusService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestPhaseStatusService
 *
 */
public class ContestPhaseStatusServiceUtil {
    private static ContestPhaseStatusService _service;

    public static ContestPhaseStatusService getService() {
        if (_service == null) {
            throw new RuntimeException("ContestPhaseStatusService is not set");
        }

        return _service;
    }

    public void setService(ContestPhaseStatusService service) {
        _service = service;
    }
}
