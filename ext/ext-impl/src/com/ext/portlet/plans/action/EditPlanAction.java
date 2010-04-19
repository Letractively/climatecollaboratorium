/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.action;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.plans.NoSuchPlanPositionException;
import com.ext.portlet.plans.PlanActivityKeys;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanPosition;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanPositionPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.DuplicateGroupException;
import com.liferay.portal.GroupNameException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.portlet.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class responsible for handling Edit Plan action.
 *
 * version 1.1: modified class to handle sequential updating of plan data, also
 *              initialization with default topic id and model id has been added
 * version 1.2: added support for plan votes and handling of new plan fields
 *              (damage cost, CO2 etc.)
 *
 * @author janusz.p, janusz.p
 * @version 1.2
 * @since 1.0
 */
public class EditPlanAction extends PortletAction {

    /**
     * Default community permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_COMMUNITY_PERMISSIONS = {ActionKeys.VIEW, ActionKeys.SUBSCRIBE,
        ActionKeys.REPLY_TO_MESSAGE, ActionKeys.ADD_MESSAGE };

    /**
     * Default guest permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_GUEST_PERMISSIONS = DEFAULT_CATEGORY_COMMUNITY_PERMISSIONS;

    /**
     * Default description of group working on a plan.
     */
    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on plan %s";

    /**
     * Default forum category name.
     */
    public static final String DEFAULT_FORUM_CATEGORY_NAME = "General discussion";

    /**
     * Default forum category description.
     */
    public static final String DEFAULT_FORUM_CATEGORY_DESCRIPTION = "General discussion about plan %s";

    /**
     * Plan position state - position already exists.
     */
    public static final int EXISTS = 0;

    /**
     * Plan position state - new position.
     */
    public static final int NEW = 1;

    /**
     * Plan position state - position should be deleted.
     */
    public static final int DELETE = 2;


    /**
     * Forwards action to appropriate page. If there is no planId supplied user
     * is directed to edit plan page, if there is planID then it is checked
     * for validity. If everything is correct user is directed to edit plan
     * page, if there is no plan with given id then user is directed to
     * view plans page (with appropriate error message).
     *
     *  @param mapping action mapping
     *  @param form action form
     *  @param portletConfig portlet config
     *  @param renderRequest render request
     *  @param renderResponse render response
     *  @return returns forward key
     *  @throws Exception in case of any error
     */
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
        RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        long planId = ParamUtil.getLong(renderRequest, PlanConstants.PLAN_ID, -1);
        String updateType = ParamUtil.getString(renderRequest, PlanConstants.UPDATE_TYPE);
        Plan plan = null;
        if (planId != -1) {
            try {
                plan = PlanLocalServiceUtil.getPlan(planId);
                renderRequest.setAttribute(PlanConstants.PLAN, plan);
            } catch (NoSuchPlanPositionException e) {
                SessionErrors.add(renderRequest, e.getClass().getName());
                return mapping.findForward(PlanConstants.VIEW_PLANS_FORWARD);
            }
        }

        if (updateType.equals(PlanConstants.UPDATE_POSITIONS)) {
            PlanLocalServiceHelper.addQuestionsAndPositions(renderRequest, Long.parseLong(plan.getTopicId()));
            renderRequest.setAttribute(PlanConstants.PLAN_POSITIONS,
                    PlanLocalServiceHelper.getPlanPositions(plan.getPlanId()));
        }

