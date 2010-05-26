package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelPositionException;
import com.ext.portlet.models.model.ModelPosition;
import com.ext.portlet.models.model.impl.ModelPositionImpl;
import com.ext.portlet.models.model.impl.ModelPositionModelImpl;

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


public class ModelPositionPersistenceImpl extends BasePersistenceImpl
    implements ModelPositionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelPositionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_POSITIONID = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByPositionId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_POSITIONID = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByPositionId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_POSITIONID = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByPositionId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelPositionPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelDiscussionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelPositionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelPositionPersistence modelPositionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputGroupPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputItemPersistence modelOutputItemPersistence;

    public void cacheResult(ModelPosition modelPosition) {
        EntityCacheUtil.putResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionImpl.class, modelPosition.getPrimaryKey(),
            modelPosition);
    }

    public void cacheResult(List<ModelPosition> modelPositions) {
        for (ModelPosition modelPosition : modelPositions) {
            if (EntityCacheUtil.getResult(
                        ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
                        ModelPositionImpl.class, modelPosition.getPrimaryKey(),
                        this) == null) {
                cacheResult(modelPosition);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelPositionImpl.class.getName());
        EntityCacheUtil.clearCache(ModelPositionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelPosition create(ModelPositionPK modelPositionPK) {
        ModelPosition modelPosition = new ModelPositionImpl();

        modelPosition.setNew(true);
        modelPosition.setPrimaryKey(modelPositionPK);

        return modelPosition;
    }

    public ModelPosition remove(ModelPositionPK modelPositionPK)
        throws NoSuchModelPositionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelPosition modelPosition = (ModelPosition) session.get(ModelPositionImpl.class,
                    modelPositionPK);

            if (modelPosition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ModelPosition exists with the primary key " +
                        modelPositionPK);
                }

                throw new NoSuchModelPositionException(
                    "No ModelPosition exists with the primary key " +
                    modelPositionPK);
            }

            return remove(modelPosition);
        } catch (NoSuchModelPositionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelPosition remove(ModelPosition modelPosition)
        throws SystemException {
        for (ModelListener<ModelPosition> listener : listeners) {
            listener.onBeforeRemove(modelPosition);
        }

        modelPosition = removeImpl(modelPosition);

        for (ModelListener<ModelPosition> listener : listeners) {
            listener.onAfterRemove(modelPosition);
        }

        return modelPosition;
    }

    protected ModelPosition removeImpl(ModelPosition modelPosition)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelPosition.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelPositionImpl.class,
                        modelPosition.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelPosition);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionImpl.class, modelPosition.getPrimaryKey());

        return modelPosition;
    }

    /**
     * @deprecated Use <code>update(ModelPosition modelPosition, boolean merge)</code>.
     */
    public ModelPosition update(ModelPosition modelPosition)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelPosition modelPosition) method. Use update(ModelPosition modelPosition, boolean merge) instead.");
        }

        return update(modelPosition, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelPosition update(ModelPosition modelPosition, boolean merge)
        throws SystemException {
        boolean isNew = modelPosition.isNew();

        for (ModelListener<ModelPosition> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelPosition);
            } else {
                listener.onBeforeUpdate(modelPosition);
            }
        }

        modelPosition = updateImpl(modelPosition, merge);

        for (ModelListener<ModelPosition> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelPosition);
            } else {
                listener.onAfterUpdate(modelPosition);
            }
        }

        return modelPosition;
    }

    public ModelPosition updateImpl(
        com.ext.portlet.models.model.ModelPosition modelPosition, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelPosition, merge);

            modelPosition.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionImpl.class, modelPosition.getPrimaryKey(),
            modelPosition);

        return modelPosition;
    }

    public ModelPosition findByPrimaryKey(ModelPositionPK modelPositionPK)
        throws NoSuchModelPositionException, SystemException {
        ModelPosition modelPosition = fetchByPrimaryKey(modelPositionPK);

        if (modelPosition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ModelPosition exists with the primary key " +
                    modelPositionPK);
            }

            throw new NoSuchModelPositionException(
                "No ModelPosition exists with the primary key " +
                modelPositionPK);
        }

        return modelPosition;
    }

    public ModelPosition fetchByPrimaryKey(ModelPositionPK modelPositionPK)
        throws SystemException {
        ModelPosition modelPosition = (ModelPosition) EntityCacheUtil.getResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
                ModelPositionImpl.class, modelPositionPK, this);

        if (modelPosition == null) {
            Session session = null;

            try {
                session = openSession();

                modelPosition = (ModelPosition) session.get(ModelPositionImpl.class,
                        modelPositionPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelPosition != null) {
                    cacheResult(modelPosition);
                }

                closeSession(session);
            }
        }

        return modelPosition;
    }

    public List<ModelPosition> findByPositionId(Long positionId)
        throws SystemException {
        Object[] finderArgs = new Object[] { positionId };

        List<ModelPosition> list = (List<ModelPosition>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_POSITIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelPosition WHERE ");

                if (positionId == null) {
                    query.append("positionId IS NULL");
                } else {
                    query.append("positionId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("modelId ASC, ");
                query.append("positionId ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (positionId != null) {
                    qPos.add(positionId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelPosition>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_POSITIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ModelPosition> findByPositionId(Long positionId, int start,
        int end) throws SystemException {
        return findByPositionId(positionId, start, end, null);
    }

    public List<ModelPosition> findByPositionId(Long positionId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                positionId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelPosition> list = (List<ModelPosition>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_POSITIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelPosition WHERE ");

                if (positionId == null) {
                    query.append("positionId IS NULL");
                } else {
                    query.append("positionId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("modelId ASC, ");
                    query.append("positionId ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (positionId != null) {
                    qPos.add(positionId.longValue());
                }

                list = (List<ModelPosition>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelPosition>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_POSITIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ModelPosition findByPositionId_First(Long positionId,
        OrderByComparator obc)
        throws NoSuchModelPositionException, SystemException {
        List<ModelPosition> list = findByPositionId(positionId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelPosition exists with the key {");

            msg.append("positionId=" + positionId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelPositionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelPosition findByPositionId_Last(Long positionId,
        OrderByComparator obc)
        throws NoSuchModelPositionException, SystemException {
        int count = countByPositionId(positionId);

        List<ModelPosition> list = findByPositionId(positionId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelPosition exists with the key {");

            msg.append("positionId=" + positionId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelPositionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelPosition[] findByPositionId_PrevAndNext(
        ModelPositionPK modelPositionPK, Long positionId, OrderByComparator obc)
        throws NoSuchModelPositionException, SystemException {
        ModelPosition modelPosition = findByPrimaryKey(modelPositionPK);

        int count = countByPositionId(positionId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.models.model.ModelPosition WHERE ");

            if (positionId == null) {
                query.append("positionId IS NULL");
            } else {
                query.append("positionId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("modelId ASC, ");
                query.append("positionId ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (positionId != null) {
                qPos.add(positionId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    modelPosition);

            ModelPosition[] array = new ModelPositionImpl[3];

            array[0] = (ModelPosition) objArray[0];
            array[1] = (ModelPosition) objArray[1];
            array[2] = (ModelPosition) objArray[2];

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

    public List<ModelPosition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelPosition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelPosition> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelPosition> list = (List<ModelPosition>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.models.model.ModelPosition ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("modelId ASC, ");
                    query.append("positionId ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelPosition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelPosition>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelPosition>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPositionId(Long positionId) throws SystemException {
        for (ModelPosition modelPosition : findByPositionId(positionId)) {
            remove(modelPosition);
        }
    }

    public void removeAll() throws SystemException {
        for (ModelPosition modelPosition : findAll()) {
            remove(modelPosition);
        }
    }

    public int countByPositionId(Long positionId) throws SystemException {
        Object[] finderArgs = new Object[] { positionId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_POSITIONID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelPosition WHERE ");

                if (positionId == null) {
                    query.append("positionId IS NULL");
                } else {
                    query.append("positionId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (positionId != null) {
                    qPos.add(positionId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_POSITIONID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelPosition");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelPosition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelPosition>> listenersList = new ArrayList<ModelListener<ModelPosition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelPosition>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
