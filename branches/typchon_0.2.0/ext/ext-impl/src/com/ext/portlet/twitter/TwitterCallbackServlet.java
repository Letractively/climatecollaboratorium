package com.ext.portlet.twitter;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.ext.portlet.twitter.model.UserTwitterMapping;
import com.ext.portlet.twitter.service.UserTwitterMappingLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.LastPath;
import com.liferay.portal.util.CookieKeys;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.login.util.LoginUtil;
import com.liferay.util.Encryptor;
import com.liferay.util.EncryptorException;
import com.liferay.util.PwdGenerator;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterCallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1657390011452788111L;
    
    private static final Log _log = LogFactoryUtil.getLog(TwitterCallbackServlet.class);
    
    private static String responseOK = "<html><head><script type='text/javascript'>window.opener.twitterSignIn(true)</script></head></html>";
    private static String responseERROR = "<html><head><script type='text/javascript'>window.opener.twitterSignIn(false)</script></head></html>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        
        
        String verifier = request.getParameter("oauth_verifier");
        try {
            AccessToken at = twitter.getOAuthAccessToken(requestToken, verifier);
            
            User twitterUser = twitter.verifyCredentials();
            com.liferay.portal.model.User lrUser = null;
           
            
            UserTwitterMapping mapping = null;
            try {
                mapping = UserTwitterMappingLocalServiceUtil.getUserTwitterMapping(twitterUser.getId());
            }
            catch(com.ext.portlet.twitter.NoSuchUserTwitterMappingException e) {
                // ignore
            }
            if (mapping == null) {
                Company company = PortalUtil.getCompany(request);
                lrUser = registerUser(twitterUser, company);
                mapping = UserTwitterMappingLocalServiceUtil.createUserTwitterMapping(twitterUser.getId(), lrUser.getUserId());
            }
            else {
                lrUser = UserLocalServiceUtil.getUser(mapping.getUserId());
            }
            
            // log user in
            signIn(request, response, lrUser);
            at.getUserId();
            
            response.getWriter().write(responseOK);
            response.getWriter().close();
            return;
            
        } catch (TwitterException e) {
            throw new ServletException(e);
        } catch (PortalException e) {
            _log.error(e);
        } catch (SystemException e) {
            _log.error(e);
        } catch (Exception e) {
            _log.error(e);
        }

        response.getWriter().write(responseERROR);
        response.getWriter().close();
        return;
    }
    
    public com.liferay.portal.model.User registerUser(User userinfo, Company company) throws SystemException, PortalException {
        
        String screenName = userinfo.getScreenName();
        boolean keepLooking = true;
        int counter = 1;
        while (keepLooking) {
            try {
                com.liferay.portal.model.User u = UserLocalServiceUtil.getUserByScreenName(company.getCompanyId(), screenName);
                if (u == null) {
                    break;
                }
                else {
                    screenName = userinfo.getScreenName() + counter;
                    counter++;
                }
            }
            catch (NoSuchUserException e) {
                keepLooking = false;
            }
        }
        long creatorUserId = 0;
        boolean autoPassword = false;
        String password1 = PwdGenerator.getPassword();
        String password2 = password1;
        String firstName = userinfo.getName();
        String lastName = null;
        String emailAddress = "empty@unknown-email.com";
        String localeString = (String) userinfo.getLang();
        Locale locale = localeString == null ? Locale.ENGLISH : LocaleUtil.fromLanguageId(localeString);
        boolean autoScreenName = (screenName == null);
        String openId = StringPool.BLANK;
        String middleName = StringPool.BLANK;
        int prefixId = 0;
        int suffixId = 0;
        boolean male = true;//!"female".equals(userinfo.get("gender"));


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
    
    public void signIn(HttpServletRequest request, HttpServletResponse response, com.liferay.portal.model.User user) throws SystemException, PortalException, EncryptorException {

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
}
