package com.ext.portlet.ontology.service.persistence;

public class OntologyTermUtil {
    private static OntologyTermPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm) {
        getPersistence().cacheResult(ontologyTerm);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.OntologyTerm> ontologyTerms) {
        getPersistence().cacheResult(ontologyTerms);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.ontology.model.OntologyTerm create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm remove(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm remove(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(ontologyTerm);
    }

    /**
     * @deprecated Use <code>update(OntologyTerm ontologyTerm, boolean merge)</code>.
     */
    public static com.ext.portlet.ontology.model.OntologyTerm update(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(ontologyTerm);
    }

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
    public static com.ext.portlet.ontology.model.OntologyTerm update(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(ontologyTerm, merge);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm updateImpl(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(ontologyTerm, merge);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentId(
        java.lang.Long parentId) throws com.liferay.portal.SystemException {
        return getPersistence().findByParentId(parentId);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentId(
        java.lang.Long parentId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByParentId(parentId, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentId(
        java.lang.Long parentId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByParentId(parentId, start, end, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm findByParentId_First(
        java.lang.Long parentId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByParentId_First(parentId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm findByParentId_Last(
        java.lang.Long parentId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByParentId_Last(parentId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm[] findByParentId_PrevAndNext(
        java.lang.Long id, java.lang.Long parentId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByParentId_PrevAndNext(id, parentId, obc);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentIdSpaceId(
        java.lang.Long parentId, java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByParentIdSpaceId(parentId, ontologySpaceId);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentIdSpaceId(
        java.lang.Long parentId, java.lang.Long ontologySpaceId, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByParentIdSpaceId(parentId, ontologySpaceId, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentIdSpaceId(
        java.lang.Long parentId, java.lang.Long ontologySpaceId, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByParentIdSpaceId(parentId, ontologySpaceId, start,
            end, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm findByParentIdSpaceId_First(
        java.lang.Long parentId, java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByParentIdSpaceId_First(parentId, ontologySpaceId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm findByParentIdSpaceId_Last(
        java.lang.Long parentId, java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByParentIdSpaceId_Last(parentId, ontologySpaceId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm[] findByParentIdSpaceId_PrevAndNext(
        java.lang.Long id, java.lang.Long parentId,
        java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByParentIdSpaceId_PrevAndNext(id, parentId,
            ontologySpaceId, obc);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findBySpaceId(
        java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBySpaceId(ontologySpaceId);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findBySpaceId(
        java.lang.Long ontologySpaceId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBySpaceId(ontologySpaceId, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findBySpaceId(
        java.lang.Long ontologySpaceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBySpaceId(ontologySpaceId, start, end, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm findBySpaceId_First(
        java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findBySpaceId_First(ontologySpaceId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm findBySpaceId_Last(
        java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findBySpaceId_Last(ontologySpaceId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm[] findBySpaceId_PrevAndNext(
        java.lang.Long id, java.lang.Long ontologySpaceId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBySpaceId_PrevAndNext(id, ontologySpaceId, obc);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByName(
        java.lang.String name) throws com.liferay.portal.SystemException {
        return getPersistence().findByName(name);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByName(
        java.lang.String name, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByName(name, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByName(
        java.lang.String name, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByName(name, start, end, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm findByName_First(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByName_First(name, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm findByName_Last(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByName_Last(name, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm[] findByName_PrevAndNext(
        java.lang.Long id, java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByName_PrevAndNext(id, name, obc);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByParentId(java.lang.Long parentId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByParentId(parentId);
    }

    public static void removeByParentIdSpaceId(java.lang.Long parentId,
        java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByParentIdSpaceId(parentId, ontologySpaceId);
    }

    public static void removeBySpaceId(java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeBySpaceId(ontologySpaceId);
    }

    public static void removeByName(java.lang.String name)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByName(name);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByParentId(java.lang.Long parentId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByParentId(parentId);
    }

    public static int countByParentIdSpaceId(java.lang.Long parentId,
        java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByParentIdSpaceId(parentId, ontologySpaceId);
    }

    public static int countBySpaceId(java.lang.Long ontologySpaceId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBySpaceId(ontologySpaceId);
    }

    public static int countByName(java.lang.String name)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByName(name);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static OntologyTermPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(OntologyTermPersistence persistence) {
        _persistence = persistence;
    }
}
