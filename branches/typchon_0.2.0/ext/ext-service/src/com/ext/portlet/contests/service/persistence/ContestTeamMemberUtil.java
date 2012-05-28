package com.ext.portlet.contests.service.persistence;

public class ContestTeamMemberUtil {
    private static ContestTeamMemberPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember) {
        getPersistence().cacheResult(contestTeamMember);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestTeamMember> contestTeamMembers) {
        getPersistence().cacheResult(contestTeamMembers);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.contests.model.ContestTeamMember create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember remove(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(contestTeamMember);
    }

    /**
     * @deprecated Use <code>update(ContestTeamMember contestTeamMember, boolean merge)</code>.
     */
    public static com.ext.portlet.contests.model.ContestTeamMember update(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(contestTeamMember);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestTeamMember the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestTeamMember is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.contests.model.ContestTeamMember update(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(contestTeamMember, merge);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember updateImpl(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(contestTeamMember, merge);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findByContestId(
        java.lang.Long contestId) throws com.liferay.portal.SystemException {
        return getPersistence().findByContestId(contestId);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findByContestId(
        java.lang.Long contestId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByContestId(contestId, start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findByContestId(
        java.lang.Long contestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByContestId(contestId, start, end, obc);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember findByContestId_First(
        java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestId_First(contestId, obc);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember findByContestId_Last(
        java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestId_Last(contestId, obc);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember[] findByContestId_PrevAndNext(
        java.lang.Long id, java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestId_PrevAndNext(id, contestId, obc);
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

    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByContestId(java.lang.Long contestId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByContestId(contestId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByContestId(java.lang.Long contestId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByContestId(contestId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ContestTeamMemberPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ContestTeamMemberPersistence persistence) {
        _persistence = persistence;
    }
}
