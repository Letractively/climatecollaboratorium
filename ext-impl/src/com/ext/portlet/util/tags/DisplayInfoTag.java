/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.util.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ext.portlet.community.CommunityUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class DisplayInfoTag extends TagSupport {
	
	private String helpText = null;
	
	public void setHelpText(String text) {
		this.helpText = text;
	}
	
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print(helpText);
		} catch (IOException ioe) {
			throw new JspException("Error: IOException while writing to client" + ioe.getMessage());
		} 
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
