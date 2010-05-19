package com.ext.portlet.debaterevision.service.persistence;

public class DebateCategoryUtil {
    private static DebateCategoryPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory) {
        getPersistence().cacheResult(debateCategory);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateCategory> debateCategories) {
        getPersistence().cacheResult(debateCategories);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debaterevision.model.DebateCategory create(
        java.lang.Long debateCategoryPK) {
        return getPersistence().create(debateCategoryPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategory remove(
        java.lang.Long debateCategoryPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateCategoryPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategory remove(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateCategory);
    }

    /**
     * @deprecated Use <code>update(DebateCategory debateCategory, boolean merge)</code>.
     */
    public static com.ext.portlet.debaterevision.model.DebateCategory update(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateCategory);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debaterevision.model.DebateCategory update(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateCategory, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategory updateImpl(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateCategory, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategory findByPrimaryKey(
        java.lang.Long debateCategoryPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateCategoryPK);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategory fetchByPrimaryKey(
        java.lang.Long debateCategoryPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateCategoryPK);
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

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateCategory> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateCategory> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateCategory> findAll(
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

    public static DebateCategoryPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateCategoryPersistence persistence) {
        _persistence = persistence;
    }
}
