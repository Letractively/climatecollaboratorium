package com.ext.portlet.debatemigration.service.persistence;

public class DebateMigrationDebateUtil {
    private static DebateMigrationDebatePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate) {
        getPersistence().cacheResult(debateMigrationDebate);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> debateMigrationDebates) {
        getPersistence().cacheResult(debateMigrationDebates);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate create(
        java.lang.Long debateMigrationDebatePK) {
        return getPersistence().create(debateMigrationDebatePK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate remove(
        java.lang.Long debateMigrationDebatePK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigrationDebatePK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate remove(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigrationDebate);
    }

    /**
     * @deprecated Use <code>update(DebateMigrationDebate debateMigrationDebate, boolean merge)</code>.
     */
    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate update(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigrationDebate);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationDebate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationDebate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate update(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigrationDebate, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateMigrationDebate, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate findByPrimaryKey(
        java.lang.Long debateMigrationDebatePK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationDebateException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateMigrationDebatePK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate fetchByPrimaryKey(
        java.lang.Long debateMigrationDebatePK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateMigrationDebatePK);
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

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> findAll(
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

    public static DebateMigrationDebatePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateMigrationDebatePersistence persistence) {
        _persistence = persistence;
    }
}
