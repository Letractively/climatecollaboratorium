/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchFilterPositionException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchFilterPositionException extends PortalException {

	public NoSuchFilterPositionException() {
		super();
	}

	public NoSuchFilterPositionException(String msg) {
		super(msg);
	}

	public NoSuchFilterPositionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchFilterPositionException(Throwable cause) {
		super(cause);
	}

}