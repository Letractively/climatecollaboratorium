package com.ext.portlet.messaging.service;


/**
 * <a href="MessageRecipientStatusServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.messaging.service.MessageRecipientStatusService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.service.MessageRecipientStatusService
 *
 */
public class MessageRecipientStatusServiceUtil {
    private static MessageRecipientStatusService _service;

    public static MessageRecipientStatusService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MessageRecipientStatusService is not set");
        }

        return _service;
    }

    public void setService(MessageRecipientStatusService service) {
        _service = service;
    }
}
