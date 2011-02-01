package org.climatecollaboratorium.userprofile;

import java.util.Collection;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.Activity.CompositeSubscriptionHolder;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;

public class UserSubscribtionsBean {
    private User user;
    
    public UserSubscribtionsBean(User user) throws SystemException {
        this.user = user;
        Collection<CompositeSubscriptionHolder> subscriptions = 
                ActivityUtil.getCompositeSubscriptions(user.getUserId()).values();
        
        for (CompositeSubscriptionHolder subs: subscriptions) {
            System.out.println("Entityid: " + subs.getEntityId());
            System.out.println("Portletid: " + subs.getPortletId());
            System.out.println("activitytype: " + subs.getSubscriptions().get(0).getActivitytype());
        }
        
        
        
    }

}
