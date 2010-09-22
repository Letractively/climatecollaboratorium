package org.climatecollaboratorium.plans.migration;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalService;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;

public class MigrationTool {
    private static Logger _log = Logger.getLogger(MigrationTool.class);

    public String migrate() {

        try {
            int counter = 1;
            int plansCount = PlanLocalServiceUtil.getPlansCount();
            _log.info("Starting migration of plans, plans to process: " + plansCount);
            for (Plan basePlan : PlanLocalServiceUtil.getPlans(0, Integer.MAX_VALUE)) {
                try {
                    String progressIndicator = counter + " of " + plansCount + ": [" + basePlan.getName() + " (" + basePlan.getPlanId() + ")] ";

                    _log.info(progressIndicator + "migration started");
                    boolean alreadyMigrated = false;
                    try {
                        if (PlanItemLocalServiceUtil.getPlan(basePlan.getPlanId()) != null) {
                            alreadyMigrated = true;
                            _log.info(progressIndicator + "already migrated");
                        }
                    }
                    catch (NoSuchPlanItemException e) {
                        // ignore
                    }
                    if (!alreadyMigrated) {
                        PlanItemLocalServiceUtil.createPlan(basePlan);
                    }

                    _log.info(progressIndicator + "migration finished");
                    counter++;
                    FacesMessage fm = new FacesMessage();
                    fm.setSeverity(FacesMessage.SEVERITY_INFO);
                    fm.setSummary("Migration of plan " + basePlan.getName() + " was successful");
                    FacesContext.getCurrentInstance().addMessage(null, fm);
                } catch (Throwable ex) {
                    FacesMessage fm = new FacesMessage();
                    fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                    fm.setSummary("Migration of plan " + basePlan.getName() + " (" + basePlan.getPlanId()
                            + ") has failed");
                    fm.setDetail(ex.getMessage());
                    FacesContext.getCurrentInstance().addMessage(null, fm);
                    _log.error("An exception was thrown when migrating plan " + basePlan.getPlanId() + " "
                            + basePlan.getName(), ex);

                }
            }
        } catch (Throwable ex) {
            FacesMessage fm = new FacesMessage();
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fm.setSummary("Migration has failed");
            fm.setDetail(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            _log.error("Migration failed" + ex.getMessage(), ex);
            return "";
        }
        _log.info("Migration successful");
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, fm);

        return "";
    }
    
    public String migrateDiscussions() {
        try {
            int counter = 1;
            int plansCount = PlanItemLocalServiceUtil.getPlans().size(); 
            _log.info("Starting migration of discussions for plans, plans to process: " + plansCount);
            for (PlanItem basePlan : PlanItemLocalServiceUtil.getPlans()) {
                try {
                    String progressIndicator = counter + " of " + plansCount + ": [" + basePlan.getName() + " (" + basePlan.getPlanId() + ")] ";

                    _log.info(progressIndicator + "migration started");
                    
                    boolean alreadyMigrated = false;
                    try {
                        Long discussionCatId = basePlan.getMBCategoryId();
                        DiscussionCategoryGroup discussionGroup = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionCatId);
                        if (discussionGroup != null) {
                            alreadyMigrated = true;
                            _log.info(progressIndicator + "already migrated");
                        }
                    }
                    catch (NoSuchDiscussionCategoryGroupException e) {
                        // ignore
                    }
                    if (!alreadyMigrated) {
                        MBCategory mbCategory = MBCategoryLocalServiceUtil.getCategory(basePlan.getMBCategoryId());
                        DiscussionCategoryGroup categoryGroup = 
                            DiscussionCategoryGroupLocalServiceUtil.createDiscussionCategoryGroup("Category group for plan: " + basePlan.getId());
                        
                        PlanMeta meta = basePlan.getPlanMeta();
                        meta.setCategoryGroupId(categoryGroup.getId());
                        meta.store();
                        
                        // default category
                        DiscussionCategory category = categoryGroup.addCategory("General discussion", null, UserLocalServiceUtil.getUser(mbCategory.getUserId())); 
                        for (MBThread mbThread : MBThreadLocalServiceUtil.getThreads(mbCategory.getCategoryId(), 0, 10000)) {
                            DiscussionMessage thread = null;
                            for (MBMessage mbMessage: MBMessageLocalServiceUtil.getThreadMessages(mbThread.getThreadId())) {
                                if (thread == null) {
                                    thread = category.addThread(mbMessage.getSubject(), mbMessage.getBody(), UserLocalServiceUtil.getUser(mbMessage.getUserId()));
                                }
                                else {
                                    thread.addThreadMessage(mbMessage.getSubject(), mbMessage.getBody(), UserLocalServiceUtil.getUser(mbMessage.getUserId()));
                                }
                                
                            }
                        }
                    }

                    _log.info(progressIndicator + "migration finished");
                    counter++;
                    FacesMessage fm = new FacesMessage();
                    fm.setSeverity(FacesMessage.SEVERITY_INFO);
                    fm.setSummary("Migration of plan " + basePlan.getName() + " was successful");
                    FacesContext.getCurrentInstance().addMessage(null, fm);
                } catch (Throwable ex) {
                    FacesMessage fm = new FacesMessage();
                    fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                    fm.setSummary("Migration of plan " + basePlan.getName() + " (" + basePlan.getPlanId()
                            + ") has failed");
                    fm.setDetail(ex.getMessage());
                    FacesContext.getCurrentInstance().addMessage(null, fm);
                    _log.error("An exception was thrown when migrating plan " + basePlan.getPlanId() + " "
                            + basePlan.getName(), ex);

                }
            }
        } catch (Throwable ex) {
            FacesMessage fm = new FacesMessage();
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fm.setSummary("Migration has failed");
            fm.setDetail(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            _log.error("Migration failed" + ex.getMessage(), ex);
            return "";
        }
        _log.info("Migration successful");
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, fm);

        return "";
    }
    
    
    public String updateDiscussionUrlsAndDescriptions() throws SystemException, PortalException {
        for (PlanItem basePlan : PlanItemLocalServiceUtil.getPlans()) {
            DiscussionCategoryGroup categoryGroup = 
                DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(basePlan.getCategoryGroupId());
            
            categoryGroup.setDescription(basePlan.getName() + " discussion");
            categoryGroup.setUrl("/web/guest/plans#plans=planId:" + basePlan.getPlanId() + ",tab:discussion");
            categoryGroup.store();
        }
        _log.info("Update successful");
        FacesMessage fm = new FacesMessage();
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fm.setSummary("Update successful");

        FacesContext.getCurrentInstance().addMessage(null, fm);
        return null;
    }
}