/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchModelDiscussionException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchModelDiscussionException extends PortalException {

	public NoSuchModelDiscussionException() {
		super();
	}

	public NoSuchModelDiscussionException(String msg) {
		super(msg);
	}

	public NoSuchModelDiscussionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchModelDiscussionException(Throwable cause) {
		super(cause);
	}

}