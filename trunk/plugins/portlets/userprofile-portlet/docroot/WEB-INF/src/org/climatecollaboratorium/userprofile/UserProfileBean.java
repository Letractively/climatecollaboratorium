package org.climatecollaboratorium.userprofile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;

import com.ext.portlet.Activity.ActivityConstants;
import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.messaging.MessageConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.model.MessagingUserPreferences;
import com.ext.portlet.messaging.service.MessagingUserPreferencesLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.util.mail.MailEngineException;

public class UserProfileBean {
    private UserWrapper currentUser; 
    private User wrappedUser;
    private final static String USER_ID_PARAM = "userId";
    private final static Log _log = LogFactoryUtil.getLog(UserProfileBean.class);
    private boolean viewingOwnProfile = false;
    private boolean editing = false;
    private String messageText;
    private String messageSubject;
    private List<MessageBean> messages;
    private ArrayList<UserActivityBean> subscribedActivities;
    private PageType pageType = PageType.PROFILE_NOT_INITIALIZED;
    private UserSubscriptionsBean subscriptionsBean;
    private String messagingPortletId;
    
    public UserProfileBean() {
        Map<String, String> parameters = Helper.getUrlParametersMap();
        long companyId = CompanyThreadLocal.getCompanyId(); 
        if (companyId == 0) {
            CompanyThreadLocal.setCompanyId(Helper.getThemeDisplay().getCompanyId());
        }
        
        if (parameters.containsKey(USER_ID_PARAM)) {
            try {
                Long userId = Long.parseLong(parameters.get(USER_ID_PARAM));
                wrappedUser = UserLocalServiceUtil.getUser(userId);
                currentUser = new UserWrapper(wrappedUser);
                pageType = PageType.PROFILE_DETAILS;
                subscriptionsBean = new UserSubscriptionsBean(wrappedUser);
            }
            catch (NumberFormatException e) {
                _log.error("Can't parse user id: " + parameters.get(USER_ID_PARAM), e);
            }
            catch (SystemException e) {
                _log.error("Can't load user with id " + parameters.get(USER_ID_PARAM), e);
            }
            catch (PortalException e) {
                _log.error("Can't load user with id " + parameters.get(USER_ID_PARAM), e);
            }
            
        }
        
        if (wrappedUser != null && 
                Helper.isUserLoggedIn() && 
                wrappedUser.getUserId() == Helper.getLiferayUser().getUserId()) {
            // user is logged in and is viewing his own profile
            viewingOwnProfile = true;
        }
        
    }
    
    public boolean isInitialized() {
        return currentUser != null;
    }
    
    
    public UserWrapper getCurrentUser() {
        return currentUser;
    }

    public boolean isViewingOwnProfile() {
        return viewingOwnProfile;
    }

    public boolean isEditing() {
        return editing;
    }
    
    public void toggleEditing(ActionEvent e) throws PortalException, SystemException {
        editing = !editing;
        if (! editing) {
            currentUser.cancelChanges();
            pageType = PageType.PROFILE_DETAILS;
        }
        else {
            pageType = PageType.PROFILE_EDIT;
        }
    }
    
    public void updateUser(ActionEvent e) throws Exception {
        currentUser.persistChanges();
        editing = !editing;
        pageType = PageType.PROFILE_DETAILS;
    }
    
    public void setMessageText(String message) {
        this.messageText = message;
    }
    
    public String getMessageText() {
        return messageText;
    }
    
    public void setMessageSubject(String subject) {
        this.messageSubject = subject;   
    }
    
    public String getMessageSubject() {
        return messageSubject;
    }
    
    
    public void sendMessage(ActionEvent e) throws AddressException, SystemException, PortalException, MailEngineException {
        if (messageText != null && messageText.trim().length() > 0 && wrappedUser != null && Helper.isUserLoggedIn()) {
            //MessageUtil.sendMessage(subject, content, fromId, repliesTo, tousers, request)
            
            List<Long> recipients = new ArrayList<Long>();
            recipients.add(wrappedUser.getUserId());
            
            MessageUtil.sendMessage(messageSubject, messageText, Helper.getLiferayUser().getUserId(), 
                    Helper.getLiferayUser().getUserId(),recipients, null);
        }
        
    }
    
    public List<MessageBean> getMessages() throws SystemException, PortalException {
        if (messages == null) {
            messages = new ArrayList<MessageBean>();
            for (Message msg: MessageUtil.getMessages(currentUser.getUserId(), 0, 2, MessageConstants.INBOX)) {
                messages.add(new MessageBean(msg));
            }
        }
        return messages;
    }
    
    public List<UserActivityBean> getSubscribedActivities() throws SystemException, PortalException {
        if (subscribedActivities == null) {
            subscribedActivities = new ArrayList<UserActivityBean>();
            for (SocialActivity activity: ActivitySubscriptionLocalServiceUtil.getActivities(wrappedUser.getUserId(), 0, 200)) {
                subscribedActivities.add(new UserActivityBean(activity));
            }
            
        }
        return subscribedActivities;
    }
    
    public boolean getSendEmailOnMessage() throws SystemException {
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(Helper.getLiferayUser().getUserId());
        return prefs.getEmailOnReceipt();
    }
    
    public void setSendEmailOnMessage(boolean send) throws SystemException {
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(Helper.getLiferayUser().getUserId());
        prefs.setEmailOnReceipt(send);
        MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
    }
    
    public void showPage(ActionEvent e) {
        try {
            String name = String.valueOf(e.getComponent().getAttributes().get("name"));
            pageType = PageType.valueOf(name);
        }
        catch (Exception ex) {
            // ignore
        }
    }


    public PageType getPageType() {
        return pageType;
    }

    public void setMessagingPortletId(String messagingPortletId) {
        this.messagingPortletId = messagingPortletId;
    }

    public String getMessagingPortletId() {
        return messagingPortletId;
    }

    public UserSubscriptionsBean getSubscriptionsBean() {
        return subscriptionsBean;
    }

}