        return mapping.findForward(PlanConstants.EDIT_PLAN_FORWARD);
    }

    /**
     * Processes edit plan action (submission of edit plan form). Passed parameters
     * are checked for validity, and if everything is correct new plan gets created.
     * If there is any error user is presented with appropriate error message.
     *
     * For each new plan separate group is created. Also in group's message board new thread
     * is created for
     *
     *  @param mapping action mapping
     *  @param form action form
     *  @param portletConfig portlet config
     *  @param actionRequest action request
     *  @param actionResponse action response
     *  @throws Exception in case of any error
     */
    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        // plan field values
        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
        String name = ParamUtil.getString(actionRequest, PlanConstants.PLAN_NAME_FIELD);
        String content = ParamUtil.getString(actionRequest, PlanConstants.PLAN_CONTENT_FIELD);
        String shortcontent = ParamUtil.getString(actionRequest, PlanConstants.PLAN_SHORT_CONTENT_FIELD);
        String updateType = ParamUtil.getString(actionRequest, PlanConstants.UPDATE_TYPE);
        updateType = updateType==null?"":updateType;
        String scenarioId = ParamUtil.getString(actionRequest, PlanConstants.SCENARIO_ID);
       // String positions = ParamUtil.getString(actionRequest, PlanConstants.POSITIONS);
        Set<DebateItem> positions = new HashSet<DebateItem>();
        for (String s:actionRequest.getParameterMap().keySet()) {
            if (s.startsWith("questionposition")) {
                 positions.add(DebateItemLocalServiceUtil.getLastActiveItem(Long.parseLong(actionRequest.getParameter(s))));
            }
        }


        String redirect = ParamUtil.getString(actionRequest, PlanConstants.REDIRECT);

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
    	long userId = themeDisplay.getUserId();
    
        
        Date now = new Date();

        Plan plan = null;
        try {

            if (cmd.equals(Constants.ADD)) {
                // new plan is to be added

                // create new plan
            	plan = PlanLocalServiceHelper.addPlan(actionRequest);
            	//updatePlanScenarioValues(actionRequest, plan);
                if (!scenarioId.trim().equals("")) {
                    updatePlanScenarioValues(actionRequest, plan);
                }

                redirect = PlanConstants.PLAN_URL_RAW;
               
                SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", plan.getPrimaryKey(),PlanActivityKeys.ADD_PLAN.id(), StringPool.BLANK, 0);
               
            } else if (cmd.equals(Constants.DELETE)) {
                long planId = ParamUtil.getLong(actionRequest, PlanConstants.PLAN_ID);
                plan = PlanLocalServiceUtil.getPlan(planId);
                long groupid = plan.getChildGroupId();
            	PlanLocalServiceUtil.deletePlan(planId);
            	GroupLocalServiceUtil.deleteGroup(groupid);
            	actionResponse.sendRedirect("/web/guest/plans");
            	return;
            	
            
            
            
            } else {
                try {
                    long planId = ParamUtil.getLong(actionRequest, PlanConstants.PLAN_ID);
                    plan = PlanLocalServiceUtil.getPlan(planId);

                } catch (NoSuchPlanPositionException e) {
                    SessionErrors.add(actionRequest, e.getClass().getName());
                    return;
                }

            }

            if (updateType.equals(PlanConstants.COPY_PLAN)) {
            	String planName = ParamUtil.getString(actionRequest, PlanConstants.PLAN_NAME_FIELD);
    	        long climatePlanId = CounterLocalServiceUtil.increment(Plan.class.getName());
                Plan newPlan =(Plan) plan.clone();
                newPlan.setPlanId(climatePlanId);
                newPlan.setName(planName);
                if (newPlan.getPlanType().getPublished()) {
                    // if we are cloning published plan then change it's type to unpublished counterpart of given type
                    newPlan.setPlanTypeId(newPlan.getPlanType().getPublishedCounterpartId());
                }
            	PlanLocalServiceHelper.initializePlan(newPlan, actionRequest);
                plan=newPlan;
                redirect = PlanConstants.PLAN_URL_RAW;
            }

             if (updateType.equals(PlanConstants.UPDATE_SCENARIO)) {          	            	           	 
                updatePlanScenarioValues(actionRequest, plan);
                SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", plan.getPrimaryKey(),PlanActivityKeys.EDIT_SCENARIO.id(), StringPool.BLANK, 0);
            } else if (updateType.equals(PlanConstants.UPDATE_POSITIONS)) {
                String positionsMessage=updatePositions(plan, positions, actionRequest);
                SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", plan.getPrimaryKey(),PlanActivityKeys.EDIT_POSITIONS.id(),positionsMessage, 0);
            } else if (updateType.equals(PlanConstants.UPDATE_PLAN_NAME)) {
                plan.setName(name);
                SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", plan.getPrimaryKey(),PlanActivityKeys.EDIT_NAME.id(), StringPool.BLANK, 0);
            } else if (updateType.equals(PlanConstants.UPDATE_DESCRIPTION)) {
            	plan.setContent(content);
                SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", plan.getPrimaryKey(),PlanActivityKeys.EDIT_DESCRIPTION.id(), StringPool.BLANK, 0);
            } else if (updateType.equals(PlanConstants.UPDATE_SHORT_DESCRIPTION)) {
            	plan.setShortcontent(shortcontent);
                SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", plan.getPrimaryKey(),PlanActivityKeys.EDIT_DESCRIPTION.id(), StringPool.BLANK, 0);
                
                
            } else if (updateType.equals(PlanConstants.UPDATE_PUBLISHED)) {
                plan.setPlanTypeId(plan.getPlanType().getPublishedCounterpartId());
                plan.setPublishDate(now);
                SocialActivityLocalServiceUtil.addActivity(userId, themeDisplay.getScopeGroupId(), "com.ext.portlet.Activity", plan.getPrimaryKey(),PlanActivityKeys.PUBLISH_UPDATES.id(), StringPool.BLANK, 0);
            }

            plan.setModifiedDate(now);

            // persist plan
            if (cmd.equals(Constants.ADD) || cmd.equals(PlanConstants.COPY_PLAN)) {
                PlanLocalServiceUtil.addPlan(plan);
                ActivityUtil.addSubscription(PlanActivityKeys.ALL,userId,plan.getPlanId());
                
            } else {
                PlanLocalServiceUtil.updatePlan(plan);
            }

            SessionMessages.add(actionRequest, portletConfig.getPortletName() + PlanConstants.PLAN_ADD_ACTION_MESSAGE);

            // if everything went well then user should be redirected to view plan page
            // to achieve this redirect URL should contain ID Placeholder (that is a
            // string that will be replaced with planId).
            if (redirect != null && !redirect.equals("")) {
                String redirectURL = redirect.replaceFirst(PlanConstants.PLAN_REPLACEMENT_STRING, plan.getPlanId().toString());

                actionResponse.sendRedirect(redirectURL);
            }
        } catch (Exception e) {
            if (e instanceof DuplicateGroupException || e instanceof GroupNameException
                    || e instanceof PrincipalException) {
                SessionErrors.add(actionRequest, e.getClass().getName());
            } else {
                throw e;
            }
        }

    }

    /**
     * Updates association between plan and positions.
     *
     * @param plan plan that is being updated
     * @param incomingpositions list of positions 
     * @throws SystemException passed up in case of framework error
     * @throws PortalException passed up in case of framework error
     */
    private String updatePositions(Plan plan, Set<DebateItem> incomingpositions, ActionRequest actionRequest)
        throws SystemException, PortalException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();
        User user = UserLocalServiceUtil.getUser(userId);
        int added=0,removed=0;

        List<PlanPosition> planpositions = PlanLocalServiceHelper.queryPlanPositions(plan.getPlanId());
        for (PlanPosition p:planpositions) {
            DebateItem item = DebateItemLocalServiceUtil.getLastActiveItem(p.getPositionId());
            if (!incomingpositions.remove(item)) {
                PlanPositionLocalServiceUtil.deletePlanPosition(p);
                removed++;
            }
        }
        for (DebateItem npos:incomingpositions) {

          PlanPositionPK pk = new PlanPositionPK();
            pk.setPlanId(plan.getPlanId());
            pk.setPositionId(npos.getDebateItemId());
            PlanPosition planPosition = PlanPositionLocalServiceUtil.createPlanPosition(pk);
            planPosition.setCreateDate(new Date());
            planPosition.setModifiedDate(new Date());
            planPosition.setUserId(userId);
            planPosition.setUserName(user.getFullName());
            PlanPositionLocalServiceUtil.updatePlanPosition(planPosition);
            added++;
        }

        return added>0?"Added "+added+" positions; ":""+(removed>0?" Removed "+removed+" positions.":"");
    }






    /**
     * Updates all plan values that are related to referenced scenario.
     *
     * @param request request from which values are taken
     * @param plan plan which value are to be updated
     * @throws PortalException 
     */
    private void updatePlanScenarioValues(ActionRequest request, Plan plan) throws SystemException, PortalException {
    	long planId = plan.getPlanId();
    	String scenarioId = ParamUtil.getString(request, PlanConstants.SCENARIO_ID);
        //long planTypeId = ParamUtil.getLong(request, PlanConstants.PLAN_TYPE_ID);
        PlanType planType = plan.getPlanType();
        
    	for (PlanConstants.Attribute attribute:PlanConstants.Attribute.getPlanTypeAttributes(planType)) {
    	    String value = attribute.calculateValue(scenarioId).toString();
    		PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(planId,attribute.name());
    		if (att!=null) {
    			att.setAttributeValue(value);
    			PlanAttributeLocalServiceUtil.updatePlanAttribute(att);
    		} else {
    			PlanAttributeLocalServiceUtil.addPlanAttribute(planId, attribute.name(),value);
    		}
    	}
    	 plan.setScenarioId( ParamUtil.getString(request, PlanConstants.SCENARIO_ID));
    }
}