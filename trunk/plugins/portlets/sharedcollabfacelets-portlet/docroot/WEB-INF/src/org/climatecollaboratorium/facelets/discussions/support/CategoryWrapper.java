package org.climatecollaboratorium.facelets.discussions.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;
import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.facelets.discussions.activity.DiscussionActivityKeys;
import org.climatecollaboratorium.utils.ContentFilterHelper;
import org.climatecollaboratorium.utils.Helper;
import org.climatecollaboratorium.utils.HumanTime;
import org.climatecollaboratorium.validation.CategoryNameValidator;
import org.climatecollaboratorium.validation.ValueRequiredValidator;

import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class CategoryWrapper {
    private User author;
    private String title;
    private String description;
    private DiscussionBean discussionBean;
    private DiscussionCategory wrapped;
    private boolean editing;
    private String filteredDescription;
    private boolean goTo;
    private boolean added = false;
    private boolean byNewest = true;
    


    private List<MessageWrapper> threads = new ArrayList<MessageWrapper>();
    
    public CategoryWrapper(DiscussionCategory category, DiscussionBean discussionBean) throws SystemException {
        this.wrapped = category;
        this.discussionBean = discussionBean;
        for (DiscussionMessage thread: category.getThreads()) {
            this.threads.add(new MessageWrapper(thread, this, discussionBean, 1));
        }
        
        this.title = category.getName();
        this.description = category.getDescription();
        setFilteredDescription(ContentFilterHelper.filterContent(description));
    }

    public CategoryWrapper(DiscussionBean discussionBean) {
        this.discussionBean = discussionBean;
    }

    public Long getId() {
        return wrapped.getCategoryId();
    }
    
    public User getAuthor() {
        return author;
    }
    
    public void setAuthor(User author) {
        this.author = author;
    }
    
    public String getTitle() {
        return wrapped != null ? wrapped.getName() : title;
    }
    
    public void setTitle(String title) {
        this.title = title.substring(0, Math.min(255, title.length()));
    }
    
    public String getDescription() {
        return wrapped != null ? wrapped.getDescription() : description;
    }
    
    public void setDescription(String description) {
        this.description = description;
        setFilteredDescription(ContentFilterHelper.filterContent(description));
    }

    public List<MessageWrapper> getThreads() {
        return threads;
    }
    
    public int getThreadsCount() {
        return threads.size();
    }
    
    public User getLastPostAuthor() {
        return null;
    }
    
    public void save(ActionEvent e) throws SystemException, PortalException {
        if (!added && discussionBean.getPermissions().getCanAddCategory()) {
            UIInput descriptionInput = (UIInput) e.getComponent().getParent().findComponent("categoryDescription"); 
            UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("categoryName"); 
            if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !CategoryNameValidator.validateComponent(nameInput)) {
                return;
            }
            
            wrapped = discussionBean.getDiscussion().addCategory(title, description, Helper.getLiferayUser());
            discussionBean.categoryAdded(this);
            added = true;
            
            Helper.sendInfoMessage("Category \"" + title + "\" has been added.");
            goTo = true;
            

            ThemeDisplay td = Helper.getThemeDisplay();
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    DiscussionMessage.class.getName(), wrapped.getCategoryId(), DiscussionActivityKeys.ADD_CATEGORY.id(),null, 0);
        }
    }

    public void threadAdded(MessageWrapper thread) throws SystemException {
        threads.add(thread);
        discussionBean.threadAdded(thread);
    }

    public DiscussionCategory getWrapped() {
        return wrapped;
    }
    
    public Long getAuthorId() {
        return wrapped.getAuthorId();
    }
    
    public Long getLastActivityAuthorId() {
        return wrapped.getLastActivityAuthorId();
    }
    
    public String getLastActivityDate() {
        Date lastActivity = wrapped.getLastActivityDate();
        if (lastActivity == null) {
            lastActivity = wrapped.getCreateDate();
        }
        return HumanTime.exactly(new Date().getTime() - lastActivity.getTime());
    }
    
    public User getLastActivityAuthor() throws PortalException, SystemException {
        return wrapped.getLastActivityAuthor();
    }
    
    public void delete(ActionEvent e) throws SystemException {
        if (discussionBean.getPermissions().getCanAdminCategories()) {
            wrapped.delete();
            discussionBean.categoryDeleted(this);
            Helper.sendInfoMessage("Category \"" + wrapped.getName() + "\" has been deleted.");
        }
    }
    
    public void messageDeleted(MessageWrapper messageWrapper) {
        if (threads != null) {
            threads.remove(messageWrapper);
            discussionBean.messageDeleted(messageWrapper);
        }
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public void toggleEdit(ActionEvent e) {
        editing = !editing;
    }
    
    public void update(ActionEvent e) throws SystemException {
        if (discussionBean.getPermissions().getCanAdmin()) {
            UIInput descriptionInput = (UIInput) e.getComponent().getParent().findComponent("categoryDescription"); 
            UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("categoryName"); 
            if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !ValueRequiredValidator.validateComponent(descriptionInput) || 
                    !CategoryNameValidator.validateComponent(nameInput)) {
                return;
            }
            
            wrapped.update(title, description);
            editing = !editing;
        }        
    }

    public void setFilteredDescription(String filteredDescription) {
        this.filteredDescription = filteredDescription;
    }

    public String getFilteredDescription() {
        return filteredDescription;
    }

    
    public boolean isGoTo() {
        return goTo;
    }

    public void setGoTo(boolean goTo) {
        this.goTo = goTo;
    }
    
    public boolean isByNewest() {
        return byNewest;
    }
    
    public void resort(ActionEvent e) {
        byNewest = !byNewest;
        
        Collections.sort(threads, new Comparator<MessageWrapper>() {

            @Override
            public int compare(MessageWrapper o1, MessageWrapper o2) {
                return o1.getLastActivityDate().compareTo(o2.getLastActivityDate()) * (byNewest ? -1 : 1);
            }
        });
    }
    
}
