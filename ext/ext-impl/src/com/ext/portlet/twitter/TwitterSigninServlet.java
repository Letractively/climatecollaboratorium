package com.ext.portlet.twitter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class TwitterSigninServlet extends HttpServlet {
    private static final long serialVersionUID = -6205814293093350242L;
    private static final Log _log = LogFactoryUtil.getLog(TwitterSigninServlet.class);
    private TwitterFactory tf = new TwitterFactory();

    private static final String DEFAULT_REDIRECT = "/web/guest/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Twitter twitter = tf.getInstance();
        request.getSession().setAttribute("twitter", twitter);
        try {
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/callback");
            
            RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
            if (requestToken == null) {
                requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
            }
            request.getSession().setAttribute("requestToken", requestToken);
            String redirectURL = requestToken.getAuthenticationURL();
            
            if (redirectURL == null || redirectURL.trim().equals("")) {
                redirectURL = DEFAULT_REDIRECT;
            }

            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", redirectURL);

        } catch (TwitterException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void init() throws ServletException {
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(Boolean.parseBoolean(PropsUtil.get("twitter.debug")))
                .setOAuthConsumerKey(PropsUtil.get("twitter.oauth.consumerKey"))
                .setOAuthConsumerSecret(PropsUtil.get("twitter.oauth.consumerSecret"));
            
            //    .setOAuthAccessToken(PropsUtil.get("twitter.oauth.accessToken"))
            //    .setOAuthAccessTokenSecret(PropsUtil.get("twitter.oauth.accessTokenSecret"));
            tf = new TwitterFactory(cb.build());
        } catch (Exception e) {
            _log.error(e);
            
        }
    }
    
}
