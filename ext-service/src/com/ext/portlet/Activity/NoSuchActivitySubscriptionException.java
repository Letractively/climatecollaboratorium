/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchActivitySubscriptionException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchActivitySubscriptionException extends PortalException {

	public NoSuchActivitySubscriptionException() {
		super();
	}

	public NoSuchActivitySubscriptionException(String msg) {
		super(msg);
	}

	public NoSuchActivitySubscriptionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchActivitySubscriptionException(Throwable cause) {
		super(cause);
	}

}