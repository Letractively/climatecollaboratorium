package com.ext.portlet.facebook.service.persistence;

public class UserFacebookMappingUtil {
    private static UserFacebookMappingPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping) {
        getPersistence().cacheResult(userFacebookMapping);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.facebook.model.UserFacebookMapping> userFacebookMappings) {
        getPersistence().cacheResult(userFacebookMappings);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping create(
        java.lang.Long userId) {
        return getPersistence().create(userId);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping remove(
        java.lang.Long userId)
        throws com.ext.portlet.facebook.NoSuchUserFacebookMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(userId);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping remove(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(userFacebookMapping);
    }

    /**
     * @deprecated Use <code>update(UserFacebookMapping userFacebookMapping, boolean merge)</code>.
     */
    public static com.ext.portlet.facebook.model.UserFacebookMapping update(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(userFacebookMapping);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                userFacebookMapping the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when userFacebookMapping is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.facebook.model.UserFacebookMapping update(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(userFacebookMapping, merge);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping updateImpl(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(userFacebookMapping, merge);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping findByPrimaryKey(
        java.lang.Long userId)
        throws com.ext.portlet.facebook.NoSuchUserFacebookMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(userId);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping fetchByPrimaryKey(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(userId);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping findByfindByFacebookId(
        java.lang.String facebookId)
        throws com.ext.portlet.facebook.NoSuchUserFacebookMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByfindByFacebookId(facebookId);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping fetchByfindByFacebookId(
        java.lang.String facebookId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByfindByFacebookId(facebookId);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping fetchByfindByFacebookId(
        java.lang.String facebookId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByfindByFacebookId(facebookId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.facebook.model.UserFacebookMapping> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.facebook.model.UserFacebookMapping> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.facebook.model.UserFacebookMapping> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByfindByFacebookId(java.lang.String facebookId)
        throws com.ext.portlet.facebook.NoSuchUserFacebookMappingException,
            com.liferay.portal.SystemException {
        getPersistence().removeByfindByFacebookId(facebookId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByfindByFacebookId(java.lang.String facebookId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByfindByFacebookId(facebookId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static UserFacebookMappingPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(UserFacebookMappingPersistence persistence) {
        _persistence = persistence;
    }
}
