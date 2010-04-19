package com.ext.portlet.debatemigration.service.persistence;

public class DebateMigrationCommentUtil {
    private static DebateMigrationCommentPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment) {
        getPersistence().cacheResult(debateMigrationComment);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationComment> debateMigrationComments) {
        getPersistence().cacheResult(debateMigrationComments);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment create(
        java.lang.Long debateMigrationCommentPK) {
        return getPersistence().create(debateMigrationCommentPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment remove(
        java.lang.Long debateMigrationCommentPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationCommentException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigrationCommentPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment remove(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigrationComment);
    }

    /**
     * @deprecated Use <code>update(DebateMigrationComment debateMigrationComment, boolean merge)</code>.
     */
    public static com.ext.portlet.debatemigration.model.DebateMigrationComment update(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigrationComment);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationComment the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationComment is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debatemigration.model.DebateMigrationComment update(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigrationComment, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateMigrationComment, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment findByPrimaryKey(
        java.lang.Long debateMigrationCommentPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationCommentException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateMigrationCommentPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment fetchByPrimaryKey(
        java.lang.Long debateMigrationCommentPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateMigrationCommentPK);
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

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationComment> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationComment> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationComment> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebateMigrationCommentPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateMigrationCommentPersistence persistence) {
        _persistence = persistence;
    }
}
