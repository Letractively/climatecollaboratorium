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

public class ArgumentMessageEntity extends MessageEntity {

	private static final Object ARGUMENT_PRO = "PRO";
	
	private final ArgumentEntity argument;
	
	protected ArgumentMessageEntity(ArgumentEntity argument, MigrationManager manager) {
		super(manager);
		this.argument = argument;
	}
	
	@Override
	public List<MessageEntity> getChildren() {
		List<MessageEntity> ret = new ArrayList<MessageEntity>();
		if (hasChildren()) {
			for(ArgumentEntity childArgument: manager.getArgumentChildren().get(argument)) {
				MessageEntity message = MessageEntity.newInstance(childArgument, manager);
				ret.add(message);
			}
		}
		return ret;
	}

	@Override
	public Date getCreateDate() {
		return new Date(Long.parseLong(argument.getCreated()));
	}

	@Override
	public String getFullBody() {
		return argument.getInformation();
	}

	@Override
	public String getFullSubject() {
		return argument.getName();
	}

	@Override
	public Date getModifiedDate() {
		return new Date(Long.parseLong(argument.getUpdated()));
	}

	@Override
	protected String getOldUserId() {
		return argument.getAuthor();
	}

	@Override
	public boolean hasChildren() {
		return manager.getArgumentChildren().containsKey(argument);
	}

	@Override
	public boolean isThread() {
		return false;
	}
	
	@Override
	public int getType() {
		if (argument.getValence().equals(ARGUMENT_PRO)) {
			return DebatesConstants.ARGUMENT_PRO_MSG_TYPE;
		}
		return DebatesConstants.ARGUMENT_CON_MSG_TYPE;
	}
	
	@Override
	public String getEntityType() {
		return argument.NAME;	
	}

	@Override
	public String getEntityId() {
		return argument.getId();
	}

}
