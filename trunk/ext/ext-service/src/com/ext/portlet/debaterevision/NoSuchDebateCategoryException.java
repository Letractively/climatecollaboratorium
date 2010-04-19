/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchDebateCategoryException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchDebateCategoryException extends PortalException {

	public NoSuchDebateCategoryException() {
		super();
	}

	public NoSuchDebateCategoryException(String msg) {
		super(msg);
	}

	public NoSuchDebateCategoryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchDebateCategoryException(Throwable cause) {
		super(cause);
	}

}