package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.NoSuchDebateItemException;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.impl.DebateItemImpl;
import com.ext.portlet.debaterevision.model.impl.DebateItemModelImpl;

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
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class DebateItemPersistenceImpl extends BasePersistenceImpl
    implements DebateItemPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_ACTIVEIDINTREEVERSION = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByactiveIdInTreeVersion",
            new String[] {
                Long.class.getName(), Long.class.getName(), Date.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ACTIVEIDINTREEVERSION =
        new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByactiveIdInTreeVersion",
            new String[] {
                Long.class.getName(), Long.class.getName(), Date.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEIDINTREEVERSION = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByactiveIdInTreeVersion",
            new String[] {
                Long.class.getName(), Long.class.getName(), Date.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_BYIDSTATUS = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBybyIdStatus",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_BYIDSTATUS = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBybyIdStatus",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_BYIDSTATUS = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countBybyIdStatus",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_BYIDVERSION = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBybyIdVersion",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_BYIDVERSION = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findBybyIdVersion",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_BYIDVERSION = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countBybyIdVersion",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateItemPersistenceImpl.class);
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

    public void cacheResult(DebateItem debateItem) {
        EntityCacheUtil.putResult(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemImpl.class, debateItem.getPrimaryKey(), debateItem);
    }

    public void cacheResult(List<DebateItem> debateItems) {
        for (DebateItem debateItem : debateItems) {
            if (EntityCacheUtil.getResult(
                        DebateItemModelImpl.ENTITY_CACHE_ENABLED,
                        DebateItemImpl.class, debateItem.getPrimaryKey(), this) == null) {
                cacheResult(debateItem);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateItemImpl.class.getName());
        EntityCacheUtil.clearCache(DebateItemImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateItem create(Long debateItemPK) {
        DebateItem debateItem = new DebateItemImpl();

        debateItem.setNew(true);
        debateItem.setPrimaryKey(debateItemPK);

        return debateItem;
    }

    public DebateItem remove(Long debateItemPK)
        throws NoSuchDebateItemException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateItem debateItem = (DebateItem) session.get(DebateItemImpl.class,
                    debateItemPK);

            if (debateItem == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No DebateItem exists with the primary key " +
                        debateItemPK);
                }

                throw new NoSuchDebateItemException(
                    "No DebateItem exists with the primary key " +
                    debateItemPK);
            }

            return remove(debateItem);
        } catch (NoSuchDebateItemException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateItem remove(DebateItem debateItem) throws SystemException {
        for (ModelListener<DebateItem> listener : listeners) {
            listener.onBeforeRemove(debateItem);
        }

        debateItem = removeImpl(debateItem);

        for (ModelListener<DebateItem> listener : listeners) {
            listener.onAfterRemove(debateItem);
        }

        return debateItem;
    }

    protected DebateItem removeImpl(DebateItem debateItem)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateItem.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateItemImpl.class,
                        debateItem.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateItem);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemImpl.class, debateItem.getPrimaryKey());

        return debateItem;
    }

    /**
     * @deprecated Use <code>update(DebateItem debateItem, boolean merge)</code>.
     */
    public DebateItem update(DebateItem debateItem) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateItem debateItem) method. Use update(DebateItem debateItem, boolean merge) instead.");
        }

        return update(debateItem, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateItem update(DebateItem debateItem, boolean merge)
        throws SystemException {
        boolean isNew = debateItem.isNew();

        for (ModelListener<DebateItem> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateItem);
            } else {
                listener.onBeforeUpdate(debateItem);
            }
        }

        debateItem = updateImpl(debateItem, merge);

        for (ModelListener<DebateItem> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateItem);
            } else {
                listener.onAfterUpdate(debateItem);
            }
        }

        return debateItem;
    }

    public DebateItem updateImpl(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateItem, merge);

            debateItem.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemImpl.class, debateItem.getPrimaryKey(), debateItem);

        return debateItem;
    }

    public DebateItem findByPrimaryKey(Long debateItemPK)
        throws NoSuchDebateItemException, SystemException {
        DebateItem debateItem = fetchByPrimaryKey(debateItemPK);

        if (debateItem == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateItem exists with the primary key " +
                    debateItemPK);
            }

            throw new NoSuchDebateItemException(
                "No DebateItem exists with the primary key " + debateItemPK);
        }

        return debateItem;
    }

    public DebateItem fetchByPrimaryKey(Long debateItemPK)
        throws SystemException {
        DebateItem debateItem = (DebateItem) EntityCacheUtil.getResult(DebateItemModelImpl.ENTITY_CACHE_ENABLED,
                DebateItemImpl.class, debateItemPK, this);

        if (debateItem == null) {
            Session session = null;

            try {
                session = openSession();

                debateItem = (DebateItem) session.get(DebateItemImpl.class,
                        debateItemPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateItem != null) {
                    cacheResult(debateItem);
                }

                closeSession(session);
            }
        }

        return debateItem;
    }

    public List<DebateItem> findByactiveIdInTreeVersion(Long debateItemId,
        Long treeVersion, Date updated) throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, treeVersion, updated };

        List<DebateItem> list = (List<DebateItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ACTIVEIDINTREEVERSION,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (treeVersion == null) {
                    query.append("treeVersion <= null");
                } else {
                    query.append("treeVersion <= ?");
                }

                query.append(" AND ");

                if (updated == null) {
                    query.append("updated <= null");
                } else {
                    query.append("updated <= ?");
                }

                query.append(" AND status LIKE 'ACTIVE' ");

                query.append("ORDER BY ");

                query.append("itemVersion ASC, ");
                query.append("weight ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (treeVersion != null) {
                    qPos.add(treeVersion.longValue());
                }

                if (updated != null) {
                    qPos.add(CalendarUtil.getTimestamp(updated));
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ACTIVEIDINTREEVERSION,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DebateItem> findByactiveIdInTreeVersion(Long debateItemId,
        Long treeVersion, Date updated, int start, int end)
        throws SystemException {
        return findByactiveIdInTreeVersion(debateItemId, treeVersion, updated,
            start, end, null);
    }

    public List<DebateItem> findByactiveIdInTreeVersion(Long debateItemId,
        Long treeVersion, Date updated, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                debateItemId,
                
                treeVersion,
                
                updated,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItem> list = (List<DebateItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ACTIVEIDINTREEVERSION,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (treeVersion == null) {
                    query.append("treeVersion <= null");
                } else {
                    query.append("treeVersion <= ?");
                }

                query.append(" AND ");

                if (updated == null) {
                    query.append("updated <= null");
                } else {
                    query.append("updated <= ?");
                }

                query.append(" AND status LIKE 'ACTIVE' ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("itemVersion ASC, ");
                    query.append("weight ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (treeVersion != null) {
                    qPos.add(treeVersion.longValue());
                }

                if (updated != null) {
                    qPos.add(CalendarUtil.getTimestamp(updated));
                }

                list = (List<DebateItem>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ACTIVEIDINTREEVERSION,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DebateItem findByactiveIdInTreeVersion_First(Long debateItemId,
        Long treeVersion, Date updated, OrderByComparator obc)
        throws NoSuchDebateItemException, SystemException {
        List<DebateItem> list = findByactiveIdInTreeVersion(debateItemId,
                treeVersion, updated, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItem exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("treeVersion=" + treeVersion);

            msg.append(", ");
            msg.append("updated=" + updated);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItem findByactiveIdInTreeVersion_Last(Long debateItemId,
        Long treeVersion, Date updated, OrderByComparator obc)
        throws NoSuchDebateItemException, SystemException {
        int count = countByactiveIdInTreeVersion(debateItemId, treeVersion,
                updated);

        List<DebateItem> list = findByactiveIdInTreeVersion(debateItemId,
                treeVersion, updated, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItem exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("treeVersion=" + treeVersion);

            msg.append(", ");
            msg.append("updated=" + updated);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItem[] findByactiveIdInTreeVersion_PrevAndNext(
        Long debateItemPK, Long debateItemId, Long treeVersion, Date updated,
        OrderByComparator obc)
        throws NoSuchDebateItemException, SystemException {
        DebateItem debateItem = findByPrimaryKey(debateItemPK);

        int count = countByactiveIdInTreeVersion(debateItemId, treeVersion,
                updated);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

            if (debateItemId == null) {
                query.append("debateItemId IS NULL");
            } else {
                query.append("debateItemId = ?");
            }

            query.append(" AND ");

            if (treeVersion == null) {
                query.append("treeVersion <= null");
            } else {
                query.append("treeVersion <= ?");
            }

            query.append(" AND ");

            if (updated == null) {
                query.append("updated <= null");
            } else {
                query.append("updated <= ?");
            }

            query.append(" AND status LIKE 'ACTIVE' ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("itemVersion ASC, ");
                query.append("weight ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (debateItemId != null) {
                qPos.add(debateItemId.longValue());
            }

            if (treeVersion != null) {
                qPos.add(treeVersion.longValue());
            }

            if (updated != null) {
                qPos.add(CalendarUtil.getTimestamp(updated));
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    debateItem);

            DebateItem[] array = new DebateItemImpl[3];

            array[0] = (DebateItem) objArray[0];
            array[1] = (DebateItem) objArray[1];
            array[2] = (DebateItem) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<DebateItem> findBybyIdStatus(Long debateItemId, String status)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, status };

        List<DebateItem> list = (List<DebateItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_BYIDSTATUS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (status == null) {
                    query.append("status LIKE null");
                } else {
                    query.append("status LIKE ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("itemVersion ASC, ");
                query.append("weight ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (status != null) {
                    qPos.add(status);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_BYIDSTATUS,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DebateItem> findBybyIdStatus(Long debateItemId, String status,
        int start, int end) throws SystemException {
        return findBybyIdStatus(debateItemId, status, start, end, null);
    }

    public List<DebateItem> findBybyIdStatus(Long debateItemId, String status,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                debateItemId,
                
                status,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItem> list = (List<DebateItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_BYIDSTATUS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (status == null) {
                    query.append("status LIKE null");
                } else {
                    query.append("status LIKE ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("itemVersion ASC, ");
                    query.append("weight ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (status != null) {
                    qPos.add(status);
                }

                list = (List<DebateItem>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_BYIDSTATUS,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DebateItem findBybyIdStatus_First(Long debateItemId, String status,
        OrderByComparator obc)
        throws NoSuchDebateItemException, SystemException {
        List<DebateItem> list = findBybyIdStatus(debateItemId, status, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItem exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("status=" + status);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItem findBybyIdStatus_Last(Long debateItemId, String status,
        OrderByComparator obc)
        throws NoSuchDebateItemException, SystemException {
        int count = countBybyIdStatus(debateItemId, status);

        List<DebateItem> list = findBybyIdStatus(debateItemId, status,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItem exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("status=" + status);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItem[] findBybyIdStatus_PrevAndNext(Long debateItemPK,
        Long debateItemId, String status, OrderByComparator obc)
        throws NoSuchDebateItemException, SystemException {
        DebateItem debateItem = findByPrimaryKey(debateItemPK);

        int count = countBybyIdStatus(debateItemId, status);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

            if (debateItemId == null) {
                query.append("debateItemId IS NULL");
            } else {
                query.append("debateItemId = ?");
            }

            query.append(" AND ");

            if (status == null) {
                query.append("status LIKE null");
            } else {
                query.append("status LIKE ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("itemVersion ASC, ");
                query.append("weight ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (debateItemId != null) {
                qPos.add(debateItemId.longValue());
            }

            if (status != null) {
                qPos.add(status);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    debateItem);

            DebateItem[] array = new DebateItemImpl[3];

            array[0] = (DebateItem) objArray[0];
            array[1] = (DebateItem) objArray[1];
            array[2] = (DebateItem) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<DebateItem> findBybyIdVersion(Long debateItemId,
        Long itemVersion) throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, itemVersion };

        List<DebateItem> list = (List<DebateItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_BYIDVERSION,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (itemVersion == null) {
                    query.append("itemVersion IS NULL");
                } else {
                    query.append("itemVersion = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("itemVersion ASC, ");
                query.append("weight ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (itemVersion != null) {
                    qPos.add(itemVersion.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_BYIDVERSION,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DebateItem> findBybyIdVersion(Long debateItemId,
        Long itemVersion, int start, int end) throws SystemException {
        return findBybyIdVersion(debateItemId, itemVersion, start, end, null);
    }

    public List<DebateItem> findBybyIdVersion(Long debateItemId,
        Long itemVersion, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                debateItemId,
                
                itemVersion,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItem> list = (List<DebateItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_BYIDVERSION,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (itemVersion == null) {
                    query.append("itemVersion IS NULL");
                } else {
                    query.append("itemVersion = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("itemVersion ASC, ");
                    query.append("weight ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (itemVersion != null) {
                    qPos.add(itemVersion.longValue());
                }

                list = (List<DebateItem>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_BYIDVERSION,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DebateItem findBybyIdVersion_First(Long debateItemId,
        Long itemVersion, OrderByComparator obc)
        throws NoSuchDebateItemException, SystemException {
        List<DebateItem> list = findBybyIdVersion(debateItemId, itemVersion, 0,
                1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItem exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("itemVersion=" + itemVersion);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItem findBybyIdVersion_Last(Long debateItemId,
        Long itemVersion, OrderByComparator obc)
        throws NoSuchDebateItemException, SystemException {
        int count = countBybyIdVersion(debateItemId, itemVersion);

        List<DebateItem> list = findBybyIdVersion(debateItemId, itemVersion,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItem exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(", ");
            msg.append("itemVersion=" + itemVersion);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDebateItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DebateItem[] findBybyIdVersion_PrevAndNext(Long debateItemPK,
        Long debateItemId, Long itemVersion, OrderByComparator obc)
        throws NoSuchDebateItemException, SystemException {
        DebateItem debateItem = findByPrimaryKey(debateItemPK);

        int count = countBybyIdVersion(debateItemId, itemVersion);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

            if (debateItemId == null) {
                query.append("debateItemId IS NULL");
            } else {
                query.append("debateItemId = ?");
            }

            query.append(" AND ");

            if (itemVersion == null) {
                query.append("itemVersion IS NULL");
            } else {
                query.append("itemVersion = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("itemVersion ASC, ");
                query.append("weight ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (debateItemId != null) {
                qPos.add(debateItemId.longValue());
            }

            if (itemVersion != null) {
                qPos.add(itemVersion.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    debateItem);

            DebateItem[] array = new DebateItemImpl[3];

            array[0] = (DebateItem) objArray[0];
            array[1] = (DebateItem) objArray[1];
            array[2] = (DebateItem) objArray[2];

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

    public List<DebateItem> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateItem> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateItem> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItem> list = (List<DebateItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("itemVersion ASC, ");
                    query.append("weight ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateItem>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateItem>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByactiveIdInTreeVersion(Long debateItemId,
        Long treeVersion, Date updated) throws SystemException {
        for (DebateItem debateItem : findByactiveIdInTreeVersion(debateItemId,
                treeVersion, updated)) {
            remove(debateItem);
        }
    }

    public void removeBybyIdStatus(Long debateItemId, String status)
        throws SystemException {
        for (DebateItem debateItem : findBybyIdStatus(debateItemId, status)) {
            remove(debateItem);
        }
    }

    public void removeBybyIdVersion(Long debateItemId, Long itemVersion)
        throws SystemException {
        for (DebateItem debateItem : findBybyIdVersion(debateItemId, itemVersion)) {
            remove(debateItem);
        }
    }

    public void removeAll() throws SystemException {
        for (DebateItem debateItem : findAll()) {
            remove(debateItem);
        }
    }

    public int countByactiveIdInTreeVersion(Long debateItemId,
        Long treeVersion, Date updated) throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, treeVersion, updated };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACTIVEIDINTREEVERSION,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (treeVersion == null) {
                    query.append("treeVersion <= null");
                } else {
                    query.append("treeVersion <= ?");
                }

                query.append(" AND ");

                if (updated == null) {
                    query.append("updated <= null");
                } else {
                    query.append("updated <= ?");
                }

                query.append(" AND status LIKE 'ACTIVE' ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (treeVersion != null) {
                    qPos.add(treeVersion.longValue());
                }

                if (updated != null) {
                    qPos.add(CalendarUtil.getTimestamp(updated));
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACTIVEIDINTREEVERSION,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countBybyIdStatus(Long debateItemId, String status)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, status };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_BYIDSTATUS,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (status == null) {
                    query.append("status LIKE null");
                } else {
                    query.append("status LIKE ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (status != null) {
                    qPos.add(status);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BYIDSTATUS,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countBybyIdVersion(Long debateItemId, Long itemVersion)
        throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId, itemVersion };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_BYIDVERSION,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItem WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" AND ");

                if (itemVersion == null) {
                    query.append("itemVersion IS NULL");
                } else {
                    query.append("itemVersion = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                if (itemVersion != null) {
                    qPos.add(itemVersion.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BYIDVERSION,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debaterevision.model.DebateItem");

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
                        "value.object.listener.com.ext.portlet.debaterevision.model.DebateItem")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateItem>> listenersList = new ArrayList<ModelListener<DebateItem>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateItem>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
