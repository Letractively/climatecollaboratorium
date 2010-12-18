package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateItemVoteStatsServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateItemVoteStatsService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateItemVoteStatsService
 *
 */
public class DebateItemVoteStatsServiceUtil {
    private static DebateItemVoteStatsService _service;

    public static DebateItemVoteStatsService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateItemVoteStatsService is not set");
        }

        return _service;
    }

    public void setService(DebateItemVoteStatsService service) {
        _service = service;
    }
}
