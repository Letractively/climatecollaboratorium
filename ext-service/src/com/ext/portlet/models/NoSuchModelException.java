/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchModelException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchModelException extends PortalException {

	public NoSuchModelException() {
		super();
	}

	public NoSuchModelException(String msg) {
		super(msg);
	}

	public NoSuchModelException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchModelException(Throwable cause) {
		super(cause);
	}

}