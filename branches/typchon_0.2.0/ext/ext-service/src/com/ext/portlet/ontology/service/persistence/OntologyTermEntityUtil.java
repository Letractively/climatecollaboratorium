package com.ext.portlet.ontology.service.persistence;

public class OntologyTermEntityUtil {
    private static OntologyTermEntityPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity) {
        getPersistence().cacheResult(ontologyTermEntity);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> ontologyTermEntities) {
        getPersistence().cacheResult(ontologyTermEntities);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity remove(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity remove(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(ontologyTermEntity);
    }

    /**
     * @deprecated Use <code>update(OntologyTermEntity ontologyTermEntity, boolean merge)</code>.
     */
    public static com.ext.portlet.ontology.model.OntologyTermEntity update(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(ontologyTermEntity);
    }

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
    public static com.ext.portlet.ontology.model.OntologyTermEntity update(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(ontologyTermEntity, merge);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity updateImpl(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(ontologyTermEntity, merge);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameId(
        java.lang.Long classNameId) throws com.liferay.portal.SystemException {
        return getPersistence().findByClassNameId(classNameId);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameId(
        java.lang.Long classNameId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByClassNameId(classNameId, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameId(
        java.lang.Long classNameId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByClassNameId(classNameId, start, end, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameId_First(
        java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence().findByClassNameId_First(classNameId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameId_Last(
        java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence().findByClassNameId_Last(classNameId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity[] findByClassNameId_PrevAndNext(
        java.lang.Long id, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameId_PrevAndNext(id, classNameId, obc);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameIdClassPk(
        java.lang.Long classNameId, java.lang.Long classPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByClassNameIdClassPk(classNameId, classPK);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameIdClassPk(
        java.lang.Long classNameId, java.lang.Long classPK, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk(classNameId, classPK, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByClassNameIdClassPk(
        java.lang.Long classNameId, java.lang.Long classPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk(classNameId, classPK, start, end,
            obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameIdClassPk_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk_First(classNameId, classPK, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity findByClassNameIdClassPk_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk_Last(classNameId, classPK, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity[] findByClassNameIdClassPk_PrevAndNext(
        java.lang.Long id, java.lang.Long classNameId, java.lang.Long classPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPk_PrevAndNext(id, classNameId,
            classPK, obc);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermId(
        java.lang.Long termId) throws com.liferay.portal.SystemException {
        return getPersistence().findByTermId(termId);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermId(
        java.lang.Long termId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByTermId(termId, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermId(
        java.lang.Long termId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByTermId(termId, start, end, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity findByTermId_First(
        java.lang.Long termId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence().findByTermId_First(termId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity findByTermId_Last(
        java.lang.Long termId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence().findByTermId_Last(termId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity[] findByTermId_PrevAndNext(
        java.lang.Long id, java.lang.Long termId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence().findByTermId_PrevAndNext(id, termId, obc);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermIdClassNameId(
        java.lang.Long termId, java.lang.Long classNameId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByTermIdClassNameId(termId, classNameId);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermIdClassNameId(
        java.lang.Long termId, java.lang.Long classNameId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId(termId, classNameId, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findByTermIdClassNameId(
        java.lang.Long termId, java.lang.Long classNameId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId(termId, classNameId, start, end, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity findByTermIdClassNameId_First(
        java.lang.Long termId, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId_First(termId, classNameId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity findByTermIdClassNameId_Last(
        java.lang.Long termId, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId_Last(termId, classNameId, obc);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity[] findByTermIdClassNameId_PrevAndNext(
        java.lang.Long id, java.lang.Long termId, java.lang.Long classNameId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchOntologyTermEntityException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByTermIdClassNameId_PrevAndNext(id, termId,
            classNameId, obc);
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

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByClassNameId(java.lang.Long classNameId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByClassNameId(classNameId);
    }

    public static void removeByClassNameIdClassPk(java.lang.Long classNameId,
        java.lang.Long classPK) throws com.liferay.portal.SystemException {
        getPersistence().removeByClassNameIdClassPk(classNameId, classPK);
    }

    public static void removeByTermId(java.lang.Long termId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByTermId(termId);
    }

    public static void removeByTermIdClassNameId(java.lang.Long termId,
        java.lang.Long classNameId) throws com.liferay.portal.SystemException {
        getPersistence().removeByTermIdClassNameId(termId, classNameId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByClassNameId(java.lang.Long classNameId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByClassNameId(classNameId);
    }

    public static int countByClassNameIdClassPk(java.lang.Long classNameId,
        java.lang.Long classPK) throws com.liferay.portal.SystemException {
        return getPersistence().countByClassNameIdClassPk(classNameId, classPK);
    }

    public static int countByTermId(java.lang.Long termId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByTermId(termId);
    }

    public static int countByTermIdClassNameId(java.lang.Long termId,
        java.lang.Long classNameId) throws com.liferay.portal.SystemException {
        return getPersistence().countByTermIdClassNameId(termId, classNameId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static OntologyTermEntityPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(OntologyTermEntityPersistence persistence) {
        _persistence = persistence;
    }
}
