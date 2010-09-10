/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.community;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

/**
 * <a href="UserScreenNameComparator.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class UserNumberActivitiesComparator extends OrderByComparator {


	public UserNumberActivitiesComparator() {
		this(false);
	}

	public UserNumberActivitiesComparator(boolean asc) {
		_asc = asc;
	}

	public int compare(Object obj1, Object obj2) {
		User user1 = (User)obj1;
		User user2 = (User)obj2;

        int count1 = 0;
        int count2 = 0;

        try {
            count2 = SocialActivityLocalServiceUtil.getUserActivitiesCount(user2.getUserId());
            count1 = SocialActivityLocalServiceUtil.getUserActivitiesCount(user1.getUserId());

        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        int value = count1 > count2?-1:count2>count1?1:0;

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	
	private boolean _asc;

}