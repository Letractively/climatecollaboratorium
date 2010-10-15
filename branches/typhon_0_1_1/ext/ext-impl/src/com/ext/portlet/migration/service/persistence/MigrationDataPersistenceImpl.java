/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service.persistence;

import com.ext.portlet.migration.NoSuchDataException;
import com.ext.portlet.migration.model.MigrationData;
import com.ext.portlet.migration.model.impl.MigrationDataImpl;
import com.ext.portlet.migration.model.impl.MigrationDataModelImpl;

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


public class MigrationDataPersistenceImpl extends BasePersistenceImpl
    implements MigrationDataPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MigrationDataImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MigrationDataModelImpl.ENTITY_CACHE_ENABLED,
            MigrationDataModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MigrationDataModelImpl.ENTITY_CACHE_ENABLED,
            MigrationDataModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MigrationDataPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.migration.service.persistence.MigrationDataPersistence.impl")
    protected com.ext.portlet.migration.service.persistence.MigrationDataPersistence migrationDataPersistence;
    @BeanReference(name = "com.ext.portlet.migration.service.persistence.MigrationMappingPersistence.impl")
    protected com.ext.portlet.migration.service.persistence.MigrationMappingPersistence migrationMappingPersistence;

    public void cacheResult(MigrationData migrationData) {
        EntityCacheUtil.putResult(MigrationDataModelImpl.ENTITY_CACHE_ENABLED,
            MigrationDataImpl.class, migrationData.getPrimaryKey(),
            migrationData);
    }

    public void cacheResult(List<MigrationData> migrationDatas) {
        for (MigrationData migrationData : migrationDatas) {
            if (EntityCacheUtil.getResult(
                        MigrationDataModelImpl.ENTITY_CACHE_ENABLED,
                        MigrationDataImpl.class, migrationData.getPrimaryKey(),
                        this) == null) {
                cacheResult(migrationData);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MigrationDataImpl.class.getName());
        EntityCacheUtil.clearCache(MigrationDataImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MigrationData create(Long migrationId) {
        MigrationData migrationData = new MigrationDataImpl();

        migrationData.setNew(true);
        migrationData.setPrimaryKey(migrationId);

        return migrationData;
    }

    public MigrationData remove(Long migrationId)
        throws NoSuchDataException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MigrationData migrationData = (MigrationData) session.get(MigrationDataImpl.class,
                    migrationId);

            if (migrationData == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No MigrationData exists with the primary key " +
                        migrationId);
                }

                throw new NoSuchDataException(
                    "No MigrationData exists with the primary key " +
                    migrationId);
            }

            return remove(migrationData);
        } catch (NoSuchDataException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MigrationData remove(MigrationData migrationData)
        throws SystemException {
        for (ModelListener<MigrationData> listener : listeners) {
            listener.onBeforeRemove(migrationData);
        }

        migrationData = removeImpl(migrationData);

        for (ModelListener<MigrationData> listener : listeners) {
            listener.onAfterRemove(migrationData);
        }

        return migrationData;
    }

    protected MigrationData removeImpl(MigrationData migrationData)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (migrationData.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MigrationDataImpl.class,
                        migrationData.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(migrationData);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(MigrationDataModelImpl.ENTITY_CACHE_ENABLED,
            MigrationDataImpl.class, migrationData.getPrimaryKey());

        return migrationData;
    }

    /**
     * @deprecated Use <code>update(MigrationData migrationData, boolean merge)</code>.
     */
    public MigrationData update(MigrationData migrationData)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MigrationData migrationData) method. Use update(MigrationData migrationData, boolean merge) instead.");
        }

        return update(migrationData, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                migrationData the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when migrationData is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MigrationData update(MigrationData migrationData, boolean merge)
        throws SystemException {
        boolean isNew = migrationData.isNew();

        for (ModelListener<MigrationData> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(migrationData);
            } else {
                listener.onBeforeUpdate(migrationData);
            }
        }

        migrationData = updateImpl(migrationData, merge);

        for (ModelListener<MigrationData> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(migrationData);
            } else {
                listener.onAfterUpdate(migrationData);
            }
        }

        return migrationData;
    }

    public MigrationData updateImpl(
        com.ext.portlet.migration.model.MigrationData migrationData,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, migrationData, merge);

            migrationData.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MigrationDataModelImpl.ENTITY_CACHE_ENABLED,
            MigrationDataImpl.class, migrationData.getPrimaryKey(),
            migrationData);

        return migrationData;
    }

    public MigrationData findByPrimaryKey(Long migrationId)
        throws NoSuchDataException, SystemException {
        MigrationData migrationData = fetchByPrimaryKey(migrationId);

        if (migrationData == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No MigrationData exists with the primary key " +
                    migrationId);
            }

            throw new NoSuchDataException(
                "No MigrationData exists with the primary key " + migrationId);
        }

        return migrationData;
    }

    public MigrationData fetchByPrimaryKey(Long migrationId)
        throws SystemException {
        MigrationData migrationData = (MigrationData) EntityCacheUtil.getResult(MigrationDataModelImpl.ENTITY_CACHE_ENABLED,
                MigrationDataImpl.class, migrationId, this);

        if (migrationData == null) {
            Session session = null;

            try {
                session = openSession();

                migrationData = (MigrationData) session.get(MigrationDataImpl.class,
                        migrationId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (migrationData != null) {
                    cacheResult(migrationData);
                }

                closeSession(session);
            }
        }

        return migrationData;
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

    public List<MigrationData> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MigrationData> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MigrationData> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MigrationData> list = (List<MigrationData>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.migration.model.MigrationData ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("createdDate DESC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MigrationData>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MigrationData>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MigrationData>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (MigrationData migrationData : findAll()) {
            remove(migrationData);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.migration.model.MigrationData");

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
                        "value.object.listener.com.ext.portlet.migration.model.MigrationData")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MigrationData>> listenersList = new ArrayList<ModelListener<MigrationData>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MigrationData>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
