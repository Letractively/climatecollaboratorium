package com.ext.portlet.surveys.service.persistence;

import com.ext.portlet.surveys.NoSuchUserSurveyException;
import com.ext.portlet.surveys.model.UserSurvey;
import com.ext.portlet.surveys.model.impl.UserSurveyImpl;
import com.ext.portlet.surveys.model.impl.UserSurveyModelImpl;

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


public class UserSurveyPersistenceImpl extends BasePersistenceImpl
    implements UserSurveyPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = UserSurveyImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByUserId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByUserId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByUserId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_EVENTNAME = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByEventName", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_EVENTNAME = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByEventName",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_EVENTNAME = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByEventName", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_USERIDEVENTNAME = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByUserIdEventName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERIDEVENTNAME = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByUserIdEventName",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_USERIDEVENTNAME = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByUserIdEventName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(UserSurveyPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.surveys.service.persistence.SurveyPersistence.impl")
    protected com.ext.portlet.surveys.service.persistence.SurveyPersistence surveyPersistence;
    @BeanReference(name = "com.ext.portlet.surveys.service.persistence.UserSurveyPersistence.impl")
    protected com.ext.portlet.surveys.service.persistence.UserSurveyPersistence userSurveyPersistence;

    public void cacheResult(UserSurvey userSurvey) {
        EntityCacheUtil.putResult(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyImpl.class, userSurvey.getPrimaryKey(), userSurvey);
    }

    public void cacheResult(List<UserSurvey> userSurveies) {
        for (UserSurvey userSurvey : userSurveies) {
            if (EntityCacheUtil.getResult(
                        UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
                        UserSurveyImpl.class, userSurvey.getPrimaryKey(), this) == null) {
                cacheResult(userSurvey);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(UserSurveyImpl.class.getName());
        EntityCacheUtil.clearCache(UserSurveyImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public UserSurvey create(UserSurveyPK userSurveyPK) {
        UserSurvey userSurvey = new UserSurveyImpl();

        userSurvey.setNew(true);
        userSurvey.setPrimaryKey(userSurveyPK);

        return userSurvey;
    }

    public UserSurvey remove(UserSurveyPK userSurveyPK)
        throws NoSuchUserSurveyException, SystemException {
        Session session = null;

        try {
            session = openSession();

            UserSurvey userSurvey = (UserSurvey) session.get(UserSurveyImpl.class,
                    userSurveyPK);

            if (userSurvey == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No UserSurvey exists with the primary key " +
                        userSurveyPK);
                }

                throw new NoSuchUserSurveyException(
                    "No UserSurvey exists with the primary key " +
                    userSurveyPK);
            }

            return remove(userSurvey);
        } catch (NoSuchUserSurveyException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public UserSurvey remove(UserSurvey userSurvey) throws SystemException {
        for (ModelListener<UserSurvey> listener : listeners) {
            listener.onBeforeRemove(userSurvey);
        }

        userSurvey = removeImpl(userSurvey);

        for (ModelListener<UserSurvey> listener : listeners) {
            listener.onAfterRemove(userSurvey);
        }

        return userSurvey;
    }

    protected UserSurvey removeImpl(UserSurvey userSurvey)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (userSurvey.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(UserSurveyImpl.class,
                        userSurvey.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(userSurvey);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyImpl.class, userSurvey.getPrimaryKey());

        return userSurvey;
    }

    /**
     * @deprecated Use <code>update(UserSurvey userSurvey, boolean merge)</code>.
     */
    public UserSurvey update(UserSurvey userSurvey) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(UserSurvey userSurvey) method. Use update(UserSurvey userSurvey, boolean merge) instead.");
        }

        return update(userSurvey, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                userSurvey the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when userSurvey is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public UserSurvey update(UserSurvey userSurvey, boolean merge)
        throws SystemException {
        boolean isNew = userSurvey.isNew();

        for (ModelListener<UserSurvey> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(userSurvey);
            } else {
                listener.onBeforeUpdate(userSurvey);
            }
        }

        userSurvey = updateImpl(userSurvey, merge);

        for (ModelListener<UserSurvey> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(userSurvey);
            } else {
                listener.onAfterUpdate(userSurvey);
            }
        }

        return userSurvey;
    }

    public UserSurvey updateImpl(
        com.ext.portlet.surveys.model.UserSurvey userSurvey, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, userSurvey, merge);

            userSurvey.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
            UserSurveyImpl.class, userSurvey.getPrimaryKey(), userSurvey);

        return userSurvey;
    }

    public UserSurvey findByPrimaryKey(UserSurveyPK userSurveyPK)
        throws NoSuchUserSurveyException, SystemException {
        UserSurvey userSurvey = fetchByPrimaryKey(userSurveyPK);

        if (userSurvey == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No UserSurvey exists with the primary key " +
                    userSurveyPK);
            }

            throw new NoSuchUserSurveyException(
                "No UserSurvey exists with the primary key " + userSurveyPK);
        }

        return userSurvey;
    }

    public UserSurvey fetchByPrimaryKey(UserSurveyPK userSurveyPK)
        throws SystemException {
        UserSurvey userSurvey = (UserSurvey) EntityCacheUtil.getResult(UserSurveyModelImpl.ENTITY_CACHE_ENABLED,
                UserSurveyImpl.class, userSurveyPK, this);

        if (userSurvey == null) {
            Session session = null;

            try {
                session = openSession();

                userSurvey = (UserSurvey) session.get(UserSurveyImpl.class,
                        userSurveyPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (userSurvey != null) {
                    cacheResult(userSurvey);
                }

                closeSession(session);
            }
        }

        return userSurvey;
    }

    public List<UserSurvey> findByUserId(Long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        List<UserSurvey> list = (List<UserSurvey>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserSurvey>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<UserSurvey> findByUserId(Long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    public List<UserSurvey> findByUserId(Long userId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                userId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<UserSurvey> list = (List<UserSurvey>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                list = (List<UserSurvey>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserSurvey>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public UserSurvey findByUserId_First(Long userId, OrderByComparator obc)
        throws NoSuchUserSurveyException, SystemException {
        List<UserSurvey> list = findByUserId(userId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No UserSurvey exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchUserSurveyException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public UserSurvey findByUserId_Last(Long userId, OrderByComparator obc)
        throws NoSuchUserSurveyException, SystemException {
        int count = countByUserId(userId);

        List<UserSurvey> list = findByUserId(userId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No UserSurvey exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchUserSurveyException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public UserSurvey[] findByUserId_PrevAndNext(UserSurveyPK userSurveyPK,
        Long userId, OrderByComparator obc)
        throws NoSuchUserSurveyException, SystemException {
        UserSurvey userSurvey = findByPrimaryKey(userSurveyPK);

        int count = countByUserId(userId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

            if (userId == null) {
                query.append("userId IS NULL");
            } else {
                query.append("userId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (userId != null) {
                qPos.add(userId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    userSurvey);

            UserSurvey[] array = new UserSurveyImpl[3];

            array[0] = (UserSurvey) objArray[0];
            array[1] = (UserSurvey) objArray[1];
            array[2] = (UserSurvey) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<UserSurvey> findByEventName(String eventName)
        throws SystemException {
        Object[] finderArgs = new Object[] { eventName };

        List<UserSurvey> list = (List<UserSurvey>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_EVENTNAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

                if (eventName == null) {
                    query.append("eventName IS NULL");
                } else {
                    query.append("eventName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (eventName != null) {
                    qPos.add(eventName);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserSurvey>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_EVENTNAME,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<UserSurvey> findByEventName(String eventName, int start, int end)
        throws SystemException {
        return findByEventName(eventName, start, end, null);
    }

    public List<UserSurvey> findByEventName(String eventName, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                eventName,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<UserSurvey> list = (List<UserSurvey>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_EVENTNAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

                if (eventName == null) {
                    query.append("eventName IS NULL");
                } else {
                    query.append("eventName = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (eventName != null) {
                    qPos.add(eventName);
                }

                list = (List<UserSurvey>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserSurvey>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_EVENTNAME,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public UserSurvey findByEventName_First(String eventName,
        OrderByComparator obc)
        throws NoSuchUserSurveyException, SystemException {
        List<UserSurvey> list = findByEventName(eventName, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No UserSurvey exists with the key {");

            msg.append("eventName=" + eventName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchUserSurveyException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public UserSurvey findByEventName_Last(String eventName,
        OrderByComparator obc)
        throws NoSuchUserSurveyException, SystemException {
        int count = countByEventName(eventName);

        List<UserSurvey> list = findByEventName(eventName, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No UserSurvey exists with the key {");

            msg.append("eventName=" + eventName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchUserSurveyException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public UserSurvey[] findByEventName_PrevAndNext(UserSurveyPK userSurveyPK,
        String eventName, OrderByComparator obc)
        throws NoSuchUserSurveyException, SystemException {
        UserSurvey userSurvey = findByPrimaryKey(userSurveyPK);

        int count = countByEventName(eventName);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

            if (eventName == null) {
                query.append("eventName IS NULL");
            } else {
                query.append("eventName = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (eventName != null) {
                qPos.add(eventName);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    userSurvey);

            UserSurvey[] array = new UserSurveyImpl[3];

            array[0] = (UserSurvey) objArray[0];
            array[1] = (UserSurvey) objArray[1];
            array[2] = (UserSurvey) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<UserSurvey> findByUserIdEventName(Long userId, String eventName)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId, eventName };

        List<UserSurvey> list = (List<UserSurvey>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERIDEVENTNAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (eventName == null) {
                    query.append("eventName IS NULL");
                } else {
                    query.append("eventName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (eventName != null) {
                    qPos.add(eventName);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserSurvey>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERIDEVENTNAME,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<UserSurvey> findByUserIdEventName(Long userId,
        String eventName, int start, int end) throws SystemException {
        return findByUserIdEventName(userId, eventName, start, end, null);
    }

    public List<UserSurvey> findByUserIdEventName(Long userId,
        String eventName, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                userId,
                
                eventName,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<UserSurvey> list = (List<UserSurvey>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERIDEVENTNAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (eventName == null) {
                    query.append("eventName IS NULL");
                } else {
                    query.append("eventName = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (eventName != null) {
                    qPos.add(eventName);
                }

                list = (List<UserSurvey>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserSurvey>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERIDEVENTNAME,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public UserSurvey findByUserIdEventName_First(Long userId,
        String eventName, OrderByComparator obc)
        throws NoSuchUserSurveyException, SystemException {
        List<UserSurvey> list = findByUserIdEventName(userId, eventName, 0, 1,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No UserSurvey exists with the key {");

            msg.append("userId=" + userId);

            msg.append(", ");
            msg.append("eventName=" + eventName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchUserSurveyException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public UserSurvey findByUserIdEventName_Last(Long userId, String eventName,
        OrderByComparator obc)
        throws NoSuchUserSurveyException, SystemException {
        int count = countByUserIdEventName(userId, eventName);

        List<UserSurvey> list = findByUserIdEventName(userId, eventName,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No UserSurvey exists with the key {");

            msg.append("userId=" + userId);

            msg.append(", ");
            msg.append("eventName=" + eventName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchUserSurveyException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public UserSurvey[] findByUserIdEventName_PrevAndNext(
        UserSurveyPK userSurveyPK, Long userId, String eventName,
        OrderByComparator obc)
        throws NoSuchUserSurveyException, SystemException {
        UserSurvey userSurvey = findByPrimaryKey(userSurveyPK);

        int count = countByUserIdEventName(userId, eventName);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

            if (userId == null) {
                query.append("userId IS NULL");
            } else {
                query.append("userId = ?");
            }

            query.append(" AND ");

            if (eventName == null) {
                query.append("eventName IS NULL");
            } else {
                query.append("eventName = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (userId != null) {
                qPos.add(userId.longValue());
            }

            if (eventName != null) {
                qPos.add(eventName);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    userSurvey);

            UserSurvey[] array = new UserSurveyImpl[3];

            array[0] = (UserSurvey) objArray[0];
            array[1] = (UserSurvey) objArray[1];
            array[2] = (UserSurvey) objArray[2];

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

    public List<UserSurvey> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<UserSurvey> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<UserSurvey> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<UserSurvey> list = (List<UserSurvey>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.surveys.model.UserSurvey ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<UserSurvey>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<UserSurvey>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserSurvey>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByUserId(Long userId) throws SystemException {
        for (UserSurvey userSurvey : findByUserId(userId)) {
            remove(userSurvey);
        }
    }

    public void removeByEventName(String eventName) throws SystemException {
        for (UserSurvey userSurvey : findByEventName(eventName)) {
            remove(userSurvey);
        }
    }

    public void removeByUserIdEventName(Long userId, String eventName)
        throws SystemException {
        for (UserSurvey userSurvey : findByUserIdEventName(userId, eventName)) {
            remove(userSurvey);
        }
    }

    public void removeAll() throws SystemException {
        for (UserSurvey userSurvey : findAll()) {
            remove(userSurvey);
        }
    }

    public int countByUserId(Long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByEventName(String eventName) throws SystemException {
        Object[] finderArgs = new Object[] { eventName };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTNAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

                if (eventName == null) {
                    query.append("eventName IS NULL");
                } else {
                    query.append("eventName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (eventName != null) {
                    qPos.add(eventName);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EVENTNAME,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByUserIdEventName(Long userId, String eventName)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId, eventName };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERIDEVENTNAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.surveys.model.UserSurvey WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (eventName == null) {
                    query.append("eventName IS NULL");
                } else {
                    query.append("eventName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (eventName != null) {
                    qPos.add(eventName);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERIDEVENTNAME,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.surveys.model.UserSurvey");

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
                        "value.object.listener.com.ext.portlet.surveys.model.UserSurvey")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<UserSurvey>> listenersList = new ArrayList<ModelListener<UserSurvey>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<UserSurvey>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
