/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration;

import com.liferay.portal.PortalException;

/**
 * <a href="NoSuchMigrationMappingException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NoSuchMigrationMappingException extends PortalException {

	public NoSuchMigrationMappingException() {
		super();
	}

	public NoSuchMigrationMappingException(String msg) {
		super(msg);
	}

	public NoSuchMigrationMappingException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMigrationMappingException(Throwable cause) {
		super(cause);
	}

}