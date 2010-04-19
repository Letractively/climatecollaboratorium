/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debates.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.debates.DebatesConstants;
import com.ext.portlet.debates.DebatesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portlet.messageboards.model.MBMessage;



public class AddDebateDiscussionAction extends PortletAction {

    private static Log _log = LogFactoryUtil.getLog(AddDebateDiscussionAction.class);

	 public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
	            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		 long debateMessageId = ParamUtil.getLong(actionRequest, DebatesConstants.DEBATE_MESSAGE_ID,-1);
		 MBMessage mesg = DebatesUtil.createDebateMessageThread(debateMessageId,actionRequest);
		 String redirect = ParamUtil.getString(actionRequest, "redirect");
         _log.debug("Received redirect string of "+redirect);
		 redirect+="&_debates_messageId="+mesg.getMessageId();
         actionResponse.sendRedirect(redirect);
	 }
}
