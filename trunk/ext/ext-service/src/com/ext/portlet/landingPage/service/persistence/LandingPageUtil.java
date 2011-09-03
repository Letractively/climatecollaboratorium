package com.ext.portlet.landingPage.service.persistence;

public class LandingPageUtil {
    private static LandingPagePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.landingPage.model.LandingPage landingPage) {
        getPersistence().cacheResult(landingPage);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.landingPage.model.LandingPage> landingPages) {
        getPersistence().cacheResult(landingPages);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.landingPage.model.LandingPage create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.landingPage.model.LandingPage remove(
        java.lang.Long id)
        throws com.ext.portlet.landingPage.NoSuchLandingPageException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.landingPage.model.LandingPage remove(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(landingPage);
    }

    /**
     * @deprecated Use <code>update(LandingPage landingPage, boolean merge)</code>.
     */
    public static com.ext.portlet.landingPage.model.LandingPage update(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(landingPage);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                landingPage the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when landingPage is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.landingPage.model.LandingPage update(
        com.ext.portlet.landingPage.model.LandingPage landingPage, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(landingPage, merge);
    }

    public static com.ext.portlet.landingPage.model.LandingPage updateImpl(
        com.ext.portlet.landingPage.model.LandingPage landingPage, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(landingPage, merge);
    }

    public static com.ext.portlet.landingPage.model.LandingPage findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.landingPage.NoSuchLandingPageException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.landingPage.model.LandingPage fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
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

    public static java.util.List<com.ext.portlet.landingPage.model.LandingPage> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.landingPage.model.LandingPage> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.landingPage.model.LandingPage> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static LandingPagePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(LandingPagePersistence persistence) {
        _persistence = persistence;
    }
}
