package org.climatecollaboratorium.feeds.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.climatecollaboratorium.feeds.FeedsPreferences;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portlet.social.model.SocialActivity;

public class ActivitiesBean {
    
    private int feedSize = 20;
    public ActivitiesBean(FeedsPreferences preferences) {
        feedSize = preferences.getFeedSize();
    }

    public List<SocialActivityWrapper> getActivities() throws SystemException {
        List<SocialActivityWrapper> ret = new ArrayList<SocialActivityWrapper>();
        int lastDaysBetween = -1;
        Date now = new Date();
        for (SocialActivity activity: ActivityUtil.retrieveAllActivities(0, feedSize)) {
            int curDaysBetween = DateUtil.getDaysBetween(activity.getCreateDate(), now, TimeZone.getDefault());
            ret.add(new SocialActivityWrapper(activity, curDaysBetween, lastDaysBetween < curDaysBetween));
            lastDaysBetween = curDaysBetween;
        }
        return ret;
    }
}
