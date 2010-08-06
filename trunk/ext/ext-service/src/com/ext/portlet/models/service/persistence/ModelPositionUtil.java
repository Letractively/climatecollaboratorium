package com.ext.portlet.models.service.persistence;

public class ModelPositionUtil {
    private static ModelPositionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelPosition modelPosition) {
        getPersistence().cacheResult(modelPosition);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelPosition> modelPositions) {
        getPersistence().cacheResult(modelPositions);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelPosition create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.models.model.ModelPosition remove(
        java.lang.Long id)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.models.model.ModelPosition remove(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelPosition);
    }

    /**
     * @deprecated Use <code>update(ModelPosition modelPosition, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelPosition update(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelPosition);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.models.model.ModelPosition update(
        com.ext.portlet.models.model.ModelPosition modelPosition, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelPosition, merge);
    }

    public static com.ext.portlet.models.model.ModelPosition updateImpl(
        com.ext.portlet.models.model.ModelPosition modelPosition, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelPosition, merge);
    }

    public static com.ext.portlet.models.model.ModelPosition findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.models.model.ModelPosition fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> findByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> findByModelId(
        java.lang.Long modelId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId, start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> findByModelId(
        java.lang.Long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId, start, end, obc);
    }

    public static com.ext.portlet.models.model.ModelPosition findByModelId_First(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelId_First(modelId, obc);
    }

    public static com.ext.portlet.models.model.ModelPosition findByModelId_Last(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelId_Last(modelId, obc);
    }

    public static com.ext.portlet.models.model.ModelPosition[] findByModelId_PrevAndNext(
        java.lang.Long id, java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelId_PrevAndNext(id, modelId, obc);
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

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByModelId(modelId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByModelId(modelId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ModelPositionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelPositionPersistence persistence) {
        _persistence = persistence;
    }
}
