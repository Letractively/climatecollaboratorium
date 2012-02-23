package com.ext.portlet.ontology.service.persistence;

public class CategoryOntologyTermUtil {
    private static CategoryOntologyTermPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm) {
        getPersistence().cacheResult(categoryOntologyTerm);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> categoryOntologyTerms) {
        getPersistence().cacheResult(categoryOntologyTerms);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm create(
        CategoryOntologyTermPK categoryOntologyTermPK) {
        return getPersistence().create(categoryOntologyTermPK);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm remove(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(categoryOntologyTermPK);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm remove(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(categoryOntologyTerm);
    }

    /**
     * @deprecated Use <code>update(CategoryOntologyTerm categoryOntologyTerm, boolean merge)</code>.
     */
    public static com.ext.portlet.ontology.model.CategoryOntologyTerm update(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(categoryOntologyTerm);
    }

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
    public static com.ext.portlet.ontology.model.CategoryOntologyTerm update(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(categoryOntologyTerm, merge);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm updateImpl(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(categoryOntologyTerm, merge);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm findByPrimaryKey(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(categoryOntologyTermPK);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm fetchByPrimaryKey(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(categoryOntologyTermPK);
    }

    public static java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findByCategoryId(
        java.lang.Long categoryId) throws com.liferay.portal.SystemException {
        return getPersistence().findByCategoryId(categoryId);
    }

    public static java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findByCategoryId(
        java.lang.Long categoryId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByCategoryId(categoryId, start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findByCategoryId(
        java.lang.Long categoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByCategoryId(categoryId, start, end, obc);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm findByCategoryId_First(
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByCategoryId_First(categoryId, obc);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm findByCategoryId_Last(
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence().findByCategoryId_Last(categoryId, obc);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm[] findByCategoryId_PrevAndNext(
        CategoryOntologyTermPK categoryOntologyTermPK,
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.ontology.NoSuchCategoryOntologyTermException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCategoryId_PrevAndNext(categoryOntologyTermPK,
            categoryId, obc);
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

    public static java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByCategoryId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByCategoryId(categoryId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByCategoryId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByCategoryId(categoryId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static CategoryOntologyTermPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(CategoryOntologyTermPersistence persistence) {
        _persistence = persistence;
    }
}
