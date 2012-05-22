package com.ext.auth;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.util.CookieKeys;

public class AuthCookiesUtils {
    
    public static final String COOKIES_SESSION_PARAM = "com.ext.auth.AuthCookiesUtils.cookiesToSet";
    public static final int VERSION = 0;
    private static ThreadLocal<List<Cookie>> threadCookies = new ThreadLocal<List<Cookie>>();
    
    public static void preProcessCookies() {
        threadCookies.set(null);
    }
    
    public static void addCookie(Cookie c) {
        
        c.setVersion(VERSION);
        
        List<Cookie> cookies = threadCookies.get();
        if (cookies == null) {
            cookies = new ArrayList<Cookie>();
            threadCookies.set(cookies);
        }
        cookies.add(c);
    }
    
    public static void postProcessCookies(HttpServletRequest request, HttpServletResponse response) {
        List<Cookie> cookies = threadCookies.get();
        if (cookies == null) {
            return;
        }
        
        for (Cookie c: cookies) {
            response.addCookie(c);
            CookieKeys.addCookie(request, response, c);
        }

        CookieKeys.addCookie(request, response, new Cookie("KRYSTYNA_CHCE_W_DZIOB", "bo tak"));
        
        
        threadCookies.set(null);
        
    }

}
