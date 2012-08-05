package org.climatecollaboratorium;

import java.util.List;

import org.climatecollaboratorium.facelets.plans.wrappers.PlanItemWrapper;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.admin.action.ActionUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.permission.WikiPagePermission;

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
        
    	for (WikiPage wp: WikiPageLocalServiceUtil.getWikiPages(0,  Integer.MAX_VALUE)) {
            PermissionLocalServiceUtil.setRolePermissions(guest.getRoleId(), companyId,
                    WikiPage.class.getName(), ResourceConstants.SCOPE_GROUP,
                    String.valueOf(wp.getResourcePrimKey()), guestActions);
    	}
    	return null;
    }
}
