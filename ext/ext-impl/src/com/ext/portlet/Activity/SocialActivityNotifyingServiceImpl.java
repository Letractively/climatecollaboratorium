package com.ext.portlet.Activity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.messaging.MessageUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.theme.ThemeDisplayFactory;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.impl.SocialActivityLocalServiceImpl;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

public class SocialActivityNotifyingServiceImpl extends SocialActivityLocalServiceImpl {
    
    private final static Log _log = LogFactoryUtil.getLog(SocialActivityNotifyingServiceImpl.class);

    @Override
    public SocialActivity addActivity(long userId, long groupId, String className, long classPK, int type,
            String extraData, long receiverUserId) throws PortalException, SystemException {
        SocialActivity activity =  super.addActivity(userId, groupId, className, classPK, type, extraData, receiverUserId);
        return activity;
    }

    @Override
    public SocialActivity addActivity(long userId, long groupId, Date createDate, String className, long classPK,
            int type, String extraData, long receiverUserId) throws PortalException, SystemException {
        SocialActivity activity =  super.addActivity(userId, groupId, createDate, className, classPK, type, extraData, receiverUserId);
        sendNotifications(activity);
        return activity;
    }

    @Override
    public SocialActivity addUniqueActivity(long userId, long groupId, String className, long classPK, int type,
            String extraData, long receiverUserId) throws PortalException, SystemException {
        SocialActivity activity =  super.addUniqueActivity(userId, groupId, className, classPK, type, extraData, receiverUserId);
        return activity;
    }

    @Override
    public SocialActivity addUniqueActivity(long userId, long groupId, Date createDate, String className, long classPK,
            int type, String extraData, long receiverUserId) throws PortalException, SystemException {
        
        SocialActivity activity = super.addUniqueActivity(userId, groupId, createDate, className, classPK, type, extraData, receiverUserId);
        sendNotifications(activity);
        return activity;
    }
    
    private final String MESSAGE_FOOTER_TEMPLATE = "<br /><br />\n---------------------------------------------------------------------------------------<br />\n" +
            "To configure your notification preferences, visit your <a href=\"USER_PROFILE_LINK\">profile</a> page";
    
    private final String USER_PROFILE_LINK_PLACEHOLDER = "USER_PROFILE_LINK";
    
    private final String USER_PROFILE_LINK_TEMPLATE = "http://climatecolab.org/web/guest/member/-/member/userId/USER_ID";
    
    private final String USER_ID_PLACEHOLDER = "USER_ID";
    
    private void sendNotifications(SocialActivity activity) throws SystemException, PortalException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ActivitySubscription.class);
        Criterion criterionClassNameId = RestrictionsFactoryUtil.eq("classNameId" , activity.getClassNameId());
        Criterion criterionClassPK = RestrictionsFactoryUtil.eq("classPK" , activity.getClassPK());
        query.add(RestrictionsFactoryUtil.and(criterionClassNameId, criterionClassPK));
        ThemeDisplay td = new ThemeDisplayFactory().create();
        
        ActivitySubscriptionLocalServiceUtil.getInterpreterForClass(activity.getClassNameId());
            

        SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, td);
        
        try {
            InternetAddress fromEmail = new InternetAddress("no-reply@climatecolab.org");
            String subject = entry.getTitle();
            String messageTemplate = entry.getBody() + MESSAGE_FOOTER_TEMPLATE; 
            messageTemplate = messageTemplate.replaceAll("\"/web/guest", "\"http://climatecolab.org/web/guest")
                    .replaceAll("'/web/guest", "'http://climatecolab.org/web/guest")
                    .replaceAll("\n" ,"<br />");
            Set<User> receipients = new HashSet<User>();
        
            for (Object subscriptionObj: ActivitySubscriptionLocalServiceUtil.dynamicQuery(query)) {
                ActivitySubscription subscription = (ActivitySubscription) subscriptionObj;
                
                if (subscription.getReceiverId() == activity.getUserId()) {
                    continue;
                }
                receipients.add(UserLocalServiceUtil.getUser(subscription.getReceiverId()));
            }
            for (User receipient: receipients) {
                
                if (MessageUtil.getMessagingPreferences(receipient.getUserId()).getEmailOnActivity()) {
                    InternetAddress toEmail = new InternetAddress(receipient.getEmailAddress());
                    String message = messageTemplate.replace(USER_PROFILE_LINK_PLACEHOLDER, getUserLink(receipient));
                    MailEngine.send(fromEmail, toEmail, subject, message, true);
                }
            
            }
        } catch (MailEngineException e) {
            _log.error("Can't send email message", e);
        } catch (AddressException e) {
            _log.error("Can't send email message", e);
        }
        
        
    }
    
    private String getUserLink(User user) {
        return USER_PROFILE_LINK_TEMPLATE.replaceAll(USER_ID_PLACEHOLDER, String.valueOf(user.getUserId()));
    }

}
