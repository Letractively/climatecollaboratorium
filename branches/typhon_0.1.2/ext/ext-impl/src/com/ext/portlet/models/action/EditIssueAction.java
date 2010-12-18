/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models.action;

import java.util.ArrayList;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.models.DebatesConstants;
import com.ext.portlet.models.DebatesUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.messageboards.CategoryNameException;
import com.liferay.portlet.messageboards.action.EditCategoryAction;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.portlet.tags.TagsEntryException;

/**
 * Action class for adding/updating a debate (called also issue or question).
 *
 * If debate is to be created new sub category is created. Within that sub
 * category two additional sub categories are created. One for actual debate the
 * other for discussion. In debate sub category new thread is created (to
 * represent debate).
 *
 * @author janusz.p
 * @version 1.0
 */
public class EditIssueAction extends EditCategoryAction {

    /**
     * Processes edit issue action.
     *
     * For each new debate separate subcategory is created in message boards.
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
    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        long topicId = ParamUtil.getLong(actionRequest, DebatesConstants.PARENT_CATEGORY_ID);
        String questionString = ParamUtil.getString(actionRequest, DebatesConstants.QUESTION);
        String redirect = ParamUtil.getString(actionRequest, DebatesConstants.REDIRECT);

        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        if (cmd.equals(Constants.ADD)) {
            try {

                // user adds a debate, create category for it
                MBCategory question = DebatesUtil.createMBCategory(actionRequest, topicId, questionString);

                // create "Debate" sub category within newly created category
                MBCategory questionDebate = DebatesUtil.createMBCategory(actionRequest, question.getCategoryId(),
                        DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);

                // create "Discussion" sub category within newly created category
                DebatesUtil.createMBCategory(actionRequest, question.getCategoryId(),
                        DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME);

                // create new message representing debate.
                ServiceContext serviceContext = ServiceContextFactory.getInstance(MBMessage.class.getName(), actionRequest);
                MBMessage debateMessage = MBMessageServiceUtil.addMessage(questionDebate.getCategoryId(), questionString,
                        questionString, new ArrayList<ObjectValuePair<String, byte[]>>(), false, 0, serviceContext);
                DebatesUtil.setMessageType(debateMessage.getMessageId(), DebatesConstants.ISSUE_MSG_TYPE);

            } catch (Exception e) {
                if (e instanceof PrincipalException || e instanceof CategoryNameException) {
                    SessionErrors.add(actionRequest, e.getClass().getName());
                } else if (e instanceof TagsEntryException) {
                    SessionErrors.add(actionRequest, e.getClass().getName(), e);
                } else {
                    throw e;
                }
            }

        } else {
            if (cmd.equals(Constants.UPDATE)) {
                // update issue name
                long debateBaseCategoryId = ParamUtil.getLong(actionRequest, DebatesConstants.CATEGORY_ID);
                MBCategory debateCategory = DebatesUtil.getSubcategory(debateBaseCategoryId,
                        DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);

                MBMessage debateMessage = DebatesUtil.getDebateMessage(debateCategory.getCategoryId());
                debateMessage.setSubject(questionString);
                MBMessageLocalServiceUtil.updateMBMessage(debateMessage);
            }
            super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
        }
        if (SessionErrors.size(actionRequest) == 0) {
            actionResponse.sendRedirect(redirect);
        }
    }

    /**
     * Action that forwadrs user to edit issue page.
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
     * @throws Exception
     *             in case of any error
     */
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        // set type of edited entity
        renderRequest.setAttribute(DebatesConstants.EDITED_ENTITY_TYPE, DebatesConstants.QUESTION_ENTITY_TYPE);

        long topicId = ParamUtil.getLong(renderRequest, DebatesConstants.PARENT_CATEGORY_ID);
        MBCategory topic = MBCategoryLocalServiceUtil.getCategory(topicId);
        renderRequest.setAttribute(DebatesConstants.TOPIC, topic);

        long issueId = ParamUtil.getLong(renderRequest, DebatesConstants.ISSUE_ID);
        if (issueId > 0) {
            MBCategory issue = MBCategoryLocalServiceUtil.getCategory(issueId);
            renderRequest.setAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY, issue);
        }
        renderRequest.setAttribute("modelsTabParameter", "Model Debates");


        return mapping.findForward(DebatesConstants.EDIT_ISSUE_FORWARD);
    }
}
