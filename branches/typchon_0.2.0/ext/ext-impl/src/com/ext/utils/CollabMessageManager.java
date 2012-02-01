package com.ext.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.util.PortalUtil;

public class CollabMessageManager {
    
    private final static String COLLAB_MESSAGES_SESSION_KEY = "CLIMATECOLAB_MESSAGES";
    private HttpServletRequest request;
    
    public CollabMessageManager(HttpServletRequest request) {
        this.request = request;
    }
    
    public List<CollabMessage> getMessagesAndClear() {
        List<CollabMessage> ret = getMessagesList(request);
        
        request.getSession().removeAttribute(COLLAB_MESSAGES_SESSION_KEY);
        
        return ret;
    }
    
    static public void addMessage(PortletRequest request, CollabMessage.Severity severity, String message) {
        CollabMessage msg = new CollabMessage(severity, message);
        getMessagesList(request).add(msg);
    }
    
    static private List<CollabMessage> getMessagesList(PortletRequest request) {
        HttpServletRequest req = PortalUtil.getHttpServletRequest(request);
        return getMessagesList(req);
    }
    
    static private List<CollabMessage> getMessagesList(HttpServletRequest request) {
        List<CollabMessage> ret = (List<CollabMessage>) request.getSession().getAttribute(COLLAB_MESSAGES_SESSION_KEY);
        
        if (ret == null) {
            ret = new ArrayList<CollabMessage>();
            request.getSession().setAttribute(COLLAB_MESSAGES_SESSION_KEY, ret);
        }
        
        return ret;
    }
    

}
