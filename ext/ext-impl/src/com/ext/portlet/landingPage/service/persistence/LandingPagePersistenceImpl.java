package com.ext.portlet.landingPage.service.persistence;

import com.ext.portlet.landingPage.NoSuchLandingPageException;
import com.ext.portlet.landingPage.model.LandingPage;
import com.ext.portlet.landingPage.model.impl.LandingPageImpl;
import com.ext.portlet.landingPage.model.impl.LandingPageModelImpl;

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


public class LandingPagePersistenceImpl extends BasePersistenceImpl
    implements LandingPagePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = LandingPageImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(LandingPageModelImpl.ENTITY_CACHE_ENABLED,
            LandingPageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LandingPageModelImpl.ENTITY_CACHE_ENABLED,
            LandingPageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(LandingPagePersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.landingPage.service.persistence.LandingPagePersistence.impl")
    protected com.ext.portlet.landingPage.service.persistence.LandingPagePersistence landingPagePersistence;

    public void cacheResult(LandingPage landingPage) {
        EntityCacheUtil.putResult(LandingPageModelImpl.ENTITY_CACHE_ENABLED,
            LandingPageImpl.class, landingPage.getPrimaryKey(), landingPage);
    }

    public void cacheResult(List<LandingPage> landingPages) {
        for (LandingPage landingPage : landingPages) {
            if (EntityCacheUtil.getResult(
                        LandingPageModelImpl.ENTITY_CACHE_ENABLED,
                        LandingPageImpl.class, landingPage.getPrimaryKey(), this) == null) {
                cacheResult(landingPage);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(LandingPageImpl.class.getName());
        EntityCacheUtil.clearCache(LandingPageImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public LandingPage create(Long id) {
        LandingPage landingPage = new LandingPageImpl();

        landingPage.setNew(true);
        landingPage.setPrimaryKey(id);

        return landingPage;
    }

    public LandingPage remove(Long id)
        throws NoSuchLandingPageException, SystemException {
        Session session = null;

        try {
            session = openSession();

            LandingPage landingPage = (LandingPage) session.get(LandingPageImpl.class,
                    id);

            if (landingPage == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No LandingPage exists with the primary key " +
                        id);
                }

                throw new NoSuchLandingPageException(
                    "No LandingPage exists with the primary key " + id);
            }

            return remove(landingPage);
        } catch (NoSuchLandingPageException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public LandingPage remove(LandingPage landingPage)
        throws SystemException {
        for (ModelListener<LandingPage> listener : listeners) {
            listener.onBeforeRemove(landingPage);
        }

        landingPage = removeImpl(landingPage);

        for (ModelListener<LandingPage> listener : listeners) {
            listener.onAfterRemove(landingPage);
        }

        return landingPage;
    }

    protected LandingPage removeImpl(LandingPage landingPage)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (landingPage.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(LandingPageImpl.class,
                        landingPage.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(landingPage);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(LandingPageModelImpl.ENTITY_CACHE_ENABLED,
            LandingPageImpl.class, landingPage.getPrimaryKey());

        return landingPage;
    }

    /**
     * @deprecated Use <code>update(LandingPage landingPage, boolean merge)</code>.
     */
    public LandingPage update(LandingPage landingPage)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(LandingPage landingPage) method. Use update(LandingPage landingPage, boolean merge) instead.");
        }

        return update(landingPage, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                landingPage the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when landingPage is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public LandingPage update(LandingPage landingPage, boolean merge)
        throws SystemException {
        boolean isNew = landingPage.isNew();

        for (ModelListener<LandingPage> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(landingPage);
            } else {
                listener.onBeforeUpdate(landingPage);
            }
        }

        landingPage = updateImpl(landingPage, merge);

        for (ModelListener<LandingPage> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(landingPage);
            } else {
                listener.onAfterUpdate(landingPage);
            }
        }

        return landingPage;
    }

    public LandingPage updateImpl(
        com.ext.portlet.landingPage.model.LandingPage landingPage, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, landingPage, merge);

            landingPage.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(LandingPageModelImpl.ENTITY_CACHE_ENABLED,
            LandingPageImpl.class, landingPage.getPrimaryKey(), landingPage);

        return landingPage;
    }

    public LandingPage findByPrimaryKey(Long id)
        throws NoSuchLandingPageException, SystemException {
        LandingPage landingPage = fetchByPrimaryKey(id);

        if (landingPage == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No LandingPage exists with the primary key " + id);
            }

            throw new NoSuchLandingPageException(
                "No LandingPage exists with the primary key " + id);
        }

        return landingPage;
    }

    public LandingPage fetchByPrimaryKey(Long id) throws SystemException {
        LandingPage landingPage = (LandingPage) EntityCacheUtil.getResult(LandingPageModelImpl.ENTITY_CACHE_ENABLED,
                LandingPageImpl.class, id, this);

        if (landingPage == null) {
            Session session = null;

            try {
                session = openSession();

                landingPage = (LandingPage) session.get(LandingPageImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (landingPage != null) {
                    cacheResult(landingPage);
                }

                closeSession(session);
            }
        }

        return landingPage;
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

    public List<LandingPage> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<LandingPage> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<LandingPage> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<LandingPage> list = (List<LandingPage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.landingPage.model.LandingPage ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<LandingPage>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<LandingPage>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<LandingPage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (LandingPage landingPage : findAll()) {
            remove(landingPage);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.landingPage.model.LandingPage");

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
                        "value.object.listener.com.ext.portlet.landingPage.model.LandingPage")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<LandingPage>> listenersList = new ArrayList<ModelListener<LandingPage>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<LandingPage>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
