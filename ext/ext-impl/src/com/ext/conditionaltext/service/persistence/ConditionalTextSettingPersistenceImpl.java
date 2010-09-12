package com.ext.conditionaltext.service.persistence;

import com.ext.conditionaltext.NoSuchConditionalTextSettingException;
import com.ext.conditionaltext.model.ConditionalTextSetting;
import com.ext.conditionaltext.model.impl.ConditionalTextSettingImpl;
import com.ext.conditionaltext.model.impl.ConditionalTextSettingModelImpl;

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


public class ConditionalTextSettingPersistenceImpl extends BasePersistenceImpl
    implements ConditionalTextSettingPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ConditionalTextSettingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_PARAMKEY = new FinderPath(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByparamKey",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_PARAMKEY = new FinderPath(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByparamKey",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_PARAMKEY = new FinderPath(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByparamKey",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_PARAMKEYPARAMVALUE = new FinderPath(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByparamKeyParamValue",
            new String[] { String.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_PARAMKEYPARAMVALUE = new FinderPath(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByparamKeyParamValue",
            new String[] { String.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ConditionalTextSettingPersistenceImpl.class);
    @BeanReference(name = "com.ext.conditionaltext.service.persistence.ConditionalTextSettingPersistence.impl")
    protected com.ext.conditionaltext.service.persistence.ConditionalTextSettingPersistence conditionalTextSettingPersistence;

    public void cacheResult(ConditionalTextSetting conditionalTextSetting) {
        EntityCacheUtil.putResult(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingImpl.class,
            conditionalTextSetting.getPrimaryKey(), conditionalTextSetting);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PARAMKEYPARAMVALUE,
            new Object[] {
                conditionalTextSetting.getParamKey(),
                
            conditionalTextSetting.getParamValue()
            }, conditionalTextSetting);
    }

    public void cacheResult(
        List<ConditionalTextSetting> conditionalTextSettings) {
        for (ConditionalTextSetting conditionalTextSetting : conditionalTextSettings) {
            if (EntityCacheUtil.getResult(
                        ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
                        ConditionalTextSettingImpl.class,
                        conditionalTextSetting.getPrimaryKey(), this) == null) {
                cacheResult(conditionalTextSetting);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ConditionalTextSettingImpl.class.getName());
        EntityCacheUtil.clearCache(ConditionalTextSettingImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ConditionalTextSetting create(Long ConditionalTextSettingId) {
        ConditionalTextSetting conditionalTextSetting = new ConditionalTextSettingImpl();

        conditionalTextSetting.setNew(true);
        conditionalTextSetting.setPrimaryKey(ConditionalTextSettingId);

        return conditionalTextSetting;
    }

    public ConditionalTextSetting remove(Long ConditionalTextSettingId)
        throws NoSuchConditionalTextSettingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ConditionalTextSetting conditionalTextSetting = (ConditionalTextSetting) session.get(ConditionalTextSettingImpl.class,
                    ConditionalTextSettingId);

            if (conditionalTextSetting == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ConditionalTextSetting exists with the primary key " +
                        ConditionalTextSettingId);
                }

                throw new NoSuchConditionalTextSettingException(
                    "No ConditionalTextSetting exists with the primary key " +
                    ConditionalTextSettingId);
            }

            return remove(conditionalTextSetting);
        } catch (NoSuchConditionalTextSettingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ConditionalTextSetting remove(
        ConditionalTextSetting conditionalTextSetting)
        throws SystemException {
        for (ModelListener<ConditionalTextSetting> listener : listeners) {
            listener.onBeforeRemove(conditionalTextSetting);
        }

        conditionalTextSetting = removeImpl(conditionalTextSetting);

        for (ModelListener<ConditionalTextSetting> listener : listeners) {
            listener.onAfterRemove(conditionalTextSetting);
        }

        return conditionalTextSetting;
    }

    protected ConditionalTextSetting removeImpl(
        ConditionalTextSetting conditionalTextSetting)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (conditionalTextSetting.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ConditionalTextSettingImpl.class,
                        conditionalTextSetting.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(conditionalTextSetting);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        ConditionalTextSettingModelImpl conditionalTextSettingModelImpl = (ConditionalTextSettingModelImpl) conditionalTextSetting;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PARAMKEYPARAMVALUE,
            new Object[] {
                conditionalTextSettingModelImpl.getOriginalParamKey(),
                
            conditionalTextSettingModelImpl.getOriginalParamValue()
            });

        EntityCacheUtil.removeResult(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingImpl.class,
            conditionalTextSetting.getPrimaryKey());

        return conditionalTextSetting;
    }

    /**
     * @deprecated Use <code>update(ConditionalTextSetting conditionalTextSetting, boolean merge)</code>.
     */
    public ConditionalTextSetting update(
        ConditionalTextSetting conditionalTextSetting)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ConditionalTextSetting conditionalTextSetting) method. Use update(ConditionalTextSetting conditionalTextSetting, boolean merge) instead.");
        }

        return update(conditionalTextSetting, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                conditionalTextSetting the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when conditionalTextSetting is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ConditionalTextSetting update(
        ConditionalTextSetting conditionalTextSetting, boolean merge)
        throws SystemException {
        boolean isNew = conditionalTextSetting.isNew();

        for (ModelListener<ConditionalTextSetting> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(conditionalTextSetting);
            } else {
                listener.onBeforeUpdate(conditionalTextSetting);
            }
        }

        conditionalTextSetting = updateImpl(conditionalTextSetting, merge);

        for (ModelListener<ConditionalTextSetting> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(conditionalTextSetting);
            } else {
                listener.onAfterUpdate(conditionalTextSetting);
            }
        }

        return conditionalTextSetting;
    }

    public ConditionalTextSetting updateImpl(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting,
        boolean merge) throws SystemException {
        boolean isNew = conditionalTextSetting.isNew();

        ConditionalTextSettingModelImpl conditionalTextSettingModelImpl = (ConditionalTextSettingModelImpl) conditionalTextSetting;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, conditionalTextSetting, merge);

            conditionalTextSetting.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
            ConditionalTextSettingImpl.class,
            conditionalTextSetting.getPrimaryKey(), conditionalTextSetting);

        if (!isNew &&
                (!Validator.equals(conditionalTextSetting.getParamKey(),
                    conditionalTextSettingModelImpl.getOriginalParamKey()) ||
                !Validator.equals(conditionalTextSetting.getParamValue(),
                    conditionalTextSettingModelImpl.getOriginalParamValue()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PARAMKEYPARAMVALUE,
                new Object[] {
                    conditionalTextSettingModelImpl.getOriginalParamKey(),
                    
                conditionalTextSettingModelImpl.getOriginalParamValue()
                });
        }

        if (isNew ||
                (!Validator.equals(conditionalTextSetting.getParamKey(),
                    conditionalTextSettingModelImpl.getOriginalParamKey()) ||
                !Validator.equals(conditionalTextSetting.getParamValue(),
                    conditionalTextSettingModelImpl.getOriginalParamValue()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PARAMKEYPARAMVALUE,
                new Object[] {
                    conditionalTextSetting.getParamKey(),
                    
                conditionalTextSetting.getParamValue()
                }, conditionalTextSetting);
        }

        return conditionalTextSetting;
    }

    public ConditionalTextSetting findByPrimaryKey(
        Long ConditionalTextSettingId)
        throws NoSuchConditionalTextSettingException, SystemException {
        ConditionalTextSetting conditionalTextSetting = fetchByPrimaryKey(ConditionalTextSettingId);

        if (conditionalTextSetting == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No ConditionalTextSetting exists with the primary key " +
                    ConditionalTextSettingId);
            }

            throw new NoSuchConditionalTextSettingException(
                "No ConditionalTextSetting exists with the primary key " +
                ConditionalTextSettingId);
        }

        return conditionalTextSetting;
    }

    public ConditionalTextSetting fetchByPrimaryKey(
        Long ConditionalTextSettingId) throws SystemException {
        ConditionalTextSetting conditionalTextSetting = (ConditionalTextSetting) EntityCacheUtil.getResult(ConditionalTextSettingModelImpl.ENTITY_CACHE_ENABLED,
                ConditionalTextSettingImpl.class, ConditionalTextSettingId, this);

        if (conditionalTextSetting == null) {
            Session session = null;

            try {
                session = openSession();

                conditionalTextSetting = (ConditionalTextSetting) session.get(ConditionalTextSettingImpl.class,
                        ConditionalTextSettingId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (conditionalTextSetting != null) {
                    cacheResult(conditionalTextSetting);
                }

                closeSession(session);
            }
        }

        return conditionalTextSetting;
    }

    public List<ConditionalTextSetting> findByparamKey(String paramKey)
        throws SystemException {
        Object[] finderArgs = new Object[] { paramKey };

        List<ConditionalTextSetting> list = (List<ConditionalTextSetting>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PARAMKEY,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.conditionaltext.model.ConditionalTextSetting WHERE ");

                if (paramKey == null) {
                    query.append("paramKey IS NULL");
                } else {
                    query.append("paramKey = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (paramKey != null) {
                    qPos.add(paramKey);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ConditionalTextSetting>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PARAMKEY,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ConditionalTextSetting> findByparamKey(String paramKey,
        int start, int end) throws SystemException {
        return findByparamKey(paramKey, start, end, null);
    }

    public List<ConditionalTextSetting> findByparamKey(String paramKey,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                paramKey,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ConditionalTextSetting> list = (List<ConditionalTextSetting>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PARAMKEY,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.conditionaltext.model.ConditionalTextSetting WHERE ");

                if (paramKey == null) {
                    query.append("paramKey IS NULL");
                } else {
                    query.append("paramKey = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (paramKey != null) {
                    qPos.add(paramKey);
                }

                list = (List<ConditionalTextSetting>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ConditionalTextSetting>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PARAMKEY,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ConditionalTextSetting findByparamKey_First(String paramKey,
        OrderByComparator obc)
        throws NoSuchConditionalTextSettingException, SystemException {
        List<ConditionalTextSetting> list = findByparamKey(paramKey, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ConditionalTextSetting exists with the key {");

            msg.append("paramKey=" + paramKey);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchConditionalTextSettingException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ConditionalTextSetting findByparamKey_Last(String paramKey,
        OrderByComparator obc)
        throws NoSuchConditionalTextSettingException, SystemException {
        int count = countByparamKey(paramKey);

        List<ConditionalTextSetting> list = findByparamKey(paramKey, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ConditionalTextSetting exists with the key {");

            msg.append("paramKey=" + paramKey);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchConditionalTextSettingException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ConditionalTextSetting[] findByparamKey_PrevAndNext(
        Long ConditionalTextSettingId, String paramKey, OrderByComparator obc)
        throws NoSuchConditionalTextSettingException, SystemException {
        ConditionalTextSetting conditionalTextSetting = findByPrimaryKey(ConditionalTextSettingId);

        int count = countByparamKey(paramKey);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.conditionaltext.model.ConditionalTextSetting WHERE ");

            if (paramKey == null) {
                query.append("paramKey IS NULL");
            } else {
                query.append("paramKey = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (paramKey != null) {
                qPos.add(paramKey);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    conditionalTextSetting);

            ConditionalTextSetting[] array = new ConditionalTextSettingImpl[3];

            array[0] = (ConditionalTextSetting) objArray[0];
            array[1] = (ConditionalTextSetting) objArray[1];
            array[2] = (ConditionalTextSetting) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ConditionalTextSetting findByparamKeyParamValue(String paramKey,
        String paramValue)
        throws NoSuchConditionalTextSettingException, SystemException {
        ConditionalTextSetting conditionalTextSetting = fetchByparamKeyParamValue(paramKey,
                paramValue);

        if (conditionalTextSetting == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ConditionalTextSetting exists with the key {");

            msg.append("paramKey=" + paramKey);

            msg.append(", ");
            msg.append("paramValue=" + paramValue);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchConditionalTextSettingException(msg.toString());
        }

        return conditionalTextSetting;
    }

    public ConditionalTextSetting fetchByparamKeyParamValue(String paramKey,
        String paramValue) throws SystemException {
        return fetchByparamKeyParamValue(paramKey, paramValue, true);
    }

    public ConditionalTextSetting fetchByparamKeyParamValue(String paramKey,
        String paramValue, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { paramKey, paramValue };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PARAMKEYPARAMVALUE,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.conditionaltext.model.ConditionalTextSetting WHERE ");

                if (paramKey == null) {
                    query.append("paramKey IS NULL");
                } else {
                    query.append("paramKey = ?");
                }

                query.append(" AND ");

                if (paramValue == null) {
                    query.append("paramValue IS NULL");
                } else {
                    query.append("paramValue = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (paramKey != null) {
                    qPos.add(paramKey);
                }

                if (paramValue != null) {
                    qPos.add(paramValue);
                }

                List<ConditionalTextSetting> list = q.list();

                result = list;

                ConditionalTextSetting conditionalTextSetting = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PARAMKEYPARAMVALUE,
                        finderArgs, list);
                } else {
                    conditionalTextSetting = list.get(0);

                    cacheResult(conditionalTextSetting);

                    if ((conditionalTextSetting.getParamKey() == null) ||
                            !conditionalTextSetting.getParamKey()
                                                       .equals(paramKey) ||
                            (conditionalTextSetting.getParamValue() == null) ||
                            !conditionalTextSetting.getParamValue()
                                                       .equals(paramValue)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PARAMKEYPARAMVALUE,
                            finderArgs, conditionalTextSetting);
                    }
                }

                return conditionalTextSetting;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PARAMKEYPARAMVALUE,
                        finderArgs, new ArrayList<ConditionalTextSetting>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (ConditionalTextSetting) result;
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

    public List<ConditionalTextSetting> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ConditionalTextSetting> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ConditionalTextSetting> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ConditionalTextSetting> list = (List<ConditionalTextSetting>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.conditionaltext.model.ConditionalTextSetting ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ConditionalTextSetting>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ConditionalTextSetting>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ConditionalTextSetting>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByparamKey(String paramKey) throws SystemException {
        for (ConditionalTextSetting conditionalTextSetting : findByparamKey(
                paramKey)) {
            remove(conditionalTextSetting);
        }
    }

    public void removeByparamKeyParamValue(String paramKey, String paramValue)
        throws NoSuchConditionalTextSettingException, SystemException {
        ConditionalTextSetting conditionalTextSetting = findByparamKeyParamValue(paramKey,
                paramValue);

        remove(conditionalTextSetting);
    }

    public void removeAll() throws SystemException {
        for (ConditionalTextSetting conditionalTextSetting : findAll()) {
            remove(conditionalTextSetting);
        }
    }

    public int countByparamKey(String paramKey) throws SystemException {
        Object[] finderArgs = new Object[] { paramKey };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARAMKEY,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.conditionaltext.model.ConditionalTextSetting WHERE ");

                if (paramKey == null) {
                    query.append("paramKey IS NULL");
                } else {
                    query.append("paramKey = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (paramKey != null) {
                    qPos.add(paramKey);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARAMKEY,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByparamKeyParamValue(String paramKey, String paramValue)
        throws SystemException {
        Object[] finderArgs = new Object[] { paramKey, paramValue };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARAMKEYPARAMVALUE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.conditionaltext.model.ConditionalTextSetting WHERE ");

                if (paramKey == null) {
                    query.append("paramKey IS NULL");
                } else {
                    query.append("paramKey = ?");
                }

                query.append(" AND ");

                if (paramValue == null) {
                    query.append("paramValue IS NULL");
                } else {
                    query.append("paramValue = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (paramKey != null) {
                    qPos.add(paramKey);
                }

                if (paramValue != null) {
                    qPos.add(paramValue);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARAMKEYPARAMVALUE,
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
                        "SELECT COUNT(*) FROM com.ext.conditionaltext.model.ConditionalTextSetting");

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
                        "value.object.listener.com.ext.conditionaltext.model.ConditionalTextSetting")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ConditionalTextSetting>> listenersList = new ArrayList<ModelListener<ConditionalTextSetting>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ConditionalTextSetting>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
