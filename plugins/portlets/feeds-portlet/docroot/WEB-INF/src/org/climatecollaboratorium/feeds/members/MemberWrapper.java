package org.climatecollaboratorium.feeds.members;

import org.climatecollaboratorium.feeds.Helper;
import org.climatecollaboratorium.feeds.activities.SocialActivityWrapper;

import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;

public class MemberWrapper {
    private User user;
    private int activitiesCount;
    private SocialActivity activity;
    private String lastActivityBody;
    
    public MemberWrapper(User user, int activitiesCount) {
        this.user = user;
        this.activitiesCount = activitiesCount;
    }
    
    
    public MemberWrapper(User user, SocialActivity activity) {
        this.user = user;
        this.activity = activity;
        if (activity != null) {
            SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, Helper.getThemeDisplay());
            lastActivityBody = entry != null ? entry.getBody() : null;
        }
    }


    public int getActivitiesCount() {
        return activitiesCount;
    }
    
    public String getScreenName() {
        return user.getScreenName();
    }
    
    public String getLastActivity() {
        return "aaa";
    }
    
    public Long getUserId() {
        return user.getUserId();
    }

    public String getLastActivityBody() {
        return lastActivityBody;
    }
}
