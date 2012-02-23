package com.ext.portlet.ontology.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface CategoryOntologyTermPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm);

    public void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> categoryOntologyTerms);

    public void clearCache();

    public com.ext.portlet.ontology.model.CategoryOntologyTerm create(
        CategoryOntologyTermPK categoryOntologyTermPK);

    public com.ext.portlet.ontology.model.CategoryOntologyTerm remove(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.CategoryOntologyTerm remove(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(CategoryOntologyTerm categoryOntologyTerm, boolean merge)</code>.
     */
    public com.ext.portlet.ontology.model.CategoryOntologyTerm update(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                categoryOntologyTerm the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when categoryOntologyTerm is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.ontology.model.CategoryOntologyTerm update(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.CategoryOntologyTerm updateImpl(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.CategoryOntologyTerm findByPrimaryKey(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.CategoryOntologyTerm fetchByPrimaryKey(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findByCategoryId(
        java.lang.Long categoryId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findByCategoryId(
        java.lang.Long categoryId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findByCategoryId(
        java.lang.Long categoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.CategoryOntologyTerm findByCategoryId_First(
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.CategoryOntologyTerm findByCategoryId_Last(
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.CategoryOntologyTerm[] findByCategoryId_PrevAndNext(
        CategoryOntologyTermPK categoryOntologyTermPK,
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByCategoryId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByCategoryId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
