package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.NoSuchDebateCategoryMapException;
import com.ext.portlet.debaterevision.model.DebateCategoryMap;
import com.ext.portlet.debaterevision.model.impl.DebateCategoryMapImpl;
import com.ext.portlet.debaterevision.model.impl.DebateCategoryMapModelImpl;

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


public class DebateCategoryMapPersistenceImpl extends BasePersistenceImpl
    implements DebateCategoryMapPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateCategoryMapImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_DEBATEIDCATEGORYID = new FinderPath(DebateCategoryMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchBydebateIdCategoryId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_DEBATEIDCATEGORYID = new FinderPath(DebateCategoryMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countBydebateIdCategoryId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateCategoryMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateCategoryMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateCategoryMapPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebatePersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebatePersistence debatePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemPersistence debateItemPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemReferencePersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemReferencePersistence debateItemReferencePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCategoryPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateCategoryPersistence debateCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCategoryMapPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateCategoryMapPersistence debateCategoryMapPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCommentPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateCommentPersistence debateCommentPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemVotePersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemVotePersistence debateItemVotePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemVoteStatsPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemVoteStatsPersistence debateItemVoteStatsPersistence;

    public void cacheResult(DebateCategoryMap debateCategoryMap) {
        EntityCacheUtil.putResult(DebateCategoryMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryMapImpl.class, debateCategoryMap.getPrimaryKey(),
            debateCategoryMap);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEIDCATEGORYID,
            new Object[] {
                debateCategoryMap.getDebateCategoryPK(),
                
            debateCategoryMap.getDebateId()
            }, debateCategoryMap);
    }

    public void cacheResult(List<DebateCategoryMap> debateCategoryMaps) {
        for (DebateCategoryMap debateCategoryMap : debateCategoryMaps) {
            if (EntityCacheUtil.getResult(
                        DebateCategoryMapModelImpl.ENTITY_CACHE_ENABLED,
                        DebateCategoryMapImpl.class,
                        debateCategoryMap.getPrimaryKey(), this) == null) {
                cacheResult(debateCategoryMap);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateCategoryMapImpl.class.getName());
        EntityCacheUtil.clearCache(DebateCategoryMapImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateCategoryMap create(Long debateCategoryMapPK) {
        DebateCategoryMap debateCategoryMap = new DebateCategoryMapImpl();

        debateCategoryMap.setNew(true);
        debateCategoryMap.setPrimaryKey(debateCategoryMapPK);

        return debateCategoryMap;
    }

    public DebateCategoryMap remove(Long debateCategoryMapPK)
        throws NoSuchDebateCategoryMapException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateCategoryMap debateCategoryMap = (DebateCategoryMap) session.get(DebateCategoryMapImpl.class,
                    debateCategoryMapPK);

            if (debateCategoryMap == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DebateCategoryMap exists with the primary key " +
                        debateCategoryMapPK);
                }

                throw new NoSuchDebateCategoryMapException(
                    "No DebateCategoryMap exists with the primary key " +
                    debateCategoryMapPK);
            }

            return remove(debateCategoryMap);
        } catch (NoSuchDebateCategoryMapException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateCategoryMap remove(DebateCategoryMap debateCategoryMap)
        throws SystemException {
        for (ModelListener<DebateCategoryMap> listener : listeners) {
            listener.onBeforeRemove(debateCategoryMap);
        }

        debateCategoryMap = removeImpl(debateCategoryMap);

        for (ModelListener<DebateCategoryMap> listener : listeners) {
            listener.onAfterRemove(debateCategoryMap);
        }

        return debateCategoryMap;
    }

    protected DebateCategoryMap removeImpl(DebateCategoryMap debateCategoryMap)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateCategoryMap.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateCategoryMapImpl.class,
                        debateCategoryMap.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateCategoryMap);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        DebateCategoryMapModelImpl debateCategoryMapModelImpl = (DebateCategoryMapModelImpl) debateCategoryMap;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEBATEIDCATEGORYID,
            new Object[] {
                debateCategoryMapModelImpl.getOriginalDebateCategoryPK(),
                
            debateCategoryMapModelImpl.getOriginalDebateId()
            });

        EntityCacheUtil.removeResult(DebateCategoryMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryMapImpl.class, debateCategoryMap.getPrimaryKey());

        return debateCategoryMap;
    }

    /**
     * @deprecated Use <code>update(DebateCategoryMap debateCategoryMap, boolean merge)</code>.
     */
    public DebateCategoryMap update(DebateCategoryMap debateCategoryMap)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateCategoryMap debateCategoryMap) method. Use update(DebateCategoryMap debateCategoryMap, boolean merge) instead.");
        }

        return update(debateCategoryMap, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateCategoryMap the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateCategoryMap is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateCategoryMap update(DebateCategoryMap debateCategoryMap,
        boolean merge) throws SystemException {
        boolean isNew = debateCategoryMap.isNew();

        for (ModelListener<DebateCategoryMap> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateCategoryMap);
            } else {
                listener.onBeforeUpdate(debateCategoryMap);
            }
        }

        debateCategoryMap = updateImpl(debateCategoryMap, merge);

        for (ModelListener<DebateCategoryMap> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateCategoryMap);
            } else {
                listener.onAfterUpdate(debateCategoryMap);
            }
        }

        return debateCategoryMap;
    }

    public DebateCategoryMap updateImpl(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap,
        boolean merge) throws SystemException {
        boolean isNew = debateCategoryMap.isNew();

        DebateCategoryMapModelImpl debateCategoryMapModelImpl = (DebateCategoryMapModelImpl) debateCategoryMap;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateCategoryMap, merge);

            debateCategoryMap.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateCategoryMapModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryMapImpl.class, debateCategoryMap.getPrimaryKey(),
            debateCategoryMap);

        if (!isNew &&
                (!Validator.equals(debateCategoryMap.getDebateCategoryPK(),
                    debateCategoryMapModelImpl.getOriginalDebateCategoryPK()) ||
                !Validator.equals(debateCategoryMap.getDebateId(),
                    debateCategoryMapModelImpl.getOriginalDebateId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEBATEIDCATEGORYID,
                new Object[] {
                    debateCategoryMapModelImpl.getOriginalDebateCategoryPK(),
                    
                debateCategoryMapModelImpl.getOriginalDebateId()
                });
        }

        if (isNew ||
                (!Validator.equals(debateCategoryMap.getDebateCategoryPK(),
                    debateCategoryMapModelImpl.getOriginalDebateCategoryPK()) ||
                !Validator.equals(debateCategoryMap.getDebateId(),
                    debateCategoryMapModelImpl.getOriginalDebateId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEIDCATEGORYID,
                new Object[] {
                    debateCategoryMap.getDebateCategoryPK(),
                    
                debateCategoryMap.getDebateId()
                }, debateCategoryMap);
        }

        return debateCategoryMap;
    }

    public DebateCategoryMap findByPrimaryKey(Long debateCategoryMapPK)
        throws NoSuchDebateCategoryMapException, SystemException {
        DebateCategoryMap debateCategoryMap = fetchByPrimaryKey(debateCategoryMapPK);

        if (debateCategoryMap == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateCategoryMap exists with the primary key " +
                    debateCategoryMapPK);
            }

            throw new NoSuchDebateCategoryMapException(
                "No DebateCategoryMap exists with the primary key " +
                debateCategoryMapPK);
        }

        return debateCategoryMap;
    }

    public DebateCategoryMap fetchByPrimaryKey(Long debateCategoryMapPK)
        throws SystemException {
        DebateCategoryMap debateCategoryMap = (DebateCategoryMap) EntityCacheUtil.getResult(DebateCategoryMapModelImpl.ENTITY_CACHE_ENABLED,
                DebateCategoryMapImpl.class, debateCategoryMapPK, this);

        if (debateCategoryMap == null) {
            Session session = null;

            try {
                session = openSession();

                debateCategoryMap = (DebateCategoryMap) session.get(DebateCategoryMapImpl.class,
                        debateCategoryMapPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateCategoryMap != null) {
                    cacheResult(debateCategoryMap);
                }

                closeSession(session);
            }
        }

        return debateCategoryMap;
    }

    public DebateCategoryMap findBydebateIdCategoryId(Long debateCategoryPK,
        Long debateId) throws NoSuchDebateCategoryMapException, SystemException {
        DebateCategoryMap debateCategoryMap = fetchBydebateIdCategoryId(debateCategoryPK,
                debateId);

        if (debateCategoryMap == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateCategoryMap exists with the key {");

            msg.append("debateCategoryPK=" + debateCategoryPK);

            msg.append(", ");
            msg.append("debateId=" + debateId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDebateCategoryMapException(msg.toString());
        }

        return debateCategoryMap;
    }

    public DebateCategoryMap fetchBydebateIdCategoryId(Long debateCategoryPK,
        Long debateId) throws SystemException {
        return fetchBydebateIdCategoryId(debateCategoryPK, debateId, true);
    }

    public DebateCategoryMap fetchBydebateIdCategoryId(Long debateCategoryPK,
        Long debateId, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { debateCategoryPK, debateId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DEBATEIDCATEGORYID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateCategoryMap WHERE ");

                if (debateCategoryPK == null) {
                    query.append("debateCategoryPK IS NULL");
                } else {
                    query.append("debateCategoryPK = ?");
                }

                query.append(" AND ");

                if (debateId == null) {
                    query.append("debateId IS NULL");
                } else {
                    query.append("debateId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateCategoryPK != null) {
                    qPos.add(debateCategoryPK.longValue());
                }

                if (debateId != null) {
                    qPos.add(debateId.longValue());
                }

                List<DebateCategoryMap> list = q.list();

                result = list;

                DebateCategoryMap debateCategoryMap = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEIDCATEGORYID,
                        finderArgs, list);
                } else {
                    debateCategoryMap = list.get(0);

                    cacheResult(debateCategoryMap);

                    if ((debateCategoryMap.getDebateCategoryPK() == null) ||
                            !debateCategoryMap.getDebateCategoryPK()
                                                  .equals(debateCategoryPK) ||
                            (debateCategoryMap.getDebateId() == null) ||
                            !debateCategoryMap.getDebateId().equals(debateId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEIDCATEGORYID,
                            finderArgs, debateCategoryMap);
                    }
                }

                return debateCategoryMap;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEIDCATEGORYID,
                        finderArgs, new ArrayList<DebateCategoryMap>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (DebateCategoryMap) result;
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

    public List<DebateCategoryMap> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateCategoryMap> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateCategoryMap> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateCategoryMap> list = (List<DebateCategoryMap>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateCategoryMap ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateCategoryMap>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateCategoryMap>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateCategoryMap>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeBydebateIdCategoryId(Long debateCategoryPK, Long debateId)
        throws NoSuchDebateCategoryMapException, SystemException {
        DebateCategoryMap debateCategoryMap = findBydebateIdCategoryId(debateCategoryPK,
                debateId);

        remove(debateCategoryMap);
    }

    public void removeAll() throws SystemException {
        for (DebateCategoryMap debateCategoryMap : findAll()) {
            remove(debateCategoryMap);
        }
    }

    public int countBydebateIdCategoryId(Long debateCategoryPK, Long debateId)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateCategoryPK, debateId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEBATEIDCATEGORYID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateCategoryMap WHERE ");

                if (debateCategoryPK == null) {
                    query.append("debateCategoryPK IS NULL");
                } else {
                    query.append("debateCategoryPK = ?");
                }

                query.append(" AND ");

                if (debateId == null) {
                    query.append("debateId IS NULL");
                } else {
                    query.append("debateId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateCategoryPK != null) {
                    qPos.add(debateCategoryPK.longValue());
                }

                if (debateId != null) {
                    qPos.add(debateId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEBATEIDCATEGORYID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debaterevision.model.DebateCategoryMap");

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
                        "value.object.listener.com.ext.portlet.debaterevision.model.DebateCategoryMap")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateCategoryMap>> listenersList = new ArrayList<ModelListener<DebateCategoryMap>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateCategoryMap>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
