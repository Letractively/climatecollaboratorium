package org.climatecollaboratorium.navigation;

import java.util.HashMap;
import java.util.Map;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.navigation.NavigationEvent;

public class NavigationManagerBean {
    private String navigationToken;
    private EventBus eventBus;
    private boolean sendUnchanged;
    private Map<String, Map<String, String>> lastTokenMap = new HashMap<String, Map<String,String>>();
    
    public boolean isSendUnchanged() {
        return sendUnchanged;
    }
    public void setSendUnchanged(boolean sendUnchanged) {
        this.sendUnchanged = sendUnchanged;
    }
    public String getNavigationToken() {
        return navigationToken;
    }
    public void setNavigationToken(String navigationToken) {
        this.navigationToken = navigationToken;
    }
    public EventBus getEventBus() {
        return eventBus;
    }
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public String navigate() {
        Map<String, Map<String, String>> tokenMap = decodeToken(navigationToken);
        for (String key: tokenMap.keySet()) {
            if (!sendUnchanged && tokenMap.get(key).equals(lastTokenMap.get(key))) {
                continue;
            }
            eventBus.fireEvent(new NavigationEvent(key, tokenMap.get(key)));
        }
        lastTokenMap = tokenMap;
        return null;
    }
    
    private Map<String, Map<String, String>> decodeToken(String token) {
        Map<String, Map<String, String>> tokenMap = new HashMap<String, Map<String,String>>();
        String[] tokenParts = token.split(";");
        for (String tokenPart: tokenParts) {
            String[] keyValue = tokenPart.split("=");

            Map<String, String> tokenParametersMap = new HashMap<String, String>();
            tokenMap.put(keyValue[0], tokenParametersMap);
            if (keyValue.length < 2) {
                continue;
            }
            
            String[] tokenParameters = keyValue[1].split(",");
            for (String tokenParameter: tokenParameters) {
                String[] paramKeyValue = tokenParameter.split(":");
                if (paramKeyValue.length < 2) {
                    continue;
                }
                tokenParametersMap.put(paramKeyValue[0], paramKeyValue[1]);
            }
        }
        
        return tokenMap;
    }
}
