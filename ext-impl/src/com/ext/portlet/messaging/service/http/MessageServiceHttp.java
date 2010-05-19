package com.ext.portlet.messaging.service.http;

import com.ext.portlet.messaging.service.MessageServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;


/**
 * <a href="MessageServiceHttp.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a HTTP utility for the
 * <code>com.ext.portlet.messaging.service.MessageServiceUtil</code> service
 * utility. The static methods of this class calls the same methods of the
 * service utility. However, the signatures are different because it requires an
 * additional <code>com.liferay.portal.security.auth.HttpPrincipal</code>
 * parameter.
 * </p>
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <code>tunnel.servlet.hosts.allowed</code> in
 * portal.properties to configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.security.auth.HttpPrincipal
 * @see com.ext.portlet.messaging.service.MessageServiceUtil
 * @see com.ext.portlet.messaging.service.http.MessageServiceSoap
 *
 */
public class MessageServiceHttp {
    private static Log _log = LogFactoryUtil.getLog(MessageServiceHttp.class);

    public static void addMessage(HttpPrincipal httpPrincipal,
        java.lang.Long recipientId, java.lang.String subject,
        java.lang.String content)
        throws com.liferay.portal.SystemException,
            com.liferay.util.mail.MailEngineException,
            javax.mail.internet.AddressException {
        try {
            Object paramObj0 = recipientId;

            if (recipientId == null) {
                paramObj0 = new NullWrapper("java.lang.Long");
            }

            Object paramObj1 = subject;

            if (subject == null) {
                paramObj1 = new NullWrapper("java.lang.String");
            }

            Object paramObj2 = content;

            if (content == null) {
                paramObj2 = new NullWrapper("java.lang.String");
            }

            MethodWrapper methodWrapper = new MethodWrapper(MessageServiceUtil.class.getName(),
                    "addMessage",
                    new Object[] { paramObj0, paramObj1, paramObj2 });

            try {
                TunnelUtil.invoke(httpPrincipal, methodWrapper);
            } catch (Exception e) {
                if (e instanceof com.liferay.util.mail.MailEngineException) {
                    throw (com.liferay.util.mail.MailEngineException) e;
                }

                if (e instanceof javax.mail.internet.AddressException) {
                    throw (javax.mail.internet.AddressException) e;
                }

                throw new com.liferay.portal.SystemException(e);
            }
        } catch (com.liferay.portal.SystemException se) {
            _log.error(se, se);

            throw se;
        }
    }
}
