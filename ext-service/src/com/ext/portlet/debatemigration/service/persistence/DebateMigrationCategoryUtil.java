package com.ext.portlet.debatemigration.service.persistence;

public class DebateMigrationCategoryUtil {
    private static DebateMigrationCategoryPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory) {
        getPersistence().cacheResult(debateMigrationCategory);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> debateMigrationCategories) {
        getPersistence().cacheResult(debateMigrationCategories);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory create(
        java.lang.Long debateMigrationCategoryPK) {
        return getPersistence().create(debateMigrationCategoryPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory remove(
        java.lang.Long debateMigrationCategoryPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigrationCategoryPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory remove(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigrationCategory);
    }

    /**
     * @deprecated Use <code>update(DebateMigrationCategory debateMigrationCategory, boolean merge)</code>.
     */
    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory update(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigrationCategory);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory update(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigrationCategory, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateMigrationCategory, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory findByPrimaryKey(
        java.lang.Long debateMigrationCategoryPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateMigrationCategoryPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory fetchByPrimaryKey(
        java.lang.Long debateMigrationCategoryPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateMigrationCategoryPK);
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

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> findAll(
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

    public static DebateMigrationCategoryPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateMigrationCategoryPersistence persistence) {
        _persistence = persistence;
    }
}
