package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.NoSuchOntologyTermEntityException;
import com.ext.portlet.ontology.model.OntologyTermEntity;
import com.ext.portlet.ontology.model.impl.OntologyTermEntityImpl;
import com.ext.portlet.ontology.model.impl.OntologyTermEntityModelImpl;

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


public class OntologyTermEntityPersistenceImpl extends BasePersistenceImpl
    implements OntologyTermEntityPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = OntologyTermEntityImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_CLASSNAMEID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByClassNameId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CLASSNAMEID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByClassNameId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByClassNameId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPK = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByClassNameIdClassPk",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPK = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByClassNameIdClassPk",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByClassNameIdClassPk",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_TERMID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByTermId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_TERMID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByTermId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_TERMID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByTermId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(OntologyTermEntityPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence ontologyTermEntityPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.CategoryPersistence categoryPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence categoryOntologyTermPersistence;

    public void cacheResult(OntologyTermEntity ontologyTermEntity) {
        EntityCacheUtil.putResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityImpl.class, ontologyTermEntity.getPrimaryKey(),
            ontologyTermEntity);
    }

    public void cacheResult(List<OntologyTermEntity> ontologyTermEntities) {
        for (OntologyTermEntity ontologyTermEntity : ontologyTermEntities) {
            if (EntityCacheUtil.getResult(
                        OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
                        OntologyTermEntityImpl.class,
                        ontologyTermEntity.getPrimaryKey(), this) == null) {
                cacheResult(ontologyTermEntity);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(OntologyTermEntityImpl.class.getName());
        EntityCacheUtil.clearCache(OntologyTermEntityImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public OntologyTermEntity create(Long id) {
        OntologyTermEntity ontologyTermEntity = new OntologyTermEntityImpl();

        ontologyTermEntity.setNew(true);
        ontologyTermEntity.setPrimaryKey(id);

        return ontologyTermEntity;
    }

    public OntologyTermEntity remove(Long id)
        throws NoSuchOntologyTermEntityException, SystemException {
        Session session = null;

        try {
            session = openSession();

            OntologyTermEntity ontologyTermEntity = (OntologyTermEntity) session.get(OntologyTermEntityImpl.class,
                    id);

            if (ontologyTermEntity == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No OntologyTermEntity exists with the primary key " +
                        id);
                }

                throw new NoSuchOntologyTermEntityException(
                    "No OntologyTermEntity exists with the primary key " + id);
            }

            return remove(ontologyTermEntity);
        } catch (NoSuchOntologyTermEntityException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public OntologyTermEntity remove(OntologyTermEntity ontologyTermEntity)
        throws SystemException {
        for (ModelListener<OntologyTermEntity> listener : listeners) {
            listener.onBeforeRemove(ontologyTermEntity);
        }

        ontologyTermEntity = removeImpl(ontologyTermEntity);

        for (ModelListener<OntologyTermEntity> listener : listeners) {
            listener.onAfterRemove(ontologyTermEntity);
        }

        return ontologyTermEntity;
    }

    protected OntologyTermEntity removeImpl(
        OntologyTermEntity ontologyTermEntity) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (ontologyTermEntity.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(OntologyTermEntityImpl.class,
                        ontologyTermEntity.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(ontologyTermEntity);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityImpl.class, ontologyTermEntity.getPrimaryKey());

        return ontologyTermEntity;
    }

    /**
     * @deprecated Use <code>update(OntologyTermEntity ontologyTermEntity, boolean merge)</code>.
     */
    public OntologyTermEntity update(OntologyTermEntity ontologyTermEntity)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(OntologyTermEntity ontologyTermEntity) method. Use update(OntologyTermEntity ontologyTermEntity, boolean merge) instead.");
        }

        return update(ontologyTermEntity, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                ontologyTermEntity the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when ontologyTermEntity is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public OntologyTermEntity update(OntologyTermEntity ontologyTermEntity,
        boolean merge) throws SystemException {
        boolean isNew = ontologyTermEntity.isNew();

        for (ModelListener<OntologyTermEntity> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(ontologyTermEntity);
            } else {
                listener.onBeforeUpdate(ontologyTermEntity);
            }
        }

        ontologyTermEntity = updateImpl(ontologyTermEntity, merge);

        for (ModelListener<OntologyTermEntity> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(ontologyTermEntity);
            } else {
                listener.onAfterUpdate(ontologyTermEntity);
            }
        }

        return ontologyTermEntity;
    }

    public OntologyTermEntity updateImpl(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, ontologyTermEntity, merge);

            ontologyTermEntity.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityImpl.class, ontologyTermEntity.getPrimaryKey(),
            ontologyTermEntity);

        return ontologyTermEntity;
    }

    public OntologyTermEntity findByPrimaryKey(Long id)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByPrimaryKey(id);

        if (ontologyTermEntity == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No OntologyTermEntity exists with the primary key " +
                    id);
            }

            throw new NoSuchOntologyTermEntityException(
                "No OntologyTermEntity exists with the primary key " + id);
        }

        return ontologyTermEntity;
    }

    public OntologyTermEntity fetchByPrimaryKey(Long id)
        throws SystemException {
        OntologyTermEntity ontologyTermEntity = (OntologyTermEntity) EntityCacheUtil.getResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
                OntologyTermEntityImpl.class, id, this);

        if (ontologyTermEntity == null) {
            Session session = null;

            try {
                session = openSession();

                ontologyTermEntity = (OntologyTermEntity) session.get(OntologyTermEntityImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (ontologyTermEntity != null) {
                    cacheResult(ontologyTermEntity);
                }

                closeSession(session);
            }
        }

        return ontologyTermEntity;
    }

    public List<OntologyTermEntity> findByClassNameId(Long classNameId)
        throws SystemException {
        Object[] finderArgs = new Object[] { classNameId };

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CLASSNAMEID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTermEntity>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CLASSNAMEID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<OntologyTermEntity> findByClassNameId(Long classNameId,
        int start, int end) throws SystemException {
        return findByClassNameId(classNameId, start, end, null);
    }

    public List<OntologyTermEntity> findByClassNameId(Long classNameId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                classNameId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
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

                list = (List<OntologyTermEntity>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTermEntity>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public OntologyTermEntity findByClassNameId_First(Long classNameId,
        OrderByComparator obc)
        throws NoSuchOntologyTermEntityException, SystemException {
        List<OntologyTermEntity> list = findByClassNameId(classNameId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTermEntity exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermEntityException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTermEntity findByClassNameId_Last(Long classNameId,
        OrderByComparator obc)
        throws NoSuchOntologyTermEntityException, SystemException {
        int count = countByClassNameId(classNameId);

        List<OntologyTermEntity> list = findByClassNameId(classNameId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTermEntity exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermEntityException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTermEntity[] findByClassNameId_PrevAndNext(Long id,
        Long classNameId, OrderByComparator obc)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = findByPrimaryKey(id);

        int count = countByClassNameId(classNameId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

            if (classNameId == null) {
                query.append("classNameId IS NULL");
            } else {
                query.append("classNameId = ?");
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

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    ontologyTermEntity);

            OntologyTermEntity[] array = new OntologyTermEntityImpl[3];

            array[0] = (OntologyTermEntity) objArray[0];
            array[1] = (OntologyTermEntity) objArray[1];
            array[2] = (OntologyTermEntity) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<OntologyTermEntity> findByClassNameIdClassPk(Long classNameId,
        Long classPK) throws SystemException {
        Object[] finderArgs = new Object[] { classNameId, classPK };

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPK,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

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

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTermEntity>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CLASSNAMEIDCLASSPK,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<OntologyTermEntity> findByClassNameIdClassPk(Long classNameId,
        Long classPK, int start, int end) throws SystemException {
        return findByClassNameIdClassPk(classNameId, classPK, start, end, null);
    }

    public List<OntologyTermEntity> findByClassNameIdClassPk(Long classNameId,
        Long classPK, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                classNameId,
                
                classPK,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPK,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

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

                list = (List<OntologyTermEntity>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTermEntity>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CLASSNAMEIDCLASSPK,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public OntologyTermEntity findByClassNameIdClassPk_First(Long classNameId,
        Long classPK, OrderByComparator obc)
        throws NoSuchOntologyTermEntityException, SystemException {
        List<OntologyTermEntity> list = findByClassNameIdClassPk(classNameId,
                classPK, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTermEntity exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(", ");
            msg.append("classPK=" + classPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermEntityException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTermEntity findByClassNameIdClassPk_Last(Long classNameId,
        Long classPK, OrderByComparator obc)
        throws NoSuchOntologyTermEntityException, SystemException {
        int count = countByClassNameIdClassPk(classNameId, classPK);

        List<OntologyTermEntity> list = findByClassNameIdClassPk(classNameId,
                classPK, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTermEntity exists with the key {");

            msg.append("classNameId=" + classNameId);

            msg.append(", ");
            msg.append("classPK=" + classPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermEntityException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTermEntity[] findByClassNameIdClassPk_PrevAndNext(Long id,
        Long classNameId, Long classPK, OrderByComparator obc)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = findByPrimaryKey(id);

        int count = countByClassNameIdClassPk(classNameId, classPK);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

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

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    ontologyTermEntity);

            OntologyTermEntity[] array = new OntologyTermEntityImpl[3];

            array[0] = (OntologyTermEntity) objArray[0];
            array[1] = (OntologyTermEntity) objArray[1];
            array[2] = (OntologyTermEntity) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<OntologyTermEntity> findByTermId(Long termId)
        throws SystemException {
        Object[] finderArgs = new Object[] { termId };

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_TERMID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

                if (termId == null) {
                    query.append("termId IS NULL");
                } else {
                    query.append("termId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (termId != null) {
                    qPos.add(termId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTermEntity>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_TERMID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<OntologyTermEntity> findByTermId(Long termId, int start, int end)
        throws SystemException {
        return findByTermId(termId, start, end, null);
    }

    public List<OntologyTermEntity> findByTermId(Long termId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                termId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_TERMID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

                if (termId == null) {
                    query.append("termId IS NULL");
                } else {
                    query.append("termId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (termId != null) {
                    qPos.add(termId.longValue());
                }

                list = (List<OntologyTermEntity>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTermEntity>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_TERMID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public OntologyTermEntity findByTermId_First(Long termId,
        OrderByComparator obc)
        throws NoSuchOntologyTermEntityException, SystemException {
        List<OntologyTermEntity> list = findByTermId(termId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTermEntity exists with the key {");

            msg.append("termId=" + termId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermEntityException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTermEntity findByTermId_Last(Long termId,
        OrderByComparator obc)
        throws NoSuchOntologyTermEntityException, SystemException {
        int count = countByTermId(termId);

        List<OntologyTermEntity> list = findByTermId(termId, count - 1, count,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No OntologyTermEntity exists with the key {");

            msg.append("termId=" + termId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchOntologyTermEntityException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public OntologyTermEntity[] findByTermId_PrevAndNext(Long id, Long termId,
        OrderByComparator obc)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = findByPrimaryKey(id);

        int count = countByTermId(termId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

            if (termId == null) {
                query.append("termId IS NULL");
            } else {
                query.append("termId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (termId != null) {
                qPos.add(termId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    ontologyTermEntity);

            OntologyTermEntity[] array = new OntologyTermEntityImpl[3];

            array[0] = (OntologyTermEntity) objArray[0];
            array[1] = (OntologyTermEntity) objArray[1];
            array[2] = (OntologyTermEntity) objArray[2];

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

    public List<OntologyTermEntity> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<OntologyTermEntity> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<OntologyTermEntity> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<OntologyTermEntity>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByClassNameId(Long classNameId) throws SystemException {
        for (OntologyTermEntity ontologyTermEntity : findByClassNameId(
                classNameId)) {
            remove(ontologyTermEntity);
        }
    }

    public void removeByClassNameIdClassPk(Long classNameId, Long classPK)
        throws SystemException {
        for (OntologyTermEntity ontologyTermEntity : findByClassNameIdClassPk(
                classNameId, classPK)) {
            remove(ontologyTermEntity);
        }
    }

    public void removeByTermId(Long termId) throws SystemException {
        for (OntologyTermEntity ontologyTermEntity : findByTermId(termId)) {
            remove(ontologyTermEntity);
        }
    }

    public void removeAll() throws SystemException {
        for (OntologyTermEntity ontologyTermEntity : findAll()) {
            remove(ontologyTermEntity);
        }
    }

    public int countByClassNameId(Long classNameId) throws SystemException {
        Object[] finderArgs = new Object[] { classNameId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

                if (classNameId == null) {
                    query.append("classNameId IS NULL");
                } else {
                    query.append("classNameId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByClassNameIdClassPk(Long classNameId, Long classPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { classNameId, classPK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

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

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (classNameId != null) {
                    qPos.add(classNameId.longValue());
                }

                if (classPK != null) {
                    qPos.add(classPK.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByTermId(Long termId) throws SystemException {
        Object[] finderArgs = new Object[] { termId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TERMID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.ontology.model.OntologyTermEntity WHERE ");

                if (termId == null) {
                    query.append("termId IS NULL");
                } else {
                    query.append("termId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (termId != null) {
                    qPos.add(termId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TERMID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.ontology.model.OntologyTermEntity");

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
                        "value.object.listener.com.ext.portlet.ontology.model.OntologyTermEntity")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<OntologyTermEntity>> listenersList = new ArrayList<ModelListener<OntologyTermEntity>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<OntologyTermEntity>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
