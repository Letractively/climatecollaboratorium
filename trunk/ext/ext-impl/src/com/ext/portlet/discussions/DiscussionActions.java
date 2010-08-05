package com.ext.portlet.discussions;


public enum DiscussionActions {
    ADD_CATEGORY("Add category"),
    ADD_THREAD("Add thread"),
    ADD_MESSAGE("Add message"),
    ADMIN_CATEGORIES("Admin categories"),
    ADMIN_MESSAGES("Admin messages"),
    ADMIN("Admin");
    
    private final String printName;
    
    DiscussionActions(String printName) {
        this.printName = printName;
    }
    
    public String getPrintName() {
        return printName;
    }
    
}
