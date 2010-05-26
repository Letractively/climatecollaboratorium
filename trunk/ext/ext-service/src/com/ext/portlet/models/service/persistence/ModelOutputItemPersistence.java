package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelOutputItemPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelOutputItem> modelOutputItems);

    public void clearCache();

    public com.ext.portlet.models.model.ModelOutputItem create(
        java.lang.Long modelOutputItemModifierPK);

    public com.ext.portlet.models.model.ModelOutputItem remove(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItem remove(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelOutputItem modelOutputItem, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelOutputItem update(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelOutputItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelOutputItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.models.model.ModelOutputItem update(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItem updateImpl(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItem findByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItem fetchByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItem findByModelOutputId(
        java.lang.Long modelOutputItemId)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItem fetchByModelOutputId(
        java.lang.Long modelOutputItemId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItem fetchByModelOutputId(
        java.lang.Long modelOutputItemId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelOutputItem> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelOutputItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelOutputItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByModelOutputId(java.lang.Long modelOutputItemId)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByModelOutputId(java.lang.Long modelOutputItemId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
