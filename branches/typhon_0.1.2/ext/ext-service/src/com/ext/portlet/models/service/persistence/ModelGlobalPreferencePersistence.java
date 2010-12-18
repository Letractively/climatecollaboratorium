package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelGlobalPreferencePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> modelGlobalPreferences);

    public void clearCache();

    public com.ext.portlet.models.model.ModelGlobalPreference create(
        java.lang.Long modelGlobalPreferencePK);

    public com.ext.portlet.models.model.ModelGlobalPreference remove(
        java.lang.Long modelGlobalPreferencePK)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference remove(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelGlobalPreference modelGlobalPreference, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelGlobalPreference update(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelGlobalPreference the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelGlobalPreference is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.models.model.ModelGlobalPreference update(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference updateImpl(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference findByPrimaryKey(
        java.lang.Long modelGlobalPreferencePK)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference fetchByPrimaryKey(
        java.lang.Long modelGlobalPreferencePK)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference findByModelId(
        java.lang.Long modelId)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference fetchByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference fetchByModelId(
        java.lang.Long modelId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findByModelCategoryId(
        java.lang.Long modelCategoryId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findByModelCategoryId(
        java.lang.Long modelCategoryId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findByModelCategoryId(
        java.lang.Long modelCategoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference findByModelCategoryId_First(
        java.lang.Long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference findByModelCategoryId_Last(
        java.lang.Long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference[] findByModelCategoryId_PrevAndNext(
        java.lang.Long modelGlobalPreferencePK, java.lang.Long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByModelId(java.lang.Long modelId)
        throws com.ext.portlet.models.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.SystemException;

    public void removeByModelCategoryId(java.lang.Long modelCategoryId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException;

    public int countByModelCategoryId(java.lang.Long modelCategoryId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
