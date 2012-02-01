/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.entities;

public class AlternativeEntity {

    public final static String PLAN_QUESTION_ID = "qid1";
	public final static String NAME = "Alternative";

	private String belief;
	private String created;
	private String id;
	private String information;
	private String nodeupdated;
	private String question;
	private String updated;
	private String user;
	private String userContent;
	private String name;
	
	public String getBelief() {
		return belief;
	}
	public void setBelief(String belief) {
		this.belief = belief;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getNodeupdated() {
		return nodeupdated;
	}
	public void setNodeupdated(String nodeupdated) {
		this.nodeupdated = nodeupdated;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserContent() {
		return userContent;
	}
	public void setUserContent(String userContent) {
		this.userContent = userContent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isPlan() {
	    return this.question.equals(PLAN_QUESTION_ID);
	}
}
