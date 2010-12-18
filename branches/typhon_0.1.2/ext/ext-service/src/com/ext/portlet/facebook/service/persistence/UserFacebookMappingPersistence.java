package com.ext.portlet.facebook.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface UserFacebookMappingPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping);

    public void cacheResult(
        java.util.List<com.ext.portlet.facebook.model.UserFacebookMapping> userFacebookMappings);

    public void clearCache();

    public com.ext.portlet.facebook.model.UserFacebookMapping create(
        java.lang.Long userId);

    public com.ext.portlet.facebook.model.UserFacebookMapping remove(
        java.lang.Long userId)
        throws com.ext.portlet.facebook.NoSuchUserFacebookMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.facebook.model.UserFacebookMapping remove(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(UserFacebookMapping userFacebookMapping, boolean merge)</code>.
     */
    public com.ext.portlet.facebook.model.UserFacebookMapping update(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.facebook.model.UserFacebookMapping update(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.facebook.model.UserFacebookMapping updateImpl(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.facebook.model.UserFacebookMapping findByPrimaryKey(
        java.lang.Long userId)
        throws com.ext.portlet.facebook.NoSuchUserFacebookMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.facebook.model.UserFacebookMapping fetchByPrimaryKey(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.facebook.model.UserFacebookMapping findByfindByFacebookId(
        java.lang.String facebookId)
        throws com.ext.portlet.facebook.NoSuchUserFacebookMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.facebook.model.UserFacebookMapping fetchByfindByFacebookId(
        java.lang.String facebookId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.facebook.model.UserFacebookMapping fetchByfindByFacebookId(
        java.lang.String facebookId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.facebook.model.UserFacebookMapping> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.facebook.model.UserFacebookMapping> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.facebook.model.UserFacebookMapping> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByfindByFacebookId(java.lang.String facebookId)
        throws com.ext.portlet.facebook.NoSuchUserFacebookMappingException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByfindByFacebookId(java.lang.String facebookId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
