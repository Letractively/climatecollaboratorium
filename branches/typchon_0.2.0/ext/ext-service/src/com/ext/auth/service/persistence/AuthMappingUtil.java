package com.ext.auth.service.persistence;

public class AuthMappingUtil {
    private static AuthMappingPersistence _persistence;

    public static void cacheResult(com.ext.auth.model.AuthMapping authMapping) {
        getPersistence().cacheResult(authMapping);
    }

    public static void cacheResult(
        java.util.List<com.ext.auth.model.AuthMapping> authMappings) {
        getPersistence().cacheResult(authMappings);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.auth.model.AuthMapping create(
        java.lang.String identifier) {
        return getPersistence().create(identifier);
    }

    public static com.ext.auth.model.AuthMapping remove(
        java.lang.String identifier)
        throws com.ext.auth.NoSuchAuthMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(identifier);
    }

    public static com.ext.auth.model.AuthMapping remove(
        com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(authMapping);
    }

    /**
     * @deprecated Use <code>update(AuthMapping authMapping, boolean merge)</code>.
     */
    public static com.ext.auth.model.AuthMapping update(
        com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(authMapping);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                authMapping the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when authMapping is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.auth.model.AuthMapping update(
        com.ext.auth.model.AuthMapping authMapping, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(authMapping, merge);
    }

    public static com.ext.auth.model.AuthMapping updateImpl(
        com.ext.auth.model.AuthMapping authMapping, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(authMapping, merge);
    }

    public static com.ext.auth.model.AuthMapping findByPrimaryKey(
        java.lang.String identifier)
        throws com.ext.auth.NoSuchAuthMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(identifier);
    }

    public static com.ext.auth.model.AuthMapping fetchByPrimaryKey(
        java.lang.String identifier) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(identifier);
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

    public static java.util.List<com.ext.auth.model.AuthMapping> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.auth.model.AuthMapping> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.auth.model.AuthMapping> findAll(
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

    public static AuthMappingPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(AuthMappingPersistence persistence) {
        _persistence = persistence;
    }
}
