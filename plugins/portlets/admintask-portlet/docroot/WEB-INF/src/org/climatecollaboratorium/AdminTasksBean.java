package org.climatecollaboratorium;

import java.util.List;

import org.climatecollaboratorium.facelets.plans.wrappers.PlanItemWrapper;

import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;

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
}
