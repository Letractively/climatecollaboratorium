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

public class AlternativeMessageEntity extends MessageEntity {

	private final AlternativeEntity alternative;
	
	protected AlternativeMessageEntity(AlternativeEntity alternative, MigrationManager manager) {
		super(manager);
		this.alternative = alternative;
	}
	
	@Override
	public List<MessageEntity> getChildren() {
		List<MessageEntity> ret = new ArrayList<MessageEntity>();
		if (hasChildren()) {
			for(ArgumentEntity childArgument: manager.getAlternativeChildren().get(alternative)) {
				MessageEntity message = MessageEntity.newInstance(childArgument, manager);
				ret.add(message);
			}
		}
		return ret;
	}

	@Override
	public Date getCreateDate() {
		return new Date(Long.parseLong(alternative.getCreated()));
	}

	@Override
	public String getFullBody() {
		return alternative.getInformation();
	}

	@Override
	public String getFullSubject() {
		return alternative.getName();
	}

	@Override
	public Date getModifiedDate() {
		return new Date(Long.parseLong(alternative.getUpdated()));
	}

	@Override
	protected String getOldUserId() {
		return alternative.getUser();
	}

	@Override
	public boolean hasChildren() {
		return manager.getAlternativeChildren().containsKey(alternative);
	}

	@Override
	public boolean isThread() {
		return false;
	}
	
	@Override
	public int getType() {
		return DebatesConstants.POSITION_MSG_TYPE;
	}
	
	@Override
	public String getEntityType() {
		return alternative.NAME;	
	}

	@Override
	public String getEntityId() {
		return alternative.getId();
	}
}
