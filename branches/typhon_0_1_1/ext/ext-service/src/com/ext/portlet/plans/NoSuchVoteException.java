/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchVoteException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchVoteException extends PortalException {

	public NoSuchVoteException() {
		super();
	}

	public NoSuchVoteException(String msg) {
		super(msg);
	}

	public NoSuchVoteException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchVoteException(Throwable cause) {
		super(cause);
	}

}