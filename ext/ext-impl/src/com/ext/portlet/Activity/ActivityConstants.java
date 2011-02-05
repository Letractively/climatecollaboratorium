/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.ext.portlet.debates.DebateActivityKeys;
import com.ext.portlet.plans.PlanActivityKeys;

public class ActivityConstants {
	
	//public static final SubscriberFactory[] SUBS_DEFAULTS = new SubscriberFactory[] { DebateActivityKeys.ALL, PlanActivityKeys.ALL };
	
	public static final String PAGER_START = "pagerStart";
	public static final String PAGER_MAX = "pageMax";
	public static final String VIEW_FORWARD = "portlet.ext.activities.view";
	public static final String SUBSCRIPTIONS_PARAMETER = "subscriptionsParam";
	public static final String MANAGE_SUBSCRIPTIONS_FORWARD = "portlet.ext.activities.manage_subs";
	public static final String REDIRECT = "activityRedirect";

	public static final String SUBSCRIPTIONS_USER_PARAMETER = "subscriptionsUserParametert";

	public static final int PAGER_MAX_NUMBER = 10;

	public static final String ACTIVITIES_PARAMETER = "activitiesParameter";

	public static final String ACTIVITY_COUNT_PARAMETER = "activitiesCountParameter";

	public static final String RESET = "resetActivitySubscriptions";

	public static final String SUBSCRIPTION_MODE = "activitySubscriptionMode";;
	
	/**
	 * Constants used for denoting message types when adding activities for message boards messages added 
	 * in various places accross the site.
	 */
	
	/**
	 * Message added to a Plan discussion board. 
	 */
	public static final int PLAN_MESSAGE = 1;
	
	/**
	 * Message added to Model discussion board.
	 */
	public static final int MODEL_MESSAGE = 2;
	
	/**
	 * Message added to any of Debates discussion boards.
	 */
	public static final int DEBATES_DISCUSSION_MESSAGE = 3;

	/**
     * Message added to one of Debates.
     */
	public static final int DEBATES_DEBATE_MESSAGE = 4;
	
	
	public static final String MESSAGE_TYPE = "messageType";
	
	public static final String PLAN_ID = "planId";
	
    public static final String MODEL_ID = "modelId";
    
    public static final String MODEL_NAME = "modelName";
	
	    
	
	
	public static enum SubscriptionMode {
		USER,PERSONAL,GLOBAL;
	}
	

}
