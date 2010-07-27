package org.climatecollaboratorium.facelets.discussions.permissions;

import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.Resource;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;

public class DefaultDiscussionsPermissionsImpl implements DiscussionsPermissions {
    
    private static final String RESOURCE_NAME = DiscussionCategoryGroup.class.getName();
    private static final int RESOURCE_SCOPE = 1;
    private static final Long companyId = Helper.getThemeDisplay().getCompanyId();
    private String primKey;
    private Resource resource = null;
    private Long groupId = Helper.getThemeDisplay().getScopeGroupId();
    
    public DefaultDiscussionsPermissionsImpl(DiscussionBean discussionBean) throws PortalException, SystemException {
        primKey = discussionBean.getDiscussion().getId().toString();
        resource = ResourceLocalServiceUtil.getResource(companyId, RESOURCE_NAME, RESOURCE_SCOPE, primKey);
    }
    
    @Override
    public boolean getCanAddCategory() {
        return getCanAdmin() || getCanAdminCategories() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_CATEGORY.name());
    }
    
    @Override
    public boolean getCanAddThread() {
        return getCanAdmin() || getCanAdminMessages() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_THREAD.name());
    }
    
    @Override
    public boolean getCanAddMessage() {
        return getCanAdmin() || getCanAdminMessages() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_MESSAGE.name());
    }
    
    @Override
    public boolean getCanAdminMessages() {
        return getCanAdmin() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN_MESSAGES.name());
    }
     
    @Override
    public boolean getCanAdminCategories() {
        return getCanAdmin() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN_CATEGORIES.name());
    }
    
    @Override
    public boolean getCanAdmin() {
        return permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN.name()) || permCheck().isCommunityAdmin(groupId) || permCheck().isCompanyAdmin();
    }

    private PermissionChecker permCheck() {
        return Helper.getPermissionChecker();
    }
}
