/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchMessagingMessageConversionException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchMessagingMessageConversionException extends PortalException {

	public NoSuchMessagingMessageConversionException() {
		super();
	}

	public NoSuchMessagingMessageConversionException(String msg) {
		super(msg);
	}

	public NoSuchMessagingMessageConversionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMessagingMessageConversionException(Throwable cause) {
		super(cause);
	}

}