package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlansFilterPositionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> plansFilterPositions);

    public void clearCache();

    public com.ext.portlet.plans.model.PlansFilterPosition create(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK);

    public com.ext.portlet.plans.model.PlansFilterPosition remove(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.ext.portlet.plans.NoSuchFilterPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansFilterPosition remove(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlansFilterPosition plansFilterPosition, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlansFilterPosition update(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plansFilterPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plansFilterPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlansFilterPosition update(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansFilterPosition updateImpl(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansFilterPosition findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.ext.portlet.plans.NoSuchFilterPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansFilterPosition fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
