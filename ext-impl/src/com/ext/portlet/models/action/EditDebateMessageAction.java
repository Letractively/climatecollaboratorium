/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletURL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.Activity.ActivityConstants;
import com.ext.portlet.debates.DebatesConstants;
import com.ext.portlet.debates.DebatesUtil;
import com.ext.portlet.debates.MessageTypeException;
import com.ext.portlet.models.ModelConstants;
import com.liferay.documentlibrary.FileNameException;
import com.liferay.documentlibrary.FileSizeException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.kernel.captcha.CaptchaUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.ActionResponseImpl;
import com.liferay.portlet.messageboards.MessageBodyException;
import com.liferay.portlet.messageboards.MessageSubjectException;
import com.liferay.portlet.messageboards.NoSuchMessageException;
import com.liferay.portlet.messageboards.RequiredMessageException;
import com.liferay.portlet.messageboards.action.EditMessageAction;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.model.MBTreeWalker;
import com.liferay.portlet.messageboards.model.impl.MBThreadImpl;
import com.liferay.portlet.messageboards.service.MBMessageFlagLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.portlet.tags.TagsEntryException;

/**
 * Action class for adding/updating a debate message.
 *
 * version 1.1: added logic responsible for deleting references from plan to
 *    a position that wont be a position anymore
 *
 * @author janusz.p
 * @version 1.1
 * @since 1.0
 * 
 */
public class EditDebateMessageAction extends EditMessageAction {

