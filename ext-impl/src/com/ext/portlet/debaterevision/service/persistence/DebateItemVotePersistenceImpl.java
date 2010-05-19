package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.NoSuchDebateItemVoteException;
import com.ext.portlet.debaterevision.model.DebateItemVote;
import com.ext.portlet.debaterevision.model.impl.DebateItemVoteImpl;
import com.ext.portlet.debaterevision.model.impl.DebateItemVoteModelImpl;

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


public class DebateItemVotePersistenceImpl extends BasePersistenceImpl
    implements DebateItemVotePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateItemVoteImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_DEBATEITEMID = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByDebateItemId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_DEBATEITEMID = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByDebateItemId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_DEBATEITEMID = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByDebateItemId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_DEBATEITEMIDUSERID = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByDebateItemIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_DEBATEITEMIDUSERID = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByDebateItemIdUserId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_DEBATEITEMIDUSERID = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByDebateItemIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateItemVotePersistenceImpl.class);
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

    public void cacheResult(DebateItemVote debateItemVote) {
        EntityCacheUtil.putResult(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteImpl.class, debateItemVote.getPrimaryKey(),
            debateItemVote);
    }

    public void cacheResult(List<DebateItemVote> debateItemVotes) {
        for (DebateItemVote debateItemVote : debateItemVotes) {
            if (EntityCacheUtil.getResult(
                        DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
                        DebateItemVoteImpl.class,
                        debateItemVote.getPrimaryKey(), this) == null) {
                cacheResult(debateItemVote);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateItemVoteImpl.class.getName());
        EntityCacheUtil.clearCache(DebateItemVoteImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateItemVote create(Long debateItemVoteId) {
        DebateItemVote debateItemVote = new DebateItemVoteImpl();

        debateItemVote.setNew(true);
        debateItemVote.setPrimaryKey(debateItemVoteId);

        return debateItemVote;
    }

    public DebateItemVote remove(Long debateItemVoteId)
        throws NoSuchDebateItemVoteException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateItemVote debateItemVote = (DebateItemVote) session.get(DebateItemVoteImpl.class,
                    debateItemVoteId);

            if (debateItemVote == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No DebateItemVote exists with the primary key " +
                        debateItemVoteId);
                }

                throw new NoSuchDebateItemVoteException(
                    "No DebateItemVote exists with the primary key " +
                    debateItemVoteId);
            }

            return remove(debateItemVote);
        } catch (NoSuchDebateItemVoteException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateItemVote remove(DebateItemVote debateItemVote)
        throws SystemException {
        for (ModelListener<DebateItemVote> listener : listeners) {
            listener.onBeforeRemove(debateItemVote);
        }

        debateItemVote = removeImpl(debateItemVote);

        for (ModelListener<DebateItemVote> listener : listeners) {
            listener.onAfterRemove(debateItemVote);
        }

        return debateItemVote;
    }

    protected DebateItemVote removeImpl(DebateItemVote debateItemVote)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateItemVote.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateItemVoteImpl.class,
                        debateItemVote.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateItemVote);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteImpl.class, debateItemVote.getPrimaryKey());

        return debateItemVote;
    }

    /**
     * @deprecated Use <code>update(DebateItemVote debateItemVote, boolean merge)</code>.
     */
    public DebateItemVote update(DebateItemVote debateItemVote)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateItemVote debateItemVote) method. Use update(DebateItemVote debateItemVote, boolean merge) instead.");
        }

        return update(debateItemVote, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItemVote the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItemVote is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateItemVote update(DebateItemVote debateItemVote, boolean merge)
        throws SystemException {
        boolean isNew = debateItemVote.isNew();

        for (ModelListener<DebateItemVote> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateItemVote);
            } else {
                listener.onBeforeUpdate(debateItemVote);
            }
        }

        debateItemVote = updateImpl(debateItemVote, merge);

        for (ModelListener<DebateItemVote> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateItemVote);
            } else {
                listener.onAfterUpdate(debateItemVote);
            }
        }

        return debateItemVote;
    }

    public DebateItemVote updateImpl(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateItemVote, merge);

            debateItemVote.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteImpl.class, debateItemVote.getPrimaryKey(),
            debateItemVote);

        return debateItemVote;
    }

    public DebateItemVote findByPrimaryKey(Long debateItemVoteId)
        throws NoSuchDebateItemVoteException, SystemException {
        DebateItemVote debateItemVote = fetchByPrimaryKey(debateItemVoteId);

        if (debateItemVote == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateItemVote exists with the primary key " +
                    debateItemVoteId);
            }

            throw new NoSuchDebateItemVoteException(
                "No DebateItemVote exists with the primary key " +
                debateItemVoteId);
        }

        return debateItemVote;
    }

    public DebateItemVote fetchByPrimaryKey(Long debateItemVoteId)
        throws SystemException {
        DebateItemVote debateItemVote = (DebateItemVote) EntityCacheUtil.getResult(DebateItemVoteModelImpl.ENTITY_CACHE_ENABLED,
                DebateItemVoteImpl.class, debateItemVoteId, this);

        if (debateItemVote == null) {
            Session session = null;

            try {
                session = openSession();

                debateItemVote = (DebateItemVote) session.get(DebateItemVoteImpl.class,
                        debateItemVoteId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateItemVote != null) {
                    cacheResult(debateItemVote);
                }

                closeSession(session);
            }
        }

        return debateItemVote;
    }

    public List<DebateItemVote> findByDebateItemId(Long debateItemId)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId };

        List<DebateItemVote> list = (List<DebateItemVote>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_DEBATEITEMID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemVote>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_DEBATEITEMID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DebateItemVote> findByDebateItemId(Long debateItemId,
        int start, int end) throws SystemException {
        return findByDebateItemId(debateItemId, start, end, null);
    }

    public List<DebateItemVote> findByDebateItemId(Long debateItemId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                debateItemId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItemVote> list = (List<DebateItemVote>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_DEBATEITEMID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
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

                list = (List<DebateItemVote>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemVote>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_DEBATEITEMID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DebateItemVote findByDebateItemId_First(Long debateItemId,
        OrderByComparator obc)
        throws NoSuchDebateItemVoteException, SystemException {
        List<DebateItemVote> list = findByDebateItemId(debateItemId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItemVote exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItemVote findByDebateItemId_Last(Long debateItemId,
        OrderByComparator obc)
        throws NoSuchDebateItemVoteException, SystemException {
        int count = countByDebateItemId(debateItemId);

        List<DebateItemVote> list = findByDebateItemId(debateItemId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItemVote exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItemVote[] findByDebateItemId_PrevAndNext(
        Long debateItemVoteId, Long debateItemId, OrderByComparator obc)
        throws NoSuchDebateItemVoteException, SystemException {
        DebateItemVote debateItemVote = findByPrimaryKey(debateItemVoteId);

        int count = countByDebateItemId(debateItemId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

            if (debateItemId == null) {
                query.append("debateItemId IS NULL");
            } else {
                query.append("debateItemId = ?");
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

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    debateItemVote);

            DebateItemVote[] array = new DebateItemVoteImpl[3];

            array[0] = (DebateItemVote) objArray[0];
            array[1] = (DebateItemVote) objArray[1];
            array[2] = (DebateItemVote) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<DebateItemVote> findByUserId(Long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        List<DebateItemVote> list = (List<DebateItemVote>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

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

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemVote>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DebateItemVote> findByUserId(Long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    public List<DebateItemVote> findByUserId(Long userId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                userId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItemVote> list = (List<DebateItemVote>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

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

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                list = (List<DebateItemVote>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemVote>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DebateItemVote findByUserId_First(Long userId, OrderByComparator obc)
        throws NoSuchDebateItemVoteException, SystemException {
        List<DebateItemVote> list = findByUserId(userId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItemVote exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItemVote findByUserId_Last(Long userId, OrderByComparator obc)
        throws NoSuchDebateItemVoteException, SystemException {
        int count = countByUserId(userId);

        List<DebateItemVote> list = findByUserId(userId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItemVote exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItemVote[] findByUserId_PrevAndNext(Long debateItemVoteId,
        Long userId, OrderByComparator obc)
        throws NoSuchDebateItemVoteException, SystemException {
        DebateItemVote debateItemVote = findByPrimaryKey(debateItemVoteId);

        int count = countByUserId(userId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

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

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (userId != null) {
                qPos.add(userId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    debateItemVote);

            DebateItemVote[] array = new DebateItemVoteImpl[3];

            array[0] = (DebateItemVote) objArray[0];
            array[1] = (DebateItemVote) objArray[1];
            array[2] = (DebateItemVote) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<DebateItemVote> findByDebateItemIdUserId(Long debateItemId,
        Long userId) throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, userId };

        List<DebateItemVote> list = (List<DebateItemVote>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_DEBATEITEMIDUSERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemVote>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_DEBATEITEMIDUSERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DebateItemVote> findByDebateItemIdUserId(Long debateItemId,
        Long userId, int start, int end) throws SystemException {
        return findByDebateItemIdUserId(debateItemId, userId, start, end, null);
    }

    public List<DebateItemVote> findByDebateItemIdUserId(Long debateItemId,
        Long userId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                debateItemId,
                
                userId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItemVote> list = (List<DebateItemVote>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_DEBATEITEMIDUSERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

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

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                list = (List<DebateItemVote>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemVote>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_DEBATEITEMIDUSERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DebateItemVote findByDebateItemIdUserId_First(Long debateItemId,
        Long userId, OrderByComparator obc)
        throws NoSuchDebateItemVoteException, SystemException {
        List<DebateItemVote> list = findByDebateItemIdUserId(debateItemId,
                userId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItemVote exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItemVote findByDebateItemIdUserId_Last(Long debateItemId,
        Long userId, OrderByComparator obc)
        throws NoSuchDebateItemVoteException, SystemException {
        int count = countByDebateItemIdUserId(debateItemId, userId);

        List<DebateItemVote> list = findByDebateItemIdUserId(debateItemId,
                userId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItemVote exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItemVote[] findByDebateItemIdUserId_PrevAndNext(
        Long debateItemVoteId, Long debateItemId, Long userId,
        OrderByComparator obc)
        throws NoSuchDebateItemVoteException, SystemException {
        DebateItemVote debateItemVote = findByPrimaryKey(debateItemVoteId);

        int count = countByDebateItemIdUserId(debateItemId, userId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

            if (debateItemId == null) {
                query.append("debateItemId IS NULL");
            } else {
                query.append("debateItemId = ?");
            }

            query.append(" AND ");

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

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (debateItemId != null) {
                qPos.add(debateItemId.longValue());
            }

            if (userId != null) {
                qPos.add(userId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    debateItemVote);

            DebateItemVote[] array = new DebateItemVoteImpl[3];

            array[0] = (DebateItemVote) objArray[0];
            array[1] = (DebateItemVote) objArray[1];
            array[2] = (DebateItemVote) objArray[2];

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

    public List<DebateItemVote> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateItemVote> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateItemVote> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItemVote> list = (List<DebateItemVote>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateItemVote>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateItemVote>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemVote>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByDebateItemId(Long debateItemId)
        throws SystemException {
        for (DebateItemVote debateItemVote : findByDebateItemId(debateItemId)) {
            remove(debateItemVote);
        }
    }

    public void removeByUserId(Long userId) throws SystemException {
        for (DebateItemVote debateItemVote : findByUserId(userId)) {
            remove(debateItemVote);
        }
    }

    public void removeByDebateItemIdUserId(Long debateItemId, Long userId)
        throws SystemException {
        for (DebateItemVote debateItemVote : findByDebateItemIdUserId(
                debateItemId, userId)) {
            remove(debateItemVote);
        }
    }

    public void removeAll() throws SystemException {
        for (DebateItemVote debateItemVote : findAll()) {
            remove(debateItemVote);
        }
    }

    public int countByDebateItemId(Long debateItemId) throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEBATEITEMID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEBATEITEMID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
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
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

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

    public int countByDebateItemIdUserId(Long debateItemId, Long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEBATEITEMIDUSERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVote WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEBATEITEMIDUSERID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debaterevision.model.DebateItemVote");

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
                        "value.object.listener.com.ext.portlet.debaterevision.model.DebateItemVote")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateItemVote>> listenersList = new ArrayList<ModelListener<DebateItemVote>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateItemVote>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
