package org.climatecollaboratorium.models;

public class JSDataTransport {
    private String id;
    private long timestamp;
    private Object payload;
    
    public JSDataTransport(String id, long timestamp, Object payload) {
        this.id = id;
        this.timestamp = timestamp;
        this.payload = payload;
    }
    
    public JSDataTransport() {
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public Object getPayload() {
        return payload;
    }
    public void setPayload(Object payload) {
        this.payload = payload;
    }
    

}
