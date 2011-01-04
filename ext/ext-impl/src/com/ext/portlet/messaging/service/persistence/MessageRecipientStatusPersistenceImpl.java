package com.ext.portlet.messaging.service.persistence;

import com.ext.portlet.messaging.NoSuchMessageRecipientStatusException;
import com.ext.portlet.messaging.model.MessageRecipientStatus;
import com.ext.portlet.messaging.model.impl.MessageRecipientStatusImpl;
import com.ext.portlet.messaging.model.impl.MessageRecipientStatusModelImpl;

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


public class MessageRecipientStatusPersistenceImpl extends BasePersistenceImpl
    implements MessageRecipientStatusPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MessageRecipientStatusImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_RECEIVINGUSER = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByReceivingUser",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_RECEIVINGUSER = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByReceivingUser",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_RECEIVINGUSER = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByReceivingUser",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_RECEIVINGUSERARCHIVED = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByReceivingUserArchived",
            new String[] { Long.class.getName(), Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_RECEIVINGUSERARCHIVED =
        new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByReceivingUserArchived",
            new String[] {
                Long.class.getName(), Boolean.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_RECEIVINGUSERARCHIVED = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByReceivingUserArchived",
            new String[] { Long.class.getName(), Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_MESSAGEID = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByMessageId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_MESSAGEID = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByMessageId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGEID = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByMessageId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_MESSAGERECIEVER = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByMessageReciever",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGERECIEVER = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByMessageReciever",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MessageRecipientStatusPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessagePersistence.impl")
    protected com.ext.portlet.messaging.service.persistence.MessagePersistence messagePersistence;
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessageRecipientStatusPersistence.impl")
    protected com.ext.portlet.messaging.service.persistence.MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessagingUserPreferencesPersistence.impl")
    protected com.ext.portlet.messaging.service.persistence.MessagingUserPreferencesPersistence messagingUserPreferencesPersistence;
    @BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence.impl")
    protected com.liferay.portal.service.persistence.UserPersistence userPersistence;

    public void cacheResult(MessageRecipientStatus messageRecipientStatus) {
        EntityCacheUtil.putResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            messageRecipientStatus.getPrimaryKey(), messageRecipientStatus);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
            new Object[] {
                messageRecipientStatus.getMessageId(),
                
            messageRecipientStatus.getUserId()
            }, messageRecipientStatus);
    }

    public void cacheResult(
        List<MessageRecipientStatus> messageRecipientStatuses) {
        for (MessageRecipientStatus messageRecipientStatus : messageRecipientStatuses) {
            if (EntityCacheUtil.getResult(
                        MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
                        MessageRecipientStatusImpl.class,
                        messageRecipientStatus.getPrimaryKey(), this) == null) {
                cacheResult(messageRecipientStatus);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MessageRecipientStatusImpl.class.getName());
        EntityCacheUtil.clearCache(MessageRecipientStatusImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MessageRecipientStatus create(Long messageRecipientId) {
        MessageRecipientStatus messageRecipientStatus = new MessageRecipientStatusImpl();

        messageRecipientStatus.setNew(true);
        messageRecipientStatus.setPrimaryKey(messageRecipientId);

        return messageRecipientStatus;
    }

    public MessageRecipientStatus remove(Long messageRecipientId)
        throws NoSuchMessageRecipientStatusException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessageRecipientStatus messageRecipientStatus = (MessageRecipientStatus) session.get(MessageRecipientStatusImpl.class,
                    messageRecipientId);

            if (messageRecipientStatus == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No MessageRecipientStatus exists with the primary key " +
                        messageRecipientId);
                }

                throw new NoSuchMessageRecipientStatusException(
                    "No MessageRecipientStatus exists with the primary key " +
                    messageRecipientId);
            }

            return remove(messageRecipientStatus);
        } catch (NoSuchMessageRecipientStatusException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MessageRecipientStatus remove(
        MessageRecipientStatus messageRecipientStatus)
        throws SystemException {
        for (ModelListener<MessageRecipientStatus> listener : listeners) {
            listener.onBeforeRemove(messageRecipientStatus);
        }

        messageRecipientStatus = removeImpl(messageRecipientStatus);

        for (ModelListener<MessageRecipientStatus> listener : listeners) {
            listener.onAfterRemove(messageRecipientStatus);
        }

        return messageRecipientStatus;
    }

    protected MessageRecipientStatus removeImpl(
        MessageRecipientStatus messageRecipientStatus)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (messageRecipientStatus.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MessageRecipientStatusImpl.class,
                        messageRecipientStatus.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(messageRecipientStatus);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        MessageRecipientStatusModelImpl messageRecipientStatusModelImpl = (MessageRecipientStatusModelImpl) messageRecipientStatus;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
            new Object[] {
                messageRecipientStatusModelImpl.getOriginalMessageId(),
                
            messageRecipientStatusModelImpl.getOriginalUserId()
            });

        EntityCacheUtil.removeResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            messageRecipientStatus.getPrimaryKey());

        return messageRecipientStatus;
    }

    /**
     * @deprecated Use <code>update(MessageRecipientStatus messageRecipientStatus, boolean merge)</code>.
     */
    public MessageRecipientStatus update(
        MessageRecipientStatus messageRecipientStatus)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MessageRecipientStatus messageRecipientStatus) method. Use update(MessageRecipientStatus messageRecipientStatus, boolean merge) instead.");
        }

        return update(messageRecipientStatus, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messageRecipientStatus the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messageRecipientStatus is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MessageRecipientStatus update(
        MessageRecipientStatus messageRecipientStatus, boolean merge)
        throws SystemException {
        boolean isNew = messageRecipientStatus.isNew();

        for (ModelListener<MessageRecipientStatus> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(messageRecipientStatus);
            } else {
                listener.onBeforeUpdate(messageRecipientStatus);
            }
        }

        messageRecipientStatus = updateImpl(messageRecipientStatus, merge);

        for (ModelListener<MessageRecipientStatus> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(messageRecipientStatus);
            } else {
                listener.onAfterUpdate(messageRecipientStatus);
            }
        }

        return messageRecipientStatus;
    }

    public MessageRecipientStatus updateImpl(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge) throws SystemException {
        boolean isNew = messageRecipientStatus.isNew();

        MessageRecipientStatusModelImpl messageRecipientStatusModelImpl = (MessageRecipientStatusModelImpl) messageRecipientStatus;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messageRecipientStatus, merge);

            messageRecipientStatus.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            messageRecipientStatus.getPrimaryKey(), messageRecipientStatus);

        if (!isNew &&
                (!Validator.equals(messageRecipientStatus.getMessageId(),
                    messageRecipientStatusModelImpl.getOriginalMessageId()) ||
                !Validator.equals(messageRecipientStatus.getUserId(),
                    messageRecipientStatusModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                new Object[] {
                    messageRecipientStatusModelImpl.getOriginalMessageId(),
                    
                messageRecipientStatusModelImpl.getOriginalUserId()
                });
        }

        if (isNew ||
                (!Validator.equals(messageRecipientStatus.getMessageId(),
                    messageRecipientStatusModelImpl.getOriginalMessageId()) ||
                !Validator.equals(messageRecipientStatus.getUserId(),
                    messageRecipientStatusModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                new Object[] {
                    messageRecipientStatus.getMessageId(),
                    
                messageRecipientStatus.getUserId()
                }, messageRecipientStatus);
        }

        return messageRecipientStatus;
    }

    public MessageRecipientStatus findByPrimaryKey(Long messageRecipientId)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = fetchByPrimaryKey(messageRecipientId);

        if (messageRecipientStatus == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No MessageRecipientStatus exists with the primary key " +
                    messageRecipientId);
            }

            throw new NoSuchMessageRecipientStatusException(
                "No MessageRecipientStatus exists with the primary key " +
                messageRecipientId);
        }

        return messageRecipientStatus;
    }

    public MessageRecipientStatus fetchByPrimaryKey(Long messageRecipientId)
        throws SystemException {
        MessageRecipientStatus messageRecipientStatus = (MessageRecipientStatus) EntityCacheUtil.getResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
                MessageRecipientStatusImpl.class, messageRecipientId, this);

        if (messageRecipientStatus == null) {
            Session session = null;

            try {
                session = openSession();

                messageRecipientStatus = (MessageRecipientStatus) session.get(MessageRecipientStatusImpl.class,
                        messageRecipientId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (messageRecipientStatus != null) {
                    cacheResult(messageRecipientStatus);
                }

                closeSession(session);
            }
        }

        return messageRecipientStatus;
    }

    public List<MessageRecipientStatus> findByReceivingUser(Long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_RECEIVINGUSER,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("messageRecipientId DESC");

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
                    list = new ArrayList<MessageRecipientStatus>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_RECEIVINGUSER,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<MessageRecipientStatus> findByReceivingUser(Long userId,
        int start, int end) throws SystemException {
        return findByReceivingUser(userId, start, end, null);
    }

    public List<MessageRecipientStatus> findByReceivingUser(Long userId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                userId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_RECEIVINGUSER,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

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
                else {
                    query.append("ORDER BY ");

                    query.append("messageRecipientId DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                list = (List<MessageRecipientStatus>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessageRecipientStatus>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_RECEIVINGUSER,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public MessageRecipientStatus findByReceivingUser_First(Long userId,
        OrderByComparator obc)
        throws NoSuchMessageRecipientStatusException, SystemException {
        List<MessageRecipientStatus> list = findByReceivingUser(userId, 0, 1,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessageRecipientStatus exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MessageRecipientStatus findByReceivingUser_Last(Long userId,
        OrderByComparator obc)
        throws NoSuchMessageRecipientStatusException, SystemException {
        int count = countByReceivingUser(userId);

        List<MessageRecipientStatus> list = findByReceivingUser(userId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessageRecipientStatus exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MessageRecipientStatus[] findByReceivingUser_PrevAndNext(
        Long messageRecipientId, Long userId, OrderByComparator obc)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = findByPrimaryKey(messageRecipientId);

        int count = countByReceivingUser(userId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

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
            else {
                query.append("ORDER BY ");

                query.append("messageRecipientId DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (userId != null) {
                qPos.add(userId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    messageRecipientStatus);

            MessageRecipientStatus[] array = new MessageRecipientStatusImpl[3];

            array[0] = (MessageRecipientStatus) objArray[0];
            array[1] = (MessageRecipientStatus) objArray[1];
            array[2] = (MessageRecipientStatus) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<MessageRecipientStatus> findByReceivingUserArchived(
        Long userId, Boolean archived) throws SystemException {
        Object[] finderArgs = new Object[] { userId, archived };

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_RECEIVINGUSERARCHIVED,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (archived == null) {
                    query.append("archived IS NULL");
                } else {
                    query.append("archived = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("messageRecipientId DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (archived != null) {
                    qPos.add(archived.booleanValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessageRecipientStatus>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_RECEIVINGUSERARCHIVED,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<MessageRecipientStatus> findByReceivingUserArchived(
        Long userId, Boolean archived, int start, int end)
        throws SystemException {
        return findByReceivingUserArchived(userId, archived, start, end, null);
    }

    public List<MessageRecipientStatus> findByReceivingUserArchived(
        Long userId, Boolean archived, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                userId,
                
                archived,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_RECEIVINGUSERARCHIVED,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (archived == null) {
                    query.append("archived IS NULL");
                } else {
                    query.append("archived = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("messageRecipientId DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (archived != null) {
                    qPos.add(archived.booleanValue());
                }

                list = (List<MessageRecipientStatus>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessageRecipientStatus>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_RECEIVINGUSERARCHIVED,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public MessageRecipientStatus findByReceivingUserArchived_First(
        Long userId, Boolean archived, OrderByComparator obc)
        throws NoSuchMessageRecipientStatusException, SystemException {
        List<MessageRecipientStatus> list = findByReceivingUserArchived(userId,
                archived, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessageRecipientStatus exists with the key {");

            msg.append("userId=" + userId);

            msg.append(", ");
            msg.append("archived=" + archived);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MessageRecipientStatus findByReceivingUserArchived_Last(
        Long userId, Boolean archived, OrderByComparator obc)
        throws NoSuchMessageRecipientStatusException, SystemException {
        int count = countByReceivingUserArchived(userId, archived);

        List<MessageRecipientStatus> list = findByReceivingUserArchived(userId,
                archived, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessageRecipientStatus exists with the key {");

            msg.append("userId=" + userId);

            msg.append(", ");
            msg.append("archived=" + archived);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MessageRecipientStatus[] findByReceivingUserArchived_PrevAndNext(
        Long messageRecipientId, Long userId, Boolean archived,
        OrderByComparator obc)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = findByPrimaryKey(messageRecipientId);

        int count = countByReceivingUserArchived(userId, archived);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

            if (userId == null) {
                query.append("userId IS NULL");
            } else {
                query.append("userId = ?");
            }

            query.append(" AND ");

            if (archived == null) {
                query.append("archived IS NULL");
            } else {
                query.append("archived = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("messageRecipientId DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (userId != null) {
                qPos.add(userId.longValue());
            }

            if (archived != null) {
                qPos.add(archived.booleanValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    messageRecipientStatus);

            MessageRecipientStatus[] array = new MessageRecipientStatusImpl[3];

            array[0] = (MessageRecipientStatus) objArray[0];
            array[1] = (MessageRecipientStatus) objArray[1];
            array[2] = (MessageRecipientStatus) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<MessageRecipientStatus> findByMessageId(Long messageId)
        throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MESSAGEID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("messageRecipientId DESC");

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
                    list = new ArrayList<MessageRecipientStatus>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MESSAGEID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<MessageRecipientStatus> findByMessageId(Long messageId,
        int start, int end) throws SystemException {
        return findByMessageId(messageId, start, end, null);
    }

    public List<MessageRecipientStatus> findByMessageId(Long messageId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                messageId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_MESSAGEID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

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
                else {
                    query.append("ORDER BY ");

                    query.append("messageRecipientId DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                list = (List<MessageRecipientStatus>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessageRecipientStatus>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_MESSAGEID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public MessageRecipientStatus findByMessageId_First(Long messageId,
        OrderByComparator obc)
        throws NoSuchMessageRecipientStatusException, SystemException {
        List<MessageRecipientStatus> list = findByMessageId(messageId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessageRecipientStatus exists with the key {");

            msg.append("messageId=" + messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MessageRecipientStatus findByMessageId_Last(Long messageId,
        OrderByComparator obc)
        throws NoSuchMessageRecipientStatusException, SystemException {
        int count = countByMessageId(messageId);

        List<MessageRecipientStatus> list = findByMessageId(messageId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessageRecipientStatus exists with the key {");

            msg.append("messageId=" + messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MessageRecipientStatus[] findByMessageId_PrevAndNext(
        Long messageRecipientId, Long messageId, OrderByComparator obc)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = findByPrimaryKey(messageRecipientId);

        int count = countByMessageId(messageId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

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
            else {
                query.append("ORDER BY ");

                query.append("messageRecipientId DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (messageId != null) {
                qPos.add(messageId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    messageRecipientStatus);

            MessageRecipientStatus[] array = new MessageRecipientStatusImpl[3];

            array[0] = (MessageRecipientStatus) objArray[0];
            array[1] = (MessageRecipientStatus) objArray[1];
            array[2] = (MessageRecipientStatus) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MessageRecipientStatus findByMessageReciever(Long messageId,
        Long userId)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = fetchByMessageReciever(messageId,
                userId);

        if (messageRecipientStatus == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessageRecipientStatus exists with the key {");

            msg.append("messageId=" + messageId);

            msg.append(", ");
            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        }

        return messageRecipientStatus;
    }

    public MessageRecipientStatus fetchByMessageReciever(Long messageId,
        Long userId) throws SystemException {
        return fetchByMessageReciever(messageId, userId, true);
    }

    public MessageRecipientStatus fetchByMessageReciever(Long messageId,
        Long userId, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { messageId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" AND ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("messageRecipientId DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                List<MessageRecipientStatus> list = q.list();

                result = list;

                MessageRecipientStatus messageRecipientStatus = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                        finderArgs, list);
                } else {
                    messageRecipientStatus = list.get(0);

                    cacheResult(messageRecipientStatus);

                    if ((messageRecipientStatus.getMessageId() == null) ||
                            !messageRecipientStatus.getMessageId()
                                                       .equals(messageId) ||
                            (messageRecipientStatus.getUserId() == null) ||
                            !messageRecipientStatus.getUserId().equals(userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                            finderArgs, messageRecipientStatus);
                    }
                }

                return messageRecipientStatus;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                        finderArgs, new ArrayList<MessageRecipientStatus>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (MessageRecipientStatus) result;
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

    public List<MessageRecipientStatus> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MessageRecipientStatus> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MessageRecipientStatus> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("messageRecipientId DESC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MessageRecipientStatus>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessageRecipientStatus>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessageRecipientStatus>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByReceivingUser(Long userId) throws SystemException {
        for (MessageRecipientStatus messageRecipientStatus : findByReceivingUser(
                userId)) {
            remove(messageRecipientStatus);
        }
    }

    public void removeByReceivingUserArchived(Long userId, Boolean archived)
        throws SystemException {
        for (MessageRecipientStatus messageRecipientStatus : findByReceivingUserArchived(
                userId, archived)) {
            remove(messageRecipientStatus);
        }
    }

    public void removeByMessageId(Long messageId) throws SystemException {
        for (MessageRecipientStatus messageRecipientStatus : findByMessageId(
                messageId)) {
            remove(messageRecipientStatus);
        }
    }

    public void removeByMessageReciever(Long messageId, Long userId)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = findByMessageReciever(messageId,
                userId);

        remove(messageRecipientStatus);
    }

    public void removeAll() throws SystemException {
        for (MessageRecipientStatus messageRecipientStatus : findAll()) {
            remove(messageRecipientStatus);
        }
    }

    public int countByReceivingUser(Long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RECEIVINGUSER,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RECEIVINGUSER,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByReceivingUserArchived(Long userId, Boolean archived)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId, archived };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RECEIVINGUSERARCHIVED,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (archived == null) {
                    query.append("archived IS NULL");
                } else {
                    query.append("archived = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (archived != null) {
                    qPos.add(archived.booleanValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RECEIVINGUSERARCHIVED,
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
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

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

    public int countByMessageReciever(Long messageId, Long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { messageId, userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MESSAGERECIEVER,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.messaging.model.MessageRecipientStatus WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
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

                if (messageId != null) {
                    qPos.add(messageId.longValue());
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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MESSAGERECIEVER,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.messaging.model.MessageRecipientStatus");

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
                        "value.object.listener.com.ext.portlet.messaging.model.MessageRecipientStatus")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessageRecipientStatus>> listenersList = new ArrayList<ModelListener<MessageRecipientStatus>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessageRecipientStatus>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
