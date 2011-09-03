package com.ext.portlet.landingPage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ext.portlet.landingPage.model.LandingPage;
import com.ext.portlet.landingPage.service.LandingPageLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class LandingPageServlet extends HttpServlet {

    private static final long serialVersionUID = 3534776054953153891L;
    
    private static final Pattern landingPageUrlPattern = Pattern.compile("/landingpage/(\\d*)/.*"); 
    private static final Log _log =  LogFactoryUtil.getLog(LandingPageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            System.out.println(req.getRequestURI());
            Matcher m = landingPageUrlPattern.matcher(req.getRequestURI());
            if (m.find()) {
                Long landingPageId = Long.parseLong(m.group(1));
                LandingPage lp = LandingPageLocalServiceUtil.getLandingPage(landingPageId);
            
                req.getSession().setAttribute("user_from_landing_page", "/landingpage/" + lp.getId() + "/" + lp.getBaseUrl());
                resp.sendRedirect(lp.getTargetUrl());
            }
            else {
                _log.error("Invalid landing page requested, should be in format /landingpage/LANDINGPAGE_ID/name");
            }
        } catch (Exception e) {
            _log.error("Can't process landing page request" , e);
        }
     
    }
}
