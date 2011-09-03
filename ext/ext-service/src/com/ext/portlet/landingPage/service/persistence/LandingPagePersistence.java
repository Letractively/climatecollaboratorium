package com.ext.portlet.landingPage.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface LandingPagePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.landingPage.model.LandingPage landingPage);

    public void cacheResult(
        java.util.List<com.ext.portlet.landingPage.model.LandingPage> landingPages);

    public void clearCache();

    public com.ext.portlet.landingPage.model.LandingPage create(
        java.lang.Long id);

    public com.ext.portlet.landingPage.model.LandingPage remove(
        java.lang.Long id)
        throws com.ext.portlet.landingPage.NoSuchLandingPageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.landingPage.model.LandingPage remove(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(LandingPage landingPage, boolean merge)</code>.
     */
    public com.ext.portlet.landingPage.model.LandingPage update(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.landingPage.model.LandingPage update(
        com.ext.portlet.landingPage.model.LandingPage landingPage, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.landingPage.model.LandingPage updateImpl(
        com.ext.portlet.landingPage.model.LandingPage landingPage, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.landingPage.model.LandingPage findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.landingPage.NoSuchLandingPageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.landingPage.model.LandingPage fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.landingPage.model.LandingPage> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.landingPage.model.LandingPage> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.landingPage.model.LandingPage> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
