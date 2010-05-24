package com.ext.portlet.models.service.persistence;

public class ModelOutputItemModifierUtil {
    private static ModelOutputItemModifierPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier) {
        getPersistence().cacheResult(modelOutputItemModifier);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelOutputItemModifier> modelOutputItemModifiers) {
        getPersistence().cacheResult(modelOutputItemModifiers);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelOutputItemModifier create(
        java.lang.Long modelOutputItemModifierPK) {
        return getPersistence().create(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItemModifier remove(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemModifierException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItemModifier remove(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelOutputItemModifier);
    }

    /**
     * @deprecated Use <code>update(ModelOutputItemModifier modelOutputItemModifier, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelOutputItemModifier update(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelOutputItemModifier);
    }

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
    public static com.ext.portlet.models.model.ModelOutputItemModifier update(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(modelOutputItemModifier, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputItemModifier updateImpl(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelOutputItemModifier, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputItemModifier findByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemModifierException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.models.model.ModelOutputItemModifier fetchByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelOutputItemModifierPK);
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

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItemModifier> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItemModifier> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItemModifier> findAll(
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

    public static ModelOutputItemModifierPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelOutputItemModifierPersistence persistence) {
        _persistence = persistence;
    }
}
