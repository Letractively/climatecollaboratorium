/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models.action;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.debates.DebatesConstants;
import com.ext.portlet.debates.DebatesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.messageboards.action.EditMessageAction;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

/**
 * Action class responsible for moving a debate message to another parent.
 * 
 * version 1.1: added logic responsible for deleting references from plan to
 *    a position that wont be a position anymore
 * 
 * @author janusz.p
 * @version 1.1
 * @since 1.0
 * 
 */
public class MoveMessageAction extends EditMessageAction {

    /**
     * Processes move message action. If message is to be moved to another
     * parent it's type is checked for validity.
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

        long messageId = ParamUtil.getLong(actionRequest, DebatesConstants.MESSAGE_ID);
        long parentMessageId = ParamUtil.getLong(actionRequest, DebatesConstants.PARENT_MESSAGE_ID);
        int messageType = ParamUtil.getInteger(actionRequest, DebatesConstants.MESSAGE_TYPE);

        MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);
        MBMessage debateMessage = DebatesUtil.getDebateMessage(message.getCategoryId());
        if (parentMessageId == debateMessage.getMessageId()) {
            // if new parent is set to debate message then moved message should
            // become a position
            messageType = DebatesConstants.POSITION_MSG_TYPE;
        }

        if (parentMessageId != message.getParentMessageId()) {
            // if message was a position and now it won't be then all references from plan should be removed
            int oldMessageType = DebatesUtil.getMessageType(messageId);
            if (oldMessageType == DebatesConstants.POSITION_MSG_TYPE) {
                DebatesUtil.deletePositionReferences(messageId);
            }

            // parent has been changed
            MBMessage parentMessage = MBMessageLocalServiceUtil.getMBMessage(parentMessageId);
            
            // check message type
            DebatesUtil.validateMessageType(parentMessage.getMessageId(), messageType);

            // update message type and parent
            DebatesUtil.setMessageType(messageId, messageType);

            message.setParentMessageId(parentMessage.getMessageId());

            MBMessageLocalServiceUtil.updateMBMessage(message);
        }
        String redirect = ParamUtil.getString(actionRequest, DebatesConstants.REDIRECT);
        actionResponse.sendRedirect(redirect);

    }

    /**
     * Action that forwadrs user to move message page.
     * 
     * Action allows user to traverse debate tree to find new parent for his
     * message. In order to achieve that list of messages from current "level"
     * is created, also a breadcrumb is build (representing path from currently
     * selected parent to top of then issue).
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

        long messageId = ParamUtil.getLong(renderRequest, DebatesConstants.MESSAGE_ID);
        long parentMessageId = ParamUtil.getLong(renderRequest, DebatesConstants.PARENT_MESSAGE_ID);
        MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);

        renderRequest.setAttribute(DebatesConstants.MESSAGE, message);

        MBMessage debateMessage = DebatesUtil.getDebateMessage(message.getCategoryId());

        // represents list of potential parentMessages (messages that are child
        // of selected parent and can be chosen as a new parent for message
        List<MBMessage> parentMessages = new ArrayList<MBMessage>();

        // get child messages of selected parent, don't include message that is
        // to be moved
        MBMessage parentMessage = null;
        if (parentMessageId > 0) {
            parentMessage = MBMessageLocalServiceUtil.getMessage(parentMessageId);
        } else {
            parentMessage = debateMessage;
        }
        for (MBMessage childMessage : DebatesUtil.getChildMessages(parentMessage)) {
            if (childMessage.getMessageId() != messageId) {
                parentMessages.add(childMessage);
            }
        }

        // build breadcrumb of selected parents tree
        List<MBMessage> breadcrumb = new ArrayList<MBMessage>();
        long parentId = parentMessage.getParentMessageId();
        while (parentId > 0) {
            MBMessage parent = MBMessageLocalServiceUtil.getMessage(parentId);
            if (parent.getMessageId() != message.getMessageId()) {
                breadcrumb.add(0, parent);
                parentId = parent.getParentMessageId();
            }
        }

        renderRequest.setAttribute(DebatesConstants.PARENT_MESSAGE, parentMessage);
        renderRequest.setAttribute(DebatesConstants.DEBATE_MESSAGE, debateMessage);
        renderRequest.setAttribute(DebatesConstants.BREADCRUMB, breadcrumb);
        renderRequest.setAttribute(DebatesConstants.PARENT_MESSAGES, parentMessages);

        return mapping.findForward(DebatesConstants.MOVE_MESSAGE_FORWARD);
    }

}
