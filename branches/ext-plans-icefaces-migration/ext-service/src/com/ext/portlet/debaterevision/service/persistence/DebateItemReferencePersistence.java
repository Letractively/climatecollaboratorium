package com.ext.portlet.debaterevision.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateItemReferencePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference);

    public void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> debateItemReferences);

    public void clearCache();

    public com.ext.portlet.debaterevision.model.DebateItemReference create(
        java.lang.Long debateItemReferencePK);

    public com.ext.portlet.debaterevision.model.DebateItemReference remove(
        java.lang.Long debateItemReferencePK)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference remove(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateItemReference debateItemReference, boolean merge)</code>.
     */
    public com.ext.portlet.debaterevision.model.DebateItemReference update(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.debaterevision.model.DebateItemReference update(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference updateImpl(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference findByPrimaryKey(
        java.lang.Long debateItemReferencePK)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference fetchByPrimaryKey(
        java.lang.Long debateItemReferencePK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference findByDebateItemIdItemVersionStatus_First(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference findByDebateItemIdItemVersionStatus_Last(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference[] findByDebateItemIdItemVersionStatus_PrevAndNext(
        java.lang.Long debateItemReferencePK, java.lang.Long debateItemId,
        java.lang.Long itemVersion, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemReferenceException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status) throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByDebateItemIdItemVersionStatus(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        java.lang.String status) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
