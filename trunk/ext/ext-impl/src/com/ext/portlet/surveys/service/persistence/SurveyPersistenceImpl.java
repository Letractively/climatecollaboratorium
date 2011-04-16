package com.ext.portlet.surveys.service.persistence;

import com.ext.portlet.surveys.NoSuchSurveyException;
import com.ext.portlet.surveys.model.Survey;
import com.ext.portlet.surveys.model.impl.SurveyImpl;
import com.ext.portlet.surveys.model.impl.SurveyModelImpl;

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


public class SurveyPersistenceImpl extends BasePersistenceImpl
    implements SurveyPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = SurveyImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(SurveyModelImpl.ENTITY_CACHE_ENABLED,
            SurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SurveyModelImpl.ENTITY_CACHE_ENABLED,
            SurveyModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(SurveyPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.surveys.service.persistence.SurveyPersistence.impl")
    protected com.ext.portlet.surveys.service.persistence.SurveyPersistence surveyPersistence;
    @BeanReference(name = "com.ext.portlet.surveys.service.persistence.UserSurveyPersistence.impl")
    protected com.ext.portlet.surveys.service.persistence.UserSurveyPersistence userSurveyPersistence;

    public void cacheResult(Survey survey) {
        EntityCacheUtil.putResult(SurveyModelImpl.ENTITY_CACHE_ENABLED,
            SurveyImpl.class, survey.getPrimaryKey(), survey);
    }

    public void cacheResult(List<Survey> surveies) {
        for (Survey survey : surveies) {
            if (EntityCacheUtil.getResult(
                        SurveyModelImpl.ENTITY_CACHE_ENABLED, SurveyImpl.class,
                        survey.getPrimaryKey(), this) == null) {
                cacheResult(survey);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(SurveyImpl.class.getName());
        EntityCacheUtil.clearCache(SurveyImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public Survey create(String eventName) {
        Survey survey = new SurveyImpl();

        survey.setNew(true);
        survey.setPrimaryKey(eventName);

        return survey;
    }

    public Survey remove(String eventName)
        throws NoSuchSurveyException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Survey survey = (Survey) session.get(SurveyImpl.class, eventName);

            if (survey == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No Survey exists with the primary key " +
                        eventName);
                }

                throw new NoSuchSurveyException(
                    "No Survey exists with the primary key " + eventName);
            }

            return remove(survey);
        } catch (NoSuchSurveyException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public Survey remove(Survey survey) throws SystemException {
        for (ModelListener<Survey> listener : listeners) {
            listener.onBeforeRemove(survey);
        }

        survey = removeImpl(survey);

        for (ModelListener<Survey> listener : listeners) {
            listener.onAfterRemove(survey);
        }

        return survey;
    }

    protected Survey removeImpl(Survey survey) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (survey.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(SurveyImpl.class,
                        survey.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(survey);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(SurveyModelImpl.ENTITY_CACHE_ENABLED,
            SurveyImpl.class, survey.getPrimaryKey());

        return survey;
    }

    /**
     * @deprecated Use <code>update(Survey survey, boolean merge)</code>.
     */
    public Survey update(Survey survey) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(Survey survey) method. Use update(Survey survey, boolean merge) instead.");
        }

        return update(survey, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                survey the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when survey is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public Survey update(Survey survey, boolean merge)
        throws SystemException {
        boolean isNew = survey.isNew();

        for (ModelListener<Survey> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(survey);
            } else {
                listener.onBeforeUpdate(survey);
            }
        }

        survey = updateImpl(survey, merge);

        for (ModelListener<Survey> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(survey);
            } else {
                listener.onAfterUpdate(survey);
            }
        }

        return survey;
    }

    public Survey updateImpl(com.ext.portlet.surveys.model.Survey survey,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, survey, merge);

            survey.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(SurveyModelImpl.ENTITY_CACHE_ENABLED,
            SurveyImpl.class, survey.getPrimaryKey(), survey);

        return survey;
    }

    public Survey findByPrimaryKey(String eventName)
        throws NoSuchSurveyException, SystemException {
        Survey survey = fetchByPrimaryKey(eventName);

        if (survey == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No Survey exists with the primary key " + eventName);
            }

            throw new NoSuchSurveyException(
                "No Survey exists with the primary key " + eventName);
        }

        return survey;
    }

    public Survey fetchByPrimaryKey(String eventName) throws SystemException {
        Survey survey = (Survey) EntityCacheUtil.getResult(SurveyModelImpl.ENTITY_CACHE_ENABLED,
                SurveyImpl.class, eventName, this);

        if (survey == null) {
            Session session = null;

            try {
                session = openSession();

                survey = (Survey) session.get(SurveyImpl.class, eventName);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (survey != null) {
                    cacheResult(survey);
                }

                closeSession(session);
            }
        }

        return survey;
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

    public List<Survey> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<Survey> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<Survey> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Survey> list = (List<Survey>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.surveys.model.Survey ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<Survey>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Survey>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Survey>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (Survey survey : findAll()) {
            remove(survey);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.surveys.model.Survey");

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
                        "value.object.listener.com.ext.portlet.surveys.model.Survey")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Survey>> listenersList = new ArrayList<ModelListener<Survey>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Survey>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
