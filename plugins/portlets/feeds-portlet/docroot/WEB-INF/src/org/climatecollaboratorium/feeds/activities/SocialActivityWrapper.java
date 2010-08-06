package org.climatecollaboratorium.feeds.activities;

import java.util.Date;
import java.util.TimeZone;

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

    public SocialActivityWrapper(SocialActivity activity, int daysBetween, boolean indicateNewDate) {
        Helper.getThemeDisplay();
        this.activity = activity;
        
        activityFeedEntry = SocialActivityInterpreterLocalServiceUtil.interpret(activity,
                Helper.getThemeDisplay());
        this.daysBetween = daysBetween;
        this.indicateNewDate = indicateNewDate;
        System.out.println(indicateNewDate);
    }
    
    public String getBody() {
        return activityFeedEntry != null ? activityFeedEntry.getBody() : null;
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

}
