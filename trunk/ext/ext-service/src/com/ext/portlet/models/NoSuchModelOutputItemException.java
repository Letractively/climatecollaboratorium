/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchModelOutputItemException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchModelOutputItemException extends PortalException {

	public NoSuchModelOutputItemException() {
		super();
	}

	public NoSuchModelOutputItemException(String msg) {
		super(msg);
	}

	public NoSuchModelOutputItemException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchModelOutputItemException(Throwable cause) {
		super(cause);
	}

}