package com.ext.auth.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface AuthMappingPersistence extends BasePersistence {
    public void cacheResult(com.ext.auth.model.AuthMapping authMapping);

    public void cacheResult(
        java.util.List<com.ext.auth.model.AuthMapping> authMappings);

    public void clearCache();

    public com.ext.auth.model.AuthMapping create(java.lang.String identifier);

    public com.ext.auth.model.AuthMapping remove(java.lang.String identifier)
        throws com.ext.auth.NoSuchAuthMappingException,
            com.liferay.portal.SystemException;

    public com.ext.auth.model.AuthMapping remove(
        com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(AuthMapping authMapping, boolean merge)</code>.
     */
    public com.ext.auth.model.AuthMapping update(
        com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException;

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
    public com.ext.auth.model.AuthMapping update(
        com.ext.auth.model.AuthMapping authMapping, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.auth.model.AuthMapping updateImpl(
        com.ext.auth.model.AuthMapping authMapping, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.auth.model.AuthMapping findByPrimaryKey(
        java.lang.String identifier)
        throws com.ext.auth.NoSuchAuthMappingException,
            com.liferay.portal.SystemException;

    public com.ext.auth.model.AuthMapping fetchByPrimaryKey(
        java.lang.String identifier) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.auth.model.AuthMapping> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.auth.model.AuthMapping> findAll(int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.auth.model.AuthMapping> findAll(int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
