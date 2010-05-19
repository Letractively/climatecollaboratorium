/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.community.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.community.action.CommunityConstants;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * Provides a link to the public user profile page in the system 
 * 
 * @author jintrone
 *
 */
public class ClimateUserTag extends TagSupport {

	private Long userId = null;

	public void setUserId(String userId) {
		this.userId = Long.parseLong(userId);
	}

	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print(CommunityUtil.generateUserURL(userId));
		} catch (IOException ioe) {
			throw new JspException("Error: IOException while writing to client" + ioe.getMessage());
		} catch (PortalException e) {
			throw new JspException("Portal exceptions retrieving user" + e.getMessage());
		} catch (SystemException e) {
			throw new JspException("System exception retrieving user" + e.getMessage());
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
