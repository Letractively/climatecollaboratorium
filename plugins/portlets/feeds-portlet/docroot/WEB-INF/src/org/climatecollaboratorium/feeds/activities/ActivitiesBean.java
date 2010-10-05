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
import java.util.TimeZone;

public class ActivitiesBean {

    /**
     * Max query to handle the bug with no activity body - sufficiently high number that we're very likely to get enough
     * non-null entres to meet the feedSize param
     */
    private int MAX_QUERY = 200;
    private int feedSize = 20;
    private boolean showAdmin = true;

    public ActivitiesBean(FeedsPreferences preferences) {
        feedSize = preferences.getFeedSize();
        showAdmin = !preferences.getRemoveAdmin();
    }

    public List<SocialActivityWrapper> getActivities() throws SystemException, PortalException {
        List<SocialActivityWrapper> ret = new ArrayList<SocialActivityWrapper>();
        int lastDaysBetween = -1;
        Date now = new Date();
        int count = feedSize;
        for (SocialActivity activity : ActivityUtil.retrieveAllActivities(0, MAX_QUERY)) {
            if (SocialActivityWrapper.isEmpty(activity) ||
                    (!showAdmin && Helper.isUserAdmin(activity.getUserId()))) {
                continue;
            }

            int curDaysBetween = DateUtil.getDaysBetween(activity.getCreateDate(), now, TimeZone.getDefault());
            ret.add(new SocialActivityWrapper(activity, curDaysBetween, lastDaysBetween < curDaysBetween));
            lastDaysBetween = curDaysBetween;
            if (--count == 0) break;
        }
        return ret;
    }
}
