package com.ext.googleanalytycs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CollabStaffMemberCookieServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer;
        try {
            writer = response.getWriter();
        
        writer.append("<html><head><script type=\"text/javascript\">var gaJsHost = " + 
                "((\"https:\" == document.location.protocol) ? \"https://ssl.\" : \"http://www.\"); " + 
                "document.write(unescape(\"%3Cscript src='\" + gaJsHost + \"google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E\"));" + 
                "</script>" + 
                "<script type=\"text/javascript\">" + 
                "try { " + 
                " var pageTracker = _gat._getTracker(\"UA-11944310-1\"); " + 
                " pageTracker._trackPageview(); " +
                "} catch(err) {}</script>" + 
                "</head><body onLoad=\"javascript:pageTracker._setVar('collaboratorium_member');\">" + 
                "Google analytics tracking has been disabled" + 
                "</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
