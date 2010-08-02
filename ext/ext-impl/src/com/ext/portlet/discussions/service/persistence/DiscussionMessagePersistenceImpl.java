package com.ext.portlet.discussions.service.persistence;

import com.ext.portlet.discussions.NoSuchDiscussionMessageException;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.model.impl.DiscussionMessageImpl;
import com.ext.portlet.discussions.model.impl.DiscussionMessageModelImpl;

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


public class DiscussionMessagePersistenceImpl extends BasePersistenceImpl
    implements DiscussionMessagePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DiscussionMessageImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_CATEGORYIDTHREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByCategoryIdThreadId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CATEGORYIDTHREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByCategoryIdThreadId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYIDTHREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByCategoryIdThreadId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_THREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByThreadId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_THREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByThreadId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_THREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByThreadId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_SINGLETHREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchBySingleThreadId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_SINGLETHREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countBySingleThreadId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_SUBJECTLIKE = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findBySubjectLike",
            new String[] { String.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_SUBJECTLIKE = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findBySubjectLike",
            new String[] {
                String.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_SUBJECTLIKE = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countBySubjectLike",
            new String[] { String.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_BODYLIKE = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByBodyLike",
            new String[] { String.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_BODYLIKE = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByBodyLike",
            new String[] {
                String.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_BODYLIKE = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByBodyLike",
            new String[] { String.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_MESSAGEID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByMessageId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGEID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByMessageId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DiscussionMessagePersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence discussionCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence discussionMessagePersistence;

    public void cacheResult(DiscussionMessage discussionMessage) {
        EntityCacheUtil.putResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageImpl.class, discussionMessage.getPrimaryKey(),
            discussionMessage);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
            new Object[] { discussionMessage.getMessageId() }, discussionMessage);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
            new Object[] { discussionMessage.getMessageId() }, discussionMessage);
    }

    public void cacheResult(List<DiscussionMessage> discussionMessages) {
        for (DiscussionMessage discussionMessage : discussionMessages) {
            if (EntityCacheUtil.getResult(
                        DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionMessageImpl.class,
                        discussionMessage.getPrimaryKey(), this) == null) {
                cacheResult(discussionMessage);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DiscussionMessageImpl.class.getName());
        EntityCacheUtil.clearCache(DiscussionMessageImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DiscussionMessage create(Long pk) {
        DiscussionMessage discussionMessage = new DiscussionMessageImpl();

        discussionMessage.setNew(true);
        discussionMessage.setPrimaryKey(pk);

        return discussionMessage;
    }

    public DiscussionMessage remove(Long pk)
        throws NoSuchDiscussionMessageException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DiscussionMessage discussionMessage = (DiscussionMessage) session.get(DiscussionMessageImpl.class,
                    pk);

            if (discussionMessage == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DiscussionMessage exists with the primary key " +
                        pk);
                }

                throw new NoSuchDiscussionMessageException(
                    "No DiscussionMessage exists with the primary key " + pk);
            }

            return remove(discussionMessage);
        } catch (NoSuchDiscussionMessageException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DiscussionMessage remove(DiscussionMessage discussionMessage)
        throws SystemException {
        for (ModelListener<DiscussionMessage> listener : listeners) {
            listener.onBeforeRemove(discussionMessage);
        }

        discussionMessage = removeImpl(discussionMessage);

        for (ModelListener<DiscussionMessage> listener : listeners) {
            listener.onAfterRemove(discussionMessage);
        }

        return discussionMessage;
    }

    protected DiscussionMessage removeImpl(DiscussionMessage discussionMessage)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (discussionMessage.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DiscussionMessageImpl.class,
                        discussionMessage.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(discussionMessage);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        DiscussionMessageModelImpl discussionMessageModelImpl = (DiscussionMessageModelImpl) discussionMessage;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
            new Object[] { discussionMessageModelImpl.getOriginalMessageId() });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGEID,
            new Object[] { discussionMessageModelImpl.getOriginalMessageId() });

        EntityCacheUtil.removeResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageImpl.class, discussionMessage.getPrimaryKey());

        return discussionMessage;
    }

    /**
     * @deprecated Use <code>update(DiscussionMessage discussionMessage, boolean merge)</code>.
     */
    public DiscussionMessage update(DiscussionMessage discussionMessage)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DiscussionMessage discussionMessage) method. Use update(DiscussionMessage discussionMessage, boolean merge) instead.");
        }

        return update(discussionMessage, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                discussionMessage the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when discussionMessage is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DiscussionMessage update(DiscussionMessage discussionMessage,
        boolean merge) throws SystemException {
        boolean isNew = discussionMessage.isNew();

        for (ModelListener<DiscussionMessage> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(discussionMessage);
            } else {
                listener.onBeforeUpdate(discussionMessage);
            }
        }

        discussionMessage = updateImpl(discussionMessage, merge);

        for (ModelListener<DiscussionMessage> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(discussionMessage);
            } else {
                listener.onAfterUpdate(discussionMessage);
            }
        }

        return discussionMessage;
    }

    public DiscussionMessage updateImpl(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge) throws SystemException {
        boolean isNew = discussionMessage.isNew();

        DiscussionMessageModelImpl discussionMessageModelImpl = (DiscussionMessageModelImpl) discussionMessage;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, discussionMessage, merge);

            discussionMessage.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageImpl.class, discussionMessage.getPrimaryKey(),
            discussionMessage);

        if (!isNew &&
                (!Validator.equals(discussionMessage.getMessageId(),
                    discussionMessageModelImpl.getOriginalMessageId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                new Object[] { discussionMessageModelImpl.getOriginalMessageId() });
        }

        if (isNew ||
                (!Validator.equals(discussionMessage.getMessageId(),
                    discussionMessageModelImpl.getOriginalMessageId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                new Object[] { discussionMessage.getMessageId() },
                discussionMessage);
        }

        if (!isNew &&
                (!Validator.equals(discussionMessage.getMessageId(),
                    discussionMessageModelImpl.getOriginalMessageId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                new Object[] { discussionMessageModelImpl.getOriginalMessageId() });
        }

        if (isNew ||
                (!Validator.equals(discussionMessage.getMessageId(),
                    discussionMessageModelImpl.getOriginalMessageId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                new Object[] { discussionMessage.getMessageId() },
                discussionMessage);
        }

        return discussionMessage;
    }

    public DiscussionMessage findByPrimaryKey(Long pk)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = fetchByPrimaryKey(pk);

        if (discussionMessage == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DiscussionMessage exists with the primary key " +
                    pk);
            }

            throw new NoSuchDiscussionMessageException(
                "No DiscussionMessage exists with the primary key " + pk);
        }

        return discussionMessage;
    }

    public DiscussionMessage fetchByPrimaryKey(Long pk)
        throws SystemException {
        DiscussionMessage discussionMessage = (DiscussionMessage) EntityCacheUtil.getResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionMessageImpl.class, pk, this);

        if (discussionMessage == null) {
            Session session = null;

            try {
                session = openSession();

                discussionMessage = (DiscussionMessage) session.get(DiscussionMessageImpl.class,
                        pk);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (discussionMessage != null) {
                    cacheResult(discussionMessage);
                }

                closeSession(session);
            }
        }

        return discussionMessage;
    }

    public List<DiscussionMessage> findByCategoryIdThreadId(Long categoryId,
        Long threadId) throws SystemException {
        Object[] finderArgs = new Object[] { categoryId, threadId };

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CATEGORYIDTHREADID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (categoryId == null) {
                    query.append("categoryId IS NULL");
                } else {
                    query.append("categoryId = ?");
                }

                query.append(" AND ");

                if (threadId == null) {
                    query.append("threadId IS NULL");
                } else {
                    query.append("threadId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("createDate DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryId != null) {
                    qPos.add(categoryId.longValue());
                }

                if (threadId != null) {
                    qPos.add(threadId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CATEGORYIDTHREADID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DiscussionMessage> findByCategoryIdThreadId(Long categoryId,
        Long threadId, int start, int end) throws SystemException {
        return findByCategoryIdThreadId(categoryId, threadId, start, end, null);
    }

    public List<DiscussionMessage> findByCategoryIdThreadId(Long categoryId,
        Long threadId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                categoryId,
                
                threadId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CATEGORYIDTHREADID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (categoryId == null) {
                    query.append("categoryId IS NULL");
                } else {
                    query.append("categoryId = ?");
                }

                query.append(" AND ");

                if (threadId == null) {
                    query.append("threadId IS NULL");
                } else {
                    query.append("threadId = ?");
                }

                query.append(" AND deleted is null ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("createDate DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryId != null) {
                    qPos.add(categoryId.longValue());
                }

                if (threadId != null) {
                    qPos.add(threadId.longValue());
                }

                list = (List<DiscussionMessage>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CATEGORYIDTHREADID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DiscussionMessage findByCategoryIdThreadId_First(Long categoryId,
        Long threadId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        List<DiscussionMessage> list = findByCategoryIdThreadId(categoryId,
                threadId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("categoryId=" + categoryId);

            msg.append(", ");
            msg.append("threadId=" + threadId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessage findByCategoryIdThreadId_Last(Long categoryId,
        Long threadId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        int count = countByCategoryIdThreadId(categoryId, threadId);

        List<DiscussionMessage> list = findByCategoryIdThreadId(categoryId,
                threadId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("categoryId=" + categoryId);

            msg.append(", ");
            msg.append("threadId=" + threadId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessage[] findByCategoryIdThreadId_PrevAndNext(Long pk,
        Long categoryId, Long threadId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByPrimaryKey(pk);

        int count = countByCategoryIdThreadId(categoryId, threadId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

            if (categoryId == null) {
                query.append("categoryId IS NULL");
            } else {
                query.append("categoryId = ?");
            }

            query.append(" AND ");

            if (threadId == null) {
                query.append("threadId IS NULL");
            } else {
                query.append("threadId = ?");
            }

            query.append(" AND deleted is null ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("createDate DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (categoryId != null) {
                qPos.add(categoryId.longValue());
            }

            if (threadId != null) {
                qPos.add(threadId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    discussionMessage);

            DiscussionMessage[] array = new DiscussionMessageImpl[3];

            array[0] = (DiscussionMessage) objArray[0];
            array[1] = (DiscussionMessage) objArray[1];
            array[2] = (DiscussionMessage) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<DiscussionMessage> findByThreadId(Long threadId)
        throws SystemException {
        Object[] finderArgs = new Object[] { threadId };

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_THREADID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (threadId == null) {
                    query.append("threadId IS NULL");
                } else {
                    query.append("threadId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("createDate DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (threadId != null) {
                    qPos.add(threadId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_THREADID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DiscussionMessage> findByThreadId(Long threadId, int start,
        int end) throws SystemException {
        return findByThreadId(threadId, start, end, null);
    }

    public List<DiscussionMessage> findByThreadId(Long threadId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                threadId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_THREADID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (threadId == null) {
                    query.append("threadId IS NULL");
                } else {
                    query.append("threadId = ?");
                }

                query.append(" AND deleted is null ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("createDate DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (threadId != null) {
                    qPos.add(threadId.longValue());
                }

                list = (List<DiscussionMessage>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_THREADID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DiscussionMessage findByThreadId_First(Long threadId,
        OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        List<DiscussionMessage> list = findByThreadId(threadId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("threadId=" + threadId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessage findByThreadId_Last(Long threadId,
        OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        int count = countByThreadId(threadId);

        List<DiscussionMessage> list = findByThreadId(threadId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("threadId=" + threadId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessage[] findByThreadId_PrevAndNext(Long pk,
        Long threadId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByPrimaryKey(pk);

        int count = countByThreadId(threadId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

            if (threadId == null) {
                query.append("threadId IS NULL");
            } else {
                query.append("threadId = ?");
            }

            query.append(" AND deleted is null ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("createDate DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (threadId != null) {
                qPos.add(threadId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    discussionMessage);

            DiscussionMessage[] array = new DiscussionMessageImpl[3];

            array[0] = (DiscussionMessage) objArray[0];
            array[1] = (DiscussionMessage) objArray[1];
            array[2] = (DiscussionMessage) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DiscussionMessage findBySingleThreadId(Long messageId)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = fetchBySingleThreadId(messageId);

        if (discussionMessage == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("messageId=" + messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDiscussionMessageException(msg.toString());
        }

        return discussionMessage;
    }

    public DiscussionMessage fetchBySingleThreadId(Long messageId)
        throws SystemException {
        return fetchBySingleThreadId(messageId, true);
    }

    public DiscussionMessage fetchBySingleThreadId(Long messageId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("createDate DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                List<DiscussionMessage> list = q.list();

                result = list;

                DiscussionMessage discussionMessage = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                        finderArgs, list);
                } else {
                    discussionMessage = list.get(0);

                    cacheResult(discussionMessage);

                    if ((discussionMessage.getMessageId() == null) ||
                            !discussionMessage.getMessageId().equals(messageId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                            finderArgs, discussionMessage);
                    }
                }

                return discussionMessage;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                        finderArgs, new ArrayList<DiscussionMessage>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (DiscussionMessage) result;
            }
        }
    }

    public List<DiscussionMessage> findBySubjectLike(String subject,
        Long categoryGroupId) throws SystemException {
        Object[] finderArgs = new Object[] { subject, categoryGroupId };

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SUBJECTLIKE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (subject == null) {
                    query.append("subject LIKE null");
                } else {
                    query.append("subject LIKE ?");
                }

                query.append(" AND ");

                if (categoryGroupId == null) {
                    query.append("categoryGroupId IS NULL");
                } else {
                    query.append("categoryGroupId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("createDate DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (subject != null) {
                    qPos.add(subject);
                }

                if (categoryGroupId != null) {
                    qPos.add(categoryGroupId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SUBJECTLIKE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DiscussionMessage> findBySubjectLike(String subject,
        Long categoryGroupId, int start, int end) throws SystemException {
        return findBySubjectLike(subject, categoryGroupId, start, end, null);
    }

    public List<DiscussionMessage> findBySubjectLike(String subject,
        Long categoryGroupId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                subject,
                
                categoryGroupId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_SUBJECTLIKE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (subject == null) {
                    query.append("subject LIKE null");
                } else {
                    query.append("subject LIKE ?");
                }

                query.append(" AND ");

                if (categoryGroupId == null) {
                    query.append("categoryGroupId IS NULL");
                } else {
                    query.append("categoryGroupId = ?");
                }

                query.append(" AND deleted is null ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("createDate DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (subject != null) {
                    qPos.add(subject);
                }

                if (categoryGroupId != null) {
                    qPos.add(categoryGroupId.longValue());
                }

                list = (List<DiscussionMessage>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_SUBJECTLIKE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DiscussionMessage findBySubjectLike_First(String subject,
        Long categoryGroupId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        List<DiscussionMessage> list = findBySubjectLike(subject,
                categoryGroupId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("subject=" + subject);

            msg.append(", ");
            msg.append("categoryGroupId=" + categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessage findBySubjectLike_Last(String subject,
        Long categoryGroupId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        int count = countBySubjectLike(subject, categoryGroupId);

        List<DiscussionMessage> list = findBySubjectLike(subject,
                categoryGroupId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("subject=" + subject);

            msg.append(", ");
            msg.append("categoryGroupId=" + categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessage[] findBySubjectLike_PrevAndNext(Long pk,
        String subject, Long categoryGroupId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByPrimaryKey(pk);

        int count = countBySubjectLike(subject, categoryGroupId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

            if (subject == null) {
                query.append("subject LIKE null");
            } else {
                query.append("subject LIKE ?");
            }

            query.append(" AND ");

            if (categoryGroupId == null) {
                query.append("categoryGroupId IS NULL");
            } else {
                query.append("categoryGroupId = ?");
            }

            query.append(" AND deleted is null ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("createDate DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (subject != null) {
                qPos.add(subject);
            }

            if (categoryGroupId != null) {
                qPos.add(categoryGroupId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    discussionMessage);

            DiscussionMessage[] array = new DiscussionMessageImpl[3];

            array[0] = (DiscussionMessage) objArray[0];
            array[1] = (DiscussionMessage) objArray[1];
            array[2] = (DiscussionMessage) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<DiscussionMessage> findByBodyLike(String body,
        Long categoryGroupId) throws SystemException {
        Object[] finderArgs = new Object[] { body, categoryGroupId };

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_BODYLIKE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (body == null) {
                    query.append("body LIKE null");
                } else {
                    query.append("body LIKE ?");
                }

                query.append(" AND ");

                if (categoryGroupId == null) {
                    query.append("categoryGroupId IS NULL");
                } else {
                    query.append("categoryGroupId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("createDate DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (body != null) {
                    qPos.add(body);
                }

                if (categoryGroupId != null) {
                    qPos.add(categoryGroupId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_BODYLIKE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DiscussionMessage> findByBodyLike(String body,
        Long categoryGroupId, int start, int end) throws SystemException {
        return findByBodyLike(body, categoryGroupId, start, end, null);
    }

    public List<DiscussionMessage> findByBodyLike(String body,
        Long categoryGroupId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                body,
                
                categoryGroupId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_BODYLIKE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (body == null) {
                    query.append("body LIKE null");
                } else {
                    query.append("body LIKE ?");
                }

                query.append(" AND ");

                if (categoryGroupId == null) {
                    query.append("categoryGroupId IS NULL");
                } else {
                    query.append("categoryGroupId = ?");
                }

                query.append(" AND deleted is null ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("createDate DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (body != null) {
                    qPos.add(body);
                }

                if (categoryGroupId != null) {
                    qPos.add(categoryGroupId.longValue());
                }

                list = (List<DiscussionMessage>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_BODYLIKE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DiscussionMessage findByBodyLike_First(String body,
        Long categoryGroupId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        List<DiscussionMessage> list = findByBodyLike(body, categoryGroupId, 0,
                1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("body=" + body);

            msg.append(", ");
            msg.append("categoryGroupId=" + categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessage findByBodyLike_Last(String body,
        Long categoryGroupId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        int count = countByBodyLike(body, categoryGroupId);

        List<DiscussionMessage> list = findByBodyLike(body, categoryGroupId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("body=" + body);

            msg.append(", ");
            msg.append("categoryGroupId=" + categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionMessage[] findByBodyLike_PrevAndNext(Long pk, String body,
        Long categoryGroupId, OrderByComparator obc)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByPrimaryKey(pk);

        int count = countByBodyLike(body, categoryGroupId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

            if (body == null) {
                query.append("body LIKE null");
            } else {
                query.append("body LIKE ?");
            }

            query.append(" AND ");

            if (categoryGroupId == null) {
                query.append("categoryGroupId IS NULL");
            } else {
                query.append("categoryGroupId = ?");
            }

            query.append(" AND deleted is null ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("createDate DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (body != null) {
                qPos.add(body);
            }

            if (categoryGroupId != null) {
                qPos.add(categoryGroupId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    discussionMessage);

            DiscussionMessage[] array = new DiscussionMessageImpl[3];

            array[0] = (DiscussionMessage) objArray[0];
            array[1] = (DiscussionMessage) objArray[1];
            array[2] = (DiscussionMessage) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DiscussionMessage findByMessageId(Long messageId)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = fetchByMessageId(messageId);

        if (discussionMessage == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionMessage exists with the key {");

            msg.append("messageId=" + messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDiscussionMessageException(msg.toString());
        }

        return discussionMessage;
    }

    public DiscussionMessage fetchByMessageId(Long messageId)
        throws SystemException {
        return fetchByMessageId(messageId, true);
    }

    public DiscussionMessage fetchByMessageId(Long messageId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("createDate DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                List<DiscussionMessage> list = q.list();

                result = list;

                DiscussionMessage discussionMessage = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                        finderArgs, list);
                } else {
                    discussionMessage = list.get(0);

                    cacheResult(discussionMessage);

                    if ((discussionMessage.getMessageId() == null) ||
                            !discussionMessage.getMessageId().equals(messageId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                            finderArgs, discussionMessage);
                    }
                }

                return discussionMessage;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                        finderArgs, new ArrayList<DiscussionMessage>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (DiscussionMessage) result;
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

    public List<DiscussionMessage> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DiscussionMessage> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DiscussionMessage> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("createDate DESC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DiscussionMessage>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DiscussionMessage>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByCategoryIdThreadId(Long categoryId, Long threadId)
        throws SystemException {
        for (DiscussionMessage discussionMessage : findByCategoryIdThreadId(
                categoryId, threadId)) {
            remove(discussionMessage);
        }
    }

    public void removeByThreadId(Long threadId) throws SystemException {
        for (DiscussionMessage discussionMessage : findByThreadId(threadId)) {
            remove(discussionMessage);
        }
    }

    public void removeBySingleThreadId(Long messageId)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findBySingleThreadId(messageId);

        remove(discussionMessage);
    }

    public void removeBySubjectLike(String subject, Long categoryGroupId)
        throws SystemException {
        for (DiscussionMessage discussionMessage : findBySubjectLike(subject,
                categoryGroupId)) {
            remove(discussionMessage);
        }
    }

    public void removeByBodyLike(String body, Long categoryGroupId)
        throws SystemException {
        for (DiscussionMessage discussionMessage : findByBodyLike(body,
                categoryGroupId)) {
            remove(discussionMessage);
        }
    }

    public void removeByMessageId(Long messageId)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByMessageId(messageId);

        remove(discussionMessage);
    }

    public void removeAll() throws SystemException {
        for (DiscussionMessage discussionMessage : findAll()) {
            remove(discussionMessage);
        }
    }

    public int countByCategoryIdThreadId(Long categoryId, Long threadId)
        throws SystemException {
        Object[] finderArgs = new Object[] { categoryId, threadId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CATEGORYIDTHREADID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (categoryId == null) {
                    query.append("categoryId IS NULL");
                } else {
                    query.append("categoryId = ?");
                }

                query.append(" AND ");

                if (threadId == null) {
                    query.append("threadId IS NULL");
                } else {
                    query.append("threadId = ?");
                }

                query.append(" AND deleted is null ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryId != null) {
                    qPos.add(categoryId.longValue());
                }

                if (threadId != null) {
                    qPos.add(threadId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CATEGORYIDTHREADID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByThreadId(Long threadId) throws SystemException {
        Object[] finderArgs = new Object[] { threadId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_THREADID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (threadId == null) {
                    query.append("threadId IS NULL");
                } else {
                    query.append("threadId = ?");
                }

                query.append(" AND deleted is null ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (threadId != null) {
                    qPos.add(threadId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_THREADID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countBySingleThreadId(Long messageId) throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SINGLETHREADID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" AND deleted is null ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SINGLETHREADID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countBySubjectLike(String subject, Long categoryGroupId)
        throws SystemException {
        Object[] finderArgs = new Object[] { subject, categoryGroupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUBJECTLIKE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (subject == null) {
                    query.append("subject LIKE null");
                } else {
                    query.append("subject LIKE ?");
                }

                query.append(" AND ");

                if (categoryGroupId == null) {
                    query.append("categoryGroupId IS NULL");
                } else {
                    query.append("categoryGroupId = ?");
                }

                query.append(" AND deleted is null ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (subject != null) {
                    qPos.add(subject);
                }

                if (categoryGroupId != null) {
                    qPos.add(categoryGroupId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUBJECTLIKE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByBodyLike(String body, Long categoryGroupId)
        throws SystemException {
        Object[] finderArgs = new Object[] { body, categoryGroupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_BODYLIKE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (body == null) {
                    query.append("body LIKE null");
                } else {
                    query.append("body LIKE ?");
                }

                query.append(" AND ");

                if (categoryGroupId == null) {
                    query.append("categoryGroupId IS NULL");
                } else {
                    query.append("categoryGroupId = ?");
                }

                query.append(" AND deleted is null ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (body != null) {
                    qPos.add(body);
                }

                if (categoryGroupId != null) {
                    qPos.add(categoryGroupId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BODYLIKE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
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
                    "FROM com.ext.portlet.discussions.model.DiscussionMessage WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" AND deleted is null ");

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
                        "SELECT COUNT(*) FROM com.ext.portlet.discussions.model.DiscussionMessage");

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
                        "value.object.listener.com.ext.portlet.discussions.model.DiscussionMessage")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DiscussionMessage>> listenersList = new ArrayList<ModelListener<DiscussionMessage>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DiscussionMessage>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
