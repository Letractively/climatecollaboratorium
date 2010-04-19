package com.ext.portlet.user_info.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

public class CollabForgotPasswordAction extends com.liferay.portlet.login.action.ForgotPasswordAction {
    public ActionForward render(
            ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws Exception {
        return super.render(mapping, form, portletConfig, renderRequest, renderResponse);
    }
    
    public void processAction(
            ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {
        super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
        
        String screenName = ParamUtil.getString(actionRequest, "screenName");
        if (SessionErrors.isEmpty(actionRequest) && !screenName.trim().equals("")) {
            
        }
    }
}
