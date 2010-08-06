package org.climatecollaboratorium.feeds;

import org.climatecollaboratorium.feeds.activities.ActivitiesBean;
import org.climatecollaboratorium.feeds.members.MembersBean;


public class FeedsBean {
    private ActivitiesBean activitiesBean;
    private MembersBean membersBean;
    private FeedsPreferences preferences;

    public FeedsBean() {
    }
    
    public ActivitiesBean getActivitiesBean() {
        if (activitiesBean == null) {
            activitiesBean = new ActivitiesBean(preferences);
        }
        return activitiesBean;
    }
    
    public void setFeedsPreferences(FeedsPreferences preferences) {
        this.preferences = preferences;
    }
    
    public MembersBean getMembersBean() {
        if (membersBean == null) {
            membersBean = new MembersBean(preferences);
        }
        return membersBean;
    }
}
