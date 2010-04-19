package com.ext.portlet.debatemigration.service.persistence;

public class DebateMigrationUtil {
    private static DebateMigrationPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration) {
        getPersistence().cacheResult(debateMigration);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> debateMigrations) {
        getPersistence().cacheResult(debateMigrations);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration create(
        java.lang.Long debateMigrationPK) {
        return getPersistence().create(debateMigrationPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration remove(
        java.lang.Long debateMigrationPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigrationPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration remove(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigration);
    }

    /**
     * @deprecated Use <code>update(DebateMigration debateMigration, boolean merge)</code>.
     */
    public static com.ext.portlet.debatemigration.model.DebateMigration update(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigration);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigration the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigration is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debatemigration.model.DebateMigration update(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigration, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateMigration, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration findByPrimaryKey(
        java.lang.Long debateMigrationPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateMigrationPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration fetchByPrimaryKey(
        java.lang.Long debateMigrationPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateMigrationPK);
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

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> findAll(
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

    public static DebateMigrationPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateMigrationPersistence persistence) {
        _persistence = persistence;
    }
}
