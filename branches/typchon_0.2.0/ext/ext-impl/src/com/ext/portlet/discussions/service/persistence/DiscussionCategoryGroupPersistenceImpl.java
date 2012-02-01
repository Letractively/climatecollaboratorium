package com.ext.portlet.discussions.service.persistence;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupImpl;
import com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupModelImpl;

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


public class DiscussionCategoryGroupPersistenceImpl extends BasePersistenceImpl
    implements DiscussionCategoryGroupPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DiscussionCategoryGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DiscussionCategoryGroupPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence discussionCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence discussionMessagePersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionMessageFlagPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionMessageFlagPersistence discussionMessageFlagPersistence;

    public void cacheResult(DiscussionCategoryGroup discussionCategoryGroup) {
        EntityCacheUtil.putResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupImpl.class,
            discussionCategoryGroup.getPrimaryKey(), discussionCategoryGroup);
    }

    public void cacheResult(
        List<DiscussionCategoryGroup> discussionCategoryGroups) {
        for (DiscussionCategoryGroup discussionCategoryGroup : discussionCategoryGroups) {
            if (EntityCacheUtil.getResult(
                        DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionCategoryGroupImpl.class,
                        discussionCategoryGroup.getPrimaryKey(), this) == null) {
                cacheResult(discussionCategoryGroup);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DiscussionCategoryGroupImpl.class.getName());
        EntityCacheUtil.clearCache(DiscussionCategoryGroupImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DiscussionCategoryGroup create(Long id) {
        DiscussionCategoryGroup discussionCategoryGroup = new DiscussionCategoryGroupImpl();

        discussionCategoryGroup.setNew(true);
        discussionCategoryGroup.setPrimaryKey(id);

        return discussionCategoryGroup;
    }

    public DiscussionCategoryGroup remove(Long id)
        throws NoSuchDiscussionCategoryGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DiscussionCategoryGroup discussionCategoryGroup = (DiscussionCategoryGroup) session.get(DiscussionCategoryGroupImpl.class,
                    id);

            if (discussionCategoryGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DiscussionCategoryGroup exists with the primary key " +
                        id);
                }

                throw new NoSuchDiscussionCategoryGroupException(
                    "No DiscussionCategoryGroup exists with the primary key " +
                    id);
            }

            return remove(discussionCategoryGroup);
        } catch (NoSuchDiscussionCategoryGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DiscussionCategoryGroup remove(
        DiscussionCategoryGroup discussionCategoryGroup)
        throws SystemException {
        for (ModelListener<DiscussionCategoryGroup> listener : listeners) {
            listener.onBeforeRemove(discussionCategoryGroup);
        }

        discussionCategoryGroup = removeImpl(discussionCategoryGroup);

        for (ModelListener<DiscussionCategoryGroup> listener : listeners) {
            listener.onAfterRemove(discussionCategoryGroup);
        }

        return discussionCategoryGroup;
    }

    protected DiscussionCategoryGroup removeImpl(
        DiscussionCategoryGroup discussionCategoryGroup)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (discussionCategoryGroup.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DiscussionCategoryGroupImpl.class,
                        discussionCategoryGroup.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(discussionCategoryGroup);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupImpl.class,
            discussionCategoryGroup.getPrimaryKey());

        return discussionCategoryGroup;
    }

    /**
     * @deprecated Use <code>update(DiscussionCategoryGroup discussionCategoryGroup, boolean merge)</code>.
     */
    public DiscussionCategoryGroup update(
        DiscussionCategoryGroup discussionCategoryGroup)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DiscussionCategoryGroup discussionCategoryGroup) method. Use update(DiscussionCategoryGroup discussionCategoryGroup, boolean merge) instead.");
        }

        return update(discussionCategoryGroup, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                discussionCategoryGroup the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when discussionCategoryGroup is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DiscussionCategoryGroup update(
        DiscussionCategoryGroup discussionCategoryGroup, boolean merge)
        throws SystemException {
        boolean isNew = discussionCategoryGroup.isNew();

        for (ModelListener<DiscussionCategoryGroup> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(discussionCategoryGroup);
            } else {
                listener.onBeforeUpdate(discussionCategoryGroup);
            }
        }

        discussionCategoryGroup = updateImpl(discussionCategoryGroup, merge);

        for (ModelListener<DiscussionCategoryGroup> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(discussionCategoryGroup);
            } else {
                listener.onAfterUpdate(discussionCategoryGroup);
            }
        }

        return discussionCategoryGroup;
    }

    public DiscussionCategoryGroup updateImpl(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, discussionCategoryGroup, merge);

            discussionCategoryGroup.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupImpl.class,
            discussionCategoryGroup.getPrimaryKey(), discussionCategoryGroup);

        return discussionCategoryGroup;
    }

    public DiscussionCategoryGroup findByPrimaryKey(Long id)
        throws NoSuchDiscussionCategoryGroupException, SystemException {
        DiscussionCategoryGroup discussionCategoryGroup = fetchByPrimaryKey(id);

        if (discussionCategoryGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No DiscussionCategoryGroup exists with the primary key " +
                    id);
            }

            throw new NoSuchDiscussionCategoryGroupException(
                "No DiscussionCategoryGroup exists with the primary key " + id);
        }

        return discussionCategoryGroup;
    }

    public DiscussionCategoryGroup fetchByPrimaryKey(Long id)
        throws SystemException {
        DiscussionCategoryGroup discussionCategoryGroup = (DiscussionCategoryGroup) EntityCacheUtil.getResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionCategoryGroupImpl.class, id, this);

        if (discussionCategoryGroup == null) {
            Session session = null;

            try {
                session = openSession();

                discussionCategoryGroup = (DiscussionCategoryGroup) session.get(DiscussionCategoryGroupImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (discussionCategoryGroup != null) {
                    cacheResult(discussionCategoryGroup);
                }

                closeSession(session);
            }
        }

        return discussionCategoryGroup;
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

    public List<DiscussionCategoryGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DiscussionCategoryGroup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DiscussionCategoryGroup> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionCategoryGroup> list = (List<DiscussionCategoryGroup>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionCategoryGroup ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DiscussionCategoryGroup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DiscussionCategoryGroup>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionCategoryGroup>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (DiscussionCategoryGroup discussionCategoryGroup : findAll()) {
            remove(discussionCategoryGroup);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.discussions.model.DiscussionCategoryGroup");

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
                        "value.object.listener.com.ext.portlet.discussions.model.DiscussionCategoryGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DiscussionCategoryGroup>> listenersList = new ArrayList<ModelListener<DiscussionCategoryGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DiscussionCategoryGroup>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
