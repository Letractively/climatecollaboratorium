package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelPositionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelPosition modelPosition);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelPosition> modelPositions);

    public void clearCache();

    public com.ext.portlet.models.model.ModelPosition create(java.lang.Long id);

    public com.ext.portlet.models.model.ModelPosition remove(java.lang.Long id)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelPosition remove(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelPosition modelPosition, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelPosition update(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.models.model.ModelPosition update(
        com.ext.portlet.models.model.ModelPosition modelPosition, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelPosition updateImpl(
        com.ext.portlet.models.model.ModelPosition modelPosition, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelPosition findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelPosition fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelPosition> findByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelPosition> findByModelId(
        java.lang.Long modelId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelPosition> findByModelId(
        java.lang.Long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelPosition findByModelId_First(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelPosition findByModelId_Last(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelPosition[] findByModelId_PrevAndNext(
        java.lang.Long id, java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelPosition> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelPosition> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelPosition> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
