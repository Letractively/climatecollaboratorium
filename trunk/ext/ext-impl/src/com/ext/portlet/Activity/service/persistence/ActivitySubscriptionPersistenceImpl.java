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
    public static final FinderPath FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPKRECEIVERID =
        new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByClassNameIdClassPKReceiverId",
            new String[] {
                Long.class.getName(), Long.class.getName(), Long.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPKRECEIVERID =
        new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByClassNameIdClassPKReceiverId",
            new String[] {
                Long.class.getName(), Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPKRECEIVERID =
        new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByClassNameIdClassPKReceiverId",
            new String[] {
                Long.class.getName(), Long.class.getName(), Long.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPKTYPERECEIVERID =
        new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByClassNameIdClassPKTypeReceiverId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                Integer.class.getName(), Long.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPKTYPERECEIVERID =
        new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByClassNameIdClassPKTypeReceiverId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                Integer.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPKTYPERECEIVERID =
        new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByClassNameIdClassPKTypeReceiverId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                Integer.class.getName(), Long.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPKTYPEEXTRADATARECEIVERID =
        new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST,
            "findByClassNameIdClassPKTypeExtraDataReceiverId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                Integer.class.getName(), String.class.getName(),
                Long.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPKTYPEEXTRADATARECEIVERID =
        new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST,
            "findByClassNameIdClassPKTypeExtraDataReceiverId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                Integer.class.getName(), String.class.getName(),
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPKTYPEEXTRADATARECEIVERID =
        new FinderPath(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
            ActivitySubscriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST,
            "countByClassNameIdClassPKTypeExtraDataReceiverId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                Integer.class.getName(), String.class.getName(),
                Long.class.getName()
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

    public ActivitySubscription create(Long pk) {
        ActivitySubscription activitySubscription = new ActivitySubscriptionImpl();

        activitySubscription.setNew(true);
        activitySubscription.setPrimaryKey(pk);

        return activitySubscription;
    }

    public ActivitySubscription remove(Long pk)
        throws NoSuchSubscriptionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ActivitySubscription activitySubscription = (ActivitySubscription) session.get(ActivitySubscriptionImpl.class,
                    pk);

            if (activitySubscription == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ActivitySubscription exists with the primary key " +
                        pk);
                }

                throw new NoSuchSubscriptionException(
                    "No ActivitySubscription exists with the primary key " +
                    pk);
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

    public ActivitySubscription findByPrimaryKey(Long pk)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = fetchByPrimaryKey(pk);

        if (activitySubscription == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No ActivitySubscription exists with the primary key " +
                    pk);
            }

            throw new NoSuchSubscriptionException(
                "No ActivitySubscription exists with the primary key " + pk);
        }

        return activitySubscription;
    }

    public ActivitySubscription fetchByPrimaryKey(Long pk)
        throws SystemException {
        ActivitySubscription activitySubscription = (ActivitySubscription) EntityCacheUtil.getResult(ActivitySubscriptionModelImpl.ENTITY_CACHE_ENABLED,
                ActivitySubscriptionImpl.class, pk, this);

        if (activitySubscription == null) {
            Session session = null;

            try {
                session = openSession();

                activitySubscription = (ActivitySubscription) session.get(ActivitySubscriptionImpl.class,
                        pk);
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

    public ActivitySubscription[] findByreceiverId_PrevAndNext(Long pk,
        Long receiverId, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = findByPrimaryKey(pk);

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

    public List<ActivitySubscription> findByClassNameIdClassPKReceiverId(
        Long classNameId, Long classPK, Long receiverId)
        throws SystemException {
        Object[] finderArgs = new Object[] { classNameId, classPK, receiverId };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPKRECEIVERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" AND ");

                if (classPK == null) {
                    query.append("classPK IS NULL");
                } else {
                    query.append("classPK = ?");
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

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
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

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPKRECEIVERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ActivitySubscription> findByClassNameIdClassPKReceiverId(
        Long classNameId, Long classPK, Long receiverId, int start, int end)
        throws SystemException {
        return findByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId, start, end, null);
    }

    public List<ActivitySubscription> findByClassNameIdClassPKReceiverId(
        Long classNameId, Long classPK, Long receiverId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                classNameId,
                
                classPK,
                
                receiverId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPKRECEIVERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" AND ");

                if (classPK == null) {
                    query.append("classPK IS NULL");
                } else {
                    query.append("classPK = ?");
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

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
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

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPKRECEIVERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ActivitySubscription findByClassNameIdClassPKReceiverId_First(
        Long classNameId, Long classPK, Long receiverId, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        List<ActivitySubscription> list = findByClassNameIdClassPKReceiverId(classNameId,
                classPK, receiverId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(", ");
            msg.append("classPK=" + classPK);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription findByClassNameIdClassPKReceiverId_Last(
        Long classNameId, Long classPK, Long receiverId, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        int count = countByClassNameIdClassPKReceiverId(classNameId, classPK,
                receiverId);

        List<ActivitySubscription> list = findByClassNameIdClassPKReceiverId(classNameId,
                classPK, receiverId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(", ");
            msg.append("classPK=" + classPK);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription[] findByClassNameIdClassPKReceiverId_PrevAndNext(
        Long pk, Long classNameId, Long classPK, Long receiverId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = findByPrimaryKey(pk);

        int count = countByClassNameIdClassPKReceiverId(classNameId, classPK,
                receiverId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

            if (classNameId == null) {
                query.append("classNameId IS NULL");
            } else {
                query.append("classNameId = ?");
            }

            query.append(" AND ");

            if (classPK == null) {
                query.append("classPK IS NULL");
            } else {
                query.append("classPK = ?");
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

            if (classNameId != null) {
                qPos.add(classNameId.longValue());
            }

            if (classPK != null) {
                qPos.add(classPK.longValue());
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

    public List<ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        Long classNameId, Long classPK, Integer type, Long receiverId)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                classNameId,
                
                classPK,
                
                type,
                
                receiverId
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPKTYPERECEIVERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" AND ");

                if (classPK == null) {
                    query.append("classPK IS NULL");
                } else {
                    query.append("classPK = ?");
                }

                query.append(" AND ");

                if (type == null) {
                    query.append("type_ IS NULL");
                } else {
                    query.append("type_ = ?");
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

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
                }

                if (type != null) {
                    qPos.add(type.intValue());
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

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPKTYPERECEIVERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        Long classNameId, Long classPK, Integer type, Long receiverId,
        int start, int end) throws SystemException {
        return findByClassNameIdClassPKTypeReceiverId(classNameId, classPK,
            type, receiverId, start, end, null);
    }

    public List<ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        Long classNameId, Long classPK, Integer type, Long receiverId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                classNameId,
                
                classPK,
                
                type,
                
                receiverId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPKTYPERECEIVERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" AND ");

                if (classPK == null) {
                    query.append("classPK IS NULL");
                } else {
                    query.append("classPK = ?");
                }

                query.append(" AND ");

                if (type == null) {
                    query.append("type_ IS NULL");
                } else {
                    query.append("type_ = ?");
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

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
                }

                if (type != null) {
                    qPos.add(type.intValue());
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

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPKTYPERECEIVERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ActivitySubscription findByClassNameIdClassPKTypeReceiverId_First(
        Long classNameId, Long classPK, Integer type, Long receiverId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        List<ActivitySubscription> list = findByClassNameIdClassPKTypeReceiverId(classNameId,
                classPK, type, receiverId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(", ");
            msg.append("classPK=" + classPK);

            msg.append(", ");
            msg.append("type=" + type);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription findByClassNameIdClassPKTypeReceiverId_Last(
        Long classNameId, Long classPK, Integer type, Long receiverId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        int count = countByClassNameIdClassPKTypeReceiverId(classNameId,
                classPK, type, receiverId);

        List<ActivitySubscription> list = findByClassNameIdClassPKTypeReceiverId(classNameId,
                classPK, type, receiverId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(", ");
            msg.append("classPK=" + classPK);

            msg.append(", ");
            msg.append("type=" + type);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription[] findByClassNameIdClassPKTypeReceiverId_PrevAndNext(
        Long pk, Long classNameId, Long classPK, Integer type, Long receiverId,
        OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = findByPrimaryKey(pk);

        int count = countByClassNameIdClassPKTypeReceiverId(classNameId,
                classPK, type, receiverId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

            if (classNameId == null) {
                query.append("classNameId IS NULL");
            } else {
                query.append("classNameId = ?");
            }

            query.append(" AND ");

            if (classPK == null) {
                query.append("classPK IS NULL");
            } else {
                query.append("classPK = ?");
            }

            query.append(" AND ");

            if (type == null) {
                query.append("type_ IS NULL");
            } else {
                query.append("type_ = ?");
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

            if (classNameId != null) {
                qPos.add(classNameId.longValue());
            }

            if (classPK != null) {
                qPos.add(classPK.longValue());
            }

            if (type != null) {
                qPos.add(type.intValue());
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

    public List<ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        Long classNameId, Long classPK, Integer type, String extraData,
        Long receiverId) throws SystemException {
        Object[] finderArgs = new Object[] {
                classNameId,
                
                classPK,
                
                type,
                
                extraData,
                
                receiverId
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPKTYPEEXTRADATARECEIVERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" AND ");

                if (classPK == null) {
                    query.append("classPK IS NULL");
                } else {
                    query.append("classPK = ?");
                }

                query.append(" AND ");

                if (type == null) {
                    query.append("type_ IS NULL");
                } else {
                    query.append("type_ = ?");
                }

                query.append(" AND ");

                if (extraData == null) {
                    query.append("extraData LIKE null");
                } else {
                    query.append("extraData LIKE ?");
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

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
                }

                if (type != null) {
                    qPos.add(type.intValue());
                }

                if (extraData != null) {
                    qPos.add(extraData);
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

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPKTYPEEXTRADATARECEIVERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        Long classNameId, Long classPK, Integer type, String extraData,
        Long receiverId, int start, int end) throws SystemException {
        return findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId, start, end, null);
    }

    public List<ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        Long classNameId, Long classPK, Integer type, String extraData,
        Long receiverId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                classNameId,
                
                classPK,
                
                type,
                
                extraData,
                
                receiverId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ActivitySubscription> list = (List<ActivitySubscription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPKTYPEEXTRADATARECEIVERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" AND ");

                if (classPK == null) {
                    query.append("classPK IS NULL");
                } else {
                    query.append("classPK = ?");
                }

                query.append(" AND ");

                if (type == null) {
                    query.append("type_ IS NULL");
                } else {
                    query.append("type_ = ?");
                }

                query.append(" AND ");

                if (extraData == null) {
                    query.append("extraData LIKE null");
                } else {
                    query.append("extraData LIKE ?");
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

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
                }

                if (type != null) {
                    qPos.add(type.intValue());
                }

                if (extraData != null) {
                    qPos.add(extraData);
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

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPKTYPEEXTRADATARECEIVERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_First(
        Long classNameId, Long classPK, Integer type, String extraData,
        Long receiverId, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        List<ActivitySubscription> list = findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
                classPK, type, extraData, receiverId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(", ");
            msg.append("classPK=" + classPK);

            msg.append(", ");
            msg.append("type=" + type);

            msg.append(", ");
            msg.append("extraData=" + extraData);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_Last(
        Long classNameId, Long classPK, Integer type, String extraData,
        Long receiverId, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        int count = countByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
                classPK, type, extraData, receiverId);

        List<ActivitySubscription> list = findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
                classPK, type, extraData, receiverId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ActivitySubscription exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(", ");
            msg.append("classPK=" + classPK);

            msg.append(", ");
            msg.append("type=" + type);

            msg.append(", ");
            msg.append("extraData=" + extraData);

            msg.append(", ");
            msg.append("receiverId=" + receiverId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchSubscriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ActivitySubscription[] findByClassNameIdClassPKTypeExtraDataReceiverId_PrevAndNext(
        Long pk, Long classNameId, Long classPK, Integer type,
        String extraData, Long receiverId, OrderByComparator obc)
        throws NoSuchSubscriptionException, SystemException {
        ActivitySubscription activitySubscription = findByPrimaryKey(pk);

        int count = countByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
                classPK, type, extraData, receiverId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

            if (classNameId == null) {
                query.append("classNameId IS NULL");
            } else {
                query.append("classNameId = ?");
            }

            query.append(" AND ");

            if (classPK == null) {
                query.append("classPK IS NULL");
            } else {
                query.append("classPK = ?");
            }

            query.append(" AND ");

            if (type == null) {
                query.append("type_ IS NULL");
            } else {
                query.append("type_ = ?");
            }

            query.append(" AND ");

            if (extraData == null) {
                query.append("extraData LIKE null");
            } else {
                query.append("extraData LIKE ?");
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

            if (classNameId != null) {
                qPos.add(classNameId.longValue());
            }

            if (classPK != null) {
                qPos.add(classPK.longValue());
            }

            if (type != null) {
                qPos.add(type.intValue());
            }

            if (extraData != null) {
                qPos.add(extraData);
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

    public void removeByreceiverId(Long receiverId) throws SystemException {
        for (ActivitySubscription activitySubscription : findByreceiverId(
                receiverId)) {
            remove(activitySubscription);
        }
    }

    public void removeByClassNameIdClassPKReceiverId(Long classNameId,
        Long classPK, Long receiverId) throws SystemException {
        for (ActivitySubscription activitySubscription : findByClassNameIdClassPKReceiverId(
                classNameId, classPK, receiverId)) {
            remove(activitySubscription);
        }
    }

    public void removeByClassNameIdClassPKTypeReceiverId(Long classNameId,
        Long classPK, Integer type, Long receiverId) throws SystemException {
        for (ActivitySubscription activitySubscription : findByClassNameIdClassPKTypeReceiverId(
                classNameId, classPK, type, receiverId)) {
            remove(activitySubscription);
        }
    }

    public void removeByClassNameIdClassPKTypeExtraDataReceiverId(
        Long classNameId, Long classPK, Integer type, String extraData,
        Long receiverId) throws SystemException {
        for (ActivitySubscription activitySubscription : findByClassNameIdClassPKTypeExtraDataReceiverId(
                classNameId, classPK, type, extraData, receiverId)) {
            remove(activitySubscription);
        }
    }

    public void removeAll() throws SystemException {
        for (ActivitySubscription activitySubscription : findAll()) {
            remove(activitySubscription);
        }
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

    public int countByClassNameIdClassPKReceiverId(Long classNameId,
        Long classPK, Long receiverId) throws SystemException {
        Object[] finderArgs = new Object[] { classNameId, classPK, receiverId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPKRECEIVERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" AND ");

                if (classPK == null) {
                    query.append("classPK IS NULL");
                } else {
                    query.append("classPK = ?");
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

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPKRECEIVERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByClassNameIdClassPKTypeReceiverId(Long classNameId,
        Long classPK, Integer type, Long receiverId) throws SystemException {
        Object[] finderArgs = new Object[] {
                classNameId,
                
                classPK,
                
                type,
                
                receiverId
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPKTYPERECEIVERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" AND ");

                if (classPK == null) {
                    query.append("classPK IS NULL");
                } else {
                    query.append("classPK = ?");
                }

                query.append(" AND ");

                if (type == null) {
                    query.append("type_ IS NULL");
                } else {
                    query.append("type_ = ?");
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

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
                }

                if (type != null) {
                    qPos.add(type.intValue());
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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPKTYPERECEIVERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByClassNameIdClassPKTypeExtraDataReceiverId(
        Long classNameId, Long classPK, Integer type, String extraData,
        Long receiverId) throws SystemException {
        Object[] finderArgs = new Object[] {
                classNameId,
                
                classPK,
                
                type,
                
                extraData,
                
                receiverId
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPKTYPEEXTRADATARECEIVERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.Activity.model.ActivitySubscription WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" AND ");

                if (classPK == null) {
                    query.append("classPK IS NULL");
                } else {
                    query.append("classPK = ?");
                }

                query.append(" AND ");

                if (type == null) {
                    query.append("type_ IS NULL");
                } else {
                    query.append("type_ = ?");
                }

                query.append(" AND ");

                if (extraData == null) {
                    query.append("extraData LIKE null");
                } else {
                    query.append("extraData LIKE ?");
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

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
                }

                if (type != null) {
                    qPos.add(type.intValue());
                }

                if (extraData != null) {
                    qPos.add(extraData);
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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPKTYPEEXTRADATARECEIVERID,
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
