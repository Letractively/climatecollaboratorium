/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchUserPreferencesException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchUserPreferencesException extends PortalException {

	public NoSuchUserPreferencesException() {
		super();
	}

	public NoSuchUserPreferencesException(String msg) {
		super(msg);
	}

	public NoSuchUserPreferencesException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchUserPreferencesException(Throwable cause) {
		super(cause);
	}

}