/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.entities;

public class VoteEntity {
	
	public final static String NAME = "Votes";
	
	private String alternative;
	private String id;
	private String user;
	public String getAlternative() {
		return alternative;
	}
	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
