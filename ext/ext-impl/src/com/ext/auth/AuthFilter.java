package com.ext.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ext.auth.service.AuthMappingLocalServiceUtil;
import com.ext.auth.service.impl.AuthMappingLocalServiceImpl;
import com.liferay.portal.CookieNotSupportedException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.LastPath;
import com.liferay.portal.util.CookieKeys;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.util.Encryptor;
import com.liferay.util.EncryptorException;

public class AuthFilter implements Filter {
    
    public final static String AUTH_SIGNIN_PARAM = "auth_signIn";
    public final static String USER_TO_LOG_IN = "auth_user_to_log_in";


    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
            ServletException {
        Object signinParam = arg0.getParameter(AUTH_SIGNIN_PARAM);
        if (signinParam != null) {
            HttpServletRequest request = (HttpServletRequest) arg0;
            HttpServletResponse response = (HttpServletResponse) arg1;
            HttpSession session = request.getSession();
            
            if (session != null) {
                User userToLogIn = (User) session.getAttribute(USER_TO_LOG_IN);
                //login(userToLogIn, request, response);
            }
            
        }

        arg2.doFilter(arg0, arg1);
        
    }
        
        
    private void login(User user, HttpServletRequest request, HttpServletResponse response) throws PortalException, SystemException, EncryptorException {
                    
                    Cookie c = new Cookie("TEST_WE_CIAS_TKO", "wartosc, wartosc,wartosc");
                    c.setDomain("localhost");
                    
                    CookieKeys.validateSupportCookie(request);

                    HttpSession session = request.getSession();
                    System.out.println("Sessja jaka dostaje: " + session);

                    long userId = user.getUserId();

                    int authResult = Authenticator.FAILURE;

                    Company company = PortalUtil.getCompany(request);

                    Map<String, String[]> headerMap = new HashMap<String, String[]>();

                    Enumeration<String> enu1 = request.getHeaderNames();

                    while (enu1.hasMoreElements()) {
                        String name = enu1.nextElement();

                        Enumeration<String> enu2 = request.getHeaders(name);

                        List<String> headers = new ArrayList<String>();

                        while (enu2.hasMoreElements()) {
                            String value = enu2.nextElement();

                            headers.add(value);
                        }

                        headerMap.put(name, headers.toArray(new String[headers.size()]));
                    }

                    Map<String, String[]> parameterMap = request.getParameterMap();
                    
                    if (PropsValues.SESSION_ENABLE_PHISHING_PROTECTION) {

                        // Invalidate the previous session to prevent phishing

                        Boolean httpsInitial = (Boolean)session.getAttribute(
                            WebKeys.HTTPS_INITIAL);

                        LastPath lastPath = (LastPath)session.getAttribute(
                            WebKeys.LAST_PATH);

                        try {
                            session.invalidate();
                        }
                        catch (IllegalStateException ise) {

                            // This only happens in Geronimo

                            if (_log.isWarnEnabled()) {
                                _log.warn(ise.getMessage());
                            }
                        }

                        session = request.getSession(true);
                        System.out.println("new session: " + session);

                        if (httpsInitial != null) {
                            session.setAttribute(WebKeys.HTTPS_INITIAL, httpsInitial);
                        }

                        if (lastPath != null) {
                            session.setAttribute(WebKeys.LAST_PATH, lastPath);
                        }
                    }

                    // Set cookies

                    String domain = CookieKeys.getDomain(request);

                    

                    String userIdString = String.valueOf(userId);

                    session.setAttribute("j_username", userIdString);
                    session.setAttribute("j_password", user.getPassword());
                    session.setAttribute("j_remoteuser", userIdString);

                    session.setAttribute(WebKeys.USER_PASSWORD, user.getPassword());

                    Cookie companyIdCookie = new Cookie(
                        CookieKeys.COMPANY_ID, String.valueOf(company.getCompanyId()));

                    if (Validator.isNotNull(domain)) {
                        companyIdCookie.setDomain(domain);
                    }

                    companyIdCookie.setPath(StringPool.SLASH);

                    Cookie idCookie = new Cookie(
                        CookieKeys.ID,
                        UserLocalServiceUtil.encryptUserId(userIdString));

                    if (Validator.isNotNull(domain)) {
                        idCookie.setDomain(domain);
                    }

                    idCookie.setPath(StringPool.SLASH);

                    Cookie passwordCookie = new Cookie(
                        CookieKeys.PASSWORD,
                        Encryptor.encrypt(company.getKeyObj(), user.getPassword()));

                    if (Validator.isNotNull(domain)) {
                        passwordCookie.setDomain(domain);
                    }

                    passwordCookie.setPath(StringPool.SLASH);

                    Cookie rememberMeCookie = new Cookie(
                        CookieKeys.REMEMBER_ME, Boolean.TRUE.toString());

                    if (Validator.isNotNull(domain)) {
                        rememberMeCookie.setDomain(domain);
                    }

                    rememberMeCookie.setPath(StringPool.SLASH);

                    int loginMaxAge = PropsValues.COMPANY_SECURITY_AUTO_LOGIN_MAX_AGE;
                    
                    boolean rememberMe = true;

                    if (PropsValues.SESSION_DISABLED) {
                        rememberMe = true;
                    }

                    if (rememberMe) {
                        companyIdCookie.setMaxAge(loginMaxAge);
                        idCookie.setMaxAge(loginMaxAge);
                        passwordCookie.setMaxAge(loginMaxAge);
                        rememberMeCookie.setMaxAge(loginMaxAge);
                    }
                    else {

                        // This was explicitly changed from 0 to -1 so that the cookie
                        // lasts as long as the browser. This allows an external servlet
                        // wrapped in AutoLoginFilter to work throughout the client
                        // connection. The cookies ARE removed on an actual logout, so
                        // there is no security issue. See LEP-4678 and LEP-5177.

                        companyIdCookie.setMaxAge(-1);
                        idCookie.setMaxAge(-1);
                        passwordCookie.setMaxAge(-1);
                        rememberMeCookie.setMaxAge(0);
                    }

                    Cookie loginCookie = new Cookie(CookieKeys.LOGIN, user.getScreenName());

                    if (Validator.isNotNull(domain)) {
                        loginCookie.setDomain(domain);
                    }

                    loginCookie.setMaxAge(loginMaxAge);
                    loginCookie.setPath(StringPool.SLASH);

                    Cookie screenNameCookie = new Cookie(
                        CookieKeys.SCREEN_NAME,
                        Encryptor.encrypt(company.getKeyObj(), user.getScreenName()));

                    if (Validator.isNotNull(domain)) {
                        screenNameCookie.setDomain(domain);
                    }

                    screenNameCookie.setMaxAge(loginMaxAge);
                    screenNameCookie.setPath(StringPool.SLASH);

                    boolean secure = request.isSecure();

                    if (secure) {
                        Boolean httpsInitial = (Boolean)session.getAttribute(
                            WebKeys.HTTPS_INITIAL);

                        if ((httpsInitial == null) || !httpsInitial.booleanValue()) {
                            secure = false;
                        }
                    }

                    AuthCookiesUtils.addCookie(companyIdCookie);
                    AuthCookiesUtils.addCookie(idCookie);
                    AuthCookiesUtils.addCookie(passwordCookie);
                    AuthCookiesUtils.addCookie(rememberMeCookie);
                    AuthCookiesUtils.addCookie(loginCookie);
                    AuthCookiesUtils.addCookie(screenNameCookie);
                    
                }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
    

    private static Log _log = LogFactoryUtil.getLog(AuthFilter.class);

}