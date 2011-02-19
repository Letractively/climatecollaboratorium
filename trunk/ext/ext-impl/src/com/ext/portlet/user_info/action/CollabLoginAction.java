package com.ext.portlet.user_info.action;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.login.action.LoginAction;

public class CollabLoginAction extends LoginAction {

    public void processAction(
            ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {
        
        String redirect = ParamUtil.getString(actionRequest, "redirect");
        
        
        redirect = Helper.removeParamFromRequestStr(redirect, "signinRegError");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningInPopup");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningIn");
        redirect = Helper.removeParamFromRequestStr(redirect, "isRegistering");
        redirect = Helper.removeParamFromRequestStr(redirect, "isPasswordReminder");
        
        super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
        
        if (! SessionErrors.isEmpty(actionRequest)) {
            // url parameters
            Map<String, String> parameters = new HashMap<String, String>();
            //boolean isSigningInPopup = ParamUtil.getBoolean(actionRequest, "isSigningInPopup");

            parameters.put("isSigningIn", "true");

            redirect = Helper.modifyRedirectUrl(redirect, actionRequest, parameters);
            
        }
        
        actionResponse.sendRedirect(redirect);
    }

}
