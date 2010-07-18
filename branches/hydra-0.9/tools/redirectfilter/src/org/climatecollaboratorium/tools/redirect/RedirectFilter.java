package org.climatecollaboratorium.tools.redirect;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectFilter implements Filter {
    private static final String TARGET_HOST_PARAM = "redirectTargetHost";
    private String targetHost;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
            ServletException {
        
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        
        response.setHeader("Location", targetHost + request.getServletPath());
        response.setStatus(301);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        targetHost = config.getInitParameter(TARGET_HOST_PARAM);
        if (targetHost == null || targetHost.trim().equals("")) {
            throw new ServletException("Filter parameter \"redirectTargetHost\" can't be null or empty");
        }
        
    }

}
