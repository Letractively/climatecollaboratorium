package org.climatecollaboratorium.feeds.members;

import java.util.*;

import com.liferay.portal.kernel.util.DateUtil;
import org.climatecollaboratorium.feeds.FeedsPreferences;


import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import org.climatecollaboratorium.feeds.Helper;
import org.climatecollaboratorium.feeds.activities.SocialActivityWrapper;

public class MembersBean {
    private List<MemberWrapper> mostActiveUsers;
    private List<MemberWrapper> recentlyJoinedUsers;
    private ArrayList<MemberWrapper> recentlyActiveUsers;
    private int feedSize;
    private boolean showAdmin = false;
    
    public MembersBean(FeedsPreferences preferences) {
        feedSize = preferences.getFeedSize();
        showAdmin = !preferences.getRemoveAdmin();
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
    
    public ArrayList<MemberWrapper> getRecentlyActiveMembers() throws SystemException, PortalException {
        if (recentlyActiveUsers == null) {
            recentlyActiveUsers = new ArrayList<MemberWrapper>();
            Set<Long> usersAlreadyAdded = new HashSet<Long>();
            int activitiesCount = ActivityUtil.getAllActivitiesCount();
            int currentStart = 0;

            int lastDaysBetween = -1;
            Date now = new Date();

            while (usersAlreadyAdded.size() < feedSize && currentStart < activitiesCount) {
                int currentEnd = currentStart + 10 * feedSize;
            // get latest 
                for (SocialActivity activity: ActivityUtil.retrieveAllActivities(currentStart, currentEnd)) {
                    if (usersAlreadyAdded.contains(activity.getUserId()) || (!showAdmin && Helper.isUserAdmin(activity.getUserId())) || SocialActivityWrapper.isEmpty(activity) ) {
                        continue;
                    }
                    usersAlreadyAdded.add(activity.getUserId());

                    int curDaysBetween = DateUtil.getDaysBetween(activity.getCreateDate(), now, TimeZone.getDefault());
                    recentlyActiveUsers.add(new MemberWrapper(activity));
                    lastDaysBetween = curDaysBetween;

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