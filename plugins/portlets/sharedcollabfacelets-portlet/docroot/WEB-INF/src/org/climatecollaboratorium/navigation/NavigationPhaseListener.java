package org.climatecollaboratorium.navigation;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.portlet.PortletRequest;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


import com.liferay.portal.util.PortalUtil;

public class NavigationPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent phaseEvent) {

        FacesContext fc = phaseEvent.getFacesContext();
        System.out.println(fc.getExternalContext().getInitParameterMap());
        System.out.println(getPortalRequest(fc));
        HttpServletRequestWrapper sr = getPortalRequest(fc);
        System.out.println("sr request uri: " + sr.getRequestURI());
        System.out.println("sr context path: " + sr.getContextPath());

        String currentUrl = PortalUtil.getCurrentURL(sr);
       // PortalUtil.getHttpServletRequest(sr);
        System.out.println(currentUrl);
        System.out.println(getHttpServletRequest(fc));
        HttpServletRequest rq = getHttpServletRequest(fc);
        System.out.println("Context path: " + rq.getContextPath());
        System.out.println("Path info: " + rq.getPathInfo());
        System.out.println(rq.getContextPath());
        System.out.println(rq.getContextPath());
        //phaseEvent.getFacesContext().getApplication().getNavigationHandler().handleNavigation(fc, null, "testTag");
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    public static HttpServletRequestWrapper getPortalRequest(FacesContext context) {
        Map<String, Object> requests = context.getExternalContext().getRequestMap();
        System.out.println("#########################################\n\n");
        for (String requestName : requests.keySet()) {
            System.out.println(requestName + ":\t\t" + requests.get(requestName) + "\tis portlet request: " + (requests.get(requestName) instanceof PortletRequest));
        }
        System.out.println("\n\n*****************************************");
        
        for (String requestName : requests.keySet()) {
            if (requests.get(requestName) instanceof HttpServletRequestWrapper) {
                System.out.println("Found request with param: " + requestName);
                return (HttpServletRequestWrapper) requests.get(requestName);
            }
        }
        return null;
    }
    
    public HttpServletRequest getHttpServletRequest(FacesContext context) {
        return PortalUtil.getHttpServletRequest((PortletRequest) context.getExternalContext().getRequestMap().get("javax.portlet.request"));
    }

}
