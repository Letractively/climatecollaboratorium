package org.climatecollaboratorium.facelets.discussions.support;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.model.User;

public class CategoryWrapper {
    private Long id;
    private User author;
    private String title;
    private String description;
    private List<ThreadWrapper> threads = new ArrayList<ThreadWrapper>();
    
    public CategoryWrapper() {
    }
    
    public CategoryWrapper(Long id, User author, String title, String description) {
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

    public void addThread(ThreadWrapper thread) {
        threads.add(thread);
    }
    
    public List<ThreadWrapper> getThreads() {
        return threads;
    }
    
    public int getThreadsCount() {
        return threads.size();
    }
    
    public User getLastPostAuthor() {
        return threads.get(threads.size()-1).getAuthor();
    }

}
