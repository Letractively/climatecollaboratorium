package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.NoSuchDebateCommentException;
import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.impl.DebateCommentImpl;
import com.ext.portlet.debaterevision.model.impl.DebateCommentModelImpl;

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


public class DebateCommentPersistenceImpl extends BasePersistenceImpl
    implements DebateCommentPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateCommentImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_COMMENTFORDEBATEITEM = new FinderPath(DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateCommentModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findBycommentForDebateItem",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMMENTFORDEBATEITEM = new FinderPath(DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateCommentModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findBycommentForDebateItem",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_COMMENTFORDEBATEITEM = new FinderPath(DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateCommentModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countBycommentForDebateItem",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateCommentModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateCommentModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateCommentPersistenceImpl.class);
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

    public void cacheResult(DebateComment debateComment) {
        EntityCacheUtil.putResult(DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateCommentImpl.class, debateComment.getPrimaryKey(),
            debateComment);
    }

    public void cacheResult(List<DebateComment> debateComments) {
        for (DebateComment debateComment : debateComments) {
            if (EntityCacheUtil.getResult(
                        DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
                        DebateCommentImpl.class, debateComment.getPrimaryKey(),
                        this) == null) {
                cacheResult(debateComment);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateCommentImpl.class.getName());
        EntityCacheUtil.clearCache(DebateCommentImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateComment create(Long debateCommentId) {
        DebateComment debateComment = new DebateCommentImpl();

        debateComment.setNew(true);
        debateComment.setPrimaryKey(debateCommentId);

        return debateComment;
    }

    public DebateComment remove(Long debateCommentId)
        throws NoSuchDebateCommentException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateComment debateComment = (DebateComment) session.get(DebateCommentImpl.class,
                    debateCommentId);

            if (debateComment == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No DebateComment exists with the primary key " +
                        debateCommentId);
                }

                throw new NoSuchDebateCommentException(
                    "No DebateComment exists with the primary key " +
                    debateCommentId);
            }

            return remove(debateComment);
        } catch (NoSuchDebateCommentException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateComment remove(DebateComment debateComment)
        throws SystemException {
        for (ModelListener<DebateComment> listener : listeners) {
            listener.onBeforeRemove(debateComment);
        }

        debateComment = removeImpl(debateComment);

        for (ModelListener<DebateComment> listener : listeners) {
            listener.onAfterRemove(debateComment);
        }

        return debateComment;
    }

    protected DebateComment removeImpl(DebateComment debateComment)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateComment.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateCommentImpl.class,
                        debateComment.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateComment);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateCommentImpl.class, debateComment.getPrimaryKey());

        return debateComment;
    }

    /**
     * @deprecated Use <code>update(DebateComment debateComment, boolean merge)</code>.
     */
    public DebateComment update(DebateComment debateComment)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateComment debateComment) method. Use update(DebateComment debateComment, boolean merge) instead.");
        }

        return update(debateComment, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateComment the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateComment is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateComment update(DebateComment debateComment, boolean merge)
        throws SystemException {
        boolean isNew = debateComment.isNew();

        for (ModelListener<DebateComment> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateComment);
            } else {
                listener.onBeforeUpdate(debateComment);
            }
        }

        debateComment = updateImpl(debateComment, merge);

        for (ModelListener<DebateComment> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateComment);
            } else {
                listener.onAfterUpdate(debateComment);
            }
        }

        return debateComment;
    }

    public DebateComment updateImpl(
        com.ext.portlet.debaterevision.model.DebateComment debateComment,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateComment, merge);

            debateComment.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateCommentImpl.class, debateComment.getPrimaryKey(),
            debateComment);

        return debateComment;
    }

    public DebateComment findByPrimaryKey(Long debateCommentId)
        throws NoSuchDebateCommentException, SystemException {
        DebateComment debateComment = fetchByPrimaryKey(debateCommentId);

        if (debateComment == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateComment exists with the primary key " +
                    debateCommentId);
            }

            throw new NoSuchDebateCommentException(
                "No DebateComment exists with the primary key " +
                debateCommentId);
        }

        return debateComment;
    }

    public DebateComment fetchByPrimaryKey(Long debateCommentId)
        throws SystemException {
        DebateComment debateComment = (DebateComment) EntityCacheUtil.getResult(DebateCommentModelImpl.ENTITY_CACHE_ENABLED,
                DebateCommentImpl.class, debateCommentId, this);

        if (debateComment == null) {
            Session session = null;

            try {
                session = openSession();

                debateComment = (DebateComment) session.get(DebateCommentImpl.class,
                        debateCommentId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateComment != null) {
                    cacheResult(debateComment);
                }

                closeSession(session);
            }
        }

        return debateComment;
    }

    public List<DebateComment> findBycommentForDebateItem(Long debateItemId)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId };

        List<DebateComment> list = (List<DebateComment>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMMENTFORDEBATEITEM,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateComment WHERE ");

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
                    list = new ArrayList<DebateComment>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMMENTFORDEBATEITEM,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DebateComment> findBycommentForDebateItem(Long debateItemId,
        int start, int end) throws SystemException {
        return findBycommentForDebateItem(debateItemId, start, end, null);
    }

    public List<DebateComment> findBycommentForDebateItem(Long debateItemId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                debateItemId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateComment> list = (List<DebateComment>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMMENTFORDEBATEITEM,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateComment WHERE ");

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

                list = (List<DebateComment>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateComment>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMMENTFORDEBATEITEM,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DebateComment findBycommentForDebateItem_First(Long debateItemId,
        OrderByComparator obc)
        throws NoSuchDebateCommentException, SystemException {
        List<DebateComment> list = findBycommentForDebateItem(debateItemId, 0,
                1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateComment exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateCommentException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateComment findBycommentForDebateItem_Last(Long debateItemId,
        OrderByComparator obc)
        throws NoSuchDebateCommentException, SystemException {
        int count = countBycommentForDebateItem(debateItemId);

        List<DebateComment> list = findBycommentForDebateItem(debateItemId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateComment exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateCommentException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateComment[] findBycommentForDebateItem_PrevAndNext(
        Long debateCommentId, Long debateItemId, OrderByComparator obc)
        throws NoSuchDebateCommentException, SystemException {
        DebateComment debateComment = findByPrimaryKey(debateCommentId);

        int count = countBycommentForDebateItem(debateItemId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.DebateComment WHERE ");

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
                    debateComment);

            DebateComment[] array = new DebateCommentImpl[3];

            array[0] = (DebateComment) objArray[0];
            array[1] = (DebateComment) objArray[1];
            array[2] = (DebateComment) objArray[2];

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

    public List<DebateComment> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateComment> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateComment> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateComment> list = (List<DebateComment>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateComment ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateComment>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateComment>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateComment>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeBycommentForDebateItem(Long debateItemId)
        throws SystemException {
        for (DebateComment debateComment : findBycommentForDebateItem(
                debateItemId)) {
            remove(debateComment);
        }
    }

    public void removeAll() throws SystemException {
        for (DebateComment debateComment : findAll()) {
            remove(debateComment);
        }
    }

    public int countBycommentForDebateItem(Long debateItemId)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMMENTFORDEBATEITEM,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateComment WHERE ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMMENTFORDEBATEITEM,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debaterevision.model.DebateComment");

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
                        "value.object.listener.com.ext.portlet.debaterevision.model.DebateComment")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateComment>> listenersList = new ArrayList<ModelListener<DebateComment>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateComment>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
