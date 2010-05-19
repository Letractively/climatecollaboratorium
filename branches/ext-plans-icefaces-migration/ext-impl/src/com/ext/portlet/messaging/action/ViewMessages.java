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

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

/**
 * @author jintrone
 * @date 01/19/2010
 * @version 1.0
 */
public class ViewMessages extends PortletAction {

    @Override
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
        long messageId = ParamUtil.get(renderRequest, MessageConstants.MESSAGE_ID, -1);
        ThemeDisplay themedisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themedisplay.getUserId();

        if (messageId > -1) {
            Message m = MessageLocalServiceUtil.getMessage(messageId);
            renderRequest.setAttribute(MessageConstants.MESSAGE, m);
            if (m.hasReciever(userId) && !m.isOpened(userId)) {
                m.setOpened(userId);
            }

        }

        int pagerStart = ParamUtil.getInteger(renderRequest, MessageConstants.PAGER_START, 0);
        int pagerNext = pagerStart + MessageConstants.PAGER_MAX_NUMBER;
        String tabView = ParamUtil.getString(renderRequest, MessageConstants.MESSAGE_TYPE, MessageConstants.INBOX);

        renderRequest.setAttribute(MessageConstants.MESSAGE_COUNT, MessageUtil.countMessages(userId, tabView));
        renderRequest.setAttribute(MessageConstants.MESSAGES, MessageUtil.getMessages(userId, pagerStart, pagerNext, tabView));
        
        String forward = MessageConstants.FORWARD_VIEW_MESSAGES;
        return mapping.findForward(forward);


    }
}
