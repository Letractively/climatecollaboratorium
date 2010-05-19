package com.ext.inlinehelp.service.persistence;

import com.ext.inlinehelp.NoSuchHelpUserSettingException;
import com.ext.inlinehelp.model.HelpUserSetting;
import com.ext.inlinehelp.model.impl.HelpUserSettingImpl;
import com.ext.inlinehelp.model.impl.HelpUserSettingModelImpl;

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


public class HelpUserSettingPersistenceImpl extends BasePersistenceImpl
    implements HelpUserSettingPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = HelpUserSettingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_USERIDMESSAGEID = new FinderPath(HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
            HelpUserSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByUserIdMessageId",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERIDMESSAGEID = new FinderPath(HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
            HelpUserSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByUserIdMessageId",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_USERIDMESSAGEID = new FinderPath(HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
            HelpUserSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByUserIdMessageId",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
            HelpUserSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
            HelpUserSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(HelpUserSettingPersistenceImpl.class);
    @BeanReference(name = "com.ext.inlinehelp.service.persistence.HelpUserSettingPersistence.impl")
    protected com.ext.inlinehelp.service.persistence.HelpUserSettingPersistence helpUserSettingPersistence;

    public void cacheResult(HelpUserSetting helpUserSetting) {
        EntityCacheUtil.putResult(HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
            HelpUserSettingImpl.class, helpUserSetting.getPrimaryKey(),
            helpUserSetting);
    }

    public void cacheResult(List<HelpUserSetting> helpUserSettings) {
        for (HelpUserSetting helpUserSetting : helpUserSettings) {
            if (EntityCacheUtil.getResult(
                        HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
                        HelpUserSettingImpl.class,
                        helpUserSetting.getPrimaryKey(), this) == null) {
                cacheResult(helpUserSetting);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(HelpUserSettingImpl.class.getName());
        EntityCacheUtil.clearCache(HelpUserSettingImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public HelpUserSetting create(Long HelpUserSettingId) {
        HelpUserSetting helpUserSetting = new HelpUserSettingImpl();

        helpUserSetting.setNew(true);
        helpUserSetting.setPrimaryKey(HelpUserSettingId);

        return helpUserSetting;
    }

    public HelpUserSetting remove(Long HelpUserSettingId)
        throws NoSuchHelpUserSettingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HelpUserSetting helpUserSetting = (HelpUserSetting) session.get(HelpUserSettingImpl.class,
                    HelpUserSettingId);

            if (helpUserSetting == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No HelpUserSetting exists with the primary key " +
                        HelpUserSettingId);
                }

                throw new NoSuchHelpUserSettingException(
                    "No HelpUserSetting exists with the primary key " +
                    HelpUserSettingId);
            }

            return remove(helpUserSetting);
        } catch (NoSuchHelpUserSettingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public HelpUserSetting remove(HelpUserSetting helpUserSetting)
        throws SystemException {
        for (ModelListener<HelpUserSetting> listener : listeners) {
            listener.onBeforeRemove(helpUserSetting);
        }

        helpUserSetting = removeImpl(helpUserSetting);

        for (ModelListener<HelpUserSetting> listener : listeners) {
            listener.onAfterRemove(helpUserSetting);
        }

        return helpUserSetting;
    }

    protected HelpUserSetting removeImpl(HelpUserSetting helpUserSetting)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (helpUserSetting.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(HelpUserSettingImpl.class,
                        helpUserSetting.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(helpUserSetting);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
            HelpUserSettingImpl.class, helpUserSetting.getPrimaryKey());

        return helpUserSetting;
    }

    /**
     * @deprecated Use <code>update(HelpUserSetting helpUserSetting, boolean merge)</code>.
     */
    public HelpUserSetting update(HelpUserSetting helpUserSetting)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(HelpUserSetting helpUserSetting) method. Use update(HelpUserSetting helpUserSetting, boolean merge) instead.");
        }

        return update(helpUserSetting, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                helpUserSetting the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when helpUserSetting is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public HelpUserSetting update(HelpUserSetting helpUserSetting, boolean merge)
        throws SystemException {
        boolean isNew = helpUserSetting.isNew();

        for (ModelListener<HelpUserSetting> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(helpUserSetting);
            } else {
                listener.onBeforeUpdate(helpUserSetting);
            }
        }

        helpUserSetting = updateImpl(helpUserSetting, merge);

        for (ModelListener<HelpUserSetting> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(helpUserSetting);
            } else {
                listener.onAfterUpdate(helpUserSetting);
            }
        }

        return helpUserSetting;
    }

    public HelpUserSetting updateImpl(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, helpUserSetting, merge);

            helpUserSetting.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
            HelpUserSettingImpl.class, helpUserSetting.getPrimaryKey(),
            helpUserSetting);

        return helpUserSetting;
    }

    public HelpUserSetting findByPrimaryKey(Long HelpUserSettingId)
        throws NoSuchHelpUserSettingException, SystemException {
        HelpUserSetting helpUserSetting = fetchByPrimaryKey(HelpUserSettingId);

        if (helpUserSetting == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No HelpUserSetting exists with the primary key " +
                    HelpUserSettingId);
            }

            throw new NoSuchHelpUserSettingException(
                "No HelpUserSetting exists with the primary key " +
                HelpUserSettingId);
        }

        return helpUserSetting;
    }

    public HelpUserSetting fetchByPrimaryKey(Long HelpUserSettingId)
        throws SystemException {
        HelpUserSetting helpUserSetting = (HelpUserSetting) EntityCacheUtil.getResult(HelpUserSettingModelImpl.ENTITY_CACHE_ENABLED,
                HelpUserSettingImpl.class, HelpUserSettingId, this);

        if (helpUserSetting == null) {
            Session session = null;

            try {
                session = openSession();

                helpUserSetting = (HelpUserSetting) session.get(HelpUserSettingImpl.class,
                        HelpUserSettingId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (helpUserSetting != null) {
                    cacheResult(helpUserSetting);
                }

                closeSession(session);
            }
        }

        return helpUserSetting;
    }

    public List<HelpUserSetting> findByUserIdMessageId(Long userId,
        String messageId) throws SystemException {
        Object[] finderArgs = new Object[] { userId, messageId };

        List<HelpUserSetting> list = (List<HelpUserSetting>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERIDMESSAGEID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.inlinehelp.model.HelpUserSetting WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (messageId != null) {
                    qPos.add(messageId);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<HelpUserSetting>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERIDMESSAGEID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<HelpUserSetting> findByUserIdMessageId(Long userId,
        String messageId, int start, int end) throws SystemException {
        return findByUserIdMessageId(userId, messageId, start, end, null);
    }

    public List<HelpUserSetting> findByUserIdMessageId(Long userId,
        String messageId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                userId,
                
                messageId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<HelpUserSetting> list = (List<HelpUserSetting>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERIDMESSAGEID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.inlinehelp.model.HelpUserSetting WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

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

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (messageId != null) {
                    qPos.add(messageId);
                }

                list = (List<HelpUserSetting>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<HelpUserSetting>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERIDMESSAGEID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public HelpUserSetting findByUserIdMessageId_First(Long userId,
        String messageId, OrderByComparator obc)
        throws NoSuchHelpUserSettingException, SystemException {
        List<HelpUserSetting> list = findByUserIdMessageId(userId, messageId,
                0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No HelpUserSetting exists with the key {");

            msg.append("userId=" + userId);

            msg.append(", ");
            msg.append("messageId=" + messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchHelpUserSettingException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public HelpUserSetting findByUserIdMessageId_Last(Long userId,
        String messageId, OrderByComparator obc)
        throws NoSuchHelpUserSettingException, SystemException {
        int count = countByUserIdMessageId(userId, messageId);

        List<HelpUserSetting> list = findByUserIdMessageId(userId, messageId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No HelpUserSetting exists with the key {");

            msg.append("userId=" + userId);

            msg.append(", ");
            msg.append("messageId=" + messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchHelpUserSettingException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public HelpUserSetting[] findByUserIdMessageId_PrevAndNext(
        Long HelpUserSettingId, Long userId, String messageId,
        OrderByComparator obc)
        throws NoSuchHelpUserSettingException, SystemException {
        HelpUserSetting helpUserSetting = findByPrimaryKey(HelpUserSettingId);

        int count = countByUserIdMessageId(userId, messageId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.inlinehelp.model.HelpUserSetting WHERE ");

            if (userId == null) {
                query.append("userId IS NULL");
            } else {
                query.append("userId = ?");
            }

            query.append(" AND ");

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

            if (userId != null) {
                qPos.add(userId.longValue());
            }

            if (messageId != null) {
                qPos.add(messageId);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    helpUserSetting);

            HelpUserSetting[] array = new HelpUserSettingImpl[3];

            array[0] = (HelpUserSetting) objArray[0];
            array[1] = (HelpUserSetting) objArray[1];
            array[2] = (HelpUserSetting) objArray[2];

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

    public List<HelpUserSetting> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<HelpUserSetting> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<HelpUserSetting> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<HelpUserSetting> list = (List<HelpUserSetting>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.inlinehelp.model.HelpUserSetting ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<HelpUserSetting>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<HelpUserSetting>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<HelpUserSetting>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByUserIdMessageId(Long userId, String messageId)
        throws SystemException {
        for (HelpUserSetting helpUserSetting : findByUserIdMessageId(userId,
                messageId)) {
            remove(helpUserSetting);
        }
    }

    public void removeAll() throws SystemException {
        for (HelpUserSetting helpUserSetting : findAll()) {
            remove(helpUserSetting);
        }
    }

    public int countByUserIdMessageId(Long userId, String messageId)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId, messageId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERIDMESSAGEID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.inlinehelp.model.HelpUserSetting WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (messageId != null) {
                    qPos.add(messageId);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERIDMESSAGEID,
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
                        "SELECT COUNT(*) FROM com.ext.inlinehelp.model.HelpUserSetting");

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
                        "value.object.listener.com.ext.inlinehelp.model.HelpUserSetting")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HelpUserSetting>> listenersList = new ArrayList<ModelListener<HelpUserSetting>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HelpUserSetting>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
