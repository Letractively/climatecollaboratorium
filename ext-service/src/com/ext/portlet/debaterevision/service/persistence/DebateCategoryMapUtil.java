package com.ext.portlet.debaterevision.service.persistence;

public class DebateCategoryMapUtil {
    private static DebateCategoryMapPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap) {
        getPersistence().cacheResult(debateCategoryMap);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateCategoryMap> debateCategoryMaps) {
        getPersistence().cacheResult(debateCategoryMaps);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap create(
        java.lang.Long debateCategoryMapPK) {
        return getPersistence().create(debateCategoryMapPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap remove(
        java.lang.Long debateCategoryMapPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryMapException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateCategoryMapPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap remove(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateCategoryMap);
    }

    /**
     * @deprecated Use <code>update(DebateCategoryMap debateCategoryMap, boolean merge)</code>.
     */
    public static com.ext.portlet.debaterevision.model.DebateCategoryMap update(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateCategoryMap);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateCategoryMap the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateCategoryMap is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debaterevision.model.DebateCategoryMap update(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateCategoryMap, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap updateImpl(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateCategoryMap, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap findByPrimaryKey(
        java.lang.Long debateCategoryMapPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryMapException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateCategoryMapPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap fetchByPrimaryKey(
        java.lang.Long debateCategoryMapPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateCategoryMapPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap findBydebateIdCategoryId(
        java.lang.Long debateCategoryPK, java.lang.Long debateId)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryMapException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBydebateIdCategoryId(debateCategoryPK, debateId);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap fetchBydebateIdCategoryId(
        java.lang.Long debateCategoryPK, java.lang.Long debateId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchBydebateIdCategoryId(debateCategoryPK, debateId);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap fetchBydebateIdCategoryId(
        java.lang.Long debateCategoryPK, java.lang.Long debateId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchBydebateIdCategoryId(debateCategoryPK, debateId,
            retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateCategoryMap> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateCategoryMap> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateCategoryMap> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeBydebateIdCategoryId(
        java.lang.Long debateCategoryPK, java.lang.Long debateId)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryMapException,
            com.liferay.portal.SystemException {
        getPersistence().removeBydebateIdCategoryId(debateCategoryPK, debateId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countBydebateIdCategoryId(
        java.lang.Long debateCategoryPK, java.lang.Long debateId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countBydebateIdCategoryId(debateCategoryPK, debateId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebateCategoryMapPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateCategoryMapPersistence persistence) {
        _persistence = persistence;
    }
}
