package org.climatecollaboratorium.feeds.activities;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import org.climatecollaboratorium.feeds.FeedsPreferences;
import org.climatecollaboratorium.feeds.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class ActivitiesBean {

    /**
     * Max query to handle the bug with no activity body - sufficiently high number that we're very likely to get enough
     * non-null entres to meet the feedSize param
     */
    private int MAX_QUERY = 500;
    private int feedSize = 20;
    private boolean showAdmin = true;
    private String feedStyle = "FULL";
    private List<SocialActivityWrapper> activities;

    public ActivitiesBean(FeedsPreferences preferences) {
        feedSize = preferences.getFeedSize();
        showAdmin = !preferences.getRemoveAdmin();
        feedStyle = preferences.getFeedStyle();
        if (feedStyle.equals("FULL")) {
            MAX_QUERY = 100;
            feedSize = MAX_QUERY/2;
        }
    }

    public List<SocialActivityWrapper> getActivities() throws SystemException, PortalException {
        if (activities == null) {
            activities = new ArrayList<SocialActivityWrapper>();
            int lastDaysBetween = -1;
            Date now = new Date();
            int count = feedSize;
            int i = 0;
            Map<String, String> parameters = Helper.getUrlParametersMap();
            Long userId = null; 
            if (parameters.containsKey("userId")) {
                try {
                    userId = Long.parseLong(parameters.get("userId"));
                }
                catch (Throwable t) {
                    // ignore
                }
            }
            
            for (SocialActivity activity : userId == null ? ActivityUtil.retrieveAllActivities(0, MAX_QUERY) : ActivityUtil.retrieveActivities(userId, 0, MAX_QUERY)) {
                if (SocialActivityWrapper.isEmpty(activity) || (!showAdmin && Helper.isUserAdmin(activity.getUserId()))) {
                    continue;
                }

                int curDaysBetween = DateUtil.getDaysBetween(activity.getCreateDate(), now, TimeZone.getDefault());
                activities.add(new SocialActivityWrapper(activity, curDaysBetween, lastDaysBetween < curDaysBetween, i % 2 == 1));
                lastDaysBetween = curDaysBetween;
                i++;
                if (--count == 0)
                    break;
            }
        }
        return activities;
    }

    public void setFeedStyle(String feedStyle) {
        this.feedStyle = feedStyle;
    }

    public String getFeedStyle() {
        return feedStyle;
    }
}
