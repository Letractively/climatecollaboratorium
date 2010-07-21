package org.climatecollaboratorium.facelets.discussions.support;

import javax.faces.event.ActionEvent;

import com.liferay.portal.model.User;

public class MessageWrapper {
    private Long id;
    private User author;
    private String title;
    private String description;
    private ThreadWrapper thread;
    
    public MessageWrapper() {
    }
    
    public MessageWrapper(Long id, User author, String title, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
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

    public void setThread(ThreadWrapper thread) {
        this.thread = thread;
    }

    public ThreadWrapper getThread() {
        return thread;
    }
    
    
    public void add(ActionEvent e) {
        this.setAuthor(DiscussionServiceMock.getRandomUser());
        thread.addMessage(this);
    }

}
