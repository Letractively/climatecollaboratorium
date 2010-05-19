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
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jintrone
 * @date 01/19/2010
 * @version 1.0
 */
public class MoveMessages extends PortletAction {

    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
                              ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        Set<Long> messages = new HashSet<Long>();
        ThemeDisplay themedisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themedisplay.getUserId();
        String redirect = ParamUtil.getString(actionRequest, MessageConstants.REDIRECT);
        String[] msgs = ParamUtil.getString(actionRequest,MessageConstants.MOVE_MESSAGE_IDS,"").split(",");

            for (String s : msgs) {
                if (s.trim().length() == 0) continue;
                long messageId = Long.parseLong(s);
                Message m = MessageLocalServiceUtil.getMessage(messageId);
                if (!m.isArchived(userId)) {
                    m.setArchived(userId);
                }


            }
        actionResponse.sendRedirect(redirect);
    }

}