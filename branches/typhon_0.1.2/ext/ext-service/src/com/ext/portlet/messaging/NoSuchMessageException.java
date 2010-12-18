/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchMessageException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchMessageException extends PortalException {

	public NoSuchMessageException() {
		super();
	}

	public NoSuchMessageException(String msg) {
		super(msg);
	}

	public NoSuchMessageException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMessageException(Throwable cause) {
		super(cause);
	}

}