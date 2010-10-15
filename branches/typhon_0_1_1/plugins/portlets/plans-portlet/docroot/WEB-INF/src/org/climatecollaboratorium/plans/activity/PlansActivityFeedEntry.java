/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans.activity;



import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

import java.util.HashMap;
import java.util.Map;


public class PlansActivityFeedEntry extends BaseSocialActivityInterpreter{

    private static Log _log =
		 LogFactoryUtil.getLog(PlansActivityFeedEntry.class);

	public static String PLAN_ADDED = "%s added plan %s";
	public static String PLAN_REMOVED = "%s removed plan %s";
	public static String PLAN_POSITIONS_UPDATED = "%s updated positions in plan %s";
	public static String PLAN_SCENARIO_UPDATED = "%s updated the model run in plan %s";
	public static String PLAN_DESCRIPTION_UPDATED="%s updated the description in plan %s";
	public static String PLAN_VOTED = "%s voted for plan %s";
	public static String PLAN_UNVOTED = "%s retracted for for plan %s";
	public static String PLAN_VOTE_SWITCHED="%s switched vote to plan %s";
	public static String PLAN_USER_ADDED="%s joined plan %s";
	public static String PLAN_USER_REMOVED = "%s left plan %s";
	public static String PLAN_PUBLISHED = "%s published plan %s";
    public static String PLAN_NAME_UPDATED="%s updated the name in plan %s";

	public static Map<PlanActivityKeys,String> msgMap = new HashMap<PlanActivityKeys,String>();
	static {
		msgMap.put(PlanActivityKeys.ADD_PLAN,PLAN_ADDED);
		msgMap.put(PlanActivityKeys.EDIT_DESCRIPTION,PLAN_DESCRIPTION_UPDATED);
		msgMap.put(PlanActivityKeys.EDIT_POSITIONS, PLAN_POSITIONS_UPDATED);
		msgMap.put(PlanActivityKeys.EDIT_SCENARIO,PLAN_SCENARIO_UPDATED);
		msgMap.put(PlanActivityKeys.VOTE_FOR_PLAN, PLAN_VOTED);
		msgMap.put(PlanActivityKeys.RETRACT_VOTE_FOR_PLAN, PLAN_UNVOTED);
		msgMap.put(PlanActivityKeys.SWICTH_VOTE_FOR_PLAN, PLAN_VOTE_SWITCHED);
		msgMap.put(PlanActivityKeys.USER_ADDED_TO_PLAN, PLAN_USER_ADDED);
		msgMap.put(PlanActivityKeys.USER_REMOVED_FROM_PLAN,PLAN_USER_REMOVED);
		msgMap.put(PlanActivityKeys.PUBLISH_UPDATES, PLAN_PUBLISHED);
        msgMap.put(PlanActivityKeys.EDIT_NAME, PLAN_NAME_UPDATED);
        msgMap.put(PlanActivityKeys.REMOVE_PLAN, PLAN_REMOVED);

	}

	public static String hyperlink = "<a href=\"%s\">%s</a>";

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}


	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PlanActivityKeys activityType = PlanActivityKeys.fromId(activity.getType());
		String title = activityType.getPrettyName();
		String body = "";
		if (msgMap.containsKey(activityType)) {
			body = String.format(msgMap.get(activityType),getUser(activity),getPlan(activity));
		}

		return new SocialActivityFeedEntry("", title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
	    "com.ext.portlet.Activity", "com.ext.portlet.plans.model.PlanItem"
	};

	private String getUser(SocialActivity activity) {
		String user = "&lt;user removed&gt;";

			try {
				user = CommunityUtil.generateUserURL(activity.getUserId());
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				_log.info(e.getMessage());
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				_log.info(e.getMessage());
			}
		return user;
	}

	private String getPlan(SocialActivity activity) {
		String result = "&lt;plan removed&gt;";
		try {
			PlanItem p = PlanItemLocalServiceUtil.getPlan(activity.getClassPK());
			result = String.format(hyperlink, getPlanURL(p),p.getName());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			_log.info(e.getMessage());
		} catch (SystemException e) {
			_log.info(e.getMessage());
		}
		return result;

	}
	

    private static String getPlanURL(PlanItem p) throws SystemException, PortalException {
        return String.format("/web/guest/plans/-/plans/contestId/" + p.getContest().getContestPK() + "/planId/" + p.getPlanId());
    }

}