/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchMessagingMessageRecipientException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchMessagingMessageRecipientException extends PortalException {

	public NoSuchMessagingMessageRecipientException() {
		super();
	}

	public NoSuchMessagingMessageRecipientException(String msg) {
		super(msg);
	}

	public NoSuchMessagingMessageRecipientException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMessagingMessageRecipientException(Throwable cause) {
		super(cause);
	}

}