/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debates.bean.backing;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.messaging.MessageUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.util.mail.MailEngineException;
import org.climatecollaboratorium.debates.DebatesUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
import java.util.ArrayList;
import java.util.List;

public class DebatesSuggestBean {

    private enum Type {
        ARGUMENT_PRO("Suggest supporting argument","New Supporting Argument","Please provide details of the supporting argument you'd like to see in this debate map."),
        ARGUMENT_CON("Suggest counter-argument","New Counter-argument","Please provide details of the counter-argument you'd like to see in this debate map."),
        POSITION("Suggest Position","New Position","Please provide details of the position you'd like to see in this debate map."),
        DEBATE("Suggest Debate","New Debate","Please provide details of the debate that you'd like to see.");

        String header;
        String subject;
        String text;

        Type(String header, String subject, String text) {
            this.header = header;
            this.subject = subject;
            this.text = text;

        }

        public String getSubject() {
            return subject;
        }

        public String getHeader() {
            return header;
        }

        public String getText() {
            return text;
        }
    }



    private boolean suggesting = false;
    private String suggestion;
    private DebatesPreferencesBean debatesPreferences;
    private Type current_type = null;
    private DebateDetailsBean details = null;


    public void setDebateDetailsBean(DebateDetailsBean bean) {
        this.details = bean;
    }

    public DebateDetailsBean getDebateDetailsBean() {
        return details;
    }
    
    public DebatesPreferencesBean getDebatesPreferences() {
        return debatesPreferences;
    }

    public void setDebatesPreferences(DebatesPreferencesBean debatesPreferences) {
        this.debatesPreferences = debatesPreferences;
    }

    public boolean isSuggesting() {
        return suggesting;
    }
    
    public void setSuggesting(boolean suggesting) {
        this.suggesting = suggesting;
    }
    
    public String getSuggestion() {
        return suggestion;
    }

    public void setOriginator(DebateItem item) {

    }
    
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    
    
    public void showAddSuggestionForm(ActionEvent event) {
        setType((String)event.getComponent().getAttributes().get("suggestionType"));
        suggesting = true;
        suggestion = null;



    }
    
    public void cancelAddSuggestion(ActionEvent event) {
        suggesting = false;
        suggestion = null;
        
    }

    public String getHeader() {
        return current_type!=null?current_type.getHeader():"Suggestion";
    }

    public String getText() {
        return current_type!=null?current_type.getText():"";
    }

    public void setType(String type) {
        current_type = Type.valueOf(type);
    }


    
    public void addSuggestion(ActionEvent event) throws PortalException, SystemException, AddressException, MailEngineException {
        long fromId = 10144;
        if (Helper.isUserLoggedIn()) {
            fromId = Helper.getLiferayUser().getUserId();
        }
        List<User> users = debatesPreferences.getSuggestionRecipients();
        List<Long> recipients = new ArrayList<Long>();
        for (User user: users) {
            recipients.add(user.getUserId());
        }

        String message = suggestion;
        if (current_type != Type.DEBATE && details.getSelectedDebateItem()!=null) {
            message = "(Selected debate item: "+
                    details.getSelectedDebateItem().getDebatePostType()+":"+
                    "<a href='"+ DebatesUtil.getItemURL(details.getSelectedDebateItem())+"'>"+
                    details.getSelectedDebateItem().getDebateSummary()+"</a> in debate \""+
                    details.getSelectedDebateItem().getDebate().getCurrentRoot().getDebateSummary()+"\")<br/><br/>"+
                    message;

        }
        MessageUtil.sendMessage(current_type!=null?current_type.getSubject():"New suggestion", message, fromId, fromId, recipients, null);
        

        FacesMessage fm = new FacesMessage();
        fm.setSummary("Message has been sent");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        
        suggesting = false;
        suggestion = null;
        
    }
    

    

}
