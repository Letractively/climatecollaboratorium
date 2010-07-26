package org.climatecollaboratorium.facelets.discussions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.facelets.discussions.support.CategoryWrapper;
import org.climatecollaboratorium.facelets.discussions.support.MessageWrapper;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.NoSuchDiscussionMessageException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DiscussionBean {
    private Long discussionId;
    private Long categoryId;
    private Long threadId;
    private Long messageId;
    private DiscussionPageType pageType = DiscussionPageType.DISCUSSIONS;

    private Long lastInitDiscussionId;
    private Long lastInitCategoryId;
    private Long lastInitThreadId;
    private Long lastInitMessageId;
    
    private EventBus eventBus;
    private CategoryWrapper currentCategory;
    private MessageWrapper currentThread;
    private List<CategoryWrapper> categories;
    private List<MessageWrapper> threads;
    private Map<Long, CategoryWrapper> categoriesById;
    private Map<Long, MessageWrapper> threadsById;
    
    private CategoryWrapper newCategory;
    private String searchQuery;
    private List<MessageWrapper> searchResults = new ArrayList<MessageWrapper>();
    private List<SelectItem> categoriesItems;
    private MessageWrapper newThread = new MessageWrapper(this);
    
    
    private DiscussionCategoryGroup discussion;
    private static Log _log = LogFactoryUtil.getLog(DiscussionBean.class);
    
    /**
     * Method initializes all elements within the bean. If bean wasn't correctly initialized (there is no such discussion)
     * false is returned. True otherwise.
     * @param discussionId
     * @param categoryId
     * @param threadId
     * @param messageId
     * @return
     */
    public boolean init(Long discussionId, Long categoryId, Long threadId, Long messageId) {
        if (discussionId == null) {
            return false;
        }
        
        if ((discussionId == null && lastInitDiscussionId == null || discussionId.equals(lastInitDiscussionId)) && 
                (categoryId == null && lastInitCategoryId == null || categoryId.equals(lastInitCategoryId)) && 
                (threadId == null && lastInitThreadId == null || threadId.equals(lastInitThreadId)) && 
                (messageId == null && lastInitMessageId == null || messageId.equals(lastInitMessageId))) {
            // initialization with the same parameters, do nothing as this would cause reset to internal discussion state
            return discussion != null;
        }
        lastInitDiscussionId = discussionId;
        lastInitCategoryId = categoryId;
        lastInitThreadId = threadId;
        lastInitMessageId = messageId;
        
        this.discussionId = discussionId;
        this.categoryId = categoryId;
        this.threadId = threadId;
        this.messageId = messageId;
        

        try {
            discussion = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId);
            
            categories = null;
            threads = null;
            
            updatePageType();
        } catch (Exception e) {
            _log.error("Error when initializing discussion bean", e);
            return false;
        }
        
        return true;
    }
    
    private void updatePageType() throws NoSuchDiscussionCategoryException, SystemException, NoSuchDiscussionMessageException {
        if (threadId != null) {
            getThreads();
            currentThread = threadsById.get(threadId);
            pageType = DiscussionPageType.THREAD;
            
        }
        else if (categoryId != null) {
            getCategories();
            currentCategory = categoriesById.get(categoryId);
            pageType = DiscussionPageType.CATEGORY; 
        }
        
    }
    
    public DiscussionPageType getPageType() {
        return pageType;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public void changePageType(ActionEvent e) throws NoSuchDiscussionCategoryException, SystemException, NoSuchDiscussionMessageException {
        DiscussionPageType newType = DiscussionPageType.valueOf(e.getComponent().getAttributes().get("pageType").toString());
        
        DiscussionPageType prevPage = pageType;
        this.pageType = newType;
        if (pageType == DiscussionPageType.CATEGORY) {
            categoryId = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoryId").toString());
            getCategories();
            currentCategory = categoriesById.get(categoryId);            
        }
        else if (pageType == DiscussionPageType.THREAD) {
            threadId = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("threadId").toString());
            getThreads();
            currentThread = threadsById.get(threadId);
            currentCategory = categoriesById.get(currentThread.getWrapped().getCategoryId());
        }
        else if (pageType == DiscussionPageType.CATEGORY_ADD) {
            newCategory = new CategoryWrapper(this);
        }
        else if (pageType == DiscussionPageType.THREAD_ADD) {
            newThread = new MessageWrapper(this);
            if (prevPage == DiscussionPageType.CATEGORY) {
                newThread.setCategoryId(currentCategory.getId());
            }
        }
            
        System.out.println(currentCategory);
    }
    
    public List<MessageWrapper> getThreads() throws SystemException {
        if (threads == null) {
            threads = new ArrayList<MessageWrapper>();
            threadsById = new HashMap<Long, MessageWrapper>();
            for (CategoryWrapper catWrapper: getCategories()) {
                for (MessageWrapper thread: catWrapper.getThreads()) {
                    threads.add(thread);   
                    threadsById.put(thread.getId(), thread);
                }
            }
        }
        return threads;
    }
    
    public List<CategoryWrapper> getCategories() throws SystemException {
        if (categories == null) {
            categories = new ArrayList<CategoryWrapper>();
            categoriesById = new HashMap<Long, CategoryWrapper>();
            for (DiscussionCategory category: discussion.getCategories()) {
                CategoryWrapper catWrapper = new CategoryWrapper(category, this);
                categories.add(catWrapper);
                categoriesById.put(catWrapper.getId(), catWrapper);
            }
        }
        return categories;
    }
    
    public CategoryWrapper getCurrentCategory() {
        return currentCategory;
    }
    
    public MessageWrapper getCurrentThread() {
        return currentThread;
        
    }

    public CategoryWrapper getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(CategoryWrapper newCategory) {
        this.newCategory = newCategory;
    }

    public DiscussionCategoryGroup getDiscussion() {
        return discussion;
    }

    public void categoryAdded(CategoryWrapper category) throws SystemException {
        getCategories().add(category);
        categoriesById.put(category.getId(), category);
        this.pageType = DiscussionPageType.CATEGORY;
        currentCategory = category;
        if (categoriesItems != null) {
            categoriesItems.add(new SelectItem(category.getId(), category.getTitle()));
        }
    }

    public void threadAdded(MessageWrapper thread) throws SystemException {
        getThreads().add(thread);
        threadsById.put(thread.getId(), thread);
        this.pageType = DiscussionPageType.THREAD;
        this.currentThread = thread;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
    
    public void search(ActionEvent e) throws SystemException {
        if (searchQuery.trim().length() == 0) {
            // do nothing
            return;
        }
        pageType = DiscussionPageType.SEARCH_RESULTS;
        searchResults.clear();
        for (DiscussionMessage message: DiscussionMessageLocalServiceUtil.search(searchQuery)) {
            searchResults.add(new MessageWrapper(message, null));
        }
    }

    public List<MessageWrapper> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<MessageWrapper> searchResults) {
        this.searchResults = searchResults;
    }
    
    public List<SelectItem> getCategoriesItems() throws SystemException {
        if (categoriesItems == null) {
            categoriesItems = new ArrayList<SelectItem>();
            for (CategoryWrapper category: getCategories()) {
                categoriesItems.add(new SelectItem(category.getId(), category.getTitle()));
            }
        }
        return categoriesItems;
    }

    public CategoryWrapper getCategoryById(Long categoryId) throws SystemException {
        getCategories();
        return categoriesById.get(categoryId);
    }
    
    public MessageWrapper getNewThread() {
        return newThread;
    }

    public void setNewThread(MessageWrapper newThread) {
        this.newThread = newThread;
    }

    public void categoryDeleted(CategoryWrapper categoryWrapper) {
        if (categories != null) {
            categories.remove(categoryWrapper);
            categoriesById.remove(categoryWrapper.getId());
        }
        if (pageType == DiscussionPageType.CATEGORY && currentCategory.getId() == categoryWrapper.getId()) {
            pageType = DiscussionPageType.CATEGORIES;
        }        
    }
    
    public void messageDeleted(MessageWrapper messageWrapper) {
        if (threads != null) {
            threads.remove(messageWrapper);
            threadsById.remove(messageWrapper.getId());
        }
        if (pageType == DiscussionPageType.THREAD && currentThread.getId() == messageWrapper.getId()) {
            pageType = DiscussionPageType.CATEGORY;
            currentCategory = currentThread.getCategory();
        }
    }
    
}
