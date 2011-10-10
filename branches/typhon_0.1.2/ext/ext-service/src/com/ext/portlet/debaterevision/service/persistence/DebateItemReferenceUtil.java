package com.ext.portlet.debaterevision.service.persistence;

public class DebateItemReferenceUtil {
    private static DebateItemReferencePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference) {
        getPersistence().cacheResult(debateItemReference);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> debateItemReferences) {
        getPersistence().cacheResult(debateItemReferences);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference create(
        java.lang.Long debateItemReferencePK) {
        return getPersistence().create(debateItemReferencePK);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference remove(
        java.lang.Long debateItemReferencePK)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateItemReferencePK);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference remove(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateItemReference);
    }

    /**
     * @deprecated Use <code>update(DebateItemReference debateItemReference, boolean merge)</code>.
     */
    public static com.ext.portlet.debaterevision.model.DebateItemReference update(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateItemReference);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItemReference the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItemReference is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debaterevision.model.DebateItemReference update(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateItemReference, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference updateImpl(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateItemReference, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference findByPrimaryKey(
        java.lang.Long debateItemReferencePK)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateItemReferencePK);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference fetchByPrimaryKey(
        java.lang.Long debateItemReferencePK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateItemReferencePK);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdItemVersionStatus(debateItemId,
            itemVersion, status);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdItemVersionStatus(debateItemId,
            itemVersion, status, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdItemVersionStatus(debateItemId,
            itemVersion, status, start, end, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference findByDebateItemIdItemVersionStatus_First(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdItemVersionStatus_First(debateItemId,
            itemVersion, status, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference findByDebateItemIdItemVersionStatus_Last(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdItemVersionStatus_Last(debateItemId,
            itemVersion, status, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference[] findByDebateItemIdItemVersionStatus_PrevAndNext(
        java.lang.Long debateItemReferencePK, java.lang.Long debateItemId,
        java.lang.Long itemVersion, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdItemVersionStatus_PrevAndNext(debateItemReferencePK,
            debateItemId, itemVersion, status, obc);
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

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status) throws com.liferay.portal.SystemException {
        getPersistence()
            .removeByDebateItemIdItemVersionStatus(debateItemId, itemVersion,
            status);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByDebateItemIdItemVersionStatus(debateItemId,
            itemVersion, status);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebateItemReferencePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateItemReferencePersistence persistence) {
        _persistence = persistence;
    }
}