package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelOutputItemModifierPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelOutputItemModifier> modelOutputItemModifiers);

    public void clearCache();

    public com.ext.portlet.models.model.ModelOutputItemModifier create(
        java.lang.Long modelOutputItemModifierPK);

    public com.ext.portlet.models.model.ModelOutputItemModifier remove(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemModifierException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItemModifier remove(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelOutputItemModifier modelOutputItemModifier, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelOutputItemModifier update(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelOutputItemModifier the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelOutputItemModifier is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.models.model.ModelOutputItemModifier update(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItemModifier updateImpl(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItemModifier findByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemModifierException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItemModifier fetchByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelOutputItemModifier> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelOutputItemModifier> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelOutputItemModifier> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
