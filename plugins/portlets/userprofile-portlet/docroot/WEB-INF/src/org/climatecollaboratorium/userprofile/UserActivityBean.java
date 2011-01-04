package org.climatecollaboratorium.userprofile;

import java.util.Date;

import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;

public class UserActivityBean {
    
    private SocialActivity activity;
    private SocialActivityFeedEntry activityFeedEntry;
    private String body;
    
    public UserActivityBean(SocialActivity activity) {
        this.activity = activity;

        activityFeedEntry = SocialActivityInterpreterLocalServiceUtil.interpret(activity,
                Helper.getThemeDisplay());
        
        if (activityFeedEntry != null) {
            body = activityFeedEntry.getBody();
            body = body != null && body.trim().equals("") ? activityFeedEntry.getTitle() : body;
        }
    }
    
    public Date getCreatedDate() {
        return activity.getCreateDate();
    }
    
    public String getBody() {
        return body;
    }

}
