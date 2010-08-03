package org.climatecollaboratorium.navigation;

import java.util.Map;

import org.climatecollaboratorium.events.Event;

public class NavigationEvent implements Event {
    
    private String source;
    private Map<String, String> parameters;
    
    public NavigationEvent(String source, Map<String, String> parameters) {
        this.source = source;
        this.parameters = parameters;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Map<String, String> getParameters() {
        return parameters;
    }
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    
    

}
