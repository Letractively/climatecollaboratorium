package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelCategoryPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelCategory modelCategory);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelCategory> modelCategories);

    public void clearCache();

    public com.ext.portlet.models.model.ModelCategory create(
        java.lang.Long modelCategoryPK);

    public com.ext.portlet.models.model.ModelCategory remove(
        java.lang.Long modelCategoryPK)
        throws com.ext.portlet.models.NoSuchModelCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelCategory remove(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelCategory modelCategory, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelCategory update(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.models.model.ModelCategory update(
        com.ext.portlet.models.model.ModelCategory modelCategory, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelCategory updateImpl(
        com.ext.portlet.models.model.ModelCategory modelCategory, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelCategory findByPrimaryKey(
        java.lang.Long modelCategoryPK)
        throws com.ext.portlet.models.NoSuchModelCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelCategory fetchByPrimaryKey(
        java.lang.Long modelCategoryPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelCategory> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelCategory> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelCategory> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
