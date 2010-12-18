package org.climatecollaboratorium.facelets.debates.test;

import java.util.ArrayList;
import java.util.List;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;

public class DebateTestBean {
    private Long debateId;
    private String debatesIds;
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();

    public Long getDebateId() {
        return debateId;
    }

    public void setDebateId(Long debateId) {
        this.debateId = debateId;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bind();
    }

    public EventBus getEventBus() {
        return eventBus;
    }
    
    private void bind() {
        for (HandlerRegistration reg: handlerRegistrations) {
            reg.unregister();
        }
        
        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {
            public void onEvent(NavigationEvent event) {
                if (event.hasSource("tagsdemo")) {
                    String debateIdStr = event.getParameters("tagsdemo").get("debateId");
                    if (debateIdStr != null) {
                        debateId = Long.parseLong(debateIdStr);
                    }
                    debatesIds = event.getParameters("tagsdemo").get("debatesIds");
                    
                }
            }
            
        }));
    }

    public String getDebatesIds() {
        return debatesIds;
    }
    

}
