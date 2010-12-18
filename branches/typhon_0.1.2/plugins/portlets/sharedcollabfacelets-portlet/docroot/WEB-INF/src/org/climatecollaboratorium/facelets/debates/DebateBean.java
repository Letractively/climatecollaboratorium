package org.climatecollaboratorium.facelets.debates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.facelets.debates.backing.DebateDetailsBean;
import org.climatecollaboratorium.facelets.debates.backing.DebateItemCommentsBean;
import org.climatecollaboratorium.facelets.debates.backing.DebatesPermissionsBean;
import org.climatecollaboratorium.facelets.debates.backing.DebatesSuggestBean;
import org.climatecollaboratorium.facelets.debates.backing.EditDebateCommentBean;
import org.climatecollaboratorium.facelets.debates.backing.EditDebateItemBean;
import org.climatecollaboratorium.navigation.NavigationEvent;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DebateBean {

    private Long lastInitDebateId;
    private Long debateId;
    private Debate debate;
    private DebateDetailsBean debateDetailsBean = new DebateDetailsBean();
    private DebateItemCommentsBean debateItemCommentsBean = new DebateItemCommentsBean();
    private EditDebateCommentBean editDebateCommentBean = new EditDebateCommentBean();
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private DebatesPermissionsBean permissionsBean = new DebatesPermissionsBean();
    private EditDebateItemBean editDebateItemBean = new EditDebateItemBean();
    private DebatesSuggestBean debatesSuggestBean = new DebatesSuggestBean();
    
    public DebateBean() {
        
        debateItemCommentsBean.setDebateDetailsBean(debateDetailsBean);
        debateItemCommentsBean.setPermissions(permissionsBean);
        
        editDebateCommentBean.setDebateDetailsBean(debateDetailsBean);
        editDebateCommentBean.setPermissions(permissionsBean);
        
        editDebateItemBean.setDebateDetailsBean(debateDetailsBean);
        editDebateItemBean.setPermissions(permissionsBean);
        
        debatesSuggestBean.setDebateDetailsBean(debateDetailsBean);
    }
    private Log _log = LogFactoryUtil.getLog(DebateBean.class);
    
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
                        try {
                            debateDetailsBean.setSelectedItemId(itemId);
                        } catch (SystemException e) {
                            _log.error("can't initialize debate details to item with id: " + itemId, e);
                        } catch (PortalException e) {
                            _log.error("can't initialize debate details to item with id: " + itemId, e);
                        }
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

    
}
