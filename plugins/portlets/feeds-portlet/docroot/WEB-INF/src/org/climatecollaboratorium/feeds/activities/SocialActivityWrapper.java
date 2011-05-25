package org.climatecollaboratorium.feeds.activities;

import java.util.Date;
import java.util.TimeZone;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.climatecollaboratorium.feeds.Helper;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;

public class SocialActivityWrapper {
    private SocialActivity activity;
    private SocialActivityFeedEntry activityFeedEntry;
    private int daysBetween;
    private boolean indicateNewDate;
    private final static Log _log = LogFactoryUtil.getLog(SocialActivityWrapper.class);
    private long daysAgo = 0;
    private String body;


    public SocialActivityWrapper(SocialActivity activity, int daysBetween, boolean indicateNewDate) {
        Helper.getThemeDisplay();
        this.activity = activity;
        try {
            activityFeedEntry = SocialActivityInterpreterLocalServiceUtil.interpret(activity,
                Helper.getThemeDisplay());
        } catch(Exception e) {
            //ignore
        }
        this.daysBetween = daysBetween;
        this.indicateNewDate = indicateNewDate;

        final int milisecondsInDay = 1000 * 60 * 60 * 24;
        long createDay = activity.getCreateDate().getTime() / milisecondsInDay;
        long daysNow = new Date().getTime() / milisecondsInDay;
        daysAgo = daysNow - createDay;
        body = getBodyFromFeedEntry(activityFeedEntry);
    }
    
    private static String getBodyFromFeedEntry(SocialActivityFeedEntry entry) {
        return entry != null ? (entry.getBody().trim().equals("") ? entry.getTitle() : entry.getBody()) : null; 
    }
    public String getBody() {
        return body;
    }

    public boolean isToday() {
        return daysBetween == 0;
    }
    
    public boolean isYesterday() {
        return daysBetween == 1;
    }
    
    public Date getCreateDate() {
        return activity.getCreateDate();
    }
    
    public boolean getIndicateNewDate() {
        return indicateNewDate;
    }

    public Boolean getIsEmpty() {
       return isEmpty(activityFeedEntry); 
    }
    
    public static Boolean isEmpty(SocialActivityFeedEntry entry) {
        String body = getBodyFromFeedEntry(entry);
        return body == null || body.trim().length() == 0;
    }

    public static Boolean isEmpty(SocialActivity activity) {
        try {

            UserLocalServiceUtil.getUser(activity.getUserId());
            SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity,
            Helper.getThemeDisplay());
            return isEmpty(entry);
        } catch (Throwable e) {
            _log.error("Some error interpreting activity: "+e.getMessage());
            return false;
        }
    }

    public void setDaysAgo(long daysAgo) {
        this.daysAgo = daysAgo;
    }

    public long getDaysAgo() {
        return daysAgo;
    }
}
