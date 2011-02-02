package com.ext.portlet.user_info.action;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.poi.hssf.record.RecalcIdRecord;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.recaptcha.ReCaptchaUtils;
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
        
        String redirect = ParamUtil.getString(actionRequest, "redirect");
        
        redirect = Helper.removeParamFromRequestStr(redirect, "signinRegError");
        redirect = Helper.removeParamFromRequestStr(redirect, "isPasswordReminder");
        
        String answer = ParamUtil.getString(actionRequest, "answer");
        if (answer != null && answer.trim().length() > 0) {
            if (!ReCaptchaUtils.validateCaptcha(actionRequest)) {
                return;
            }
        }
        //if (ReCaptchaUtils.validateCaptcha(actionRequest)) {
            super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
        //}
        
        if (! SessionErrors.isEmpty(actionRequest)) {
            // url parameters
            Map<String, String> parameters = new HashMap<String, String>();
            //boolean isSigningInPopup = ParamUtil.getBoolean(actionRequest, "isSigningInPopup");

            parameters.put("isPasswordReminder", "true");

            redirect = Helper.modifyRedirectUrl(redirect, actionRequest, parameters);
            
        }
        actionResponse.sendRedirect(redirect);
        
    }
}
