package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelGlobalPreferenceException;
import com.ext.portlet.models.model.ModelGlobalPreference;
import com.ext.portlet.models.model.impl.ModelGlobalPreferenceImpl;
import com.ext.portlet.models.model.impl.ModelGlobalPreferenceModelImpl;

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


public class ModelGlobalPreferencePersistenceImpl extends BasePersistenceImpl
    implements ModelGlobalPreferencePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelGlobalPreferenceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_MODELID = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELID = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelGlobalPreferencePersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelDiscussionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelPositionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelPositionPersistence modelPositionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence modelGlobalPreferencePersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputGroupPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputItemPersistence modelOutputItemPersistence;

    public void cacheResult(ModelGlobalPreference modelGlobalPreference) {
        EntityCacheUtil.putResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            modelGlobalPreference.getPrimaryKey(), modelGlobalPreference);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID,
            new Object[] { modelGlobalPreference.getModelId() },
            modelGlobalPreference);
    }

    public void cacheResult(List<ModelGlobalPreference> modelGlobalPreferences) {
        for (ModelGlobalPreference modelGlobalPreference : modelGlobalPreferences) {
            if (EntityCacheUtil.getResult(
                        ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
                        ModelGlobalPreferenceImpl.class,
                        modelGlobalPreference.getPrimaryKey(), this) == null) {
                cacheResult(modelGlobalPreference);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelGlobalPreferenceImpl.class.getName());
        EntityCacheUtil.clearCache(ModelGlobalPreferenceImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelGlobalPreference create(Long modelGlobalPreferencePK) {
        ModelGlobalPreference modelGlobalPreference = new ModelGlobalPreferenceImpl();

        modelGlobalPreference.setNew(true);
        modelGlobalPreference.setPrimaryKey(modelGlobalPreferencePK);

        return modelGlobalPreference;
    }

    public ModelGlobalPreference remove(Long modelGlobalPreferencePK)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelGlobalPreference modelGlobalPreference = (ModelGlobalPreference) session.get(ModelGlobalPreferenceImpl.class,
                    modelGlobalPreferencePK);

            if (modelGlobalPreference == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ModelGlobalPreference exists with the primary key " +
                        modelGlobalPreferencePK);
                }

                throw new NoSuchModelGlobalPreferenceException(
                    "No ModelGlobalPreference exists with the primary key " +
                    modelGlobalPreferencePK);
            }

            return remove(modelGlobalPreference);
        } catch (NoSuchModelGlobalPreferenceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelGlobalPreference remove(
        ModelGlobalPreference modelGlobalPreference) throws SystemException {
        for (ModelListener<ModelGlobalPreference> listener : listeners) {
            listener.onBeforeRemove(modelGlobalPreference);
        }

        modelGlobalPreference = removeImpl(modelGlobalPreference);

        for (ModelListener<ModelGlobalPreference> listener : listeners) {
            listener.onAfterRemove(modelGlobalPreference);
        }

        return modelGlobalPreference;
    }

    protected ModelGlobalPreference removeImpl(
        ModelGlobalPreference modelGlobalPreference) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelGlobalPreference.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelGlobalPreferenceImpl.class,
                        modelGlobalPreference.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelGlobalPreference);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        ModelGlobalPreferenceModelImpl modelGlobalPreferenceModelImpl = (ModelGlobalPreferenceModelImpl) modelGlobalPreference;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELID,
            new Object[] { modelGlobalPreferenceModelImpl.getOriginalModelId() });

        EntityCacheUtil.removeResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            modelGlobalPreference.getPrimaryKey());

        return modelGlobalPreference;
    }

    /**
     * @deprecated Use <code>update(ModelGlobalPreference modelGlobalPreference, boolean merge)</code>.
     */
    public ModelGlobalPreference update(
        ModelGlobalPreference modelGlobalPreference) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelGlobalPreference modelGlobalPreference) method. Use update(ModelGlobalPreference modelGlobalPreference, boolean merge) instead.");
        }

        return update(modelGlobalPreference, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelGlobalPreference the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelGlobalPreference is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelGlobalPreference update(
        ModelGlobalPreference modelGlobalPreference, boolean merge)
        throws SystemException {
        boolean isNew = modelGlobalPreference.isNew();

        for (ModelListener<ModelGlobalPreference> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelGlobalPreference);
            } else {
                listener.onBeforeUpdate(modelGlobalPreference);
            }
        }

        modelGlobalPreference = updateImpl(modelGlobalPreference, merge);

        for (ModelListener<ModelGlobalPreference> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelGlobalPreference);
            } else {
                listener.onAfterUpdate(modelGlobalPreference);
            }
        }

        return modelGlobalPreference;
    }

    public ModelGlobalPreference updateImpl(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge) throws SystemException {
        boolean isNew = modelGlobalPreference.isNew();

        ModelGlobalPreferenceModelImpl modelGlobalPreferenceModelImpl = (ModelGlobalPreferenceModelImpl) modelGlobalPreference;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelGlobalPreference, merge);

            modelGlobalPreference.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            modelGlobalPreference.getPrimaryKey(), modelGlobalPreference);

        if (!isNew &&
                (!Validator.equals(modelGlobalPreference.getModelId(),
                    modelGlobalPreferenceModelImpl.getOriginalModelId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELID,
                new Object[] { modelGlobalPreferenceModelImpl.getOriginalModelId() });
        }

        if (isNew ||
                (!Validator.equals(modelGlobalPreference.getModelId(),
                    modelGlobalPreferenceModelImpl.getOriginalModelId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID,
                new Object[] { modelGlobalPreference.getModelId() },
                modelGlobalPreference);
        }

        return modelGlobalPreference;
    }

    public ModelGlobalPreference findByPrimaryKey(Long modelGlobalPreferencePK)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        ModelGlobalPreference modelGlobalPreference = fetchByPrimaryKey(modelGlobalPreferencePK);

        if (modelGlobalPreference == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No ModelGlobalPreference exists with the primary key " +
                    modelGlobalPreferencePK);
            }

            throw new NoSuchModelGlobalPreferenceException(
                "No ModelGlobalPreference exists with the primary key " +
                modelGlobalPreferencePK);
        }

        return modelGlobalPreference;
    }

    public ModelGlobalPreference fetchByPrimaryKey(Long modelGlobalPreferencePK)
        throws SystemException {
        ModelGlobalPreference modelGlobalPreference = (ModelGlobalPreference) EntityCacheUtil.getResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
                ModelGlobalPreferenceImpl.class, modelGlobalPreferencePK, this);

        if (modelGlobalPreference == null) {
            Session session = null;

            try {
                session = openSession();

                modelGlobalPreference = (ModelGlobalPreference) session.get(ModelGlobalPreferenceImpl.class,
                        modelGlobalPreferencePK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelGlobalPreference != null) {
                    cacheResult(modelGlobalPreference);
                }

                closeSession(session);
            }
        }

        return modelGlobalPreference;
    }

    public ModelGlobalPreference findByModelId(Long modelId)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        ModelGlobalPreference modelGlobalPreference = fetchByModelId(modelId);

        if (modelGlobalPreference == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelGlobalPreference exists with the key {");

            msg.append("modelId=" + modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchModelGlobalPreferenceException(msg.toString());
        }

        return modelGlobalPreference;
    }

    public ModelGlobalPreference fetchByModelId(Long modelId)
        throws SystemException {
        return fetchByModelId(modelId, true);
    }

    public ModelGlobalPreference fetchByModelId(Long modelId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { modelId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MODELID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelGlobalPreference WHERE ");

                if (modelId == null) {
                    query.append("modelId IS NULL");
                } else {
                    query.append("modelId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelId != null) {
                    qPos.add(modelId.longValue());
                }

                List<ModelGlobalPreference> list = q.list();

                result = list;

                ModelGlobalPreference modelGlobalPreference = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID,
                        finderArgs, list);
                } else {
                    modelGlobalPreference = list.get(0);

                    cacheResult(modelGlobalPreference);

                    if ((modelGlobalPreference.getModelId() == null) ||
                            !modelGlobalPreference.getModelId().equals(modelId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID,
                            finderArgs, modelGlobalPreference);
                    }
                }

                return modelGlobalPreference;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID,
                        finderArgs, new ArrayList<ModelGlobalPreference>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (ModelGlobalPreference) result;
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

    public List<ModelGlobalPreference> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelGlobalPreference> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelGlobalPreference> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelGlobalPreference> list = (List<ModelGlobalPreference>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelGlobalPreference ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelGlobalPreference>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelGlobalPreference>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelGlobalPreference>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByModelId(Long modelId)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        ModelGlobalPreference modelGlobalPreference = findByModelId(modelId);

        remove(modelGlobalPreference);
    }

    public void removeAll() throws SystemException {
        for (ModelGlobalPreference modelGlobalPreference : findAll()) {
            remove(modelGlobalPreference);
        }
    }

    public int countByModelId(Long modelId) throws SystemException {
        Object[] finderArgs = new Object[] { modelId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelGlobalPreference WHERE ");

                if (modelId == null) {
                    query.append("modelId IS NULL");
                } else {
                    query.append("modelId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelId != null) {
                    qPos.add(modelId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelGlobalPreference");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelGlobalPreference")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelGlobalPreference>> listenersList = new ArrayList<ModelListener<ModelGlobalPreference>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelGlobalPreference>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
