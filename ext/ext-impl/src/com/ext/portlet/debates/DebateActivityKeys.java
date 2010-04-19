/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debates;

import com.ext.portlet.Activity.SubscriberFactory;
import com.ext.portlet.Activity.SubscriptionProvider;
import com.liferay.portal.SystemException;

public enum DebateActivityKeys implements SubscriberFactory {
	
	ALL("A debate activity"),
	ADD_QUESTION("Question added"), EDIT_QUESTION("Question edited"), REMOVE_QUESTION("Question removed"),
	ADD_POSITION("Position added"), EDIT_POSITION("Position edited"), REMOVE_POSITION("Position removed"),
	ADD_ARGUMENT("Argument added"), EDIT_ARGUMENT("Argument edited"), REMOVE_ARGUMENT("Argument removed"),
	VOTE_FOR_POSITION("Voted for positions"), RETRACT_VOTE_FOR_POSITION("Retracted vote for position");
	
	private String prettyName;
	
	private DebateActivityKeys(String name) {
		this.prettyName = name;
	}
	
	public String getPrettyName() {
		return prettyName;
	}
	
	public int id() {
		return ordinal();
	}
	
	public static DebateActivityKeys fromId(int id) {
		return DebateActivityKeys.values()[id];
	}
	
	public void subcribe(long userid, long entityid, SubscriptionProvider service) throws SystemException {
		if (this == ALL) {
			for (DebateActivityKeys key:values()) {
				if (key == ALL) continue;
				else key.subcribe(userid,entityid,service);
			}
		}
		service.createSubscription("debates", userid, entityid,ordinal());
	}

	public void unsubcribe(long userid, long entityid, SubscriptionProvider service) throws SystemException {
		if (this == ALL) {
			for (DebateActivityKeys key:values()) {
				if (key == ALL) continue;
				else key.unsubcribe(userid,entityid,service);
			}
		}
		service.deleteSubscription("debates", userid, entityid,ordinal());
		
	}

     public boolean isSubscribed(long userid, long entityid, SubscriptionProvider service) throws SystemException {
       if (this == ALL) {
			for (DebateActivityKeys key:values()) {
				if (key == ALL) continue;
				if (!key.isSubscribed(userid,entityid,service)) return false;
			}
		}
		return service.isSubscribed("debates", userid, entityid,ordinal());
    }
	
}
	
	
	
//	public static final int ADD_QUESTION = 1;
//	public static final int EDIT_QUESTION = 2;
//	public static final int REMOVE_QUESTION = 3;
//	
//	public static final int ADD_POSITION = 4;
//	public static final int EDIT_POSITION = 5;
//	public static final int REMOVE_POSITION = 6;
//	
//	public static final int ADD_ARGUMENT = 7;
//	public static final int EDIT_ARGUMENT = 8;
//	public static final int REMOVE_ARGUMENT = 9;
//	
//	public static final int VOTE_FOR_POSITION = 10;
//	public static final int RETRACT_VOTE_FOR_POSITION = 11;
	

