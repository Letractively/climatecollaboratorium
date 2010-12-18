/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.community;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Sep 9, 2010
 * Time: 11:08:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserRoleQueryFilter {


    public static List<User> filterByRoles(List<User> users, String... roles) {
        String rolestring = "";
        if (roles.length ==0) return users;
        for (String r:roles) {
            rolestring+=r;
        }
    

        for (Iterator<User> i = users.iterator();i.hasNext();) {
            User test = i.next();
            boolean flag = false;
            for(Role role:test.getRoles()) {
                if (rolestring.contains(role.getName())) {
                   flag = true;
                    break;
                }
            }
            if (!flag) {
                i.remove();
            }
           

        }
        return users;
    }

}
