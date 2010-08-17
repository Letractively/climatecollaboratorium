/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import org.climatecollaboratorium.test.BaseCollabTest;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 16, 2010
 * Time: 9:59:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlanVoteTest extends BaseCollabTest {



    public void testPlanVoteByType() throws SystemException, PortalException {
        PlanType type = PlanTypeLocalServiceUtil.getPlanType(2L);
        System.err.println("Votes for plan type 2 "+PlanVoteLocalServiceUtil.countPlanVotes(type));


    }

}
