/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debates.service.persistence;

import com.ext.portlet.debates.NoSuchDebateDiscussionMapException;
import com.ext.portlet.debates.model.DebateDiscussionMap;
import com.ext.portlet.debates.model.impl.DebateDiscussionMapImpl;
import com.ext.portlet.debates.model.impl.DebateDiscussionMapModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DebateDiscussionMapPersistenceImpl extends BasePersistenceImpl
    implements DebateDiscussionMapPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateDiscussionMapImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_DEBATEMESSAGEFROMDISCUSSION =
        new FinderPath(DebateDiscussionMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateDiscussionMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByDebateMessageFromDiscussion",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_DEBATEMESSAGEFROMDISCUSSION =
        new FinderPath(DebateDiscussionMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateDiscussionMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByDebateMessageFromDiscussion",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateDiscussionMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateDiscussionMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateDiscussionMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateDiscussionMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateDiscussionMapPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.debates.service.persistence.DebateDiscussionMapPersistence.impl")
    protected com.ext.portlet.debates.service.persistence.DebateDiscussionMapPersistence debateDiscussionMapPersistence;

    public void cacheResult(DebateDiscussionMap debateDiscussionMap) {
        EntityCacheUtil.putResult(DebateDiscussionMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateDiscussionMapImpl.class, debateDiscussionMap.getPrimaryKey(),
            debateDiscussionMap);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMESSAGEFROMDISCUSSION,
            new Object[] { debateDiscussionMap.getDebateMessageId() },
            debateDiscussionMap);
    }

    public void cacheResult(List<DebateDiscussionMap> debateDiscussionMaps) {
        for (DebateDiscussionMap debateDiscussionMap : debateDiscussionMaps) {
            if (EntityCacheUtil.getResult(
                        DebateDiscussionMapModelImpl.ENTITY_CACHE_ENABLED,
                        DebateDiscussionMapImpl.class,
                        debateDiscussionMap.getPrimaryKey(), this) == null) {
                cacheResult(debateDiscussionMap);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateDiscussionMapImpl.class.getName());
        EntityCacheUtil.clearCache(DebateDiscussionMapImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateDiscussionMap create(Long debateMessageId) {
        DebateDiscussionMap debateDiscussionMap = new DebateDiscussionMapImpl();

        debateDiscussionMap.setNew(true);
        debateDiscussionMap.setPrimaryKey(debateMessageId);

        return debateDiscussionMap;
    }

    public DebateDiscussionMap remove(Long debateMessageId)
        throws NoSuchDebateDiscussionMapException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateDiscussionMap debateDiscussionMap = (DebateDiscussionMap) session.get(DebateDiscussionMapImpl.class,
                    debateMessageId);

            if (debateDiscussionMap == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DebateDiscussionMap exists with the primary key " +
                        debateMessageId);
                }

                throw new NoSuchDebateDiscussionMapException(
                    "No DebateDiscussionMap exists with the primary key " +
                    debateMessageId);
            }

            return remove(debateDiscussionMap);
        } catch (NoSuchDebateDiscussionMapException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateDiscussionMap remove(DebateDiscussionMap debateDiscussionMap)
        throws SystemException {
        for (ModelListener<DebateDiscussionMap> listener : listeners) {
            listener.onBeforeRemove(debateDiscussionMap);
        }

        debateDiscussionMap = removeImpl(debateDiscussionMap);

        for (ModelListener<DebateDiscussionMap> listener : listeners) {
            listener.onAfterRemove(debateDiscussionMap);
        }

        return debateDiscussionMap;
    }

    protected DebateDiscussionMap removeImpl(
        DebateDiscussionMap debateDiscussionMap) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateDiscussionMap.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateDiscussionMapImpl.class,
                        debateDiscussionMap.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateDiscussionMap);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        DebateDiscussionMapModelImpl debateDiscussionMapModelImpl = (DebateDiscussionMapModelImpl) debateDiscussionMap;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEBATEMESSAGEFROMDISCUSSION,
            new Object[] {
                debateDiscussionMapModelImpl.getOriginalDebateMessageId()
            });

        EntityCacheUtil.removeResult(DebateDiscussionMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateDiscussionMapImpl.class, debateDiscussionMap.getPrimaryKey());

        return debateDiscussionMap;
    }

    /**
     * @deprecated Use <code>update(DebateDiscussionMap debateDiscussionMap, boolean merge)</code>.
     */
    public DebateDiscussionMap update(DebateDiscussionMap debateDiscussionMap)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateDiscussionMap debateDiscussionMap) method. Use update(DebateDiscussionMap debateDiscussionMap, boolean merge) instead.");
        }

        return update(debateDiscussionMap, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateDiscussionMap the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateDiscussionMap is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateDiscussionMap update(DebateDiscussionMap debateDiscussionMap,
        boolean merge) throws SystemException {
        boolean isNew = debateDiscussionMap.isNew();

        for (ModelListener<DebateDiscussionMap> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateDiscussionMap);
            } else {
                listener.onBeforeUpdate(debateDiscussionMap);
            }
        }

        debateDiscussionMap = updateImpl(debateDiscussionMap, merge);

        for (ModelListener<DebateDiscussionMap> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateDiscussionMap);
            } else {
                listener.onAfterUpdate(debateDiscussionMap);
            }
        }

        return debateDiscussionMap;
    }

    public DebateDiscussionMap updateImpl(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap,
        boolean merge) throws SystemException {
        boolean isNew = debateDiscussionMap.isNew();

        DebateDiscussionMapModelImpl debateDiscussionMapModelImpl = (DebateDiscussionMapModelImpl) debateDiscussionMap;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateDiscussionMap, merge);

            debateDiscussionMap.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateDiscussionMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateDiscussionMapImpl.class, debateDiscussionMap.getPrimaryKey(),
            debateDiscussionMap);

        if (!isNew &&
                (!Validator.equals(debateDiscussionMap.getDebateMessageId(),
                    debateDiscussionMapModelImpl.getOriginalDebateMessageId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEBATEMESSAGEFROMDISCUSSION,
                new Object[] {
                    debateDiscussionMapModelImpl.getOriginalDebateMessageId()
                });
        }

        if (isNew ||
                (!Validator.equals(debateDiscussionMap.getDebateMessageId(),
                    debateDiscussionMapModelImpl.getOriginalDebateMessageId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMESSAGEFROMDISCUSSION,
                new Object[] { debateDiscussionMap.getDebateMessageId() },
                debateDiscussionMap);
        }

        return debateDiscussionMap;
    }

    public DebateDiscussionMap findByPrimaryKey(Long debateMessageId)
        throws NoSuchDebateDiscussionMapException, SystemException {
        DebateDiscussionMap debateDiscussionMap = fetchByPrimaryKey(debateMessageId);

        if (debateDiscussionMap == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateDiscussionMap exists with the primary key " +
                    debateMessageId);
            }

            throw new NoSuchDebateDiscussionMapException(
                "No DebateDiscussionMap exists with the primary key " +
                debateMessageId);
        }

        return debateDiscussionMap;
    }

    public DebateDiscussionMap fetchByPrimaryKey(Long debateMessageId)
        throws SystemException {
        DebateDiscussionMap debateDiscussionMap = (DebateDiscussionMap) EntityCacheUtil.getResult(DebateDiscussionMapModelImpl.ENTITY_CACHE_ENABLED,
                DebateDiscussionMapImpl.class, debateMessageId, this);

        if (debateDiscussionMap == null) {
            Session session = null;

            try {
                session = openSession();

                debateDiscussionMap = (DebateDiscussionMap) session.get(DebateDiscussionMapImpl.class,
                        debateMessageId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateDiscussionMap != null) {
                    cacheResult(debateDiscussionMap);
                }

                closeSession(session);
            }
        }

        return debateDiscussionMap;
    }

    public DebateDiscussionMap findByDebateMessageFromDiscussion(
        Long debateMessageId)
        throws NoSuchDebateDiscussionMapException, SystemException {
        DebateDiscussionMap debateDiscussionMap = fetchByDebateMessageFromDiscussion(debateMessageId);

        if (debateDiscussionMap == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateDiscussionMap exists with the key {");

            msg.append("debateMessageId=" + debateMessageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDebateDiscussionMapException(msg.toString());
        }

        return debateDiscussionMap;
    }

    public DebateDiscussionMap fetchByDebateMessageFromDiscussion(
        Long debateMessageId) throws SystemException {
        return fetchByDebateMessageFromDiscussion(debateMessageId, true);
    }

    public DebateDiscussionMap fetchByDebateMessageFromDiscussion(
        Long debateMessageId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateMessageId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DEBATEMESSAGEFROMDISCUSSION,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debates.model.DebateDiscussionMap WHERE ");

                if (debateMessageId == null) {
                    query.append("debateMessageId IS NULL");
                } else {
                    query.append("debateMessageId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateMessageId != null) {
                    qPos.add(debateMessageId.longValue());
                }

                List<DebateDiscussionMap> list = q.list();

                result = list;

                DebateDiscussionMap debateDiscussionMap = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMESSAGEFROMDISCUSSION,
                        finderArgs, list);
                } else {
                    debateDiscussionMap = list.get(0);

                    cacheResult(debateDiscussionMap);

                    if ((debateDiscussionMap.getDebateMessageId() == null) ||
                            !debateDiscussionMap.getDebateMessageId()
                                                    .equals(debateMessageId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMESSAGEFROMDISCUSSION,
                            finderArgs, debateDiscussionMap);
                    }
                }

                return debateDiscussionMap;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMESSAGEFROMDISCUSSION,
                        finderArgs, new ArrayList<DebateDiscussionMap>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (DebateDiscussionMap) result;
            }
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

    public List<DebateDiscussionMap> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateDiscussionMap> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateDiscussionMap> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateDiscussionMap> list = (List<DebateDiscussionMap>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debates.model.DebateDiscussionMap ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateDiscussionMap>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateDiscussionMap>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateDiscussionMap>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByDebateMessageFromDiscussion(Long debateMessageId)
        throws NoSuchDebateDiscussionMapException, SystemException {
        DebateDiscussionMap debateDiscussionMap = findByDebateMessageFromDiscussion(debateMessageId);

        remove(debateDiscussionMap);
    }

    public void removeAll() throws SystemException {
        for (DebateDiscussionMap debateDiscussionMap : findAll()) {
            remove(debateDiscussionMap);
        }
    }

    public int countByDebateMessageFromDiscussion(Long debateMessageId)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateMessageId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEBATEMESSAGEFROMDISCUSSION,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debates.model.DebateDiscussionMap WHERE ");

                if (debateMessageId == null) {
                    query.append("debateMessageId IS NULL");
                } else {
                    query.append("debateMessageId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateMessageId != null) {
                    qPos.add(debateMessageId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEBATEMESSAGEFROMDISCUSSION,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debates.model.DebateDiscussionMap");

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
                        "value.object.listener.com.ext.portlet.debates.model.DebateDiscussionMap")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateDiscussionMap>> listenersList = new ArrayList<ModelListener<DebateDiscussionMap>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateDiscussionMap>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
