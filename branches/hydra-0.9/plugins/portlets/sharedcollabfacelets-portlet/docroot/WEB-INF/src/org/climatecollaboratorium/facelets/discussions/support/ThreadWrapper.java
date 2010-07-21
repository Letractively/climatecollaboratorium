package org.climatecollaboratorium.facelets.discussions.support;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.model.User;

public class ThreadWrapper {
    private Long id;
    private User author;
    private String title;
    private String description;
    private List<MessageWrapper> messages = new ArrayList<MessageWrapper>();
    private CategoryWrapper category;
    private MessageWrapper newMessage =  new MessageWrapper();
    
    public ThreadWrapper() {
        newMessage.setThread(this);   
    }
    
    public ThreadWrapper(Long id, User author, String title, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        newMessage.setThread(this);
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void addMessage(MessageWrapper message) {
        messages.add(message);
        if (message == newMessage) {
            newMessage = new MessageWrapper();
            newMessage.setThread(this);
        }
    }
    
    public List<MessageWrapper> getMessages() {
        return messages;
    }

    public void setCategory(CategoryWrapper category) {
        this.category = category;
    }

    public CategoryWrapper getCategory() {
        return category;
    }

    public int getMessagesCount() {
        return messages.size();
    }
    
    public User getLastPostAuthor() {
        return messages.get(messages.size()-1).getAuthor();
    }

    public MessageWrapper getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(MessageWrapper newMessage) {
        this.newMessage = newMessage;
    }
}
