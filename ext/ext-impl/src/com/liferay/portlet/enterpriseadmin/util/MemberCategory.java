package com.liferay.portlet.enterpriseadmin.util;

public enum MemberCategory {
    MODERATOR("Moderator"),
    EXPERT("Experts"),
    STAFF("Staff"),
    MEMBER("User");
    
    private final String roleName;
    
    MemberCategory(String roleName) {
        this.roleName = roleName;
    }

    public String getPrintName() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
    
    public String getLowerCase() {
        return name().toLowerCase();
    }

    public String getRoleName() {
        return roleName;
    }
}
