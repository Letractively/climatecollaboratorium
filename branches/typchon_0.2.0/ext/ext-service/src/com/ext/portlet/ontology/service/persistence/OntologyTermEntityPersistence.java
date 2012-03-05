package com.ext.portlet.ontology.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface OntologyTermEntityPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity);

    public void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> ontologyTermEntities);

    public void clearCache();

    public com.ext.portlet.ontology.model.OntologyTermEntity create(
        java.lang.Long id);

    public com.ext.portlet.ontology.model.OntologyTermEntity remove(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity remove(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(OntologyTermEntity ontologyTermEntity, boolean merge)</code>.
     */
    public com.ext.portlet.ontology.model.OntologyTermEntity update(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                ontologyTermEntity the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when ontologyTermEntity is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.ontology.model.OntologyTermEntity update(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity updateImpl(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameId(
        java.lang.Long classNameId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameId(
        java.lang.Long classNameId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameId(
        java.lang.Long classNameId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameId_First(
        java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameId_Last(
        java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity[] findByClassNameId_PrevAndNext(
        java.lang.Long id, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameIdClassPk(
        java.lang.Long classNameId, java.lang.Long classPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameIdClassPk(
        java.lang.Long classNameId, java.lang.Long classPK, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameIdClassPk(
        java.lang.Long classNameId, java.lang.Long classPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameIdClassPk_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameIdClassPk_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity[] findByClassNameIdClassPk_PrevAndNext(
        java.lang.Long id, java.lang.Long classNameId, java.lang.Long classPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermId(
        java.lang.Long termId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermId(
        java.lang.Long termId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermId(
        java.lang.Long termId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity findByTermId_First(
        java.lang.Long termId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity findByTermId_Last(
        java.lang.Long termId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity[] findByTermId_PrevAndNext(
        java.lang.Long id, java.lang.Long termId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermIdClassNameId(
        java.lang.Long termId, java.lang.Long classNameId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermIdClassNameId(
        java.lang.Long termId, java.lang.Long classNameId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermIdClassNameId(
        java.lang.Long termId, java.lang.Long classNameId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity findByTermIdClassNameId_First(
        java.lang.Long termId, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity findByTermIdClassNameId_Last(
        java.lang.Long termId, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity[] findByTermIdClassNameId_PrevAndNext(
        java.lang.Long id, java.lang.Long termId, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByClassNameId(java.lang.Long classNameId)
        throws com.liferay.portal.SystemException;

    public void removeByClassNameIdClassPk(java.lang.Long classNameId,
        java.lang.Long classPK) throws com.liferay.portal.SystemException;

    public void removeByTermId(java.lang.Long termId)
        throws com.liferay.portal.SystemException;

    public void removeByTermIdClassNameId(java.lang.Long termId,
        java.lang.Long classNameId) throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByClassNameId(java.lang.Long classNameId)
        throws com.liferay.portal.SystemException;

    public int countByClassNameIdClassPk(java.lang.Long classNameId,
        java.lang.Long classPK) throws com.liferay.portal.SystemException;

    public int countByTermId(java.lang.Long termId)
        throws com.liferay.portal.SystemException;

    public int countByTermIdClassNameId(java.lang.Long termId,
        java.lang.Long classNameId) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
