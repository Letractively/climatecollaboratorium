package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateCommentServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateCommentService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateCommentService
 *
 */
public class DebateCommentServiceUtil {
    private static DebateCommentService _service;

    public static DebateCommentService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateCommentService is not set");
        }

        return _service;
    }

    public void setService(DebateCommentService service) {
        _service = service;
    }
}
