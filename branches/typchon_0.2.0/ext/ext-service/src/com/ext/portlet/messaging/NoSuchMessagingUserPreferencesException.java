/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchMessagingUserPreferencesException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchMessagingUserPreferencesException extends PortalException {

	public NoSuchMessagingUserPreferencesException() {
		super();
	}

	public NoSuchMessagingUserPreferencesException(String msg) {
		super(msg);
	}

	public NoSuchMessagingUserPreferencesException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMessagingUserPreferencesException(Throwable cause) {
		super(cause);
	}

}