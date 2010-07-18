package com.ext.portlet.debatemigration.service.persistence;

import com.ext.portlet.debatemigration.NoSuchDebateMigrationCommentException;
import com.ext.portlet.debatemigration.model.DebateMigrationComment;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationCommentImpl;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationCommentModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DebateMigrationCommentPersistenceImpl extends BasePersistenceImpl
    implements DebateMigrationCommentPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateMigrationCommentImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateMigrationCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCommentModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateMigrationCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCommentModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateMigrationCommentPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationPersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationPersistence debateMigrationPersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationCategoryPersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationCategoryPersistence debateMigrationCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationDebatePersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationDebatePersistence debateMigrationDebatePersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationItemPersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationItemPersistence debateMigrationItemPersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationCommentPersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationCommentPersistence debateMigrationCommentPersistence;

    public void cacheResult(DebateMigrationComment debateMigrationComment) {
        EntityCacheUtil.putResult(DebateMigrationCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCommentImpl.class,
            debateMigrationComment.getPrimaryKey(), debateMigrationComment);
    }

    public void cacheResult(
        List<DebateMigrationComment> debateMigrationComments) {
        for (DebateMigrationComment debateMigrationComment : debateMigrationComments) {
            if (EntityCacheUtil.getResult(
                        DebateMigrationCommentModelImpl.ENTITY_CACHE_ENABLED,
                        DebateMigrationCommentImpl.class,
                        debateMigrationComment.getPrimaryKey(), this) == null) {
                cacheResult(debateMigrationComment);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateMigrationCommentImpl.class.getName());
        EntityCacheUtil.clearCache(DebateMigrationCommentImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateMigrationComment create(Long debateMigrationCommentPK) {
        DebateMigrationComment debateMigrationComment = new DebateMigrationCommentImpl();

        debateMigrationComment.setNew(true);
        debateMigrationComment.setPrimaryKey(debateMigrationCommentPK);

        return debateMigrationComment;
    }

    public DebateMigrationComment remove(Long debateMigrationCommentPK)
        throws NoSuchDebateMigrationCommentException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateMigrationComment debateMigrationComment = (DebateMigrationComment) session.get(DebateMigrationCommentImpl.class,
                    debateMigrationCommentPK);

            if (debateMigrationComment == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DebateMigrationComment exists with the primary key " +
                        debateMigrationCommentPK);
                }

                throw new NoSuchDebateMigrationCommentException(
                    "No DebateMigrationComment exists with the primary key " +
                    debateMigrationCommentPK);
            }

            return remove(debateMigrationComment);
        } catch (NoSuchDebateMigrationCommentException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateMigrationComment remove(
        DebateMigrationComment debateMigrationComment)
        throws SystemException {
        for (ModelListener<DebateMigrationComment> listener : listeners) {
            listener.onBeforeRemove(debateMigrationComment);
        }

        debateMigrationComment = removeImpl(debateMigrationComment);

        for (ModelListener<DebateMigrationComment> listener : listeners) {
            listener.onAfterRemove(debateMigrationComment);
        }

        return debateMigrationComment;
    }

    protected DebateMigrationComment removeImpl(
        DebateMigrationComment debateMigrationComment)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateMigrationComment.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateMigrationCommentImpl.class,
                        debateMigrationComment.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateMigrationComment);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateMigrationCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCommentImpl.class,
            debateMigrationComment.getPrimaryKey());

        return debateMigrationComment;
    }

    /**
     * @deprecated Use <code>update(DebateMigrationComment debateMigrationComment, boolean merge)</code>.
     */
    public DebateMigrationComment update(
        DebateMigrationComment debateMigrationComment)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateMigrationComment debateMigrationComment) method. Use update(DebateMigrationComment debateMigrationComment, boolean merge) instead.");
        }

        return update(debateMigrationComment, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationComment the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationComment is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateMigrationComment update(
        DebateMigrationComment debateMigrationComment, boolean merge)
        throws SystemException {
        boolean isNew = debateMigrationComment.isNew();

        for (ModelListener<DebateMigrationComment> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateMigrationComment);
            } else {
                listener.onBeforeUpdate(debateMigrationComment);
            }
        }

        debateMigrationComment = updateImpl(debateMigrationComment, merge);

        for (ModelListener<DebateMigrationComment> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateMigrationComment);
            } else {
                listener.onAfterUpdate(debateMigrationComment);
            }
        }

        return debateMigrationComment;
    }

    public DebateMigrationComment updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateMigrationComment, merge);

            debateMigrationComment.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateMigrationCommentModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCommentImpl.class,
            debateMigrationComment.getPrimaryKey(), debateMigrationComment);

        return debateMigrationComment;
    }

    public DebateMigrationComment findByPrimaryKey(
        Long debateMigrationCommentPK)
        throws NoSuchDebateMigrationCommentException, SystemException {
        DebateMigrationComment debateMigrationComment = fetchByPrimaryKey(debateMigrationCommentPK);

        if (debateMigrationComment == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No DebateMigrationComment exists with the primary key " +
                    debateMigrationCommentPK);
            }

            throw new NoSuchDebateMigrationCommentException(
                "No DebateMigrationComment exists with the primary key " +
                debateMigrationCommentPK);
        }

        return debateMigrationComment;
    }

    public DebateMigrationComment fetchByPrimaryKey(
        Long debateMigrationCommentPK) throws SystemException {
        DebateMigrationComment debateMigrationComment = (DebateMigrationComment) EntityCacheUtil.getResult(DebateMigrationCommentModelImpl.ENTITY_CACHE_ENABLED,
                DebateMigrationCommentImpl.class, debateMigrationCommentPK, this);

        if (debateMigrationComment == null) {
            Session session = null;

            try {
                session = openSession();

                debateMigrationComment = (DebateMigrationComment) session.get(DebateMigrationCommentImpl.class,
                        debateMigrationCommentPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateMigrationComment != null) {
                    cacheResult(debateMigrationComment);
                }

                closeSession(session);
            }
        }

        return debateMigrationComment;
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

    public List<DebateMigrationComment> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateMigrationComment> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateMigrationComment> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateMigrationComment> list = (List<DebateMigrationComment>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debatemigration.model.DebateMigrationComment ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateMigrationComment>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateMigrationComment>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateMigrationComment>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (DebateMigrationComment debateMigrationComment : findAll()) {
            remove(debateMigrationComment);
        }
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debatemigration.model.DebateMigrationComment");

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
                        "value.object.listener.com.ext.portlet.debatemigration.model.DebateMigrationComment")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateMigrationComment>> listenersList = new ArrayList<ModelListener<DebateMigrationComment>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateMigrationComment>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
