package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.NoSuchOntologyTermException;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.model.impl.OntologyTermImpl;
import com.ext.portlet.ontology.model.impl.OntologyTermModelImpl;

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


public class OntologyTermPersistenceImpl extends BasePersistenceImpl
    implements OntologyTermPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = OntologyTermImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_PARENTID = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByParentId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_PARENTID = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByParentId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_PARENTID = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByParentId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_NAME = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByName", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_NAME = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByName",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByName", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(OntologyTermPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence ontologyTermEntityPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.CategoryPersistence categoryPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence categoryOntologyTermPersistence;

    public void cacheResult(OntologyTerm ontologyTerm) {
        EntityCacheUtil.putResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermImpl.class, ontologyTerm.getPrimaryKey(), ontologyTerm);
    }

    public void cacheResult(List<OntologyTerm> ontologyTerms) {
        for (OntologyTerm ontologyTerm : ontologyTerms) {
            if (EntityCacheUtil.getResult(
                        OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                        OntologyTermImpl.class, ontologyTerm.getPrimaryKey(),
                        this) == null) {
                cacheResult(ontologyTerm);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(OntologyTermImpl.class.getName());
        EntityCacheUtil.clearCache(OntologyTermImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public OntologyTerm create(Long id) {
        OntologyTerm ontologyTerm = new OntologyTermImpl();

        ontologyTerm.setNew(true);
        ontologyTerm.setPrimaryKey(id);

        return ontologyTerm;
    }

    public OntologyTerm remove(Long id)
        throws NoSuchOntologyTermException, SystemException {
        Session session = null;

        try {
            session = openSession();

            OntologyTerm ontologyTerm = (OntologyTerm) session.get(OntologyTermImpl.class,
                    id);

            if (ontologyTerm == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No OntologyTerm exists with the primary key " +
                        id);
                }

                throw new NoSuchOntologyTermException(
                    "No OntologyTerm exists with the primary key " + id);
            }

            return remove(ontologyTerm);
        } catch (NoSuchOntologyTermException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public OntologyTerm remove(OntologyTerm ontologyTerm)
        throws SystemException {
        for (ModelListener<OntologyTerm> listener : listeners) {
            listener.onBeforeRemove(ontologyTerm);
        }

        ontologyTerm = removeImpl(ontologyTerm);

        for (ModelListener<OntologyTerm> listener : listeners) {
            listener.onAfterRemove(ontologyTerm);
        }

        return ontologyTerm;
    }

    protected OntologyTerm removeImpl(OntologyTerm ontologyTerm)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (ontologyTerm.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(OntologyTermImpl.class,
                        ontologyTerm.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(ontologyTerm);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermImpl.class, ontologyTerm.getPrimaryKey());

        return ontologyTerm;
    }

    /**
     * @deprecated Use <code>update(OntologyTerm ontologyTerm, boolean merge)</code>.
     */
    public OntologyTerm update(OntologyTerm ontologyTerm)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(OntologyTerm ontologyTerm) method. Use update(OntologyTerm ontologyTerm, boolean merge) instead.");
        }

        return update(ontologyTerm, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                ontologyTerm the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when ontologyTerm is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public OntologyTerm update(OntologyTerm ontologyTerm, boolean merge)
        throws SystemException {
        boolean isNew = ontologyTerm.isNew();

        for (ModelListener<OntologyTerm> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(ontologyTerm);
            } else {
                listener.onBeforeUpdate(ontologyTerm);
            }
        }

        ontologyTerm = updateImpl(ontologyTerm, merge);

        for (ModelListener<OntologyTerm> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(ontologyTerm);
            } else {
                listener.onAfterUpdate(ontologyTerm);
            }
        }

        return ontologyTerm;
    }

    public OntologyTerm updateImpl(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, ontologyTerm, merge);

            ontologyTerm.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermImpl.class, ontologyTerm.getPrimaryKey(), ontologyTerm);

        return ontologyTerm;
    }

    public OntologyTerm findByPrimaryKey(Long id)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchByPrimaryKey(id);

        if (ontologyTerm == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No OntologyTerm exists with the primary key " + id);
            }

            throw new NoSuchOntologyTermException(
                "No OntologyTerm exists with the primary key " + id);
        }

        return ontologyTerm;
    }

    public OntologyTerm fetchByPrimaryKey(Long id) throws SystemException {
        OntologyTerm ontologyTerm = (OntologyTerm) EntityCacheUtil.getResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                OntologyTermImpl.class, id, this);

        if (ontologyTerm == null) {
            Session session = null;

            try {
                session = openSession();

                ontologyTerm = (OntologyTerm) session.get(OntologyTermImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (ontologyTerm != null) {
                    cacheResult(ontologyTerm);
                }

                closeSession(session);
            }
        }

        return ontologyTerm;
    }

    public List<OntologyTerm> findByParentId(Long parentId)
        throws SystemException {
        Object[] finderArgs = new Object[] { parentId };

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PARENTID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTerm WHERE ");

                if (parentId == null) {
                    query.append("parentId IS NULL");
                } else {
                    query.append("parentId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (parentId != null) {
                    qPos.add(parentId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PARENTID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<OntologyTerm> findByParentId(Long parentId, int start, int end)
        throws SystemException {
        return findByParentId(parentId, start, end, null);
    }

    public List<OntologyTerm> findByParentId(Long parentId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                parentId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PARENTID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTerm WHERE ");

                if (parentId == null) {
                    query.append("parentId IS NULL");
                } else {
                    query.append("parentId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (parentId != null) {
                    qPos.add(parentId.longValue());
                }

                list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PARENTID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public OntologyTerm findByParentId_First(Long parentId,
        OrderByComparator obc)
        throws NoSuchOntologyTermException, SystemException {
        List<OntologyTerm> list = findByParentId(parentId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTerm exists with the key {");

            msg.append("parentId=" + parentId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTerm findByParentId_Last(Long parentId, OrderByComparator obc)
        throws NoSuchOntologyTermException, SystemException {
        int count = countByParentId(parentId);

        List<OntologyTerm> list = findByParentId(parentId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTerm exists with the key {");

            msg.append("parentId=" + parentId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTerm[] findByParentId_PrevAndNext(Long id, Long parentId,
        OrderByComparator obc)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = findByPrimaryKey(id);

        int count = countByParentId(parentId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.ontology.model.OntologyTerm WHERE ");

            if (parentId == null) {
                query.append("parentId IS NULL");
            } else {
                query.append("parentId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (parentId != null) {
                qPos.add(parentId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    ontologyTerm);

            OntologyTerm[] array = new OntologyTermImpl[3];

            array[0] = (OntologyTerm) objArray[0];
            array[1] = (OntologyTerm) objArray[1];
            array[2] = (OntologyTerm) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<OntologyTerm> findByName(String name) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_NAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTerm WHERE ");

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

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_NAME, finderArgs,
                    list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<OntologyTerm> findByName(String name, int start, int end)
        throws SystemException {
        return findByName(name, start, end, null);
    }

    public List<OntologyTerm> findByName(String name, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                name,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_NAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTerm WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_NAME,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public OntologyTerm findByName_First(String name, OrderByComparator obc)
        throws NoSuchOntologyTermException, SystemException {
        List<OntologyTerm> list = findByName(name, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTerm exists with the key {");

            msg.append("name=" + name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTerm findByName_Last(String name, OrderByComparator obc)
        throws NoSuchOntologyTermException, SystemException {
        int count = countByName(name);

        List<OntologyTerm> list = findByName(name, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTerm exists with the key {");

            msg.append("name=" + name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTerm[] findByName_PrevAndNext(Long id, String name,
        OrderByComparator obc)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = findByPrimaryKey(id);

        int count = countByName(name);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.ontology.model.OntologyTerm WHERE ");

            if (name == null) {
                query.append("name IS NULL");
            } else {
                query.append("name = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (name != null) {
                qPos.add(name);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    ontologyTerm);

            OntologyTerm[] array = new OntologyTermImpl[3];

            array[0] = (OntologyTerm) objArray[0];
            array[1] = (OntologyTerm) objArray[1];
            array[2] = (OntologyTerm) objArray[2];

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

    public List<OntologyTerm> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<OntologyTerm> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<OntologyTerm> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTerm ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByParentId(Long parentId) throws SystemException {
        for (OntologyTerm ontologyTerm : findByParentId(parentId)) {
            remove(ontologyTerm);
        }
    }

    public void removeByName(String name) throws SystemException {
        for (OntologyTerm ontologyTerm : findByName(name)) {
            remove(ontologyTerm);
        }
    }

    public void removeAll() throws SystemException {
        for (OntologyTerm ontologyTerm : findAll()) {
            remove(ontologyTerm);
        }
    }

    public int countByParentId(Long parentId) throws SystemException {
        Object[] finderArgs = new Object[] { parentId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARENTID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTerm WHERE ");

                if (parentId == null) {
                    query.append("parentId IS NULL");
                } else {
                    query.append("parentId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (parentId != null) {
                    qPos.add(parentId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARENTID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
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
                    "FROM com.ext.portlet.ontology.model.OntologyTerm WHERE ");

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
                        "SELECT COUNT(*) FROM com.ext.portlet.ontology.model.OntologyTerm");

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
                        "value.object.listener.com.ext.portlet.ontology.model.OntologyTerm")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<OntologyTerm>> listenersList = new ArrayList<ModelListener<OntologyTerm>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<OntologyTerm>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
