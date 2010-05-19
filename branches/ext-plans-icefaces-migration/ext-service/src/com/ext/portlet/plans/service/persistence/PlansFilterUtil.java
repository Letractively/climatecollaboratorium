package com.ext.portlet.plans.service.persistence;

public class PlansFilterUtil {
    private static PlansFilterPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlansFilter plansFilter) {
        getPersistence().cacheResult(plansFilter);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlansFilter> plansFilters) {
        getPersistence().cacheResult(plansFilters);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlansFilter create(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK) {
        return getPersistence().create(plansFilterPK);
    }

    public static com.ext.portlet.plans.model.PlansFilter remove(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.ext.portlet.plans.NoSuchFilterException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(plansFilterPK);
    }

    public static com.ext.portlet.plans.model.PlansFilter remove(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(plansFilter);
    }

    /**
     * @deprecated Use <code>update(PlansFilter plansFilter, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlansFilter update(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(plansFilter);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plansFilter the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plansFilter is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlansFilter update(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(plansFilter, merge);
    }

    public static com.ext.portlet.plans.model.PlansFilter updateImpl(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(plansFilter, merge);
    }

    public static com.ext.portlet.plans.model.PlansFilter findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.ext.portlet.plans.NoSuchFilterException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(plansFilterPK);
    }

    public static com.ext.portlet.plans.model.PlansFilter fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(plansFilterPK);
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

    public static java.util.List<com.ext.portlet.plans.model.PlansFilter> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilter> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilter> findAll(
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

    public static PlansFilterPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlansFilterPersistence persistence) {
        _persistence = persistence;
    }
}
