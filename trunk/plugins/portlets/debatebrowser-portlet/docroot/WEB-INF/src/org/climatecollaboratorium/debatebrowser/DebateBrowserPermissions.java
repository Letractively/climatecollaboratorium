package org.climatecollaboratorium.debatebrowser;

import org.climatecollaboratorium.debatebrowser.utils.Helper;

public class DebateBrowserPermissions {
    
    public boolean getCanAddCategory() {
        return Helper.getPermissionChecker().isOmniadmin();
    }
    
    public boolean getCanEditCategory() {
        return Helper.getPermissionChecker().isOmniadmin();
    }

    public boolean getCanAddDebate() {
        return Helper.getPermissionChecker().isOmniadmin();
    }
    
}
