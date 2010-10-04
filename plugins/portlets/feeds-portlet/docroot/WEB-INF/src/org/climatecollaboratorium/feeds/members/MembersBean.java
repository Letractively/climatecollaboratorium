package org.climatecollaboratorium.feeds.members;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.climatecollaboratorium.feeds.FeedsPreferences;


import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class MembersBean {
    private List<MemberWrapper> mostActiveUsers;
    private List<MemberWrapper> recentlyJoinedUsers;
    private List<MemberWrapper> recentlyActiveUsers;
    private int feedSize;
    
    private final static Log _log = LogFactoryUtil.getLog(MembersBean.class);
    
    public MembersBean(FeedsPreferences preferences) {
        feedSize = preferences.getFeedSize();
    }
    
    public List<MemberWrapper> getMostActiveMembers() throws SystemException {
        if (mostActiveUsers == null) {
            mostActiveUsers = new ArrayList<MemberWrapper>();
            
            /**
             * This is very uneffective, get all users and sort them by number of activities
             */
            mostActiveUsers = new ArrayList<MemberWrapper>();
            for (User user: UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE)) {
                mostActiveUsers.add(new MemberWrapper(user, SocialActivityLocalServiceUtil.getUserActivitiesCount(user.getUserId())));
            }
            
            Collections.sort(mostActiveUsers, new Comparator<MemberWrapper>() {

                @Override
                public int compare(MemberWrapper arg0, MemberWrapper arg1) {
                    return arg1.getActivitiesCount() - arg0.getActivitiesCount();
                }
                
            });
            mostActiveUsers = mostActiveUsers.subList(0, feedSize);
        }
        return mostActiveUsers;
    }
    
    public List<MemberWrapper> getRecentlyJoinedMembers() throws SystemException {
        if (recentlyJoinedUsers == null) {
            recentlyJoinedUsers = new ArrayList<MemberWrapper>();
            int usersCount = UserLocalServiceUtil.getUsersCount();
            
            for (User user: UserLocalServiceUtil.getUsers(usersCount-feedSize, usersCount)) {
                recentlyJoinedUsers.add(new MemberWrapper(user, -1));
            }
            
            Collections.reverse(recentlyJoinedUsers);
        }
        return recentlyJoinedUsers;
    }
    
    public List<MemberWrapper> getRecentlyActiveMembers() throws SystemException, PortalException {
        if (recentlyActiveUsers == null) {
            recentlyActiveUsers = new ArrayList<MemberWrapper>();
            Set<Long> usersAlreadyAdded = new HashSet<Long>();
            int activitiesCount = ActivityUtil.getAllActivitiesCount();
            int currentStart = 0;
            while (usersAlreadyAdded.size() < feedSize && currentStart < activitiesCount) {
                int currentEnd = currentStart + 10 * feedSize;
            // get latest 
                for (SocialActivity activity: ActivityUtil.retrieveAllActivities(currentStart, currentEnd)) {
                    if (usersAlreadyAdded.contains(activity.getUserId())) {
                        continue;
                    }
                    usersAlreadyAdded.add(activity.getUserId());
                    try {
                        recentlyActiveUsers.add(
                                new MemberWrapper(UserLocalServiceUtil.getUser(activity.getUserId()), activity));
                    }
                    catch (Exception e) {
                        _log.error("An error was thrown when retrieving a user. ", e);
                    }
                    if (recentlyActiveUsers.size() == feedSize) {
                        break;
                    }
                }
                currentStart = currentEnd; 
            }
        }
        return recentlyActiveUsers;
        
    }

}
