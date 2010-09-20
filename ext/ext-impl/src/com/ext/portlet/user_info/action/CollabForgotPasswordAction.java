package com.ext.portlet.user_info.action;

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
        
        String answer = ParamUtil.getString(actionRequest, "answer");
        if (answer != null && answer.trim().length() > 0) {
            if (!ReCaptchaUtils.validateCaptcha(actionRequest)) {
                return;
            }
        }
        
        super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
    }
}
