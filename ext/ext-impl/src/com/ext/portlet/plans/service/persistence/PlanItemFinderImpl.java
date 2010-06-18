package com.ext.portlet.plans.service.persistence;

import java.util.List;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.impl.PlanItemImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class PlanItemFinderImpl extends BasePersistenceImpl implements PlanItemFinder {
    private final String GET_PLAN_ITEMS = PlanItemFinderImpl.class.getName() + ".getPlans";
    private final String REMOVE_PLAN_WITH_HISTORY = PlanItemFinderImpl.class.getName() + ".removePlanWithHistory";
    
    public List<PlanItem> getPlans() {


        Session session = openSession();
        String sql = CustomSQLUtil.get(GET_PLAN_ITEMS);
        SQLQuery query = session.createSQLQuery(sql);

        query.addEntity("PlanItem", PlanItemImpl.class);
        return (List<PlanItem>) QueryUtil.list(query,getDialect(),0,Integer.MAX_VALUE);
     }
    
    public void removePlanWithHistory(long planId) {


        Session session = openSession();
        String sql = CustomSQLUtil.get(REMOVE_PLAN_WITH_HISTORY);
        SQLQuery query = session.createSQLQuery(sql);

        query.setLong(0, planId);
        query.executeUpdate();
     }
}
