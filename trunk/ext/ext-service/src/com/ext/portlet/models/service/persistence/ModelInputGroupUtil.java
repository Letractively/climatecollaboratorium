package com.ext.portlet.models.service.persistence;

public class ModelInputGroupUtil {
    private static ModelInputGroupPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup) {
        getPersistence().cacheResult(modelInputGroup);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelInputGroup> modelInputGroups) {
        getPersistence().cacheResult(modelInputGroups);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelInputGroup create(
        java.lang.Long modelInputGroupPK) {
        return getPersistence().create(modelInputGroupPK);
    }

    public static com.ext.portlet.models.model.ModelInputGroup remove(
        java.lang.Long modelInputGroupPK)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelInputGroupPK);
    }

    public static com.ext.portlet.models.model.ModelInputGroup remove(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelInputGroup);
    }

    /**
     * @deprecated Use <code>update(ModelInputGroup modelInputGroup, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelInputGroup update(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelInputGroup);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelInputGroup the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelInputGroup is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.models.model.ModelInputGroup update(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(modelInputGroup, merge);
    }

    public static com.ext.portlet.models.model.ModelInputGroup updateImpl(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelInputGroup, merge);
    }

    public static com.ext.portlet.models.model.ModelInputGroup findByPrimaryKey(
        java.lang.Long modelInputGroupPK)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelInputGroupPK);
    }

    public static com.ext.portlet.models.model.ModelInputGroup fetchByPrimaryKey(
        java.lang.Long modelInputGroupPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelInputGroupPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByparentModelId(
        java.lang.Long parentGroupPK) throws com.liferay.portal.SystemException {
        return getPersistence().findByparentModelId(parentGroupPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByparentModelId(
        java.lang.Long parentGroupPK, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByparentModelId(parentGroupPK, start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByparentModelId(
        java.lang.Long parentGroupPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByparentModelId(parentGroupPK, start, end, obc);
    }

    public static com.ext.portlet.models.model.ModelInputGroup findByparentModelId_First(
        java.lang.Long parentGroupPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException {
        return getPersistence().findByparentModelId_First(parentGroupPK, obc);
    }

    public static com.ext.portlet.models.model.ModelInputGroup findByparentModelId_Last(
        java.lang.Long parentGroupPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException {
        return getPersistence().findByparentModelId_Last(parentGroupPK, obc);
    }

    public static com.ext.portlet.models.model.ModelInputGroup[] findByparentModelId_PrevAndNext(
        java.lang.Long modelInputGroupPK, java.lang.Long parentGroupPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByparentModelId_PrevAndNext(modelInputGroupPK,
            parentGroupPK, obc);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        java.lang.Long modelId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId, start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        java.lang.Long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId, start, end, obc);
    }

    public static com.ext.portlet.models.model.ModelInputGroup findByModelId_First(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelId_First(modelId, obc);
    }

    public static com.ext.portlet.models.model.ModelInputGroup findByModelId_Last(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelId_Last(modelId, obc);
    }

    public static com.ext.portlet.models.model.ModelInputGroup[] findByModelId_PrevAndNext(
        java.lang.Long modelInputGroupPK, java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByModelId_PrevAndNext(modelInputGroupPK, modelId, obc);
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

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByparentModelId(java.lang.Long parentGroupPK)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByparentModelId(parentGroupPK);
    }

    public static void removeByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByModelId(modelId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByparentModelId(java.lang.Long parentGroupPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByparentModelId(parentGroupPK);
    }

    public static int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByModelId(modelId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ModelInputGroupPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelInputGroupPersistence persistence) {
        _persistence = persistence;
    }
}
