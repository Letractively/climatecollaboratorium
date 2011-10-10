package com.ext.portlet.twitter.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface UserTwitterMappingPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping);

    public void cacheResult(
        java.util.List<com.ext.portlet.twitter.model.UserTwitterMapping> userTwitterMappings);

    public void clearCache();

    public com.ext.portlet.twitter.model.UserTwitterMapping create(
        java.lang.Long twitterId);

    public com.ext.portlet.twitter.model.UserTwitterMapping remove(
        java.lang.Long twitterId)
        throws com.ext.portlet.twitter.NoSuchUserTwitterMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.twitter.model.UserTwitterMapping remove(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(UserTwitterMapping userTwitterMapping, boolean merge)</code>.
     */
    public com.ext.portlet.twitter.model.UserTwitterMapping update(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.twitter.model.UserTwitterMapping update(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.twitter.model.UserTwitterMapping updateImpl(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.twitter.model.UserTwitterMapping findByPrimaryKey(
        java.lang.Long twitterId)
        throws com.ext.portlet.twitter.NoSuchUserTwitterMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.twitter.model.UserTwitterMapping fetchByPrimaryKey(
        java.lang.Long twitterId) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.twitter.model.UserTwitterMapping> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.twitter.model.UserTwitterMapping> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.twitter.model.UserTwitterMapping> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}