    /**
     * Processes edit debate message action.
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

        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
        int messageType = ParamUtil.getInteger(actionRequest, DebatesConstants.MESSAGE_TYPE,
                DebatesConstants.NORMAL_MSG_TYPE);
        long messageId = ParamUtil.getLong(actionRequest, DebatesConstants.MESSAGE_ID);
        long parentMessageId = ParamUtil.getLong(actionRequest, DebatesConstants.PARENT_MESSAGE_ID);
        boolean requiresType = ParamUtil.getBoolean(actionRequest, DebatesConstants.REQUIRES_TYPE);
        boolean preview = ParamUtil.getBoolean(actionRequest, DebatesConstants.PREVIEW);
        
        // if user wants to see preview or issued cmd is equal to empty string, pass 
        // method call to super class
        if (preview || cmd.equals("")) {
        	super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
        	return;
        }
        
        
        try {
            if (requiresType == true && messageType == DebatesConstants.NORMAL_MSG_TYPE) {
                throw new MessageTypeException("Message type is required");
            }
            if ((cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE))) {
                // if message is to be updated or added chec if it has correct
                // type
                if (messageType != DebatesConstants.NORMAL_MSG_TYPE) {
                    DebatesUtil.validateMessageType(parentMessageId, messageType);
                }
                updateMessage(actionRequest, actionResponse);
                messageId = (Long) actionRequest.getAttribute(DebatesConstants.MESSAGE_ID);
            } else if (messageType != DebatesConstants.NORMAL_MSG_TYPE && cmd.equals(Constants.DELETE)) {
                // if debate message is to be deleted, delete all it's child
                // messages
                MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);
                deleteMessageWithSubmessages(message);
                // if deleted message is a position then delete all references from plans to this position
                if (messageType == DebatesConstants.POSITION_MSG_TYPE) {
                    DebatesUtil.deletePositionReferences(messageId);
                }
            } else {
                super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
            }

            // redirect user to appropriate page
            if (messageType != DebatesConstants.NORMAL_MSG_TYPE) {
                String redirect = ParamUtil.getString(actionRequest, DebatesConstants.REDIRECT);
                redirect += "#" + actionResponse.getNamespace() + "message_" + messageId;
                actionResponse.sendRedirect(redirect);
            }
        } catch (Exception e) {
            if (e instanceof NoSuchMessageException || e instanceof PrincipalException
                    || e instanceof RequiredMessageException) {

                SessionErrors.add(actionRequest, e.getClass().getName());

            } else if (e instanceof CaptchaTextException || e instanceof FileNameException
                    || e instanceof FileSizeException || e instanceof MessageBodyException
                    || e instanceof MessageSubjectException || e instanceof MessageTypeException) {

                SessionErrors.add(actionRequest, e.getClass().getName());
            } else if (e instanceof TagsEntryException) {
                SessionErrors.add(actionRequest, e.getClass().getName(), e);
            } else {
                throw e;
            }
        }

    }

    /**
     * Updates/Adds a message. Message values are taken from actionRequest.
     * 
     * @param actionRequest
     *            action request
     * @param actionResponse
     *            action response
     * @throws Exception
     *             in case of any error
     */
    protected void updateMessage(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        long messageId = ParamUtil.getLong(actionRequest, "messageId");

        long categoryId = ParamUtil.getLong(actionRequest, "categoryId");
        long threadId = ParamUtil.getLong(actionRequest, "threadId");
        long parentMessageId = ParamUtil.getLong(actionRequest, "parentMessageId");
        String subject = ParamUtil.getString(actionRequest, "subject");
        String body = ParamUtil.getString(actionRequest, "body");
        boolean attachments = ParamUtil.getBoolean(actionRequest, "attachments");
        long modelId = ParamUtil.getLong(actionRequest, "modelId");
        String modelName = ParamUtil.getString(actionRequest, "modelName");

        List<ObjectValuePair<String, byte[]>> files = new ArrayList<ObjectValuePair<String, byte[]>>();

        if (attachments) {
            UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

            for (int i = 1; i <= 5; i++) {
                File file = uploadRequest.getFile("msgFile" + i);
                String fileName = uploadRequest.getFileName("msgFile" + i);
                byte[] bytes = FileUtil.getBytes(file);

                if ((bytes != null) && (bytes.length > 0)) {
                    ObjectValuePair<String, byte[]> ovp = new ObjectValuePair<String, byte[]>(fileName, bytes);

                    files.add(ovp);
                }
            }
        }

        boolean question = ParamUtil.getBoolean(actionRequest, "question");
        boolean anonymous = ParamUtil.getBoolean(actionRequest, "anonymous");
        double priority = ParamUtil.getDouble(actionRequest, "priority");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(MBMessage.class.getName(), actionRequest);
        serviceContext.setAttribute(ActivityConstants.MESSAGE_TYPE, ActivityConstants.MODEL_MESSAGE);
        serviceContext.setAttribute(ActivityConstants.MODEL_ID, modelId);
        serviceContext.setAttribute(ActivityConstants.MODEL_NAME, modelName);

        MBMessage message = null;

        if (messageId <= 0) {
            if (PropsValues.CAPTCHA_CHECK_PORTLET_MESSAGE_BOARDS_EDIT_MESSAGE) {
                CaptchaUtil.check(actionRequest);
            }

            if (threadId <= 0) {

                // Post new thread

                message = MBMessageServiceUtil.addMessage(categoryId, subject, body, files, anonymous, priority,
                        serviceContext);

                if (question) {
                    MBMessageFlagLocalServiceUtil.addQuestionFlag(message.getMessageId());
                }
            } else {

                // Post reply

                message = MBMessageServiceUtil.addMessage(categoryId, threadId, parentMessageId, subject, body, files,
                        anonymous, priority, serviceContext);
            }
        } else {
            List<String> existingFiles = new ArrayList<String>();

            for (int i = 1; i <= 5; i++) {
                String path = ParamUtil.getString(actionRequest, "existingPath" + i);

                if (Validator.isNotNull(path)) {
                    existingFiles.add(path);
                }
            }

            // Update message

            message = MBMessageServiceUtil.updateMessage(messageId, subject, body, files, existingFiles, priority,
                    serviceContext);

            if (message.isRoot()) {
                if (question) {
                    MBMessageFlagLocalServiceUtil.addQuestionFlag(messageId);
                } else {
                    MBMessageFlagLocalServiceUtil.deleteQuestionAndAnswerFlags(message.getThreadId());
                }
            }
        }

        // set message type
        int messageType = ParamUtil.getInteger(actionRequest, DebatesConstants.MESSAGE_TYPE,
                DebatesConstants.NORMAL_MSG_TYPE);
        if (messageType != DebatesConstants.NORMAL_MSG_TYPE) {
            DebatesUtil.setMessageType(message.getMessageId(), messageType);
        }

        PortletURL portletURL = ((ActionResponseImpl) actionResponse).createRenderURL();

        portletURL.setParameter("struts_action", "/ext/models/view_message");
        portletURL.setParameter("messageId", String.valueOf(message.getMessageId()));
        portletURL.setParameter(DebatesConstants.VIEW_DEBATES_INDEX_TABS, DebatesConstants.DISCUSSION_TAB);
        
        String modelname = ParamUtil.getString(actionRequest,ModelConstants.MODEL_NAME);
        String modelid = ParamUtil.getString(actionRequest,ModelConstants.MODEL_ID);
        if (modelid!=null) {
        	portletURL.setParameter(ModelConstants.MODEL_ID, modelid);
        	
        }
        
        if (modelname!=null) {
        	portletURL.setParameter(ModelConstants.MODEL_NAME, modelname);
        	
        }
        
        portletURL.setParameter(ModelConstants.VIEW_MODEL_INDEX_TABS, ModelConstants.DISCUSSION_TAB);


        actionResponse.sendRedirect(portletURL.toString());
        actionRequest.setAttribute(DebatesConstants.MESSAGE_ID, message.getMessageId());
    }

    /**
     * Deletes messages with all it's sub messages.
     * 
     * @param message
     *            message that is to be deleted
     * @throws PortalException
     *             passed up in case of an error in portal framework
     * @throws SystemException
     *             passed up in case of an error in portal framework
     */
    protected void deleteMessageWithSubmessages(MBMessage message) throws PortalException, SystemException {

        MBMessageDisplay display = MBMessageLocalServiceUtil.getMessageDisplay(message.getMessageId(),
                MBThreadImpl.THREAD_VIEW_TREE);
        MBTreeWalker treeWalker = display.getTreeWalker();

        for (MBMessage childMessage : treeWalker.getChildren(message)) {
            deleteMessageWithSubmessages(childMessage);
        }
        MBMessageServiceUtil.deleteMessage(message.getMessageId());
    }
}
