package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.NoSuchOntologySpaceException;
import com.ext.portlet.ontology.model.OntologySpace;
import com.ext.portlet.ontology.model.impl.OntologySpaceImpl;
import com.ext.portlet.ontology.model.impl.OntologySpaceModelImpl;

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


public class OntologySpacePersistenceImpl extends BasePersistenceImpl
    implements OntologySpacePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = OntologySpaceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByName",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByName",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(OntologySpacePersistenceImpl.class);
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

    public void cacheResult(OntologySpace ontologySpace) {
        EntityCacheUtil.putResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceImpl.class, ontologySpace.getPrimaryKey(),
            ontologySpace);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { ontologySpace.getName() }, ontologySpace);
    }

    public void cacheResult(List<OntologySpace> ontologySpaces) {
        for (OntologySpace ontologySpace : ontologySpaces) {
            if (EntityCacheUtil.getResult(
                        OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                        OntologySpaceImpl.class, ontologySpace.getPrimaryKey(),
                        this) == null) {
                cacheResult(ontologySpace);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(OntologySpaceImpl.class.getName());
        EntityCacheUtil.clearCache(OntologySpaceImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public OntologySpace create(Long id) {
        OntologySpace ontologySpace = new OntologySpaceImpl();

        ontologySpace.setNew(true);
        ontologySpace.setPrimaryKey(id);

        return ontologySpace;
    }

    public OntologySpace remove(Long id)
        throws NoSuchOntologySpaceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            OntologySpace ontologySpace = (OntologySpace) session.get(OntologySpaceImpl.class,
                    id);

            if (ontologySpace == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No OntologySpace exists with the primary key " +
                        id);
                }

                throw new NoSuchOntologySpaceException(
                    "No OntologySpace exists with the primary key " + id);
            }

            return remove(ontologySpace);
        } catch (NoSuchOntologySpaceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public OntologySpace remove(OntologySpace ontologySpace)
        throws SystemException {
        for (ModelListener<OntologySpace> listener : listeners) {
            listener.onBeforeRemove(ontologySpace);
        }

        ontologySpace = removeImpl(ontologySpace);

        for (ModelListener<OntologySpace> listener : listeners) {
            listener.onAfterRemove(ontologySpace);
        }

        return ontologySpace;
    }

    protected OntologySpace removeImpl(OntologySpace ontologySpace)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (ontologySpace.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(OntologySpaceImpl.class,
                        ontologySpace.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(ontologySpace);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        OntologySpaceModelImpl ontologySpaceModelImpl = (OntologySpaceModelImpl) ontologySpace;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { ontologySpaceModelImpl.getOriginalName() });

        EntityCacheUtil.removeResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceImpl.class, ontologySpace.getPrimaryKey());

        return ontologySpace;
    }

    /**
     * @deprecated Use <code>update(OntologySpace ontologySpace, boolean merge)</code>.
     */
    public OntologySpace update(OntologySpace ontologySpace)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(OntologySpace ontologySpace) method. Use update(OntologySpace ontologySpace, boolean merge) instead.");
        }

        return update(ontologySpace, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                ontologySpace the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when ontologySpace is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public OntologySpace update(OntologySpace ontologySpace, boolean merge)
        throws SystemException {
        boolean isNew = ontologySpace.isNew();

        for (ModelListener<OntologySpace> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(ontologySpace);
            } else {
                listener.onBeforeUpdate(ontologySpace);
            }
        }

        ontologySpace = updateImpl(ontologySpace, merge);

        for (ModelListener<OntologySpace> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(ontologySpace);
            } else {
                listener.onAfterUpdate(ontologySpace);
            }
        }

        return ontologySpace;
    }

    public OntologySpace updateImpl(
        com.ext.portlet.ontology.model.OntologySpace ontologySpace,
        boolean merge) throws SystemException {
        boolean isNew = ontologySpace.isNew();

        OntologySpaceModelImpl ontologySpaceModelImpl = (OntologySpaceModelImpl) ontologySpace;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, ontologySpace, merge);

            ontologySpace.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceImpl.class, ontologySpace.getPrimaryKey(),
            ontologySpace);

        if (!isNew &&
                (!Validator.equals(ontologySpace.getName(),
                    ontologySpaceModelImpl.getOriginalName()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
                new Object[] { ontologySpaceModelImpl.getOriginalName() });
        }

        if (isNew ||
                (!Validator.equals(ontologySpace.getName(),
                    ontologySpaceModelImpl.getOriginalName()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                new Object[] { ontologySpace.getName() }, ontologySpace);
        }

        return ontologySpace;
    }

    public OntologySpace findByPrimaryKey(Long id)
        throws NoSuchOntologySpaceException, SystemException {
        OntologySpace ontologySpace = fetchByPrimaryKey(id);

        if (ontologySpace == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No OntologySpace exists with the primary key " + id);
            }

            throw new NoSuchOntologySpaceException(
                "No OntologySpace exists with the primary key " + id);
        }

        return ontologySpace;
    }

    public OntologySpace fetchByPrimaryKey(Long id) throws SystemException {
        OntologySpace ontologySpace = (OntologySpace) EntityCacheUtil.getResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                OntologySpaceImpl.class, id, this);

        if (ontologySpace == null) {
            Session session = null;

            try {
                session = openSession();

                ontologySpace = (OntologySpace) session.get(OntologySpaceImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (ontologySpace != null) {
                    cacheResult(ontologySpace);
                }

                closeSession(session);
            }
        }

        return ontologySpace;
    }

    public OntologySpace findByName(String name)
        throws NoSuchOntologySpaceException, SystemException {
        OntologySpace ontologySpace = fetchByName(name);

        if (ontologySpace == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologySpace exists with the key {");

            msg.append("name=" + name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchOntologySpaceException(msg.toString());
        }

        return ontologySpace;
    }

    public OntologySpace fetchByName(String name) throws SystemException {
        return fetchByName(name, true);
    }

    public OntologySpace fetchByName(String name, boolean retrieveFromCache)
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
                    "FROM com.ext.portlet.ontology.model.OntologySpace WHERE ");

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

                List<OntologySpace> list = q.list();

                result = list;

                OntologySpace ontologySpace = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, list);
                } else {
                    ontologySpace = list.get(0);

                    cacheResult(ontologySpace);

                    if ((ontologySpace.getName() == null) ||
                            !ontologySpace.getName().equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                            finderArgs, ontologySpace);
                    }
                }

                return ontologySpace;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, new ArrayList<OntologySpace>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (OntologySpace) result;
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

    public List<OntologySpace> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<OntologySpace> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<OntologySpace> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<OntologySpace> list = (List<OntologySpace>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologySpace ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<OntologySpace>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<OntologySpace>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologySpace>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByName(String name)
        throws NoSuchOntologySpaceException, SystemException {
        OntologySpace ontologySpace = findByName(name);

        remove(ontologySpace);
    }

    public void removeAll() throws SystemException {
        for (OntologySpace ontologySpace : findAll()) {
            remove(ontologySpace);
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
                    "FROM com.ext.portlet.ontology.model.OntologySpace WHERE ");

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
                        "SELECT COUNT(*) FROM com.ext.portlet.ontology.model.OntologySpace");

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
                        "value.object.listener.com.ext.portlet.ontology.model.OntologySpace")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<OntologySpace>> listenersList = new ArrayList<ModelListener<OntologySpace>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<OntologySpace>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
