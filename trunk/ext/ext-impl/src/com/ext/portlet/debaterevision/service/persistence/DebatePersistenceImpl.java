package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.NoSuchDebateException;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.impl.DebateImpl;
import com.ext.portlet.debaterevision.model.impl.DebateModelImpl;

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


public class DebatePersistenceImpl extends BasePersistenceImpl
    implements DebatePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_BYIDSTATUS = new FinderPath(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBybyIdStatus",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_BYIDSTATUS = new FinderPath(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBybyIdStatus",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_BYIDSTATUS = new FinderPath(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countBybyIdStatus",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_BYIDVERSION = new FinderPath(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBybyIdVersion",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_BYIDVERSION = new FinderPath(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBybyIdVersion",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_BYIDVERSION = new FinderPath(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countBybyIdVersion",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebatePersistenceImpl.class);
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

    public void cacheResult(Debate debate) {
        EntityCacheUtil.putResult(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateImpl.class, debate.getPrimaryKey(), debate);
    }

    public void cacheResult(List<Debate> debates) {
        for (Debate debate : debates) {
            if (EntityCacheUtil.getResult(
                        DebateModelImpl.ENTITY_CACHE_ENABLED, DebateImpl.class,
                        debate.getPrimaryKey(), this) == null) {
                cacheResult(debate);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateImpl.class.getName());
        EntityCacheUtil.clearCache(DebateImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public Debate create(Long debatePK) {
        Debate debate = new DebateImpl();

        debate.setNew(true);
        debate.setPrimaryKey(debatePK);

        return debate;
    }

    public Debate remove(Long debatePK)
        throws NoSuchDebateException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Debate debate = (Debate) session.get(DebateImpl.class, debatePK);

            if (debate == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No Debate exists with the primary key " +
                        debatePK);
                }

                throw new NoSuchDebateException(
                    "No Debate exists with the primary key " + debatePK);
            }

            return remove(debate);
        } catch (NoSuchDebateException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public Debate remove(Debate debate) throws SystemException {
        for (ModelListener<Debate> listener : listeners) {
            listener.onBeforeRemove(debate);
        }

        debate = removeImpl(debate);

        for (ModelListener<Debate> listener : listeners) {
            listener.onAfterRemove(debate);
        }

        return debate;
    }

    protected Debate removeImpl(Debate debate) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debate.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateImpl.class,
                        debate.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debate);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateImpl.class, debate.getPrimaryKey());

        return debate;
    }

    /**
     * @deprecated Use <code>update(Debate debate, boolean merge)</code>.
     */
    public Debate update(Debate debate) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(Debate debate) method. Use update(Debate debate, boolean merge) instead.");
        }

        return update(debate, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public Debate update(Debate debate, boolean merge)
        throws SystemException {
        boolean isNew = debate.isNew();

        for (ModelListener<Debate> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debate);
            } else {
                listener.onBeforeUpdate(debate);
            }
        }

        debate = updateImpl(debate, merge);

        for (ModelListener<Debate> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debate);
            } else {
                listener.onAfterUpdate(debate);
            }
        }

        return debate;
    }

    public Debate updateImpl(
        com.ext.portlet.debaterevision.model.Debate debate, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debate, merge);

            debate.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateImpl.class, debate.getPrimaryKey(), debate);

        return debate;
    }

    public Debate findByPrimaryKey(Long debatePK)
        throws NoSuchDebateException, SystemException {
        Debate debate = fetchByPrimaryKey(debatePK);

        if (debate == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No Debate exists with the primary key " + debatePK);
            }

            throw new NoSuchDebateException(
                "No Debate exists with the primary key " + debatePK);
        }

        return debate;
    }

    public Debate fetchByPrimaryKey(Long debatePK) throws SystemException {
        Debate debate = (Debate) EntityCacheUtil.getResult(DebateModelImpl.ENTITY_CACHE_ENABLED,
                DebateImpl.class, debatePK, this);

        if (debate == null) {
            Session session = null;

            try {
                session = openSession();

                debate = (Debate) session.get(DebateImpl.class, debatePK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debate != null) {
                    cacheResult(debate);
                }

                closeSession(session);
            }
        }

        return debate;
    }

    public List<Debate> findBybyIdStatus(Long debateId, String status)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateId, status };

        List<Debate> list = (List<Debate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_BYIDSTATUS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.Debate WHERE ");

                if (debateId == null) {
                    query.append("debateId IS NULL");
                } else {
                    query.append("debateId = ?");
                }

                query.append(" AND ");

                if (status == null) {
                    query.append("status LIKE null");
                } else {
                    query.append("status LIKE ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("debateId ASC, ");
                query.append("treeVersion ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateId != null) {
                    qPos.add(debateId.longValue());
                }

                if (status != null) {
                    qPos.add(status);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Debate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_BYIDSTATUS,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<Debate> findBybyIdStatus(Long debateId, String status,
        int start, int end) throws SystemException {
        return findBybyIdStatus(debateId, status, start, end, null);
    }

    public List<Debate> findBybyIdStatus(Long debateId, String status,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                debateId,
                
                status,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Debate> list = (List<Debate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_BYIDSTATUS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.Debate WHERE ");

                if (debateId == null) {
                    query.append("debateId IS NULL");
                } else {
                    query.append("debateId = ?");
                }

                query.append(" AND ");

                if (status == null) {
                    query.append("status LIKE null");
                } else {
                    query.append("status LIKE ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("debateId ASC, ");
                    query.append("treeVersion ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateId != null) {
                    qPos.add(debateId.longValue());
                }

                if (status != null) {
                    qPos.add(status);
                }

                list = (List<Debate>) QueryUtil.list(q, getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Debate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_BYIDSTATUS,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public Debate findBybyIdStatus_First(Long debateId, String status,
        OrderByComparator obc) throws NoSuchDebateException, SystemException {
        List<Debate> list = findBybyIdStatus(debateId, status, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Debate exists with the key {");

            msg.append("debateId=" + debateId);

            msg.append(", ");
            msg.append("status=" + status);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Debate findBybyIdStatus_Last(Long debateId, String status,
        OrderByComparator obc) throws NoSuchDebateException, SystemException {
        int count = countBybyIdStatus(debateId, status);

        List<Debate> list = findBybyIdStatus(debateId, status, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Debate exists with the key {");

            msg.append("debateId=" + debateId);

            msg.append(", ");
            msg.append("status=" + status);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Debate[] findBybyIdStatus_PrevAndNext(Long debatePK, Long debateId,
        String status, OrderByComparator obc)
        throws NoSuchDebateException, SystemException {
        Debate debate = findByPrimaryKey(debatePK);

        int count = countBybyIdStatus(debateId, status);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.Debate WHERE ");

            if (debateId == null) {
                query.append("debateId IS NULL");
            } else {
                query.append("debateId = ?");
            }

            query.append(" AND ");

            if (status == null) {
                query.append("status LIKE null");
            } else {
                query.append("status LIKE ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("debateId ASC, ");
                query.append("treeVersion ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (debateId != null) {
                qPos.add(debateId.longValue());
            }

            if (status != null) {
                qPos.add(status);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, debate);

            Debate[] array = new DebateImpl[3];

            array[0] = (Debate) objArray[0];
            array[1] = (Debate) objArray[1];
            array[2] = (Debate) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<Debate> findBybyIdVersion(Long debateId, Long treeVersion)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateId, treeVersion };

        List<Debate> list = (List<Debate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_BYIDVERSION,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.Debate WHERE ");

                if (debateId == null) {
                    query.append("debateId IS NULL");
                } else {
                    query.append("debateId = ?");
                }

                query.append(" AND ");

                if (treeVersion == null) {
                    query.append("treeVersion IS NULL");
                } else {
                    query.append("treeVersion = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("debateId ASC, ");
                query.append("treeVersion ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateId != null) {
                    qPos.add(debateId.longValue());
                }

                if (treeVersion != null) {
                    qPos.add(treeVersion.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Debate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_BYIDVERSION,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<Debate> findBybyIdVersion(Long debateId, Long treeVersion,
        int start, int end) throws SystemException {
        return findBybyIdVersion(debateId, treeVersion, start, end, null);
    }

    public List<Debate> findBybyIdVersion(Long debateId, Long treeVersion,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                debateId,
                
                treeVersion,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Debate> list = (List<Debate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_BYIDVERSION,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.Debate WHERE ");

                if (debateId == null) {
                    query.append("debateId IS NULL");
                } else {
                    query.append("debateId = ?");
                }

                query.append(" AND ");

                if (treeVersion == null) {
                    query.append("treeVersion IS NULL");
                } else {
                    query.append("treeVersion = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("debateId ASC, ");
                    query.append("treeVersion ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateId != null) {
                    qPos.add(debateId.longValue());
                }

                if (treeVersion != null) {
                    qPos.add(treeVersion.longValue());
                }

                list = (List<Debate>) QueryUtil.list(q, getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Debate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_BYIDVERSION,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public Debate findBybyIdVersion_First(Long debateId, Long treeVersion,
        OrderByComparator obc) throws NoSuchDebateException, SystemException {
        List<Debate> list = findBybyIdVersion(debateId, treeVersion, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Debate exists with the key {");

            msg.append("debateId=" + debateId);

            msg.append(", ");
            msg.append("treeVersion=" + treeVersion);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Debate findBybyIdVersion_Last(Long debateId, Long treeVersion,
        OrderByComparator obc) throws NoSuchDebateException, SystemException {
        int count = countBybyIdVersion(debateId, treeVersion);

        List<Debate> list = findBybyIdVersion(debateId, treeVersion, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Debate exists with the key {");

            msg.append("debateId=" + debateId);

            msg.append(", ");
            msg.append("treeVersion=" + treeVersion);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Debate[] findBybyIdVersion_PrevAndNext(Long debatePK, Long debateId,
        Long treeVersion, OrderByComparator obc)
        throws NoSuchDebateException, SystemException {
        Debate debate = findByPrimaryKey(debatePK);

        int count = countBybyIdVersion(debateId, treeVersion);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.Debate WHERE ");

            if (debateId == null) {
                query.append("debateId IS NULL");
            } else {
                query.append("debateId = ?");
            }

            query.append(" AND ");

            if (treeVersion == null) {
                query.append("treeVersion IS NULL");
            } else {
                query.append("treeVersion = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("debateId ASC, ");
                query.append("treeVersion ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (debateId != null) {
                qPos.add(debateId.longValue());
            }

            if (treeVersion != null) {
                qPos.add(treeVersion.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, debate);

            Debate[] array = new DebateImpl[3];

            array[0] = (Debate) objArray[0];
            array[1] = (Debate) objArray[1];
            array[2] = (Debate) objArray[2];

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

    public List<Debate> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<Debate> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<Debate> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Debate> list = (List<Debate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.Debate ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("debateId ASC, ");
                    query.append("treeVersion ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<Debate>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Debate>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Debate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeBybyIdStatus(Long debateId, String status)
        throws SystemException {
        for (Debate debate : findBybyIdStatus(debateId, status)) {
            remove(debate);
        }
    }

    public void removeBybyIdVersion(Long debateId, Long treeVersion)
        throws SystemException {
        for (Debate debate : findBybyIdVersion(debateId, treeVersion)) {
            remove(debate);
        }
    }

    public void removeAll() throws SystemException {
        for (Debate debate : findAll()) {
            remove(debate);
        }
    }

    public int countBybyIdStatus(Long debateId, String status)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateId, status };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_BYIDSTATUS,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.Debate WHERE ");

                if (debateId == null) {
                    query.append("debateId IS NULL");
                } else {
                    query.append("debateId = ?");
                }

                query.append(" AND ");

                if (status == null) {
                    query.append("status LIKE null");
                } else {
                    query.append("status LIKE ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateId != null) {
                    qPos.add(debateId.longValue());
                }

                if (status != null) {
                    qPos.add(status);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BYIDSTATUS,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countBybyIdVersion(Long debateId, Long treeVersion)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateId, treeVersion };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_BYIDVERSION,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.Debate WHERE ");

                if (debateId == null) {
                    query.append("debateId IS NULL");
                } else {
                    query.append("debateId = ?");
                }

                query.append(" AND ");

                if (treeVersion == null) {
                    query.append("treeVersion IS NULL");
                } else {
                    query.append("treeVersion = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateId != null) {
                    qPos.add(debateId.longValue());
                }

                if (treeVersion != null) {
                    qPos.add(treeVersion.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BYIDVERSION,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debaterevision.model.Debate");

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
                        "value.object.listener.com.ext.portlet.debaterevision.model.Debate")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Debate>> listenersList = new ArrayList<ModelListener<Debate>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Debate>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
