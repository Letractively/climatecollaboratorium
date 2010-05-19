package com.ext.portlet.debatemigration.service.persistence;

public class DebateMigrationItemUtil {
    private static DebateMigrationItemPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem) {
        getPersistence().cacheResult(debateMigrationItem);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationItem> debateMigrationItems) {
        getPersistence().cacheResult(debateMigrationItems);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem create(
        java.lang.Long debateMigrationItemPK) {
        return getPersistence().create(debateMigrationItemPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem remove(
        java.lang.Long debateMigrationItemPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationItemException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigrationItemPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem remove(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateMigrationItem);
    }

    /**
     * @deprecated Use <code>update(DebateMigrationItem debateMigrationItem, boolean merge)</code>.
     */
    public static com.ext.portlet.debatemigration.model.DebateMigrationItem update(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigrationItem);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debatemigration.model.DebateMigrationItem update(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateMigrationItem, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateMigrationItem, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem findByPrimaryKey(
        java.lang.Long debateMigrationItemPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationItemException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateMigrationItemPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem fetchByPrimaryKey(
        java.lang.Long debateMigrationItemPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateMigrationItemPK);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem findByDebateMigrationItem(
        java.lang.Long debateMigrationId, java.lang.Long oldMBMessageId)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateMigrationItem(debateMigrationId, oldMBMessageId);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem fetchByDebateMigrationItem(
        java.lang.Long debateMigrationId, java.lang.Long oldMBMessageId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByDebateMigrationItem(debateMigrationId, oldMBMessageId);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem fetchByDebateMigrationItem(
        java.lang.Long debateMigrationId, java.lang.Long oldMBMessageId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByDebateMigrationItem(debateMigrationId,
            oldMBMessageId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationItem> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByDebateMigrationItem(
        java.lang.Long debateMigrationId, java.lang.Long oldMBMessageId)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationItemException,
            com.liferay.portal.SystemException {
        getPersistence()
            .removeByDebateMigrationItem(debateMigrationId, oldMBMessageId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByDebateMigrationItem(
        java.lang.Long debateMigrationId, java.lang.Long oldMBMessageId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByDebateMigrationItem(debateMigrationId, oldMBMessageId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebateMigrationItemPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateMigrationItemPersistence persistence) {
        _persistence = persistence;
    }
}
