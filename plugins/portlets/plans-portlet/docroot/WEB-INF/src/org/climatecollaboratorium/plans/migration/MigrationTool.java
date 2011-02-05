package org.climatecollaboratorium.plans.migration;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;
import com.ext.portlet.models.ui.ModelInputIndividualDisplayItem;
import com.ext.portlet.models.ui.ModelWidgetProperty;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import org.apache.log4j.Logger;
import org.climatecollaboratorium.utils.Helper;
import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.Variable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MigrationTool {
    private static Logger _log = Logger.getLogger(MigrationTool.class);


    private long generalGroup = 701L;
    private long ADMIN = 10144L;

    public Long getMbCategoryId() {
        return mbCategoryId;
    }

    public void setMbCategoryId(Long mbCategoryId) {
        this.mbCategoryId = mbCategoryId;
    }

    private Long mbCategoryId = null;

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

    public String migrateGeneralDiscussions() {
        FacesMessage fm = new FacesMessage();
        try {
            MBCategory mbCategory = MBCategoryLocalServiceUtil.getCategory(mbCategoryId);
            if (mbCategory == null) {
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                fm.setSummary("Could not get message category " + mbCategoryId);
            } else {
                DiscussionCategoryGroup categoryGroup = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(generalGroup);
                if (categoryGroup == null) {
                    categoryGroup = DiscussionCategoryGroupLocalServiceUtil.createDiscussionCategoryGroup(generalGroup);
                }
                DiscussionCategory category = categoryGroup.addCategory("General", null, UserLocalServiceUtil.getUser(ADMIN));
                for (MBThread mbThread : MBThreadLocalServiceUtil.getThreads(mbCategory.getCategoryId(), 0, 10000)) {
                    DiscussionMessage thread = null;
                    for (MBMessage mbMessage : MBMessageLocalServiceUtil.getThreadMessages(mbThread.getThreadId())) {
                        if (thread == null) {
                            thread = category.addThread(mbMessage.getSubject(), mbMessage.getBody(), UserLocalServiceUtil.getUser(mbMessage.getUserId()));
                        } else {
                            thread.addThreadMessage(mbMessage.getSubject(), mbMessage.getBody(), UserLocalServiceUtil.getUser(mbMessage.getUserId()));
                        }

                    }
                }
                fm.setSeverity(FacesMessage.SEVERITY_INFO);
                fm.setSummary("Success! We have won!");
            }


        } catch (NoSuchDiscussionCategoryGroupException e) {
               fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                fm.setSummary("Could not get message category " + mbCategoryId);
                fm.setDetail(e.getMessage());
        } catch (SystemException e) {
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
                fm.setSummary("Wicked bummer");
                fm.setDetail(e.getMessage());
        } catch (PortalException e) {
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
                fm.setSummary("Wicked bummer");
                fm.setDetail(e.getMessage());
        }
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
                        DiscussionCategoryGroup categoryGroup =
                                DiscussionCategoryGroupLocalServiceUtil.createDiscussionCategoryGroup("Category group for plan: " + basePlan.getId());

                        //  default category
                        DiscussionCategory category = categoryGroup.addCategory("General discussion", null, UserLocalServiceUtil.getUser(basePlan.getAuthorId()));

                        PlanMeta meta = basePlan.getPlanMeta();
                        meta.setCategoryGroupId(categoryGroup.getId());
                        meta.store();
                        try {

                            MBCategory mbCategory = MBCategoryLocalServiceUtil.getCategory(basePlan.getMBCategoryId());

                            for (MBThread mbThread : MBThreadLocalServiceUtil.getThreads(mbCategory.getCategoryId(), 0, 10000)) {
                                DiscussionMessage thread = null;
                                for (MBMessage mbMessage : MBMessageLocalServiceUtil.getThreadMessages(mbThread.getThreadId())) {
                                    if (thread == null) {
                                        thread = category.addThread(mbMessage.getSubject(), mbMessage.getBody(), UserLocalServiceUtil.getUser(mbMessage.getUserId()));
                                    } else {
                                        thread.addThreadMessage(mbMessage.getSubject(), mbMessage.getBody(), UserLocalServiceUtil.getUser(mbMessage.getUserId()));
                                    }

                                }
                            }
                        }
                        catch (Exception e) {
                            _log.error("Error when migrating discussion for plan: " + basePlan.getPlanId());
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
            categoryGroup.setUrl("/web/guest/plans/-/plans/contestId/" + basePlan.getContest().getContestPK() + 
                    "/planId/" + basePlan.getPlanId() + "#plans=tab:comments");
            categoryGroup.store();
        }
        _log.info("Update successful");
        FacesMessage fm = new FacesMessage();
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fm.setSummary("Update successful");

        FacesContext.getCurrentInstance().addMessage(null, fm);
        return null;
    }

    private static final String RESOURCE_NAME = DiscussionCategoryGroup.class.getName();
    private static final int SCOPE = ResourceConstants.SCOPE_GROUP;

    public String updateDiscussionsPermissions() throws SystemException, PortalException {
        String[] supportedRolesNames = {RoleConstants.COMMUNITY_OWNER,
                RoleConstants.COMMUNITY_ADMINISTRATOR,
                RoleConstants.COMMUNITY_MEMBER,
                RoleConstants.USER,
                RoleConstants.GUEST
        };
        Long companyId = Helper.getThemeDisplay().getCompanyId();

        Role owner = RoleLocalServiceUtil.getRole(companyId, RoleConstants.COMMUNITY_OWNER);
        Role admin = RoleLocalServiceUtil.getRole(companyId, RoleConstants.COMMUNITY_ADMINISTRATOR);
        Role member = RoleLocalServiceUtil.getRole(companyId, RoleConstants.COMMUNITY_MEMBER);
        Role user = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
        Role guest = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);

        String[] ownerActions = {DiscussionActions.ADMIN.name(), DiscussionActions.ADD_CATEGORY.name(),
                DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name()
        };

        String[] adminActions = {DiscussionActions.ADMIN.name(), DiscussionActions.ADD_CATEGORY.name(),
                DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name()
        };

        String[] memberActions = {DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name()
        };

        String[] userActions = {DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADD_COMMENT.name()};

        String[] guestActions = {};

        Map<Role, String[]> rolesActionsMap = new HashMap<Role, String[]>();

        rolesActionsMap.put(owner, ownerActions);
        rolesActionsMap.put(admin, adminActions);
        rolesActionsMap.put(member, memberActions);
        rolesActionsMap.put(user, userActions);
        rolesActionsMap.put(guest, guestActions);


        for (PlanItem plan : PlanItemLocalServiceUtil.getPlans()) {
            for (Role role : rolesActionsMap.keySet()) {
                PermissionLocalServiceUtil.setRolePermissions(role.getRoleId(), companyId, RESOURCE_NAME, SCOPE,
                        plan.getPlanGroupId().toString(), rolesActionsMap.get(role));

            }
        }

        _log.info("Update successful");
        FacesMessage fm = new FacesMessage();
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fm.setSummary("Update successful");

        FacesContext.getCurrentInstance().addMessage(null, fm);

        return null;
    }

    public String updatePlanOpenAttribute() throws SystemException {

        for (PlanItem basePlan : PlanItemLocalServiceUtil.getPlans()) {
            basePlan.updateAttribute(Attribute.IS_PLAN_OPEN.name());
            basePlan.updateAttribute(Attribute.SUPPORTERS.name());
        }
        FacesMessage fm = new FacesMessage();
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fm.setSummary("Update successful");

        FacesContext.getCurrentInstance().addMessage(null, fm);

        return null;
    }


    public String migrateWiki() throws SystemException {
        Pattern pattern = Pattern.compile("plan", Pattern.CASE_INSENSITIVE);
        int pagesCount = 0;
        for (WikiPage page : WikiPageLocalServiceUtil.getWikiPages(0, Integer.MAX_VALUE)) {
            if (!page.isHead()) {
                continue;
            }

            Matcher matcher = pattern.matcher(page.getContent());

            int occurrences = 0;
            int occurrencesUppercase = 0;
            while (matcher.find()) {
                boolean isUpper = false;
                occurrences++;
                if (matcher.group().startsWith("P")) {
                    isUpper = true;
                    occurrencesUppercase++;
                }


            }

            if (occurrences > 0) {
                pagesCount++;
                System.out.println(pagesCount + ": " + page.getTitle() + "\t" + page.isHead() + "\toccurrences: " + occurrences + "\toccurrencesUppercase: " + occurrencesUppercase);
            }

            String content = replaceUnwantedStrings(page.getContent());
            String title = replaceUnwantedStrings(page.getTitle());
            String parentTitle = replaceUnwantedStrings(page.getParentTitle());


            page.setTitle(title);
            page.setContent(content);
            page.setParentTitle(parentTitle);


            WikiPageLocalServiceUtil.updateWikiPage(page);


        }
        /*
        for (WikiPageResource pageRes: WikiPageResourceLocalServiceUtil.getWikiPageResources(0, Integer.MAX_VALUE)) {
            pageRes.setTitle(replacePlanOccurrences(pageRes.getTitle()));
        }
        */
        return null;
    }

    private String replaceUnwantedStrings(String baseStr) {
        String ret = baseStr;
        ret = ret.replaceAll("plan", "proposal");
        ret = ret.replaceAll("Plan", "Proposal");
        ret = ret.replaceAll("cognosis.mit.edu:8888", "climatecolab.org");

        return ret;

    }
    

    public String setIntervals() throws SystemException {
        for (ModelInputItem modelInput: ModelInputItemLocalServiceUtil.getModelInputItems(0, Integer.MAX_VALUE)) {
            Map<String, String> props = modelInput.getPropertyMap();
            if (props.size() > 0) {
                props.put(ModelWidgetProperty.Slider.INTERVAL.name(), "5");
                modelInput.saveProperties(props);
            }
            
        }
        FacesMessage fm = new FacesMessage();
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fm.setSummary("Update successful");
        
        
        return null;
    }
    
    public String updateOldPlanVotes() throws SystemException {
        for (PlanVote planVote: PlanVoteLocalServiceUtil.getPlanVotes(0, Integer.MAX_VALUE)) {
            if (planVote.getContestId() == null || planVote.getContestId().equals(0L)) {
                planVote.setContestId(1L);
                planVote.store();
            }
        }
        FacesMessage fm = new FacesMessage();
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fm.setSummary("Update successful");
        return null;
    }
    

    public String rerunPlansSimulations() throws SystemException, com.liferay.portal.PortalException, java.io.IOException, 
	mit.simulation.climate.client.comm.ModelNotFoundException {
	mit.simulation.climate.client.comm.ClientRepository repository = com.ext.portlet.models.CollaboratoriumModelingService.repository();

        for (PlanItem basePlan : PlanItemLocalServiceUtil.getPlans()) {
		if (basePlan.getContest().getContestPK().equals(2L) && basePlan.getScenarioId() != null && basePlan.getScenarioId() > 0) {
		_log.info("reruning simulation for plan: " + basePlan.getPlanId() + "\t" + basePlan.getName());
		// rerun only plans that belong to cancun contest
		try {
			Scenario scenario = repository.getScenario(basePlan.getScenarioId());
			Map<Long, Object> values = new HashMap<Long, Object>();
        	        for (Variable v: scenario.getInputSet()) {
                	    values.put(v.getMetaData().getId(), v.getValue().get(0).getValues()[0]);
	                }
			Scenario newScenario = repository.runModel(scenario.getSimulation(), values, 10144L, true);
		        FacesMessage fm = new FacesMessage();
		        fm.setSeverity(FacesMessage.SEVERITY_INFO);
		        fm.setSummary("Update successful");

		        FacesContext.getCurrentInstance().addMessage(null, fm);
			_log.info("rerun successful");
		}	
		catch (Exception e) {
			_log.error("Exception thrown when reruning plan: " + basePlan.getPlanId());
			FacesMessage fm = new FacesMessage();
		        fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		        fm.setSummary("Error when reruning plan " + basePlan.getName());
        		FacesContext.getCurrentInstance().addMessage(null, fm);
		}

			
	}

    }
        return null;
	}
    
    public String updateUrlsInErrors() throws SystemException {
        for (PlanAttribute attr: PlanAttributeLocalServiceUtil.getPlanAttributes(0, Integer.MAX_VALUE)) {
            String val = attr.getAttributeValue();
            if (val.contains("climatecollaboratorium.org")) {
                val = val.replaceAll("climatecollaboratorium\\.org", "climatecolab.org");
                attr.setAttributeValue(val);
                PlanAttributeLocalServiceUtil.updatePlanAttribute(attr);
            }
            // add space after a comma
            if (val.contains(",")) {
                val = val.replaceAll(",", ", ");
                attr.setAttributeValue(val);
                PlanAttributeLocalServiceUtil.updatePlanAttribute(attr);
            }
        }
        return null;
    }
}
