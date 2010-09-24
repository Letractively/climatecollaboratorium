package org.climatecollaboratorium.jsintegration;

public interface JSEventManager {
    public void sendEvent(JSEvent event);
    public void sendEvent(JSEvent event, boolean forcepush);
    public void addJsEventHandler(JSEventHandler handler, String eventName);

}