package com.ext.utils.service.persistence;

import com.ext.utils.NoSuchUserForgotPasswordRequestException;
import com.ext.utils.model.UserForgotPasswordRequest;
import com.ext.utils.model.impl.UserForgotPasswordRequestImpl;
import com.ext.utils.model.impl.UserForgotPasswordRequestModelImpl;

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


public class UserForgotPasswordRequestPersistenceImpl
    extends BasePersistenceImpl implements UserForgotPasswordRequestPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = UserForgotPasswordRequestImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
            UserForgotPasswordRequestModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
            UserForgotPasswordRequestModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
            UserForgotPasswordRequestModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
            UserForgotPasswordRequestModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
            UserForgotPasswordRequestModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(UserForgotPasswordRequestPersistenceImpl.class);
    @BeanReference(name = "com.ext.utils.service.persistence.UserForgotPasswordRequestPersistence.impl")
    protected com.ext.utils.service.persistence.UserForgotPasswordRequestPersistence userForgotPasswordRequestPersistence;

    public void cacheResult(UserForgotPasswordRequest userForgotPasswordRequest) {
        EntityCacheUtil.putResult(UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
            UserForgotPasswordRequestImpl.class,
            userForgotPasswordRequest.getPrimaryKey(), userForgotPasswordRequest);
    }

    public void cacheResult(
        List<UserForgotPasswordRequest> userForgotPasswordRequests) {
        for (UserForgotPasswordRequest userForgotPasswordRequest : userForgotPasswordRequests) {
            if (EntityCacheUtil.getResult(
                        UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
                        UserForgotPasswordRequestImpl.class,
                        userForgotPasswordRequest.getPrimaryKey(), this) == null) {
                cacheResult(userForgotPasswordRequest);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(UserForgotPasswordRequestImpl.class.getName());
        EntityCacheUtil.clearCache(UserForgotPasswordRequestImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public UserForgotPasswordRequest create(String token) {
        UserForgotPasswordRequest userForgotPasswordRequest = new UserForgotPasswordRequestImpl();

        userForgotPasswordRequest.setNew(true);
        userForgotPasswordRequest.setPrimaryKey(token);

        return userForgotPasswordRequest;
    }

    public UserForgotPasswordRequest remove(String token)
        throws NoSuchUserForgotPasswordRequestException, SystemException {
        Session session = null;

        try {
            session = openSession();

            UserForgotPasswordRequest userForgotPasswordRequest = (UserForgotPasswordRequest) session.get(UserForgotPasswordRequestImpl.class,
                    token);

            if (userForgotPasswordRequest == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No UserForgotPasswordRequest exists with the primary key " +
                        token);
                }

                throw new NoSuchUserForgotPasswordRequestException(
                    "No UserForgotPasswordRequest exists with the primary key " +
                    token);
            }

            return remove(userForgotPasswordRequest);
        } catch (NoSuchUserForgotPasswordRequestException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public UserForgotPasswordRequest remove(
        UserForgotPasswordRequest userForgotPasswordRequest)
        throws SystemException {
        for (ModelListener<UserForgotPasswordRequest> listener : listeners) {
            listener.onBeforeRemove(userForgotPasswordRequest);
        }

        userForgotPasswordRequest = removeImpl(userForgotPasswordRequest);

        for (ModelListener<UserForgotPasswordRequest> listener : listeners) {
            listener.onAfterRemove(userForgotPasswordRequest);
        }

        return userForgotPasswordRequest;
    }

    protected UserForgotPasswordRequest removeImpl(
        UserForgotPasswordRequest userForgotPasswordRequest)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (userForgotPasswordRequest.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(UserForgotPasswordRequestImpl.class,
                        userForgotPasswordRequest.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(userForgotPasswordRequest);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
            UserForgotPasswordRequestImpl.class,
            userForgotPasswordRequest.getPrimaryKey());

        return userForgotPasswordRequest;
    }

    /**
     * @deprecated Use <code>update(UserForgotPasswordRequest userForgotPasswordRequest, boolean merge)</code>.
     */
    public UserForgotPasswordRequest update(
        UserForgotPasswordRequest userForgotPasswordRequest)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(UserForgotPasswordRequest userForgotPasswordRequest) method. Use update(UserForgotPasswordRequest userForgotPasswordRequest, boolean merge) instead.");
        }

        return update(userForgotPasswordRequest, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                userForgotPasswordRequest the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when userForgotPasswordRequest is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public UserForgotPasswordRequest update(
        UserForgotPasswordRequest userForgotPasswordRequest, boolean merge)
        throws SystemException {
        boolean isNew = userForgotPasswordRequest.isNew();

        for (ModelListener<UserForgotPasswordRequest> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(userForgotPasswordRequest);
            } else {
                listener.onBeforeUpdate(userForgotPasswordRequest);
            }
        }

        userForgotPasswordRequest = updateImpl(userForgotPasswordRequest, merge);

        for (ModelListener<UserForgotPasswordRequest> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(userForgotPasswordRequest);
            } else {
                listener.onAfterUpdate(userForgotPasswordRequest);
            }
        }

        return userForgotPasswordRequest;
    }

    public UserForgotPasswordRequest updateImpl(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, userForgotPasswordRequest, merge);

            userForgotPasswordRequest.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
            UserForgotPasswordRequestImpl.class,
            userForgotPasswordRequest.getPrimaryKey(), userForgotPasswordRequest);

        return userForgotPasswordRequest;
    }

    public UserForgotPasswordRequest findByPrimaryKey(String token)
        throws NoSuchUserForgotPasswordRequestException, SystemException {
        UserForgotPasswordRequest userForgotPasswordRequest = fetchByPrimaryKey(token);

        if (userForgotPasswordRequest == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No UserForgotPasswordRequest exists with the primary key " +
                    token);
            }

            throw new NoSuchUserForgotPasswordRequestException(
                "No UserForgotPasswordRequest exists with the primary key " +
                token);
        }

        return userForgotPasswordRequest;
    }

    public UserForgotPasswordRequest fetchByPrimaryKey(String token)
        throws SystemException {
        UserForgotPasswordRequest userForgotPasswordRequest = (UserForgotPasswordRequest) EntityCacheUtil.getResult(UserForgotPasswordRequestModelImpl.ENTITY_CACHE_ENABLED,
                UserForgotPasswordRequestImpl.class, token, this);

        if (userForgotPasswordRequest == null) {
            Session session = null;

            try {
                session = openSession();

                userForgotPasswordRequest = (UserForgotPasswordRequest) session.get(UserForgotPasswordRequestImpl.class,
                        token);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (userForgotPasswordRequest != null) {
                    cacheResult(userForgotPasswordRequest);
                }

                closeSession(session);
            }
        }

        return userForgotPasswordRequest;
    }

    public List<UserForgotPasswordRequest> findByUserId(Long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        List<UserForgotPasswordRequest> list = (List<UserForgotPasswordRequest>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.utils.model.UserForgotPasswordRequest WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("created DESC");

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
                    list = new ArrayList<UserForgotPasswordRequest>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<UserForgotPasswordRequest> findByUserId(Long userId, int start,
        int end) throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    public List<UserForgotPasswordRequest> findByUserId(Long userId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                userId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<UserForgotPasswordRequest> list = (List<UserForgotPasswordRequest>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.utils.model.UserForgotPasswordRequest WHERE ");

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
                else {
                    query.append("ORDER BY ");

                    query.append("created DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                list = (List<UserForgotPasswordRequest>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserForgotPasswordRequest>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public UserForgotPasswordRequest findByUserId_First(Long userId,
        OrderByComparator obc)
        throws NoSuchUserForgotPasswordRequestException, SystemException {
        List<UserForgotPasswordRequest> list = findByUserId(userId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No UserForgotPasswordRequest exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchUserForgotPasswordRequestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public UserForgotPasswordRequest findByUserId_Last(Long userId,
        OrderByComparator obc)
        throws NoSuchUserForgotPasswordRequestException, SystemException {
        int count = countByUserId(userId);

        List<UserForgotPasswordRequest> list = findByUserId(userId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No UserForgotPasswordRequest exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchUserForgotPasswordRequestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public UserForgotPasswordRequest[] findByUserId_PrevAndNext(String token,
        Long userId, OrderByComparator obc)
        throws NoSuchUserForgotPasswordRequestException, SystemException {
        UserForgotPasswordRequest userForgotPasswordRequest = findByPrimaryKey(token);

        int count = countByUserId(userId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.utils.model.UserForgotPasswordRequest WHERE ");

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
            else {
                query.append("ORDER BY ");

                query.append("created DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (userId != null) {
                qPos.add(userId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    userForgotPasswordRequest);

            UserForgotPasswordRequest[] array = new UserForgotPasswordRequestImpl[3];

            array[0] = (UserForgotPasswordRequest) objArray[0];
            array[1] = (UserForgotPasswordRequest) objArray[1];
            array[2] = (UserForgotPasswordRequest) objArray[2];

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

    public List<UserForgotPasswordRequest> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<UserForgotPasswordRequest> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<UserForgotPasswordRequest> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<UserForgotPasswordRequest> list = (List<UserForgotPasswordRequest>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.utils.model.UserForgotPasswordRequest ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("created DESC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<UserForgotPasswordRequest>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<UserForgotPasswordRequest>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserForgotPasswordRequest>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByUserId(Long userId) throws SystemException {
        for (UserForgotPasswordRequest userForgotPasswordRequest : findByUserId(
                userId)) {
            remove(userForgotPasswordRequest);
        }
    }

    public void removeAll() throws SystemException {
        for (UserForgotPasswordRequest userForgotPasswordRequest : findAll()) {
            remove(userForgotPasswordRequest);
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
                    "FROM com.ext.utils.model.UserForgotPasswordRequest WHERE ");

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

    public int countAll() throws SystemException {
        Object[] finderArgs = new Object[0];

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(
                        "SELECT COUNT(*) FROM com.ext.utils.model.UserForgotPasswordRequest");

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
                        "value.object.listener.com.ext.utils.model.UserForgotPasswordRequest")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<UserForgotPasswordRequest>> listenersList = new ArrayList<ModelListener<UserForgotPasswordRequest>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<UserForgotPasswordRequest>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
