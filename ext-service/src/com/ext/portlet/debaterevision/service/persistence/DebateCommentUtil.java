package com.ext.portlet.debaterevision.service.persistence;

public class DebateCommentUtil {
    private static DebateCommentPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debaterevision.model.DebateComment debateComment) {
        getPersistence().cacheResult(debateComment);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateComment> debateComments) {
        getPersistence().cacheResult(debateComments);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debaterevision.model.DebateComment create(
        java.lang.Long debateCommentId) {
        return getPersistence().create(debateCommentId);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment remove(
        java.lang.Long debateCommentId)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateCommentId);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment remove(
        com.ext.portlet.debaterevision.model.DebateComment debateComment)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateComment);
    }

    /**
     * @deprecated Use <code>update(DebateComment debateComment, boolean merge)</code>.
     */
    public static com.ext.portlet.debaterevision.model.DebateComment update(
        com.ext.portlet.debaterevision.model.DebateComment debateComment)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateComment);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateComment the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateComment is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debaterevision.model.DebateComment update(
        com.ext.portlet.debaterevision.model.DebateComment debateComment,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateComment, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment updateImpl(
        com.ext.portlet.debaterevision.model.DebateComment debateComment,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateComment, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment findByPrimaryKey(
        java.lang.Long debateCommentId)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateCommentId);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment fetchByPrimaryKey(
        java.lang.Long debateCommentId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateCommentId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findBycommentForDebateItem(
        java.lang.Long debateItemId) throws com.liferay.portal.SystemException {
        return getPersistence().findBycommentForDebateItem(debateItemId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findBycommentForDebateItem(
        java.lang.Long debateItemId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBycommentForDebateItem(debateItemId, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findBycommentForDebateItem(
        java.lang.Long debateItemId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBycommentForDebateItem(debateItemId, start, end, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment findBycommentForDebateItem_First(
        java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBycommentForDebateItem_First(debateItemId, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment findBycommentForDebateItem_Last(
        java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBycommentForDebateItem_Last(debateItemId, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment[] findBycommentForDebateItem_PrevAndNext(
        java.lang.Long debateCommentId, java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBycommentForDebateItem_PrevAndNext(debateCommentId,
            debateItemId, obc);
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

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeBycommentForDebateItem(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeBycommentForDebateItem(debateItemId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countBycommentForDebateItem(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBycommentForDebateItem(debateItemId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebateCommentPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateCommentPersistence persistence) {
        _persistence = persistence;
    }
}
