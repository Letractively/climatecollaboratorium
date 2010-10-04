package org.climatecollaboratorium.feeds.members;

import java.util.Date;

import org.climatecollaboratorium.feeds.Helper;

import com.liferay.portal.model.User;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;

public class MemberWrapper {
    private User user;
    private int activitiesCount;
    private SocialActivity activity;
    private String lastActivityBody;
    private Date lastActivityDate;
    
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
            if (lastActivityBody == null || lastActivityBody.trim().length() == 0) {
                lastActivityBody = entry != null ? entry.getTitle() : null;
            }
        }
        lastActivityDate = activity.getCreateDate();
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

    public Date getLastActivityDate() {
        return lastActivityDate;
    }
}
