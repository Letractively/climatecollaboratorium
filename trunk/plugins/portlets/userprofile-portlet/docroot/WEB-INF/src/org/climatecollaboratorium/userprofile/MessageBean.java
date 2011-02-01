package org.climatecollaboratorium.userprofile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.model.MessageRecipientStatus;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class MessageBean {
    
    private Message message;
    private boolean selected;
    private List<User> receipients = new ArrayList<User>();

    public MessageBean(Message message) throws PortalException, SystemException {
        this.message = message;
        for (MessageRecipientStatus receipient: message.getRecipients()) {
            receipients.add(UserLocalServiceUtil.getUser(receipient.getUserId()));
        }
    }
    
    public String getSubject() {
        return message.getSubject();
    }
    
    public String getContent() {
        return message.getContent();
    }
    
    public Date getCreateDate() {
        return message.getCreateDate();
    }
    
    public long getDaysAgo() {
        final int milisecondsInDay = 1000 * 60 * 60 * 24;
        long createDay = message.getCreateDate().getTime() / milisecondsInDay;
        long daysNow = new Date().getTime() / milisecondsInDay;
        return daysNow - createDay;
    }
    
    public User getFrom() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(message.getFromId());
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public Message getMessage() {
        return message;
        
    }
    
    public List<User> getTo() {
        return receipients;
    }
    
}
