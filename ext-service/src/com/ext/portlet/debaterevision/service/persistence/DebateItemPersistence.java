package com.ext.portlet.debaterevision.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateItemPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debaterevision.model.DebateItem debateItem);

    public void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateItem> debateItems);

    public void clearCache();

    public com.ext.portlet.debaterevision.model.DebateItem create(
        java.lang.Long debateItemPK);

    public com.ext.portlet.debaterevision.model.DebateItem remove(
        java.lang.Long debateItemPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem remove(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateItem debateItem, boolean merge)</code>.
     */
    public com.ext.portlet.debaterevision.model.DebateItem update(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debaterevision.model.DebateItem update(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem updateImpl(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem findByPrimaryKey(
        java.lang.Long debateItemPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem fetchByPrimaryKey(
        java.lang.Long debateItemPK) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findByactiveIdInTreeVersion(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findByactiveIdInTreeVersion(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findByactiveIdInTreeVersion(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem findByactiveIdInTreeVersion_First(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem findByactiveIdInTreeVersion_Last(
        java.lang.Long debateItemId, java.lang.Long treeVersion,
        java.util.Date updated,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem[] findByactiveIdInTreeVersion_PrevAndNext(
        java.lang.Long debateItemPK, java.lang.Long debateItemId,
        java.lang.Long treeVersion, java.util.Date updated,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdStatus(
        java.lang.Long debateItemId, java.lang.String status)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdStatus(
        java.lang.Long debateItemId, java.lang.String status, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdStatus(
        java.lang.Long debateItemId, java.lang.String status, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem findBybyIdStatus_First(
        java.lang.Long debateItemId, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem findBybyIdStatus_Last(
        java.lang.Long debateItemId, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem[] findBybyIdStatus_PrevAndNext(
        java.lang.Long debateItemPK, java.lang.Long debateItemId,
        java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdVersion(
        java.lang.Long debateItemId, java.lang.Long itemVersion)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdVersion(
        java.lang.Long debateItemId, java.lang.Long itemVersion, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findBybyIdVersion(
        java.lang.Long debateItemId, java.lang.Long itemVersion, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem findBybyIdVersion_First(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem findBybyIdVersion_Last(
        java.lang.Long debateItemId, java.lang.Long itemVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem[] findBybyIdVersion_PrevAndNext(
        java.lang.Long debateItemPK, java.lang.Long debateItemId,
        java.lang.Long itemVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByactiveIdInTreeVersion(java.lang.Long debateItemId,
        java.lang.Long treeVersion, java.util.Date updated)
        throws com.liferay.portal.SystemException;

    public void removeBybyIdStatus(java.lang.Long debateItemId,
        java.lang.String status) throws com.liferay.portal.SystemException;

    public void removeBybyIdVersion(java.lang.Long debateItemId,
        java.lang.Long itemVersion) throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByactiveIdInTreeVersion(java.lang.Long debateItemId,
        java.lang.Long treeVersion, java.util.Date updated)
        throws com.liferay.portal.SystemException;

    public int countBybyIdStatus(java.lang.Long debateItemId,
        java.lang.String status) throws com.liferay.portal.SystemException;

    public int countBybyIdVersion(java.lang.Long debateItemId,
        java.lang.Long itemVersion) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
