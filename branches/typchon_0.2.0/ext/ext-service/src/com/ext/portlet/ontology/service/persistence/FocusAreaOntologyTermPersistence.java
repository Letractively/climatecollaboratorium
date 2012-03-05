package com.ext.portlet.ontology.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface FocusAreaOntologyTermPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm);

    public void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> focusAreaOntologyTerms);

    public void clearCache();

    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm create(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK);

    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm remove(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm remove(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(FocusAreaOntologyTerm focusAreaOntologyTerm, boolean merge)</code>.
     */
    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm update(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                focusAreaOntologyTerm the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when focusAreaOntologyTerm is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm update(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm updateImpl(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm findByPrimaryKey(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm fetchByPrimaryKey(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findByFocusAreaId(
        java.lang.Long focusAreaId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findByFocusAreaId(
        java.lang.Long focusAreaId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findByFocusAreaId(
        java.lang.Long focusAreaId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm findByFocusAreaId_First(
        java.lang.Long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm findByFocusAreaId_Last(
        java.lang.Long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusAreaOntologyTerm[] findByFocusAreaId_PrevAndNext(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK,
        java.lang.Long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByFocusAreaId(java.lang.Long focusAreaId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByFocusAreaId(java.lang.Long focusAreaId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
