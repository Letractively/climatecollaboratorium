/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchMessageRecipientException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchMessageRecipientException extends PortalException {

	public NoSuchMessageRecipientException() {
		super();
	}

	public NoSuchMessageRecipientException(String msg) {
		super(msg);
	}

	public NoSuchMessageRecipientException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMessageRecipientException(Throwable cause) {
		super(cause);
	}

}