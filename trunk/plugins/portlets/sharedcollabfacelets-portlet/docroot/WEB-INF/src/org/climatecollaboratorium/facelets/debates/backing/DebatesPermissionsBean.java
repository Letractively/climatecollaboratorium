package org.climatecollaboratorium.facelets.debates.backing;


import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplayFactory;

public class DebatesPermissionsBean {
    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final long groupId;
    private final String primKey;
    
    public DebatesPermissionsBean() {
        permissionChecker = Helper.getPermissionChecker();
        groupId = Helper.getThemeDisplay().getScopeGroupId();
        // to reuse values from debates portlet
        //primKey = Helper.getThemeDisplay().getPortletDisplay().getResourcePK();
        //portletId = Helper.getThemeDisplay().getPortletDisplay().getRootPortletId();
        primKey = Helper.getPrimKey();
        //groupId = Helper.getGroupId();
        portletId = Helper.getRootPortletId();
            
    }
    
    public boolean getCanVote() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, DebatesActions.CAN_VOTE);
    }
    
    public boolean getCanView() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, DebatesActions.CAN_VIEW);
    }
    
    public boolean getCanAddComment() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, DebatesActions.CAN_ADD_COMMENT);
    }
    
    public boolean getCanEditDebateMap() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, DebatesActions.CAN_EDIT_DEBATE_MAP);
    }

    public boolean getCanSuggestEdits() {
     return permissionChecker.hasPermission(groupId, portletId, primKey, DebatesActions.CAN_SUGGEST_EDITS);
    }

    public boolean getShouldSuggestEdits() {
        return getCanSuggestEdits() && (! (getCanAdmin() || getCanEditDebateMap()));
    }
    
    public boolean getCanAdmin() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, DebatesActions.CAN_ADMIN);
    }
    
    public boolean getCanDeleteComment() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, DebatesActions.CAN_ADMIN);
    }

    public boolean getCanSubscribe() {
        return Helper.isUserLoggedIn();
    }
}
