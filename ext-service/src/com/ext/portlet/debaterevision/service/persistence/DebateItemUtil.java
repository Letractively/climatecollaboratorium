package com.ext.portlet.debaterevision.service.persistence;

public class DebateItemUtil {
    private static DebateItemPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debaterevision.model.DebateItem debateItem) {
        getPersistence().cacheResult(debateItem);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateItem> debateItems) {
        getPersistence().cacheResult(debateItems);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debaterevision.model.DebateItem create(
        java.lang.Long debateItemPK) {
        return getPersistence().create(debateItemPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem remove(
        java.lang.Long debateItemPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateItemPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem remove(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateItem);
    }

    /**
     * @deprecated Use <code>update(DebateItem debateItem, boolean merge)</code>.
     */
    public static com.ext.portlet.debaterevision.model.DebateItem update(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateItem);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debaterevision.model.DebateItem update(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateItem, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem updateImpl(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateItem, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem findByPrimaryKey(
        java.lang.Long debateItemPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateItemPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem fetchByPrimaryKey(
        java.lang.Long debateItemPK) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateItemPK);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findByactiveIdInTreeVersion(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByactiveIdInTreeVersion(debateItemId, treeVersion,
            updated);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findByactiveIdInTreeVersion(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByactiveIdInTreeVersion(debateItemId, treeVersion,
            updated, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findByactiveIdInTreeVersion(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByactiveIdInTreeVersion(debateItemId, treeVersion,
            updated, start, end, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem findByactiveIdInTreeVersion_First(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByactiveIdInTreeVersion_First(debateItemId,
            treeVersion, updated, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem findByactiveIdInTreeVersion_Last(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByactiveIdInTreeVersion_Last(debateItemId, treeVersion,
            updated, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem[] findByactiveIdInTreeVersion_PrevAndNext(
        java.lang.Long debateItemPK, java.lang.Long debateItemId,
        java.lang.Long treeVersion, java.util.Date updated,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByactiveIdInTreeVersion_PrevAndNext(debateItemPK,
            debateItemId, treeVersion, updated, obc);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdStatus(
        java.lang.Long debateItemId, java.lang.String status)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBybyIdStatus(debateItemId, status);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdStatus(
        java.lang.Long debateItemId, java.lang.String status, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdStatus(debateItemId, status, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdStatus(
        java.lang.Long debateItemId, java.lang.String status, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdStatus(debateItemId, status, start, end, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem findBybyIdStatus_First(
        java.lang.Long debateItemId, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence().findBybyIdStatus_First(debateItemId, status, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem findBybyIdStatus_Last(
        java.lang.Long debateItemId, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence().findBybyIdStatus_Last(debateItemId, status, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem[] findBybyIdStatus_PrevAndNext(
        java.lang.Long debateItemPK, java.lang.Long debateItemId,
        java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdStatus_PrevAndNext(debateItemPK, debateItemId,
            status, obc);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdVersion(
        java.lang.Long debateItemId, java.lang.Long itemVersion)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBybyIdVersion(debateItemId, itemVersion);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdVersion(
        java.lang.Long debateItemId, java.lang.Long itemVersion, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion(debateItemId, itemVersion, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdVersion(
        java.lang.Long debateItemId, java.lang.Long itemVersion, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion(debateItemId, itemVersion, start, end, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem findBybyIdVersion_First(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion_First(debateItemId, itemVersion, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem findBybyIdVersion_Last(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion_Last(debateItemId, itemVersion, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem[] findBybyIdVersion_PrevAndNext(
        java.lang.Long debateItemPK, java.lang.Long debateItemId,
        java.lang.Long itemVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBybyIdVersion_PrevAndNext(debateItemPK, debateItemId,
            itemVersion, obc);
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

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByactiveIdInTreeVersion(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated) throws com.liferay.portal.SystemException {
        getPersistence()
            .removeByactiveIdInTreeVersion(debateItemId, treeVersion, updated);
    }

    public static void removeBybyIdStatus(java.lang.Long debateItemId,
        java.lang.String status) throws com.liferay.portal.SystemException {
        getPersistence().removeBybyIdStatus(debateItemId, status);
    }

    public static void removeBybyIdVersion(java.lang.Long debateItemId,
        java.lang.Long itemVersion) throws com.liferay.portal.SystemException {
        getPersistence().removeBybyIdVersion(debateItemId, itemVersion);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByactiveIdInTreeVersion(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByactiveIdInTreeVersion(debateItemId, treeVersion,
            updated);
    }

    public static int countBybyIdStatus(java.lang.Long debateItemId,
        java.lang.String status) throws com.liferay.portal.SystemException {
        return getPersistence().countBybyIdStatus(debateItemId, status);
    }

    public static int countBybyIdVersion(java.lang.Long debateItemId,
        java.lang.Long itemVersion) throws com.liferay.portal.SystemException {
        return getPersistence().countBybyIdVersion(debateItemId, itemVersion);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebateItemPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateItemPersistence persistence) {
        _persistence = persistence;
    }
}
