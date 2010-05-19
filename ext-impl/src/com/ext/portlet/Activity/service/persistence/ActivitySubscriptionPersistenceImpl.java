/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service.persistence;

import com.ext.portlet.Activity.NoSuchSubscriptionException;
import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl;
import com.ext.portlet.Activity.model.impl.ActivitySubscriptionModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ActivitySubscriptionPersistenceImpl extends BasePersistenceImpl
    implements ActivitySubscriptionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ActivitySubscriptionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_ENTITYID = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByentityId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ENTITYID = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByentityId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ENTITYID = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByentityId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_RECEIVERID = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByreceiverId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_RECEIVERID = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByreceiverId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_RECEIVERID = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByreceiverId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ACTIVITYTYPE = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByactivitytype",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ACTIVITYTYPE = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByactivitytype",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVITYTYPE = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByactivitytype",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ENTITYRECEIVER = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByentityreceiver",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ENTITYRECEIVER = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByentityreceiver",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ENTITYRECEIVER = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByentityreceiver",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ENTITYRECEIVERTYPE = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByentityreceivertype",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ENTITYRECEIVERTYPE = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByentityreceivertype",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ENTITYRECEIVERTYPE = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByentityreceivertype",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ActivitySubscriptionPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPersistence.impl")
    protected com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPersistence activitySubscriptionPersistence;

    public void cacheResult(ActivitySubscription activitySubscription) {
        EntityCacheUtil.putResult(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionImpl.class,
            activitySubscription.getPrimaryKey(), activitySubscription);
    }

    public void cacheResult(List<ActivitySubscription> activitySubscriptions) {
        for (ActivitySubscription activitySubscription : activitySubscriptions) {
            if (EntityCacheUtil.getResult(
                        ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
                        ActivitySubscriptionImpl.class,
                        activitySubscription.getPrimaryKey(), this) == null) {
                cacheResult(activitySubscription);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ActivitySubscriptionImpl.class.getName());
        EntityCacheUtil.clearCache(ActivitySubscriptionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ActivitySubscription create(
        ActivitySubscriptionPK activitySubscriptionPK) {
        ActivitySubscription activitySubscription = new ActivitySubscriptionImpl();

        activitySubscription.setNew(true);
        activitySubscription.setPrimaryKey(activitySubscriptionPK);

        return activitySubscription;
    }

    public ActivitySubscription remove(
        ActivitySubscriptionPK activitySubscriptionPK)
        throws NoSuchSubscriptionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ActivitySubscription activitySubscription = (ActivitySubscription) session.get(ActivitySubscriptionImpl.class,
                    activitySubscriptionPK);

            if (activitySubscription == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ActivitySubscription exists with the primary key " +
                        activitySubscriptionPK);
                }

                throw new NoSuchSubscriptionException(
                    "No ActivitySubscription exists with the primary key " +
                    activitySubscriptionPK);
            }

            return remove(activitySubscription);
        } catch (NoSuchSubscriptionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ActivitySubscription remove(
        ActivitySubscription activitySubscription) throws SystemException {
        for (ModelListener<ActivitySubscription> listener : listeners) {
            listener.onBeforeRemove(activitySubscription);
        }

        activitySubscription = removeImpl(activitySubscription);

        for (ModelListener<ActivitySubscription> listener : listeners) {
            listener.onAfterRemove(activitySubscription);
        }

        return activitySubscription;
    }

    protected ActivitySubscription removeImpl(
        ActivitySubscription activitySubscription) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (activitySubscription.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ActivitySubscriptionImpl.class,
                        activitySubscription.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(activitySubscription);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionImpl.class, activitySubscription.getPrimaryKey());

        return activitySubscription;
    }

    /**
     * @deprecated Use <code>update(ActivitySubscription activitySubscription, boolean merge)</code>.
     */
    public ActivitySubscription update(
        ActivitySubscription activitySubscription) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ActivitySubscription activitySubscription) method. Use update(ActivitySubscription activitySubscription, boolean merge) instead.");
        }

        return update(activitySubscription, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                activitySubscription the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when activitySubscription is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ActivitySubscription update(
        ActivitySubscription activitySubscription, boolean merge)
        throws SystemException {
        boolean isNew = activitySubscription.isNew();

        for (ModelListener<ActivitySubscription> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(activitySubscription);
            } else {
                listener.onBeforeUpdate(activitySubscription);
            }
        }

        activitySubscription = updateImpl(activitySubscription, merge);

        for (ModelListener<ActivitySubscription> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(activitySubscription);
            } else {
                listener.onAfterUpdate(activitySubscription);
            }
        }

        return activitySubscription;
    }

    public ActivitySubscription updateImpl(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, activitySubscription, merge);

            activitySubscription.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionImpl.class,
            activitySubscription.getPrimaryKey(), activitySubscription);

        return activitySubscription;
    }

    public ActivitySubscription findByPrimaryKey(
        ActivitySubscriptionPK activitySubscriptionPK)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = fetchByPrimaryKey(activitySubscriptionPK);

        if (activitySubscription == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No ActivitySubscription exists with the primary key " +
                    activitySubscriptionPK);
            }

            throw new NoSuchSubscriptionException(
                "No ActivitySubscription exists with the primary key " +
                activitySubscriptionPK);
        }

        return activitySubscription;
    }

    public ActivitySubscription fetchByPrimaryKey(
        ActivitySubscriptionPK activitySubscriptionPK)
        throws SystemException {
        ActivitySubscription activitySubscription = (ActivitySubscription) EntityCacheUtil.getResult(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
                ActivitySubscriptionImpl.class, activitySubscriptionPK, this);

        if (activitySubscription == null) {
            Session session = null;

            try {
                session = openSession();

                activitySubscription = (ActivitySubscription) session.get(ActivitySubscriptionImpl.class,
                        activitySubscriptionPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (activitySubscription != null) {
                    cacheResult(activitySubscription);
                }

                closeSession(session);
            }
        }

        return activitySubscription;
    }

    public List<ActivitySubscription> findByentityId(Long entityId)
        throws SystemException {
        Object[] finderArgs = new Object[] { entityId };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ENTITYID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (entityId == null) {
                    query.append("entityId IS NULL");
                } else {
                    query.append("entityId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (entityId != null) {
                    qPos.add(entityId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ENTITYID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ActivitySubscription> findByentityId(Long entityId, int start,
        int end) throws SystemException {
        return findByentityId(entityId, start, end, null);
    }

    public List<ActivitySubscription> findByentityId(Long entityId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                entityId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ENTITYID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (entityId == null) {
                    query.append("entityId IS NULL");
                } else {
                    query.append("entityId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (entityId != null) {
                    qPos.add(entityId.longValue());
                }

                list = (List<ActivitySubscription>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ENTITYID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ActivitySubscription findByentityId_First(Long entityId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        List<ActivitySubscription> list = findByentityId(entityId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("entityId=" + entityId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription findByentityId_Last(Long entityId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        int count = countByentityId(entityId);

        List<ActivitySubscription> list = findByentityId(entityId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("entityId=" + entityId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription[] findByentityId_PrevAndNext(
        ActivitySubscriptionPK activitySubscriptionPK, Long entityId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = findByPrimaryKey(activitySubscriptionPK);

        int count = countByentityId(entityId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

            if (entityId == null) {
                query.append("entityId IS NULL");
            } else {
                query.append("entityId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (entityId != null) {
                qPos.add(entityId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    activitySubscription);

            ActivitySubscription[] array = new ActivitySubscriptionImpl[3];

            array[0] = (ActivitySubscription) objArray[0];
            array[1] = (ActivitySubscription) objArray[1];
            array[2] = (ActivitySubscription) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<ActivitySubscription> findByreceiverId(Long receiverId)
        throws SystemException {
        Object[] finderArgs = new Object[] { receiverId };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_RECEIVERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (receiverId == null) {
                    query.append("receiverId IS NULL");
                } else {
                    query.append("receiverId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (receiverId != null) {
                    qPos.add(receiverId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_RECEIVERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ActivitySubscription> findByreceiverId(Long receiverId,
        int start, int end) throws SystemException {
        return findByreceiverId(receiverId, start, end, null);
    }

    public List<ActivitySubscription> findByreceiverId(Long receiverId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                receiverId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_RECEIVERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (receiverId == null) {
                    query.append("receiverId IS NULL");
                } else {
                    query.append("receiverId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (receiverId != null) {
                    qPos.add(receiverId.longValue());
                }

                list = (List<ActivitySubscription>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_RECEIVERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ActivitySubscription findByreceiverId_First(Long receiverId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        List<ActivitySubscription> list = findByreceiverId(receiverId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription findByreceiverId_Last(Long receiverId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        int count = countByreceiverId(receiverId);

        List<ActivitySubscription> list = findByreceiverId(receiverId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription[] findByreceiverId_PrevAndNext(
        ActivitySubscriptionPK activitySubscriptionPK, Long receiverId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = findByPrimaryKey(activitySubscriptionPK);

        int count = countByreceiverId(receiverId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

            if (receiverId == null) {
                query.append("receiverId IS NULL");
            } else {
                query.append("receiverId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (receiverId != null) {
                qPos.add(receiverId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    activitySubscription);

            ActivitySubscription[] array = new ActivitySubscriptionImpl[3];

            array[0] = (ActivitySubscription) objArray[0];
            array[1] = (ActivitySubscription) objArray[1];
            array[2] = (ActivitySubscription) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<ActivitySubscription> findByactivitytype(String activitytype)
        throws SystemException {
        Object[] finderArgs = new Object[] { activitytype };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ACTIVITYTYPE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (activitytype == null) {
                    query.append("activitytype IS NULL");
                } else {
                    query.append("activitytype = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (activitytype != null) {
                    qPos.add(activitytype);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ACTIVITYTYPE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ActivitySubscription> findByactivitytype(String activitytype,
        int start, int end) throws SystemException {
        return findByactivitytype(activitytype, start, end, null);
    }

    public List<ActivitySubscription> findByactivitytype(String activitytype,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                activitytype,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ACTIVITYTYPE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (activitytype == null) {
                    query.append("activitytype IS NULL");
                } else {
                    query.append("activitytype = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (activitytype != null) {
                    qPos.add(activitytype);
                }

                list = (List<ActivitySubscription>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ACTIVITYTYPE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ActivitySubscription findByactivitytype_First(String activitytype,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        List<ActivitySubscription> list = findByactivitytype(activitytype, 0,
                1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("activitytype=" + activitytype);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription findByactivitytype_Last(String activitytype,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        int count = countByactivitytype(activitytype);

        List<ActivitySubscription> list = findByactivitytype(activitytype,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("activitytype=" + activitytype);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription[] findByactivitytype_PrevAndNext(
        ActivitySubscriptionPK activitySubscriptionPK, String activitytype,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = findByPrimaryKey(activitySubscriptionPK);

        int count = countByactivitytype(activitytype);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

            if (activitytype == null) {
                query.append("activitytype IS NULL");
            } else {
                query.append("activitytype = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (activitytype != null) {
                qPos.add(activitytype);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    activitySubscription);

            ActivitySubscription[] array = new ActivitySubscriptionImpl[3];

            array[0] = (ActivitySubscription) objArray[0];
            array[1] = (ActivitySubscription) objArray[1];
            array[2] = (ActivitySubscription) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<ActivitySubscription> findByentityreceiver(Long entityId,
        Long receiverId) throws SystemException {
        Object[] finderArgs = new Object[] { entityId, receiverId };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ENTITYRECEIVER,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (entityId == null) {
                    query.append("entityId IS NULL");
                } else {
                    query.append("entityId = ?");
                }

                query.append(" AND ");

                if (receiverId == null) {
                    query.append("receiverId IS NULL");
                } else {
                    query.append("receiverId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (entityId != null) {
                    qPos.add(entityId.longValue());
                }

                if (receiverId != null) {
                    qPos.add(receiverId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ENTITYRECEIVER,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ActivitySubscription> findByentityreceiver(Long entityId,
        Long receiverId, int start, int end) throws SystemException {
        return findByentityreceiver(entityId, receiverId, start, end, null);
    }

    public List<ActivitySubscription> findByentityreceiver(Long entityId,
        Long receiverId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                entityId,
                
                receiverId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ENTITYRECEIVER,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (entityId == null) {
                    query.append("entityId IS NULL");
                } else {
                    query.append("entityId = ?");
                }

                query.append(" AND ");

                if (receiverId == null) {
                    query.append("receiverId IS NULL");
                } else {
                    query.append("receiverId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (entityId != null) {
                    qPos.add(entityId.longValue());
                }

                if (receiverId != null) {
                    qPos.add(receiverId.longValue());
                }

                list = (List<ActivitySubscription>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ENTITYRECEIVER,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ActivitySubscription findByentityreceiver_First(Long entityId,
        Long receiverId, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        List<ActivitySubscription> list = findByentityreceiver(entityId,
                receiverId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("entityId=" + entityId);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription findByentityreceiver_Last(Long entityId,
        Long receiverId, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        int count = countByentityreceiver(entityId, receiverId);

        List<ActivitySubscription> list = findByentityreceiver(entityId,
                receiverId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("entityId=" + entityId);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription[] findByentityreceiver_PrevAndNext(
        ActivitySubscriptionPK activitySubscriptionPK, Long entityId,
        Long receiverId, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = findByPrimaryKey(activitySubscriptionPK);

        int count = countByentityreceiver(entityId, receiverId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

            if (entityId == null) {
                query.append("entityId IS NULL");
            } else {
                query.append("entityId = ?");
            }

            query.append(" AND ");

            if (receiverId == null) {
                query.append("receiverId IS NULL");
            } else {
                query.append("receiverId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (entityId != null) {
                qPos.add(entityId.longValue());
            }

            if (receiverId != null) {
                qPos.add(receiverId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    activitySubscription);

            ActivitySubscription[] array = new ActivitySubscriptionImpl[3];

            array[0] = (ActivitySubscription) objArray[0];
            array[1] = (ActivitySubscription) objArray[1];
            array[2] = (ActivitySubscription) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<ActivitySubscription> findByentityreceivertype(Long entityId,
        Long receiverId, String activitytype) throws SystemException {
        Object[] finderArgs = new Object[] { entityId, receiverId, activitytype };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ENTITYRECEIVERTYPE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (entityId == null) {
                    query.append("entityId IS NULL");
                } else {
                    query.append("entityId = ?");
                }

                query.append(" AND ");

                if (receiverId == null) {
                    query.append("receiverId IS NULL");
                } else {
                    query.append("receiverId = ?");
                }

                query.append(" AND ");

                if (activitytype == null) {
                    query.append("activitytype IS NULL");
                } else {
                    query.append("activitytype = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (entityId != null) {
                    qPos.add(entityId.longValue());
                }

                if (receiverId != null) {
                    qPos.add(receiverId.longValue());
                }

                if (activitytype != null) {
                    qPos.add(activitytype);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ENTITYRECEIVERTYPE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ActivitySubscription> findByentityreceivertype(Long entityId,
        Long receiverId, String activitytype, int start, int end)
        throws SystemException {
        return findByentityreceivertype(entityId, receiverId, activitytype,
            start, end, null);
    }

    public List<ActivitySubscription> findByentityreceivertype(Long entityId,
        Long receiverId, String activitytype, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                entityId,
                
                receiverId,
                
                activitytype,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ENTITYRECEIVERTYPE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (entityId == null) {
                    query.append("entityId IS NULL");
                } else {
                    query.append("entityId = ?");
                }

                query.append(" AND ");

                if (receiverId == null) {
                    query.append("receiverId IS NULL");
                } else {
                    query.append("receiverId = ?");
                }

                query.append(" AND ");

                if (activitytype == null) {
                    query.append("activitytype IS NULL");
                } else {
                    query.append("activitytype = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (entityId != null) {
                    qPos.add(entityId.longValue());
                }

                if (receiverId != null) {
                    qPos.add(receiverId.longValue());
                }

                if (activitytype != null) {
                    qPos.add(activitytype);
                }

                list = (List<ActivitySubscription>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ENTITYRECEIVERTYPE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ActivitySubscription findByentityreceivertype_First(Long entityId,
        Long receiverId, String activitytype, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        List<ActivitySubscription> list = findByentityreceivertype(entityId,
                receiverId, activitytype, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("entityId=" + entityId);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(", ");
            msg.append("activitytype=" + activitytype);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription findByentityreceivertype_Last(Long entityId,
        Long receiverId, String activitytype, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        int count = countByentityreceivertype(entityId, receiverId, activitytype);

        List<ActivitySubscription> list = findByentityreceivertype(entityId,
                receiverId, activitytype, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("entityId=" + entityId);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(", ");
            msg.append("activitytype=" + activitytype);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription[] findByentityreceivertype_PrevAndNext(
        ActivitySubscriptionPK activitySubscriptionPK, Long entityId,
        Long receiverId, String activitytype, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = findByPrimaryKey(activitySubscriptionPK);

        int count = countByentityreceivertype(entityId, receiverId, activitytype);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

            if (entityId == null) {
                query.append("entityId IS NULL");
            } else {
                query.append("entityId = ?");
            }

            query.append(" AND ");

            if (receiverId == null) {
                query.append("receiverId IS NULL");
            } else {
                query.append("receiverId = ?");
            }

            query.append(" AND ");

            if (activitytype == null) {
                query.append("activitytype IS NULL");
            } else {
                query.append("activitytype = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (entityId != null) {
                qPos.add(entityId.longValue());
            }

            if (receiverId != null) {
                qPos.add(receiverId.longValue());
            }

            if (activitytype != null) {
                qPos.add(activitytype);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    activitySubscription);

            ActivitySubscription[] array = new ActivitySubscriptionImpl[3];

            array[0] = (ActivitySubscription) objArray[0];
            array[1] = (ActivitySubscription) objArray[1];
            array[2] = (ActivitySubscription) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            dynamicQuery.compile(session);

            return dynamicQuery.list();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            dynamicQuery.setLimit(start, end);

            dynamicQuery.compile(session);

            return dynamicQuery.list();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<ActivitySubscription> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ActivitySubscription> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ActivitySubscription> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ActivitySubscription>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ActivitySubscription>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ActivitySubscription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByentityId(Long entityId) throws SystemException {
        for (ActivitySubscription activitySubscription : findByentityId(
                entityId)) {
            remove(activitySubscription);
        }
    }

    public void removeByreceiverId(Long receiverId) throws SystemException {
        for (ActivitySubscription activitySubscription : findByreceiverId(
                receiverId)) {
            remove(activitySubscription);
        }
    }

    public void removeByactivitytype(String activitytype)
        throws SystemException {
        for (ActivitySubscription activitySubscription : findByactivitytype(
                activitytype)) {
            remove(activitySubscription);
        }
    }

    public void removeByentityreceiver(Long entityId, Long receiverId)
        throws SystemException {
        for (ActivitySubscription activitySubscription : findByentityreceiver(
                entityId, receiverId)) {
            remove(activitySubscription);
        }
    }

    public void removeByentityreceivertype(Long entityId, Long receiverId,
        String activitytype) throws SystemException {
        for (ActivitySubscription activitySubscription : findByentityreceivertype(
                entityId, receiverId, activitytype)) {
            remove(activitySubscription);
        }
    }

    public void removeAll() throws SystemException {
        for (ActivitySubscription activitySubscription : findAll()) {
            remove(activitySubscription);
        }
    }

    public int countByentityId(Long entityId) throws SystemException {
        Object[] finderArgs = new Object[] { entityId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ENTITYID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (entityId == null) {
                    query.append("entityId IS NULL");
                } else {
                    query.append("entityId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (entityId != null) {
                    qPos.add(entityId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ENTITYID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByreceiverId(Long receiverId) throws SystemException {
        Object[] finderArgs = new Object[] { receiverId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RECEIVERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (receiverId == null) {
                    query.append("receiverId IS NULL");
                } else {
                    query.append("receiverId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (receiverId != null) {
                    qPos.add(receiverId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RECEIVERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByactivitytype(String activitytype)
        throws SystemException {
        Object[] finderArgs = new Object[] { activitytype };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACTIVITYTYPE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (activitytype == null) {
                    query.append("activitytype IS NULL");
                } else {
                    query.append("activitytype = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (activitytype != null) {
                    qPos.add(activitytype);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACTIVITYTYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByentityreceiver(Long entityId, Long receiverId)
        throws SystemException {
        Object[] finderArgs = new Object[] { entityId, receiverId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ENTITYRECEIVER,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (entityId == null) {
                    query.append("entityId IS NULL");
                } else {
                    query.append("entityId = ?");
                }

                query.append(" AND ");

                if (receiverId == null) {
                    query.append("receiverId IS NULL");
                } else {
                    query.append("receiverId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (entityId != null) {
                    qPos.add(entityId.longValue());
                }

                if (receiverId != null) {
                    qPos.add(receiverId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ENTITYRECEIVER,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByentityreceivertype(Long entityId, Long receiverId,
        String activitytype) throws SystemException {
        Object[] finderArgs = new Object[] { entityId, receiverId, activitytype };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ENTITYRECEIVERTYPE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (entityId == null) {
                    query.append("entityId IS NULL");
                } else {
                    query.append("entityId = ?");
                }

                query.append(" AND ");

                if (receiverId == null) {
                    query.append("receiverId IS NULL");
                } else {
                    query.append("receiverId = ?");
                }

                query.append(" AND ");

                if (activitytype == null) {
                    query.append("activitytype IS NULL");
                } else {
                    query.append("activitytype = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (entityId != null) {
                    qPos.add(entityId.longValue());
                }

                if (receiverId != null) {
                    qPos.add(receiverId.longValue());
                }

                if (activitytype != null) {
                    qPos.add(activitytype);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ENTITYRECEIVERTYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countAll() throws SystemException {
        Object[] finderArgs = new Object[0];

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(
                        "SELECT COUNT(*) FROM com.ext.portlet.Activity.model.ActivitySubscription");

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
                    count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.portal.util.PropsUtil.get(
                        "value.object.listener.com.ext.portlet.Activity.model.ActivitySubscription")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ActivitySubscription>> listenersList = new ArrayList<ModelListener<ActivitySubscription>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ActivitySubscription>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
