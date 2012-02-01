package com.ext.utils;

public class CollabMessage {
    private String message;
    private Severity severity;
    
    
    public CollabMessage(Severity severity, String message) {
        this.message = message;
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Severity getSeverity() {
        return severity;
    }


    public void setSeverity(Severity severity) {
        this.severity = severity;
    }




    public enum Severity {
        INFO,
        WARN,
        ERROR
        
    }
}
