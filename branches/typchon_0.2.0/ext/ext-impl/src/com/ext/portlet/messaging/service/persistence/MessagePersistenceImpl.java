package com.ext.portlet.messaging.service.persistence;

import com.ext.portlet.messaging.NoSuchMessageException;
import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.model.impl.MessageImpl;
import com.ext.portlet.messaging.model.impl.MessageModelImpl;

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


public class MessagePersistenceImpl extends BasePersistenceImpl
    implements MessagePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MessageImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_SENDINGUSER = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
            MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBySendingUser", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_SENDINGUSER = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
            MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBySendingUser",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_SENDINGUSER = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
            MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countBySendingUser", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
            MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
            MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MessagePersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessagePersistence.impl")
    protected com.ext.portlet.messaging.service.persistence.MessagePersistence messagePersistence;
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessageRecipientStatusPersistence.impl")
    protected com.ext.portlet.messaging.service.persistence.MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessagingUserPreferencesPersistence.impl")
    protected com.ext.portlet.messaging.service.persistence.MessagingUserPreferencesPersistence messagingUserPreferencesPersistence;
    @BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence.impl")
    protected com.liferay.portal.service.persistence.UserPersistence userPersistence;

    public void cacheResult(Message message) {
        EntityCacheUtil.putResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
            MessageImpl.class, message.getPrimaryKey(), message);
    }

    public void cacheResult(List<Message> messages) {
        for (Message message : messages) {
            if (EntityCacheUtil.getResult(
                        MessageModelImpl.ENTITY_CACHE_ENABLED,
                        MessageImpl.class, message.getPrimaryKey(), this) == null) {
                cacheResult(message);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MessageImpl.class.getName());
        EntityCacheUtil.clearCache(MessageImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public Message create(Long messageId) {
        Message message = new MessageImpl();

        message.setNew(true);
        message.setPrimaryKey(messageId);

        return message;
    }

    public Message remove(Long messageId)
        throws NoSuchMessageException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Message message = (Message) session.get(MessageImpl.class, messageId);

            if (message == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No Message exists with the primary key " +
                        messageId);
                }

                throw new NoSuchMessageException(
                    "No Message exists with the primary key " + messageId);
            }

            return remove(message);
        } catch (NoSuchMessageException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public Message remove(Message message) throws SystemException {
        for (ModelListener<Message> listener : listeners) {
            listener.onBeforeRemove(message);
        }

        message = removeImpl(message);

        for (ModelListener<Message> listener : listeners) {
            listener.onAfterRemove(message);
        }

        return message;
    }

    protected Message removeImpl(Message message) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (message.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MessageImpl.class,
                        message.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(message);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
            MessageImpl.class, message.getPrimaryKey());

        return message;
    }

    /**
     * @deprecated Use <code>update(Message message, boolean merge)</code>.
     */
    public Message update(Message message) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(Message message) method. Use update(Message message, boolean merge) instead.");
        }

        return update(message, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                message the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when message is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public Message update(Message message, boolean merge)
        throws SystemException {
        boolean isNew = message.isNew();

        for (ModelListener<Message> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(message);
            } else {
                listener.onBeforeUpdate(message);
            }
        }

        message = updateImpl(message, merge);

        for (ModelListener<Message> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(message);
            } else {
                listener.onAfterUpdate(message);
            }
        }

        return message;
    }

    public Message updateImpl(com.ext.portlet.messaging.model.Message message,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, message, merge);

            message.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
            MessageImpl.class, message.getPrimaryKey(), message);

        return message;
    }

    public Message findByPrimaryKey(Long messageId)
        throws NoSuchMessageException, SystemException {
        Message message = fetchByPrimaryKey(messageId);

        if (message == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No Message exists with the primary key " +
                    messageId);
            }

            throw new NoSuchMessageException(
                "No Message exists with the primary key " + messageId);
        }

        return message;
    }

    public Message fetchByPrimaryKey(Long messageId) throws SystemException {
        Message message = (Message) EntityCacheUtil.getResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
                MessageImpl.class, messageId, this);

        if (message == null) {
            Session session = null;

            try {
                session = openSession();

                message = (Message) session.get(MessageImpl.class, messageId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (message != null) {
                    cacheResult(message);
                }

                closeSession(session);
            }
        }

        return message;
    }

    public List<Message> findBySendingUser(Long fromId)
        throws SystemException {
        Object[] finderArgs = new Object[] { fromId };

        List<Message> list = (List<Message>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SENDINGUSER,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.Message WHERE ");

                if (fromId == null) {
                    query.append("fromId IS NULL");
                } else {
                    query.append("fromId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("createDate DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (fromId != null) {
                    qPos.add(fromId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Message>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SENDINGUSER,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<Message> findBySendingUser(Long fromId, int start, int end)
        throws SystemException {
        return findBySendingUser(fromId, start, end, null);
    }

    public List<Message> findBySendingUser(Long fromId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                fromId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Message> list = (List<Message>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_SENDINGUSER,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.Message WHERE ");

                if (fromId == null) {
                    query.append("fromId IS NULL");
                } else {
                    query.append("fromId = ?");
                }

                query.append(" ");

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

                if (fromId != null) {
                    qPos.add(fromId.longValue());
                }

                list = (List<Message>) QueryUtil.list(q, getDialect(), start,
                        end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Message>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_SENDINGUSER,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public Message findBySendingUser_First(Long fromId, OrderByComparator obc)
        throws NoSuchMessageException, SystemException {
        List<Message> list = findBySendingUser(fromId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Message exists with the key {");

            msg.append("fromId=" + fromId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Message findBySendingUser_Last(Long fromId, OrderByComparator obc)
        throws NoSuchMessageException, SystemException {
        int count = countBySendingUser(fromId);

        List<Message> list = findBySendingUser(fromId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Message exists with the key {");

            msg.append("fromId=" + fromId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Message[] findBySendingUser_PrevAndNext(Long messageId, Long fromId,
        OrderByComparator obc) throws NoSuchMessageException, SystemException {
        Message message = findByPrimaryKey(messageId);

        int count = countBySendingUser(fromId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.messaging.model.Message WHERE ");

            if (fromId == null) {
                query.append("fromId IS NULL");
            } else {
                query.append("fromId = ?");
            }

            query.append(" ");

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

            if (fromId != null) {
                qPos.add(fromId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, message);

            Message[] array = new MessageImpl[3];

            array[0] = (Message) objArray[0];
            array[1] = (Message) objArray[1];
            array[2] = (Message) objArray[2];

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

    public List<Message> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<Message> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<Message> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Message> list = (List<Message>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.messaging.model.Message ");

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
                    list = (List<Message>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Message>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Message>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeBySendingUser(Long fromId) throws SystemException {
        for (Message message : findBySendingUser(fromId)) {
            remove(message);
        }
    }

    public void removeAll() throws SystemException {
        for (Message message : findAll()) {
            remove(message);
        }
    }

    public int countBySendingUser(Long fromId) throws SystemException {
        Object[] finderArgs = new Object[] { fromId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SENDINGUSER,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.messaging.model.Message WHERE ");

                if (fromId == null) {
                    query.append("fromId IS NULL");
                } else {
                    query.append("fromId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (fromId != null) {
                    qPos.add(fromId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SENDINGUSER,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.messaging.model.Message");

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
                        "value.object.listener.com.ext.portlet.messaging.model.Message")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Message>> listenersList = new ArrayList<ModelListener<Message>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Message>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
