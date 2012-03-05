package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException;
import com.ext.portlet.ontology.model.FocusAreaOntologyTerm;
import com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermImpl;
import com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermModelImpl;

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


public class FocusAreaOntologyTermPersistenceImpl extends BasePersistenceImpl
    implements FocusAreaOntologyTermPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = FocusAreaOntologyTermImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_FOCUSAREAID = new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByFocusAreaId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_FOCUSAREAID = new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByFocusAreaId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_FOCUSAREAID = new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByFocusAreaId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(FocusAreaOntologyTermPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologySpacePersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologySpacePersistence ontologySpacePersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence ontologyTermEntityPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.FocusAreaPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.FocusAreaPersistence focusAreaPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence;

    public void cacheResult(FocusAreaOntologyTerm focusAreaOntologyTerm) {
        EntityCacheUtil.putResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            focusAreaOntologyTerm.getPrimaryKey(), focusAreaOntologyTerm);
    }

    public void cacheResult(List<FocusAreaOntologyTerm> focusAreaOntologyTerms) {
        for (FocusAreaOntologyTerm focusAreaOntologyTerm : focusAreaOntologyTerms) {
            if (EntityCacheUtil.getResult(
                        FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                        FocusAreaOntologyTermImpl.class,
                        focusAreaOntologyTerm.getPrimaryKey(), this) == null) {
                cacheResult(focusAreaOntologyTerm);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(FocusAreaOntologyTermImpl.class.getName());
        EntityCacheUtil.clearCache(FocusAreaOntologyTermImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public FocusAreaOntologyTerm create(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK) {
        FocusAreaOntologyTerm focusAreaOntologyTerm = new FocusAreaOntologyTermImpl();

        focusAreaOntologyTerm.setNew(true);
        focusAreaOntologyTerm.setPrimaryKey(focusAreaOntologyTermPK);

        return focusAreaOntologyTerm;
    }

    public FocusAreaOntologyTerm remove(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FocusAreaOntologyTerm focusAreaOntologyTerm = (FocusAreaOntologyTerm) session.get(FocusAreaOntologyTermImpl.class,
                    focusAreaOntologyTermPK);

            if (focusAreaOntologyTerm == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No FocusAreaOntologyTerm exists with the primary key " +
                        focusAreaOntologyTermPK);
                }

                throw new NoSuchFocusAreaOntologyTermException(
                    "No FocusAreaOntologyTerm exists with the primary key " +
                    focusAreaOntologyTermPK);
            }

            return remove(focusAreaOntologyTerm);
        } catch (NoSuchFocusAreaOntologyTermException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public FocusAreaOntologyTerm remove(
        FocusAreaOntologyTerm focusAreaOntologyTerm) throws SystemException {
        for (ModelListener<FocusAreaOntologyTerm> listener : listeners) {
            listener.onBeforeRemove(focusAreaOntologyTerm);
        }

        focusAreaOntologyTerm = removeImpl(focusAreaOntologyTerm);

        for (ModelListener<FocusAreaOntologyTerm> listener : listeners) {
            listener.onAfterRemove(focusAreaOntologyTerm);
        }

        return focusAreaOntologyTerm;
    }

    protected FocusAreaOntologyTerm removeImpl(
        FocusAreaOntologyTerm focusAreaOntologyTerm) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (focusAreaOntologyTerm.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(FocusAreaOntologyTermImpl.class,
                        focusAreaOntologyTerm.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(focusAreaOntologyTerm);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            focusAreaOntologyTerm.getPrimaryKey());

        return focusAreaOntologyTerm;
    }

    /**
     * @deprecated Use <code>update(FocusAreaOntologyTerm focusAreaOntologyTerm, boolean merge)</code>.
     */
    public FocusAreaOntologyTerm update(
        FocusAreaOntologyTerm focusAreaOntologyTerm) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(FocusAreaOntologyTerm focusAreaOntologyTerm) method. Use update(FocusAreaOntologyTerm focusAreaOntologyTerm, boolean merge) instead.");
        }

        return update(focusAreaOntologyTerm, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                focusAreaOntologyTerm the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when focusAreaOntologyTerm is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public FocusAreaOntologyTerm update(
        FocusAreaOntologyTerm focusAreaOntologyTerm, boolean merge)
        throws SystemException {
        boolean isNew = focusAreaOntologyTerm.isNew();

        for (ModelListener<FocusAreaOntologyTerm> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(focusAreaOntologyTerm);
            } else {
                listener.onBeforeUpdate(focusAreaOntologyTerm);
            }
        }

        focusAreaOntologyTerm = updateImpl(focusAreaOntologyTerm, merge);

        for (ModelListener<FocusAreaOntologyTerm> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(focusAreaOntologyTerm);
            } else {
                listener.onAfterUpdate(focusAreaOntologyTerm);
            }
        }

        return focusAreaOntologyTerm;
    }

    public FocusAreaOntologyTerm updateImpl(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, focusAreaOntologyTerm, merge);

            focusAreaOntologyTerm.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            focusAreaOntologyTerm.getPrimaryKey(), focusAreaOntologyTerm);

        return focusAreaOntologyTerm;
    }

    public FocusAreaOntologyTerm findByPrimaryKey(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        FocusAreaOntologyTerm focusAreaOntologyTerm = fetchByPrimaryKey(focusAreaOntologyTermPK);

        if (focusAreaOntologyTerm == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No FocusAreaOntologyTerm exists with the primary key " +
                    focusAreaOntologyTermPK);
            }

            throw new NoSuchFocusAreaOntologyTermException(
                "No FocusAreaOntologyTerm exists with the primary key " +
                focusAreaOntologyTermPK);
        }

        return focusAreaOntologyTerm;
    }

    public FocusAreaOntologyTerm fetchByPrimaryKey(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws SystemException {
        FocusAreaOntologyTerm focusAreaOntologyTerm = (FocusAreaOntologyTerm) EntityCacheUtil.getResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                FocusAreaOntologyTermImpl.class, focusAreaOntologyTermPK, this);

        if (focusAreaOntologyTerm == null) {
            Session session = null;

            try {
                session = openSession();

                focusAreaOntologyTerm = (FocusAreaOntologyTerm) session.get(FocusAreaOntologyTermImpl.class,
                        focusAreaOntologyTermPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (focusAreaOntologyTerm != null) {
                    cacheResult(focusAreaOntologyTerm);
                }

                closeSession(session);
            }
        }

        return focusAreaOntologyTerm;
    }

    public List<FocusAreaOntologyTerm> findByFocusAreaId(Long focusAreaId)
        throws SystemException {
        Object[] finderArgs = new Object[] { focusAreaId };

        List<FocusAreaOntologyTerm> list = (List<FocusAreaOntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_FOCUSAREAID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.FocusAreaOntologyTerm WHERE ");

                if (focusAreaId == null) {
                    query.append("focusAreaId IS NULL");
                } else {
                    query.append("focusAreaId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (focusAreaId != null) {
                    qPos.add(focusAreaId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<FocusAreaOntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_FOCUSAREAID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<FocusAreaOntologyTerm> findByFocusAreaId(Long focusAreaId,
        int start, int end) throws SystemException {
        return findByFocusAreaId(focusAreaId, start, end, null);
    }

    public List<FocusAreaOntologyTerm> findByFocusAreaId(Long focusAreaId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                focusAreaId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<FocusAreaOntologyTerm> list = (List<FocusAreaOntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_FOCUSAREAID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.FocusAreaOntologyTerm WHERE ");

                if (focusAreaId == null) {
                    query.append("focusAreaId IS NULL");
                } else {
                    query.append("focusAreaId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (focusAreaId != null) {
                    qPos.add(focusAreaId.longValue());
                }

                list = (List<FocusAreaOntologyTerm>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<FocusAreaOntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_FOCUSAREAID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public FocusAreaOntologyTerm findByFocusAreaId_First(Long focusAreaId,
        OrderByComparator obc)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        List<FocusAreaOntologyTerm> list = findByFocusAreaId(focusAreaId, 0, 1,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No FocusAreaOntologyTerm exists with the key {");

            msg.append("focusAreaId=" + focusAreaId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFocusAreaOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public FocusAreaOntologyTerm findByFocusAreaId_Last(Long focusAreaId,
        OrderByComparator obc)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        int count = countByFocusAreaId(focusAreaId);

        List<FocusAreaOntologyTerm> list = findByFocusAreaId(focusAreaId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No FocusAreaOntologyTerm exists with the key {");

            msg.append("focusAreaId=" + focusAreaId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFocusAreaOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public FocusAreaOntologyTerm[] findByFocusAreaId_PrevAndNext(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK, Long focusAreaId,
        OrderByComparator obc)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        FocusAreaOntologyTerm focusAreaOntologyTerm = findByPrimaryKey(focusAreaOntologyTermPK);

        int count = countByFocusAreaId(focusAreaId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.ontology.model.FocusAreaOntologyTerm WHERE ");

            if (focusAreaId == null) {
                query.append("focusAreaId IS NULL");
            } else {
                query.append("focusAreaId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (focusAreaId != null) {
                qPos.add(focusAreaId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    focusAreaOntologyTerm);

            FocusAreaOntologyTerm[] array = new FocusAreaOntologyTermImpl[3];

            array[0] = (FocusAreaOntologyTerm) objArray[0];
            array[1] = (FocusAreaOntologyTerm) objArray[1];
            array[2] = (FocusAreaOntologyTerm) objArray[2];

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

    public List<FocusAreaOntologyTerm> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<FocusAreaOntologyTerm> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<FocusAreaOntologyTerm> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<FocusAreaOntologyTerm> list = (List<FocusAreaOntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.FocusAreaOntologyTerm ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<FocusAreaOntologyTerm>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<FocusAreaOntologyTerm>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<FocusAreaOntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByFocusAreaId(Long focusAreaId) throws SystemException {
        for (FocusAreaOntologyTerm focusAreaOntologyTerm : findByFocusAreaId(
                focusAreaId)) {
            remove(focusAreaOntologyTerm);
        }
    }

    public void removeAll() throws SystemException {
        for (FocusAreaOntologyTerm focusAreaOntologyTerm : findAll()) {
            remove(focusAreaOntologyTerm);
        }
    }

    public int countByFocusAreaId(Long focusAreaId) throws SystemException {
        Object[] finderArgs = new Object[] { focusAreaId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FOCUSAREAID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.ontology.model.FocusAreaOntologyTerm WHERE ");

                if (focusAreaId == null) {
                    query.append("focusAreaId IS NULL");
                } else {
                    query.append("focusAreaId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (focusAreaId != null) {
                    qPos.add(focusAreaId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FOCUSAREAID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.ontology.model.FocusAreaOntologyTerm");

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
                        "value.object.listener.com.ext.portlet.ontology.model.FocusAreaOntologyTerm")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FocusAreaOntologyTerm>> listenersList = new ArrayList<ModelListener<FocusAreaOntologyTerm>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FocusAreaOntologyTerm>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
