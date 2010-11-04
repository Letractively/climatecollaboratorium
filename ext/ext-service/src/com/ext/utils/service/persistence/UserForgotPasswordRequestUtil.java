package com.ext.utils.service.persistence;

public class UserForgotPasswordRequestUtil {
    private static UserForgotPasswordRequestPersistence _persistence;

    public static void cacheResult(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest) {
        getPersistence().cacheResult(userForgotPasswordRequest);
    }

    public static void cacheResult(
        java.util.List<com.ext.utils.model.UserForgotPasswordRequest> userForgotPasswordRequests) {
        getPersistence().cacheResult(userForgotPasswordRequests);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.utils.model.UserForgotPasswordRequest create(
        java.lang.String token) {
        return getPersistence().create(token);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest remove(
        java.lang.String token)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(token);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest remove(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(userForgotPasswordRequest);
    }

    /**
     * @deprecated Use <code>update(UserForgotPasswordRequest userForgotPasswordRequest, boolean merge)</code>.
     */
    public static com.ext.utils.model.UserForgotPasswordRequest update(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(userForgotPasswordRequest);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                userForgotPasswordRequest the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when userForgotPasswordRequest is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.utils.model.UserForgotPasswordRequest update(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(userForgotPasswordRequest, merge);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest updateImpl(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(userForgotPasswordRequest, merge);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest findByPrimaryKey(
        java.lang.String token)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(token);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest fetchByPrimaryKey(
        java.lang.String token) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(token);
    }

    public static java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId);
    }

    public static java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    public static java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findByUserId(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId, start, end, obc);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest findByUserId_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_First(userId, obc);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest findByUserId_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_Last(userId, obc);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest[] findByUserId_PrevAndNext(
        java.lang.String token, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_PrevAndNext(token, userId, obc);
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

    public static java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByUserId(userId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByUserId(userId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static UserForgotPasswordRequestPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(UserForgotPasswordRequestPersistence persistence) {
        _persistence = persistence;
    }
}
