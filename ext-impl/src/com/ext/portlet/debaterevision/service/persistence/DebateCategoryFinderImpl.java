/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.impl.DebateImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author: jintrone
 * @date: Mar 17, 2010
 */
public class DebateCategoryFinderImpl extends BasePersistenceImpl implements DebateCategoryFinder {

    public static String Q1_CATEGORY_CHILDREN = DebateCategoryFinderImpl.class.getName() + ".categoryDebates";
    public static int PARAM_Q1_CATEGORY_ID = 0;

    public List<Debate> getCategoryDebates(long categoryId) {
        Session session = openSession();
        String sql = CustomSQLUtil.get(Q1_CATEGORY_CHILDREN);
        SQLQuery query = session.createSQLQuery(sql);
        query.setLong(PARAM_Q1_CATEGORY_ID, categoryId);
        query.addEntity("Debate", DebateImpl.class);
        List<Debate> items = (List<Debate>) QueryUtil.list(query, getDialect(), 0, Integer.MAX_VALUE);
        return items;

    }

}