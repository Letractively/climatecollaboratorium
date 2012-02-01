/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.impl.DebateItemImpl;
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
public class DebateItemFinderImpl extends BasePersistenceImpl implements DebateItemFinder {

    public static String Q1_LAST_ITEM_IN_VERSION = DebateItemFinderImpl.class.getName()+".lastItemInVersion";
    public static int PARAM_Q1_TREE_VERSION  = 0;
    public static int PARAM_Q1_ITEM_ID = 1;


    public static String Q2_LAST_ITEMS_IN_VERSION_BY_PARENT = DebateItemFinderImpl.class.getName()+".lastItemByParent";
    public static int PARAM_Q2_TREE_VERSION  = 0;
    public static int PARAM_Q2_PARENT_ITEM_ID = 1;

    public static String Q3_LAST_ACTIVE_ITEM = DebateItemFinderImpl.class.getName()+".lastActiveItem";
    public static int PARAM_Q3_ITEM_ID  = 0;

    public static String Q4_LAST_ITEM = DebateItemFinderImpl.class.getName()+".lastItem";
    public static int PARAM_Q4_ITEM_ID  = 0;
    
    public static String Q5_ITEM_COMMENTS_COUNT = DebateItemFinderImpl.class.getName()+".countDebateItemComments";
    public static int PARAM_Q5_ITEM_ID  = 0;
    public static String OUTPUT_Q5_COUNT = "comments";


    public DebateItem getLastItem(long itemId) {

       Session session = openSession();
       String sql = CustomSQLUtil.get(Q4_LAST_ITEM);
       SQLQuery query = session.createSQLQuery(sql);
       query.setLong(PARAM_Q4_ITEM_ID,itemId);

       query.addEntity("DebateItem", DebateItemImpl.class);
       List<DebateItem> items = (List<DebateItem>) QueryUtil.list(query,getDialect(),0,1);
       if (items!=null && items.size() > 0) {
           DebateItem result =  items.get(0);
           result.setTreeVersion(DebateFinderUtil.getMostRecentVersion(result.getDebateId()).getTreeVersion());
           return result;
       } else {
           return null;
       }


    }

    public DebateItem getLastActiveItem(long itemId) {

       Session session = openSession();
       String sql = CustomSQLUtil.get(Q3_LAST_ACTIVE_ITEM);
       SQLQuery query = session.createSQLQuery(sql);
       query.setLong(PARAM_Q3_ITEM_ID,itemId);

       query.addEntity("DebateItem", DebateItemImpl.class);
       List<DebateItem> items = (List<DebateItem>) QueryUtil.list(query,getDialect(),0,1);
       if (items!=null && items.size() > 0) {
           DebateItem result =  items.get(0);
           result.setTreeVersion(DebateFinderUtil.getMostRecentVersion(result.getDebateId()).getTreeVersion());
           result.setDebateVersion(result.getTreeVersion());
           return result;
       } else {
           return null;
       }


    }

    public DebateItem getLastItemInVersion(long treeVersion, long itemId) {

       Session session = openSession();
       String sql = CustomSQLUtil.get(Q1_LAST_ITEM_IN_VERSION);
       SQLQuery query = session.createSQLQuery(sql);
       query.setLong(PARAM_Q1_TREE_VERSION,treeVersion);
       query.setLong(PARAM_Q1_ITEM_ID,itemId);

       query.addEntity("DebateItem", DebateItemImpl.class);
       List<DebateItem> items = (List<DebateItem>) QueryUtil.list(query,getDialect(),0,1);
       if (items!=null && items.size() > 0) {
           DebateItem result =  items.get(0);
           result.setDebateVersion(treeVersion);
           return result;
       } else {
           return null;
       }


    }

    /**
     * Returns the history for this item up until (and including) tree version change
     * Passing the most recent tree version will retrieve the entire history of this item
     * @param treeVersion
     * @param itemId
     * @return
     */
    public List<DebateItem> getHistory(long treeVersion, long itemId) {

       Session session = openSession();
       String sql = CustomSQLUtil.get(Q1_LAST_ITEM_IN_VERSION);
       SQLQuery query = session.createSQLQuery(sql);
       query.setLong(PARAM_Q1_TREE_VERSION,treeVersion);
       query.setLong(PARAM_Q1_ITEM_ID,itemId);

       query.addEntity("DebateItem", DebateItemImpl.class);
       return (List<DebateItem>) QueryUtil.list(query,getDialect(),0,Integer.MAX_VALUE);
    }

    public List<DebateItem> findByParentInVersion(long treeVersion, long parentId) {

       Session session = openSession();
       String sql = CustomSQLUtil.get(Q2_LAST_ITEMS_IN_VERSION_BY_PARENT);
       SQLQuery query = session.createSQLQuery(sql);
       query.setLong(PARAM_Q2_TREE_VERSION,treeVersion);
       query.setLong(PARAM_Q2_PARENT_ITEM_ID,parentId);

       query.addEntity("DebateItem", DebateItemImpl.class);
       List<DebateItem> result = (List<DebateItem>) QueryUtil.list(query,getDialect(),0,Integer.MAX_VALUE);
       for (DebateItem d:result) {
           d.setDebateVersion(treeVersion);
       }
       return result;

    }
    
    public int getDebateItemCommentsCount(long debateItemId) {

        Session session = openSession();
        String sql = CustomSQLUtil.get(Q5_ITEM_COMMENTS_COUNT);
        SQLQuery query = session.createSQLQuery(sql);
        query.setLong(PARAM_Q5_ITEM_ID, debateItemId);
        
        query.addScalar(OUTPUT_Q5_COUNT, Type.INTEGER);

        Iterator<Integer> itr = query.list().iterator();
        if (itr.hasNext()) {
            Integer count = itr.next();

            if (count != null) {
                return count.intValue();
            }
        }
        return 0;
    }

    

    
}
