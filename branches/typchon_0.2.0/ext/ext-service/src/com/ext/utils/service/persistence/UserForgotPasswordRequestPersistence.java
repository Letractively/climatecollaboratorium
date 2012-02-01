package com.ext.utils.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface UserForgotPasswordRequestPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest);

    public void cacheResult(
        java.util.List<com.ext.utils.model.UserForgotPasswordRequest> userForgotPasswordRequests);

    public void clearCache();

    public com.ext.utils.model.UserForgotPasswordRequest create(
        java.lang.String token);

    public com.ext.utils.model.UserForgotPasswordRequest remove(
        java.lang.String token)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest remove(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(UserForgotPasswordRequest userForgotPasswordRequest, boolean merge)</code>.
     */
    public com.ext.utils.model.UserForgotPasswordRequest update(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException;

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
    public com.ext.utils.model.UserForgotPasswordRequest update(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest updateImpl(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest findByPrimaryKey(
        java.lang.String token)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest fetchByPrimaryKey(
        java.lang.String token) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findByUserId(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest findByUserId_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest findByUserId_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest[] findByUserId_PrevAndNext(
        java.lang.String token, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.utils.NoSuchUserForgotPasswordRequestException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.utils.model.UserForgotPasswordRequest> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
