package org.climatecollaboratorium.facelets.discussions;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.facelets.discussions.support.CategoryWrapper;
import org.climatecollaboratorium.facelets.discussions.support.DiscussionServiceMock;
import org.climatecollaboratorium.facelets.discussions.support.ThreadWrapper;

public class DiscussionBean {
    private Long discussionId;
    private Long categoryId;
    private Long threadId;
    private DiscussionPageType pageType = DiscussionPageType.DISCUSSIONS;

    private Long lastInitDiscussionId;
    private Long lastInitCategoryId;
    private Long lastInitThreadId;
    private EventBus eventBus;
    private CategoryWrapper currentCategory;
    private ThreadWrapper currentThread;
    
    public void init(Long discussionId, Long categoryId, Long threadId) {
        if (discussionId == null) {
            return;
        }
        if (discussionId == lastInitDiscussionId && categoryId == lastInitCategoryId && threadId == lastInitThreadId) {
            // initialization with the same parameters, do nothing as this would cause reset to internal discussion state
            return;
        }
        lastInitDiscussionId = discussionId;
        lastInitCategoryId = categoryId;
        lastInitThreadId = threadId;
        
        this.discussionId = discussionId;
        this.categoryId = categoryId;
        this.threadId = threadId;
        
        updatePageType();
    }
    
    private void updatePageType() {
        if (threadId != null) {
            currentThread = DiscussionServiceMock.getThreadById(threadId);
            pageType = DiscussionPageType.THREAD;
            
        }
        else if (categoryId != null) {
            currentCategory = DiscussionServiceMock.getCategoryById(categoryId);
            pageType = DiscussionPageType.CATEGORY; 
        }
        
    }
    
    public DiscussionPageType getPageType() {
        return pageType;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public void changePageType(ActionEvent e) {
        DiscussionPageType newType = DiscussionPageType.valueOf(
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pageType").toString());
        this.pageType = newType;
        if (pageType == DiscussionPageType.CATEGORY) {
            categoryId = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoryId").toString());
            currentCategory = DiscussionServiceMock.getCategoryById(categoryId);            
        }
        else if (pageType == DiscussionPageType.THREAD) {
            threadId = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("threadId").toString());
            currentThread = DiscussionServiceMock.getThreadById(threadId);
        }
        
    }
    
    public List<ThreadWrapper> getThreads() {
        return DiscussionServiceMock.getThreads();
    }
    
    public List<CategoryWrapper> getCategories() {
        return DiscussionServiceMock.getCategories();
    }
    
    public CategoryWrapper getCurrentCategory() {
        return currentCategory;
    }
    
    public ThreadWrapper getCurrentThread() {
        return currentThread;
        
    }


}
