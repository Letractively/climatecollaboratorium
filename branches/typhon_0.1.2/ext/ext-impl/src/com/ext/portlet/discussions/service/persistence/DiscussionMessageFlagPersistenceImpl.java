package com.ext.portlet.discussions.service.persistence;

import com.ext.portlet.discussions.NoSuchDiscussionMessageFlagException;
import com.ext.portlet.discussions.model.DiscussionMessageFlag;
import com.ext.portlet.discussions.model.impl.DiscussionMessageFlagImpl;
import com.ext.portlet.discussions.model.impl.DiscussionMessageFlagModelImpl;

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


public class DiscussionMessageFlagPersistenceImpl extends BasePersistenceImpl
    implements DiscussionMessageFlagPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DiscussionMessageFlagImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_MESSAGEID = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByMessageId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_MESSAGEID = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByMessageId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGEID = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByMessageId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DiscussionMessageFlagPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence discussionCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence discussionMessagePersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionMessageFlagPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionMessageFlagPersistence discussionMessageFlagPersistence;

    public void cacheResult(DiscussionMessageFlag discussionMessageFlag) {
        EntityCacheUtil.putResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            discussionMessageFlag.getPrimaryKey(), discussionMessageFlag);
    }

    public void cacheResult(List<DiscussionMessageFlag> discussionMessageFlags) {
        for (DiscussionMessageFlag discussionMessageFlag : discussionMessageFlags) {
            if (EntityCacheUtil.getResult(
                        DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionMessageFlagImpl.class,
                        discussionMessageFlag.getPrimaryKey(), this) == null) {
                cacheResult(discussionMessageFlag);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DiscussionMessageFlagImpl.class.getName());
        EntityCacheUtil.clearCache(DiscussionMessageFlagImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DiscussionMessageFlag create(Long pk) {
        DiscussionMessageFlag discussionMessageFlag = new DiscussionMessageFlagImpl();

        discussionMessageFlag.setNew(true);
        discussionMessageFlag.setPrimaryKey(pk);

        return discussionMessageFlag;
    }

    public DiscussionMessageFlag remove(Long pk)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DiscussionMessageFlag discussionMessageFlag = (DiscussionMessageFlag) session.get(DiscussionMessageFlagImpl.class,
                    pk);

            if (discussionMessageFlag == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DiscussionMessageFlag exists with the primary key " +
                        pk);
                }

                throw new NoSuchDiscussionMessageFlagException(
                    "No DiscussionMessageFlag exists with the primary key " +
                    pk);
            }

            return remove(discussionMessageFlag);
        } catch (NoSuchDiscussionMessageFlagException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DiscussionMessageFlag remove(
        DiscussionMessageFlag discussionMessageFlag) throws SystemException {
        for (ModelListener<DiscussionMessageFlag> listener : listeners) {
            listener.onBeforeRemove(discussionMessageFlag);
        }

        discussionMessageFlag = removeImpl(discussionMessageFlag);

        for (ModelListener<DiscussionMessageFlag> listener : listeners) {
            listener.onAfterRemove(discussionMessageFlag);
        }

        return discussionMessageFlag;
    }

    protected DiscussionMessageFlag removeImpl(
        DiscussionMessageFlag discussionMessageFlag) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (discussionMessageFlag.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DiscussionMessageFlagImpl.class,
                        discussionMessageFlag.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(discussionMessageFlag);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            discussionMessageFlag.getPrimaryKey());

        return discussionMessageFlag;
    }

    /**
     * @deprecated Use <code>update(DiscussionMessageFlag discussionMessageFlag, boolean merge)</code>.
     */
    public DiscussionMessageFlag update(
        DiscussionMessageFlag discussionMessageFlag) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DiscussionMessageFlag discussionMessageFlag) method. Use update(DiscussionMessageFlag discussionMessageFlag, boolean merge) instead.");
        }

        return update(discussionMessageFlag, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                discussionMessageFlag the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when discussionMessageFlag is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DiscussionMessageFlag update(
        DiscussionMessageFlag discussionMessageFlag, boolean merge)
        throws SystemException {
        boolean isNew = discussionMessageFlag.isNew();

        for (ModelListener<DiscussionMessageFlag> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(discussionMessageFlag);
            } else {
                listener.onBeforeUpdate(discussionMessageFlag);
            }
        }

        discussionMessageFlag = updateImpl(discussionMessageFlag, merge);

        for (ModelListener<DiscussionMessageFlag> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(discussionMessageFlag);
            } else {
                listener.onAfterUpdate(discussionMessageFlag);
            }
        }

        return discussionMessageFlag;
    }

    public DiscussionMessageFlag updateImpl(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, discussionMessageFlag, merge);

            discussionMessageFlag.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            discussionMessageFlag.getPrimaryKey(), discussionMessageFlag);

        return discussionMessageFlag;
    }

    public DiscussionMessageFlag findByPrimaryKey(Long pk)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        DiscussionMessageFlag discussionMessageFlag = fetchByPrimaryKey(pk);

        if (discussionMessageFlag == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No DiscussionMessageFlag exists with the primary key " +
                    pk);
            }

            throw new NoSuchDiscussionMessageFlagException(
                "No DiscussionMessageFlag exists with the primary key " + pk);
        }

        return discussionMessageFlag;
    }

    public DiscussionMessageFlag fetchByPrimaryKey(Long pk)
        throws SystemException {
        DiscussionMessageFlag discussionMessageFlag = (DiscussionMessageFlag) EntityCacheUtil.getResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionMessageFlagImpl.class, pk, this);

        if (discussionMessageFlag == null) {
            Session session = null;

            try {
                session = openSession();

                discussionMessageFlag = (DiscussionMessageFlag) session.get(DiscussionMessageFlagImpl.class,
                        pk);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (discussionMessageFlag != null) {
                    cacheResult(discussionMessageFlag);
                }

                closeSession(session);
            }
        }

        return discussionMessageFlag;
    }

    public List<DiscussionMessageFlag> findByMessageId(Long messageId)
        throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        List<DiscussionMessageFlag> list = (List<DiscussionMessageFlag>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MESSAGEID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessageFlag WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessageFlag>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MESSAGEID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DiscussionMessageFlag> findByMessageId(Long messageId,
        int start, int end) throws SystemException {
        return findByMessageId(messageId, start, end, null);
    }

    public List<DiscussionMessageFlag> findByMessageId(Long messageId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                messageId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionMessageFlag> list = (List<DiscussionMessageFlag>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_MESSAGEID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessageFlag WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                list = (List<DiscussionMessageFlag>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessageFlag>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_MESSAGEID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DiscussionMessageFlag findByMessageId_First(Long messageId,
        OrderByComparator obc)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        List<DiscussionMessageFlag> list = findByMessageId(messageId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessageFlag exists with the key {");

            msg.append("messageId=" + messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageFlagException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessageFlag findByMessageId_Last(Long messageId,
        OrderByComparator obc)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        int count = countByMessageId(messageId);

        List<DiscussionMessageFlag> list = findByMessageId(messageId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessageFlag exists with the key {");

            msg.append("messageId=" + messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageFlagException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessageFlag[] findByMessageId_PrevAndNext(Long pk,
        Long messageId, OrderByComparator obc)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        DiscussionMessageFlag discussionMessageFlag = findByPrimaryKey(pk);

        int count = countByMessageId(messageId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.discussions.model.DiscussionMessageFlag WHERE ");

            if (messageId == null) {
                query.append("messageId IS NULL");
            } else {
                query.append("messageId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (messageId != null) {
                qPos.add(messageId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    discussionMessageFlag);

            DiscussionMessageFlag[] array = new DiscussionMessageFlagImpl[3];

            array[0] = (DiscussionMessageFlag) objArray[0];
            array[1] = (DiscussionMessageFlag) objArray[1];
            array[2] = (DiscussionMessageFlag) objArray[2];

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

    public List<DiscussionMessageFlag> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DiscussionMessageFlag> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DiscussionMessageFlag> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionMessageFlag> list = (List<DiscussionMessageFlag>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessageFlag ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DiscussionMessageFlag>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DiscussionMessageFlag>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessageFlag>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByMessageId(Long messageId) throws SystemException {
        for (DiscussionMessageFlag discussionMessageFlag : findByMessageId(
                messageId)) {
            remove(discussionMessageFlag);
        }
    }

    public void removeAll() throws SystemException {
        for (DiscussionMessageFlag discussionMessageFlag : findAll()) {
            remove(discussionMessageFlag);
        }
    }

    public int countByMessageId(Long messageId) throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MESSAGEID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessageFlag WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MESSAGEID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.discussions.model.DiscussionMessageFlag");

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
                        "value.object.listener.com.ext.portlet.discussions.model.DiscussionMessageFlag")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DiscussionMessageFlag>> listenersList = new ArrayList<ModelListener<DiscussionMessageFlag>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DiscussionMessageFlag>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
