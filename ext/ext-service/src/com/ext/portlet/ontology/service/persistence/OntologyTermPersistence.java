package com.ext.portlet.ontology.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface OntologyTermPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm);

    public void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.OntologyTerm> ontologyTerms);

    public void clearCache();

    public com.ext.portlet.ontology.model.OntologyTerm create(java.lang.Long id);

    public com.ext.portlet.ontology.model.OntologyTerm remove(java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm remove(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(OntologyTerm ontologyTerm, boolean merge)</code>.
     */
    public com.ext.portlet.ontology.model.OntologyTerm update(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                ontologyTerm the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when ontologyTerm is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.ontology.model.OntologyTerm update(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm updateImpl(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentId(
        java.lang.Long parentId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentId(
        java.lang.Long parentId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentId(
        java.lang.Long parentId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm findByParentId_First(
        java.lang.Long parentId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm findByParentId_Last(
        java.lang.Long parentId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm[] findByParentId_PrevAndNext(
        java.lang.Long id, java.lang.Long parentId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentIdSpaceId(
        java.lang.Long parentId, java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentIdSpaceId(
        java.lang.Long parentId, java.lang.Long ontologySpaceId, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentIdSpaceId(
        java.lang.Long parentId, java.lang.Long ontologySpaceId, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm findByParentIdSpaceId_First(
        java.lang.Long parentId, java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm findByParentIdSpaceId_Last(
        java.lang.Long parentId, java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm[] findByParentIdSpaceId_PrevAndNext(
        java.lang.Long id, java.lang.Long parentId,
        java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findBySpaceId(
        java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findBySpaceId(
        java.lang.Long ontologySpaceId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findBySpaceId(
        java.lang.Long ontologySpaceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm findBySpaceId_First(
        java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm findBySpaceId_Last(
        java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm[] findBySpaceId_PrevAndNext(
        java.lang.Long id, java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByName(
        java.lang.String name) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByName(
        java.lang.String name, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByName(
        java.lang.String name, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm findByName_First(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm findByName_Last(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm[] findByName_PrevAndNext(
        java.lang.Long id, java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByParentId(java.lang.Long parentId)
        throws com.liferay.portal.SystemException;

    public void removeByParentIdSpaceId(java.lang.Long parentId,
        java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException;

    public void removeBySpaceId(java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException;

    public void removeByName(java.lang.String name)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByParentId(java.lang.Long parentId)
        throws com.liferay.portal.SystemException;

    public int countByParentIdSpaceId(java.lang.Long parentId,
        java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException;

    public int countBySpaceId(java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException;

    public int countByName(java.lang.String name)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
