package com.ext.portlet.messaging.service.http;

import com.ext.portlet.messaging.service.MessageServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;


/**
 * <a href="MessageServiceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a SOAP utility for the
 * <code>com.ext.portlet.messaging.service.MessageServiceUtil</code> service
 * utility. The static methods of this class calls the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.ext.portlet.messaging.model.MessageSoap</code>. If the method in the
 * service utility returns a <code>com.ext.portlet.messaging.model.Message</code>,
 * that is translated to a <code>com.ext.portlet.messaging.model.MessageSoap</code>.
 * Methods that SOAP cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/tunnel-web/secure/axis. Set the property
 * <code>tunnel.servlet.hosts.allowed</code> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.model.MessageSoap
 * @see com.ext.portlet.messaging.service.MessageServiceUtil
 * @see com.ext.portlet.messaging.service.http.MessageServiceHttp
 *
 */
public class MessageServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(MessageServiceSoap.class);

    public static void addMessage(java.lang.Long recipientId,
        java.lang.String subject, java.lang.String content)
        throws RemoteException {
        try {
            MessageServiceUtil.addMessage(recipientId, subject, content);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
