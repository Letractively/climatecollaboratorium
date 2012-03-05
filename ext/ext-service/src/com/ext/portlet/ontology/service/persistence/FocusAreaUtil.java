package com.ext.portlet.ontology.service.persistence;

public class FocusAreaUtil {
    private static FocusAreaPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.ontology.model.FocusArea focusArea) {
        getPersistence().cacheResult(focusArea);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.FocusArea> focusAreas) {
        getPersistence().cacheResult(focusAreas);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.ontology.model.FocusArea create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.ontology.model.FocusArea remove(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.ontology.model.FocusArea remove(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(focusArea);
    }

    /**
     * @deprecated Use <code>update(FocusArea focusArea, boolean merge)</code>.
     */
    public static com.ext.portlet.ontology.model.FocusArea update(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(focusArea);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                focusArea the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when focusArea is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.ontology.model.FocusArea update(
        com.ext.portlet.ontology.model.FocusArea focusArea, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(focusArea, merge);
    }

    public static com.ext.portlet.ontology.model.FocusArea updateImpl(
        com.ext.portlet.ontology.model.FocusArea focusArea, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(focusArea, merge);
    }

    public static com.ext.portlet.ontology.model.FocusArea findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.ontology.model.FocusArea fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static com.ext.portlet.ontology.model.FocusArea findByName(
        java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.SystemException {
        return getPersistence().findByName(name);
    }

    public static com.ext.portlet.ontology.model.FocusArea fetchByName(
        java.lang.String name) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByName(name);
    }

    public static com.ext.portlet.ontology.model.FocusArea fetchByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByName(name, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.ontology.model.FocusArea> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusArea> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusArea> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByName(java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.SystemException {
        getPersistence().removeByName(name);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByName(java.lang.String name)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByName(name);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static FocusAreaPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(FocusAreaPersistence persistence) {
        _persistence = persistence;
    }
}
