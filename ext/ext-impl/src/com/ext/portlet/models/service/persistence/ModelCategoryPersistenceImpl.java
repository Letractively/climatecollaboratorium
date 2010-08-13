/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelCategoryException;
import com.ext.portlet.models.model.ModelCategory;
import com.ext.portlet.models.model.impl.ModelCategoryImpl;
import com.ext.portlet.models.model.impl.ModelCategoryModelImpl;

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


public class ModelCategoryPersistenceImpl extends BasePersistenceImpl
    implements ModelCategoryPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelCategoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelCategoryModelImpl.ENTITY_CACHE_ENABLED,
            ModelCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelCategoryModelImpl.ENTITY_CACHE_ENABLED,
            ModelCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelCategoryPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelDiscussionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelPositionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelPositionPersistence modelPositionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence modelGlobalPreferencePersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelCategoryPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelCategoryPersistence modelCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputGroupPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputItemPersistence modelOutputItemPersistence;

    public void cacheResult(ModelCategory modelCategory) {
        EntityCacheUtil.putResult(ModelCategoryModelImpl.ENTITY_CACHE_ENABLED,
            ModelCategoryImpl.class, modelCategory.getPrimaryKey(),
            modelCategory);
    }

    public void cacheResult(List<ModelCategory> modelCategories) {
        for (ModelCategory modelCategory : modelCategories) {
            if (EntityCacheUtil.getResult(
                        ModelCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        ModelCategoryImpl.class, modelCategory.getPrimaryKey(),
                        this) == null) {
                cacheResult(modelCategory);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelCategoryImpl.class.getName());
        EntityCacheUtil.clearCache(ModelCategoryImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelCategory create(Long modelCategoryPK) {
        ModelCategory modelCategory = new ModelCategoryImpl();

        modelCategory.setNew(true);
        modelCategory.setPrimaryKey(modelCategoryPK);

        return modelCategory;
    }

    public ModelCategory remove(Long modelCategoryPK)
        throws NoSuchModelCategoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelCategory modelCategory = (ModelCategory) session.get(ModelCategoryImpl.class,
                    modelCategoryPK);

            if (modelCategory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ModelCategory exists with the primary key " +
                        modelCategoryPK);
                }

                throw new NoSuchModelCategoryException(
                    "No ModelCategory exists with the primary key " +
                    modelCategoryPK);
            }

            return remove(modelCategory);
        } catch (NoSuchModelCategoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelCategory remove(ModelCategory modelCategory)
        throws SystemException {
        for (ModelListener<ModelCategory> listener : listeners) {
            listener.onBeforeRemove(modelCategory);
        }

        modelCategory = removeImpl(modelCategory);

        for (ModelListener<ModelCategory> listener : listeners) {
            listener.onAfterRemove(modelCategory);
        }

        return modelCategory;
    }

    protected ModelCategory removeImpl(ModelCategory modelCategory)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelCategory.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelCategoryImpl.class,
                        modelCategory.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelCategory);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ModelCategoryModelImpl.ENTITY_CACHE_ENABLED,
            ModelCategoryImpl.class, modelCategory.getPrimaryKey());

        return modelCategory;
    }

    /**
     * @deprecated Use <code>update(ModelCategory modelCategory, boolean merge)</code>.
     */
    public ModelCategory update(ModelCategory modelCategory)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelCategory modelCategory) method. Use update(ModelCategory modelCategory, boolean merge) instead.");
        }

        return update(modelCategory, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelCategory update(ModelCategory modelCategory, boolean merge)
        throws SystemException {
        boolean isNew = modelCategory.isNew();

        for (ModelListener<ModelCategory> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelCategory);
            } else {
                listener.onBeforeUpdate(modelCategory);
            }
        }

        modelCategory = updateImpl(modelCategory, merge);

        for (ModelListener<ModelCategory> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelCategory);
            } else {
                listener.onAfterUpdate(modelCategory);
            }
        }

        return modelCategory;
    }

    public ModelCategory updateImpl(
        com.ext.portlet.models.model.ModelCategory modelCategory, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelCategory, merge);

            modelCategory.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelCategoryModelImpl.ENTITY_CACHE_ENABLED,
            ModelCategoryImpl.class, modelCategory.getPrimaryKey(),
            modelCategory);

        return modelCategory;
    }

    public ModelCategory findByPrimaryKey(Long modelCategoryPK)
        throws NoSuchModelCategoryException, SystemException {
        ModelCategory modelCategory = fetchByPrimaryKey(modelCategoryPK);

        if (modelCategory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ModelCategory exists with the primary key " +
                    modelCategoryPK);
            }

            throw new NoSuchModelCategoryException(
                "No ModelCategory exists with the primary key " +
                modelCategoryPK);
        }

        return modelCategory;
    }

    public ModelCategory fetchByPrimaryKey(Long modelCategoryPK)
        throws SystemException {
        ModelCategory modelCategory = (ModelCategory) EntityCacheUtil.getResult(ModelCategoryModelImpl.ENTITY_CACHE_ENABLED,
                ModelCategoryImpl.class, modelCategoryPK, this);

        if (modelCategory == null) {
            Session session = null;

            try {
                session = openSession();

                modelCategory = (ModelCategory) session.get(ModelCategoryImpl.class,
                        modelCategoryPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelCategory != null) {
                    cacheResult(modelCategory);
                }

                closeSession(session);
            }
        }

        return modelCategory;
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

    public List<ModelCategory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelCategory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelCategory> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelCategory> list = (List<ModelCategory>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.models.model.ModelCategory ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelCategory>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelCategory>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (ModelCategory modelCategory : findAll()) {
            remove(modelCategory);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelCategory");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelCategory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelCategory>> listenersList = new ArrayList<ModelListener<ModelCategory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelCategory>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
