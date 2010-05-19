package com.ext.portlet.messaging.service;


/**
 * <a href="MessageServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.messaging.service.MessageService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.service.MessageService
 *
 */
public class MessageServiceUtil {
    private static MessageService _service;

    public static void addMessage(java.lang.Long recipientId,
        java.lang.String subject, java.lang.String content)
        throws com.liferay.util.mail.MailEngineException,
            javax.mail.internet.AddressException {
        getService().addMessage(recipientId, subject, content);
    }

    public static MessageService getService() {
        if (_service == null) {
            throw new RuntimeException("MessageService is not set");
        }

        return _service;
    }

    public void setService(MessageService service) {
        _service = service;
    }
}
