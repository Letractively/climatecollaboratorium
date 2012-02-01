/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.contest;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.climatecollaboratorium.test.BaseCollabTest;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 9, 2010
 * Time: 4:38:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestServiceTest extends BaseCollabTest {


    public void testService() throws SystemException, PortalException {
        List<Contest> contests = ContestLocalServiceUtil.getContests(0,Integer.MAX_VALUE);
        for (Contest contest:contests) {
           System.err.println("Contest: "+contest.getContestName());

        for (ContestPhase phase:contest.getPhases()) {
            System.err.println("Contest phase: "+phase.getContestPhaseName());
            System.err.println("Number of plans: "+phase.getPlans().size());
        }
        }

    }

    public void testUsers() throws SystemException, PortalException {
        List<User> users = UserLocalServiceUtil.getUsers(0,Integer.MAX_VALUE);
        for (User u:users) {
            System.err.println(u.getScreenName()+" - "+u.getGroup());
        }
    }

}
