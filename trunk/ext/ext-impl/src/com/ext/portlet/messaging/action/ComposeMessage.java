/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */



package com.ext.portlet.messaging.action;

import com.ext.portlet.messaging.MessageConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

  /**
 * @author jintrone
 * @date 01/19/2010
 * @version 1.0
 */

public class ComposeMessage extends PortletAction {

    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
                              ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {


        ThemeDisplay themedisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themedisplay.getUserId();
        String redirect = ParamUtil.getString(actionRequest, MessageConstants.REDIRECT);

        String subject = ParamUtil.get(actionRequest,MessageConstants.COMPOSE_SUBJECT, "");
        String content = ParamUtil.get(actionRequest,MessageConstants.COMPOSE_CONTENT, "");
        String[] receiverIds = ParamUtil.get(actionRequest,MessageConstants.COMPOSE_RECIPIENTS,"").split(",");
        Long replyTo = ParamUtil.getLong(actionRequest,MessageConstants.COMPOSE_REPLY_TO,-1);
        List<Long> recipients = new ArrayList<Long>();

        for (String receiver:receiverIds) {
            try {
                recipients.add(Long.parseLong(receiver));
            }
            catch (NumberFormatException e) {
                SessionErrors.add(actionRequest, e.getClass().getName());
                return;
            }
        }

        MessageUtil.sendMessage(subject,content,userId,replyTo,recipients,actionRequest);




        actionResponse.sendRedirect(redirect);
    }

}
