/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ext.portlet.Activity.ICollabActivityInterpreter;
import com.ext.portlet.Activity.NoSuchActivitySubscriptionException;
import com.ext.portlet.Activity.NoSuchSubscriptionException;
import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.Activity.service.base.ActivitySubscriptionLocalServiceBaseImpl;
import com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityInterpreter;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class ActivitySubscriptionLocalServiceImpl extends ActivitySubscriptionLocalServiceBaseImpl {

    private Map<String, ICollabActivityInterpreter> interpreters = new HashMap<String, ICollabActivityInterpreter>();

    public List<ActivitySubscription> findByUser(Long userId) throws SystemException {
        return activitySubscriptionPersistence.findByreceiverId(userId);
    }

    public boolean isSubscribed(Long userId, Long classNameId, Long classPK, Integer type, String extraData)
            throws PortalException, SystemException {
        return activitySubscriptionPersistence.findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId, classPK,
                type, extraData, userId).size() > 0;
    }
    
    public boolean isSubscribed(Long userId, Class clasz, Long classPK, Integer type, String extraData)
    throws PortalException, SystemException {
        return isSubscribed(userId, PortalUtil.getClassNameId(clasz), classPK, type, extraData);
    }
    
    public void deleteSubscription(Long userId, Long classNameId, Long classPK, Integer type, String extraData) throws SystemException {
        List<ActivitySubscription> subscriptions = 
            activitySubscriptionPersistence.findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId, classPK, type, extraData, userId);
        for (ActivitySubscription subscription: subscriptions) {
            subscription.delete();
        }
        
    }
    
    public void deleteSubscription(Long userId, Class clasz, Long classPK, Integer type, String extraData) throws SystemException {
        deleteSubscription(userId, PortalUtil.getClassNameId(clasz), classPK, type, extraData);
    }

    public void addSubscription(Long classNameId, Long classPK, Integer type, String extraData, Long userId)
            throws PortalException, SystemException {

        if (activitySubscriptionPersistence.findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId, classPK, type,
                extraData, userId).size() > 0) {
            // subscription exists, do nothing
            return;
        }

        Long pk = CounterLocalServiceUtil.increment(ActivitySubscription.class.getName());
        ActivitySubscription subscription = ActivitySubscriptionLocalServiceUtil.createActivitySubscription(pk);

        subscription.setClassNameId(classNameId);
        subscription.setClassPK(classPK);
        subscription.setType(type);
        subscription.setExtraData(extraData);
        subscription.setReceiverId(userId);

        subscription.setModifiedDate(new Date());
        subscription.setCreateDate(new Date());

        subscription.store();
    }

    public void addSubscription(Class clasz, Long classPK, Integer type, String extraData, Long userId)
            throws PortalException, SystemException {
        Long classNameId = ClassNameLocalServiceUtil.getClassNameId(clasz);
        addSubscription(classNameId, classPK, type, extraData, userId);
    }

    public void addActivityInterpreter(ICollabActivityInterpreter interpreter) {
        for (String className : interpreter.getClassNames()) {
            interpreters.put(className, interpreter);
        }
    }

    public ICollabActivityInterpreter getInterpreterForClass(String className) {
        return interpreters.get(className);
    }

    public ICollabActivityInterpreter getInterpreterForClass(Long classNameId) {
        return getInterpreterForClass(PortalUtil.getClassName(classNameId));
    }

    public List<SocialActivity> getActivities(Long userId, int start, int count) throws SystemException {
        List<ActivitySubscription> subscriptions = null;
        // for now no activity selection is made, TODO
        subscriptions = activitySubscriptionPersistence.findByreceiverId(userId);

        if (subscriptions.size() == 0) {
            return new ArrayList<SocialActivity>();
        }

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(SocialActivity.class);
        Criterion crit = null;
        
        for (ActivitySubscription sub : subscriptions) {
            Map<String, Number> criterion = new HashMap<String, Number>();
            criterion.put("classNameId", sub.getClassNameId());
            criterion.put("classPK", sub.getClassPK());
            
            if (sub.getType() != null) {
                criterion.put("type", sub.getType());
            }
            Criterion subCriterion = RestrictionsFactoryUtil.allEq(criterion);
            
            if (sub.getExtraData() != null && sub.getExtraData().length() > 0) {
                subCriterion = RestrictionsFactoryUtil.and(subCriterion,
                        RestrictionsFactoryUtil.ilike("extraData", sub.getExtraData() + "%"));
            }

            if (crit == null) {
                crit = subCriterion;
            } else {
                crit = RestrictionsFactoryUtil.or(crit, subCriterion);
            }
        }
        query.add(crit).addOrder(OrderFactoryUtil.desc("createDate"));

        List<SocialActivity> activities = new ArrayList<SocialActivity>();
        List<Object> queryResults = SocialActivityLocalServiceUtil.dynamicQuery(query, start, start + count - 1);

        for (Object activity : queryResults) {
            activities.add((SocialActivity) activity);
        }

        return activities;

    }

}
