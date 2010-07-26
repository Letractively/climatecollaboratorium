package org.climatecollaboratorium.facelets.discussions.support;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;

import edu.emory.mathcs.backport.java.util.Collections;

public class MessageWrapper {
    private String title;
    private String description;
    private DiscussionMessage wrapped;
    private CategoryWrapper category;
    private List<MessageWrapper> messages;
    private MessageWrapper newMessage;
    private MessageWrapper thread;
    
    public MessageWrapper(DiscussionMessage wrapped, CategoryWrapper category) {
        this.category = category;
        this.wrapped = wrapped;
        if (wrapped.getThreadId() == null) {
            // message represents a thread create placeholder for new message
            newMessage = new MessageWrapper(this);
        }
    }

    public MessageWrapper(CategoryWrapper categoryWrapper) {
        this.category = categoryWrapper; 
    }
    
    public MessageWrapper(MessageWrapper thread) {
        this.thread = thread;
    }

    public Long getId() {
        return wrapped.getMessageId();
    }

    public String getTitle() {
        return wrapped != null ? wrapped.getSubject() : title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return wrapped != null ? wrapped.getBody() : description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<MessageWrapper> getThreadMessages() throws SystemException {
        if (messages == null) {
            messages = new ArrayList<MessageWrapper>();
            messages.add(this);
            for (DiscussionMessage message: wrapped.getThreadMessages()) {
                messages.add(new MessageWrapper(message, category));
            }
            
            Collections.sort(messages, new Comparator<MessageWrapper>() {

                @Override
                public int compare(MessageWrapper o1, MessageWrapper o2) {
                    return o1.getCreateDate().compareTo(o2.getCreateDate());
                }
            });
        }
        return messages;
    }
    
    public CategoryWrapper getCategory() {
        return category;
    }
    
    public int getThreadMessagesCount() throws SystemException {
        return wrapped.getThreadMessagesCount();
    }
    
    public void save(ActionEvent e) throws SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped = category.getWrapped().addThread(title, description, Helper.getLiferayUser());
            category.threadAdded(this);
            newMessage = new MessageWrapper(this);
        }
    }
    
    public MessageWrapper getNewMessage() {
        return newMessage;
    }
    
    public void addMessageToThread(ActionEvent e) throws SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped = thread.getWrapped().addThreadMessage(title, description, Helper.getLiferayUser());
            thread.addMessage(this);
        }
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return wrapped.getAuthor();
    }
    
    public Date getCreateDate() {
        return wrapped.getCreateDate();
    }
    
    private void addMessage(MessageWrapper messageWrapper) {
        messages.add(messageWrapper);
        newMessage = new MessageWrapper(this);
    }

    public DiscussionMessage getWrapped() {
        return wrapped;
    }
    
    public Long getAuthorId() {
        return wrapped.getAuthorId();
    }
    
    public Long getLastActivityAuthorId() {
        return wrapped.getLastActivityAuthorId();
    }
    
    public Date getLastActivityDate() {
        return wrapped.getLastActivityDate();
    }
    
    public User getLastActivityAuthor() throws PortalException, SystemException {
        return wrapped.getLastActivityAuthor();
    }

}
