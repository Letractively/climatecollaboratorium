package com.ext.portlet.ontology.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface CategoryPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.ontology.model.Category category);

    public void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.Category> categories);

    public void clearCache();

    public com.ext.portlet.ontology.model.Category create(java.lang.Long id);

    public com.ext.portlet.ontology.model.Category remove(java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.Category remove(
        com.ext.portlet.ontology.model.Category category)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(Category category, boolean merge)</code>.
     */
    public com.ext.portlet.ontology.model.Category update(
        com.ext.portlet.ontology.model.Category category)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                category the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when category is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.ontology.model.Category update(
        com.ext.portlet.ontology.model.Category category, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.Category updateImpl(
        com.ext.portlet.ontology.model.Category category, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.Category findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.Category fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.Category findByName(
        java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.Category fetchByName(
        java.lang.String name) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.Category fetchByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.Category> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.Category> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.Category> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByName(java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchCategoryException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByName(java.lang.String name)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
