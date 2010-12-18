package org.climatecollaboratorium.facelets.discussions.support;

import java.util.ArrayList;
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
import org.climatecollaboratorium.validation.ValueRequiredValidator;

import com.ext.portlet.discussions.model.DiscussionMessage;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class MessageWrapper {
    private String title;
    private String description;
    private DiscussionMessage wrapped;
    private CategoryWrapper category;
    private List<MessageWrapper> messages;
    private MessageWrapper newMessage;
    private MessageWrapper thread;
    private Long categoryId;
    private DiscussionBean discussionBean;
    private boolean editing;
    private String filteredDescription;
    private boolean empty = false;

    
    public String getShortDescription() {
        return shortDescription;
    }

    private String shortDescription;
    private boolean goTo;
    private boolean added = false;
    private int messageNum;
    private boolean oldestFirst = true;
    
    public MessageWrapper(DiscussionMessage wrapped, CategoryWrapper category, DiscussionBean discussionBean, int messageNum) {
        this.category = category;
        this.wrapped = wrapped;

        title = wrapped.getSubject();
        description = wrapped.getBody();
        filteredDescription = ContentFilterHelper.filterContent(description);
        shortDescription = ContentFilterHelper.getShortString(description);
        this.discussionBean = discussionBean;
        messageNum = messageNum;
        
        
        if (wrapped.getThreadId() == null) {
            // message represents a thread create placeholder for new message
            newMessage = new MessageWrapper(this);
        }
    }

    public MessageWrapper(DiscussionBean discussionBean) {
        this.discussionBean = discussionBean; 
        empty = true;
        messages = new ArrayList<MessageWrapper>();
    }
    
    public MessageWrapper(MessageWrapper thread) {
        this.thread = thread;
        this.discussionBean = thread.discussionBean;
    }
    
    public Long getId() {
        return wrapped.getMessageId();
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title.substring(0, Math.min(255, title.length()));
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFilteredDescription() {
        return filteredDescription;
    }

    
    
    public List<MessageWrapper> getThreadMessages() throws SystemException {
        if (messages == null || messages.size() == 0) {
            messages = new ArrayList<MessageWrapper>();
            messages.add(this);
            for (DiscussionMessage message: wrapped.getThreadMessages()) {
                messages.add(new MessageWrapper(message, category, discussionBean, 0));
            }
            
            Collections.sort(messages, new Comparator<MessageWrapper>() {

                @Override
                public int compare(MessageWrapper o1, MessageWrapper o2) {
                    return o1.getCreateDate().compareTo(o2.getCreateDate());
                }
            });
            int messageNum = 1;
            for (MessageWrapper message: messages) {
                message.messageNum = messageNum;
                messageNum++;
            }
        }
        return messages;
    }
    
    public CategoryWrapper getCategory() {
        return category;
    }
    
    public int getThreadMessagesCount() throws SystemException {
        return wrapped.getThreadMessagesCount();
    }
    
    public void save(ActionEvent e) throws SystemException, PortalException {
        if (!added && discussionBean.getPermissions().getCanAddThread()) {

            UIInput messageInput = (UIInput) e.getComponent().getParent().findComponent("messageContent"); 
            UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("messageTitle"); 
            if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !ValueRequiredValidator.validateComponent(messageInput)) {
                return;
            }
            
            
            category = discussionBean.getCategoryById(categoryId);
            wrapped = category.getWrapped().addThread(title, description, Helper.getLiferayUser());
            added = true;
            category.threadAdded(this);
            newMessage = new MessageWrapper(this);
            filteredDescription = ContentFilterHelper.filterContent(description);
            Helper.sendInfoMessage("Message \"" + title + "\" has been added.");
            goTo = true;
            ThemeDisplay td = Helper.getThemeDisplay();

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    DiscussionMessage.class.getName(), wrapped.getMessageId(), DiscussionActivityKeys.ADD_DISCUSSION.id(),null, 0);
        }
    }
    
    public void addMessageToThread(ActionEvent e) throws SystemException, PortalException {
        if (!added && discussionBean.getPermissions().getCanAddMessage()) {

            UIInput messageInput = (UIInput) e.getComponent().getParent().findComponent("messageContent"); 
            UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("messageTitle"); 
            if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !ValueRequiredValidator.validateComponent(messageInput)) {
                return;
            }
            
            wrapped = thread.getWrapped().addThreadMessage(title, description, Helper.getLiferayUser());
            added = true;
            thread.addMessage(this);
            filteredDescription = ContentFilterHelper.filterContent(description);
            Helper.sendInfoMessage("Message \"" + title + "\" has been added.");

            ThemeDisplay td = Helper.getThemeDisplay();
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    DiscussionMessage.class.getName(), wrapped.getMessageId(), DiscussionActivityKeys.ADD_COMMENT.id(),null, 0);
        }
    }
    
    public void addComment(ActionEvent e) throws SystemException, PortalException {
        try {
        if (!added && discussionBean.getPermissions().getCanAddComment()) {

            UIInput messageInput = (UIInput) e.getComponent().getParent().findComponent("messageContent"); 
            
           
            if (!ValueRequiredValidator.validateComponent(messageInput)) {
                return;
            }
            title = "Comment title";
            wrapped = discussionBean.getDiscussion().addComment(title, description, Helper.getLiferayUser());
            added = true;

            discussionBean.commentAdded(this);
            empty = false;
            
            filteredDescription = ContentFilterHelper.filterContent(description);
            Helper.sendInfoMessage("Comment \"" + title + "\" has been added.");

            ThemeDisplay td = Helper.getThemeDisplay();
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    DiscussionMessage.class.getName(), wrapped.getMessageId(), DiscussionActivityKeys.ADD_DISCUSSION_COMMENT.id(),null, 0);
        }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateMessage(ActionEvent e) throws SystemException {
        try {
        if (discussionBean.getPermissions().getCanAdminMessages()) {

            UIInput messageInput = (UIInput) e.getComponent().getParent().findComponent("messageContent"); 
            UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("messageTitle"); 
            if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !ValueRequiredValidator.validateComponent(messageInput)) {
                return;
            }
            
            
            wrapped.update(title, description);
            filteredDescription = ContentFilterHelper.filterContent(description);
            editing = false;
        }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public MessageWrapper getNewMessage() {
        return newMessage;
    }

    public User getAuthor() throws PortalException, SystemException {
        return wrapped.getAuthor();
    }
    
    public Date getCreateDate() {
        return wrapped.getCreateDate();
    }
    
    public void addMessage(MessageWrapper messageWrapper) throws SystemException {
        if (messages == null) {
            getThreadMessages();
        }
        messageWrapper.messageNum = messages.get(messages.size() - 1).messageNum + 1;
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
    
    public String getLastActivityDate() {
        return HumanTime.exactly(new Date().getTime() - wrapped.getLastActivityDate().getTime());
    }
    
    public User getLastActivityAuthor() throws PortalException, SystemException {
        return wrapped.getLastActivityAuthor();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    
    public void delete(ActionEvent e) throws SystemException, PortalException {
        if (discussionBean.getPermissions().getCanAdminMessages()) {
            wrapped.delete();
            if (thread != null) {
                // this is a message within a thread
                thread.messageDeleted(this);
            }
            if (category != null) {
                category.messageDeleted(this);
            }
            
            //category.messageDeleted(this);
            Helper.sendInfoMessage("Message \"" + wrapped.getSubject() + "\" has been deleted.");
        }
    }
    
    public void messageDeleted(MessageWrapper messageWrapper) {
        messages.remove(messageWrapper);
    }
    
    public void toggleEdit(ActionEvent e) {
        editing = !editing;
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public Long getThreadId() {
        return wrapped.getThreadId() != null ? wrapped.getThreadId() : wrapped.getMessageId();
    }

    public void setGoTo(boolean goTo) {
        this.goTo = goTo;
    }

    public boolean isGoTo() {
        return goTo;
    }
    
    public int getMessageNum() {
        return messageNum;
    }
    
    public boolean isNewMsg() {
        return empty;
    }
    
    public boolean isOldestFirst() {
        return oldestFirst;
    }
    
    public void revertMessages(ActionEvent e) {
        Collections.reverse(messages);
        oldestFirst = !oldestFirst;
    }
}
