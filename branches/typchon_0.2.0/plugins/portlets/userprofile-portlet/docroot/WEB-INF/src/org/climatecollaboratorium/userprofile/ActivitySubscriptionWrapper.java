package org.climatecollaboratorium.userprofile;

import java.util.Date;

import com.ext.portlet.Activity.SubscriptionType;
import com.ext.portlet.Activity.model.ActivitySubscription;

public class ActivitySubscriptionWrapper {
    private ActivitySubscription subscription;
    private boolean selected;
    
    public ActivitySubscriptionWrapper(ActivitySubscription subscription) {
        this.subscription = subscription;
    }
    
    public String getName() {
        return subscription.getName();
    }
    
    public Date getUpdated() {
        return subscription.getModifiedDate();
    }
    
    public SubscriptionType getType() {
        return subscription.getSubscriptionType();
    }
    
    public boolean getSelected() {
        return selected;
    }
    
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ActivitySubscription getWrapped() {
        return subscription;
    }
}
