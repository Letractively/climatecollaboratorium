/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.action;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.models.ModelConstants;
import com.ext.portlet.models.model.ModelPosition;
import com.ext.portlet.models.service.ModelPositionLocalServiceUtil;
import com.ext.portlet.models.service.persistence.ModelPositionPK;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
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
 * initialization with default topic id and model id has been added version 1.2:
 * added support for plan votes and handling of new plan fields (damage cost,
 * CO2 etc.)
 *
 * @author janusz.p, janusz.p
 * @version 1.2
 * @since 1.0
 */
public class EditModelAction extends PortletAction {

	/**
	 * Default community permissions for community forum category.
	 */
	public static final String[] DEFAULT_CATEGORY_COMMUNITY_PERMISSIONS = {
			ActionKeys.VIEW, ActionKeys.SUBSCRIBE, ActionKeys.REPLY_TO_MESSAGE,
			ActionKeys.ADD_MESSAGE };

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
	 * is directed to edit plan page, if there is planID then it is checked for
	 * validity. If everything is correct user is directed to edit plan page, if
	 * there is no plan with given id then user is directed to view plans page
	 * (with appropriate error message).
	 *
	 * @param mapping
	 *            action mapping
	 * @param form
	 *            action form
	 * @param portletConfig
	 *            portlet config
	 * @param renderRequest
	 *            render request
	 * @param renderResponse
	 *            render response
	 * @return returns forward key
	 * @throws Exception
	 *             in case of any error
	 */
	public ActionForward render(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {

		long modelId = ParamUtil.getLong(renderRequest,
				ModelConstants.MODEL_ID, -1);

		List<DebateItem> positions = ModelLocalServiceHelper
				.getModelPositions(modelId);

		renderRequest.setAttribute(ModelConstants.MODEL_POSITIONS, positions);

		renderRequest.getPortletSession().setAttribute(
				ModelConstants.MODEL_POSITIONS, positions);

		return mapping.findForward(ModelConstants.EDIT_MODEL_FORWARD);
	}

	/**
	 * Processes edit plan action (submission of edit plan form). Passed
	 * parameters are checked for validity, and if everything is correct new
	 * plan gets created. If there is any error user is presented with
	 * appropriate error message.
	 *
	 * For each new plan separate group is created. Also in group's message
	 * board new thread is created for
	 *
	 * @param mapping
	 *            action mapping
	 * @param form
	 *            action form
	 * @param portletConfig
	 *            portlet config
	 * @param actionRequest
	 *            action request
	 * @param actionResponse
	 *            action response
	 * @throws Exception
	 *             in case of any error
	 */
	public void processAction(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ModelLocalServiceHelper.addQuestionsAndPositions(actionRequest, Long
				.parseLong(PlanLocalServiceHelper
						.getDefaultTopicId(actionRequest)));

        Set<DebateItem> positions = new HashSet<DebateItem>();
        for (String s:actionRequest.getParameterMap().keySet()) {
            if (s.startsWith("questionposition")) {
                 positions.add(DebateItemLocalServiceUtil.getLastActiveItem(Long.parseLong(actionRequest.getParameter(s))));
            }
        }

		updatePositions(ParamUtil.getLong(actionRequest, "modelId", -1),
				positions,
				actionRequest);

	    String redirect = ParamUtil.getString(actionRequest, PlanConstants.REDIRECT);
	    actionResponse.sendRedirect(redirect);

	}


     private String updatePositions(Long modelId, Set<DebateItem> incomingpositions, ActionRequest actionRequest)
        throws SystemException, PortalException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();
        User user = UserLocalServiceUtil.getUser(userId);
        int added=0,removed=0;

        List<ModelPosition> modelpositions = ModelLocalServiceHelper.queryModelPositions(modelId);
        for (ModelPosition p:modelpositions) {
            DebateItem item = DebateItemLocalServiceUtil.getLastActiveItem(p.getPositionId());
            if (!incomingpositions.remove(item)) {
                ModelPositionLocalServiceUtil.deleteModelPosition(p);
                removed++;
            }
        }
        for (DebateItem npos:incomingpositions) {

          ModelPositionPK pk = new ModelPositionPK();
            pk.setModelId(modelId);
            pk.setPositionId(npos.getDebateItemId());
            ModelPosition modelPosition = ModelPositionLocalServiceUtil.createModelPosition(pk);
            modelPosition.setCreateDate(new Date());
            modelPosition.setModifiedDate(new Date());
            modelPosition.setUserId(userId);
            modelPosition.setUserName(user.getFullName());
            ModelPositionLocalServiceUtil.updateModelPosition(modelPosition);
            added++;
        }

        return added>0?"Added "+added+" positions; ":""+(removed>0?" Removed "+removed+" positions.":"");
    }



}