/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchDebateCategoryMapException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchDebateCategoryMapException extends PortalException {

	public NoSuchDebateCategoryMapException() {
		super();
	}

	public NoSuchDebateCategoryMapException(String msg) {
		super(msg);
	}

	public NoSuchDebateCategoryMapException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchDebateCategoryMapException(Throwable cause) {
		super(cause);
	}

}