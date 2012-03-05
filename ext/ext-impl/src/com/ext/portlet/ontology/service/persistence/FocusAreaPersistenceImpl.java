package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.NoSuchFocusAreaException;
import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.model.impl.FocusAreaImpl;
import com.ext.portlet.ontology.model.impl.FocusAreaModelImpl;

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


public class FocusAreaPersistenceImpl extends BasePersistenceImpl
    implements FocusAreaPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = FocusAreaImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
            "fetchByName", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByName", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(FocusAreaPersistenceImpl.class);
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

    public void cacheResult(FocusArea focusArea) {
        EntityCacheUtil.putResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaImpl.class, focusArea.getPrimaryKey(), focusArea);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { focusArea.getName() }, focusArea);
    }

    public void cacheResult(List<FocusArea> focusAreas) {
        for (FocusArea focusArea : focusAreas) {
            if (EntityCacheUtil.getResult(
                        FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                        FocusAreaImpl.class, focusArea.getPrimaryKey(), this) == null) {
                cacheResult(focusArea);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(FocusAreaImpl.class.getName());
        EntityCacheUtil.clearCache(FocusAreaImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public FocusArea create(Long id) {
        FocusArea focusArea = new FocusAreaImpl();

        focusArea.setNew(true);
        focusArea.setPrimaryKey(id);

        return focusArea;
    }

    public FocusArea remove(Long id)
        throws NoSuchFocusAreaException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FocusArea focusArea = (FocusArea) session.get(FocusAreaImpl.class,
                    id);

            if (focusArea == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No FocusArea exists with the primary key " + id);
                }

                throw new NoSuchFocusAreaException(
                    "No FocusArea exists with the primary key " + id);
            }

            return remove(focusArea);
        } catch (NoSuchFocusAreaException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public FocusArea remove(FocusArea focusArea) throws SystemException {
        for (ModelListener<FocusArea> listener : listeners) {
            listener.onBeforeRemove(focusArea);
        }

        focusArea = removeImpl(focusArea);

        for (ModelListener<FocusArea> listener : listeners) {
            listener.onAfterRemove(focusArea);
        }

        return focusArea;
    }

    protected FocusArea removeImpl(FocusArea focusArea)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (focusArea.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(FocusAreaImpl.class,
                        focusArea.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(focusArea);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        FocusAreaModelImpl focusAreaModelImpl = (FocusAreaModelImpl) focusArea;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { focusAreaModelImpl.getOriginalName() });

        EntityCacheUtil.removeResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaImpl.class, focusArea.getPrimaryKey());

        return focusArea;
    }

    /**
     * @deprecated Use <code>update(FocusArea focusArea, boolean merge)</code>.
     */
    public FocusArea update(FocusArea focusArea) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(FocusArea focusArea) method. Use update(FocusArea focusArea, boolean merge) instead.");
        }

        return update(focusArea, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                focusArea the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when focusArea is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public FocusArea update(FocusArea focusArea, boolean merge)
        throws SystemException {
        boolean isNew = focusArea.isNew();

        for (ModelListener<FocusArea> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(focusArea);
            } else {
                listener.onBeforeUpdate(focusArea);
            }
        }

        focusArea = updateImpl(focusArea, merge);

        for (ModelListener<FocusArea> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(focusArea);
            } else {
                listener.onAfterUpdate(focusArea);
            }
        }

        return focusArea;
    }

    public FocusArea updateImpl(
        com.ext.portlet.ontology.model.FocusArea focusArea, boolean merge)
        throws SystemException {
        boolean isNew = focusArea.isNew();

        FocusAreaModelImpl focusAreaModelImpl = (FocusAreaModelImpl) focusArea;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, focusArea, merge);

            focusArea.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaImpl.class, focusArea.getPrimaryKey(), focusArea);

        if (!isNew &&
                (!Validator.equals(focusArea.getName(),
                    focusAreaModelImpl.getOriginalName()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
                new Object[] { focusAreaModelImpl.getOriginalName() });
        }

        if (isNew ||
                (!Validator.equals(focusArea.getName(),
                    focusAreaModelImpl.getOriginalName()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                new Object[] { focusArea.getName() }, focusArea);
        }

        return focusArea;
    }

    public FocusArea findByPrimaryKey(Long id)
        throws NoSuchFocusAreaException, SystemException {
        FocusArea focusArea = fetchByPrimaryKey(id);

        if (focusArea == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No FocusArea exists with the primary key " + id);
            }

            throw new NoSuchFocusAreaException(
                "No FocusArea exists with the primary key " + id);
        }

        return focusArea;
    }

    public FocusArea fetchByPrimaryKey(Long id) throws SystemException {
        FocusArea focusArea = (FocusArea) EntityCacheUtil.getResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                FocusAreaImpl.class, id, this);

        if (focusArea == null) {
            Session session = null;

            try {
                session = openSession();

                focusArea = (FocusArea) session.get(FocusAreaImpl.class, id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (focusArea != null) {
                    cacheResult(focusArea);
                }

                closeSession(session);
            }
        }

        return focusArea;
    }

    public FocusArea findByName(String name)
        throws NoSuchFocusAreaException, SystemException {
        FocusArea focusArea = fetchByName(name);

        if (focusArea == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No FocusArea exists with the key {");

            msg.append("name=" + name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchFocusAreaException(msg.toString());
        }

        return focusArea;
    }

    public FocusArea fetchByName(String name) throws SystemException {
        return fetchByName(name, true);
    }

    public FocusArea fetchByName(String name, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.FocusArea WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                List<FocusArea> list = q.list();

                result = list;

                FocusArea focusArea = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, list);
                } else {
                    focusArea = list.get(0);

                    cacheResult(focusArea);

                    if ((focusArea.getName() == null) ||
                            !focusArea.getName().equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                            finderArgs, focusArea);
                    }
                }

                return focusArea;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, new ArrayList<FocusArea>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (FocusArea) result;
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

    public List<FocusArea> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<FocusArea> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<FocusArea> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<FocusArea> list = (List<FocusArea>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.ontology.model.FocusArea ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<FocusArea>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<FocusArea>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<FocusArea>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByName(String name)
        throws NoSuchFocusAreaException, SystemException {
        FocusArea focusArea = findByName(name);

        remove(focusArea);
    }

    public void removeAll() throws SystemException {
        for (FocusArea focusArea : findAll()) {
            remove(focusArea);
        }
    }

    public int countByName(String name) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.ontology.model.FocusArea WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.ontology.model.FocusArea");

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
                        "value.object.listener.com.ext.portlet.ontology.model.FocusArea")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FocusArea>> listenersList = new ArrayList<ModelListener<FocusArea>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FocusArea>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
