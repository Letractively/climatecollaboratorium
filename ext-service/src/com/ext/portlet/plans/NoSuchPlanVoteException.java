/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchPlanVoteException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchPlanVoteException extends PortalException {

	public NoSuchPlanVoteException() {
		super();
	}

	public NoSuchPlanVoteException(String msg) {
		super(msg);
	}

	public NoSuchPlanVoteException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchPlanVoteException(Throwable cause) {
		super(cause);
	}

}