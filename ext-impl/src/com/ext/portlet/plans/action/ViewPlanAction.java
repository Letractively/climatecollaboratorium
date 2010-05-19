/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.action;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.models.action.ModelLocalServiceHelper;
import com.ext.portlet.plans.NoSuchPlanPositionException;
import com.ext.portlet.plans.PlanActivityKeys;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.MembershipRequestImpl;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Class responsible for processing View Plan action. This action requires
 * "planId" parameter to be set to ID of existing plan. If it isn't then
 * appropriate message will be displayed to the user on top of View Plans page.
 * If passed plan ID is correct then request is populated with necessary values.
 *
 * version 1.1: added retrieval of positions, changed model from standard
 *              article to id of entity provided by external service
 * version 1.2: added support for plan votes
 *
 * @author janusz.p, janusz.p
 *
 * @version 1.2
 * @since 1.0
 */
public class ViewPlanAction extends PortletAction {

    /**
     * Represents string denoting "begin" of plan's summary (first paragraph).
     */
    public static final String PLAN_SUMMARY_BEGIN = "<p>";

    /**
     * Represents string denoting "end" of plan's summary (first paragraph).
     */
    public static final String PLAN_SUMMARY_END = "</p>";

    /**
     * Represents parameter that should be passed to UserLocalService when
     * counting plan group members.
     */
    public static final String COUNT_GROUP_USERS_PARAMETER = "usersGroups";

    /**
     * Represents regular expression that will be used to remove HTML tags from
     * plan summary.
     */
    public static final String STRIP_HTML_TAGS_REGEXP = "\\<.*?>";

    /**
     * Method checks if planId parameter is set and if it holds valid Plan ID.
     *
     * If everything is correct then request gets populated with plan, summary,
     * external URLs, model content and plan membership counters.
     *
     * @param mapping
     *            action mapping
     * @param form
     *            action form
     * @param portletConfig
     *            portlet configuration
     * @param renderRequest
     *            render request
     * @param renderResponse
     *            render response
     * @return action forward for page that should be displayed
     * @throws Exception
     *             in case of any error
     */
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
        RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        // check if planId has been passed and if it represents correct value
        Plan plan = null;
        try {
            long planId = ParamUtil.getLong(renderRequest, PlanConstants.PLAN_ID, -1);
            plan = PlanLocalServiceUtil.getPlan(planId);
        } catch (NoSuchPlanPositionException e) {
            SessionErrors.add(renderRequest, e.getClass().getName());
            return mapping.findForward(PlanConstants.VIEW_PLANS_FORWARD);
        }
        // add variables denoting user relation to the Plan's group
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
  
        
        
        
        long userId = themeDisplay.getUserId();

        if( ParamUtil.getString(renderRequest,PlanConstants.UPDATE_TYPE).equals("subscribe"))
        {
        	ActivityUtil.addSubscription(PlanActivityKeys.ALL, userId, plan.getPlanId());
        	
        }
        renderRequest.setAttribute(PlanConstants.PLAN, plan);

          

        boolean isGroupMember = GroupServiceUtil.hasUserGroup(userId, plan.getChildGroupId());
        boolean isGroupOwner = plan.getUserId() == userId;
       

        renderRequest.setAttribute(PlanConstants.IS_PLAN_MEMBER, isGroupMember);
        renderRequest.setAttribute(PlanConstants.IS_PLAN_OWNER, isGroupOwner);

        // add number of plan group members to the request
        LinkedHashMap < String, Object > userParams = new LinkedHashMap < String, Object > ();
        userParams.put(COUNT_GROUP_USERS_PARAMETER, plan.getChildGroupId());
        int membersCount = UserLocalServiceUtil.searchCount(plan.getCompanyId(), null, Boolean.TRUE, userParams);

        renderRequest.setAttribute(PlanConstants.PLAN_MEMBERS_COUNT, membersCount);

        // add number of plan group pending membership requests
        int pendingRequests = MembershipRequestLocalServiceUtil.searchCount(plan.getChildGroupId(),
                MembershipRequestImpl.STATUS_PENDING);
        renderRequest.setAttribute(PlanConstants.PLAN_PENDING_REQUESTS, pendingRequests);

        // add external URLs
        addExternalURLs(renderRequest, plan);

        // decide which page should be shown next, decision is based on value of
        // PlanConstants.VIEW_PLAN_TABS parameter which represents selected View
        // Plan tab (by default it should
        // be "summary".
        String viewPlanTabs = ParamUtil.getString(renderRequest, PlanConstants.VIEW_PLAN_TABS,
                PlanConstants.SUMMARY_TAB);

        PlanLocalServiceHelper.addPlanVote(renderRequest);

        String forward = PlanConstants.VIEW_PLAN_SUMMARY_FORWARD;

