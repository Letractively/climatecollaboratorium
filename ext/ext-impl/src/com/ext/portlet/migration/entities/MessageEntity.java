/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.entities;

import java.util.Date;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ext.portlet.migration.MigrationHelper;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public abstract class MessageEntity {
	private final static int MAX_SUBJECT_LEN = 75;
	private final static Pattern HTML_STRIPING_PATTERN = Pattern.compile("<(.|\n)*?>");
    private final static Pattern FONT_STRIPING_PATTERN = Pattern.compile("<font(.|\n)*?>|</font>", Pattern.CASE_INSENSITIVE);
    private final static Pattern TEXTFORMAT_STRIPING_PATTERN = Pattern.compile("<textformat(.|\n)*?>|</textformat>", Pattern.CASE_INSENSITIVE);
	
	
	protected MigrationManager manager;
	
	
	MessageEntity(MigrationManager manager) {
		this.manager = manager;
	}
	
	public abstract boolean isThread();
	
	public abstract String getFullSubject();
	
	public String getSubject() {
	    
		String subject = getFullSubject();
		if (subject == null || subject.trim().equals("")) {
			subject = getFullBody();
		}
		

		Matcher matcher = HTML_STRIPING_PATTERN.matcher(subject);
		subject = matcher.replaceAll("");
		
		int length = subject.length();
		
		
		return subject.substring(0, length < MAX_SUBJECT_LEN ? length : MAX_SUBJECT_LEN);
	}
	
	public abstract String getFullBody();
	
	public String getBody() {
		String body = getFullBody();
		if (body == null || body.trim().equals("")) {
			body = getFullSubject();
		}
		
		Matcher matcher = FONT_STRIPING_PATTERN.matcher(body);
        body = matcher.replaceAll("");
        matcher = TEXTFORMAT_STRIPING_PATTERN.matcher(body);
        body = matcher.replaceAll("");
		
		return body;
	}
	
	public abstract boolean hasChildren();
	
	public abstract List<MessageEntity> getChildren();
	
	protected abstract String getOldUserId();
	
	public User getOwner() throws PortalException, SystemException {
		long userId = MigrationHelper.getNewIdFromMapping(UserEntity.NAME, getOldUserId());
		return UserLocalServiceUtil.getUser(userId);
	}
	
	public abstract Date getCreateDate();
	public abstract Date getModifiedDate();
	
	public static MessageEntity newInstance(QuestionEntity question, MigrationManager manager) {
		return new QuestionMessageEntity(question, manager);
	}
	
	public static MessageEntity newInstance(AlternativeEntity alternative, MigrationManager manager) {
		return new AlternativeMessageEntity(alternative, manager);
	}
	
	public static MessageEntity newInstance(ArgumentEntity argument, MigrationManager manager) {
		return new ArgumentMessageEntity(argument, manager);
	}
	
	public abstract int getType();
	
	public abstract String getEntityType();
	
	public abstract String getEntityId();
}
