package com.ext.portlet.Activity;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.deploy.hot.BaseHotDeployListener;
import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivityInterpreter;
import com.liferay.portlet.social.model.impl.SocialActivityInterpreterImpl;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;

/**
 * This class is responsible for registering activity interpreters for each
 * newly installed plugins
 * 
 */
public class CollabActivityHotDeployListener extends BaseHotDeployListener {
    private static Log _log = LogFactoryUtil.getLog(CollabActivityHotDeployListener.class);

    @Override
    public void invokeDeploy(HotDeployEvent event) throws HotDeployException {
        try {
            // Servlet context

            ServletContext servletContext = event.getServletContext();

            String servletContextName = servletContext.getServletContextName();

            if (_log.isDebugEnabled()) {
                _log.debug("Invoking deploy for " + servletContextName);
            }

            // Company ids

            long[] companyIds = PortalInstances.getCompanyIds();

            // Initialize portlets

            String[] xmls = new String[] {
                    HttpUtil.URLtoString(servletContext
                            .getResource("/WEB-INF/" + Portal.PORTLET_XML_FILE_NAME_STANDARD)),
                    HttpUtil.URLtoString(servletContext.getResource("/WEB-INF/" + Portal.PORTLET_XML_FILE_NAME_CUSTOM)),
                    HttpUtil.URLtoString(servletContext.getResource("/WEB-INF/liferay-portlet.xml")),
                    HttpUtil.URLtoString(servletContext.getResource("/WEB-INF/web.xml")) };

            if (xmls[0] == null) {
                return;
            }

            if (_log.isInfoEnabled()) {
                _log.info("Registering portlets for " + servletContextName);
            }

            List<Portlet> portlets = PortletLocalServiceUtil.initWAR(servletContextName, servletContext, xmls,
                    event.getPluginPackage());

            // Class loader

            ClassLoader portletClassLoader = event.getContextClassLoader();

            servletContext.setAttribute(PortletServlet.PORTLET_CLASS_LOADER, portletClassLoader);

            // Portlet context wrapper

            Iterator<Portlet> itr = portlets.iterator();

            while (itr.hasNext()) {
                Portlet portlet = itr.next();

                SocialActivityInterpreter socialActivityInterpreterInstance = null;

                if (Validator.isNotNull(portlet.getSocialActivityInterpreterClass())) {
                    Class socialInterpreterClass = portletClassLoader.loadClass(portlet
                            .getSocialActivityInterpreterClass());
                    socialActivityInterpreterInstance = (SocialActivityInterpreter) portletClassLoader.loadClass(
                            portlet.getSocialActivityInterpreterClass()).newInstance();

                    if (socialActivityInterpreterInstance instanceof ICollabActivityInterpreter) {
                        ActivitySubscriptionLocalServiceUtil
                                .addActivityInterpreter((ICollabActivityInterpreter) socialActivityInterpreterInstance);
                    }
                }
            }
        } catch (Throwable e) {
            throwHotDeployException(event, "Error registering portlets for ", e);
        }

    }

    @Override
    public void invokeUndeploy(HotDeployEvent arg0) throws HotDeployException {
        // do nothing

    }

}
