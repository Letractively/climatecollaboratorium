/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ext.portlet.debates.DebatesConstants;

public class QuestionMessageEntity extends MessageEntity {

	private final QuestionEntity question;
	
	protected QuestionMessageEntity(QuestionEntity question, MigrationManager manager) {
		super(manager);
		this.question = question;	
	}
	
	@Override
	public List<MessageEntity> getChildren() {
		List<MessageEntity> ret = new ArrayList<MessageEntity>();
		if (hasChildren()) {
			for(AlternativeEntity alternative: manager.getQuestionChildren().get(question)) {
				MessageEntity message = MessageEntity.newInstance(alternative, manager);
				ret.add(message);
			}
		}
		return ret;
	}

	@Override
	public Date getCreateDate() {
		return new Date(Long.parseLong(question.getCreated()));
	}

	@Override
	public String getFullBody() {
		return question.getInformation();
	}

	@Override
	public String getFullSubject() {
		return question.getInformation();
	}

	@Override
	public Date getModifiedDate() {
		return new Date(Long.parseLong(question.getUpdated()));
	}

	@Override
	protected String getOldUserId() {
		return question.getAuthor();
	}

	@Override
	public boolean hasChildren() {
		return manager.getQuestionChildren().containsKey(question);
	}

	@Override
	public boolean isThread() {
		return true;
	}
	
	@Override
	public int getType() {
		return DebatesConstants.ISSUE_MSG_TYPE;
	}
	
	@Override
	public String getEntityType() {
		return question.NAME;	
	}

	@Override
	public String getEntityId() {
		return question.getId();
	}

}
