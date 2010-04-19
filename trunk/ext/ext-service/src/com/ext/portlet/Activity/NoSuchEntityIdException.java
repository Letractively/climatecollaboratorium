/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchEntityIdException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchEntityIdException extends PortalException {

	public NoSuchEntityIdException() {
		super();
	}

	public NoSuchEntityIdException(String msg) {
		super(msg);
	}

	public NoSuchEntityIdException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchEntityIdException(Throwable cause) {
		super(cause);
	}

}