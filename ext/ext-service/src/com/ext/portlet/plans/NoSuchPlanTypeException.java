/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchPlanException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchPlanTypeException extends PortalException {

	public NoSuchPlanTypeException() {
		super();
	}

	public NoSuchPlanTypeException(String msg) {
		super(msg);
	}

	public NoSuchPlanTypeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchPlanTypeException(Throwable cause) {
		super(cause);
	}

}