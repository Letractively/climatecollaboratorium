package com.ext.portlet.ontology.service.persistence;

public class FocusAreaOntologyTermUtil {
    private static FocusAreaOntologyTermPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm) {
        getPersistence().cacheResult(focusAreaOntologyTerm);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> focusAreaOntologyTerms) {
        getPersistence().cacheResult(focusAreaOntologyTerms);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm create(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK) {
        return getPersistence().create(focusAreaOntologyTermPK);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm remove(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(focusAreaOntologyTermPK);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm remove(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(focusAreaOntologyTerm);
    }

    /**
     * @deprecated Use <code>update(FocusAreaOntologyTerm focusAreaOntologyTerm, boolean merge)</code>.
     */
    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm update(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(focusAreaOntologyTerm);
    }

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
    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm update(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(focusAreaOntologyTerm, merge);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm updateImpl(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(focusAreaOntologyTerm, merge);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm findByPrimaryKey(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(focusAreaOntologyTermPK);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm fetchByPrimaryKey(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(focusAreaOntologyTermPK);
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findByFocusAreaId(
        java.lang.Long focusAreaId) throws com.liferay.portal.SystemException {
        return getPersistence().findByFocusAreaId(focusAreaId);
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findByFocusAreaId(
        java.lang.Long focusAreaId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByFocusAreaId(focusAreaId, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findByFocusAreaId(
        java.lang.Long focusAreaId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByFocusAreaId(focusAreaId, start, end, obc);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm findByFocusAreaId_First(
        java.lang.Long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByFocusAreaId_First(focusAreaId, obc);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm findByFocusAreaId_Last(
        java.lang.Long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByFocusAreaId_Last(focusAreaId, obc);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm[] findByFocusAreaId_PrevAndNext(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK,
        java.lang.Long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchFocusAreaOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByFocusAreaId_PrevAndNext(focusAreaOntologyTermPK,
            focusAreaId, obc);
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

    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByFocusAreaId(java.lang.Long focusAreaId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByFocusAreaId(focusAreaId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByFocusAreaId(java.lang.Long focusAreaId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByFocusAreaId(focusAreaId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static FocusAreaOntologyTermPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(FocusAreaOntologyTermPersistence persistence) {
        _persistence = persistence;
    }
}
