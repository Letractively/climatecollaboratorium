/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchSubscriptionException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchSubscriptionException extends PortalException {

	public NoSuchSubscriptionException() {
		super();
	}

	public NoSuchSubscriptionException(String msg) {
		super(msg);
	}

	public NoSuchSubscriptionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchSubscriptionException(Throwable cause) {
		super(cause);
	}

}