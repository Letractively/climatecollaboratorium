package org.climatecollaboratorium.debatebrowser;

import org.climatecollaboratorium.debatebrowser.utils.Helper;

public class DebateBrowserPermissions {
    
    private final String CAN_ADMIN_ACTION = "DEBATES_CAN_ADMIN";
    
    public DebateBrowserPermissions() {
        
    }
    
    public boolean getCanAddCategory() {
        return Helper.getPermissionChecker().hasPermission(Helper.getGroupId(), Helper.getRootPortletId(), 
                Helper.getPrimKey(), CAN_ADMIN_ACTION) || Helper.getPermissionChecker().isOmniadmin();
    }
    
    public boolean getCanEditCategory() {
        return Helper.getPermissionChecker().hasPermission(Helper.getGroupId(), Helper.getRootPortletId(), 
                Helper.getPrimKey(), CAN_ADMIN_ACTION) || Helper.getPermissionChecker().isOmniadmin();
    }

    public boolean getCanAddDebate() {
        return Helper.getPermissionChecker().hasPermission(Helper.getGroupId(), Helper.getRootPortletId(), 
                Helper.getPrimKey(), CAN_ADMIN_ACTION) || Helper.getPermissionChecker().isOmniadmin();
    }
    
}
