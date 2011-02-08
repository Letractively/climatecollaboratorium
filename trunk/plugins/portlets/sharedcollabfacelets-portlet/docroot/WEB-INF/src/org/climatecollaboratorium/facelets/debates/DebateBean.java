package org.climatecollaboratorium.facelets.debates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.facelets.debates.backing.DebateDetailsBean;
import org.climatecollaboratorium.facelets.debates.backing.DebateItemCommentsBean;
import org.climatecollaboratorium.facelets.debates.backing.DebatesPermissionsBean;
import org.climatecollaboratorium.facelets.debates.backing.DebatesSuggestBean;
import org.climatecollaboratorium.facelets.debates.backing.EditDebateCommentBean;
import org.climatecollaboratorium.facelets.debates.backing.EditDebateItemBean;
import org.climatecollaboratorium.facelets.debates.support.DebateItemWrapper;
import org.climatecollaboratorium.facelets.discussions.DiscussionPageType;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DebateBean {

    private Long lastInitDebateId;
    private Long debateId;
    private Debate debate;
    private DebateItemWrapper currentItem;
    private boolean showAdvanced;
    
    private DebateDetailsBean debateDetailsBean = new DebateDetailsBean();
    private DebateItemCommentsBean debateItemCommentsBean = new DebateItemCommentsBean();
    private EditDebateCommentBean editDebateCommentBean = new EditDebateCommentBean();
    private AddEditDebateItemBean addEditDebateItemBean = new AddEditDebateItemBean();
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private DebatesPermissionsBean permissionsBean = new DebatesPermissionsBean();
    private EditDebateItemBean editDebateItemBean = new EditDebateItemBean();
    private DebatesSuggestBean debatesSuggestBean = new DebatesSuggestBean();
    private DebatePageType pageType = DebatePageType.WELCOME;
    
    public DebateBean() {
        
        debateItemCommentsBean.setDebateDetailsBean(debateDetailsBean);
        debateItemCommentsBean.setPermissions(permissionsBean);
        
        editDebateCommentBean.setDebateDetailsBean(debateDetailsBean);
        editDebateCommentBean.setPermissions(permissionsBean);
        
        editDebateItemBean.setDebateDetailsBean(debateDetailsBean);
        editDebateItemBean.setPermissions(permissionsBean);
        
        debatesSuggestBean.setDebateDetailsBean(debateDetailsBean);
        
        addEditDebateItemBean.setPermissions(permissionsBean);
        addEditDebateItemBean.setDebateBean(this);
    }
    private Log _log = LogFactoryUtil.getLog(DebateBean.class);
    private boolean editing;
    
    public boolean init(Long debateId) {
        if ((debateId == null && lastInitDebateId == debateId) || (debateId != null && debateId.equals(lastInitDebateId))) {
            return debate != null;
        }
        
        lastInitDebateId = debateId;
        this.debateId = debateId;
        if (debateId == null) {
            debate = null;
        } 
        else {
            debate = DebateLocalServiceUtil.findLastVersion(debateId);
            if (debate != null) {
                try {
                    debateDetailsBean.setDebateId(debateId);
                } catch (SystemException e) {
                    _log.error("Can't initialize debate bean for debate id: " + debateId, e);
                }
            }
        }
        
        return debate != null;
    }

    public void setDebateId(Long debateId) {
        this.debateId = debateId;
    }

    public Long getDebateId() {
        return debateId;
    }

    public DebateDetailsBean getDebateDetailsBean() {
        return debateDetailsBean;
    }

    public Debate getDebate() {
        return debate;
    }
    
    public String getDebateName() {
        return debate.getCurrentRoot().getDebateSummary();
    }

    public DebateItemCommentsBean getDebateItemCommentsBean() {
        return debateItemCommentsBean;
    }
    
    public EditDebateCommentBean getEditDebateCommentBean() {
        return editDebateCommentBean;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bindEvents();
    }

    public EventBus getEventBus() {
        return eventBus;
    }
    
    private void bindEvents() {
        for (HandlerRegistration reg: handlerRegistrations) {
            reg.unregister();
        }
        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                if (event.hasSource("debate")) {
                    Map<String, String> parameters = event.getParameters("debate");
                    if (parameters.containsKey("itemId")) {
                        Long itemId = Long.parseLong(parameters.get("itemId"));
                        currentItem = new DebateItemWrapper(DebateItemLocalServiceUtil.getLastItem(itemId));
                        pageType = DebatePageType.ITEM_DETAILS;
                        try {
                            debateDetailsBean.setSelectedItemId(itemId);
                        } catch (SystemException e) {
                            _log.error("can't initialize debate details to item with id: " + itemId, e);
                        } catch (PortalException e) {
                            _log.error("can't initialize debate details to item with id: " + itemId, e);
                        }
                    }
                    else {
                        pageType = DebatePageType.WELCOME;
                    }
                }
                
            }
            
        }
        ));
    }

    public DebatesPermissionsBean getDebatesPermissionsBean() {
        return permissionsBean;
    }
    
    public EditDebateItemBean getEditDebateItemBean() {
        return editDebateItemBean;
    }

    public DebatesSuggestBean getDebatesSuggestBean() {
        return debatesSuggestBean;
    }

    public DebatePageType getPageType() {
        return pageType;
    }
    public DebateItemWrapper getCurrentItem() {
        return currentItem;
    }

    public void toggleAdvanced(ActionEvent e) {
        showAdvanced = !showAdvanced;
        
    }

    public boolean isShowAdvanced() {
        return currentItem != null && showAdvanced;
    }
    
    public void toggleEditing(ActionEvent e) throws SystemException {
        editing = !editing;
        if (currentItem != null) {
            addEditDebateItemBean.setEditing(true);
            addEditDebateItemBean.setItem(currentItem.getItem());
            if (editing) {
                pageType = DebatePageType.ITEM_EDIT;
            }
            else {
                pageType = DebatePageType.ITEM_DETAILS;
            }
        }
    }
    
    public void addDebateItem(ActionEvent e) {
        DebateItemType type = DebateItemType.valueOf(e.getComponent().getAttributes().get("type").toString());
        
        addEditDebateItemBean.addDebateItem(type);
        editing = true;
        pageType = DebatePageType.ITEM_EDIT;
        
    }
    

    public void debateItemRemoved(DebateItem item) {
        // TODO Auto-generated method stub

    }

    public boolean isSubscribed() throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {

            return ActivitySubscriptionLocalServiceUtil.isSubscribed(Helper.getLiferayUser().getUserId(), Debate.class,
                    debateId, null, currentItem != null ? DebatesUtil.getActivityExtraData(currentItem.getItem()) : "");

        }
        return false;
    }

    public void subscribe(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            if (isSubscribed()) {
                ActivitySubscriptionLocalServiceUtil.deleteSubscription(Helper.getLiferayUser().getUserId(), Debate.class,
                    debateId, null, currentItem != null ? DebatesUtil.getActivityExtraData(currentItem.getItem()) : "");
            }
            else {
                ActivitySubscriptionLocalServiceUtil.addSubscription(Debate.class, debateId, 
                        null, currentItem != null ? DebatesUtil.getActivityExtraData(currentItem.getItem()) : "", Helper.getLiferayUser().getUserId());
            }
        }
    }

    public void debateItemUpdated(DebateItem savedItem) throws SystemException {
        toggleEditing(null);
        
    }

    public void debateItemAdded(DebateItem savedItem) throws SystemException {
        toggleEditing(null);
        debate = DebateLocalServiceUtil.findLastVersion(debate.getDebateId());
    }
    
    public AddEditDebateItemBean getAddEditBean() {
        return addEditDebateItemBean;
    }

    
}
