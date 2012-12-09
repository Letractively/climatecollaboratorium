package org.climatecollaboratorium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.NoSuchResourceException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

public class AdminTasksBean {

    public String removeBlogsFormatting() throws SystemException {
        for (BlogsEntry entry: BlogsEntryLocalServiceUtil.getBlogsEntries(0, Integer.MAX_VALUE)) {
            
            String content = entry.getContent();
            
            content = ContentFilteringHelper.removeStylingFromHTMLContent(content);
            
            entry.setContent(content);
            BlogsEntryLocalServiceUtil.updateBlogsEntry(entry);
        }
        return null;
    }
    
    public String removeProposalFormatting() throws SystemException {
        for (PlanItem plan: PlanItemLocalServiceUtil.getPlans()) {

            String description = plan.getDescription();
            if (description != null) {
                description = ContentFilteringHelper.removeStylingFromHTMLContent(description);
            }
            List<PlanDescription> descriptions = plan.getPlanDescriptions();
            PlanDescription planDescriptionLast = descriptions.get(0);
            planDescriptionLast.setDescription(description);
            planDescriptionLast.store();
        }
        return null;
    }
    
    public String updatePitchForProposals() throws SystemException {
        for (PlanItem plan: PlanItemLocalServiceUtil.getPlans()) {
            String pitch = plan.getPitch();
            if (pitch == null || pitch.trim().length() == 0) {
                PlanAttribute attr = plan.getPlanAttribute(PlanConstants.Attribute.ABSTRACT.name());
                if (attr != null && attr.getAttributeValue() != null) {
                    List<PlanDescription> descriptions = plan.getPlanDescriptions();
                    PlanDescription planDescriptionLast = descriptions.get(0);
                    System.out.println("Updating plan: " + plan.getPlanId());
                    if (plan.getPlanId().compareTo(15207L) == 0) {
                        System.out.println(attr.getAttributeValue());
                    }
                    planDescriptionLast.setPitch(attr.getAttributeValue());
                    planDescriptionLast.store();
                }
            }
        }
        return null;
    }
    
    
    public String populateFirstEmptySectionWithDescription() throws SystemException, PortalException {
        for (PlanItem plan: PlanItemLocalServiceUtil.getPlans()) {
            List<PlanSection> sections = plan.getPlanSections();
            if (sections == null || sections.isEmpty()) continue;
            
            // ignore plans where first section isn't empty
            if (sections.get(0).getContent() == null && sections.get(0).getContent().trim().length() > 0) continue;
            
            // ignore plans with empty description
            if (plan.getDescription() == null || plan.getDescription().trim().length() == 0) continue;
            
            plan.setSectionContent(sections.get(0).getDefinition(), plan.getDescription(), null, plan.getAuthorId());
        }
        return null;
    }

    private final long defaultCompanyId = 10112L;
    public String fixWikiPermissions() throws SystemException, PortalException {
    	
    	Long companyId = defaultCompanyId;
        Role guest = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
        String[] guestActions = { ActionKeys.VIEW };
        
        int idx = 0;
        int total = WikiPageLocalServiceUtil.getWikiPagesCount();
        
        String[] actionIds = {ActionKeys.VIEW};
    	for (WikiPage wp: WikiPageLocalServiceUtil.getWikiPages(0,  Integer.MAX_VALUE)) {
    		idx++;
    		System.out.println(idx + " of " + total);
    		
    		Resource resource = null;

    		try {
    	        resource = ResourceLocalServiceUtil.getResource(defaultCompanyId, 
    	        		WikiPage.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(wp.getResourcePrimKey()));
    		}
    		catch (NoSuchResourceException nsre) {
    			System.out.println("Can't find resource for page: " + wp.getPageId());
    			resource = ResourceLocalServiceUtil.addResource(defaultCompanyId, WikiPage.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(wp.getResourcePrimKey()));
    			System.out.println("New resource created: " + resource.getResourceId());
    			ResourceLocalServiceUtil.updateResource(resource);
    		}

            /*PermissionLocalServiceUtil.setRolePermissions(guest.getRoleId(), companyId,
                    WikiPage.class.getName(), ResourceConstants.SCOPE_COMPANY,
                    String.valueOf(wp.getResourcePrimKey()), guestActions);
                    */
    		PermissionLocalServiceUtil.setRolePermissions(guest.getRoleId(), actionIds, resource.getResourceId()); 
            
			/*PermissionServiceUtil.setRolePermissions(
					guest.getRoleId(), wp.getGroupId(), actionIds,
					resource.getResourceId());
					*/
    	}
    	return null;
    }
    
    public String fixContestsDiscussionPermissions() throws SystemException, PortalException {
    	for (Contest contest: ContestLocalServiceUtil.getContests(0,  Integer.MAX_VALUE)) {
    		try {
    			Group g = GroupLocalServiceUtil.getGroup(contest.getGroupId());
    		}
    		catch (Exception e) {
    			contest.setGroupId(0L);
    			contest.store();
    		}
    	
    	}
    	
    	ContestLocalServiceUtil.updateContestGroupsAndDiscussions();


    	Long companyId = defaultCompanyId;
        Role owner = RoleLocalServiceUtil.getRole(companyId, RoleConstants.COMMUNITY_OWNER);
        Role admin = RoleLocalServiceUtil.getRole(companyId, RoleConstants.COMMUNITY_ADMINISTRATOR);
        Role member = RoleLocalServiceUtil.getRole(companyId, RoleConstants.COMMUNITY_MEMBER);
        Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
        Role guest = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
        Role moderator = RoleLocalServiceUtil.getRole(companyId, "Moderator");

        String[] ownerActions = { DiscussionActions.ADMIN.name(), DiscussionActions.ADD_CATEGORY.name(),
                DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADMIN_CATEGORIES.name(), DiscussionActions.ADMIN_MESSAGES.name(),
                DiscussionActions.ADD_COMMENT.name() };

        String[] adminActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] moderatorActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] memberActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] userActions = { DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADD_COMMENT.name() };

        String[] guestActions = {};

        Map<Role, String[]> rolesActionsMap = new HashMap<Role, String[]>();

        rolesActionsMap.put(owner, ownerActions);
        rolesActionsMap.put(admin, adminActions);
        rolesActionsMap.put(member, memberActions);
        rolesActionsMap.put(userRole, userActions);
        rolesActionsMap.put(guest, guestActions);
        rolesActionsMap.put(moderator, moderatorActions);
    	
    	for (Contest contest: ContestLocalServiceUtil.getContests(0,  Integer.MAX_VALUE)) {
            for (Role role : rolesActionsMap.keySet()) {
            	
                PermissionLocalServiceUtil.setRolePermissions(role.getRoleId(), companyId,
                        DiscussionCategoryGroup.class.getName(), ResourceConstants.SCOPE_GROUP,
                        String.valueOf(contest.getGroupId()), rolesActionsMap.get(role));
            }
    	
    	}
    	
    	return null;
    	
    }
    
    public String fixResourceReferencesInPermissions() throws SystemException, PortalException {
    	
    	for (Permission p: PermissionLocalServiceUtil.getPermissions(0,  Integer.MAX_VALUE)) {
    		try {
    			ResourceLocalServiceUtil.getResource(p.getResourceId());
    		}
    		catch (NoSuchResourceException e) {
    			PermissionLocalServiceUtil.deletePermission(p);
    		}
    	}
    	
    	
    	
    	return null;
    }
    
    
    
}
