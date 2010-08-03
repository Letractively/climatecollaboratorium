package org.climatecollaboratorium.contact;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.PortletSession;

import com.ext.portlet.messaging.MessageUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.util.WebKeys;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

public class ContactBean {
    private String name;
    private String email;
    private String message;
    private int requestCount;
    private boolean expanded;


    private ContactPreferences contactPreferences;
    private String captcha;
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setCaptchaText(String captcha) {
        this.captcha = captcha;
    }
    
    public String getCaptchaText() {
        return captcha;
    }



    public void sendMessage(ActionEvent e) throws AddressException, SystemException, PortalException, MailEngineException {
        String messageSubject = applyFilters(contactPreferences.getMessageSubject());
        String messageBody = applyFilters(contactPreferences.getMessageFormat());        

        InternetAddress addressFrom = new InternetAddress("admin@climatecollaboratorium.org");
        
        String[] receipients = contactPreferences.getReceipientsArray();
        InternetAddress[] addressTo = new InternetAddress[receipients.length];
        for (int i=0; i < receipients.length; i++) {
            addressTo[i] = new InternetAddress(receipients[i]);
        }
        
        InternetAddress replyTo[] = {new InternetAddress(email)};
        
        
        MailEngine.send(addressFrom, addressTo, null, null, null, messageSubject, messageBody, false, replyTo, null, null);
    }
    
    private String applyFilters(String msg) {
        msg = msg.replaceAll("USER_NAME", name);
        msg = msg.replaceAll("USER_EMAIL", email);
        msg = msg.replaceAll("USER_MESSAGE", message);
        return msg; 
    }
    
    
    public int getRequestCount() {
        return requestCount++;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
    
    public void toggleExpanded(ActionEvent e) {
        expanded = !expanded;
        if (!expanded) {
            captcha = "";
            name = "";
            message = "";
            email = "";
        }
    }
    
    public String getExpandFormMessage() {
        return contactPreferences.getExpandLinkText();
    }


    public void setContactPreferences(ContactPreferences contactPreferences) {
        this.contactPreferences = contactPreferences;
    }

}
