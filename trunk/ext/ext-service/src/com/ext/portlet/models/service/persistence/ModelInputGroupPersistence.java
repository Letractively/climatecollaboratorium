package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelInputGroupPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelInputGroup> modelInputGroups);

    public void clearCache();

    public com.ext.portlet.models.model.ModelInputGroup create(
        java.lang.Long modelInputGroupPK);

    public com.ext.portlet.models.model.ModelInputGroup remove(
        java.lang.Long modelInputGroupPK)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputGroup remove(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelInputGroup modelInputGroup, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelInputGroup update(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelInputGroup the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelInputGroup is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.models.model.ModelInputGroup update(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputGroup updateImpl(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputGroup findByPrimaryKey(
        java.lang.Long modelInputGroupPK)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputGroup fetchByPrimaryKey(
        java.lang.Long modelInputGroupPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        java.lang.Long modelId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        java.lang.Long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputGroup findByModelId_First(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputGroup findByModelId_Last(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelInputGroup[] findByModelId_PrevAndNext(
        java.lang.Long modelInputGroupPK, java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