        if (PlanConstants.ACTIONS_TAB.equals(viewPlanTabs)) {
            forward = PlanConstants.VIEW_PLAN_ACTIONS_FORWARD;
        } else if (PlanConstants.DESCRIPTION_TAB.equals(viewPlanTabs)) {
            addPlanSummary(renderRequest, plan);
            forward = PlanConstants.VIEW_PLAN_DESCRIPTION_FORWARD;
        } else if (PlanConstants.DISCUSSION_TAB.equals(viewPlanTabs)) {
            forward = PlanConstants.VIEW_PLAN_DISCUSSION_FORWARD;
        } else if (PlanConstants.IMPACTS_TAB.equals(viewPlanTabs)) {
            forward = PlanConstants.VIEW_PLAN_IMPACTS_FORWARD;
        } else if (PlanConstants.MODEL_TAB.equals(viewPlanTabs)) {
            // add plan's model to the request
            addPlanModel(renderRequest, plan);
            forward = PlanConstants.VIEW_PLAN_MODEL_FORWARD;
        } else if (PlanConstants.MEMBERS_TAB.equals(viewPlanTabs)) {
        	addPlanMembers(renderRequest,plan);
            forward = PlanConstants.VIEW_PLAN_MEMBERS_FORWARD;
        } else {
            // plan summary page will be shown thus plan content summary should be added
            addPlanSummary(renderRequest, plan);
            //long topicId = 401L;
            long topicId = Long.parseLong(renderRequest.getPreferences().getValue(PlanConstants.DEFAULT_TOPIC_ID, "-1"));
            
           // PlanLocalServiceHelper.addQuestionsAndPositions(renderRequest, topicId);
            PlanLocalServiceHelper.addQuestionsAndPositionsRevision(renderRequest, topicId);

            // add plan positions
//            renderRequest.setAttribute(PlanConstants.PLAN_POSITIONS,
//                    PlanLocalServiceHelper.getPlanPositions(plan.getPlanId()));

            renderRequest.setAttribute(PlanConstants.PLAN_POSITIONS,
                    PlanLocalServiceHelper.getPlanPositionsDebateRevision(plan.getPlanId()));
        }

        return mapping.findForward(forward);
    }

    /**
     * Method calculates external URLs that is URLs that don't point to Plans portlet (for example
     * message board portlet URL). URL patterns are retrieved from portlet's configuration, for
     * each pattern ID Placeholder is replaced with appropriate value (for example plan's group
     * friendly address).
     *
     * For example discussion widget URL pattern could be something like:
     *  "/widget/web/ID_PLACEHOLDER/home/-/message_boards"
     *
     * If we have a plan named "Example" then group representing it's community
     * will have friendly URL "example", we need to replace ID_PLACEHOLDER in
     * given URL, with name of community. Thus resulting URL will be:
     * "/widget/web/example/home/-/message_boards"
     *
     * @param renderRequest render request
     * @param plan plan that is to be shown to the user
     * @throws PortalException in case of portal exceptions
     * @throws SystemException in case of system exceptions
     */
    private void addExternalURLs(RenderRequest renderRequest, Plan plan) throws PortalException, SystemException {
        // get portlet preferences and group representing plan's community
        PortletPreferences preferences = renderRequest.getPreferences();
        Group childGroup = GroupServiceUtil.getGroup(plan.getChildGroupId());

        String keys[] = {PlanConstants.PLAN_DISCUSSION_URL, PlanConstants.PLAN_MEMBERS_URL,
            PlanConstants.PLAN_MANAGE_MEMBERS_URL, PlanConstants.PLAN_MANAGE_MEMBERSHIP_REQUESTS_URL,
            PlanConstants.PLAN_REQUEST_MEMBERSHIP_URL, PlanConstants.PLAN_MODEL_URL,
            PlanConstants.PLAN_ACTIONS_URL, PlanConstants.PLAN_IMPACTS_URL, PlanConstants.PLAN_POSITION_URL};

        for (String key : keys) {
            String pattern = preferences.getValue(key, "");
            if (key.equals(PlanConstants.PLAN_MODEL_URL)) {
            	//why, you ask?  because there seems to be no way to get llferay to reread it's preferences from portal-ext unless it detects that this is a new version of the system.  FFFFUUUUU.
            	pattern = PlanConstants.PLAN_MODEL_URL_ACTUAL;
            }
            
            pattern = pattern.replaceFirst(PlanConstants.MODEL_ID_PLACEHOLDER, String.valueOf(plan.getPlanType().getModelId()));
            pattern = pattern.replaceFirst(PlanConstants.SCENARIO_ID_PLACEHOLDER, plan.getScenarioId());
            pattern = pattern.replaceFirst(PlanConstants.GROUP_NAME_PLACEHOLDER, childGroup.getFriendlyURL());
            pattern = pattern.replaceFirst(PlanConstants.ID_PLACEHOLDER, Long.toString(childGroup.getGroupId()));
             pattern = pattern.replaceFirst(PlanConstants.CHILD_CATEGORY_ID, Long.toString(plan.getMBCategoryId()));

            renderRequest.setAttribute(key, pattern);
        }
        String questionURL = preferences.getValue(PlanConstants.PLAN_QUESTION_URL, "");
        renderRequest.setAttribute(PlanConstants.PLAN_QUESTION_URL, questionURL);
    }

    private void addPlanModel(RenderRequest renderRequest, Plan plan) throws NumberFormatException, PortalException, SystemException {
    	Long modelid = plan.getPlanType().getModelId();
    	ModelLocalServiceHelper.addAllQuestionsAndPositions(renderRequest, modelid);

    }

    /**
     * Method responsible for calculating plan's description summary (first
     * paragraph).
     *
     * @param renderRequest render request
     * @param plan plan which summary is to be calculated
     */
    private void addPlanSummary(RenderRequest renderRequest, Plan plan) {
        String summary = plan.getShortcontent();
        renderRequest.setAttribute(PlanConstants.PLAN_SUMMARY, summary);
    }
    
    private void addPlanMembers(RenderRequest renderRequest, Plan plan) throws SystemException {
		
    	List<User> users = UserLocalServiceUtil.getGroupUsers(plan.getChildGroupId());
    	renderRequest.setAttribute(PlanConstants.MEMBERS_PARAMETER,users);
    	List<MembershipRequest> requests = new ArrayList<MembershipRequest>();
    	requests.addAll(MembershipRequestLocalServiceUtil.search(plan.getChildGroupId(), PlanConstants.STATUS_PENDING,0,Integer.MAX_VALUE));
    	renderRequest.setAttribute(PlanConstants.MEMBERSHIP_REQUESTS_PARAMETER,requests);
    }
}