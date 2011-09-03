package com.ext.portlet.landingPage.action;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.landingPage.model.LandingPage;
import com.ext.portlet.landingPage.service.LandingPageLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.PortletAction;

public class ViewLandingPageAction extends PortletAction {
    
    public static final String VIEW_FORWARD = "portlet.ext.landingpage.view";

    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        renderRequest.setAttribute("landingPages", LandingPageLocalServiceUtil.getLandingPages(0, Integer.MAX_VALUE));
        
        return mapping.findForward(VIEW_FORWARD);
    }

    @Override
    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        // TODO Auto-generated method stub
        
        String cmd = ParamUtil.getString(actionRequest, "cmd");
        String baseUrl = ParamUtil.getString(actionRequest, "baseUrl");
        String targetUrl = ParamUtil.getString(actionRequest, "targetUrl");
        Long landingPageId = ParamUtil.getLong(actionRequest, "landingPageId");
        if ("add".equals(cmd)) {
            LandingPageLocalServiceUtil.createNewLandingPage(baseUrl, targetUrl);
        }
        else if ("update".equals(cmd)) {
            LandingPage landingPage = LandingPageLocalServiceUtil.getLandingPage(landingPageId);
            landingPage.setBaseUrl(baseUrl);
            landingPage.setTargetUrl(targetUrl);
            landingPage.setUpdated(new Date());
            
            landingPage.store();
        }
        else if ("delete".equals(cmd)) {
            LandingPage landingPage = LandingPageLocalServiceUtil.getLandingPage(landingPageId);
            LandingPageLocalServiceUtil.deleteLandingPage(landingPage);
        }
        
        //super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
    }

    
}
