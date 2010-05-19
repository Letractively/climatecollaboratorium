package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException;
import com.ext.portlet.debaterevision.model.DebateItemReference;
import com.ext.portlet.debaterevision.model.impl.DebateItemReferenceImpl;
import com.ext.portlet.debaterevision.model.impl.DebateItemReferenceModelImpl;

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


public class DebateItemReferencePersistenceImpl extends BasePersistenceImpl
    implements DebateItemReferencePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateItemReferenceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_DEBATEITEMIDITEMVERSIONSTATUS =
        new FinderPath(DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemReferenceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByDebateItemIdItemVersionStatus",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_DEBATEITEMIDITEMVERSIONSTATUS =
        new FinderPath(DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemReferenceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByDebateItemIdItemVersionStatus",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_DEBATEITEMIDITEMVERSIONSTATUS =
        new FinderPath(DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemReferenceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByDebateItemIdItemVersionStatus",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemReferenceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemReferenceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateItemReferencePersistenceImpl.class);
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

    public void cacheResult(DebateItemReference debateItemReference) {
        EntityCacheUtil.putResult(DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemReferenceImpl.class, debateItemReference.getPrimaryKey(),
            debateItemReference);
    }

    public void cacheResult(List<DebateItemReference> debateItemReferences) {
        for (DebateItemReference debateItemReference : debateItemReferences) {
            if (EntityCacheUtil.getResult(
                        DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
                        DebateItemReferenceImpl.class,
                        debateItemReference.getPrimaryKey(), this) == null) {
                cacheResult(debateItemReference);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateItemReferenceImpl.class.getName());
        EntityCacheUtil.clearCache(DebateItemReferenceImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateItemReference create(Long debateItemReferencePK) {
        DebateItemReference debateItemReference = new DebateItemReferenceImpl();

        debateItemReference.setNew(true);
        debateItemReference.setPrimaryKey(debateItemReferencePK);

        return debateItemReference;
    }

    public DebateItemReference remove(Long debateItemReferencePK)
        throws NoSuchDebateItemReferenceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateItemReference debateItemReference = (DebateItemReference) session.get(DebateItemReferenceImpl.class,
                    debateItemReferencePK);

            if (debateItemReference == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DebateItemReference exists with the primary key " +
                        debateItemReferencePK);
                }

                throw new NoSuchDebateItemReferenceException(
                    "No DebateItemReference exists with the primary key " +
                    debateItemReferencePK);
            }

            return remove(debateItemReference);
        } catch (NoSuchDebateItemReferenceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateItemReference remove(DebateItemReference debateItemReference)
        throws SystemException {
        for (ModelListener<DebateItemReference> listener : listeners) {
            listener.onBeforeRemove(debateItemReference);
        }

        debateItemReference = removeImpl(debateItemReference);

        for (ModelListener<DebateItemReference> listener : listeners) {
            listener.onAfterRemove(debateItemReference);
        }

        return debateItemReference;
    }

    protected DebateItemReference removeImpl(
        DebateItemReference debateItemReference) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateItemReference.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateItemReferenceImpl.class,
                        debateItemReference.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateItemReference);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemReferenceImpl.class, debateItemReference.getPrimaryKey());

        return debateItemReference;
    }

    /**
     * @deprecated Use <code>update(DebateItemReference debateItemReference, boolean merge)</code>.
     */
    public DebateItemReference update(DebateItemReference debateItemReference)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateItemReference debateItemReference) method. Use update(DebateItemReference debateItemReference, boolean merge) instead.");
        }

        return update(debateItemReference, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItemReference the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItemReference is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateItemReference update(DebateItemReference debateItemReference,
        boolean merge) throws SystemException {
        boolean isNew = debateItemReference.isNew();

        for (ModelListener<DebateItemReference> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateItemReference);
            } else {
                listener.onBeforeUpdate(debateItemReference);
            }
        }

        debateItemReference = updateImpl(debateItemReference, merge);

        for (ModelListener<DebateItemReference> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateItemReference);
            } else {
                listener.onAfterUpdate(debateItemReference);
            }
        }

        return debateItemReference;
    }

    public DebateItemReference updateImpl(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateItemReference, merge);

            debateItemReference.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemReferenceImpl.class, debateItemReference.getPrimaryKey(),
            debateItemReference);

        return debateItemReference;
    }

    public DebateItemReference findByPrimaryKey(Long debateItemReferencePK)
        throws NoSuchDebateItemReferenceException, SystemException {
        DebateItemReference debateItemReference = fetchByPrimaryKey(debateItemReferencePK);

        if (debateItemReference == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateItemReference exists with the primary key " +
                    debateItemReferencePK);
            }

            throw new NoSuchDebateItemReferenceException(
                "No DebateItemReference exists with the primary key " +
                debateItemReferencePK);
        }

        return debateItemReference;
    }

    public DebateItemReference fetchByPrimaryKey(Long debateItemReferencePK)
        throws SystemException {
        DebateItemReference debateItemReference = (DebateItemReference) EntityCacheUtil.getResult(DebateItemReferenceModelImpl.ENTITY_CACHE_ENABLED,
                DebateItemReferenceImpl.class, debateItemReferencePK, this);

        if (debateItemReference == null) {
            Session session = null;

            try {
                session = openSession();

                debateItemReference = (DebateItemReference) session.get(DebateItemReferenceImpl.class,
                        debateItemReferencePK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateItemReference != null) {
                    cacheResult(debateItemReference);
                }

                closeSession(session);
            }
        }

        return debateItemReference;
    }

    public List<DebateItemReference> findByDebateItemIdItemVersionStatus(
        Long debateItemId, Long itemVersion, String status)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, itemVersion, status };

        List<DebateItemReference> list = (List<DebateItemReference>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_DEBATEITEMIDITEMVERSIONSTATUS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemReference WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (itemVersion == null) {
                    query.append("itemVersion IS NULL");
                } else {
                    query.append("itemVersion = ?");
                }

                query.append(" AND ");

                if (status == null) {
                    query.append("status IS NULL");
                } else {
                    query.append("status = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (itemVersion != null) {
                    qPos.add(itemVersion.longValue());
                }

                if (status != null) {
                    qPos.add(status);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemReference>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_DEBATEITEMIDITEMVERSIONSTATUS,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DebateItemReference> findByDebateItemIdItemVersionStatus(
        Long debateItemId, Long itemVersion, String status, int start, int end)
        throws SystemException {
        return findByDebateItemIdItemVersionStatus(debateItemId, itemVersion,
            status, start, end, null);
    }

    public List<DebateItemReference> findByDebateItemIdItemVersionStatus(
        Long debateItemId, Long itemVersion, String status, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                debateItemId,
                
                itemVersion,
                
                status,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItemReference> list = (List<DebateItemReference>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_DEBATEITEMIDITEMVERSIONSTATUS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemReference WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (itemVersion == null) {
                    query.append("itemVersion IS NULL");
                } else {
                    query.append("itemVersion = ?");
                }

                query.append(" AND ");

                if (status == null) {
                    query.append("status IS NULL");
                } else {
                    query.append("status = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (itemVersion != null) {
                    qPos.add(itemVersion.longValue());
                }

                if (status != null) {
                    qPos.add(status);
                }

                list = (List<DebateItemReference>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemReference>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_DEBATEITEMIDITEMVERSIONSTATUS,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DebateItemReference findByDebateItemIdItemVersionStatus_First(
        Long debateItemId, Long itemVersion, String status,
        OrderByComparator obc)
        throws NoSuchDebateItemReferenceException, SystemException {
        List<DebateItemReference> list = findByDebateItemIdItemVersionStatus(debateItemId,
                itemVersion, status, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItemReference exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("itemVersion=" + itemVersion);

            msg.append(", ");
            msg.append("status=" + status);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItemReference findByDebateItemIdItemVersionStatus_Last(
        Long debateItemId, Long itemVersion, String status,
        OrderByComparator obc)
        throws NoSuchDebateItemReferenceException, SystemException {
        int count = countByDebateItemIdItemVersionStatus(debateItemId,
                itemVersion, status);

        List<DebateItemReference> list = findByDebateItemIdItemVersionStatus(debateItemId,
                itemVersion, status, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItemReference exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("itemVersion=" + itemVersion);

            msg.append(", ");
            msg.append("status=" + status);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItemReference[] findByDebateItemIdItemVersionStatus_PrevAndNext(
        Long debateItemReferencePK, Long debateItemId, Long itemVersion,
        String status, OrderByComparator obc)
        throws NoSuchDebateItemReferenceException, SystemException {
        DebateItemReference debateItemReference = findByPrimaryKey(debateItemReferencePK);

        int count = countByDebateItemIdItemVersionStatus(debateItemId,
                itemVersion, status);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.DebateItemReference WHERE ");

            if (debateItemId == null) {
                query.append("debateItemId IS NULL");
            } else {
                query.append("debateItemId = ?");
            }

            query.append(" AND ");

            if (itemVersion == null) {
                query.append("itemVersion IS NULL");
            } else {
                query.append("itemVersion = ?");
            }

            query.append(" AND ");

            if (status == null) {
                query.append("status IS NULL");
            } else {
                query.append("status = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (debateItemId != null) {
                qPos.add(debateItemId.longValue());
            }

            if (itemVersion != null) {
                qPos.add(itemVersion.longValue());
            }

            if (status != null) {
                qPos.add(status);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    debateItemReference);

            DebateItemReference[] array = new DebateItemReferenceImpl[3];

            array[0] = (DebateItemReference) objArray[0];
            array[1] = (DebateItemReference) objArray[1];
            array[2] = (DebateItemReference) objArray[2];

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

    public List<DebateItemReference> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateItemReference> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateItemReference> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItemReference> list = (List<DebateItemReference>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemReference ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateItemReference>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateItemReference>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemReference>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByDebateItemIdItemVersionStatus(Long debateItemId,
        Long itemVersion, String status) throws SystemException {
        for (DebateItemReference debateItemReference : findByDebateItemIdItemVersionStatus(
                debateItemId, itemVersion, status)) {
            remove(debateItemReference);
        }
    }

    public void removeAll() throws SystemException {
        for (DebateItemReference debateItemReference : findAll()) {
            remove(debateItemReference);
        }
    }

    public int countByDebateItemIdItemVersionStatus(Long debateItemId,
        Long itemVersion, String status) throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, itemVersion, status };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEBATEITEMIDITEMVERSIONSTATUS,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemReference WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (itemVersion == null) {
                    query.append("itemVersion IS NULL");
                } else {
                    query.append("itemVersion = ?");
                }

                query.append(" AND ");

                if (status == null) {
                    query.append("status IS NULL");
                } else {
                    query.append("status = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (itemVersion != null) {
                    qPos.add(itemVersion.longValue());
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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEBATEITEMIDITEMVERSIONSTATUS,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debaterevision.model.DebateItemReference");

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
                        "value.object.listener.com.ext.portlet.debaterevision.model.DebateItemReference")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateItemReference>> listenersList = new ArrayList<ModelListener<DebateItemReference>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateItemReference>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
