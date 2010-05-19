package com.ext.portlet.debaterevision.service.persistence;

public class DebateUtil {
    private static DebatePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debaterevision.model.Debate debate) {
        getPersistence().cacheResult(debate);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.Debate> debates) {
        getPersistence().cacheResult(debates);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debaterevision.model.Debate create(
        java.lang.Long debatePK) {
        return getPersistence().create(debatePK);
    }

    public static com.ext.portlet.debaterevision.model.Debate remove(
        java.lang.Long debatePK)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debatePK);
    }

    public static com.ext.portlet.debaterevision.model.Debate remove(
        com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debate);
    }

    /**
     * @deprecated Use <code>update(Debate debate, boolean merge)</code>.
     */
    public static com.ext.portlet.debaterevision.model.Debate update(
        com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debate);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debaterevision.model.Debate update(
        com.ext.portlet.debaterevision.model.Debate debate, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debate, merge);
    }

    public static com.ext.portlet.debaterevision.model.Debate updateImpl(
        com.ext.portlet.debaterevision.model.Debate debate, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debate, merge);
    }

    public static com.ext.portlet.debaterevision.model.Debate findByPrimaryKey(
        java.lang.Long debatePK)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debatePK);
    }

    public static com.ext.portlet.debaterevision.model.Debate fetchByPrimaryKey(
        java.lang.Long debatePK) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debatePK);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdStatus(
        java.lang.Long debateId, java.lang.String status)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBybyIdStatus(debateId, status);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdStatus(
        java.lang.Long debateId, java.lang.String status, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBybyIdStatus(debateId, status, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdStatus(
        java.lang.Long debateId, java.lang.String status, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdStatus(debateId, status, start, end, obc);
    }

    public static com.ext.portlet.debaterevision.model.Debate findBybyIdStatus_First(
        java.lang.Long debateId, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().findBybyIdStatus_First(debateId, status, obc);
    }

    public static com.ext.portlet.debaterevision.model.Debate findBybyIdStatus_Last(
        java.lang.Long debateId, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().findBybyIdStatus_Last(debateId, status, obc);
    }

    public static com.ext.portlet.debaterevision.model.Debate[] findBybyIdStatus_PrevAndNext(
        java.lang.Long debatePK, java.lang.Long debateId,
        java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdStatus_PrevAndNext(debatePK, debateId, status, obc);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdVersion(
        java.lang.Long debateId, java.lang.Long treeVersion)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBybyIdVersion(debateId, treeVersion);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdVersion(
        java.lang.Long debateId, java.lang.Long treeVersion, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion(debateId, treeVersion, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdVersion(
        java.lang.Long debateId, java.lang.Long treeVersion, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion(debateId, treeVersion, start, end, obc);
    }

    public static com.ext.portlet.debaterevision.model.Debate findBybyIdVersion_First(
        java.lang.Long debateId, java.lang.Long treeVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion_First(debateId, treeVersion, obc);
    }

    public static com.ext.portlet.debaterevision.model.Debate findBybyIdVersion_Last(
        java.lang.Long debateId, java.lang.Long treeVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion_Last(debateId, treeVersion, obc);
    }

    public static com.ext.portlet.debaterevision.model.Debate[] findBybyIdVersion_PrevAndNext(
        java.lang.Long debatePK, java.lang.Long debateId,
        java.lang.Long treeVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion_PrevAndNext(debatePK, debateId,
            treeVersion, obc);
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

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeBybyIdStatus(java.lang.Long debateId,
        java.lang.String status) throws com.liferay.portal.SystemException {
        getPersistence().removeBybyIdStatus(debateId, status);
    }

    public static void removeBybyIdVersion(java.lang.Long debateId,
        java.lang.Long treeVersion) throws com.liferay.portal.SystemException {
        getPersistence().removeBybyIdVersion(debateId, treeVersion);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countBybyIdStatus(java.lang.Long debateId,
        java.lang.String status) throws com.liferay.portal.SystemException {
        return getPersistence().countBybyIdStatus(debateId, status);
    }

    public static int countBybyIdVersion(java.lang.Long debateId,
        java.lang.Long treeVersion) throws com.liferay.portal.SystemException {
        return getPersistence().countBybyIdVersion(debateId, treeVersion);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebatePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebatePersistence persistence) {
        _persistence = persistence;
    }
}
