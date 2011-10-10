/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchMessagingMessageReceipientException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchMessagingMessageReceipientException extends PortalException {

	public NoSuchMessagingMessageReceipientException() {
		super();
	}

	public NoSuchMessagingMessageReceipientException(String msg) {
		super(msg);
	}

	public NoSuchMessagingMessageReceipientException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMessagingMessageReceipientException(Throwable cause) {
		super(cause);
	}

}