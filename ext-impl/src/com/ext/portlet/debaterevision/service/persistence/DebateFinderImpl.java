/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.impl.DebateCommentImpl;
import com.ext.portlet.debaterevision.model.impl.DebateImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author: jintrone
 * @date: Mar 17, 2010
 */
public class DebateFinderImpl extends BasePersistenceImpl implements DebateFinder {

    public static String Q1_MOST_RECENT_VERSION = DebateFinderImpl.class.getName() + ".lastDebateVersion";
    public static int PARAM_Q1_DEBATE_ID = 0;

    public static String Q2_COUNT_COMMENTS = DebateFinderImpl.class.getName() + ".countDebateComments";
    public static int PARAM_Q2_DEBATE_ID = 0;
    public static String OUTPUT_Q2_COUNT = "comments";

    public static String Q3_DEBATE_COMMENTS = DebateFinderImpl.class.getName() + ".debateComments";
    public static int PARAM_Q3_DEBATE_ID = 0;

     public static String Q4_ALL_RECENT_ACTIVE = DebateFinderImpl.class.getName() + ".activeRecentDebates";



    public Debate getMostRecentVersion(long debateId) {
        Session session = openSession();
        String sql = CustomSQLUtil.get(Q1_MOST_RECENT_VERSION);
        SQLQuery query = session.createSQLQuery(sql);
        query.setLong(PARAM_Q1_DEBATE_ID, debateId);

        query.addEntity("Debate", DebateImpl.class);
        List<Debate> items = (List<Debate>) QueryUtil.list(query, getDialect(), 0, 1);
        if (items != null && items.size() > 0) {
            return items.get(0);
        } else {
            return null;
        }

    }

    public int getNumberOfComments(long debateId) {
        Session session = openSession();
        String sql = CustomSQLUtil.get(Q2_COUNT_COMMENTS);
        SQLQuery query = session.createSQLQuery(sql);
        query.setLong(PARAM_Q2_DEBATE_ID, debateId);
        query.addScalar(OUTPUT_Q2_COUNT, Type.INTEGER);

        Iterator<Integer> itr = query.list().iterator();
        if (itr.hasNext()) {
            Integer count = itr.next();

            if (count != null) {
                return count.intValue();
            }
        }
        return 0;
    }

    public DebateComment getMostRecentComment(long debateId) {
       Session session = openSession();
        String sql = CustomSQLUtil.get(Q3_DEBATE_COMMENTS);
        SQLQuery query = session.createSQLQuery(sql);
        query.setLong(PARAM_Q3_DEBATE_ID, debateId);
        query.addEntity("DebateComment", DebateCommentImpl.class);
        List<DebateComment> items = (List<DebateComment>) QueryUtil.list(query, getDialect(), 0, 1);
        if (items != null && items.size() > 0) {
            return items.get(0);
        } else {
            return null;
        }
    }

    public List<Debate> getAllRecentActiveDebates() {
         Session session = openSession();
            String sql = CustomSQLUtil.get(Q4_ALL_RECENT_ACTIVE);
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity("Debate", DebateImpl.class);
        return (List<Debate>) query.list();
    }

}
