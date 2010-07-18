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
        com.ext.portlet.models.service.persistence.ModelPositionPK modelPositionPK) {
        return getPersistence().create(modelPositionPK);
    }

    public static com.ext.portlet.models.model.ModelPosition remove(
        com.ext.portlet.models.service.persistence.ModelPositionPK modelPositionPK)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelPositionPK);
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
        com.ext.portlet.models.service.persistence.ModelPositionPK modelPositionPK)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelPositionPK);
    }

    public static com.ext.portlet.models.model.ModelPosition fetchByPrimaryKey(
        com.ext.portlet.models.service.persistence.ModelPositionPK modelPositionPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelPositionPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> findByPositionId(
        java.lang.Long positionId) throws com.liferay.portal.SystemException {
        return getPersistence().findByPositionId(positionId);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> findByPositionId(
        java.lang.Long positionId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPositionId(positionId, start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> findByPositionId(
        java.lang.Long positionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByPositionId(positionId, start, end, obc);
    }

    public static com.ext.portlet.models.model.ModelPosition findByPositionId_First(
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPositionId_First(positionId, obc);
    }

    public static com.ext.portlet.models.model.ModelPosition findByPositionId_Last(
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPositionId_Last(positionId, obc);
    }

    public static com.ext.portlet.models.model.ModelPosition[] findByPositionId_PrevAndNext(
        com.ext.portlet.models.service.persistence.ModelPositionPK modelPositionPK,
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelPositionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPositionId_PrevAndNext(modelPositionPK, positionId,
            obc);
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

    public static void removeByPositionId(java.lang.Long positionId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByPositionId(positionId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPositionId(java.lang.Long positionId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByPositionId(positionId);
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
