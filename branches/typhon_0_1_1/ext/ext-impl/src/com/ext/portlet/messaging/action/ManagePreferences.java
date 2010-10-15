/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging.action;

import com.ext.portlet.mass_messaging.service.persistence.MessagingMessageUtil;
import com.ext.portlet.messaging.MessageConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.model.MessagingUserPreferences;
import com.ext.portlet.messaging.service.MessageLocalServiceUtil;
import com.ext.portlet.messaging.service.MessagingUserPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.ActionConstants;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.portlet.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jintrone
 * @date 01/19/2010
 * @version 1.0
 */
public class ManagePreferences extends PortletAction {


    @Override
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        ThemeDisplay themedisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themedisplay.getUserId();
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(userId);
        renderRequest.setAttribute(MessageConstants.MESSAGE_PREFERENCES,prefs);
        
        String forward = MessageConstants.FORWARD_VIEW_MESSAGE_PREFERENCES;
        return mapping.findForward(forward);


    }



    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
                              ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        ThemeDisplay themedisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themedisplay.getUserId();
        String redirect = ParamUtil.getString(actionRequest, MessageConstants.REDIRECT);
        boolean copyonreceipt = ParamUtil.getBoolean(actionRequest,MessageConstants.MESSAGING_PREF_COPY_ON_RECEIPT,false);
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(userId);
        if (copyonreceipt!=prefs.getEmailOnReceipt()) {
            prefs.setEmailOnReceipt(copyonreceipt);
            MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);

        }
        actionResponse.sendRedirect(redirect);

    }

}
