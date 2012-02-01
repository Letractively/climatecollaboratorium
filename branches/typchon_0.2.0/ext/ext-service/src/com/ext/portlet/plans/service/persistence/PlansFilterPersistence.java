package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlansFilterPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.plans.model.PlansFilter plansFilter);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlansFilter> plansFilters);

    public void clearCache();

    public com.ext.portlet.plans.model.PlansFilter create(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK);

    public com.ext.portlet.plans.model.PlansFilter remove(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.ext.portlet.plans.NoSuchFilterException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansFilter remove(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlansFilter plansFilter, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlansFilter update(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.plans.model.PlansFilter update(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansFilter updateImpl(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansFilter findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.ext.portlet.plans.NoSuchFilterException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansFilter fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlansFilter> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlansFilter> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlansFilter> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
