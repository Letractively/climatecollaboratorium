/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debates;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchDebateDiscussionMapException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchDebateDiscussionMapException extends PortalException {

	public NoSuchDebateDiscussionMapException() {
		super();
	}

	public NoSuchDebateDiscussionMapException(String msg) {
		super(msg);
	}

	public NoSuchDebateDiscussionMapException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchDebateDiscussionMapException(Throwable cause) {
		super(cause);
	}

}