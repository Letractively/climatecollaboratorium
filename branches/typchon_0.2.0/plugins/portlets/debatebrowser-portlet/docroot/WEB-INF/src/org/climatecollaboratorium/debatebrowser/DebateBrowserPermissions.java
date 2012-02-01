package org.climatecollaboratorium.debatebrowser;

import org.climatecollaboratorium.debatebrowser.utils.Helper;

public class DebateBrowserPermissions {
    
    private final String CAN_ADMIN_ACTION = "DEBATES_CAN_ADMIN";
    private final String primKey;
    private final String rootPortletId;
    private final Long groupId;
    
    public DebateBrowserPermissions() {
        groupId = Helper.getGroupId();
        rootPortletId = Helper.getRootPortletId();
        primKey = Helper.getPrimKey();
    }
    
    public boolean getCanAddCategory() {
        return Helper.getPermissionChecker().hasPermission(groupId, rootPortletId, primKey, CAN_ADMIN_ACTION) || 
        Helper.getPermissionChecker().isOmniadmin();
    }
    
    public boolean getCanEditCategory() {
        return Helper.getPermissionChecker().hasPermission(groupId, rootPortletId, 
                primKey, CAN_ADMIN_ACTION) || Helper.getPermissionChecker().isOmniadmin();
    }

    public boolean getCanAddDebate() {
        return Helper.getPermissionChecker().hasPermission(groupId, rootPortletId, 
                primKey, CAN_ADMIN_ACTION) || Helper.getPermissionChecker().isOmniadmin();
    }
    
}
