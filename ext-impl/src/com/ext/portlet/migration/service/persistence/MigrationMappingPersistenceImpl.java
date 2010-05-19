/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service.persistence;

import com.ext.portlet.migration.NoSuchMappingException;
import com.ext.portlet.migration.model.MigrationMapping;
import com.ext.portlet.migration.model.impl.MigrationMappingImpl;
import com.ext.portlet.migration.model.impl.MigrationMappingModelImpl;

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


public class MigrationMappingPersistenceImpl extends BasePersistenceImpl
    implements MigrationMappingPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MigrationMappingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_OLDID = new FinderPath(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByOldId",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_OLDID = new FinderPath(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByOldId",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_NEWID = new FinderPath(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByNewId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_NEWID = new FinderPath(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByNewId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ENTITYNAME = new FinderPath(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByentityName",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ENTITYNAME = new FinderPath(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByentityName",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ENTITYNAME = new FinderPath(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByentityName",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MigrationMappingPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.migration.service.persistence.MigrationDataPersistence.impl")
    protected com.ext.portlet.migration.service.persistence.MigrationDataPersistence migrationDataPersistence;
    @BeanReference(name = "com.ext.portlet.migration.service.persistence.MigrationMappingPersistence.impl")
    protected com.ext.portlet.migration.service.persistence.MigrationMappingPersistence migrationMappingPersistence;

    public void cacheResult(MigrationMapping migrationMapping) {
        EntityCacheUtil.putResult(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingImpl.class, migrationMapping.getPrimaryKey(),
            migrationMapping);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OLDID,
            new Object[] { migrationMapping.getOldId() }, migrationMapping);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NEWID,
            new Object[] { migrationMapping.getNewId() }, migrationMapping);
    }

    public void cacheResult(List<MigrationMapping> migrationMappings) {
        for (MigrationMapping migrationMapping : migrationMappings) {
            if (EntityCacheUtil.getResult(
                        MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
                        MigrationMappingImpl.class,
                        migrationMapping.getPrimaryKey(), this) == null) {
                cacheResult(migrationMapping);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MigrationMappingImpl.class.getName());
        EntityCacheUtil.clearCache(MigrationMappingImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MigrationMapping create(MigrationMappingPK migrationMappingPK) {
        MigrationMapping migrationMapping = new MigrationMappingImpl();

        migrationMapping.setNew(true);
        migrationMapping.setPrimaryKey(migrationMappingPK);

        return migrationMapping;
    }

    public MigrationMapping remove(MigrationMappingPK migrationMappingPK)
        throws NoSuchMappingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MigrationMapping migrationMapping = (MigrationMapping) session.get(MigrationMappingImpl.class,
                    migrationMappingPK);

            if (migrationMapping == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No MigrationMapping exists with the primary key " +
                        migrationMappingPK);
                }

                throw new NoSuchMappingException(
                    "No MigrationMapping exists with the primary key " +
                    migrationMappingPK);
            }

            return remove(migrationMapping);
        } catch (NoSuchMappingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MigrationMapping remove(MigrationMapping migrationMapping)
        throws SystemException {
        for (ModelListener<MigrationMapping> listener : listeners) {
            listener.onBeforeRemove(migrationMapping);
        }

        migrationMapping = removeImpl(migrationMapping);

        for (ModelListener<MigrationMapping> listener : listeners) {
            listener.onAfterRemove(migrationMapping);
        }

        return migrationMapping;
    }

    protected MigrationMapping removeImpl(MigrationMapping migrationMapping)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (migrationMapping.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MigrationMappingImpl.class,
                        migrationMapping.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(migrationMapping);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        MigrationMappingModelImpl migrationMappingModelImpl = (MigrationMappingModelImpl) migrationMapping;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_OLDID,
            new Object[] { migrationMappingModelImpl.getOriginalOldId() });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NEWID,
            new Object[] { migrationMappingModelImpl.getOriginalNewId() });

        EntityCacheUtil.removeResult(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingImpl.class, migrationMapping.getPrimaryKey());

        return migrationMapping;
    }

    /**
     * @deprecated Use <code>update(MigrationMapping migrationMapping, boolean merge)</code>.
     */
    public MigrationMapping update(MigrationMapping migrationMapping)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MigrationMapping migrationMapping) method. Use update(MigrationMapping migrationMapping, boolean merge) instead.");
        }

        return update(migrationMapping, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                migrationMapping the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when migrationMapping is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MigrationMapping update(MigrationMapping migrationMapping,
        boolean merge) throws SystemException {
        boolean isNew = migrationMapping.isNew();

        for (ModelListener<MigrationMapping> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(migrationMapping);
            } else {
                listener.onBeforeUpdate(migrationMapping);
            }
        }

        migrationMapping = updateImpl(migrationMapping, merge);

        for (ModelListener<MigrationMapping> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(migrationMapping);
            } else {
                listener.onAfterUpdate(migrationMapping);
            }
        }

        return migrationMapping;
    }

    public MigrationMapping updateImpl(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping,
        boolean merge) throws SystemException {
        boolean isNew = migrationMapping.isNew();

        MigrationMappingModelImpl migrationMappingModelImpl = (MigrationMappingModelImpl) migrationMapping;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, migrationMapping, merge);

            migrationMapping.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
            MigrationMappingImpl.class, migrationMapping.getPrimaryKey(),
            migrationMapping);

        if (!isNew &&
                (!Validator.equals(migrationMapping.getOldId(),
                    migrationMappingModelImpl.getOriginalOldId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_OLDID,
                new Object[] { migrationMappingModelImpl.getOriginalOldId() });
        }

        if (isNew ||
                (!Validator.equals(migrationMapping.getOldId(),
                    migrationMappingModelImpl.getOriginalOldId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OLDID,
                new Object[] { migrationMapping.getOldId() }, migrationMapping);
        }

        if (!isNew &&
                (!Validator.equals(migrationMapping.getNewId(),
                    migrationMappingModelImpl.getOriginalNewId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NEWID,
                new Object[] { migrationMappingModelImpl.getOriginalNewId() });
        }

        if (isNew ||
                (!Validator.equals(migrationMapping.getNewId(),
                    migrationMappingModelImpl.getOriginalNewId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NEWID,
                new Object[] { migrationMapping.getNewId() }, migrationMapping);
        }

        return migrationMapping;
    }

    public MigrationMapping findByPrimaryKey(
        MigrationMappingPK migrationMappingPK)
        throws NoSuchMappingException, SystemException {
        MigrationMapping migrationMapping = fetchByPrimaryKey(migrationMappingPK);

        if (migrationMapping == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No MigrationMapping exists with the primary key " +
                    migrationMappingPK);
            }

            throw new NoSuchMappingException(
                "No MigrationMapping exists with the primary key " +
                migrationMappingPK);
        }

        return migrationMapping;
    }

    public MigrationMapping fetchByPrimaryKey(
        MigrationMappingPK migrationMappingPK) throws SystemException {
        MigrationMapping migrationMapping = (MigrationMapping) EntityCacheUtil.getResult(MigrationMappingModelImpl.ENTITY_CACHE_ENABLED,
                MigrationMappingImpl.class, migrationMappingPK, this);

        if (migrationMapping == null) {
            Session session = null;

            try {
                session = openSession();

                migrationMapping = (MigrationMapping) session.get(MigrationMappingImpl.class,
                        migrationMappingPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (migrationMapping != null) {
                    cacheResult(migrationMapping);
                }

                closeSession(session);
            }
        }

        return migrationMapping;
    }

    public MigrationMapping findByOldId(String oldId)
        throws NoSuchMappingException, SystemException {
        MigrationMapping migrationMapping = fetchByOldId(oldId);

        if (migrationMapping == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MigrationMapping exists with the key {");

            msg.append("oldId=" + oldId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMappingException(msg.toString());
        }

        return migrationMapping;
    }

    public MigrationMapping fetchByOldId(String oldId)
        throws SystemException {
        return fetchByOldId(oldId, true);
    }

    public MigrationMapping fetchByOldId(String oldId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { oldId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_OLDID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.migration.model.MigrationMapping WHERE ");

                if (oldId == null) {
                    query.append("oldId IS NULL");
                } else {
                    query.append("oldId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (oldId != null) {
                    qPos.add(oldId);
                }

                List<MigrationMapping> list = q.list();

                result = list;

                MigrationMapping migrationMapping = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OLDID,
                        finderArgs, list);
                } else {
                    migrationMapping = list.get(0);

                    cacheResult(migrationMapping);

                    if ((migrationMapping.getOldId() == null) ||
                            !migrationMapping.getOldId().equals(oldId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OLDID,
                            finderArgs, migrationMapping);
                    }
                }

                return migrationMapping;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OLDID,
                        finderArgs, new ArrayList<MigrationMapping>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (MigrationMapping) result;
            }
        }
    }

    public MigrationMapping findByNewId(Long newId)
        throws NoSuchMappingException, SystemException {
        MigrationMapping migrationMapping = fetchByNewId(newId);

        if (migrationMapping == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MigrationMapping exists with the key {");

            msg.append("newId=" + newId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMappingException(msg.toString());
        }

        return migrationMapping;
    }

    public MigrationMapping fetchByNewId(Long newId) throws SystemException {
        return fetchByNewId(newId, true);
    }

    public MigrationMapping fetchByNewId(Long newId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { newId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NEWID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.migration.model.MigrationMapping WHERE ");

                if (newId == null) {
                    query.append("newId IS NULL");
                } else {
                    query.append("newId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (newId != null) {
                    qPos.add(newId.longValue());
                }

                List<MigrationMapping> list = q.list();

                result = list;

                MigrationMapping migrationMapping = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NEWID,
                        finderArgs, list);
                } else {
                    migrationMapping = list.get(0);

                    cacheResult(migrationMapping);

                    if ((migrationMapping.getNewId() == null) ||
                            !migrationMapping.getNewId().equals(newId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NEWID,
                            finderArgs, migrationMapping);
                    }
                }

                return migrationMapping;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NEWID,
                        finderArgs, new ArrayList<MigrationMapping>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (MigrationMapping) result;
            }
        }
    }

    public List<MigrationMapping> findByentityName(Long newId)
        throws SystemException {
        Object[] finderArgs = new Object[] { newId };

        List<MigrationMapping> list = (List<MigrationMapping>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ENTITYNAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.migration.model.MigrationMapping WHERE ");

                if (newId == null) {
                    query.append("newId IS NULL");
                } else {
                    query.append("newId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (newId != null) {
                    qPos.add(newId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MigrationMapping>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ENTITYNAME,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<MigrationMapping> findByentityName(Long newId, int start,
        int end) throws SystemException {
        return findByentityName(newId, start, end, null);
    }

    public List<MigrationMapping> findByentityName(Long newId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                newId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MigrationMapping> list = (List<MigrationMapping>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ENTITYNAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.migration.model.MigrationMapping WHERE ");

                if (newId == null) {
                    query.append("newId IS NULL");
                } else {
                    query.append("newId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (newId != null) {
                    qPos.add(newId.longValue());
                }

                list = (List<MigrationMapping>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MigrationMapping>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ENTITYNAME,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public MigrationMapping findByentityName_First(Long newId,
        OrderByComparator obc) throws NoSuchMappingException, SystemException {
        List<MigrationMapping> list = findByentityName(newId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MigrationMapping exists with the key {");

            msg.append("newId=" + newId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMappingException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MigrationMapping findByentityName_Last(Long newId,
        OrderByComparator obc) throws NoSuchMappingException, SystemException {
        int count = countByentityName(newId);

        List<MigrationMapping> list = findByentityName(newId, count - 1, count,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MigrationMapping exists with the key {");

            msg.append("newId=" + newId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMappingException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MigrationMapping[] findByentityName_PrevAndNext(
        MigrationMappingPK migrationMappingPK, Long newId, OrderByComparator obc)
        throws NoSuchMappingException, SystemException {
        MigrationMapping migrationMapping = findByPrimaryKey(migrationMappingPK);

        int count = countByentityName(newId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.migration.model.MigrationMapping WHERE ");

            if (newId == null) {
                query.append("newId IS NULL");
            } else {
                query.append("newId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (newId != null) {
                qPos.add(newId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    migrationMapping);

            MigrationMapping[] array = new MigrationMappingImpl[3];

            array[0] = (MigrationMapping) objArray[0];
            array[1] = (MigrationMapping) objArray[1];
            array[2] = (MigrationMapping) objArray[2];

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

    public List<MigrationMapping> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MigrationMapping> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MigrationMapping> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MigrationMapping> list = (List<MigrationMapping>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.migration.model.MigrationMapping ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MigrationMapping>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MigrationMapping>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MigrationMapping>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByOldId(String oldId)
        throws NoSuchMappingException, SystemException {
        MigrationMapping migrationMapping = findByOldId(oldId);

        remove(migrationMapping);
    }

    public void removeByNewId(Long newId)
        throws NoSuchMappingException, SystemException {
        MigrationMapping migrationMapping = findByNewId(newId);

        remove(migrationMapping);
    }

    public void removeByentityName(Long newId) throws SystemException {
        for (MigrationMapping migrationMapping : findByentityName(newId)) {
            remove(migrationMapping);
        }
    }

    public void removeAll() throws SystemException {
        for (MigrationMapping migrationMapping : findAll()) {
            remove(migrationMapping);
        }
    }

    public int countByOldId(String oldId) throws SystemException {
        Object[] finderArgs = new Object[] { oldId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OLDID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.migration.model.MigrationMapping WHERE ");

                if (oldId == null) {
                    query.append("oldId IS NULL");
                } else {
                    query.append("oldId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (oldId != null) {
                    qPos.add(oldId);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OLDID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByNewId(Long newId) throws SystemException {
        Object[] finderArgs = new Object[] { newId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NEWID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.migration.model.MigrationMapping WHERE ");

                if (newId == null) {
                    query.append("newId IS NULL");
                } else {
                    query.append("newId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (newId != null) {
                    qPos.add(newId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NEWID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByentityName(Long newId) throws SystemException {
        Object[] finderArgs = new Object[] { newId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ENTITYNAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.migration.model.MigrationMapping WHERE ");

                if (newId == null) {
                    query.append("newId IS NULL");
                } else {
                    query.append("newId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (newId != null) {
                    qPos.add(newId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ENTITYNAME,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.migration.model.MigrationMapping");

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
                        "value.object.listener.com.ext.portlet.migration.model.MigrationMapping")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MigrationMapping>> listenersList = new ArrayList<ModelListener<MigrationMapping>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MigrationMapping>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
