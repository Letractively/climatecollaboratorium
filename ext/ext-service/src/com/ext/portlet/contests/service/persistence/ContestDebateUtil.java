package com.ext.portlet.contests.service.persistence;

public class ContestDebateUtil {
    private static ContestDebatePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.contests.model.ContestDebate contestDebate) {
        getPersistence().cacheResult(contestDebate);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestDebate> contestDebates) {
        getPersistence().cacheResult(contestDebates);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.contests.model.ContestDebate create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.contests.model.ContestDebate remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.contests.model.ContestDebate remove(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(contestDebate);
    }

    /**
     * @deprecated Use <code>update(ContestDebate contestDebate, boolean merge)</code>.
     */
    public static com.ext.portlet.contests.model.ContestDebate update(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(contestDebate);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestDebate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestDebate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.contests.model.ContestDebate update(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(contestDebate, merge);
    }

    public static com.ext.portlet.contests.model.ContestDebate updateImpl(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(contestDebate, merge);
    }

    public static com.ext.portlet.contests.model.ContestDebate findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.contests.model.ContestDebate fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        java.lang.Long ContestPK) throws com.liferay.portal.SystemException {
        return getPersistence().findByContestPK(ContestPK);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        java.lang.Long ContestPK, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByContestPK(ContestPK, start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        java.lang.Long ContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByContestPK(ContestPK, start, end, obc);
    }

    public static com.ext.portlet.contests.model.ContestDebate findByContestPK_First(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestPK_First(ContestPK, obc);
    }

    public static com.ext.portlet.contests.model.ContestDebate findByContestPK_Last(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestPK_Last(ContestPK, obc);
    }

    public static com.ext.portlet.contests.model.ContestDebate[] findByContestPK_PrevAndNext(
        java.lang.Long id, java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestPK_PrevAndNext(id, ContestPK, obc);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByContestPK(java.lang.Long ContestPK)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByContestPK(ContestPK);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByContestPK(java.lang.Long ContestPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByContestPK(ContestPK);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ContestDebatePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ContestDebatePersistence persistence) {
        _persistence = persistence;
    }
}
