package com.ext.portlet.twitter.service.persistence;

public class UserTwitterMappingUtil {
    private static UserTwitterMappingPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping) {
        getPersistence().cacheResult(userTwitterMapping);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.twitter.model.UserTwitterMapping> userTwitterMappings) {
        getPersistence().cacheResult(userTwitterMappings);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping create(
        java.lang.Long twitterId) {
        return getPersistence().create(twitterId);
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping remove(
        java.lang.Long twitterId)
        throws com.ext.portlet.twitter.NoSuchUserTwitterMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(twitterId);
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping remove(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(userTwitterMapping);
    }

    /**
     * @deprecated Use <code>update(UserTwitterMapping userTwitterMapping, boolean merge)</code>.
     */
    public static com.ext.portlet.twitter.model.UserTwitterMapping update(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(userTwitterMapping);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                userTwitterMapping the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when userTwitterMapping is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.twitter.model.UserTwitterMapping update(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(userTwitterMapping, merge);
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping updateImpl(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(userTwitterMapping, merge);
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping findByPrimaryKey(
        java.lang.Long twitterId)
        throws com.ext.portlet.twitter.NoSuchUserTwitterMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(twitterId);
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping fetchByPrimaryKey(
        java.lang.Long twitterId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(twitterId);
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

    public static java.util.List<com.ext.portlet.twitter.model.UserTwitterMapping> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.twitter.model.UserTwitterMapping> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.twitter.model.UserTwitterMapping> findAll(
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

    public static UserTwitterMappingPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(UserTwitterMappingPersistence persistence) {
        _persistence = persistence;
    }
}
