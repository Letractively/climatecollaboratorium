package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelInputItemPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelInputItem modelInputItem);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelInputItem> modelInputItems);

    public void clearCache();

    public com.ext.portlet.models.model.ModelInputItem create(
        java.lang.Long modelInputItemPK);

    public com.ext.portlet.models.model.ModelInputItem remove(
        java.lang.Long modelInputItemPK)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem remove(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelInputItem modelInputItem, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelInputItem update(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelInputItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelInputItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.models.model.ModelInputItem update(
        com.ext.portlet.models.model.ModelInputItem modelInputItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem updateImpl(
        com.ext.portlet.models.model.ModelInputItem modelInputItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem findByPrimaryKey(
        java.lang.Long modelInputItemPK)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem fetchByPrimaryKey(
        java.lang.Long modelInputItemPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelGroupId(
        java.lang.Long modelGroupId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelGroupId(
        java.lang.Long modelGroupId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelGroupId(
        java.lang.Long modelGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem findByModelGroupId_First(
        java.lang.Long modelGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem findByModelGroupId_Last(
        java.lang.Long modelGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem[] findByModelGroupId_PrevAndNext(
        java.lang.Long modelInputItemPK, java.lang.Long modelGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem findByModelInputId(
        java.lang.Long modelInputItemID)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem fetchByModelInputId(
        java.lang.Long modelInputItemID)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem fetchByModelInputId(
        java.lang.Long modelInputItemID, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelId(
        java.lang.Long modelInputItemID)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelId(
        java.lang.Long modelInputItemID, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelId(
        java.lang.Long modelInputItemID, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem findByModelId_First(
        java.lang.Long modelInputItemID,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem findByModelId_Last(
        java.lang.Long modelInputItemID,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputItem[] findByModelId_PrevAndNext(
        java.lang.Long modelInputItemPK, java.lang.Long modelInputItemID,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByModelGroupId(java.lang.Long modelGroupId)
        throws com.liferay.portal.SystemException;

    public void removeByModelInputId(java.lang.Long modelInputItemID)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.SystemException;

    public void removeByModelId(java.lang.Long modelInputItemID)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByModelGroupId(java.lang.Long modelGroupId)
        throws com.liferay.portal.SystemException;

    public int countByModelInputId(java.lang.Long modelInputItemID)
        throws com.liferay.portal.SystemException;

    public int countByModelId(java.lang.Long modelInputItemID)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
