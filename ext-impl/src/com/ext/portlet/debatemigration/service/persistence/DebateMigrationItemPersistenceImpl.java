package com.ext.portlet.debatemigration.service.persistence;

import com.ext.portlet.debatemigration.NoSuchDebateMigrationItemException;
import com.ext.portlet.debatemigration.model.DebateMigrationItem;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationItemImpl;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationItemModelImpl;

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


public class DebateMigrationItemPersistenceImpl extends BasePersistenceImpl
    implements DebateMigrationItemPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateMigrationItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_DEBATEMIGRATIONITEM = new FinderPath(DebateMigrationItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByDebateMigrationItem",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_DEBATEMIGRATIONITEM = new FinderPath(DebateMigrationItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByDebateMigrationItem",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateMigrationItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateMigrationItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateMigrationItemPersistenceImpl.class);
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

    public void cacheResult(DebateMigrationItem debateMigrationItem) {
        EntityCacheUtil.putResult(DebateMigrationItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationItemImpl.class, debateMigrationItem.getPrimaryKey(),
            debateMigrationItem);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMIGRATIONITEM,
            new Object[] {
                debateMigrationItem.getDebateMigrationId(),
                
            debateMigrationItem.getOldMBMessageId()
            }, debateMigrationItem);
    }

    public void cacheResult(List<DebateMigrationItem> debateMigrationItems) {
        for (DebateMigrationItem debateMigrationItem : debateMigrationItems) {
            if (EntityCacheUtil.getResult(
                        DebateMigrationItemModelImpl.ENTITY_CACHE_ENABLED,
                        DebateMigrationItemImpl.class,
                        debateMigrationItem.getPrimaryKey(), this) == null) {
                cacheResult(debateMigrationItem);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateMigrationItemImpl.class.getName());
        EntityCacheUtil.clearCache(DebateMigrationItemImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateMigrationItem create(Long debateMigrationItemPK) {
        DebateMigrationItem debateMigrationItem = new DebateMigrationItemImpl();

        debateMigrationItem.setNew(true);
        debateMigrationItem.setPrimaryKey(debateMigrationItemPK);

        return debateMigrationItem;
    }

    public DebateMigrationItem remove(Long debateMigrationItemPK)
        throws NoSuchDebateMigrationItemException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateMigrationItem debateMigrationItem = (DebateMigrationItem) session.get(DebateMigrationItemImpl.class,
                    debateMigrationItemPK);

            if (debateMigrationItem == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DebateMigrationItem exists with the primary key " +
                        debateMigrationItemPK);
                }

                throw new NoSuchDebateMigrationItemException(
                    "No DebateMigrationItem exists with the primary key " +
                    debateMigrationItemPK);
            }

            return remove(debateMigrationItem);
        } catch (NoSuchDebateMigrationItemException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateMigrationItem remove(DebateMigrationItem debateMigrationItem)
        throws SystemException {
        for (ModelListener<DebateMigrationItem> listener : listeners) {
            listener.onBeforeRemove(debateMigrationItem);
        }

        debateMigrationItem = removeImpl(debateMigrationItem);

        for (ModelListener<DebateMigrationItem> listener : listeners) {
            listener.onAfterRemove(debateMigrationItem);
        }

        return debateMigrationItem;
    }

    protected DebateMigrationItem removeImpl(
        DebateMigrationItem debateMigrationItem) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateMigrationItem.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateMigrationItemImpl.class,
                        debateMigrationItem.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateMigrationItem);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        DebateMigrationItemModelImpl debateMigrationItemModelImpl = (DebateMigrationItemModelImpl) debateMigrationItem;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEBATEMIGRATIONITEM,
            new Object[] {
                debateMigrationItemModelImpl.getOriginalDebateMigrationId(),
                
            debateMigrationItemModelImpl.getOriginalOldMBMessageId()
            });

        EntityCacheUtil.removeResult(DebateMigrationItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationItemImpl.class, debateMigrationItem.getPrimaryKey());

        return debateMigrationItem;
    }

    /**
     * @deprecated Use <code>update(DebateMigrationItem debateMigrationItem, boolean merge)</code>.
     */
    public DebateMigrationItem update(DebateMigrationItem debateMigrationItem)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateMigrationItem debateMigrationItem) method. Use update(DebateMigrationItem debateMigrationItem, boolean merge) instead.");
        }

        return update(debateMigrationItem, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateMigrationItem update(DebateMigrationItem debateMigrationItem,
        boolean merge) throws SystemException {
        boolean isNew = debateMigrationItem.isNew();

        for (ModelListener<DebateMigrationItem> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateMigrationItem);
            } else {
                listener.onBeforeUpdate(debateMigrationItem);
            }
        }

        debateMigrationItem = updateImpl(debateMigrationItem, merge);

        for (ModelListener<DebateMigrationItem> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateMigrationItem);
            } else {
                listener.onAfterUpdate(debateMigrationItem);
            }
        }

        return debateMigrationItem;
    }

    public DebateMigrationItem updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem,
        boolean merge) throws SystemException {
        boolean isNew = debateMigrationItem.isNew();

        DebateMigrationItemModelImpl debateMigrationItemModelImpl = (DebateMigrationItemModelImpl) debateMigrationItem;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateMigrationItem, merge);

            debateMigrationItem.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateMigrationItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationItemImpl.class, debateMigrationItem.getPrimaryKey(),
            debateMigrationItem);

        if (!isNew &&
                (!Validator.equals(debateMigrationItem.getDebateMigrationId(),
                    debateMigrationItemModelImpl.getOriginalDebateMigrationId()) ||
                !Validator.equals(debateMigrationItem.getOldMBMessageId(),
                    debateMigrationItemModelImpl.getOriginalOldMBMessageId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEBATEMIGRATIONITEM,
                new Object[] {
                    debateMigrationItemModelImpl.getOriginalDebateMigrationId(),
                    
                debateMigrationItemModelImpl.getOriginalOldMBMessageId()
                });
        }

        if (isNew ||
                (!Validator.equals(debateMigrationItem.getDebateMigrationId(),
                    debateMigrationItemModelImpl.getOriginalDebateMigrationId()) ||
                !Validator.equals(debateMigrationItem.getOldMBMessageId(),
                    debateMigrationItemModelImpl.getOriginalOldMBMessageId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMIGRATIONITEM,
                new Object[] {
                    debateMigrationItem.getDebateMigrationId(),
                    
                debateMigrationItem.getOldMBMessageId()
                }, debateMigrationItem);
        }

        return debateMigrationItem;
    }

    public DebateMigrationItem findByPrimaryKey(Long debateMigrationItemPK)
        throws NoSuchDebateMigrationItemException, SystemException {
        DebateMigrationItem debateMigrationItem = fetchByPrimaryKey(debateMigrationItemPK);

        if (debateMigrationItem == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateMigrationItem exists with the primary key " +
                    debateMigrationItemPK);
            }

            throw new NoSuchDebateMigrationItemException(
                "No DebateMigrationItem exists with the primary key " +
                debateMigrationItemPK);
        }

        return debateMigrationItem;
    }

    public DebateMigrationItem fetchByPrimaryKey(Long debateMigrationItemPK)
        throws SystemException {
        DebateMigrationItem debateMigrationItem = (DebateMigrationItem) EntityCacheUtil.getResult(DebateMigrationItemModelImpl.ENTITY_CACHE_ENABLED,
                DebateMigrationItemImpl.class, debateMigrationItemPK, this);

        if (debateMigrationItem == null) {
            Session session = null;

            try {
                session = openSession();

                debateMigrationItem = (DebateMigrationItem) session.get(DebateMigrationItemImpl.class,
                        debateMigrationItemPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateMigrationItem != null) {
                    cacheResult(debateMigrationItem);
                }

                closeSession(session);
            }
        }

        return debateMigrationItem;
    }

    public DebateMigrationItem findByDebateMigrationItem(
        Long debateMigrationId, Long oldMBMessageId)
        throws NoSuchDebateMigrationItemException, SystemException {
        DebateMigrationItem debateMigrationItem = fetchByDebateMigrationItem(debateMigrationId,
                oldMBMessageId);

        if (debateMigrationItem == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateMigrationItem exists with the key {");

            msg.append("debateMigrationId=" + debateMigrationId);

            msg.append(", ");
            msg.append("oldMBMessageId=" + oldMBMessageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDebateMigrationItemException(msg.toString());
        }

        return debateMigrationItem;
    }

    public DebateMigrationItem fetchByDebateMigrationItem(
        Long debateMigrationId, Long oldMBMessageId) throws SystemException {
        return fetchByDebateMigrationItem(debateMigrationId, oldMBMessageId,
            true);
    }

    public DebateMigrationItem fetchByDebateMigrationItem(
        Long debateMigrationId, Long oldMBMessageId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateMigrationId, oldMBMessageId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DEBATEMIGRATIONITEM,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debatemigration.model.DebateMigrationItem WHERE ");

                if (debateMigrationId == null) {
                    query.append("debateMigrationId IS NULL");
                } else {
                    query.append("debateMigrationId = ?");
                }

                query.append(" AND ");

                if (oldMBMessageId == null) {
                    query.append("oldMBMessageId IS NULL");
                } else {
                    query.append("oldMBMessageId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateMigrationId != null) {
                    qPos.add(debateMigrationId.longValue());
                }

                if (oldMBMessageId != null) {
                    qPos.add(oldMBMessageId.longValue());
                }

                List<DebateMigrationItem> list = q.list();

                result = list;

                DebateMigrationItem debateMigrationItem = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMIGRATIONITEM,
                        finderArgs, list);
                } else {
                    debateMigrationItem = list.get(0);

                    cacheResult(debateMigrationItem);

                    if ((debateMigrationItem.getDebateMigrationId() == null) ||
                            !debateMigrationItem.getDebateMigrationId()
                                                    .equals(debateMigrationId) ||
                            (debateMigrationItem.getOldMBMessageId() == null) ||
                            !debateMigrationItem.getOldMBMessageId()
                                                    .equals(oldMBMessageId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMIGRATIONITEM,
                            finderArgs, debateMigrationItem);
                    }
                }

                return debateMigrationItem;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEMIGRATIONITEM,
                        finderArgs, new ArrayList<DebateMigrationItem>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (DebateMigrationItem) result;
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

    public List<DebateMigrationItem> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateMigrationItem> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateMigrationItem> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateMigrationItem> list = (List<DebateMigrationItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debatemigration.model.DebateMigrationItem ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateMigrationItem>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateMigrationItem>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateMigrationItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByDebateMigrationItem(Long debateMigrationId,
        Long oldMBMessageId)
        throws NoSuchDebateMigrationItemException, SystemException {
        DebateMigrationItem debateMigrationItem = findByDebateMigrationItem(debateMigrationId,
                oldMBMessageId);

        remove(debateMigrationItem);
    }

    public void removeAll() throws SystemException {
        for (DebateMigrationItem debateMigrationItem : findAll()) {
            remove(debateMigrationItem);
        }
    }

    public int countByDebateMigrationItem(Long debateMigrationId,
        Long oldMBMessageId) throws SystemException {
        Object[] finderArgs = new Object[] { debateMigrationId, oldMBMessageId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEBATEMIGRATIONITEM,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debatemigration.model.DebateMigrationItem WHERE ");

                if (debateMigrationId == null) {
                    query.append("debateMigrationId IS NULL");
                } else {
                    query.append("debateMigrationId = ?");
                }

                query.append(" AND ");

                if (oldMBMessageId == null) {
                    query.append("oldMBMessageId IS NULL");
                } else {
                    query.append("oldMBMessageId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateMigrationId != null) {
                    qPos.add(debateMigrationId.longValue());
                }

                if (oldMBMessageId != null) {
                    qPos.add(oldMBMessageId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEBATEMIGRATIONITEM,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debatemigration.model.DebateMigrationItem");

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
                        "value.object.listener.com.ext.portlet.debatemigration.model.DebateMigrationItem")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateMigrationItem>> listenersList = new ArrayList<ModelListener<DebateMigrationItem>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateMigrationItem>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
