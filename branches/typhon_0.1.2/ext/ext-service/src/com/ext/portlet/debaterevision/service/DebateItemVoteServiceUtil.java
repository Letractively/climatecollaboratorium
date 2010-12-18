package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateItemVoteServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateItemVoteService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateItemVoteService
 *
 */
public class DebateItemVoteServiceUtil {
    private static DebateItemVoteService _service;

    public static DebateItemVoteService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateItemVoteService is not set");
        }

        return _service;
    }

    public void setService(DebateItemVoteService service) {
        _service = service;
    }
}
