/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchEntityException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchEntityException extends PortalException {

	public NoSuchEntityException() {
		super();
	}

	public NoSuchEntityException(String msg) {
		super(msg);
	}

	public NoSuchEntityException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchEntityException(Throwable cause) {
		super(cause);
	}

}