/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.facebook;

import com.ext.portlet.facebook.model.UserFacebookMapping;
import com.ext.portlet.facebook.service.UserFacebookMappingLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.events.ServicePreAction;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.LastPath;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.*;
import com.liferay.util.Encryptor;
import com.liferay.util.EncryptorException;
import com.liferay.util.PwdGenerator;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Oct 18, 2010
 * Time: 3:33:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookPreAction extends ServicePreAction {

    private static String COOKIE_DESC = "fbs_";
    private static String ACCESS_TOKEN = "access_token";
    private static boolean DEBUG = false;
    private static String FACEBOOK_RESULT = "facebookLoginResult";

    private static String RESULT_SUCCESS = "success";
    private static String RESULT_SYSTEM_ERR = "systemerror";
    private static String RESULT_COOKIE_ERR = "cookieerror";
    private static String RESULT_FB_ERR = "facebookerror";
    private static String RESULT_NO_EMAIL = "email";

    private static Log _log = LogFactoryUtil.getLog(FacebookPreAction.class);

    public void run(HttpServletRequest req, HttpServletResponse res)
            throws ActionException {


        ThemeDisplay themeDisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
        Map<String, Object> vmVariables = (Map<String, Object>) req.getAttribute(WebKeys.VM_VARIABLES);
        if (vmVariables == null) {
            vmVariables = new HashMap<String, Object>();
            req.setAttribute(WebKeys.VM_VARIABLES, vmVariables);
        }
        vmVariables.put(FACEBOOK_RESULT, RESULT_SUCCESS);

        String fbSignIn = req.getParameter("fbEvent");

        if (fbSignIn != null) {
            if ("true".equals(fbSignIn) && !themeDisplay.isSignedIn()) {
                String cookiename = COOKIE_DESC + PropsUtil.get("collaboratorium.facebook.appid");
                Cookie fbcookie = null;
                for (Cookie c : req.getCookies()) {
                    _log.info("Looking at cookie " + c.getName());
                    if (cookiename.equals(c.getName())) {
                        fbcookie = c;
                        break;
                    }
                }
                if (fbcookie != null) {
                    Map<String, String> splitCookie = parseCookie(fbcookie.getValue());
                    try {
                        Company company = PortalUtil.getCompany(req);
                        User u = identifyUserByFbId(PortalUtil.getCompany(req),splitCookie);
                        if (u == null) {
                            addUser(company,vmVariables,splitCookie);
                        } else {
                            signIn(req, res, u);
                        }
                    } catch (Exception e) {
                        vmVariables.put(FACEBOOK_RESULT, RESULT_SYSTEM_ERR);
                        return;
                    }

                } else {
                    vmVariables.put(FACEBOOK_RESULT, RESULT_COOKIE_ERR);
                    return;
                }

                redirect(req, res, themeDisplay);
            } else if ("false".equals(fbSignIn) && !themeDisplay.isSignedIn()) {
                _log.info("Could sign out");
            }
        }


        if (DEBUG && req.getParameter("debugLogin") != null) {
            String email = req.getParameter("debugLogin");
            try {
                signIn(req, res, UserLocalServiceUtil.getUserByEmailAddress(PortalUtil.getCompany(req).getCompanyId(), email));
            } catch (SystemException e) {
                throw new ActionException(e);
            } catch (PortalException e) {
                throw new ActionException(e);
            } catch (EncryptorException e) {
                throw new ActionException(e);
            }
            redirect(req, res, themeDisplay);


        }
    }

    public User identifyUserByFbId( Company company, Map<String,String> splitcookie) throws SystemException, PortalException {
        User u = UserFacebookMappingLocalServiceUtil.findUserByFacebookId((String)splitcookie.get("uid"));
        if (u != null) {
            String accesstoken =  (String)splitcookie.get(ACCESS_TOKEN);
            _log.info("Access token is "+accesstoken);
            JSONObject userinfo = JSONObject.fromObject(request("https://graph.facebook.com/me", new NameValuePair[]{new NameValuePair("access_token",accesstoken)}));
            if (userinfo != null) {
                updateUser(u, userinfo);
            } else {
                _log.warn("Could not retrieve info from facebook for user " + u.getUserId());
            }
        }
        return u;
    }

    public User addUser(Company company, Map<String, Object> vmVariables, Map<String,String> splitCookie) throws SystemException, PortalException {
         String accesstoken =  (String)splitCookie.get(ACCESS_TOKEN);
            _log.info("Access token is "+accesstoken);
        JSONObject userinfo = JSONObject.fromObject(request("https://graph.facebook.com/me", new NameValuePair[]{new NameValuePair("access_token", accesstoken)}));
        User user = null;
        if (userinfo != null) {

            String fbid = (String) userinfo.get("id");
            _log.info("Retrieved " + userinfo.toString());


            //no previous login association via facebook, resolve via email
            String email = (String) userinfo.get("email");
            if (email == null || email.trim().length() == 0) {
                vmVariables.put(FACEBOOK_RESULT, RESULT_NO_EMAIL);
                return null;
            }

            try {
                user = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(), email);
            } catch (NoSuchUserException e) {
                //don't worry about it
            }
            if (user != null) {
                createFacebookMapping(user, fbid);
            }


            if (user == null) {
                //user is null, need to register
                user = registerUser(userinfo, company);
                createFacebookMapping(user, fbid);
                return user;
            }
        } else {
            vmVariables.put(FACEBOOK_RESULT, RESULT_FB_ERR);

        }
        return user;
    }

    public void createFacebookMapping(User user, String fbid) throws SystemException {
        UserFacebookMapping mapping = UserFacebookMappingLocalServiceUtil.createUserFacebookMapping(user.getUserId());
        mapping.setFacebookId(fbid);
        UserFacebookMappingLocalServiceUtil.addUserFacebookMapping(mapping);
    }

    public User updateUser(User user, JSONObject userinfo) throws SystemException {
        String firstName = (String) userinfo.get("first_name");
        String lastName = (String) userinfo.get("last_name");
        String screenName = (String) userinfo.get("username");
        String emailAddress = (String) userinfo.get("email");
        String localeString = (String) userinfo.get("locale");
        Locale locale = localeString == null ? Locale.ENGLISH : LocaleUtil.fromLanguageId(localeString);
        if (!user.getFirstName().equals(firstName)) {
            user.setFirstName(firstName);
        }
        if (!user.getLastName().equals(lastName)) {
            user.setLastName(lastName);
        }
        if (screenName != null && !user.getScreenName().equals(screenName)) {
            user.setScreenName(screenName);
        }
        UserLocalServiceUtil.updateUser(user);
        return user;

    }

    public User registerUser(JSONObject userinfo, Company company) throws SystemException, PortalException {
        long creatorUserId = 0;
        boolean autoPassword = false;
        String password1 = PwdGenerator.getPassword();
        String password2 = password1;
        String firstName = (String) userinfo.get("first_name");
        String lastName = (String) userinfo.get("last_name");
        String screenName = (String) userinfo.get("username");
        String emailAddress = (String) userinfo.get("email");
        String localeString = (String) userinfo.get("locale");
        Locale locale = localeString == null ? Locale.ENGLISH : LocaleUtil.fromLanguageId(localeString);
        boolean autoScreenName = (screenName == null);
        String openId = StringPool.BLANK;
        String middleName = StringPool.BLANK;
        int prefixId = 0;
        int suffixId = 0;
        boolean male = !"female".equals(userinfo.get("gender"));


        int birthdayMonth = Calendar.JANUARY;
        int birthdayDay = 1;
        int birthdayYear = 1970;
        String jobTitle = StringPool.BLANK;
        long[] groupIds = null;
        long[] organizationIds = null;
        long[] roleIds = null;
        long[] userGroupIds = null;
        boolean sendEmail = false;
        ServiceContext serviceContext = new ServiceContext();

        return UserLocalServiceUtil.addUser(
                creatorUserId, company.getCompanyId(), autoPassword, password1, password2,
                autoScreenName, screenName, emailAddress, openId, locale, firstName,
                middleName, lastName, prefixId, suffixId, male, birthdayMonth,
                birthdayDay, birthdayYear, jobTitle, groupIds, organizationIds,
                roleIds, userGroupIds, sendEmail, serviceContext);
    }


    protected void redirect(HttpServletRequest req, HttpServletResponse res, ThemeDisplay themeDisplay) throws ActionException {
        String redirect = ParamUtil.getString(req, "redirect");
        try {
            if (Validator.isNotNull(redirect)) {

                res.sendRedirect(redirect);

            } else {
                res.sendRedirect(themeDisplay.getURLHome());
            }
        } catch (IOException e) {
            _log.error("Could not redirect", e);
            throw new ActionException(e);
        }
    }


    public void signIn(HttpServletRequest request, HttpServletResponse response, User user) throws SystemException, PortalException, EncryptorException {

        Company company = PortalUtil.getCompany(request);
        Long userId = user.getUserId();
        HttpSession session = request.getSession();
        if (PropsValues.SESSION_ENABLE_PHISHING_PROTECTION) {

            // Invalidate the previous session to prevent phishing

            Boolean httpsInitial = (Boolean) session.getAttribute(
                    WebKeys.HTTPS_INITIAL);

            LastPath lastPath = (LastPath) session.getAttribute(
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

            if (httpsInitial != null) {
                session.setAttribute(WebKeys.HTTPS_INITIAL, httpsInitial);
            }

            if (lastPath != null) {
                session.setAttribute(WebKeys.LAST_PATH, lastPath);
            }
        }

        // Set cookies

        String domain = com.liferay.portal.util.CookieKeys.getDomain(request);

        String userIdString = String.valueOf(userId);

        session.setAttribute("j_username", userIdString);
        session.setAttribute("j_password", user.getPassword());
        session.setAttribute("j_remoteuser", userIdString);

        //session.setAttribute(WebKeys.USER_PASSWORD, password);

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
                CookieKeys.PASSWORD, user.getPassword());

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


        companyIdCookie.setMaxAge(-1);
        idCookie.setMaxAge(-1);
        passwordCookie.setMaxAge(-1);
        rememberMeCookie.setMaxAge(0);


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
            Boolean httpsInitial = (Boolean) session.getAttribute(
                    WebKeys.HTTPS_INITIAL);

            if ((httpsInitial == null) || !httpsInitial.booleanValue()) {
                secure = false;
            }
        }

        CookieKeys.addCookie(request, response, companyIdCookie, secure);
        CookieKeys.addCookie(request, response, idCookie, secure);
        CookieKeys.addCookie(request, response, passwordCookie, secure);
        CookieKeys.addCookie(request, response, rememberMeCookie, secure);
        CookieKeys.addCookie(request, response, loginCookie, secure);
        CookieKeys.addCookie(request, response, screenNameCookie, secure);
    }


    private static Map<String, String> parseCookie(String cookieval) {
        _log.info("Parse cookie " + cookieval);
        Map<String, String> result = new HashMap<String, String>();

        for (String portion : cookieval.split("&")) {
            String[] tmp = portion.split("=");
            result.put(tmp[0], tmp[1]);
        }
        _log.info("Split cookie " + result);
        return result;
    }


    private static String request(String url, NameValuePair[] params) throws ActionException {
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(url);
        get.setQueryString(params);
        _log.info("Sending request to facebook " + get);
        get.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        try {
            client.executeMethod(get);
            String result = get.getResponseBodyAsString();
            _log.info("Got response " + result);
            return result;

        } catch (HttpException e) {
            throw new ActionException("Error connecting to server", e);
        } catch (IOException e) {
            throw new ActionException("Error connecting to server", e);
        } finally {
            get.releaseConnection();
        }

    }


    public static void main(String[] args) {
        String json = "{" +
                "   \"name\": \"Facebook Platform\"," +
                "   \"type\": \"page\"," +
                "   \"website\": \"http://developers.facebook.com\"," +
                "   \"username\": \"platform\"," +
                "   \"founded\": \"May 2007\"," +
                "   \"company_overview\": \"Facebook Platform enables anyone to build...\"," +
                "   \"mission\": \"To make the web more open and social.\"," +
                "   \"products\": \"Facebook Application Programming Interface (API)...\"," +
                "   \"fan_count\": 449921," +
                "   \"id\": 19292868552," +
                "   \"category\": \"Technology\"" +
                "}";
        JSONObject object = JSONObject.fromObject("{   \"foo\":\"bar\"}");
        System.err.println(object.get("foo"));
    }

}
