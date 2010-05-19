/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchDataException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchDataException extends PortalException {

	public NoSuchDataException() {
		super();
	}

	public NoSuchDataException(String msg) {
		super(msg);
	}

	public NoSuchDataException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchDataException(Throwable cause) {
		super(cause);
	}

}